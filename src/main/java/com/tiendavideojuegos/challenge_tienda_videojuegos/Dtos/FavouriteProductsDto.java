package com.tiendavideojuegos.challenge_tienda_videojuegos.Dtos;

import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Client;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.FavouriteProduct;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Product;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
