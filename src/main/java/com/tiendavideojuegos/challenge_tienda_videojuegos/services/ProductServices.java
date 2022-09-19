package com.tiendavideojuegos.challenge_tienda_videojuegos.services;

import com.tiendavideojuegos.challenge_tienda_videojuegos.dto.ProductDto;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.*;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.ProductRepository;
import com.tiendavideojuegos.challenge_tienda_videojuegos.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
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
    public ResponseEntity<Object> addProduct(Authentication authentication, String name, Double price, Integer stock, String releaseDate, String [] category, Platform platform, ProductStatus productStatus, Integer productDiscount, String image, String description) {


        if (name.isEmpty() || price<=0 || stock <=0 || releaseDate.isEmpty() || category.length<=0 || platform.toString().isEmpty()||productStatus.toString().isEmpty() || productDiscount.toString().isEmpty() || image.isEmpty() || description.isEmpty()){

            return new ResponseEntity<>("THERE CAN'T BE EMPTY FIELDS FOOL",HttpStatus.FORBIDDEN);
        }
        long validPlatform = Arrays.stream(Platform.values()).filter(platform1 -> platform1.equals(platform)).count();
        long validProductStatus = Arrays.stream(ProductStatus.values()).filter(productStatus1 -> productStatus1.equals(productStatus)).count();


        if (validProductStatus==0 || validPlatform==0 ) {
            return new ResponseEntity<>("Platform or ProductStatus is Incorrect", HttpStatus.FORBIDDEN);
        }

        for (String categoryActual: category) {
        if (Arrays.stream(ProductCategory.values()).noneMatch(cx -> (cx.toString()).equals(categoryActual))){
            return new ResponseEntity<>("There is no "+categoryActual +" category" ,HttpStatus.FORBIDDEN);
        }
        };



        List <ProductCategory> categoryReal = Arrays.stream(category).map(x -> ProductCategory.valueOf(x)).collect(Collectors.toList());

        Product product = new Product(name, price, stock, 0,  LocalDate.parse(releaseDate), categoryReal, platform, productStatus, productDiscount,image,description);


        productRepository.save(product);

        return new ResponseEntity<>("Product added", HttpStatus.CREATED);
    }

    @Override
    public List<String> getProductCategories() {
        return ProductCategory.stream().map(productCategory -> productCategory.getProductCategory()).collect(Collectors.toList());
    }


}
