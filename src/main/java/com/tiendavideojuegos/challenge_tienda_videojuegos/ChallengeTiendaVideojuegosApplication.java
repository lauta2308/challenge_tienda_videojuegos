package com.tiendavideojuegos.challenge_tienda_videojuegos;

import com.tiendavideojuegos.challenge_tienda_videojuegos.models.*;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class ChallengeTiendaVideojuegosApplication {



	public static void main(String[] args) {
		SpringApplication.run(ChallengeTiendaVideojuegosApplication.class, args);
	}



	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initData(ClientRepository userRepository, DiscountRepository discountRepository, PedidoRepository pedidoRepository , FavouriteProductRepository favouriteProductRepository, ProductRepository productRepository, ProductPedidoRepository productPedidoRepository) {
		return (args) -> {

			Client userOne = new Client("melba", "morel", LocalDate.of(1985,07,04), "melba@test.com", Rol.USER,  passwordEncoder.encode("123"));

			//userRepository.save(userOne);


			Product productOne = new Product("fifa", 20.00, 10, 16, LocalDate.now(), ProductCategory.SPORTS, Platform.PC, ProductStatus.LAUNCHED, 0);

					productRepository.save(productOne);

			Product productTwo = new Product("pes", 20.00, 10, 16, LocalDate.now(), ProductCategory.SPORTS, Platform.PC, ProductStatus.LAUNCHED, 0);


			productRepository.save(productTwo);
		/*	Discount discountOne = new Discount("deportes", 20.00, "deportes");

			discountRepository.save(discountOne);*/



			Pedido pedido = new Pedido("testAddress", "testCity", "123", LocalDate.now(), LocalDate.now().plusDays(1), OrderStatus.SENDING, PaymentMethod.PAYPAL, userOne);



			userRepository.save(userOne);

			pedidoRepository.save(pedido);



			ProductPedido productPedidoOne = new ProductPedido(pedido, productOne);
			ProductPedido productPedidoTwo = new ProductPedido(pedido, productTwo);

			productPedidoRepository.save(productPedidoOne);
			productPedidoRepository.save(productPedidoTwo);


			Pedido pedido2 = new Pedido("testAddress", "testCity", "123", LocalDate.now(), LocalDate.now().plusDays(1), OrderStatus.SENDING, PaymentMethod.PAYPAL, userOne);

			userRepository.save(userOne);

			pedidoRepository.save(pedido2);

			Product product3 = new Product("pes", 20.00, 10, 16, LocalDate.now(), ProductCategory.SPORTS, Platform.PC, ProductStatus.LAUNCHED, 0);

			productRepository.save(product3);

			ProductPedido productPedido3 = new ProductPedido(pedido2, productTwo);

			productPedidoRepository.save(productPedido3);


			FavouriteProduct favouriteProductOne = new FavouriteProduct(userOne, productOne);

			favouriteProductRepository.save(favouriteProductOne);

			Client admin = new Client("matias", "admin", LocalDate.of(1991,05,03), "admin@test.com", Rol.ADMIN,  passwordEncoder.encode("123"));

			userRepository.save(admin);

		};

	}
}
