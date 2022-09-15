package com.tiendavideojuegos.challenge_tienda_videojuegos.Controllers;


import com.tiendavideojuegos.challenge_tienda_videojuegos.Dtos.ClientDto;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClientController {


    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/api/clients")
    public List<ClientDto> getClients(){

        return clientRepository.findAll().stream().map(client -> new ClientDto(client)).collect(Collectors.toList());
    }



/*    @GetMapping("/api/clients/{id}")
    public ClientDto getClient(@PathVariable Long id){


        return clientRepository.findById(id).map(client -> new ClientDto(client)).orElse(null);



    }*/



}
