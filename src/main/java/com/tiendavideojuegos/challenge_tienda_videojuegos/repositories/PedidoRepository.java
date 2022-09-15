package com.tiendavideojuegos.challenge_tienda_videojuegos.repositories;

import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
