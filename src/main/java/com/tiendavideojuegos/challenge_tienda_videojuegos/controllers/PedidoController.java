package com.tiendavideojuegos.challenge_tienda_videojuegos.controllers;

import com.tiendavideojuegos.challenge_tienda_videojuegos.dto.PedidoDto;
import com.tiendavideojuegos.challenge_tienda_videojuegos.dto.ProductOrderDto;
import com.tiendavideojuegos.challenge_tienda_videojuegos.dto.RequestPedido;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.*;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.ClientRepository;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.PedidoRepository;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.ProductPedidoRepository;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.ProductRepository;
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
    PedidoRepository pedidoRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductPedidoRepository productPedidoRepository;

    @PostMapping("/api/clients/current/pedido")
    public ResponseEntity<Object> addPedido(@RequestBody RequestPedido requestPedido, Authentication authentication){

        Client client = clientRepository.findByEmail(authentication.getName());

        Pedido pedido = new Pedido(requestPedido.getShippingAddress(), requestPedido.getShippingCity(), requestPedido.getZipCode(), LocalDate.now().plusDays(1) , LocalDate.now().plusDays(3), OrderStatus.READYTOSEND, requestPedido.getPaymentMethod(), client);


        pedidoRepository.save(pedido);

        requestPedido.getProducts().stream().forEach(productOrderDto -> {

            for (int i = 0; i < productOrderDto.getProductQuantity(); i++) {
                ProductPedido productPedido = new ProductPedido(pedido, productRepository.findById(productOrderDto.getIdProducto()).get());

                productPedidoRepository.save(productPedido);
            }

        }
        );



        return new ResponseEntity<>("Pedido added", HttpStatus.CREATED);
    }
}
