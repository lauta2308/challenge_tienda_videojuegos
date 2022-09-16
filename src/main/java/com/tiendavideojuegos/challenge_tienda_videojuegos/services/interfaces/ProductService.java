package com.tiendavideojuegos.challenge_tienda_videojuegos.services.interfaces;

import com.tiendavideojuegos.challenge_tienda_videojuegos.dto.ProductDto;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Platform;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.ProductCategory;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.ProductStatus;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ProductService {
    List<ProductDto> getProducts();

    void addProduct(Authentication authentication, String name, Double price, Integer stock, Integer sales, String releaseDate, ProductCategory category, Platform platform, ProductStatus productStatus, Integer productDiscount);

    List<String> getProductCategories();
}
