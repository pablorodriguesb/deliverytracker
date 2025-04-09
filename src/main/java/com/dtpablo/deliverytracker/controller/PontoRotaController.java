package com.dtpablo.deliverytracker.controller;

import com.dtpablo.deliverytracker.entity.PontoRota;
import com.dtpablo.deliverytracker.service.PontoRotaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pontos")
@CrossOrigin(origins = "*")
public class PontoRotaController {

    private final PontoRotaService pontoRotaService;

    public PontoRotaController(PontoRotaService pontoRotaService) {
        this.pontoRotaService = pontoRotaService;
    }

    @GetMapping("/{entregadorId}")
    public List<PontoRota> listarPontos(@PathVariable Long entregadorId) {
        return pontoRotaService.listarPontosPorEntregador(entregadorId);
    }

    @PostMapping
    public PontoRota salvar(@RequestBody PontoRota pontoRota) {
        return pontoRotaService.salvarPonto(pontoRota);
    }
}