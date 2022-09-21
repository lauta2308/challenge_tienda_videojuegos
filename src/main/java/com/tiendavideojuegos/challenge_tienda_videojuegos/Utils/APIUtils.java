package com.tiendavideojuegos.challenge_tienda_videojuegos.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.HttpURLConnection;
import java.net.URL;

public final class APIUtils {

    public APIUtils() {
    }

    public static ResponseEntity<Object> apiConnection(String urlrequest){
        try {
            URL url = new URL(urlrequest);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 201){
                return new ResponseEntity<>("Error en el proceso.",HttpStatus.FORBIDDEN);
            }else {
                return new ResponseEntity<>("La conexión funcionó.", HttpStatus.OK);
            }


        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
