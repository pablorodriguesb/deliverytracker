package com.dtpablo.deliverytracker.service;

import com.dtpablo.deliverytracker.entity.PontoRota;
import com.dtpablo.deliverytracker.repository.PontoRotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PontoRotaService {

    private final PontoRotaRepository pontoRotaRepository;

    // Método para listar pontos de um entregador, ordenados por timestamp
    public List<PontoRota> listarPontosPorEntregador(Long entregadorId) {
        return pontoRotaRepository.findByEntregador_IdOrderByTimestampAsc(entregadorId);
    }

    // Método para salvar um ponto de rota
    public PontoRota salvarPonto(PontoRota ponto) {
        return pontoRotaRepository.save(ponto);
    }

    // Método para listar todos os pontos de rota, ordenados por timestamp
    public List<PontoRota> buscarTodosOrdenadosPorTempo() {
        return pontoRotaRepository.findAllByOrderByTimestampAsc();
    }

    // Método para listar os pontos que são checkpoints em uma rota específica
    public List<PontoRota> listarCheckpointsPorRota(Long rotaId) {
        return pontoRotaRepository.findByRota_IdAndIsCheckpointTrue(rotaId);
    }
}
