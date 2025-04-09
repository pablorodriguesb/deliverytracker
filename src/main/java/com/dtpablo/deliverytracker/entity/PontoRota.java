package com.dtpablo.deliverytracker.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PontoRota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com o entregador
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entregador_id", nullable = false)
    private Entregador entregador;  // Adicionado o relacionamento com Entregador

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rota_id", nullable = false)
    private Rota rota;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // Campo para definir a ordem do ponto na rota
    @Column(nullable = false)
    private Integer ordem;
}
