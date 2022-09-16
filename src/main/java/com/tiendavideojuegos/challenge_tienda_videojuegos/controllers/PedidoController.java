package com.tiendavideojuegos.challenge_tienda_videojuegos.controllers;

import com.tiendavideojuegos.challenge_tienda_videojuegos.dto.PedidoDto;
import com.tiendavideojuegos.challenge_tienda_videojuegos.dto.ProductOrderDto;
import com.tiendavideojuegos.challenge_tienda_videojuegos.dto.RequestPedido;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.*;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.ClientRepository;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.PedidoRepository;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.ProductPedidoRepository;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.ProductRepository;
import com.tiendavideojuegos.challenge_tienda_videojuegos.services.interfaces.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class PedidoController {



    @Autowired
    PedidoService pedidoService;

    @PostMapping("/api/clients/current/pedido")
    public ResponseEntity<Object> addPedido(@RequestBody RequestPedido requestPedido, Authentication authentication){


        return pedidoService.addPedido(requestPedido, authentication);




    }
}
