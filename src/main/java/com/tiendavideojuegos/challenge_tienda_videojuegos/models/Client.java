package com.tiendavideojuegos.challenge_tienda_videojuegos.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String name;

    private String lastName;

    private LocalDate birthDate;

    private String email;

    private Rol rol;


    private String password;

    @OneToMany(mappedBy="user", fetch=FetchType.EAGER)
    private Set <Pedido> pedidos;

    @OneToMany(mappedBy="user", fetch=FetchType.EAGER)
    private Set <FavouriteProduct> favouritesProducts = new HashSet<>();


    public Client() {
    }

    public Client(String name, String lastName, LocalDate birthDate, String email, Rol rol, String password) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.rol = rol;

        this.password = password;
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }



    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Set<FavouriteProduct> getFavouritesProducts() {
        return favouritesProducts;
    }

    public void setFavouritesProducts(Set<FavouriteProduct> favouritesProducts) {
        this.favouritesProducts = favouritesProducts;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addPedido(Pedido pedido) {
        pedido.setUser(this);
        this.pedidos.add(pedido);
    }
}
