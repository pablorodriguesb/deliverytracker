package com.dtpablo.deliverytracker.repository;

import com.dtpablo.deliverytracker.entity.PontoRota;
import com.dtpablo.deliverytracker.entity.Rota;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RotaRepository extends JpaRepository<Rota, Long> {

    @Override
    @EntityGraph(attributePaths = {"pontos"})
    Optional<Rota> findById(Long id);

    @Override
    @EntityGraph(attributePaths = {"pontos"})
    List<Rota> findAll();

    @Query("SELECT r FROM Rota r JOIN FETCH r.pontos WHERE r.entregador.id = :entregadorId")
    List<Rota> findByEntregadorId(Long entregadorId);


}
