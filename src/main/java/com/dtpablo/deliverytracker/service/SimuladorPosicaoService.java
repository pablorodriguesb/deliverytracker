package com.dtpablo.deliverytracker.service;

import com.dtpablo.deliverytracker.dto.PosicaoDTO;
import com.dtpablo.deliverytracker.entity.Entregador;
import com.dtpablo.deliverytracker.entity.PontoRota;
import com.dtpablo.deliverytracker.enums.Status;
import com.dtpablo.deliverytracker.repository.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SimuladorPosicaoService {

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Autowired
    private WebSocketService webSocketService;

    private final Map<Long, Integer> indiceAtualPorEntregador = new HashMap<>();

    @Scheduled(fixedRate = 5000)
    public void simularMovimentacao() {
        List<Entregador> entregadoresAtivos = entregadorRepository.findByStatus(Status.ATIVO);

        for (Entregador entregador : entregadoresAtivos) {
            List<PontoRota> rota = entregador.getRotaOrdenada(); // A rota já deve estar ordenada

            // Pegando o índice atual ou iniciando com 0
            int indiceAtual = indiceAtualPorEntregador.getOrDefault(entregador.getId(), 0);

            // Verificando se o índice atual ainda está dentro do tamanho da rota
            if (indiceAtual < rota.size()) {
                PontoRota novoPonto = rota.get(indiceAtual);

                // Atualizando as coordenadas do entregador
                entregador.setLatitudeAtual(novoPonto.getLatitude());
                entregador.setLongitudeAtual(novoPonto.getLongitude());

                // Atualizando o campo de última atualização
                entregador.setUltimaAtualizacao(LocalDateTime.now());

                // Salvando o entregador com a posição atualizada
                entregadorRepository.save(entregador);

                // Criando o DTO para a posição e enviando via WebSocket
                PosicaoDTO posicaoDTO = new PosicaoDTO(
                        entregador.getId(),
                        entregador.getNome(),
                        entregador.getStatus(),
                        entregador.getLatitudeAtual(),
                        entregador.getLongitudeAtual(),
                        LocalDateTime.now().toInstant(ZoneOffset.UTC) // Usando a hora atual para o timestamp
                );

                // Enviando a posição via WebSocket
                webSocketService.enviarPosicao(posicaoDTO);

                // Atualizando o índice para o próximo ponto na rota
                indiceAtualPorEntregador.put(entregador.getId(), indiceAtual + 1);
            } else {
                // Se o entregador percorreu toda a rota, reiniciar o índice ou parar
                indiceAtualPorEntregador.put(entregador.getId(), 0); // Recomeçar ou pode definir outro comportamento
            }
        }
    }
}
