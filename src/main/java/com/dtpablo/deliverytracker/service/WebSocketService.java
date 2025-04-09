package com.dtpablo.deliverytracker.service;

import com.dtpablo.deliverytracker.dto.PosicaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void enviarPosicaoAtualizada(PosicaoDTO posicaoDTO) {
        messagingTemplate.convertAndSend("/topic/posicoes", posicaoDTO);
    }

}
