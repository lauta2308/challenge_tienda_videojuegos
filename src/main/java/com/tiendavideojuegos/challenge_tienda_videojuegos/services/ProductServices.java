package com.tiendavideojuegos.challenge_tienda_videojuegos.services;

import com.tiendavideojuegos.challenge_tienda_videojuegos.dto.ProductDto;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Platform;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Product;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.ProductCategory;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.ProductStatus;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.ProductRepository;
import com.tiendavideojuegos.challenge_tienda_videojuegos.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class ProductServices implements ProductService {

    @Autowired
    ProductRepository productRepository;




    @Override
    public List<ProductDto> getProducts() {
        return productRepository.findAll().stream().map(product -> new ProductDto(product)).collect(Collectors.toList());
    }

    @Override
    public void addProduct(Authentication authentication, String name, Double price, Integer stock, Integer sales, String releaseDate, ProductCategory category, Platform platform, ProductStatus productStatus, Integer productDiscount) {
        Product product = new Product(name, price, stock, sales,  LocalDate.parse(releaseDate), category, platform, productStatus, productDiscount);

        productRepository.save(product);
    }

    @Override
    public List<String> getProductCategories() {
        return ProductCategory.stream().map(productCategory -> productCategory.getProductCategory()).collect(Collectors.toList());
    }


}
