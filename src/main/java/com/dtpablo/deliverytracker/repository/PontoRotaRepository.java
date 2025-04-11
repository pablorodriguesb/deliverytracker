package com.dtpablo.deliverytracker.repository;

import com.dtpablo.deliverytracker.entity.PontoRota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PontoRotaRepository extends JpaRepository<PontoRota, Long> {

    // Método para buscar pontos de um entregador, ordenados por timestamp
    List<PontoRota> findByRota_Entregador_IdOrderByTimestampAsc(Long entregadorId);

}
