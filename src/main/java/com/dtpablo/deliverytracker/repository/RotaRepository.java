package com.dtpablo.deliverytracker.repository;

import com.dtpablo.deliverytracker.entity.Rota;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface RotaRepository extends JpaRepository<Rota, Long> {

    @EntityGraph(attributePaths = {"pontos"})
    Optional<Rota> findById(Long id);

    List<Rota> findByEntregadorId(Long entregadorId);
}
