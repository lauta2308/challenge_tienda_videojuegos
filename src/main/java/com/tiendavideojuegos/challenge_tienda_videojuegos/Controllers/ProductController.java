package com.tiendavideojuegos.challenge_tienda_videojuegos.controllers;

import com.tiendavideojuegos.challenge_tienda_videojuegos.dto.ProductDto;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Platform;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.ProductStatus;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.ProductRepository;
import com.tiendavideojuegos.challenge_tienda_videojuegos.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/api/products")
    public List<ProductDto> getProducts(){

      return  productService.getProducts();

    }

    @PostMapping("/api/admin/products")
    public ResponseEntity<Object> addProduct(Authentication authentication, @RequestParam String name, @RequestParam Double price, @RequestParam  Integer stock, @RequestParam  Integer sales, @RequestParam String releaseDate, @RequestParam  String category, @RequestParam Platform platform, @RequestParam ProductStatus productStatus ){

        productService.addProduct(authentication, name, price, stock, sales, releaseDate, category, platform, productStatus);



        return new ResponseEntity<>("Product added", HttpStatus.CREATED);
    }


}
