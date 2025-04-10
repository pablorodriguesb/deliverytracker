package com.dtpablo.deliverytracker.entity;

import com.dtpablo.deliverytracker.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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

    @Column(nullable = true)
    private Double latitudeAtual;

    @Column(nullable = true)
    private Double longitudeAtual;

    @OneToMany(mappedBy = "entregador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("ordem ASC")
    private List<PontoRota> rota;

    // Campo para armazenar a data/hora da última atualização
    @Column(nullable = true)
    private LocalDateTime ultimaAtualizacao;

    public List<PontoRota> getRotaOrdenada() {
        return rota == null ? List.of() :
                rota.stream()
                        .sorted(Comparator.comparing(PontoRota::getOrdem))
                        .toList();
    }
}
