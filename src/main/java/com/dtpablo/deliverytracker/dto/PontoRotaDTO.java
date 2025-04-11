package com.dtpablo.deliverytracker.dto;

import java.time.Instant;
import com.dtpablo.deliverytracker.enums.Status;
import lombok.Data;

@Data
public class PontoRotaDTO {
    private Long entregadorId;
    private String nome;
    private Status status; // Referência ao enum Status
    private Double latitude;
    private Double longitude;
    private Instant timestamp;

    // Construtor personalizado
    public PontoRotaDTO(Long entregadorId, String nome, Status status, Double latitude, Double longitude, Instant timestamp) {
        this.entregadorId = entregadorId;
        this.nome = nome;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }
}
