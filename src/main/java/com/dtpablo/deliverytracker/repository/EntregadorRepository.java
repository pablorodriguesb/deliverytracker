package com.dtpablo.deliverytracker.repository;

import com.dtpablo.deliverytracker.entity.Entregador;
import com.dtpablo.deliverytracker.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EntregadorRepository extends JpaRepository<Entregador, Long> {
    List<Entregador> findByStatus(Status status);
}

