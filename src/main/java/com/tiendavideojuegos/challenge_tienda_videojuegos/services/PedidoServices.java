package com.tiendavideojuegos.challenge_tienda_videojuegos.services;

import com.tiendavideojuegos.challenge_tienda_videojuegos.dto.ProductOrderDto;
import com.tiendavideojuegos.challenge_tienda_videojuegos.dto.RequestPedido;
import com.tiendavideojuegos.challenge_tienda_videojuegos.models.*;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.*;
import com.tiendavideojuegos.challenge_tienda_videojuegos.services.interfaces.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;


@Service
public class PedidoServices implements PedidoService {


    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductPedidoRepository productPedidoRepository;

    @Autowired
    DiscountRepository discountRepository;

    @Override
    public ResponseEntity<Object> addPedido(RequestPedido requestPedido, Authentication authentication, String codeDiscount) {
        Client client = clientRepository.findByEmail(authentication.getName());

        if(requestPedido.getShippingAddress().isEmpty() || requestPedido.getShippingCity().isEmpty() || requestPedido.getZipCode().isEmpty() || requestPedido.getProducts().isEmpty() || requestPedido.getPaymentMethod() == null){
            return new ResponseEntity<>("missing data", HttpStatus.FORBIDDEN);
        }

        long validPaymentMethod = Arrays.stream(PaymentMethod.values()).filter(paymentMethod -> paymentMethod.equals(requestPedido.getPaymentMethod())).count();

        if(validPaymentMethod == 0){
            return new ResponseEntity<>("Payment method not allowed", HttpStatus.FORBIDDEN);
        }


        for(ProductOrderDto productOrder: requestPedido.getProducts()){

            Optional<Product> product = productRepository.findById(productOrder.getIdProducto());
            if(product.isEmpty()) {
                return new ResponseEntity<>("Product " + productOrder.getIdProducto() + " not found", HttpStatus.FORBIDDEN);
            } else if(product.get().getStock() < productOrder.getProductQuantity()) {
                return new ResponseEntity<>("Stock of product " + productOrder.getIdProducto() + " is " + product.get().getStock(), HttpStatus.FORBIDDEN);
            }
        }

        Discount discount= discountRepository.findByCode(codeDiscount);



        Pedido pedido = new Pedido(requestPedido.getShippingAddress(), requestPedido.getShippingCity(), requestPedido.getZipCode(), LocalDate.now().plusDays(1), LocalDate.now().plusDays(3), OrderStatus.READYTOSEND, requestPedido.getPaymentMethod(), client, discount);

        pedidoRepository.save(pedido);








        requestPedido.getProducts().stream().forEach(productOrderDto -> {
            Product product = productRepository.findById(productOrderDto.getIdProducto()).get();


            //for (int i = 0; i < productOrderDto.getProductQuantity(); i++) {
                ProductPedido productPedido = new ProductPedido(productOrderDto.getProductQuantity(),pedido, product);


                product.setStock(product.getStock() -productOrderDto.getProductQuantity());
                product.setSales(product.getSales() + productOrderDto.getProductQuantity());
                productPedidoRepository.save(productPedido);

                productRepository.save(product);








        });



        return new ResponseEntity<>("Pedido added!", HttpStatus.CREATED);


    }


}
