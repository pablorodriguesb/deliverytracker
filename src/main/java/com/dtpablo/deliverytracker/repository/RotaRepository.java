package com.dtpablo.deliverytracker.repository;

import com.dtpablo.deliverytracker.entity.PontoRota;
import com.dtpablo.deliverytracker.entity.Rota;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RotaRepository extends JpaRepository<Rota, Long> {

    @Override
    @EntityGraph(attributePaths = {"pontos"})
    Optional<Rota> findById(Long id);

    @Override
    @EntityGraph(attributePaths = {"pontos"})
    List<Rota> findAll();

    List<Rota> findByEntregadorId(Long entregadorId);


}
