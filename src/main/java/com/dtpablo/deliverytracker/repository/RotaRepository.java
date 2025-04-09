package com.dtpablo.deliverytracker.repository;

import com.dtpablo.deliverytracker.entity.Rota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RotaRepository extends JpaRepository<Rota, Long> {
    List<Rota> findByEntregadorId(Long entregadorId);
}
