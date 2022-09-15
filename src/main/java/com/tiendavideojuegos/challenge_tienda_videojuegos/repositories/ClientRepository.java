package com.tiendavideojuegos.challenge_tienda_videojuegos.repositories;

import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);
}
