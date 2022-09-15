package com.tiendavideojuegos.challenge_tienda_videojuegos.Dtos;

import com.tiendavideojuegos.challenge_tienda_videojuegos.models.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PedidoDto {

    private long id;


    private String shippingAddress;


    private String shippingCity;

    private String zipCode;

    private LocalDate shippingDate;

    private LocalDate deliveryDate;

    private OrderStatus orderStatus;

    private PaymentMethod paymentMethod;


    private Set<ProductPedido> products;


    private Discount discount;

    public PedidoDto() {
    }


    public PedidoDto(Pedido pedido) {
        this.id = pedido.getId();
        this.shippingAddress = pedido.getShippingAddress();
        this.shippingCity = pedido.getShippingCity();
        this.zipCode = pedido.getZipCode();
        this.shippingDate = pedido.getShippingDate();
        this.deliveryDate = pedido.getDeliveryDate();
        this.orderStatus = pedido.getOrderStatus();
        this.paymentMethod = pedido.getPaymentMethod();
        this.products = pedido.getProducts();

    }

    public long getId() {
        return id;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public String getZipCode() {
        return zipCode;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }


    public Set<ProductPedido> getProducts() {
        return products;
    }

    public Discount getDiscount() {
        return discount;
    }
}
