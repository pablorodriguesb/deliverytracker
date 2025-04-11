package com.dtpablo.deliverytracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "rotas")
@Getter
@Setter
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
    @JsonIgnore  // Controla a serialização do lado da Rota
    @ToString.Exclude
    private Entregador entregador;

    @OneToMany(mappedBy = "rota", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // Controla a serialização do lado de PontoRota
    @ToString.Exclude
    private List<PontoRota> pontos;

}
