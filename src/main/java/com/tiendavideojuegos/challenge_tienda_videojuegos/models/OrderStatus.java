package com.tiendavideojuegos.challenge_tienda_videojuegos.models;

import java.util.stream.Stream;

public enum OrderStatus {

    READYTOSEND("READYTOSEND"),
    SENDING("SENDING"),
    DELIVERED("DELIVERED");

    private String orderStatus;

    OrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public static Stream<OrderStatus> stream(){
        return Stream.of(OrderStatus.values());
    }

}
