package com.dtpablo.deliverytracker.controller;

import com.dtpablo.deliverytracker.dto.RotaDTO;
import com.dtpablo.deliverytracker.entity.Rota;
import com.dtpablo.deliverytracker.entity.PontoRota;
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
        // Obtendo as rotas
        List<Rota> rotas = rotaService.listarTodas();

        // Verificando se as rotas estão vazias
        if (rotas.isEmpty()) {
            return ResponseEntity.noContent().build();  // Retorna 204 No Content
        }

        // Log para ajudar na depuração
        System.out.println("Rotas encontradas: " + rotas);

        // Converte as rotas para DTO
        List<RotaDTO> rotasDTO = rotas.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(rotasDTO);  // Retorna as rotas convertidas
    }

    @GetMapping("/{entregadorId}")
    public ResponseEntity<List<RotaDTO>> listarPorEntregador(@PathVariable Long entregadorId) {
        // Obtendo as rotas por entregador
        List<Rota> rotas = rotaService.listarPorEntregador(entregadorId);

        // Verificando se as rotas estão vazias
        if (rotas.isEmpty()) {
            return ResponseEntity.noContent().build();  // Retorna 204 No Content
        }

        // Converte as rotas para DTO
        List<RotaDTO> rotasDTO = rotas.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(rotasDTO);  // Retorna as rotas convertidas
    }

    // Método para converter Rota para RotaDTO
    private RotaDTO converterParaDTO(Rota rota) {
        // Obtendo o nome do entregador
        String nomeEntregador = (rota.getEntregador() != null) ? rota.getEntregador().getNome() : null;

        // Acessando os pontos da rota
        List<PontoRota> pontos = rota.getPontos();

        // Retornando o DTO com os dados da rota
        return new RotaDTO(
                rota.getId(),
                rota.getNome(),
                nomeEntregador,
                pontos
        );
    }
}
