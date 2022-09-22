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
            shippingAddress: "",
            shippingCity: "",
            zipCode: "",
            paymentMethod: "",
            discountCode: "",
            existClient: false,
            totalPrice: 0,
            totalQuantity: 0,
            monto: 0,
            numeroTarjeta: "",
            pedido: [],
            clientInformation: [],

        }
    },
    created() {


        this.productos();
        this.current()



    },
    mounted() {


    },
    computed: {

    },
    methods: {
        submitUrl() {
            let numero = this.numeroTarjeta.slice(0,19).split(' ')
            let numeroTarjeta = ""
            for (let i = 0; i < numero.length; i++) {
                numeroTarjeta += numero[i] + "-"
            }
            numeroTarjetaCompleto = numeroTarjeta.slice(0,19)
            
            axios.get('/api/data', { params: { url: `http://localhost:8085/api/pay?amount=${this.totalPrice}&cardNumber=${numeroTarjetaCompleto}` } })
                .then(
                    this.realizarCompra(),
                    console.log("se envió la petición")
                )
            .catch(err => {
                console.log(err.response.data);
            })
        },
        realizarCompra() {
            let buyProducts = [];

            this.listaJuegos.forEach(juego => {
                let productCart = new Object();

                productCart.idProducto = juego.id,

                    productCart.productQuantity = juego.cantidad;

                buyProducts.push(productCart);

            })

            let discountCode = "";
            if (this.discountCode.length === 0) {
                discountCode = "SinDescuento"
            } else {
                discountCode = this.discountCode;
            }

            axios.post("/api/clients/current/pedido", {
                "shippingAddress": this.shippingAddress,
                "shippingCity": this.shippingCity,
                "zipCode": this.zipCode,
                "paymentMethod": this.paymentMethod,
                "products": buyProducts,
                "codeDiscount": discountCode
            }).then( response => {
                
                this.vaciarCarrito()
                window.location.href = "/listaProducto.html"
            })
            .catch(err => {
                console.log(err.response.data);
            })


        },
        current(){
            console.log("funciona")
            axios.get("/api/clients/current")
            .then(response => {
                this.existClient = true
                console.log(this.existClient)
                this.clientInformation = response.data
                let listPedido = this.clientInformation.pedidos.sort((a,b) => b.id - a.id)
                console.log(listPedido)
                this.pedido = listPedido[0]
                console.log(this.pedido)
                let product = this.pedido.products
                for (let i = 0; i < product.length; i++) {
                    let total = product[i].quantity * product[i].product.price
                    console.log(total)
                    this.totalPrice = this.totalPrice +  total
                    this.totalQuantity = this.totalQuantity + product[i].quantity
                }
                console.log(this.totalPrice)
            })
            .catch(error => this.existClient = false)
        },
        vaciarCarrito() {
            let todosProductos = JSON.parse(localStorage.getItem("productos"))
            todosProductos.forEach(response => {
                if (response.carrito == true) {
                    response.carrito = false
                    this.productos()
                }
            })
            localStorage.setItem("productos", JSON.stringify(todosProductos))
        },
        amountFixed(number) {
            return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', minimumFractionDigits: 2 }).format(number);
        },


        productos() {


            let productosAgregados = JSON.parse(localStorage.getItem("carrito"));

            if (productosAgregados) {
                if (productosAgregados.length > 0) {
                    window.location.reload();
                    this.productosCarrito = productosAgregados;
                    console.log(productosAgregados === true)
                    console.log("funciona prod agregados")
                } else {
                    this.productosCarrito = JSON.parse(localStorage.getItem("productos"));
                    console.log("funciona productos")
                }
            }

            console.log(this.productosCarrito);

            if (this.productosCarrito === null) {
                console.log("no funciona");

            } else {
                console.log(this.productosCarrito)

                let productos = this.productosCarrito.filter(producto => producto.carrito === true);
                console.log(this.productosCarrito)
                for (let i = 0; i < productos.length; i++) {
                    let productosM = new Object();

                    productosM.id = productos[i].id;
                    productosM.name = productos[i].name;
                    productosM.price = productos[i].price;
                    productosM.category = productos[i].category;
                    productosM.platform = productos[i].platform;
                    productosM.image = productos[i].image;
                    productosM.cantidad = productos[i].cantidad;


                    productosM.stock = productos[i].stock;
                    productosM.total = productos[i].cantidad * productos[i].price;
                    this.totalPrice += productosM.total;

                    this.cantidadProductosCarrito += productos[i].cantidad;
                    this.listaJuegos.push(productosM);


                }
                localStorage.setItem('carrito', JSON.stringify(this.listaJuegos));
            }


            console.log(this.listaJuegos);
        },



        deleteUnit(game) {
            index = this.listaJuegos.findIndex(articulo => articulo.id === game.id);
            let indexCarrito = this.productosCarrito.findIndex(articulo => articulo.id === game.id);
            if (this.listaJuegos[index]["cantidad"] > 1) {
                this.productosCarrito[indexCarrito]["cantidad"] -= 1;
                this.listaJuegos[index]["cantidad"] -= 1;
                this.listaJuegos[index]["total"] -= this.listaJuegos[index]["price"];
                this.totalPrice -= this.listaJuegos[index]["price"];
                console.log(this.listaJuegos[index])
                localStorage.setItem("productos", JSON.stringify(this.productosCarrito));
                this.cantidadProductosCarrito -= 1;
            } else {
                this.eliminarCarrito(game);



            }






        },


        addMoreUnits(game) {

            let index = this.productosCarrito.findIndex(articulo => articulo.id === game.id);
            let indexx = this.listaJuegos.findIndex(articulo => articulo.id === game.id);

            if ((this.productosCarrito[index]["cantidad"] + 1) <= this.productosCarrito[index]["stock"]) {
                console.log(this.listaJuegos);
                console.log(this.productosCarrito);
                this.productosCarrito[index]["cantidad"] += 1
                this.listaJuegos[indexx]["cantidad"] += 1;
                this.listaJuegos[indexx]["total"] += this.listaJuegos[indexx]["price"];
                this.totalPrice += this.listaJuegos[indexx]["price"];
                console.log(this.listaJuegos[indexx])
                this.cantidadProductosCarrito += 1;

            }

            localStorage.setItem("productos", JSON.stringify(this.productosCarrito));
            console.log(this.productosCarrito);
            localStorage.setItem("carrito", JSON.stringify(this.listaJuegos));






        },


        eliminarCarrito(info) {
            index = this.listaJuegos.findIndex(articulo => articulo.id === info.id);
            this.cantidadProductosCarrito -= this.listaJuegos[index].cantidad;
            let cantidadProductos = JSON.parse(localStorage.getItem("productos"))

            console.log(cantidadProductos);



            let indexProducto = cantidadProductos.findIndex(articulo => articulo.id === info.id);
            cantidadProductos[indexProducto].carrito = false;




            this.totalPrice -= this.listaJuegos[index].total
            console.log(this.listaJuegos[index].cantidad)
            console.log(this.listaJuegos[index]["price"])
            console.log(this.totalPrice);
            this.listaJuegos = this.listaJuegos.filter(response => response != info)
            localStorage.setItem("productos", JSON.stringify(cantidadProductos))
            localStorage.setItem("carrito", JSON.stringify(this.listaJuegos));

            console.log("activado");


            //window.location.reload();


        },

        solicitudRealizarCompra() {

            if (this.listaJuegos.length === 0) {
                alert("El carrito está vacío!")
            } else if (this.existClient == false) {
                alert("no esta autenticado")
            }else{
                window.location.href = "/postnet.html"
            }

        },

        

        async prueba() {
            localStorage.setItem("buscar", this.texto)
            window.location.href = "/index.html"
        },


        // Front detalles
        moveSidebar() {
            let button = document.getElementById("button-menu").classList;
            let spanCategories = document.getElementsByClassName("span-categories")[0].classList;
            let sidebar = document.getElementsByClassName("sidebar-categories")[0].classList;

            if (spanCategories.length == 1) {
                button.add("open")
                sidebar.add("active")
                spanCategories.add("transparent")
            } else {
                button.remove("open")
                sidebar.remove("active")
                spanCategories.remove("transparent")
            }
        },

        moveUser() {
            let dropdown = document.getElementsByClassName("user-dropdown")[0].classList;

            if (dropdown.length == 1) {
                dropdown.add("active")
            } else {
                dropdown.remove("active")
            }

        },

        turnLogIn() {
            register = document.getElementsByClassName("register")[0].classList;
            login = document.getElementsByClassName("login")[0].classList;

            if (register.contains("none")) {
                register.remove("none")
                login.add("none")
            } else {
                register.add("none")
                login.remove("none")
            }
        },

        discountedPrice(price, discount) {
            return price + discount * price / 100
        },






    }
}).mount('#app')