package com.dtpablo.deliverytracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
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
    @OrderBy("ordem ASC") // ordena os pontos automaticamente pela ordem
    private List<PontoRota> pontos;

    private String nome;
}
