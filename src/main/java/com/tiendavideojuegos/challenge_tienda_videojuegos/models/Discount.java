package com.tiendavideojuegos.challenge_tienda_videojuegos.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String name;

    private Double discount;

    private String code;

    private LocalDate thruDate;

    private Integer stock;

    @OneToMany(mappedBy="discount", fetch=FetchType.EAGER)
    private Set<Pedido> pedidos = new HashSet<>();

    public Discount() {
    }

    public Discount(String name, Double discount, String code, LocalDate thruDate, Integer stock) {
        this.name = name;
        this.discount = discount;
        this.code = code;
        this.thruDate = thruDate;
        this.stock = stock;
    }

    public long getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDate thruDate) {
        this.thruDate = thruDate;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
