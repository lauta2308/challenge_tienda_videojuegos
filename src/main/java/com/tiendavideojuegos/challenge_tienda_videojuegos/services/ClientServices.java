package com.tiendavideojuegos.challenge_tienda_videojuegos.services;

import com.tiendavideojuegos.challenge_tienda_videojuegos.dto.ClientDto;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Client;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.FavouriteProduct;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Product;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Rol;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.ClientRepository;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.FavouriteProductRepository;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.ProductRepository;
import com.tiendavideojuegos.challenge_tienda_videojuegos.services.interfaces.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServices implements ClientServiceInterface {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    FavouriteProductRepository favouriteProductRepository;

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream().map(client -> new ClientDto(client)).collect(Collectors.toList());
    }

    @Override
    public ClientDto findById(Long id) {
        return clientRepository.findById(id).map(client -> new ClientDto(client)).orElse(null);
    }

    @Override
    public ResponseEntity<String> registerClient(String name, String lastName, String email, String birthDate, String password) {
        if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {

            return new ResponseEntity<>("missing data", HttpStatus.FORBIDDEN);


        }


        if (clientRepository.findByEmail(email) != null) {

            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);


        }

        Client newClient = new Client(name, lastName, LocalDate.parse(birthDate), email, Rol.USER, passwordEncoder.encode(password));
        clientRepository.save(newClient);
         return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @Override
    public ClientDto getClientInfo(Authentication authentication) {
        return new ClientDto(clientRepository.findByEmail(authentication.getName()));
    }

    @Override
    public void changeRol(Authentication authentication, String email) {
        Client client = clientRepository.findByEmail(email);

        client.setRol(Rol.ADMIN);

        clientRepository.save(client);
    }

    @Override
    public ResponseEntity<String> addFavouriteProduct(Authentication authentication, Long productId){
        Client client = clientRepository.findByEmail(authentication.getName());

        Optional<Product> productFound = productRepository.findById(productId);


        long clientHasFavProduct = client.getFavouritesProducts().stream().filter(favouriteProduct -> favouriteProduct.getProduct().getId() == productId).count();

        if( clientHasFavProduct > 0){
            return new ResponseEntity<>("Product already added!",HttpStatus.FORBIDDEN);

        }

        if(productFound.isEmpty() ){
            return new ResponseEntity<>("Product not found",HttpStatus.FORBIDDEN);
        }


        FavouriteProduct newFavouriteProduct = new FavouriteProduct(client, productFound.get());



        favouriteProductRepository.save(newFavouriteProduct);

        return new ResponseEntity<>("Product added!",HttpStatus.CREATED);
    }

    @Override
    public void deleteFavourite(Authentication authentication, Long favouriteProductId) {
        Client client = clientRepository.findByEmail(authentication.getName());

        FavouriteProduct favouriteProductFound = favouriteProductRepository.findById(favouriteProductId).get();

        favouriteProductRepository.delete(favouriteProductFound);
    }


}
