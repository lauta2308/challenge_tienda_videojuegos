package com.tiendavideojuegos.challenge_tienda_videojuegos.models;

import java.util.stream.Stream;

public enum PaymentMethod {

    CREDITCARD("CREDITCARD"),
    CASH("CASH"),
    PAYPAL("PAYPAL");

    private String paymentMethod;

    PaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public static Stream<PaymentMethod> stream(){
        return Stream.of(PaymentMethod.values());}


}
