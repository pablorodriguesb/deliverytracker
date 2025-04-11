package com.dtpablo.deliverytracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RotaDTO {
    private Long id;
    private String nome;
    private String nomeEntregador;
    private List<PontoDTO> pontos;
}
