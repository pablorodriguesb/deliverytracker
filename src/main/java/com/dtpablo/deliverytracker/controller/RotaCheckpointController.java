package com.dtpablo.deliverytracker.controller;

import com.dtpablo.deliverytracker.dto.RotaCheckpointDTO;
import com.dtpablo.deliverytracker.entity.RotaCheckpoint;
import com.dtpablo.deliverytracker.service.RotaCheckpointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkpoints")
@RequiredArgsConstructor
public class RotaCheckpointController {

    private final RotaCheckpointService service;

    @PostMapping
    public ResponseEntity<RotaCheckpoint> salvar(@RequestBody RotaCheckpointDTO dto) {
        RotaCheckpoint salvo = service.salvar(dto);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/rota/{rotaId}")
    public ResponseEntity<List<RotaCheckpoint>> listarPorRota(@PathVariable Long rotaId) {
        List<RotaCheckpoint> lista = service.listarPorRota(rotaId);
        return ResponseEntity.ok(lista);
    }
}
