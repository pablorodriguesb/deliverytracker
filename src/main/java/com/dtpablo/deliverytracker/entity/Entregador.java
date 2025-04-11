package com.dtpablo.deliverytracker.entity;

import com.dtpablo.deliverytracker.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "entregadores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entregador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Double latitude;
    private Double longitude;

    @Column(name = "ultima_atualizacao")
    private LocalDateTime ultimaAtualizacao;

    @OneToMany(mappedBy = "entregador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude // removendo recursão infinita
    private List<Rota> rotas;

    private String localizacaoStatus;

    public void setLatitudeAtual(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitudeAtual(Double longitude) {
        this.longitude = longitude;
    }
}
