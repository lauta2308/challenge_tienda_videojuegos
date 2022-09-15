package com.tiendavideojuegos.challenge_tienda_videojuegos.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Pedido {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;


    private String shippingAddress;


    private String shippingCity;

    private String zipCode;

    private LocalDate shippingDate;

    private LocalDate deliveryDate;

    private OrderStatus orderStatus;

    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private Client user;

    @OneToMany(mappedBy="pedido", fetch=FetchType.EAGER)
    private Set<ProductPedido> products = new HashSet<>();


/*    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="discount_id")
    private Discount discount;*/


    public Pedido() {
    }

    public Pedido(String shippingAddress, String shippingCity, String zipCode, LocalDate shippingDate, LocalDate deliveryDate, OrderStatus orderStatus, PaymentMethod paymentMethod) {
        this.shippingAddress = shippingAddress;
        this.shippingCity = shippingCity;
        this.zipCode = zipCode;
        this.shippingDate = shippingDate;
        this.deliveryDate = deliveryDate;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
    }

    public Pedido(String shippingAddress, String shippingCity, String zipCode, LocalDate shippingDate, LocalDate deliveryDate, OrderStatus orderStatus, PaymentMethod paymentMethod, Client user) {
        this.shippingAddress = shippingAddress;
        this.shippingCity = shippingCity;
        this.zipCode = zipCode;
        this.shippingDate = shippingDate;
        this.deliveryDate = deliveryDate;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.user = user;
    }

    public long getId() {
        return id;
    }



    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


/*    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }*/


/*    public Client getUser() {
        return user;
    }*/

    public void setUser(Client user) {
        this.user = user;
    }

    public Set<ProductPedido> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductPedido> products) {
        this.products = products;
    }


}
