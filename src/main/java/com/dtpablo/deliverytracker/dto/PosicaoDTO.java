package com.dtpablo.deliverytracker.dto;

import java.time.Instant;
import com.dtpablo.deliverytracker.enums.Status;

public record PosicaoDTO(
        Long entregadorId,
        String nome,
        Status status,
        Double latitude,
        Double longitude,
        Instant timestamp
) {}
