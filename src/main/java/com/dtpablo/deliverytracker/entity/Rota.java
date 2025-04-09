package com.dtpablo.deliverytracker.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Entregador entregador;

    private Double pontoInicioLat;
    private Double pontoInicioLng;

    private Double pontoFimLat;
    private Double pontoFimLng;

    @ElementCollection
    private List<String> checkpoints;
}
