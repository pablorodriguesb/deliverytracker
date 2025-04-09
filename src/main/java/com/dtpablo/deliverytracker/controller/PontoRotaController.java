package com.dtpablo.deliverytracker.controller;

import com.dtpablo.deliverytracker.entity.PontoRota;
import com.dtpablo.deliverytracker.service.PontoRotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pontos")
@RequiredArgsConstructor
public class PontoRotaController {

    private final PontoRotaService pontoRotaService;

    @GetMapping("/entregador/{entregadorId}")
    public ResponseEntity<List<PontoRota>> listarPorEntregador(@PathVariable Long entregadorId) {
        List<PontoRota> pontos = pontoRotaService.listarPontosPorEntregador(entregadorId);
        return ResponseEntity.ok(pontos);
    }

    @PostMapping
    public ResponseEntity<PontoRota> salvar(@RequestBody PontoRota ponto) {
        PontoRota novoPonto = pontoRotaService.salvarPonto(ponto);
        return ResponseEntity.ok(novoPonto);
    }
}
