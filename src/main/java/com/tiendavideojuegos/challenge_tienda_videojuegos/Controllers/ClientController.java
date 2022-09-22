package com.tiendavideojuegos.challenge_tienda_videojuegos.Controllers;


import com.tiendavideojuegos.challenge_tienda_videojuegos.dto.ClientDto;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.ClientRepository;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.FavouriteProductRepository;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.ProductRepository;
import com.tiendavideojuegos.challenge_tienda_videojuegos.services.interfaces.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {


    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientServiceInterface clientService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    FavouriteProductRepository favouriteProductRepository;

    @GetMapping("/api/admin/clients")
    public List<ClientDto> getClients(){

        return clientService.findAll();
    }



    @GetMapping("/api/admin/clients/{id}")
    public ClientDto getClient(@PathVariable Long id){


        return clientService.findById(id);



    }




    @PostMapping("/api/clients")
    public ResponseEntity<String> register(

            @RequestParam String name, @RequestParam String lastName,

            @RequestParam String email, @RequestParam String birthDate, @RequestParam String password) {


        return clientService.registerClient(name, lastName, email, birthDate, password);




    }

   @GetMapping("/api/clients/current")

    public ClientDto getClientInfo (Authentication authentication){

       return clientService.getClientInfo(authentication);

    }

    @PatchMapping("/api/admin/clients/rol/admin")
    public ResponseEntity<Object> changeRol(Authentication authentication, @RequestParam String email) {

        clientService.changeRolAdmin(authentication, email);


        return new ResponseEntity<>("User rol changed", HttpStatus.CREATED);
    }
            @PatchMapping("/api/admin/clients/rol/user")
    public ResponseEntity<Object> changeRolUser(Authentication authentication, @RequestParam String email) {



        clientService.changeRolUser(authentication, email);


        return new ResponseEntity<>("User rol changed", HttpStatus.CREATED);
    }





    @PatchMapping("/api/clients/current/favourites")
    public ResponseEntity<String> addFavouriteProduct(Authentication authentication, @RequestParam Long productId){



        return  clientService.addFavouriteProduct(authentication, productId);
    }


    @RequestMapping("/api/clients/current/favourites")

    public ResponseEntity<Object> deleteFavourite(Authentication authentication, @RequestParam Long favouriteProductId) {


        clientService.deleteFavourite(authentication, favouriteProductId);



        return new ResponseEntity<>("Favourite Product deleted", HttpStatus.CREATED);
    }
}
