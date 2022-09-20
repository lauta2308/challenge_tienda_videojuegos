package com.tiendavideojuegos.challenge_tienda_videojuegos.models;

import java.util.stream.Stream;

public enum Rol {
    USER("USER"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN");

    private String rol;

    Rol(String rol) {
        this.rol = rol;
    }
    public static Stream<Rol> stream(){
        return Stream.of(Rol.values());
    }


}
