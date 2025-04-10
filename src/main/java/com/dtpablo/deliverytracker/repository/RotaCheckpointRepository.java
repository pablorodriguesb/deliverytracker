package com.dtpablo.deliverytracker.repository;

import com.dtpablo.deliverytracker.entity.RotaCheckpoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RotaCheckpointRepository extends JpaRepository<RotaCheckpoint, Long> {
    List<RotaCheckpoint> findAllByRotaId(Long rotaId);
}
