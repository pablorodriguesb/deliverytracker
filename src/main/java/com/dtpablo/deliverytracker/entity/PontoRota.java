package com.dtpablo.deliverytracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "pontos_rota")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PontoRota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rota_id", nullable = false)
    @JsonIgnore // Controla a serialização do lado de PontoRota
    private Rota rota;

    private Double latitude;
    private Double longitude;

    @Column(name = "timestamp")
    private Instant timestamp;

    // Campo adicional para marcar se é um checkpoint
    @Column(name = "is_checkpoint")
    private Boolean isCheckpoint;

}