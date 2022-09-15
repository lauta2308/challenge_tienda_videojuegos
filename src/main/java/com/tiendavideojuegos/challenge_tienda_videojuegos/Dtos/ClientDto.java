package com.tiendavideojuegos.challenge_tienda_videojuegos.Dtos;

import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Client;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.FavouriteProduct;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Pedido;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Rol;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDto {

    private long id;

    private String name;

    private String lastName;

    private Integer age;

    private String email;

    private Rol rol;

    private Double balance;

    private Set<PedidoDto> pedidos;

    private Set <FavouriteProduct> favouritesProducts;

    public ClientDto() {
    }

    public ClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.age = client.getAge();
        this.email = client.getEmail();
        this.rol = client.getRol();
        this.balance = client.getBalance();
        this.pedidos = client.getPedidos().stream().map(pedido -> new PedidoDto(pedido)).collect(Collectors.toSet());
        this.favouritesProducts = client.getFavouritesProducts();
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

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public Rol getRol() {
        return rol;
    }

    public Double getBalance() {
        return balance;
    }

    public Set<PedidoDto> getPedidos() {
        return pedidos;
    }

    public Set<FavouriteProduct> getFavouritesProducts() {
        return favouritesProducts;
    }
}
