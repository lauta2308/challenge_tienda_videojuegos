package com.tiendavideojuegos.challenge_tienda_videojuegos;

import com.tiendavideojuegos.challenge_tienda_videojuegos.models.*;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.*;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

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




			Product productOne = new Product("fifa", 20.00, 10, 16, LocalDate.now(),List.of(ProductCategory.SPORTS,ProductCategory.ADVENTURE), Platform.PC, ProductStatus.LAUNCHED, 0,"https://assets-prd.ignimgs.com/2021/09/27/fifa-22-20210927161754-1632775274574.jpg","Test Description");

			productRepository.save(productOne);

			Product productTwo = new Product("pes", 20.00, 10, 16, LocalDate.now(), List.of(ProductCategory.SPORTS), Platform.PC, ProductStatus.LAUNCHED, 0,"https://assets-prd.ignimgs.com/2021/09/27/fifa-22-20210927161754-1632775274574.jpg","Test Description");


			productRepository.save(productTwo);

			Discount discountOne = new Discount("Sin Descuento", 0.00, "Sin Descuento",LocalDate.now().plusYears(999),99999999);
			discountRepository.save(discountOne);



			Pedido pedido = new Pedido("testAddress", "testCity", "123", LocalDate.now(), LocalDate.now().plusDays(1), OrderStatus.SENDING, PaymentMethod.PAYPAL, userOne,discountOne);



			userRepository.save(userOne);

			pedidoRepository.save(pedido);



			ProductPedido productPedidoOne = new ProductPedido(1,pedido, productOne);
			ProductPedido productPedidoTwo = new ProductPedido(2,pedido, productTwo);

			productPedidoRepository.save(productPedidoOne);
			productPedidoRepository.save(productPedidoTwo);


			Pedido pedido2 = new Pedido("testAddress", "testCity", "123", LocalDate.now(), LocalDate.now().plusDays(1), OrderStatus.SENDING, PaymentMethod.PAYPAL, userOne,discountOne);

			userRepository.save(userOne);

			pedidoRepository.save(pedido2);

			Product product3 = new Product("pes", 20.00, 10, 16, LocalDate.now(), List.of( ProductCategory.ARCADE), Platform.PC, ProductStatus.LAUNCHED, 0,"https://assets-prd.ignimgs.com/2021/09/27/fifa-22-20210927161754-1632775274574.jpg","Test Description");

			productRepository.save(product3);

			ProductPedido productPedido3 = new ProductPedido(3,pedido2, productTwo);

			productPedidoRepository.save(productPedido3);


			FavouriteProduct favouriteProductOne = new FavouriteProduct(userOne, productOne);

			favouriteProductRepository.save(favouriteProductOne);

			Client admin = new Client("matias", "admin", LocalDate.of(1991,05,03), "admin@test.com", Rol.ADMIN,  passwordEncoder.encode("123"));

			userRepository.save(admin);


			Discount discountLimit = new Discount("test",1.2,"mati01",LocalDate.of(2022,10,20),25);
			discountRepository.save(discountLimit);

			Pedido pedidoDiscount= new Pedido("testAddress", "testCity", "123", LocalDate.now(), LocalDate.now().plusDays(1), OrderStatus.SENDING, PaymentMethod.PAYPAL, userOne, discountLimit);

			pedidoRepository.save(pedidoDiscount);

			ProductPedido productPedidothree = new ProductPedido(2,pedidoDiscount, productOne);
			productPedidoRepository.save(productPedidothree);


			Product omori = new Product("Omori",29.99,20,16,LocalDate.of(2020,6,17),List.of(ProductCategory.STRATEGY), Platform.PC,ProductStatus.LAUNCHED,0, "https://areajugones.sport.es/wp-content/uploads/2022/01/omori-2-441x588.jpg.webp", "Explore a strange world full of colorful friends and foes. When the time comes, the path you’ve chosen will determine your fate... and perhaps the fate of others as well.");
			productRepository.save(omori);
			Product sims4 = new Product("Sims 4",30.00,10,11,LocalDate.of(2014,9,2),List.of(ProductCategory.SIMULATION) ,Platform.PC,ProductStatus.LAUNCHED,0, "https://sm.ign.com/ign_es/game/l/los-sims-4/los-sims-4_c8f4.jpg", "Unleash your imagination and create a completely unique Sims world. Explore and customize every detail, from your Sims to their homes and beyond.");
			productRepository.save(sims4);
			Product littleBigPlanet = new Product("The little big planet 3", 19.00,40,1,LocalDate.of(18,11,29),List.of(ProductCategory.ADVENTURE) ,Platform.PLAYSTATION,ProductStatus.LAUNCHED,0, "https://image.api.playstation.com/vulcan/img/rnd/202011/0523/uCNQSzE3APHneMXOdWeBAyeR.png", "In LittleBigPlanet™ 3, you can discover a world of endless creative surprises as you explore every nook and cranny of the Imagisphere. Meet the inhabitants of the mysterious planet Bunkum and take on the nefarious Newton in this rich and vibrant world.");
			productRepository.save(littleBigPlanet);
			Product splatoon3 = new Product("splatoon 3",59.99,5,40,LocalDate.of(2022,9,9),List.of(ProductCategory.ACTION) ,Platform.SWITCH,ProductStatus.LAUNCHED,0, "https://assets.nintendo.com/image/upload/c_fill,w_1200/q_auto:best/f_auto/dpr_2.0/ncom/en_US/games/switch/s/splatoon-3-switch/hero", "Enter the Splatlands, a sun-scorched wasteland inhabited by battle-hardened Inklings and Octolings. Splatsville, the city of chaos, is the adrenaline-pumping heart of this dusty wasteland.");
			productRepository.save(splatoon3);
			Product stray = new Product("Stray",29.99,10,30,LocalDate.of(2022,7,19),List.of(ProductCategory.ACTION) ,Platform.PLAYSTATION,ProductStatus.LAUNCHED,0, "https://as01.epimg.net/meristation/imagenes/2022/06/02/noticias/1654194600_954026_1654194648_noticia_normal.jpg", "Lost, alone and separated from his family, a stray cat must unravel an ancient mystery to escape a forgotten cybercity and find his way home.");
			productRepository.save(stray);
			Product resident4 = new Product("Resident evil 4 Remake",19.99,20,5,LocalDate.of(2023,3,24),List.of(ProductCategory.ADVENTURE) ,Platform.PC,ProductStatus.COMINGSOON,0, "https://www.egames.news/__export/1663255395811/sites/debate/img/2022/09/15/resident-evil-4-remake-1.jpg_242310155.jpg", "Special agent Leon S. Kennedy is sent on a mission to rescue the U.S. President’s daughter who has been kidnapped.");
			productRepository.save(resident4);
			Product resident3 = new Product("Resident evil 3 Remake",28.19,30,5,LocalDate.of(2020,4,3),List.of(ProductCategory.ADVENTURE) ,Platform.PC,ProductStatus.LAUNCHED,0, "https://www.hd-tecnologia.com/imagenes/articulos/2020/10/Resident-Evil-3-Remake-Capcom-elimino-Denuvo-la-tecnologia-anti-pirateria.jpg", "Jill Valentine is one of the last remaining people in Raccoon City to witness the atrocities Umbrella performed. To stop her, Umbrella unleashes their ultimate secret weapon: Nemesis! Also includes Resident Evil Resistance,");
			productRepository.save(resident3);
			Product resident2R = new Product("Resident evil 2 Remake",23.47,20,5,LocalDate.of(2002,1,25),List.of(ProductCategory.ADVENTURE) ,Platform.PC,ProductStatus.LAUNCHED,0, "https://media.vandal.net/t200/116112/resident-evil-2-remake-202262114402158_2.jpg", "A deadly virus engulfs the residents of Raccoon City in September of 1998, plunging the city into chaos as flesh eating zombies roam the streets for survivors. An unparalleled adrenaline rush, gripping storyline, and unimaginable horrors await you.");
			productRepository.save(resident2R);
			Product resident = new Product("Resident evil",19.99,10,5,LocalDate.of(2002,3,22),List.of(ProductCategory.ADVENTURE) ,Platform.PC,ProductStatus.LAUNCHED,0, "https://cdn1.epicgames.com/offer/611482b8586142cda48a0786eb8a127c/EGS_DeadbyDaylightResidentEvilChapter_BehaviourInteractive_DLC_S2_1200x1600-a55ef30dee6864a545ff0f7a2140b43a", "The game that defined the survival-horror genre is back! Check out the remastered HD version of Resident Evil");
			productRepository.save(resident);
			Product residentvillage = new Product("Resident evil Village",49.99,5,25,LocalDate.of(2021,5,2),List.of(ProductCategory.ADVENTURE),Platform.PC,ProductStatus.LAUNCHED,0, "https://image.api.playstation.com/vulcan/ap/rnd/202101/0812/FkzwjnJknkrFlozkTdeQBMub.png", "Experience survival horror like never before in the 8th major installment in the Resident Evil franchise - Resident Evil Village. With detailed graphics, intense first-person action and masterful storytelling, the terror has never felt more realistic.");
			productRepository.save(residentvillage);
			Product persona5 = new Product("Persona 5",19.99,10,4,LocalDate.of(2016,9,15),List.of(ProductCategory.STRATEGY),Platform.PLAYSTATION,ProductStatus.LAUNCHED,0, "https://image.api.playstation.com/cdn/EP4062/CUSA06638_00/0fSaYhFhEVP183JLTwVec7qkzmaHNMS2.png", "Join the Phantom Thieves and strike back against the corruption overtaking cities across Japan. A summer vacation with close friends takes a sudden turn as a distorted reality emerges");
			productRepository.save(persona5);
			Product hollow = new Product("Hollow Night", 14.49,20,10,LocalDate.of(2017,2,24),List.of(ProductCategory.ADVENTURE),Platform.PC,ProductStatus.LAUNCHED,0, "https://cdn.hobbyconsolas.com/sites/navi.axelspringer.es/public/styles/1200/public/media/image/2018/03/hollow-knight-portada.jpg?itok=2GVj_phl", "Forge your own path in Hollow Knight! An epic action adventure through a vast ruined kingdom of insects and heroes. Explore twisting caverns, battle tainted creatures and befriend bizarre bugs, all in a classic, hand-drawn 2D style.");
			productRepository.save(hollow);
			Product sonicUnl = new Product("Sonic unleashed",14.99,50,1,LocalDate.of(2008,11,18),List.of(ProductCategory.ACTION),Platform.XBOX,ProductStatus.LAUNCHED,0, "https://cdn.shopify.com/s/files/1/0286/4870/2038/products/0001-12603616437_20211118_162039_0000.png?v=1637277758", "Sonic transforms at night! Twice the fun! Speed \u200B\u200Band skill or strength and fury!");
			productRepository.save(sonicUnl);
			Product sonicMania = new Product("Sonic Mania",10.99,30,40,LocalDate.of(2017,8,15),List.of(ProductCategory.ARCADE),Platform.PC,ProductStatus.LAUNCHED,0, "https://assets.nintendo.com/image/upload/f_auto/q_auto/dpr_2.625/c_scale,w_400/ncom/es_MX/games/switch/s/sonic-mania-switch/description-image", "Sonic Mania is an all-new adventure with Sonic, Tails, and Knuckles full of unique bosses, rolling 2D landscapes, and fun classic gameplay.");
			productRepository.save(sonicMania);
			Product cuphead = new Product("Cuphead" ,15.00,10,50,LocalDate.of(2017,9,29),List.of(ProductCategory.STRATEGY),Platform.XBOX,ProductStatus.LAUNCHED,0, "https://image.api.playstation.com/vulcan/img/cfn/11307fllh6D-IvbpCa18N0vRggVeRYWA06paTNCj3DENJMScAzW2f3ry4IwFcXBAt9kWXdZGpGoOGjxJ_e9MdordMVAosNhZ.png", "Cuphead is a classic run and gun action game heavily focused on boss battles. Inspired by cartoons of the 1930s, the visuals and audio are painstakingly created with the same techniques of the era, i.e. ");
			productRepository.save(cuphead);
			Product zelda = new Product("The legent of Zelda: Breath of the Wild",60.00,10,30,LocalDate.of(2017,3,3),List.of(ProductCategory.ADVENTURE),Platform.SWITCH,ProductStatus.LAUNCHED,0, "https://static.wikia.nocookie.net/doblaje/images/1/1a/The_Legend_of_Zelda_Breath_of_the_Wild.png/revision/latest?cb=20200320051740&path-prefix=es", "Forget everything you know about The Legend of Zelda series of games. Explore and discover a world full of adventures in The Legend of Zelda: Breath of the Wild, a new saga that breaks the mold of the acclaimed series." );
			productRepository.save(zelda);
			Product newZelda = new Product("The legent of Zelda: Tears of the Kingdom",70.00,0,70,LocalDate.of(2023,12,24),List.of(ProductCategory.ADVENTURE),Platform.SWITCH,ProductStatus.COMINGSOON,0, "https://www.notebookcheck.org/fileadmin/_processed_/1/7/csm_The_Legend_of_Zelda_Tears_of_the_Kingdom_a015cd25fb.jpg", "no description at the moment, wait until the game is released");
			productRepository.save(newZelda);
			Product fnaf = new Product("Five Nights at Freddy's",5.00,10,30,LocalDate.of(2014,8,14),List.of(ProductCategory.STRATEGY),Platform.PC,ProductStatus.LAUNCHED,0, "https://image.api.playstation.com/vulcan/img/cfn/11307vgQElwqj713tcLfBlPXZCtY2wg-YYFPRa8LfMgJeXlkNj9xFh3Eks_cF-p-k2O5Y2UUNYE_AF5bKZ-QRlLQV80UeplF.png","Welcome to your new summer job at Freddy Fazbear's Pizza, where kids and parents alike come for entertainment and food! The main attraction is Freddy Fazbear, of course; and his two friends. They are animatronic robots, programmed to please the crowds!" );
			productRepository.save(fnaf);
			Product fnaf2 = new Product("Five Nights at Freddy's 2",4.00,10,30,LocalDate.of(2014,11,11),List.of(ProductCategory.STRATEGY),Platform.PC,ProductStatus.LAUNCHED,0, "https://static.wikia.nocookie.net/five-nights-at-freddys-espanol/images/a/aa/Five_Nights_At_Freddy%27s_2.png/revision/latest?cb=20141121004719&path-prefix=es", "Welcome back to the new and improved Freddy Fazbear's Pizza! As always, Fazbear Entertainment is not responsible for death or dismemberment.");
			productRepository.save(fnaf2);
			Product fnaf3 = new Product("Five Nights at Freddy's 3",6.00,10,30,LocalDate.of(2015,3,2),List.of(ProductCategory.STRATEGY),Platform.PC,ProductStatus.LAUNCHED,0, "https://argengamestore.com/wp-content/uploads/2015/03/capsule_616x353-2.jpg", "Thirty years after Freddy Fazbear's Pizza closed it's doors, the events that took place there have become nothing more than a rumor and a childhood memory");
			productRepository.save(fnaf3);
			Product fnaf4 = new Product("Five Nights at Freddy's 4",10.00,10,30,LocalDate.of(2015,6,23),List.of(ProductCategory.STRATEGY),Platform.PC,ProductStatus.LAUNCHED,0, "https://static.wikia.nocookie.net/freddy-fazbears-pizza/images/2/28/FNaF4_-_Portada.jpg/revision/latest?cb=20150722220108&path-prefix=es", "In this last chapter of the Five Nights at Freddy's original story, you must once again defend yourself against Freddy Fazbear, Chica, Bonnie, Foxy, and even worse things that lurk in the shadows." );
			productRepository.save(fnaf4);
			Product fnafsis = new Product("Five Nights at Freddy's Sister Location",10.00,10,30,LocalDate.of(2016,10,7),List.of(ProductCategory.STRATEGY),Platform.PC,ProductStatus.LAUNCHED,0, "https://static.wikia.nocookie.net/freddy-fazbears-pizza/images/8/83/FNaF_SL_-_Box_Art_%28Grande%29.jpeg/revision/latest?cb=20160802184441&path-prefix=es", "Welcome to Circus Baby's Pizza World, where family fun and interactivity go beyond anything you've seen at those other pizza places! Now hiring: Late night technician. Must enjoy cramped spaces and be comfortable around active machinery.");
			productRepository.save(fnafsis);
			Product crash = new Product("Crash Bandicoot N. Sane Trilogy",29.99,5,20, LocalDate.now(),List.of(ProductCategory.ADVENTURE),Platform.XBOX,ProductStatus.LAUNCHED,0, "https://image.api.playstation.com/cdn/UP0002/CUSA07402_00/03ZtrPdjasIxzi8QrzOb2zCIHLMydFbh.png", "Your favorite marsupial, Crash Bandicoot™, is back! He’s enhanced, entranced and ready-to-dance with the N. Sane Trilogy. Relive all your favorite moments in Crash Bandicoot™");
			productRepository.save(crash);
			Product animalCr = new Product("Animal Crossing new horizon",59.99,20,5,LocalDate.of(2020,3,20),List.of(ProductCategory.SIMULATION),Platform.SWITCH,ProductStatus.LAUNCHED,0, "https://animal-crossing.com/new-horizons/assets/img/share-tw.jpg", "Escape to a desert island and create your own paradise as you explore, create and customize in the Animal Crossing: New Horizons game.");
			productRepository.save(animalCr);
			Product alicemd = new Product("Alice: Madness Returns",19.99,40,1,LocalDate.of(2011,6,14),List.of(ProductCategory.ADVENTURE),Platform.XBOX,ProductStatus.LAUNCHED,0, "https://static.wikia.nocookie.net/alice/images/c/c6/AMR_Cover.png/revision/latest?cb=20140525122322&path-prefix=es", "Alice: Madness Returns is a third-person, single-player, action adventure platformer. Visit the grim reality of Victorian London and travel to the beautiful yet ghastly Wonderland to uncover the root of Alice's madness.");
			productRepository.save(alicemd);

			Product asteroids = new Product("Asteroids",19.99,20,5,LocalDate.of(1979,6,14),List.of(ProductCategory.ARCADE), Platform.RETRO,ProductStatus.LAUNCHED,0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQOwNX78EnGnSi3GE-8I9_ILO7WLRgEl1_QUg&usqp=CAU", "The objective of Asteroids is to destroy asteroids and saucers. The player controls a triangular ship that can rotate left and right, fire shots straight forward, and thrust forward.");
			productRepository.save(asteroids);


			Product donkeyKong = new Product("Donkey Kong",19.99,35,10,LocalDate.of(1981,6,14),List.of(ProductCategory.ARCADE), Platform.RETRO,ProductStatus.LAUNCHED,0, "https://upload.wikimedia.org/wikipedia/en/thumb/1/14/Donkey_Kong_flier.jpg/220px-Donkey_Kong_flier.jpg", "Donkey Kong is a 1981 arcade platform video game developed and published by Nintendo. Its gameplay involves maneuvering Mario across platforms to ascend a construction site and rescue Pauline from the giant gorilla named Donkey Kong");
			productRepository.save(donkeyKong);

			Product spaceInvaders = new Product("Space Invaders",19.99,35,10,LocalDate.of(1978,4,1),List.of(ProductCategory.ARCADE), Platform.RETRO,ProductStatus.LAUNCHED,0, "https://upload.wikimedia.org/wikipedia/en/thumb/0/0f/Space_Invaders_flyer%2C_1978.jpg/220px-Space_Invaders_flyer%2C_1978.jpg", "Space Invaders is a fixed shooter in which the player moves a laser cannon horizontally across the bottom of the screen and fires at aliens overhead.");
			productRepository.save(spaceInvaders);


			Product frogger = new Product("Frogger",19.99,25,5,LocalDate.of(1981,8,1),List.of(ProductCategory.ARCADE), Platform.RETRO,ProductStatus.LAUNCHED,0, "https://upload.wikimedia.org/wikipedia/en/thumb/4/4b/Frogger_arcade_flyer.jpg/220px-Frogger_arcade_flyer.jpg", "Frogger is a 1981 arcade action game developed by Konami. The object of the game is to direct frogs to their homes one by one by crossing a busy road and navigating a river full of hazards.");
			productRepository.save(frogger);

			Product pacMan = new Product("Pac-man",19.99,10,2,LocalDate.of(1980,7,1),List.of(ProductCategory.ARCADE), Platform.RETRO,ProductStatus.LAUNCHED,0, "https://upload.wikimedia.org/wikipedia/en/thumb/1/16/Pac_flyer.png/220px-Pac_flyer.png", "Pac-Man is an action maze chase video game. The player controls the eponymous character through an enclosed maze. The objective of the game is to eat all of the dots placed in the maze while avoiding four colored ghosts");
			productRepository.save(pacMan);

			Product mortalKombat = new Product("Mortal Kombat",19.99,10,2,LocalDate.of(1992,10,8),List.of(ProductCategory.ARCADE), Platform.RETRO,ProductStatus.LAUNCHED,0, "https://upload.wikimedia.org/wikipedia/en/3/33/Mortal_Kombat_cover.JPG", "Mortal Kombat is an arcade fighting game developed and published by Midway in 1992.  The game focuses on several characters of various intentions who enter a martial arts tournament with worldly consequences.");
			productRepository.save(mortalKombat);


			Product doom = new Product("Doom",19.99,7,4,LocalDate.of(1993,12,10),List.of(ProductCategory.ARCADE), Platform.RETRO,ProductStatus.LAUNCHED,0, "https://upload.wikimedia.org/wikipedia/en/thumb/5/57/Doom_cover_art.jpg/220px-Doom_cover_art.jpg", "Doom is a 1993 first-person shooter (FPS) game developed by id Software for MS-DOS. Players assume the role of a space marine, popularly known as Doomguy, fighting their way through hordes of invading demons from hell.");
			productRepository.save(doom);


			Product counterStrike = new Product("Doom",9.99,20,10,LocalDate.of(2000,11,9),List.of(ProductCategory.SHOOTER), Platform.PC,ProductStatus.LAUNCHED,0, "https://upload.wikimedia.org/wikipedia/en/thumb/6/67/Counter-Strike_Box.jpg/220px-Counter-Strike_Box.jpg", "Doom is a 1993 first-person shooter (FPS) game developed by id Software for MS-DOS. Players assume the role of a space marine, popularly known as Doomguy, fighting their way through hordes of invading demons from hell.");
			productRepository.save(counterStrike);

			Product batlefield = new Product("Batlefield 2042",49.99,20,5,LocalDate.of(2021,11,19),List.of(ProductCategory.SHOOTER), Platform.PC,ProductStatus.LAUNCHED,0, "https://upload.wikimedia.org/wikipedia/en/thumb/e/ec/Battlefield_2042_cover_art.jpg/220px-Battlefield_2042_cover_art.jpg", "Battlefield 2042 is a multiplayer-focused first-person shooter. As the game is set in the near future, it features futuristic weapons and gadgets like deployable turrets and drones, as well as vehicles that players can control.");
			productRepository.save(batlefield);


			Product batlefieldFive = new Product("Batlefield V",49.99,30,8,LocalDate.of(2018,11,20),List.of(ProductCategory.SHOOTER), Platform.PC,ProductStatus.LAUNCHED,0, "https://upload.wikimedia.org/wikipedia/en/thumb/e/ec/Battlefield_2042_cover_art.jpg/220px-Battlefield_2042_cover_art.jpg", "Battlefield V is a first-person shooter game developed by DICE and published by Electronic Arts. Focuses extensively on party-based features and mechanics, scarcity of resources, and removing abstractions from game mechanics to increase realism.");
			productRepository.save(batlefieldFive);


			Product codModernWF = new Product("Call of Duty: Modern Warfare",29.99,12,6,LocalDate.of(2019,10,25),List.of(ProductCategory.SHOOTER), Platform.PC,ProductStatus.LAUNCHED,0, "https://upload.wikimedia.org/wikipedia/en/thumb/1/1f/Call_of_Duty_Modern_Warfare_%282019%29_cover.jpg/220px-Call_of_Duty_Modern_Warfare_%282019%29_cover.jpg", "Call of Duty: Modern Warfare's single-player campaign focuses on realism and features tactically-based moral choices whereupon the player is evaluated and assigned a score at the end of each level ");
			productRepository.save(codModernWF);

			Product csGo = new Product("Counter Strike Global Offensive",24.99,30,15,LocalDate.of(2014,9,23),List.of(ProductCategory.SHOOTER), Platform.PC,ProductStatus.LAUNCHED,0, "https://upload.wikimedia.org/wikipedia/en/thumb/6/6e/CSGOcoverMarch2020.jpg/220px-CSGOcoverMarch2020.jpg", "Counter-Strike: Global Offensive (CS:GO) is a 2012 multiplayer first-person shooter developed by Valve and Hidden Path Entertainment. It is the fourth game in the Counter-Strike series.");
			productRepository.save(csGo);

			Product superMario = new Product("Super Mario Bros",49.99,20,5,LocalDate.of(1985,9,13),List.of(ProductCategory.ARCADE,ProductCategory.ADVENTURE), Platform.SWITCH,ProductStatus.LAUNCHED,0, "https://i.blogs.es/638318/super_mario_bros_logo/1366_2000.jpeg", "Jump with Mario in search of the princess, who is in the hands of the fearsome kooba king Bowser!");
			productRepository.save(superMario);

			Product minecraft = new Product("Minecraft",21.00,20,50,LocalDate.of(2011,11,18),List.of(ProductCategory.ADVENTURE,ProductCategory.SIMULATION),Platform.PC,ProductStatus.LAUNCHED,0,"https://sm.ign.com/ign_es/screenshot/default/image-1_aeyp.jpg","Explore your own unique world, survive the night and create anything you can imagine!");
			productRepository.save(minecraft);



		};

	}
}
