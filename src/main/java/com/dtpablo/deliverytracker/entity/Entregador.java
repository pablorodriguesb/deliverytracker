package com.dtpablo.deliverytracker.entity;

import com.dtpablo.deliverytracker.enums.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "entregadores")
@Data
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

    // Relacionamento com PontoRota
    @OneToMany(mappedBy = "entregador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference  // Evita recursão infinita na serialização
    @OrderBy("timestamp ASC")  // Ordena os pontos pela data de timestamp
    private List<PontoRota> rotaOrdenada;

    // Adicionando um status para localização
    private String localizacaoStatus;  // Ex: "em movimento", "parado"

    // Métodos setters para latitude e longitude
    public void setLatitudeAtual(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitudeAtual(Double longitude) {
        this.longitude = longitude;
    }
}
