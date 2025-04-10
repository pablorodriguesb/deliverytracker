package com.dtpablo.deliverytracker.service;

import com.dtpablo.deliverytracker.entity.Entregador;
import com.dtpablo.deliverytracker.entity.PontoRota;
import com.dtpablo.deliverytracker.dto.PosicaoDTO;
import com.dtpablo.deliverytracker.enums.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SimuladorLocalizacaoService {

    private final PontoRotaService pontoRotaService;
    private final WebSocketService webSocketService;

    private final Map<Long, Integer> entregadorIndicePontoAtual = new ConcurrentHashMap<>();
    private boolean simulacaoAtiva = false;

    public SimuladorLocalizacaoService(PontoRotaService pontoRotaService, WebSocketService webSocketService) {
        this.pontoRotaService = pontoRotaService;
        this.webSocketService = webSocketService;
    }

    /**
     * Inicia a simulação de movimentação.
     */
    public void iniciarSimulacao() {
        log.info("Simulação de entregadores iniciada.");
        this.simulacaoAtiva = true;
        simularMovimento(); // Executa imediatamente ao iniciar
    }

    /**
     * Pausa a simulação.
     */
    public void pararSimulacao() {
        log.info("Simulação de entregadores pausada.");
        this.simulacaoAtiva = false;
        entregadorIndicePontoAtual.clear(); // Limpa o índice quando a simulação é pausada
    }

    /**
     * Roda a cada 5 segundos, atualizando as posições.
     */
    @Scheduled(fixedRate = 5000)
    public void simularMovimento() {
        if (!simulacaoAtiva) return;

        log.info("Atualizando posições dos entregadores...");

        List<PontoRota> todosPontos = pontoRotaService.buscarTodosOrdenadosPorTempo();

        if (todosPontos.isEmpty()) {
            log.warn("Nenhum ponto de rota encontrado.");
            return;
        }

        Map<Long, List<PontoRota>> pontosPorEntregador = todosPontos.stream()
                .collect(Collectors.groupingBy(p -> p.getEntregador().getId()));

        for (Map.Entry<Long, List<PontoRota>> entry : pontosPorEntregador.entrySet()) {
            Long entregadorId = entry.getKey();
            List<PontoRota> pontos = entry.getValue();

            if (pontos.isEmpty()) continue;

            Entregador entregador = pontos.get(0).getEntregador();
            if (entregador.getStatus() == Status.INATIVO) continue;

            int indiceAtual = entregadorIndicePontoAtual.getOrDefault(entregadorId, 0);
            if (indiceAtual >= pontos.size() - 1) {
                // Não move o entregador se estiver no último ponto da rota
                continue;
            } else {
                indiceAtual++;
            }

            PontoRota novaPosicao = pontos.get(indiceAtual);
            entregador.setLatitude(novaPosicao.getLatitude());
            entregador.setLongitude(novaPosicao.getLongitude());
            entregador.setUltimaAtualizacao(LocalDateTime.now());

            entregadorIndicePontoAtual.put(entregadorId, indiceAtual);

            log.debug("Entregador {} movido para a posição {}, {}", entregador.getId(), novaPosicao.getLatitude(), novaPosicao.getLongitude());

            // Criando o PosicaoDTO com os dados necessários
            PosicaoDTO posicaoDTO = new PosicaoDTO(
                    entregador.getId(),
                    entregador.getNome(),
                    entregador.getStatus(),
                    entregador.getLatitude(),
                    entregador.getLongitude(),
                    entregador.getUltimaAtualizacao().toInstant(ZoneOffset.UTC)
            );

            // Enviando a posição do entregador via WebSocket
            webSocketService.enviarPosicao(posicaoDTO);

            log.info("Entregador {} ({}) → Ponto {}/{} enviado.",
                    entregador.getId(), entregador.getNome(), indiceAtual + 1, pontos.size());
        }
    }

    /**
     * Reseta a simulação.
     */
    public void resetarSimulacao() {
        entregadorIndicePontoAtual.clear();
        pararSimulacao();
        log.info("Simulação resetada.");
    }
}
