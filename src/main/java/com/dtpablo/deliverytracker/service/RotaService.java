package com.dtpablo.deliverytracker.service;

import com.dtpablo.deliverytracker.entity.PontoRota;
import com.dtpablo.deliverytracker.entity.Rota;
import com.dtpablo.deliverytracker.repository.PontoRotaRepository;
import com.dtpablo.deliverytracker.repository.RotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RotaService {

    private final RotaRepository rotaRepository;
    private final PontoRotaRepository pontoRotaRepository;

    // Criar uma nova rota
    public Rota criar(Rota rota) {
        return rotaRepository.save(rota);
    }

    // Listar todas as rotas
    public List<Rota> listarTodas() {
        return rotaRepository.findAll();
    }

    // Listar rotas por entregador
    public List<Rota> listarPorEntregador(Long entregadorId) {
        return rotaRepository.findByEntregadorId(entregadorId);
    }

    // Listar pontos de uma rota ordenados por timestamp
    public List<PontoRota> listarPontosPorRota(Long rotaId) {
        return pontoRotaRepository.findByRota_IdOrderByTimestampAsc(rotaId);  // Usando o repositório correto
    }
}