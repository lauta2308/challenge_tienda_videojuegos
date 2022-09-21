package com.tiendavideojuegos.challenge_tienda_videojuegos.Controllers;

import com.tiendavideojuegos.challenge_tienda_videojuegos.Utils.APIUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataController {

    @GetMapping("/data")
    public ResponseEntity<Object>getData(@RequestParam String url){
        ResponseEntity<Object>apiConnection = APIUtils.apiConnection(url);
        return new ResponseEntity<>(apiConnection, HttpStatus.OK);

    }
}
