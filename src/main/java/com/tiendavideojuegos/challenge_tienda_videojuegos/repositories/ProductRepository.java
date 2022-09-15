package com.tiendavideojuegos.challenge_tienda_videojuegos.repositories;

import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
}



