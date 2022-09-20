package com.tiendavideojuegos.challenge_tienda_videojuegos.models;

import java.util.stream.Stream;

public enum Platform {
    XBOX("XBOX"),
    PLAYSTATION("PLAYSTATION"),
    SWITCH("SWITCH"),
    PC("PC"),
    RETRO("RETRO");

    private String platform;

    Platform(String platform){this.platform=platform;}

    public String getPlatform() {
        return platform;
    }

    public static Stream<Platform> stream(){
        return Stream.of(Platform.values());
    }
}
