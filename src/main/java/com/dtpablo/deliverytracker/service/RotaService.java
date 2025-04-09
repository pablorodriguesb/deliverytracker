package com.dtpablo.deliverytracker.service;

import com.dtpablo.deliverytracker.entity.Rota;
import com.dtpablo.deliverytracker.repository.RotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RotaService {

    private final RotaRepository rotaRepository;

    public Rota criar(Rota rota) {
        return rotaRepository.save(rota);
    }

    public List<Rota> listarTodas() {
        return rotaRepository.findAll();
    }

    public List<Rota> listarPorEntregador(Long entregadorId) {
        return rotaRepository.findByEntregadorId(entregadorId);
    }
}
