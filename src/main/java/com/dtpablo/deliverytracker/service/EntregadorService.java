package com.dtpablo.deliverytracker.service;

import com.dtpablo.deliverytracker.dto.PontoRotaDTO;
import com.dtpablo.deliverytracker.entity.Entregador;
import com.dtpablo.deliverytracker.entity.PontoRota;
import com.dtpablo.deliverytracker.repository.PontoRotaRepository;
import com.dtpablo.deliverytracker.enums.Status;
import com.dtpablo.deliverytracker.repository.EntregadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntregadorService {

    private final EntregadorRepository entregadorRepository;
    private final PontoRotaRepository pontoRotaRepository;

    /**
     * Método para atualizar a localização do entregador e o seu status.
     */
    public void atualizarLocalizacao(Long entregadorId, double latitude, double longitude) {
        Entregador entregador = entregadorRepository.findById(entregadorId)
                .orElseThrow(() -> new IllegalArgumentException("Entregador não encontrado"));

        entregador.setLatitude(latitude);
        entregador.setLongitude(longitude);
        entregador.setUltimaAtualizacao(java.time.LocalDateTime.now());
        entregador.setStatus(Status.EM_ROTA);

        entregadorRepository.save(entregador);
    }

    /**
     * Lista todas as posições atuais dos entregadores.
     */
    public List<PontoRotaDTO> listarPosicoesAtuais() {
        List<Entregador> entregadores = entregadorRepository.findAll();

        return entregadores.stream()
                .map(entregador -> {
                    List<PontoRota> pontos = pontoRotaRepository.findByEntregador_IdOrderByTimestampAsc(entregador.getId());

                    PontoRota ultimoPonto = pontos.stream()
                            .max(Comparator.comparing(PontoRota::getTimestamp)) // Acessando corretamente o timestamp
                            .orElse(null);

                    if (ultimoPonto == null) return null;

                    // Convertendo o LocalDateTime para Instant corretamente
                    return new PontoRotaDTO(
                            entregador.getId(),
                            entregador.getNome(),
                            entregador.getStatus(),
                            ultimoPonto.getLatitude(),
                            ultimoPonto.getLongitude(),
                            ultimoPonto.getTimestamp()
                    );
                })
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }
}
