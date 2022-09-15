package com.tiendavideojuegos.challenge_tienda_videojuegos.repositories;


import com.tiendavideojuegos.challenge_tienda_videojuegos.models.ProductPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductPedidoRepository extends JpaRepository<ProductPedido, Long> {
}
