package com.dtpablo.deliverytracker.controller;

import com.dtpablo.deliverytracker.entity.Rota;
import com.dtpablo.deliverytracker.repository.RotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rotas")
@RequiredArgsConstructor
public class RotaController {

    private final RotaRepository rotaRepository;

    @PostMapping
    public Rota criar(@RequestBody Rota rota) {
        return rotaRepository.save(rota);
    }

    //  NOVO: Listar todas as rotas
    @GetMapping
    public List<Rota> listarTodas() {
        return rotaRepository.findAll();
    }

    // ? Rota específica por entregador
    @GetMapping("/{entregadorId}")
    public List<Rota> listarPorEntregador(@PathVariable Long entregadorId) {
        return rotaRepository.findByEntregadorId(entregadorId);
    }
}
