package com.tiendavideojuegos.challenge_tienda_videojuegos.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

    private Integer age;

    private String email;

    private Rol rol;

    private Double balance;

    private String password;

    @OneToMany(mappedBy="user", fetch=FetchType.EAGER)
    private Set <Pedido> pedidos;

    @OneToMany(mappedBy="user", fetch=FetchType.EAGER)
    private Set <FavouriteProduct> favouritesProducts = new HashSet<>();


    public Client() {
    }

    public Client(String name, String lastName, Integer age, String email, Rol rol, Double balance, String password) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.rol = rol;
        this.balance = balance;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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
