package com.dtpablo.deliverytracker.repository;

import com.dtpablo.deliverytracker.entity.PontoRota;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PontoRotaRepository extends JpaRepository<PontoRota, Long> {
    List<PontoRota> findByEntregador_IdOrderByTimestampAsc(Long entregadorId);

    // Método para buscar todos os pontos ordenados
    List<PontoRota> findAllByOrderByTimestampAsc();
}
