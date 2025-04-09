package com.dtpablo.deliverytracker.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationMessage {
    private Long entregadorId;
    private Double latitude;
    private Double longitude;
}