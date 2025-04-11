package com.dtpablo.deliverytracker.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "rotas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    // Relacionamento com Entregador (Muitos rotas podem ter um entregador)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entregador_id", nullable = false)  // Defina a coluna corretamente no banco
    @JsonManagedReference  // Controla a serialização do lado da Rota
    private Entregador entregador;

    @OneToMany(mappedBy = "rota", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // Controla a serialização do lado de PontoRota
    private List<PontoRota> pontos;

}
