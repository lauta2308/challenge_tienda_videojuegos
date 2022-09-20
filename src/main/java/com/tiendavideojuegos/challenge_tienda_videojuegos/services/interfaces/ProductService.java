package com.tiendavideojuegos.challenge_tienda_videojuegos.services.interfaces;

import com.tiendavideojuegos.challenge_tienda_videojuegos.dto.ProductDto;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Platform;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Product;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.ProductCategory;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.ProductStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ProductService {
    List<ProductDto> getProducts();

    ResponseEntity <Object> addProduct(Authentication authentication, String name, Double price, Integer stock, String releaseDate, String [] category, Platform platform, ProductStatus productStatus, Integer productDiscount,String image, String description);

    List<String> getProductCategories();

    ResponseEntity <Object> addStock(Long idProduct, Integer stockQuantity);


}
