package com.dtpablo.deliverytracker.service;

import com.dtpablo.deliverytracker.dto.RotaCheckpointDTO;
import com.dtpablo.deliverytracker.entity.Rota;
import com.dtpablo.deliverytracker.entity.RotaCheckpoint;
import com.dtpablo.deliverytracker.repository.RotaCheckpointRepository;
import com.dtpablo.deliverytracker.repository.RotaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RotaCheckpointService {

    private final RotaCheckpointRepository checkpointRepository;
    private final RotaRepository rotaRepository;

    @Transactional
    public RotaCheckpoint salvar(RotaCheckpointDTO dto) {
        Rota rota = rotaRepository.findById(dto.getRotaId())
                .orElseThrow(() -> new IllegalArgumentException("Rota não encontrada com ID: " + dto.getRotaId()));

        RotaCheckpoint checkpoint = RotaCheckpoint.builder()
                .rota(rota)
                .checkpoints(dto.getCheckpoints())
                .build();

        return checkpointRepository.save(checkpoint);
    }

    public List<RotaCheckpoint> listarPorRota(Long rotaId) {
        return checkpointRepository.findAllByRotaId(rotaId);
    }
}
