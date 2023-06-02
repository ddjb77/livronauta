window.onload = function form() {
    let modal = document.querySelector("#modal");
    modal.style.display = "block"
}

function perfil() {
    var genero = document.querySelector('#genero').value;

    if (genero == 'trono de vidro') {
        modal.style.display="none";
        document.querySelector("#texto").innerHTML = "Você está lendo Trono de Vidro" + '<br>' + "Gênero: Fantasia"  + '<br>' + "Páginas lidas: 370"  + '<br>' + "Páginas restantes: 120";
        document.querySelector("#imagem-banner").style.background = "url('img/icone-livros-fantasia.png')";
        document.querySelector("#imagem-banner").style.backgroundRepeat = "no-repeat";
        document.querySelector("#imagem-banner").style.backgroundPosition = "left";

    } else if (genero == 'teto para dois') {
        modal.style.display="none";
        document.querySelector("#texto").innerHTML = "Você está lendo Teto para Dois" + '<br>' + "Gênero: Romance"  + '<br>' + "Páginas lidas: 100"  + '<br>' + "Páginas restantes: 250";
        document.querySelector("#imagem-banner").style.background = "url('img/icone-livros-romance.png')";
        document.querySelector("#imagem-banner").style.backgroundRepeat = "no-repeat";
        document.querySelector("#imagem-banner").style.backgroundPosition = "left";
    } else {
        document.querySelector("#texto").innerHTML = genero
        document.querySelector("#imagem-banner").style.background = "url('img/icone-lidos.png')";
        document.querySelector("#imagem-banner").style.backgroundRepeat = "no-repeat";
        document.querySelector("#imagem-banner").style.backgroundPosition = "center";
    }
}

