package com.dtpablo.deliverytracker.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Removendo o parâmetro {token} da URL
        registry.addEndpoint("/ws-location")
                .setAllowedOrigins("http://localhost:5173")  // Permite origens do frontend
                .withSockJS();  // Habilita fallback para navegadores antigos
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // Mensagens de saída
        config.setApplicationDestinationPrefixes("/app"); // Mensagens de entrada
    }
}
