package com.dtpablo.deliverytracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class PontoDTO {
    private Long id;
    private Double latitude;
    private Double longitude;
    private Instant timestamp;
    private Boolean isCheckpoint;
}
