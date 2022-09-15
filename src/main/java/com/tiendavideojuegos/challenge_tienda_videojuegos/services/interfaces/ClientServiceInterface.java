package com.tiendavideojuegos.challenge_tienda_videojuegos.services.interfaces;

import com.tiendavideojuegos.challenge_tienda_videojuegos.dto.ClientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClientServiceInterface {


    List<ClientDto> findAll();

    ClientDto findById(Long id);


    ResponseEntity<String> registerClient(String name, String lastName, String email, String birthDate, String password);

    ClientDto getClientInfo(Authentication authentication);

    void changeRol(Authentication authentication, String email);

    ResponseEntity<String> addFavouriteProduct(Authentication authentication, Long productId);

    void deleteFavourite(Authentication authentication, Long favouriteProductId);
}
