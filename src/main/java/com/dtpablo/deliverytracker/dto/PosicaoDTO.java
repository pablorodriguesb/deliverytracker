package com.dtpablo.deliverytracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PosicaoDTO {
    private Long entregadorId;
    private Double latitude;
    private Double longitude;
    private String status;
    private LocalDateTime timestamp;
}
