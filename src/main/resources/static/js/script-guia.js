window.onload = () => {
    var imagem = document.querySelector('.mudar-img');
    var texto = document.querySelector('.mudar-txt');
    var modal = document.querySelector('#modal-guia');
       

    setTimeout(() => {
        imagem.setAttribute('src', '/img/astronauta-binoculo.png');
        imagem.style.width="300px";
        texto.innerHTML = "Aqui você poderá encontrar opiniões daquele livro que você tanto procura";
        setTimeout(() => {
            imagem.setAttribute('src', '/img/astronauta-avaliacao-usuario.png');
            imagem.style.width="300px";
            texto.innerHTML = "Veja quais foram as avaliações dos usuários";

            setTimeout(() => {
                imagem.setAttribute('src', '/img/astronauta-anuncio.png');
                imagem.style.width="300px";
                texto.innerHTML = "Anuncie seus livros que estão à venda e encontre aqueles que você tanto deseja";

                setTimeout(() => {
                imagem.setAttribute('src', '/img/icone-cadastrar-lista.png');
                imagem.style.width="300px";
                texto.innerHTML = "Aproveite o Guia das Galáxias !!!";

                   setTimeout(() => {
                       modal.remove();
                    }, 5000 );
                }, 4000 );
            }, 4000 );
        }, 4000 );
    }, 4000 )
}


function pular() {
    var modal = document.querySelector('#modal-guia');
    modal.remove();
}





    
    
    
    
    

