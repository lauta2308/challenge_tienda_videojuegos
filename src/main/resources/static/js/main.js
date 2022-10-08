const { createApp } = Vue

createApp({

    data() {

        return {
            option: "",
            emailLogin: "",
            clientInformation: [],
            idProductFavorite: 0,
            passwordLogin: "",
            categories: [],
            products: [],
            datas: [],
            filtrado: [],
            checks: [],
            listaCheck: [],
            numberPage: 1,
            products: [],
            listaJuegos: JSON.parse(localStorage.getItem(`productos`)),
            search: "",
            filterProducts: [],
            productsFilter: [],
            existClient: false,
            listIdFavourites: [],
            listProductsFavorites: [],
            listFavorites: [],
            rolClient: "",
            pedidos: [],
            pedido: [],
            pedidoActivo: {},
            totalPedidoActivo: 0,
            allClients: [],
            clienteSeleccionado: {},
            pedidosClienteSeleccionado: [],
            pedidoProductos: [],
            pregunta: false,
            infoClientAlRecargar: JSON.parse(localStorage.getItem(`clientEmail`)),

            register: {
                name: "",
                lastName: "",
                email: "",
                nacimiento: "",
                password: "",
            },

        }
    },
    created() {
        this.loadCategories();



        if (this.infoClientAlRecargar) {
            if (this.infoClientAlRecargar.length > 0) {
                this.current();
            }
        }



    },
    computed: {





        filterSearch() {


            if (this.search.length === 0 && this.listaCheck.length === 0) {

                this.filterProducts = this.products;

            } else if (this.search.length > 0 && this.listaCheck.length === 0) {

                this.filterProducts = this.products.filter(product => product.name.toLowerCase().includes(this.search.toLowerCase()) || product.platform.toLowerCase().includes(this.search.toLowerCase()))
            } else if (this.search.length === 0 && this.listaCheck.length > 0) {

                this.filterProducts = [];

                for (let i = 0; i < this.listaCheck.length; i++) {

                    this.products.forEach(product => {
                        product.category.forEach(category => {
                            if (category.includes(this.listaCheck[i])) {
                                this.filterProducts.push(product)

                            }
                        })
                    })
                }
            } else if (this.search.length > 0 && this.listaCheck.length > 0) {

                this.filterProducts = [];

                for (let i = 0; i < this.listaCheck.length; i++) {
                    this.products.filter((product) => {

                        if (product.category === this.listaCheck[i] && product.name.toLowerCase().includes(this.search.toLowerCase())) {
                            this.filterProducts.push(product);
                        }

                    })
                }
            }
        },

    },
    methods: {
        registrada() {



            axios.post("/api/clients?name=" + this.register.name + "&lastName=" + this.register.lastName + "&email=" + this.register.email + "&birthDate=" + this.register.nacimiento + "&password=" + this.register.password)


            swal.fire({
                title: "Welcome to REBEL " + this.register.name,
                text: "We will send you to the home page",
                icon: 'success',
                confirmButtonText: 'Continue',
            }).then(result => {



                axios.post("/api/login", `email=${this.register.email}&password=${this.register.password}`, )
                    .then(response => {
                        localStorage.setItem('clientEmail', JSON.stringify(this.register.email));
                        window.location.href = "/index.html"


                    })

            })


        },

        changeActiveOrder(orderId) {

            if (this.rolClient == "ADMIN") {
                this.pedidoActivo = this.pedidosClienteSeleccionado.filter(pedido => pedido.id == orderId)[0]
            }

            if (this.rolClient == "USER") {
                this.pedidoActivo = this.pedidos.filter(pedido => pedido.id == orderId)[0]
            }

            this.totalPedidoActivo = 0
            this.pedidoActivo.products.forEach(pedido => {
                this.totalPedidoActivo = this.totalPedidoActivo + pedido.product.price * pedido.quantity
            })
            this.pedidoProductos = this.pedidoActivo.products
        },


        amountFixed(number) {
            return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', minimumFractionDigits: 2 }).format(number);
        },
        addToFaavorites(id) {
            axios.patch("/api/clients/current/favourites", `productId=${id}`)
                .then((response) => {
                    this.current()
                    this.loadProducts()
                }).then(() => {
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
                        title: 'Added to favorites'
                    })
                })
        },
        deleteFavorites(id) {
            let arrayFavorites = this.clientInformation.favouritesProducts

            for (let i = 0; i < arrayFavorites.length; i++) {

                if (arrayFavorites[i].product.id == id) {
                    this.favouritesId = arrayFavorites[i].id
                    axios.post("/api/clients/current/favourites", `favouriteProductId=${this.favouritesId}`)
                        .then((response) => {
                            window.location.reload();
                        })
                }
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
                icon: 'error',
                title: 'Deleted of favorites'
            })
        },
        deleteListFavorites(id) {
            axios.post("/api/clients/current/favourites", `favouriteProductId=${id}`)
                .then((response) => {
                    window.location.reload();
                })
        },

        logOut() {

            swal.fire({
                title: "Are you sure?",
                text: "You are about to log out of REBEL",
                icon: 'question',
                confirmButtonText: 'Accept',
                showCancelButton: "true",
                cancelButtonText: 'Cancel',
            }).then(result => {
                if (result.isConfirmed) {
                    axios.post("/api/logout")
                        .then(response => {
                            localStorage.setItem('clientEmail', JSON.stringify(""));
                            this.existClient = false
                            window.location.href = "/index.html"
                        })
                }
            })


        },

        loginUser() {

            axios.post("/api/login", `email=${this.emailLogin}&password=${this.passwordLogin}`)

            .then(response => {

                swal.fire({
                    title: "Welcome back to REBEL",
                    text: "We will send you to the home page",
                    icon: 'success',
                    confirmButtonText: 'Continue',
                }).then(result => {
                    localStorage.setItem('clientEmail', JSON.stringify(this.emailLogin));
                    window.location.href = "/index.html"
                })

            })
        },
        current() {
            axios.get("/api/clients/current")
                .then(response => {
                    this.rolClient = response.data.rol
                    if (this.rolClient === "ADMIN") {
                        this.getClients();
                    }
                    this.pedidos = response.data.pedidos;
                    this.existClient = true
                    this.clientInformation = response.data
                    let listFavourites = this.clientInformation.favouritesProducts
                    listFavourites.forEach(response => {
                        this.listProductsFavorites.push(response)
                    })
                    this.listProductsFavorites.forEach(response => {
                        this.listFavorites.push(response.product)
                    })

                    this.listFavorites.forEach(response => {
                        this.listIdFavourites.push(response.id)
                    })

                    let listPedido = this.clientInformation.pedidos.sort((a, b) => b.id - a.id)
                    this.pedido = listPedido[0]

                })
        },
        loadProducts() {
            axios.get("/api/products").then(response => {
                this.products = response.data;
                this.products.forEach(product => {
                    product.favourite = false;
                })

                if (this.listaJuegos == null || this.listaJuegos.length != this.products.length) {
                    this.products.forEach(product => {
                        product.carrito = false;
                    })

                    this.filterProducts = this.products
                } else {
                    this.products = this.listaJuegos;
                    this.filterProducts = this.products
                    this.filterProducts.forEach(product => {
                        product.favourite = false;
                    })

                }


            }).then(response => {
                this.filterProducts.forEach(e => {
                    this.listIdFavourites.forEach(element => {
                        if (e.id == element) {
                            e.favourite = true;
                        }

                    });



                });

            })

        },

        addToCart(addGame) {


            //let producto = this.listaJuegos.filter(item => item.id === addGame.id)
            index = this.filterProducts.findIndex(item => item.id === addGame.id);
            let filtrado = this.filterProducts;
            filtrado[index].cantidad = 1;
            filtrado[index].carrito = true
            localStorage.setItem('productos', JSON.stringify(filtrado));


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
                title: 'Added to cart'
            })
        },

        deleteToCart(deleteGame) {
            index = this.filterProducts.findIndex(item => item.id === deleteGame.id);
            let filtrado = this.filterProducts;
            filtrado[index].carrito = false;

            localStorage.setItem('productos', JSON.stringify(filtrado));

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

        textoBotones(elementos) {

            let x = this.filtrado

            for (let i = 0; i < x.length; x++) {
                if (x[i] === elementos) {
                    elementos.textBoton = "Borrar del carrito"
                } else {
                    elementos.textBoton = "Agregar al carrito"
                }
            }


        },

        deleteFromCart(deleteGame) {
            this.listaJuegos = this.listaJuegos.filter(game => game.id !== deleteGame.id);
            localStorage.setItem('seleccion', JSON.stringify(this.listaJuegos));
        },

        // Get data
        async loadCategories() {

            await axios.get("/api/products/categories").then(response => {

                this.categories = response.data;

            }).then(response => this.loadProducts())

        },
        select(option) {
            if (option == "menos") {
                this.filterProducts.sort((a, b) => {
                    if (a.price > b.price) { return 1 }
                    if (a.price < b.price) { return -1 }
                    return 0
                })
            }
            if (option == "mas") {
                this.filterProducts.sort((a, b) => {
                    if (a.price < b.price) { return 1 }
                    if (a.price > b.price) { return -1 }
                    return 0
                })
            }
            if (option == "a") {
                this.filterProducts.sort((a, b) => {
                    if (a.name > b.name) { return 1 }
                    if (a.name < b.name) { return -1 }
                    return 0
                })
            }
            if (option == "z") {
                this.filterProducts.sort((a, b) => {
                    if (a.name < b.name) { return 1 }
                    if (a.name > b.name) { return -1 }
                    return 0
                })
            }
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

        moneyFormat(number, digits) {
            return new Intl.NumberFormat('es-CO', {
                style: 'currency',
                currency: 'COP',
                minimumFractionDigits: digits,
                maximumFractionDigits: digits
            }).format(number);
        },

        getClients() {
            axios.get("/api/admin/clients").then(response => {
                this.allClients = response.data

            })
        },
        obtenerProductosCliente(id) {
            this.totalPedidoActivo = 0
            this.pregunta = true
            this.clienteSeleccionado = this.allClients.filter(cliente => cliente.id == id)[0]
            this.pedidosClienteSeleccionado = this.clienteSeleccionado.pedidos
        },

        alertaOne() {
            swal.fire({
                title: 'Your message has been sent',
                text: "Thank you for your help collaborating with the page, we will answer your question as soon as possible",
                icon: 'success',
                confirmButtonText: 'Accept',
            })
        },

        alertaTwo() {
            swal.fire({
                title: 'Your message has been sent',
                text: "Thank you for your help collaborating with the page, we will answer your question as soon as possible",
                icon: 'success',
                confirmButtonText: 'Accept',
            })
        },


    },

}).mount('#app')


window.onload = function() {
    $("#onload").fadeOut();
    $("body").removeClass("hidden")
}