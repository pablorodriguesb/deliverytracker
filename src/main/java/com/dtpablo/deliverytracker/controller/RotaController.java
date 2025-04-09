package com.dtpablo.deliverytracker.controller;

import com.dtpablo.deliverytracker.dto.RotaDTO;
import com.dtpablo.deliverytracker.entity.Rota;
import com.dtpablo.deliverytracker.entity.PontoRota; // Importando PontoRota
import com.dtpablo.deliverytracker.service.RotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rotas")
@RequiredArgsConstructor
public class RotaController {

    private final RotaService rotaService;

    @PostMapping
    public ResponseEntity<Rota> criar(@RequestBody Rota rota) {
        Rota novaRota = rotaService.criar(rota);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaRota);
    }

    @GetMapping
    public ResponseEntity<List<RotaDTO>> listarTodas() {
        List<Rota> rotas = rotaService.listarTodas();
        // Converte a lista de rotas para RotaDTO
        List<RotaDTO> rotasDTO = rotas.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(rotasDTO);
    }

    @GetMapping("/{entregadorId}")
    public ResponseEntity<List<RotaDTO>> listarPorEntregador(@PathVariable Long entregadorId) {
        List<Rota> rotas = rotaService.listarPorEntregador(entregadorId);
        // Converte a lista de rotas para RotaDTO
        List<RotaDTO> rotasDTO = rotas.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(rotasDTO);
    }

    // Método para converter Rota para RotaDTO
    private RotaDTO converterParaDTO(Rota rota) {
        String nomeEntregador = (rota.getEntregador() != null) ? rota.getEntregador().getNome() : null;

        // Incluindo os pontos associados à rota no DTO
        List<PontoRota> pontos = rota.getPontos(); // Acessando os pontos da rota
        return new RotaDTO(
                rota.getId(),
                rota.getNome(),  // Agora o campo nome pode ser acessado aqui
                nomeEntregador,
                pontos // Incluindo os pontos no DTO
        );
    }
}
