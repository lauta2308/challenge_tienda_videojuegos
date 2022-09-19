package com.tiendavideojuegos.challenge_tienda_videojuegos.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
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

    @ElementCollection
    private List <ProductCategory> category;

    private Platform platform;

    private ProductStatus productStatus;

    private Integer discount;

    private String image;

    private String description;

    @OneToMany(mappedBy="product", fetch=FetchType.EAGER)
    private Set<ProductPedido> productsPedidos = new HashSet<>();



    public Product() {
    }

    public Product(String name, Double price, Integer stock, Integer sales, LocalDate releaseDate,  List <ProductCategory> category, Platform platform, ProductStatus productStatus, Integer discount, String image, String description) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.sales = sales;
        this.releaseDate = releaseDate;
        this.category = category;
        this.platform = platform;
        this.productStatus = productStatus;
        this.discount = discount;
        this.image=image;
        this.description=description;
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

    public  List <ProductCategory> getCategory() {
        return category;
    }

    public void setCategory(List <ProductCategory> category) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String  image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
