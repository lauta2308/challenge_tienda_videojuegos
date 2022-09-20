const { createApp } = Vue

createApp({
    data() {
        return {
            datas: [],
            texto: "",
            filtrado: [],
            checks: [],
            listaCheck: [],
            numberPage: 1,
            listaJuegos: [],
            texto: "",
            todosArticulos: [],
            totalPrice: 0,
            productosCarrito: [],
            cantidadProductosCarrito: 0,

        }
    },
    created() {

        this.login();
        this.productos();

        //this.getCategories()

    },
    mounted() {


    },
    methods: {


        productos() {


            this.productosCarrito = JSON.parse(localStorage.getItem("carrito"));
            console.log(this.productosCarrito);

            if (this.productosCarrito === null) {
                console.log("no funciona");

            } else {
                for (let i = 0; i < this.productosCarrito.length; i++) {
                    let productos = new Object();

                    productos.id = this.productosCarrito[i].id;
                    productos.name = this.productosCarrito[i].name;
                    productos.price = this.productosCarrito[i].price;
                    productos.category = this.productosCarrito[i].category;
                    productos.platform = this.productosCarrito[i].platform;

                    productos.cantidad = this.productosCarrito[i].cantidad;


                    productos.stock = this.productosCarrito[i].stock;
                    productos.total = this.productosCarrito[i].cantidad * this.productosCarrito[i].price;
                    this.totalPrice += productos.total;
                    this.cantidadProductosCarrito += this.productosCarrito[i].cantidad;
                    this.listaJuegos.push(productos);


                }
                localStorage.setItem('carrito', JSON.stringify(this.listaJuegos));
            }

            /*
                        this.productosCarrito.map((producto) => {
                            let productos = new Object();

                            productos.id = producto.id;
                            productos.name = producto.name;
                            productos.price = producto.price;
                            productos.category = producto.category;
                            productos.platform = producto.platform;
                            productos.cantidad = 1;
                            productos.stock = producto.stock;
                            productos.total = productos.cantidad * productos.price;
                            this.totalPrice += productos.total;
                            this.listaJuegos.push(productos);

                        })
            */
            console.log(this.listaJuegos);
        },

        deleteUnit(game) {
            index = this.listaJuegos.findIndex(articulo => articulo.id === game.id);

            if (this.productosCarrito[index]["cantidad"] > 1) {
                this.productosCarrito[index]["cantidad"] -= 1;
                this.listaJuegos[index]["cantidad"] -= 1;
                this.listaJuegos[index]["total"] -= this.listaJuegos[index]["price"];
                this.totalPrice -= this.listaJuegos[index]["price"];
                console.log(this.listaJuegos[index])
                localStorage.setItem("carrito", JSON.stringify(this.listaJuegos));
                this.cantidadProductosCarrito -= 1;
            } else {
                this.eliminarCarrito(game);


                // this.listaJuegos.filter(producto => producto.id !== game.id);
                // console.log(this.productosCarrito);
                // localStorage.setItem("carrito", JSON.stringify(this.listaJuegos));
            }




            // let selectGame = document.getElementsByClassName(game.id.toString())[0];
            // console.log(selectGame);
            // let productQuantity = parseInt(selectGame.textContent);
            // let totalProduct = document.getElementById(game.id.toString());
            // let productPrice = parseInt(totalProduct.textContent);

            // if (productQuantity > 1) {
            //     productQuantity = productQuantity - 1;
            //     selectGame.textContent = productQuantity;
            //     productPrice = productPrice - game.price;
            //     totalProduct.textContent = productPrice;
            // } else {
            //     this.listaJuegos = this.listaJuegos.filter(product => product.id !== game.id);
            //     localStorage.setItem('carrito', JSON.stringify(this.listaJuegos));
            // }

            // console.log(productQuantity);

        },

        addMoreUnits(game) {

            index = this.listaJuegos.findIndex(articulo => articulo.id === game.id);

            if ((this.productosCarrito[index]["cantidad"] + 1) <= this.productosCarrito[index]["stock"]) {
                console.log(this.listaJuegos);

                this.productosCarrito[index]["cantidad"] += 1;
                this.listaJuegos[index]["cantidad"] += 1;
                this.listaJuegos[index]["total"] += this.listaJuegos[index]["price"];
                this.totalPrice += this.listaJuegos[index]["price"];
                console.log(this.listaJuegos[index])
                this.cantidadProductosCarrito += 1;
            }

            localStorage.setItem("carrito", JSON.stringify(this.listaJuegos));

            /*
            let juego = this.listaJuegos.filter(juego => juego.id === game.id);

            let selectGame = document.getElementsByClassName(game.id.toString())[0];
            console.log(selectGame);
            let productQuantity = parseInt(selectGame.textContent);
            let totalProduct = document.getElementById(game.id.toString());
            let productPrice = parseInt(totalProduct.textContent);
            console.log(totalProduct);
            console.log(productPrice);
            console.log(game.price);


            if (productQuantity < game.stock) {

                productQuantity = productQuantity + 1;
                selectGame.textContent = productQuantity;
                productPrice = productPrice + game.price;
                totalProduct.textContent = productPrice;
                juego.quantity = productQuantity;

                /*
                juego.totalPrice = productPrice;
                this.listaJuegos.push(juego);
                localStorage.setItem("carrito", JSON.stringify(this.listaJuegos));
                this.listaJuegos = this.listaJuegos.filter(juego => juego.id !== game.id);

            }
            */





        },


        eliminarCarrito(info) {
            index = this.listaJuegos.findIndex(articulo => articulo.id === info.id);
            this.cantidadProductosCarrito -= this.listaJuegos[index].cantidad;
            if (this.cantidadProductosCarrito > 1) {




                this.listaJuegos = this.listaJuegos.filter(response => response != info)


                localStorage.setItem("carrito", JSON.stringify(this.listaJuegos));
                this.totalPrice -= this.listaJuegos[index]["cantidad"] * this.listaJuegos[index]["price"]


            } else {
                this.listaJuegos = this.listaJuegos.filter(response => response != info)


                localStorage.setItem("carrito", JSON.stringify(this.listaJuegos));
                window.location.reload();
                console.log("activado");
            }
        },
        async prueba() {
            localStorage.setItem("buscar", this.texto)
            window.location.href = "/index.html"
        },

        login() {
            const options = {
                method: 'GET',
                headers: {
                    'X-RapidAPI-Key': '327a7782d6msh933898697bcd159p1465efjsndb73b806052c',
                    'X-RapidAPI-Host': 'free-to-play-games-database.p.rapidapi.com'
                }
            };
            fetch(`https://free-to-play-games-database.p.rapidapi.com/api/games`, options)
                .then(response => response.json())
                .then(response => {
                    //this.titulo = response.results
                    this.datas = response
                    this.filtrado = this.datas
                    let check = this.datas.map(data => data.genre)
                    check.forEach(genre => {
                        if (!this.checks.includes(genre)) {
                            this.checks.push(genre)
                        }
                    })

                })
                .catch(err => console.log(err));

        },




        //https://movies-app1.p.rapidapi.com/api/movies
    }
}).mount('#app')

/*
            const options = {
            method: 'GET',
            url: 'https://rickandmortyapi.com/api/character?page=1',
            headers: {
                'X-RapidAPI-Key': '327a7782d6msh933898697bcd159p1465efjsndb73b806052c',
                'X-RapidAPI-Host': 'movies-app1.p.rapidapi.com'
            }
            };

            axios.request(options).then(function (response)  {
                console.log(response);
                this.titulo = response.data.results[0].name
                console.log(this.titulo);
                this.datas = response.data.results
                console.log(this.datas);

            }).catch(function (error) {
                console.error(error);
            });*/