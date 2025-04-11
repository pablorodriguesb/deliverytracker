package com.dtpablo.deliverytracker.service;

import com.dtpablo.deliverytracker.dto.PontoRotaDTO;
import com.dtpablo.deliverytracker.entity.PontoRota;
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

    // Guarda o índice atual da simulação por entregador
    private final Map<Long, Integer> entregadorIndicePontoAtual = new ConcurrentHashMap<>();
    private boolean simulacaoAtiva = false;

    public SimuladorLocalizacaoService(PontoRotaService pontoRotaService, WebSocketService webSocketService) {
        this.pontoRotaService = pontoRotaService;
        this.webSocketService = webSocketService;
    }

    public void iniciarSimulacao() {
        log.info("Simulação de localização iniciada.");
        simulacaoAtiva = true;
        simularMovimento(); // Executa uma vez imediatamente
    }

    public void pararSimulacao() {
        log.info("Simulação de localização pausada.");
        simulacaoAtiva = false;
        entregadorIndicePontoAtual.clear();
    }

    @Scheduled(fixedRate = 5000)
    public void simularMovimento() {
        if (!simulacaoAtiva) return;

        List<PontoRota> todosPontos = pontoRotaService.buscarTodosOrdenadosPorTempo();

        if (todosPontos.isEmpty()) {
            log.warn("Nenhum ponto de rota encontrado.");
            return;
        }

        Map<Long, List<PontoRota>> pontosPorEntregador = todosPontos.stream()
                .collect(Collectors.groupingBy(p -> p.getRota().getEntregador().getId()));


        for (Map.Entry<Long, List<PontoRota>> entry : pontosPorEntregador.entrySet()) {
            Long entregadorId = entry.getKey();
            List<PontoRota> pontos = entry.getValue();

            if (pontos.isEmpty()) continue;

            int indiceAtual = entregadorIndicePontoAtual.getOrDefault(entregadorId, 0);
            if (indiceAtual >= pontos.size() - 1) {
                // Entregador chegou ao fim da rota
                continue;
            }

            PontoRota pontoAtual = pontos.get(indiceAtual);
            entregadorIndicePontoAtual.put(entregadorId, indiceAtual + 1);

            PontoRotaDTO pontoRotaDTO = new PontoRotaDTO(
                    entregadorId,
                    pontoAtual.getRota().getEntregador().getNome(),
                    Status.EM_ROTA, // Simulando como se estivesse em rota
                    pontoAtual.getLatitude(),
                    pontoAtual.getLongitude(),
                    LocalDateTime.now().toInstant(ZoneOffset.UTC)
            );

            webSocketService.enviarPosicao(pontoRotaDTO);

            log.info("Entregador {} → posição simulada {}/{}: ({}, {})",
                    entregadorId, indiceAtual + 1, pontos.size(),
                    pontoAtual.getLatitude(), pontoAtual.getLongitude());
        }
    }

    public void resetarSimulacao() {
        pararSimulacao();
        log.info("Simulação resetada.");
    }
}
