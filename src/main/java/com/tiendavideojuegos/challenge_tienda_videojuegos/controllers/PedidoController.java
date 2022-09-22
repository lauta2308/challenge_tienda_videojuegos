package com.tiendavideojuegos.challenge_tienda_videojuegos.Controllers;
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
import org.springframework.web.bind.annotation.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PedidoController {



    @Autowired
    PedidoService pedidoService;

    @PostMapping("/api/clients/current/pedido")
    public ResponseEntity<Object> addPedido(@RequestBody RequestPedido requestPedido, Authentication authentication){


        return pedidoService.addPedido(requestPedido, authentication );
    }

    @GetMapping("/api/admin/orderstatus")
    public List<OrderStatus> orderStatuses(){

        return OrderStatus.stream().collect(Collectors.toList());
    };

    @GetMapping("/api/admin/paymentMethod")
    public List<PaymentMethod> paymentMethods(){

        return PaymentMethod.stream().collect(Collectors.toList());
    };
}
