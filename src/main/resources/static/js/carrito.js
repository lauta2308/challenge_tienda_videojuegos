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
            totalPriceProduct: 0,
            productosCarrito: [],
            cantidadProductosCarrito: 0,
            shippingAddress: "",
            shippingCity: "",
            zipCode: "",
            paymentMethod: "",
            discountCode: "",
            existClient: false,
            totalQuantity: 0,
            monto: 0,
            numeroTarjeta: "",
            pedido: [],
            clientInformation: [],
            checkImprimir: [],
            infoClientAlRecargar: JSON.parse(localStorage.getItem(`logged-in`))

        }
    },
    created() {


        this.productos();

        if (this.infoClientAlRecargar) {
            if (this.infoClientAlRecargar.length > 0) {
                this.current();
            }
        }




    },
    mounted() {


    },
    computed: {

    },
    methods: {
        imprimir() {
            return print()
        },

        submitUrl() {
            let numero = this.numeroTarjeta.slice(0, 19).split(' ')
            let numeroTarjeta = ""
            for (let i = 0; i < numero.length; i++) {
                numeroTarjeta += numero[i] + "-"
            }
            numeroTarjetaCompleto = numeroTarjeta.slice(0, 19)


            axios.get('/api/data', { params: { url: `http://localhost:8085/api/pay?amount=${this.totalPrice}&cardNumber=${numeroTarjetaCompleto}` } })
                .then(
                    this.realizarCompra(),

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
                    "paymentMethod": "PAYPAL",
                    "products": buyProducts,
                    "codeDiscount": discountCode
                }).then(response => {
                    this.vaciarCarrito()
                    window.location.href = "/listaProducto.html"
                })
                .catch(err => {
                    console.log(err);
                })


        },
        descargarReporte: function() {

            var doc = new jsPDF('p', 'pt', 'letter');
            var margin = 10;
            var scale = (doc.internal.pageSize.width - margin * 2) / document.body.scrollWidth;
            doc.html(document.querySelector(".contenedor-tabla"), {
                x: margin,
                y: margin,
                html2canvas: {
                    scale: scale,
                },
                callback: function(doc) {
                    //doc.output("dataurlnewwindow", { filename: "comprobante de pago.pdf" });
                    doc.save("comprobante.pdf")
                }
            })

        },
        current() {

            axios.get("/api/clients/current")
                .then(response => {
                    this.existClient = true

                    this.clientInformation = response.data
                    let listPedido = this.clientInformation.pedidos.sort((a, b) => b.id - a.id)

                    this.pedido = listPedido[0]

                    let product = this.pedido.products
                    for (let i = 0; i < product.length; i++) {
                        let total = product[i].quantity * product[i].product.price

                        this.totalPriceProduct = this.totalPriceProduct + total
                        this.totalQuantity = this.totalQuantity + product[i].quantity
                    }

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
                    this.productosCarrito = productosAgregados;
                    window.location.reload();

                } else {
                    this.productosCarrito = JSON.parse(localStorage.getItem("productos"));

                }
            }



            if (this.productosCarrito === null) {


            } else {


                let productos = this.productosCarrito.filter(producto => producto.carrito === true);

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



        },



        deleteUnit(game) {
            index = this.listaJuegos.findIndex(articulo => articulo.id === game.id);
            let indexCarrito = this.productosCarrito.findIndex(articulo => articulo.id === game.id);
            if (this.listaJuegos[index]["cantidad"] > 1) {
                this.productosCarrito[indexCarrito]["cantidad"] -= 1;
                this.listaJuegos[index]["cantidad"] -= 1;
                this.listaJuegos[index]["total"] -= this.listaJuegos[index]["price"];
                this.totalPrice -= this.listaJuegos[index]["price"];

                localStorage.setItem("productos", JSON.stringify(this.productosCarrito));
                this.cantidadProductosCarrito -= 1;
            } else {
                this.eliminarCarrito(game);



            }




            const Toast = Swal.mixin({
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true,
                didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                }
            })

            Toast.fire({
                icon: 'success',
                title: 'You deleted one unit'
            })


        },


        addMoreUnits(game) {

            let index = this.productosCarrito.findIndex(articulo => articulo.id === game.id);
            let indexx = this.listaJuegos.findIndex(articulo => articulo.id === game.id);

            if ((this.productosCarrito[index]["cantidad"] + 1) <= this.productosCarrito[index]["stock"]) {

                this.productosCarrito[index]["cantidad"] += 1
                this.listaJuegos[indexx]["cantidad"] += 1;
                this.listaJuegos[indexx]["total"] += this.listaJuegos[indexx]["price"];
                this.totalPrice += this.listaJuegos[indexx]["price"];

                this.cantidadProductosCarrito += 1;

            }

            localStorage.setItem("productos", JSON.stringify(this.productosCarrito));

            localStorage.setItem("carrito", JSON.stringify(this.listaJuegos));



            const Toast = Swal.mixin({
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true,
                didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                }
            })

            Toast.fire({
                icon: 'success',
                title: 'You added one unit'
            })



        },


        eliminarCarrito(info) {
            index = this.listaJuegos.findIndex(articulo => articulo.id === info.id);
            this.cantidadProductosCarrito -= this.listaJuegos[index].cantidad;
            let cantidadProductos = JSON.parse(localStorage.getItem("productos"))





            let indexProducto = cantidadProductos.findIndex(articulo => articulo.id === info.id);
            cantidadProductos[indexProducto].carrito = false;




            this.totalPrice -= this.listaJuegos[index].total

            this.listaJuegos = this.listaJuegos.filter(response => response != info)
            localStorage.setItem("productos", JSON.stringify(cantidadProductos))
            localStorage.setItem("carrito", JSON.stringify(this.listaJuegos));


            const Toast = Swal.mixin({
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true,
                didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                }
            })

            Toast.fire({
                icon: 'error',
                title: 'Deleted of cart'
            })


        },

        solicitudRealizarCompra() {

            if (this.existClient == false) {
                swal.fire({
                    title: "You can't make the purchase",
                    text: "You need to login or register to perform this action",
                    icon: 'error',
                    confirmButtonText: 'Accept',
                })
            } else if (this.listaJuegos.length === 0) {
                swal.fire({
                    title: "You can't make the purchase",
                    text: "The shopping cart is empty",
                    icon: 'error',
                    confirmButtonText: 'Accept',
                })
            } else {
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