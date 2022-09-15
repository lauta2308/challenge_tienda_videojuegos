package com.tiendavideojuegos.challenge_tienda_videojuegos.Controllers;

import com.tiendavideojuegos.challenge_tienda_videojuegos.Dtos.ClientDto;
import com.tiendavideojuegos.challenge_tienda_videojuegos.Dtos.ProductDto;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Platform;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Product;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.ProductStatus;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/api/products")
    public List<ProductDto> getProducts(){

        return productRepository.findAll().stream().map(product -> new ProductDto(product)).collect(Collectors.toList());
    }

    @PostMapping("/api/admin/products")
    public ResponseEntity<Object> addProduct(Authentication authentication, @RequestParam String name, @RequestParam Double price, @RequestParam  Integer stock, @RequestParam  Integer sales, @RequestParam String releaseDate, @RequestParam  String category, @RequestParam Platform platform, @RequestParam ProductStatus productStatus ){

        Product product = new Product(name, price, stock, sales,  LocalDate.parse(releaseDate), category, platform, productStatus);

        productRepository.save(product);

        return new ResponseEntity<>("Product added", HttpStatus.CREATED);
    }
}
