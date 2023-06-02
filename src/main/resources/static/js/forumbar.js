
let select = document.getElementById("select");
let list = document.getElementById("list");
let selectText = document.getElementById("selectText");
let opcoes = document.getElementsByClassName("opcoes");
let categorias = document.getElementsByClassName("categorias");
let inputField = document.getElementById("inputField");
let selectCategorias = document.getElementById("ct");
let selectRecomendacoes = document.getElementById("cr");
let selectAvaliacoes = document.getElementById("ca");
let selectOpinioes = document.getElementById("co");
let tc = document.getElementById("tc");
let rec = document.getElementById("rec");
let aval = document.getElementById("aval");
let opn= document.getElementById("opn");

select.onclick = function () {
    list.classList.toggle("open");
}


tc.addEventListener("click", function(){
    
    let selectCategorias = document.getElementById("ct");

    if(selectCategorias.style.display, selectRecomendacoes.style.display = "none"){
        selectCategorias.style.display = "block";
        selectRecomendacoes.style.display = "block";
        selectAvaliacoes.style.display = "block";
        selectOpinioes.style.display = "block";
    } else {
        selectCategorias.style.display = "none";
    }

});

 rec.addEventListener("click", function(){
    
    let selectRecomendacoes = document.getElementById("cr");

    if(selectRecomendacoes.style.display = "none"){
        selectRecomendacoes.style.display = "block";
        selectCategorias.style.display = "none";
        selectAvaliacoes.style.display = "none";
        selectOpinioes.style.display = "none"
    } else {
        selectRecomendacoes.style.display = "none";
    }
 });

 aval.addEventListener("click", function(){
    
    let selectAvaliacoes = document.getElementById("ca");

    if(selectAvaliacoes.style.display = "none"){
        selectAvaliacoes.style.display = "block";
        selectCategorias.style.display = "none";
        selectRecomendacoes.style.display = "none";
        selectOpinioes.style.display = "none";
    } else {
        selectAvaliacoes.style.display = "none";
    }
 });

 opn.addEventListener("click", function(){
    
    let selectOpinioes = document.getElementById("co");

    if(selectOpinioes.style.display = "none"){
        selectOpinioes.style.display = "block";
        selectAvaliacoes.style.display = "none";
        selectCategorias.style.display = "none";
        selectRecomendacoes.style.display = "none";
    } else {
        selectAvaliacoes.style.display = "none";
    }
 });

for (opcao of opcoes) {
    opcao.onclick = function () {
        selectText.innerHTML = this.innerHTML;
        inputField.placeholder = "Pesquisar em " + selectText.innerHTML;
    
    }
}


let livros = {
    data: [

        {
            nomeLivro: "Morte no Nilo",
            autor: "Agatha Christie",
            avaliacao: "5",
            categoria: "policial",
            opiniao: "excelente",
            image: "./img/capas/mortenonilo.jpg"
        },

        {
            nomeLivro: "Harry Potter e a Pedra Filosofal",
            autor: "J.K Rowling",
            avaliacao: "5",
            categoria: "Aventura",
            opiniao: "ótimo",
            image: "./img/capas/hp-filosofal.jpg"
        },

        {
            nomeLivro: "O livro do Desassossego",
            autor: "Fernando Pessoa",
            avaliacao: "4",
            categoria: "Poesia",
            opiniao: "muito bom",
            image: "./img/capas/desassossego.jpg"
        },
    ]
};

for (let i of livros.data) {

    let card = document.createElement("div");

    card.classList.add("card", i.categoria, "hide");

    let imgContainer = document.createElement("div");
    imgContainer.classList.add("image-container");

    let image = document.createElement("img");
    image.setAttribute("src", i.image);

    imgContainer.appendChild(image);
    card.appendChild(imgContainer);

    let container = document.createElement("div");
    container.classList.add("container");


    //nome livro//
    let name = document.createElement("div");
    name.classList.add("nome-livro");
    name.innerText = i.nomeLivro.toUpperCase();
    container.appendChild(name);

    //autor do livro//
    let author = document.createElement("div");
    author.classList.add("autor-livro");
    author.innerText = i.autor.toUpperCase();
    container.appendChild(author);
    
    //categoria//

    let category = document.createElement("small");
    category.classList.add("cat-livro");
    category.innerText = i.categoria.toUpperCase();
    container.appendChild(category);

    //avaliacao
    let avaliation = document.createElement("div");
    avaliation.classList.add("aval-livro");
    avaliation.innerText = i.avaliacao.toUpperCase();
    container.appendChild(avaliation);

    //avaliacao
    let opinion = document.createElement("div");
    opinion.classList.add("opn-livro");
    opinion.innerText = i.opiniao.toUpperCase();
    container.appendChild(opinion);

    card.appendChild(container);
    document.getElementById("livros").appendChild(card);
}

    function filterCat(value){

        let lis = document.querySelectorAll(".opcoes");

        lis.forEach((button) =>{
            if (value.toUpperCase() == button.innerText.toUpperCase()){
                button.classList.add("active");
            }
            else{
                button.classList.remove("active");
            }
        })

    };


    //selecionar todas categorias //

    // let elements = document.querySelectorAll(".card");

    // elements.forEach((element)=>
    // {
    //     if (value = "all")
    //     {
    //         element.classList.remove("hide");
    //     }else
        
    //     {
    //         if (element.classList.contains(value)){
    //             element.classList.remove("hide");
    //         }
    //     else {
    //         element.classList.add("hide");
    //     }
    //     }
    // });
    
    //buscar e mostrar por categoria//

    document.getElementById("search").addEventListener("click", () => {
        let searchInput = document.getElementById("inputField").value;
        let livro = document.querySelector("#livro");
        for (opcao of opcoes) {
            opcao.onclick = function () {
                if (opcoes == livro ) {
                    searchInput.value == ('.nome-livro');
                    // let elements = document.querySelectorAll(".nome-livro");


        
                }
            
            }
        }

        

        let elements = document.querySelectorAll(".autor-livro");
        // let elementsAutor = document.querySelectorAll(".autor-livro");
        


        
        let cards = document.querySelectorAll(".card");



        elements.forEach((element,index) => {

            //veriricar se texto da busca contem no resultado//
    
            if
            
            (element.innerText.includes(searchInput.toUpperCase()))
            
            
            
            
            {
                cards[index].classList.remove("hide");
            

            }
    
            else {
                cards[index].classList.add("hide");
            }
        });

        console.log(searchInput);
    });
    
        
    

   











