package com.dtpablo.deliverytracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entregador_id", nullable = false)
    @JsonIgnore
    private Entregador entregador;

    @OneToMany(mappedBy = "rota", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("ordem ASC")
    private List<PontoRota> pontos = new ArrayList<>();

    @OneToMany(mappedBy = "rota", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RotaCheckpoint> checkpoints = new ArrayList<>();

    private String nome;
}