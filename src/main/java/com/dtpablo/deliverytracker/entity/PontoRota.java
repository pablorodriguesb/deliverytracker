package com.dtpablo.deliverytracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "pontos_rota")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PontoRota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rota_id", nullable = false)
    @JsonBackReference // Controla a serialização do lado de PontoRota
    private Rota rota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entregador_id", nullable = false)
    @JsonIgnore
    private Entregador entregador;

    private Double latitude;
    private Double longitude;

    @Column(name = "timestamp")
    private Instant timestamp;

    // Campo adicional para marcar se é um checkpoint
    @Column(name = "is_checkpoint")
    private Boolean isCheckpoint;  // Use o tipo correto para indicar se é um checkpoint

}