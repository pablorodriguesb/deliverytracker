package com.dtpablo.deliverytracker.entity;

import com.dtpablo.deliverytracker.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.Comparator;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entregador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    // Novos campos para simulação da posição atual
    @Column(nullable = true)
    private Double latitudeAtual;

    @Column(nullable = true)
    private Double longitudeAtual;

    // Relacionamento com a rota do entregador
    @OneToMany(mappedBy = "entregador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PontoRota> rota;

    // Método para obter a rota em ordem
    public List<PontoRota> getRotaOrdenada() {
        return rota == null ? List.of() :
               rota.stream()
                   .sorted(Comparator.comparing(PontoRota::getOrdem))
                   .toList();
    }
}
