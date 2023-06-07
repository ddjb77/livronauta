window.onload = () => {
    var imagem = document.querySelector('.mudar-img');
    var texto = document.querySelector('.mudar-txt');
    var modal = document.querySelector('#modal-guia');
    var conquista = document.querySelector('#modal-conquista');
    conquista.style.display="none";   

    setTimeout(() => {
        imagem.setAttribute('src', 'img/astronauta-cometa.png');
        imagem.style.width="300px";
        texto.innerHTML = "Conquiste cometas cumpridos as missões do Livronauta";
        setTimeout(() => {
            imagem.setAttribute('src', 'img/astronauta-sacola-cometa.png');
            imagem.style.width="300px";
            texto.innerHTML = "Quanto mais cometas você conquistar, melhor";

            setTimeout(() => {
                imagem.setAttribute('src', 'img/cupom-desconto.png');
                
                texto.innerHTML = "A cada 5 cometas conquistados, ganhe um cupom";

                setTimeout(() => {
                imagem.setAttribute('src', 'img/icone-cadastrar-lista.png');
                imagem.style.width="300px";
                texto.innerHTML = "Aproveite !!";

                   setTimeout(() => {
                       modal.remove();
                    }, 5000 );
                }, 4000 );
            }, 4000 );
        }, 4000 );
    }, 4000 )
    
        conquista.style.display="block";

}


function pular() {
    var modal = document.querySelector('#modal-guia');
    modal.remove();
}



function cometa(){
	var conquista = document.querySelector('#modal-conquista');
	conquista.remove();
}