package com.tiendavideojuegos.challenge_tienda_videojuegos.Controllers;


import com.tiendavideojuegos.challenge_tienda_videojuegos.Dtos.ClientDto;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Client;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.FavouriteProduct;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Product;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Rol;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.ClientRepository;

import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.FavouriteProductRepository;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClientController {


    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/api/admin/clients")
    public List<ClientDto> getClients(){

        return clientRepository.findAll().stream().map(client -> new ClientDto(client)).collect(Collectors.toList());
    }



    @GetMapping("/api/admin/clients/{id}")
    public ClientDto getClient(@PathVariable Long id){


        return clientRepository.findById(id).map(client -> new ClientDto(client)).orElse(null);



    }


    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/api/clients")
    public ResponseEntity<Object> register(

            @RequestParam String name, @RequestParam String lastName,

            @RequestParam String email, @RequestParam String birthDate, @RequestParam String password) {


        if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {

            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);

        }


        if (clientRepository.findByEmail(email) != null) {

            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);

        }

        Client newClient = new Client(name, lastName, LocalDate.parse(birthDate), email, Rol.USER, passwordEncoder.encode(password));
        clientRepository.save(newClient);

        return new ResponseEntity<>("User created", HttpStatus.CREATED);

    }

   @GetMapping("/api/clients/current")

    public ClientDto getClientInfo (Authentication authentication){

       return new ClientDto(clientRepository.findByEmail(authentication.getName()));

    }

    @PatchMapping("/api/admin/clients/rol")
    public ResponseEntity<Object> changeRol(Authentication authentication, @RequestParam String email) {

        Client client = clientRepository.findByEmail(email);

        client.setRol(Rol.ADMIN);

        clientRepository.save(client);

        return new ResponseEntity<>("User rol changed", HttpStatus.CREATED);
    }


    @Autowired
    ProductRepository productRepository;

    @Autowired
    FavouriteProductRepository favouriteProductRepository;

    @PatchMapping("/api/clients/current/favourites")
    public ResponseEntity<Object> addFavouriteProduct(Authentication authentication, @RequestParam Long productId) {

        Client client = clientRepository.findByEmail(authentication.getName());

        Product productFound = productRepository.findById(productId).get();


        FavouriteProduct newFavouriteProduct = new FavouriteProduct(client, productFound);

        favouriteProductRepository.save(newFavouriteProduct);


        return new ResponseEntity<>("Favourite Product added", HttpStatus.CREATED);
    }


    @DeleteMapping("/api/clients/current/favourites")
    public ResponseEntity<Object> deleteFavourite(Authentication authentication, @RequestParam Long favouriteProductId) {

        Client client = clientRepository.findByEmail(authentication.getName());

        FavouriteProduct favouriteProductFound = favouriteProductRepository.findById(favouriteProductId).get();

        favouriteProductRepository.delete(favouriteProductFound);


        return new ResponseEntity<>("Favourite Product deleted", HttpStatus.CREATED);
    }
}
