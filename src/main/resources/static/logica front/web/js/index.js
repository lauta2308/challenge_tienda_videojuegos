const { createApp } = Vue

createApp({
    data() {
        return {
            datas: [],

            filtrado: [],
            checks: [],
            listaCheck: [],
            numberPage: 1,
            listaJuegos: [],
            categories: [],
            products: [],
            search: "",
            filterProducts: [],
            local: "Agregar al carrito"

        }
    },
    created() {
        //this.login();
        //console.log(this.datas);

        this.loadCategories();
        this.loadProducts();
    },
    mounted() {
        
    },
    computed: {

        filterSearch() {

            console.log(this.filterProducts);

            if (this.search.length === 0 && this.listaCheck.length === 0) {

                this.filterProducts = this.products;

            } else if (this.search.length > 0 && this.listaCheck.length === 0) {

                this.filterProducts = this.products.filter(product => product.name.toLowerCase().includes(this.search.toLowerCase()))
            } else if (this.search.length === 0 && this.listaCheck.length > 0) {

                this.filterProducts = [];

                for (let i = 0; i < this.listaCheck.length; i++) {
                    
                    this.products.forEach(product => {
                        product.category.forEach(category =>{
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

                        }




                    )
                }
            }



        },

    },
    methods: {
        async loadCategories() {

            await axios.get("/api/products/categories").then(response => {

                this.categories = response.data;

                console.log(this.categories);

            })

            .catch(error => console.log(error));

        },

        loadProducts() {

            this.listaJuegos = JSON.parse(localStorage.getItem("carrito"));

            console.log(this.listaJuegos);

            axios.get("/api/products").then(response => {

                this.products = response.data;

                this.filterProducts = this.products;

                console.log(this.products);
                this.serchLocal(this.products)

            })

            .catch(error => console.log(error));

        },
        
        addToCart(addGame) {

            console.log(addGame);
            
            //let producto = this.listaJuegos.filter(item => item.id === addGame.id)
            
            if (!this.listaJuegos.includes(addGame)) {    

                addGame.cantidad = 1;

                this.listaJuegos.push(addGame);

                console.log(this.listaJuegos);

                localStorage.setItem('carrito', JSON.stringify(this.listaJuegos));
            }else{
                this.listaJuegos = []
            }
        },


        

        upyear() {
            
        },

        downyear() {
            
        },

        upNumber() {
            this.filterProducts.sort((a, b) => {
                if (a.price > b.price) { return 1 }
                if (a.price < b.price) { return -1 }
                return 0
            })
        },
        downNumber() {
            this.filterProducts.sort((a, b) => {
                if (a.price < b.price) { return 1 }
                if (a.price > b.price) { return -1 }
                return 0
            })
        },

        /*  filterCheck(array) {
            console.log(this.listaCheck);
            console.log(this.filtrado);
            if (this.listaCheck.length == 0) {
                this.filtrado = array;
            } else {
                this.filtrado = array.filter(data => this.listaCheck.includes(data.genre))
            }
            return this.filtrado;
        },
        search() {
            console.log(this.texto)
            return this.filtrado = array.filter(info => info.title.toLowerCase().includes(this.texto.toLowerCase().trim()));
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
                    console.log(this.datas)
                    check.forEach(genre => {
                        if (!this.checks.includes(genre)) {
                            this.checks.push(genre)
                        }
                    })
                    this.serchLocal(this.datas)
                })
                .catch(err => console.log(err));

        },*/

        serchLocal(data) {
            let txt = localStorage.getItem("buscar");
            if (txt) {
                this.search = txt
                this.filtrado = data.filter(info => info.name.toLowerCase().includes(this.search.toLowerCase().trim()));
                localStorage.removeItem("buscar")
                console.log(txt)
                console.log("funciona")
            }
            
        },
        
        
/*
        upPage(){
            this.numberPage = this.numberPage + 1;
            this.login()
        },


*/





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