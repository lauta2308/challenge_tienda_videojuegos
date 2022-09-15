package com.tiendavideojuegos.challenge_tienda_videojuegos.dto;

import com.tiendavideojuegos.challenge_tienda_videojuegos.models.FavouriteProduct;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Product;

public class FavouriteProductsDto {

    private long id;



    private Product product;

    public FavouriteProductsDto() {
    }

    public FavouriteProductsDto(FavouriteProduct favouriteProduct) {
        this.id = favouriteProduct.getId();
        this.product = favouriteProduct.getProduct();
    }

    public long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }
}
