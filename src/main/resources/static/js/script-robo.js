var frases = [
    'Hmm, hoje o dia está ótimo para ler',
    'E ai? Já está apaixonado por algum personagem literário?',
    'Curiosidade: Sem livros, dificilmente se aprende a gostar de ler, então vamos ler!!',
    'Sabia que você não saberá mais nada sobre seu personagem favorito se você não continuar sua leitura?',
    'LEMBRE-SE: Ler um livro é nunca estar só.',
    'Não se esqueça que tem vários outros livros esperando para serem lidos.',
    'LEMBRE-SE: A falta de leitura cria uma geração que acredita em fake news',
    'Que tal viajar para outros lugares sem gastar nada? Leia para isso acontecer',
    'Continue lendo.. quem sabe não tem algum PLOT TWIST?',
    'Já leu quantas páginas hoje?',
    'LEMBRETE DIÁRIO: Ler prejudica gravemente a sua ignorância.'
    ]


   window.onload = function novaFrase() {
    document.querySelector("#frases").innerHTML="Olá! Como você está? Conte me como está sua leitura 📚" 


    setInterval(() => { 
        var aleatorio = Math.floor(Math.random()*(frases.length));
            document.querySelector("#frases").innerHTML=frases[aleatorio]; 
    } , 15000)

    }

    document.getElementById("fechar").addEventListener("click", fecharRobo);

    function fecharRobo() {
        document.querySelector('#robo').style.display = "none"

        setInterval(() => { 
            document.querySelector('#robo').style.display = "block"
        } , 15000)
    

    }

    