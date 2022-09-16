package com.tiendavideojuegos.challenge_tienda_videojuegos.models;

import java.util.stream.Stream;

public enum ProductCategory {


    ACTION("ACTION"),
    ADVENTURE("ADVENTURE"),
    ARCADE("ARCADE"),
    SIMULATION("SIMULATION"),
    SPORTS("SPORTS"),
    STRATEGY("STRATEGY");

    private String productCategory;

    ProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public static Stream<ProductCategory> stream(){
        return Stream.of(ProductCategory.values());
    }
}
