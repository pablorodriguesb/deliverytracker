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

    @GetMapping("/{entregadorId}")
    public List<Rota> listarPorEntregador(@PathVariable Long entregadorId) {
        return rotaRepository.findByEntregadorId(entregadorId);
    }
}

