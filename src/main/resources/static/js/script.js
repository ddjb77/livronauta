function senha() {
  var img = document.querySelector(".imagem-login");
  img.style.backgroundAttachment = "fixed";
  inputSenha = document.querySelector("input[type='password']").value;

    img.setAttribute('src','img/botao-senha.png');

}



function email() {
    var img = document.querySelector(".imagem-login");
    img.setAttribute('src','img/botao-entrar.png');
    img.style.backgroundAttachment  = "fixed"
  }




function entrar() {
    var img = document.querySelector(".imagem-login");
    img.setAttribute('src','img/botao-paz.png');
    img.style.backgroundAttachment  = "fixed"
}

function form() {
    let modal = document.querySelector("#modal");
    modal.style.display = "block"

     document.querySelector(".fechar").addEventListener('click', () =>{

        modal.style.display="none";
    })
}

//calcular % do livro lido //


const porcentagemSpan = document.getElementById('porcentagem');
const numeroPaginas = parseInt(document.getElementById('numeroPaginas').textContent);
const paginasTotais = parseInt(document.getElementById('paginasTotais').textContent);
const progressBar = document.getElementById('progresso');
const btnQuiz = document.getElementById('btnQuiz');
// Calcula a porcentagem lida
const porcentagemLida = (numeroPaginas / paginasTotais) * 100;

// Atualiza o valor da porcentagem na página
porcentagemSpan.textContent = porcentagemLida.toFixed(2);
progressBar.value = porcentagemLida;


//habilitar botao quiz//
if (porcentagemLida === 100) {
  btnQuiz.style.backgroundColor="#00a8ff";
  btnQuiz.style.color="#ffffff";
  btnQuiz.style.border= "none";
  btnQuiz.style.padding="1%";
  btnQuiz.style.borderRadius="5px";
  btnQuiz.style.cursor="pointer";
  btnQuiz.style.fontSize="14px";
  
  
  //desabilitar botao quiz
} else {
  btnQuiz.disabled="true";
  btnQuiz.style.opacity="0.5";
  btnQuiz.style.backgroundColor="#00a8ff";
  btnQuiz.style.color="#ffffff";
  btnQuiz.style.border= "none";
  btnQuiz.style.padding="1%";
  btnQuiz.style.borderRadius="5px";
  btnQuiz.style.cursor="none";
  btnQuiz.style.fontSize="14px";
  btnQuiz.style.disabled="true";
  
  
}

//desabilitar função antes da barra chegar a 100
btnQuiz.addEventListener('click', function(event) {
  if (porcentagemLida !== 100) {
    event.preventDefault();
    event.stopPropagation();
  }
});



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




