package com.dtpablo.deliverytracker.service;

import com.dtpablo.deliverytracker.dto.PontoRotaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void enviarPosicao(PontoRotaDTO pontoRotaDTO) {
        log.debug("Enviando posição para o tópico WebSocket: {}", pontoRotaDTO);
        messagingTemplate.convertAndSend("/topic/posicoes", pontoRotaDTO);
    }
}
