package com.dtpablo.deliverytracker.service;

import com.dtpablo.deliverytracker.dto.PosicaoDTO;
import com.dtpablo.deliverytracker.entity.Entregador;
import com.dtpablo.deliverytracker.entity.PontoRota;
import com.dtpablo.deliverytracker.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class SimuladorLocalizacaoServiceTest {

    @Mock
    private PontoRotaService pontoRotaService;

    @Mock
    private WebSocketService webSocketService;

    @InjectMocks
    private SimuladorLocalizacaoService simuladorLocalizacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicia os mocks
    }

    @Test
    void testSimularMovimento() {
        // Dados de teste
        Entregador entregador = new Entregador();
        entregador.setId(1L);
        entregador.setNome("Entregador 1");
        entregador.setStatus(Status.ATIVO);
        entregador.setLatitude(10.0);
        entregador.setLongitude(20.0);
        entregador.setUltimaAtualizacao(LocalDateTime.now());

        PontoRota pontoRota = new PontoRota();
        pontoRota.setEntregador(entregador);
        pontoRota.setLatitude(10.1);
        pontoRota.setLongitude(20.1);

        // Simulando o comportamento do PontoRotaService
        when(pontoRotaService.buscarTodosOrdenadosPorTempo()).thenReturn(Arrays.asList(pontoRota));

        // Chama o método de simulação
        simuladorLocalizacaoService.simularMovimento();

        // Verifica se o método de envio via WebSocket foi chamado
        verify(webSocketService, times(1)).enviarPosicao(any(PosicaoDTO.class));

        // Verifica se o entregador teve sua posição atualizada
        assert entregador.getLatitude() == 10.1;
        assert entregador.getLongitude() == 20.1;
    }

    @Test
    void testIniciarSimulacao() {
        simuladorLocalizacaoService.iniciarSimulacao();

    }

    @Test
    void testPararSimulacao() {
        simuladorLocalizacaoService.pararSimulacao();

    }
}
