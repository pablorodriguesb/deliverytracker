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
            List<PontoRota> rota = entregador.getRotaOrdenada(); // precisa vir ordenada

            int indiceAtual = indiceAtualPorEntregador.getOrDefault(entregador.getId(), 0);

            if (indiceAtual < rota.size()) {
                PontoRota novoPonto = rota.get(indiceAtual);

                entregador.setLatitudeAtual(novoPonto.getLatitude());
                entregador.setLongitudeAtual(novoPonto.getLongitude());

                entregadorRepository.save(entregador);

                // Criar DTO e enviar pelo WebSocket
                PosicaoDTO posicaoDTO = new PosicaoDTO(
                        entregador.getId(),
                        entregador.getLatitudeAtual(),
                        entregador.getLongitudeAtual(),
                        entregador.getStatus().toString(),
                        LocalDateTime.now()
                );

                webSocketService.enviarPosicaoAtualizada(posicaoDTO);

                indiceAtualPorEntregador.put(entregador.getId(), indiceAtual + 1);
            }
        }
    }
}
