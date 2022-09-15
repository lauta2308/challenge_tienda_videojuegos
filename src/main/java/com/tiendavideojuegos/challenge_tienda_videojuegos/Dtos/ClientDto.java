package com.tiendavideojuegos.challenge_tienda_videojuegos.Dtos;

import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Client;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.FavouriteProduct;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Pedido;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Rol;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDto {

    private long id;

    private String name;

    private String lastName;

    private LocalDate birthDate;

    private String email;

    private Rol rol;



    private Set<PedidoDto> pedidos;

    private Set <FavouriteProductsDto> favouritesProducts;

    public ClientDto() {
    }

    public ClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.birthDate= client.getBirthDate();
        this.email = client.getEmail();
        this.rol = client.getRol();
        this.pedidos = client.getPedidos().stream().map(pedido -> new PedidoDto(pedido)).collect(Collectors.toSet());
        this.favouritesProducts = client.getFavouritesProducts().stream().map(favouriteProduct -> new FavouriteProductsDto(favouriteProduct)).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public Rol getRol() {
        return rol;
    }


    public Set<PedidoDto> getPedidos() {
        return pedidos;
    }

    public Set<FavouriteProductsDto> getFavouritesProducts() {
        return favouritesProducts;
    }
}
