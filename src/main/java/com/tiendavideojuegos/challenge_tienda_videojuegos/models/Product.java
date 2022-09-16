package com.tiendavideojuegos.challenge_tienda_videojuegos.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
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

    @OneToMany(mappedBy="product", fetch=FetchType.EAGER)
    private Set<ProductPedido> productsPedidos = new HashSet<>();



    public Product() {
    }

    public Product(String name, Double price, Integer stock, Integer sales, LocalDate releaseDate, ProductCategory category, Platform platform, ProductStatus productStatus, Integer discount) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.sales = sales;
        this.releaseDate = releaseDate;
        this.category = category;
        this.platform = platform;
        this.productStatus = productStatus;
        this.discount = discount;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }


    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
