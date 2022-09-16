package com.tiendavideojuegos.challenge_tienda_videojuegos.dto;

import com.tiendavideojuegos.challenge_tienda_videojuegos.models.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ProductDto {

    private long id;

    private String name;

    private Double price;

    private Integer stock;

    private Integer sales;

    private LocalDate releaseDate;

    private ProductCategory category;

    private Platform platform;

    private ProductStatus productStatus;

    private Integer discount;
    private Set<ProductPedido> productsPedidos = new HashSet<>();



    public ProductDto() {

    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.sales = product.getSales();
        this.releaseDate = product.getReleaseDate();
        this.category = product.getCategory();
        this.platform = product.getPlatform();
        this.productStatus = product.getProductStatus();
        this.discount = product.getDiscount();

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getSales() {
        return sales;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public Platform getPlatform() {
        return platform;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Set<ProductPedido> getProductsPedidos() {
        return productsPedidos;
    }


}
