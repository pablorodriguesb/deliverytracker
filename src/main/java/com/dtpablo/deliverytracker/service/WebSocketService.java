package com.dtpablo.deliverytracker.service;

import com.dtpablo.deliverytracker.dto.PosicaoDTO;
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

    public void enviarPosicao(PosicaoDTO posicaoDTO) {
        log.debug("Enviando posição para o tópico WebSocket: {}", posicaoDTO);
        messagingTemplate.convertAndSend("/topic/posicoes", posicaoDTO);
    }
}
