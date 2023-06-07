
  const slidePage = document.querySelector(".slide-page");
  const nextBtnFirst = document.querySelector(".firstNext");
  const prevBtnSec = document.querySelector(".prev-1");
  const nextBtnSec = document.querySelector(".next-1");
  const prevBtnThird = document.querySelector(".prev-2");
  const nextBtnThird = document.querySelector(".next-2");
  const prevBtnFourth = document.querySelector(".prev-3");
  const submitBtn = document.querySelector(".submit");
  const progressText = [...document.querySelectorAll(".step p")];
  const progressCheck = [...document.querySelectorAll(".step .check")];
  const bullet = [...document.querySelectorAll(".step .bullet")];
  let max = 4;
  let current = 1;
 
 

  nextBtnFirst.addEventListener("click", function(){
      nome = document.querySelector('#nome').value
      tel = document.querySelector('#telefone').value
      cpf = document.querySelector('#cpf').value

      
    
      if (nome === null || nome === '' && tel === null || tel === '' && cpf === null || cpf === '') {
        alert('Preencha os dados')
      }  else {
        slidePage.style.marginLeft = "-25%";
        bullet[current - 1].classList.add("active");
        progressCheck[current - 1].classList.add("active");
        progressText[current - 1].classList.add("active");
        current += 1;

      }
  });

  nextBtnSec.addEventListener("click", function(){
    slidePage.style.marginLeft = "-50%";
    bullet[current - 1].classList.add("active");
    progressCheck[current - 1].classList.add("active");
    progressText[current - 1].classList.add("active");
    current += 1;
  });
  nextBtnThird.addEventListener("click", function(){
    slidePage.style.marginLeft = "-75%";
    bullet[current - 1].classList.add("active");
    progressCheck[current - 1].classList.add("active");
    progressText[current - 1].classList.add("active");
    current += 1;
  });
  submitBtn.addEventListener("click", function(){
    bullet[current - 1].classList.add("active");
    progressCheck[current - 1].classList.add("active");
    progressText[current - 1].classList.add("active");
    current += 1;
  });

  prevBtnSec.addEventListener("click", function(){
    slidePage.style.marginLeft = "0%";
    bullet[current - 2].classList.remove("active");
    progressCheck[current - 2].classList.remove("active");
    progressText[current - 2].classList.remove("active");
    current -= 1;
  });
  prevBtnThird.addEventListener("click", function(){
    slidePage.style.marginLeft = "-25%";
    bullet[current - 2].classList.remove("active");
    progressCheck[current - 2].classList.remove("active");
    progressText[current - 2].classList.remove("active");
    current -= 1;
  });
  prevBtnFourth.addEventListener("click", function(){
    slidePage.style.marginLeft = "-50%";
    bullet[current - 2].classList.remove("active");
    progressCheck[current - 2].classList.remove("active");
    progressText[current - 2].classList.remove("active");
    current -= 1;
  });




function bemvindoInicial() {
  document.querySelector('#modal-user').style.display = "block";
  document.querySelector('#modal').style.display = "none";


window.onload = function bemvindo() {
  document.querySelector('#modal-user').style.display = "block";
  document.querySelector('#modal').style.display = "none";
}


  setTimeout(() => { 
    document.querySelector('#modal-user').remove();
    document.querySelector('#modal').style.display = "block";
  }, 10000);
}



function leituraAtual() {
  var check = document.getElementById('checar');
  var leituraAtual = document.querySelector('#livro-atual');
  var querLer = document.querySelector('#pergunta-desbloqueio');

  if (check.checked == true) {
    leituraAtual.style.display = "none"
    querLer.style.display = "block" 
  } else {
    querLer.style.display = "none" 
    leituraAtual.style.display = "block"
  }
}


function fazerQuiz() {
  var checkar = document.getElementById('checarLivro');
  var btnQuiz = document.querySelector('#fzrQuiz');

  if (checkar.checked == false) {
    btnQuiz.style.display = "none"
  } else {
    btnQuiz.style.display = "block"
  }
}



function fechar() {
  document.querySelector("#modal").remove();
  
}


function fecharUser() {
  document.querySelector("#modal-user").remove();
}

function cometa() {
  document.querySelector("#modal-conquista").remove();
}




