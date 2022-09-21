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
        }
    },
    created() {
        this.loadCategories();
        this.current();
        //this.loadProducts();
    },
    computed: {




        filterSearch() {


            if (this.search.length === 0 && this.listaCheck.length === 0) {

                this.filterProducts = this.products;

            } else if (this.search.length > 0 && this.listaCheck.length === 0) {

                this.filterProducts = this.products.filter(product => product.name.toLowerCase().includes(this.search.toLowerCase()) || product.platform.toLowerCase().includes(this.search.toLowerCase()))
                console.log(this.products);
                console.log(this.filterProducts);
            } else if (this.search.length === 0 && this.listaCheck.length > 0) {

                this.filterProducts = [];

                for (let i = 0; i < this.listaCheck.length; i++) {

                    this.products.forEach(product => {
                        product.category.forEach(category => {
                            if (category === this.listaCheck[i]) {
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
        addToFaavorites(id){
            axios.patch("/api/clients/current/favourites",`productId=${id}`)
            .then((response) => {
                console.log(response);
            })
            .catch((error) => console.log(error))
        },
        deleteFavorites(id){
            axios.delete("/api/clients/current/favourites",`favouriteProductId=${id}`)
            .then((response) => {
                console.log(response);
            })
            .catch((error) => console.log(error))
        },
        logOut(){
            axios.post("/api/logout")
            .then(response => {
                this.existClient = false
                console.log("logged out")
                window.location.href = "/index.html"
            })
        },
        loginUser(){
            axios.post("/api/login", `email=${this.emailLogin}&password=${this.passwordLogin}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            
            .then(response => {
                console.log("registrado")
                console.log(response)
                
                window.location.href = "/index.html"
            })
        },
        current(){
            axios.get("/api/clients/current")
            .then(response => { 
                console.log(response)
                this.existClient = true
                this.clientInformation = response.data
                let listFavourites= this.clientInformation.favouritesProducts
                listFavourites.forEach(response => {
                    let pro = response
                    console.log(pro)
                    pro.forEach(response=>{
                        console.log(response.product)
                    })
                })
                console.log(this.listIdFavourites)
                console.log(this.clientInformation)
            })
            .catch(err => console.error(err))
        },
        loadProducts() {
            axios.get("/api/products").then(response => {
                this.products = response.data;
                if (this.listaJuegos == null || this.listaJuegos.length != this.products.length ) {

                    this.products.forEach(product => {
                        product.carrito = false;
                    })
                    this.filterProducts = this.products
                } else  {
                    console.log(this.listaJuegos.length)
                    this.products = this.listaJuegos;
                    this.filterProducts = this.products
                }

                console.log(this.filterProducts);

            })

                .catch(error => console.log(error));

        },
        addToCart(addGame) {
            console.log(addGame);
            //let producto = this.listaJuegos.filter(item => item.id === addGame.id)
            index = this.filterProducts.findIndex(item => item.id === addGame.id);
            let filtrado = this.filterProducts;
            console.log("added")
            filtrado[index].cantidad = 1;
            filtrado[index].carrito = true
            //console.log(this.listaJuegos);

            localStorage.setItem('productos', JSON.stringify(filtrado));



        },

        deleteToCart(deleteGame) {
            index = this.filterProducts.findIndex(item => item.id === deleteGame.id);
            let filtrado = this.filterProducts;
            console.log("deleted")
            filtrado[index].carrito = false;

            localStorage.setItem('productos', JSON.stringify(filtrado));

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

            console.log(this.listaJuegos);
            this.listaJuegos = this.listaJuegos.filter(game => game.id !== deleteGame.id);
            console.log(this.listaJuegos);
            localStorage.setItem('seleccion', JSON.stringify(this.listaJuegos));
        },

        // Get data
        async loadCategories() {

            await axios.get("/api/products/categories").then(response => {

                this.categories = response.data;

            }).then(response => this.loadProducts()).catch(error => console.log(error));

        },
        select(option){
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




    },



}).mount('#app')