package com.tiendavideojuegos.challenge_tienda_videojuegos.Controllers;

import com.tiendavideojuegos.challenge_tienda_videojuegos.dto.ProductDto;
import com.tiendavideojuegos.challenge_tienda_videojuegos.dto.RequestProduct;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Platform;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.ProductCategory;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.ProductStatus;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.ProductRepository;
import com.tiendavideojuegos.challenge_tienda_videojuegos.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/api/products/categories")
    public List <String> getProductsCategories(){
        return productService.getProductCategories();
    }

    @PostMapping("/api/admin/products")
    public ResponseEntity<Object> addProduct(Authentication authentication, @RequestBody RequestProduct requestProduct){

         return productService.addProduct(authentication, requestProduct.getName(), requestProduct.getPrice(), requestProduct.getStock(), requestProduct.getReleaseDate(), requestProduct.getCategory(),requestProduct.getPlatform(),requestProduct.getProductStatus(), requestProduct.getProductDiscount(),requestProduct.getImage(),requestProduct.getDescription());

    }

    @PatchMapping("/api/admin/products/addstock")
    public ResponseEntity <Object> stockadd( @RequestParam Long idProduct, @RequestParam Integer stockQuantity){

        return productService.addStock(idProduct, stockQuantity);
    }

    @GetMapping("/api/admin/products/productstatus")
    public List<ProductStatus> productStatus(){

        return ProductStatus.stream().collect(Collectors.toList());
    };




}
