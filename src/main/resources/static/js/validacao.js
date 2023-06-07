
function verificar() {

     if (document.querySelector('.notificacao')) {
        document.querySelector('.notificacao').remove();
     }  

	
    const inputNome = document.querySelector('#nome');
    const inputEmail = document.querySelector('#email');
    const inputSenha = document.querySelector('#senha');
    const inputSenhaC = document.querySelector('#confirmaSenha');
    const botao = document.querySelector('#btn-cadastro')
    const inputUsuario = document.querySelector('#usuario');
    // const mensagem = document.querySelector('#mensagem');
    
    var nome = inputNome.value;
    var email = inputEmail.value;
    var senha = inputSenha.value;
    var senhaC = inputSenhaC.value;
    var usuario = inputUsuario.value;
   
    var notificacao = document.createElement('div');
    notificacao.classList.add('notificacao');
    notificacao.style.padding= "5px";
    notificacao.style.textAlign = "center";
    notificacao.style.color ="rgb(224, 197, 138)";
    


//validar nome //

    if (nome == "" || nome == "null") {
       
       notificacao.innerHTML += "O campo nome não pode estar vazio!<br>";
       botao.parentNode.insertBefore(notificacao, botao);
       inputNome.style.border = "2px solid red";
    } else if  (nome.length < 3) {
        notificacao.innerHTML += "O campo nome deve conter no mínimo 3 caracteres<br>";
        botao.parentNode.insertBefore(notificacao, botao);
    }  else {
        notificacao.innerHTML = ""
        inputNome.style.border = "2px solid green";
        
    }

//validar usuario //

    if (usuario == "" || usuario == "null") {
       
       notificacao.innerHTML += "O campo usuario não pode estar vazio!<br>";
       botao.parentNode.insertBefore(notificacao, botao);
       inputNome.style.border = "2px solid red";
    } else if  (nome.length < 3) {
        notificacao.innerHTML += "O campo usuario deve conter no mínimo 3 caracteres<br>";
        botao.parentNode.insertBefore(notificacao, botao);
    }  else {
        notificacao.innerHTML = ""
        inputNome.style.border = "2px solid green";
        
    }



//validar email//
    if (email == "" || email == "null") {
        notificacao.innerHTML += "O campo email não pode estar vazio!<br>";
        botao.parentNode.insertBefore(notificacao, botao)
        inputEmail.style.border = "2px solid red";
     } else if(email.indexOf('@')==-1 )  {
        notificacao.innerHTML += "O email deve conter '@'<br>";
        botao.parentNode.insertBefore(notificacao, botao)
        inputEmail.style.border = "2px solid red";
     } else if (email.indexOf('.')==-1) {
        notificacao.innerHTML += "O email deve conter '.'<br>";
        botao.parentNode.insertBefore(notificacao, botao)
        inputEmail.style.border = "2px solid red";
    }
    else {
        notificacao.innerHTML = ""
        inputNome.style.border = "2px solid green";
        
    }

//validar senha//
    if (senha == "" || senha == "null") {
        notificacao.innerHTML += "O campo senha não pode estar vazio!<br>";
        botao.parentNode.insertBefore(notificacao, botao);
        inputSenha.style.border = "2px solid red";

    } 

    else if  (senha.length < 6) {
        notificacao.innerHTML += "O campo senha deve conter no mínimo 6 caracteres<br>";
        botao.parentNode.insertBefore(notificacao, botao);
    }

    else  if (senha != senhaC) {
        notificacao.innerHTML += "Senhas não conferem<br>";
        botao.parentNode.insertBefore(notificacao, botao);
        inputSenhaC.style.border = "2px solid red";
    }
    else {
        notificacao.innerHTML = "";
        inputSenha.style.border = "2px solid green";
        inputSenhaC.style.border = "2px solid green";
    }
	
	
}






