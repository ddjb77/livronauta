function form() {
    let modal = document.querySelector("#modal");
    modal.style.display = "block"

     document.querySelector(".fechar").addEventListener('click', () =>{

        modal.style.display="none";
    })
}



function formEdit() {
    let modal = document.querySelector("#modal-editar");
    modal.style.display = "block"

     document.querySelector(".fechar").addEventListener('click', () =>{

        modal.style.display="none";
    })
}

function cancelar() {
    let modal = document.querySelector("#modal");
    modal.style.display = "block"

     document.querySelector(".cancelar").addEventListener('click', () =>{

        modal.style.display="none";
    })
}

function sucesso() {
    let sucesso = document.querySelector('#modal-sucesso');
    sucesso.style.display = "block"

    document.querySelector(".ok").addEventListener('click', () =>{

        sucesso.style.display="none";
        modal.style.display = "none";

    })
}


function email() {
    var img = document.querySelector(".imagem-login");
    img.setAttribute('src','img/botao-entrar.png');
    img.style.backgroundAttachment  = "fixed"
  }


function senha() {
  var img = document.querySelector(".imagem-login");
  img.style.backgroundAttachment = "fixed"
  //var inputSenha = document.querySelector("input[type='password']").value;

    img.setAttribute('src','img/botao-senha.png');

}

function entrar() {
    var img = document.querySelector(".imagem-login");
    img.setAttribute('src','img/botao-paz.png');
    img.style.backgroundAttachment  = "fixed"
}

