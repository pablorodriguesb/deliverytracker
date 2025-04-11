package com.dtpablo.deliverytracker.websocket;

import com.dtpablo.deliverytracker.entity.Entregador;
import com.dtpablo.deliverytracker.repository.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LocationWebSocketController {

    @Autowired
    private EntregadorRepository entregadorRepository;

    @MessageMapping("/location")
    @SendTo("/topic/updates")
    public LocationMessage updateLocation(LocationMessage message) {
        Entregador entregador = entregadorRepository.findById(message.getEntregadorId())
                .orElseThrow(() -> new RuntimeException("Entregador não encontrado"));

        // Atualiza a posição atual (utilizada no mapa em tempo real)
        entregador.setLatitudeAtual(message.getLatitude());
        entregador.setLongitudeAtual(message.getLongitude());

        // Opcional: atualizar também a posição fixa (caso necessário)
        entregador.setLatitude(message.getLatitude());
        entregador.setLongitude(message.getLongitude());

        entregadorRepository.save(entregador);

        return message; // Envia de volta para todos os clientes
    }
}
