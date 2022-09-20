package com.tiendavideojuegos.challenge_tienda_videojuegos.models;

import java.util.stream.Stream;

public enum ProductStatus {
    COMINGSOON("COMINGSOON"),
    LAUNCHED("LAUNCHED");

    private String productStatus;

    ProductStatus (String productStatus){
        this.productStatus=productStatus;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public static Stream<ProductStatus> stream(){
        return Stream.of(ProductStatus.values());
    }
}
