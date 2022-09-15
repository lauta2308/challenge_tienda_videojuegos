package com.tiendavideojuegos.challenge_tienda_videojuegos.dto;


import com.tiendavideojuegos.challenge_tienda_videojuegos.models.PaymentMethod;

import java.util.List;

public class RequestPedido {

    private String shippingAddress;
    private String shippingCity;
    private String zipCode;
    private PaymentMethod paymentMethod;
    private List<ProductOrderDto> products;

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public String getZipCode() {
        return zipCode;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public List<ProductOrderDto> getProducts() {
        return products;
    }
}