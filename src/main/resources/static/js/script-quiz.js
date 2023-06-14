/* dados das perguntas do quiz*/
const quizData = [
    {
        pergunta: "Quantas eram as estátuas que foram quebradas?",
        a: "10",
        b: "9",
        c: "5",
        d: "6",
        correct: "a",
    },


    {
        pergunta: "Qual destes não é um personagem do livro?",
        a: "Philip Lombard",
        b: "Juiz Wargrave",
        c: "Carlota Adams",
        d: "Dr. Armstrong",
        correct: "c",
    },

    {
        pergunta: "O que foi encontrado no quarto de todos os hóspedes?",
        a: "Um poema",
        b: "Uma Tesoura",
        c: "Pôster do dono da casa",
        d: "Um quadro de chaves",
        correct: "a",
    },

    {
        pergunta: "Qual era o sobrenome do casal de mordomos da casa?",
        a: "Sr e Sra Smith",
        b: "Sr e Sra Golding",
        c: "Sr e Sra Rogers",
        d: "Sr e Sra Hallt",
        correct: "c",
    },

    {
        pergunta: "Como o autor das mortes é descoberto?",
        a: "Ele se entrega",
        b: "Ninguém o descobre",
        c: "A polícia cerca a ilha",
        d: "Um dos personagens o pega em flagrante ",
        correct: "b",
    },
]
/* recebe os dados das perguntas e das alternativas */
const quiz = document.getElementById('quiz')
const respostas = document.querySelectorAll('.resposta')
const pergunta = document.getElementById('pergunta')
const texto_a = document.getElementById('texto_a')
const texto_b = document.getElementById('texto_b')
const texto_c = document.getElementById('texto_c')
const texto_d = document.getElementById('texto_d')

const enviar = document.getElementById('enviar')

/* inicializa os contadores */
let currentQuiz = 0
let score = 0

loadQuiz()


function loadQuiz() {
    deselectAnswers()

    const currentQuizData = quizData[currentQuiz]

    pergunta.innerText = currentQuizData.pergunta
    texto_a.innerText = currentQuizData.a
    texto_b.innerText = currentQuizData.b
    texto_c.innerText = currentQuizData.c
    texto_d.innerText = currentQuizData.d
}


function voltarAoPerfil() {
   
   window.location.href = "/user/profile";
}


function deselectAnswers() {
    respostas.forEach(resposta => resposta.checked = false)
}

function getSelected() {
    let answer
    respostas.forEach(resposta => {
        if (
            resposta.checked) {
            answer = resposta.id
        }
    })
    return answer
}

enviar.addEventListener('click', () => {
    const answer = getSelected()
    if (answer) {
        if (answer === quizData[currentQuiz].correct) {
            score++
        }

        currentQuiz++
        if (currentQuiz < quizData.length) {
            loadQuiz()
        } else  {
            
            if(score == quizData.length) {
                quiz.style.margin = "30px"
                quiz.innerHTML = `<h2> Parabéns!! Você respondeu ${score}/${quizData.length} perguntas corretamente</h2>`
                var img = document.createElement('div');
                img.style.background = "url('img/quiz/quiz-acerto-total.png')"
                img.style.backgroundRepeat = "no-repeat";
                img.style.backgroundPosition = "center";
                img.style.height = "300px"
                quiz.appendChild(img);
                let btn = document.createElement('button');
                btn.style.border = "none";
                btn.style.borderRadius = "5%"
                btn.style.backgroundColor = "#0a0233"
                btn.style.color = "#fff"
                btn.style.padding = "15px";
                btn.style.fontSize = "1.2rem";
                btn.style.margin = "20px";
                btn.style.cursor = "pointer";
                btn.innerHTML = "Voltar ao livronauta"
                quiz.appendChild(btn);
                btn.addEventListener('click', voltarAoPerfil);

            } else if 
                (score == quizData.length - 1) {
                    quiz.style.margin = "30px"
                    quiz.innerHTML = `<h2> Mandou muito bem!! <br> Você respondeu ${score}/${quizData.length} perguntas corretamente</h2>`
                    var img = document.createElement('div');
                    img.style.background = "url('img/quiz/quiz-3-acertos.png')"
                    img.style.backgroundRepeat = "no-repeat";
                    img.style.backgroundPosition = "center";
                    img.style.height = "300px"
                    quiz.appendChild(img);
                    let btn = document.createElement('button');
                    btn.style.border = "none";
                    btn.style.borderRadius = "5%"
                    btn.style.backgroundColor = "#0a0233"
                    btn.style.color = "#fff"
                    btn.style.padding = "15px";
                    btn.style.fontSize = "1.2rem";
                    btn.style.margin = "20px";
                    btn.style.cursor = "pointer";
                    btn.innerHTML = "Voltar ao livronauta"
                    quiz.appendChild(btn);
                    btn.addEventListener('click', voltarAoPerfil);

                } else if 
                (score == quizData.length - 2 ) {
                    quiz.style.margin = "30px"
                    quiz.innerHTML = `<h2> Parece que você esqueceu algumas coisas...<br>  Você respondeu ${score}/${quizData.length} perguntas corretamente</h2>`
                    var img = document.createElement('div');
                    img.style.background = "url('img/quiz/quiz-poucos-acertos.png')"
                    img.style.backgroundRepeat = "no-repeat";
                    img.style.backgroundPosition = "center";
                    img.style.height = "300px"
                    quiz.appendChild(img);
                    let btn = document.createElement('button');
                    btn.style.border = "none";
                    btn.style.borderRadius = "5%"
                    btn.style.backgroundColor = "#0a0233"
                    btn.style.color = "#fff"
                    btn.style.padding = "15px";
                    btn.style.fontSize = "1.2rem";
                    btn.style.margin = "20px";
                    btn.style.cursor = "pointer";
                    btn.innerHTML = "Voltar ao livronauta"
                    quiz.appendChild(btn);
                     btn.addEventListener('click', voltarAoPerfil);

                } else if 
                    (score == quizData.length - 3 ) {
                        quiz.style.margin = "30px"
                        quiz.innerHTML = `<h2> Parece que você esqueceu muitas coisas...<br>  Você respondeu ${score}/${quizData.length} perguntas corretamente</h2>`
                        var img = document.createElement('div');
                        img.style.background = "url('img/quiz/quiz-poucos-acertos.png')"
                        img.style.backgroundRepeat = "no-repeat";
                        img.style.backgroundPosition = "center";
                        img.style.height = "300px"
                        quiz.appendChild(img);
                        let btn = document.createElement('button');
                        btn.style.border = "none";
                        btn.style.borderRadius = "5%"
                        btn.style.backgroundColor = "#0a0233"
                        btn.style.color = "#fff"
                        btn.style.padding = "15px";
                        btn.style.fontSize = "1.2rem";
                        btn.style.margin = "20px";
                        btn.style.cursor = "pointer";
                        btn.innerHTML = "Voltar ao livronauta"
                        quiz.appendChild(btn);
                        btn.addEventListener('click', voltarAoPerfil);

                }  else if 
                   (score == quizData.length - 4 ) {
                    quiz.style.margin = "30px"
                    quiz.innerHTML = `<h2> Parece que você esqueceu muitas coisas...<br>  Você respondeu ${score}/${quizData.length} perguntas corretamente</h2>`
                    var img = document.createElement('div');
                    img.style.background = "url('img/quiz/quiz-poucos-acertos.png')"
                    img.style.backgroundRepeat = "no-repeat";
                    img.style.backgroundPosition = "center";
                    img.style.height = "300px"
                    quiz.appendChild(img);
                    let btn = document.createElement('button');
                    btn.style.border = "none";
                    btn.style.borderRadius = "5%"
                    btn.style.backgroundColor = "#0a0233"
                    btn.style.color = "#fff"
                    btn.style.padding = "15px";
                    btn.style.fontSize = "1.2rem";
                    btn.style.margin = "20px";
                    btn.style.cursor = "pointer";
                    btn.innerHTML = "Voltar ao livronauta"
                    quiz.appendChild(btn);
                    btn.addEventListener('click', voltarAoPerfil);

            }
                
                
                else {
                        quiz.style.margin = "30px"
                        quiz.innerHTML = `<h2> Talvez você tem que reler o livro <br>  Você respondeu ${score}/${quizData.length} perguntas corretamente</h2>`
                        var img = document.createElement('div');
                        img.style.background = "url('img/quiz/quiz-nenhum-acerto.png')"
                        img.style.backgroundRepeat = "no-repeat";
                        img.style.backgroundPosition = "center";
                        img.style.height = "300px"
                        quiz.appendChild(img);
                        let btn = document.createElement('button');
                        btn.style.border = "none";
                        btn.style.borderRadius = "5%"
                        btn.style.backgroundColor = "#0a0233"
                        btn.style.color = "#fff"
                        btn.style.padding = "15px";
                        btn.style.fontSize = "1.2rem";
                        btn.style.margin = "20px";
                        btn.style.cursor = "pointer";
                        btn.innerHTML = "Voltar ao livronauta"
                        quiz.appendChild(btn);
                        btn.addEventListener('click', voltarAoPerfil);

                }

                
                }
            
            }			
            
        }
)



function comecar() {
    document.querySelector('#modal-user').remove();
}

function pularQuiz() {
    document.querySelector('#modal-user').remove();
    var sair = document.querySelector('#modal-sair');
    sair.style.display = "block";
}

function voltarQuiz() {
    document.querySelector('#modal-sair').remove();
    document.querySelector('#modal-user').style.display = "block";
}
