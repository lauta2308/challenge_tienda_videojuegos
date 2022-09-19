package com.tiendavideojuegos.challenge_tienda_videojuegos.dto;

import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Platform;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.ProductCategory;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.ProductStatus;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class RequestProduct {
    private String name;
    private Double price;
    private Integer stock;

    private String releaseDate;
    private String[] category;
    private Platform platform;
    private ProductStatus productStatus;
    private Integer productDiscount;

    private String image;

    private String description;

    public RequestProduct(String name, Double price, Integer stock, String releaseDate, String[] category, Platform platform, ProductStatus productStatus, Integer productDiscount, String image, String description) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.releaseDate = releaseDate;
        this.category = category;
        this.platform = platform;
        this.productStatus = productStatus;
        this.productDiscount = productDiscount;
        this.image = image;
        this.description = description;
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



    public String getReleaseDate() {
        return releaseDate;
    }

    public String[] getCategory() {
        return category;
    }

    public Platform getPlatform() {
        return platform;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public Integer getProductDiscount() {
        return productDiscount;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
