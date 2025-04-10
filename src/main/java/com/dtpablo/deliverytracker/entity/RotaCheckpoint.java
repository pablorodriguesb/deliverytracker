package com.dtpablo.deliverytracker.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rota_checkpoints")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RotaCheckpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rota_id", nullable = false)
    private Rota rota;

    private String checkpoints;
}
