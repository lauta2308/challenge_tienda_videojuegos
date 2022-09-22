package com.tiendavideojuegos.challenge_tienda_videojuegos.services.interfaces;

import com.tiendavideojuegos.challenge_tienda_videojuegos.dto.RequestPedido;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface PedidoService {
    ResponseEntity<Object> addPedido(RequestPedido requestPedido, Authentication authentication);
}
