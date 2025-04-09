package com.dtpablo.deliverytracker.websocket;

import com.dtpablo.deliverytracker.entity.Entregador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void enviarPosicaoAtualizada(Entregador entregador) {
        messagingTemplate.convertAndSend("/topic/posicao", entregador);
    }
}
