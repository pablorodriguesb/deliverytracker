package com.dtpablo.deliverytracker.dto;

import com.dtpablo.deliverytracker.entity.PontoRota;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RotaDTO {

    private Long id;
    private String nome;  // Adicionando o campo nome
    private String nomeEntregador;
    private List<PontoRota> pontos;
}
