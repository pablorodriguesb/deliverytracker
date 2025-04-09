package com.dtpablo.deliverytracker.controller;

import com.dtpablo.deliverytracker.entity.Entregador;
import com.dtpablo.deliverytracker.repository.EntregadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entregadores")
@RequiredArgsConstructor
public class EntregadorController {

    private final EntregadorRepository entregadorRepository;

    @GetMapping
    public List<Entregador> listar() {
        return entregadorRepository.findAll();
    }

    @PostMapping
    public Entregador criar(@RequestBody Entregador entregador) {
        return entregadorRepository.save(entregador);
    }
}

