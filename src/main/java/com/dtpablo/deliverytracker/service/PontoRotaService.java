package com.dtpablo.deliverytracker.service;

import com.dtpablo.deliverytracker.entity.PontoRota;
import com.dtpablo.deliverytracker.repository.PontoRotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PontoRotaService {

    private final PontoRotaRepository pontoRotaRepository;

    public PontoRotaService(PontoRotaRepository pontoRotaRepository) {
        this.pontoRotaRepository = pontoRotaRepository;
    }

    public List<PontoRota> listarPontosPorEntregador(Long entregadorId) {
        return pontoRotaRepository.findByEntregador_IdOrderByTimestampAsc(entregadorId);
    }

    public PontoRota salvarPonto(PontoRota ponto) {
        return pontoRotaRepository.save(ponto);
    }
}
