//MÉTODOS DE EXCLUSÃO//


function exibirModalConfirmacao(button) {
    var id = button.querySelector('input[name="id"]').value;
    var nomeUsuario = button.parentNode.parentNode.querySelector('td:first-child').innerText;
    document.getElementById('nomeUsuario').innerText = nomeUsuario;
    console.log("ID a ser excluído:", id);
    var modal = document.getElementById("modalConfirmacao");
    modal.style.display = "block";
    
    var confirmarExclusao = document.getElementById("confirmarExclusao");
    confirmarExclusao.onclick = function() {
        excluirUsuario(id);
    };
}

var closeModal = document.getElementsByClassName("close")[0];
closeModal.onclick = function() {
    var modal = document.getElementById("modalConfirmacao");
    modal.style.display = "none";
}

function excluirUsuario(id) {
    console.log("Excluindo usuário com ID:", id);
    $.ajax({
        url: "/excluir/user/" + id,
        type: "DELETE",
        success: function(response) {
            // Lógica para manipular a resposta de sucesso
            console.log(response);
            var modal = document.getElementById("modalConfirmacao");
            modal.style.display = "none";
            location.reload("/admin");
        },
        error: function(xhr, status, error) {
            // Lógica para manipular o erro de requisição
            console.log(xhr.responseText);
            var modal = document.getElementById("modalConfirmacao");
            modal.style.display = "none";
        }
    });
    
    
}

function exibirModalConfirmacaoLivro(button) {
    var id = button.querySelector('input[name="id"]').value;
    var nomeLivroLido = button.parentNode.parentNode.querySelector('td:first-child').innerText;
    document.getElementById('nomeLivroLido').innerText = nomeLivroLido;
    console.log("ID a ser excluído:", id);
    var modal = document.getElementById("modalConfirmacao");
    modal.style.display = "block";
    
    var confirmarExclusao = document.getElementById("confirmarExclusao");
    confirmarExclusao.onclick = function() {
        excluirLivroLido(id);
    };
}

//excluir livro lido//

function excluirLivroLido(id) {
    console.log("Excluindo livro lido com ID:", id);
    $.ajax({
        url: "/excluir/livros/" + id,
        type: "DELETE",
        success: function(response) {
            // Lógica para manipular a resposta de sucesso
            console.log(response);
            var modal = document.getElementById("modalConfirmacao");
            modal.style.display = "none";
            location.reload("/livros-Lidos");
        },
        error: function(xhr, status, error) {
            // Lógica para manipular o erro de requisição
            console.log(xhr.responseText);
            var modal = document.getElementById("modalConfirmacao");
            modal.style.display = "none";
            
        }
        
    });
		location.reload("/livros-Lidos");
}


function exibirModalConfirmacaoLista(button) {
    var id = button.querySelector('input[name="id"]').value;
    var listaDesejos = button.parentNode.parentNode.querySelector('td:first-child').innerText;
    document.getElementById('listaDesejos').innerText = listaDesejos;
    console.log("ID a ser excluído:", id);
    var modal = document.getElementById("modalConfirmacao");
    modal.style.display = "block";
    
    var confirmarExclusao = document.getElementById("confirmarExclusao");
    confirmarExclusao.onclick = function() {
        excluirLista(id);
    };
}


//excluir livro lido//

function excluirLista(id) {
    console.log("Excluindo livro da lista com ID:", id);
    $.ajax({
        url: "/excluir/lista/" + id,
        type: "DELETE",
        success: function(response) {
            // Lógica para manipular a resposta de sucesso
            console.log(response);
            var modal = document.getElementById("modalConfirmacao");
            modal.style.display = "none";
            location.reload("/lista-desejos");

        },
        error: function(xhr, status, error) {
            // Lógica para manipular o erro de requisição
            console.log(xhr.responseText);
            var modal = document.getElementById("modalConfirmacao");
            modal.style.display = "none";
            
        }
    });
    
}



function exibirModalConfirmacaoEmprest(button) {
    var id = button.querySelector('input[name="id"]').value;
    var emprestimos = button.parentNode.parentNode.querySelector('td:first-child').innerText;
    document.getElementById('emprestimos').innerText = emprestimos;
    console.log("ID a ser excluído:", id);
    var modal = document.getElementById("modalConfirmacao");
    modal.style.display = "block";
    
    var confirmarExclusao = document.getElementById("confirmarExclusao");
    confirmarExclusao.onclick = function() {
        excluirEmprestado(id);
    };
}


//excluir livro lido//

function excluirEmprestado(id) {
    console.log("Excluindo empréstimo da lista com ID:", id);
    $.ajax({
        url: "/excluir/emprestados/" + id,
        type: "DELETE",
        success: function(response) {
            // Lógica para manipular a resposta de sucesso
            console.log(response);
            var modal = document.getElementById("modalConfirmacao");
            modal.style.display = "none";
            location.reload("/emprestados")
        },
        error: function(xhr, status, error) {
            // Lógica para manipular o erro de requisição
            console.log(xhr.responseText);
            var modal = document.getElementById("modalConfirmacao");
            modal.style.display = "none";
            
        }
    });
   

}





function exibirModalConfirmacaoProx(button) {
    var id = button.querySelector('input[name="id"]').value;
    var proximasLeituras = button.parentNode.parentNode.querySelector('td:first-child').innerText;
    document.getElementById('proximasLeituras').innerText = proximasLeituras;
    console.log("ID a ser excluído:", id);
    var modal = document.getElementById("modalConfirmacao");
    modal.style.display = "block";
    
    var confirmarExclusao = document.getElementById("confirmarExclusao");
    confirmarExclusao.onclick = function() {
        excluirProximas(id);
    };



//excluir proximas//

function excluirProximas(id) {
    console.log("Excluindo livro das proximas leituras", id);
    $.ajax({
        url: "/excluir/proximas-leituras/" + id,
        type: "DELETE",
        success: function(response) {
            // Lógica para manipular a resposta de sucesso
            console.log(response);
            var modal = document.getElementById("modalConfirmacao");
            modal.style.display = "none";
             location.reload("/proximas-leituras");
        },
        error: function(xhr, status, error) {
            // Lógica para manipular o erro de requisição
            console.log(xhr.responseText);
            var modal = document.getElementById("modalConfirmacao");
            modal.style.display = "none";
            
        }
    });
   

}



}


											//MÉTODOS DE EDIÇÃO//

function exibirModalEdicao(button) {
    var id = button.querySelector('input[name="id"]').value;
    var modal = document.getElementById("modal");
    modal.style.display = "block";
    var salvarEdicao = document.getElementById("salvarEdicao");
    
    
   salvarEdicao.onclick = function() {
    if (validarCampos()) {
        editarLivroLido(id, getLivroEditado());
    } else {
		var inputsInvalidos = document.querySelectorAll('input:invalid');
        var selectsInvalidos = document.querySelectorAll('select:invalid');
        
        for (var i = 0; i < inputsInvalidos.length; i++) {
            inputsInvalidos[i].style.borderColor = "#ff0000";
        }
        
        for (var s = 0; s < selectsInvalidos.length; s++) {
            selectsInvalidos[s].style.borderColor = "#ff0000";
        }
		
    }
};


function validarCampos() {
    var livroInput = document.querySelector('input[name="livro"]');
    var autorInput = document.querySelector('input[name="autor"]');
    var generoSelect = document.querySelector('select[name="genero"]');
    var anoInput = document.querySelector('input[name="ano"]');
    var avaliacaoSelect = document.querySelector('select[name="avaliacao"]');

    if (
        livroInput.value === "" ||
        autorInput.value === "" ||
        generoSelect.value === "" ||
        anoInput.value === "" ||
        avaliacaoSelect.value === ""
    ) {
        return false;
    }

    return true;
}


function getLivroEditado(){
	
	var livroEditado = {
        livro: document.querySelector('input[name="livro"]').value,
        autor: document.querySelector('input[name="autor"]').value,
        genero: document.querySelector('select[name="genero"]').value,
        ano: document.querySelector('input[name="ano"]').value,
        avaliacao: document.querySelector('select[name="avaliacao"]').value,

        
    };
    
    return livroEditado
}

function editarLivroLido(id, livroEditado) {
    console.log("Editando livro:", id);
 
    
    //RECEBE A ROTA VIA SPRINGBOOT
    $.ajax({
        url: "/editar/livros-lidos/" + id,
        type: "PUT",
        data: JSON.stringify(livroEditado),
        contentType: "application/json",
        success: function(response) {
            // Lógica para manipular a resposta de sucesso
            console.log(response);
            var modal = document.getElementById("modal");
            modal.style.display = "none";
            var modalSucesso = document.getElementById("modal-sucesso");
            modalSucesso.style.display = "block";
    
        },
        error: function(xhr, status, error) {
            // Lógica para manipular o erro de requisição
            console.log(xhr.responseText);
            var modal = document.getElementById("modal");
            modal.style.display = "none";
            
        }
        
        
    }) ;
 
}}




//EDITAR PROXIMAS LEITURAS



function exibirModalEdicaoProx(button) {
    var id = button.querySelector('input[name="id"]').value;
    var modalProx = document.getElementById("modal");
    modalProx.style.display = "block";
    var salvarEdicao = document.getElementById("salvarEdicao");
  	salvarEdicao.onclick = function() {
    if (validarProx()) {
        editarProximas(id, getProximasLeituras());
    } else {
		var inputsInvalidos = document.querySelectorAll('input:invalid');
        var selectsInvalidos = document.querySelectorAll('select:invalid');
        
        for (var i = 0; i < inputsInvalidos.length; i++) {
            inputsInvalidos[i].style.borderColor = "#ff0000";
        }
        
        for (var s = 0; s < selectsInvalidos.length; s++) {
            selectsInvalidos[s].style.borderColor = "#ff0000";
        }
		
    }
};


function validarProx() {
	var ordemInput = document.querySelector('input[name="ordem"]');
    var livroInput = document.querySelector('input[name="livro"]');
    var autorInput = document.querySelector('input[name="autor"]');
    var generoSelect = document.querySelector('select[name="genero"]');
    var numeroPagInput = document.querySelector('input[name="numeroPaginas"]');

    if (
		ordemInput.value === "" ||
        livroInput.value === "" ||
        autorInput.value === "" ||
        generoSelect.value === ""||
        numeroPagInput.value === ""

    ) {
        return false;
    }

    return true;
}


function getProximasLeituras(){
	
	var proximasLeiturasEdit = {
		ordem: document.querySelector('input[name="ordem"]').value,
        livro: document.querySelector('input[name="livro"]').value,
        autor: document.querySelector('input[name="autor"]').value,
        genero: document.querySelector('select[name="genero"]').value,
        numeroPaginas : document.querySelector('input[name="numeroPaginas"]').value
     
        
    };
    
    return proximasLeiturasEdit
}


function editarProximas(id, proximasLeiturasEdit){
    console.log("Editando próximas leituras:", id);
    
    //RECEBE OS DADOS DO FORMULÁRIO

    
    //RECEBE A ROTA VIA SPRINGBOOT
    $.ajax({
        url: "/editar/proximas-leituras/" + id,
        type: "PUT",
        data: JSON.stringify(proximasLeiturasEdit),
        contentType: "application/json",
        success: function(response) {
            // Lógica para manipular a resposta de sucesso
            console.log(response);
            var modalProx = document.getElementById("modal");
            modalProx.style.display = "none";
            var modalSucesso = document.getElementById("modal-sucesso");
            modalSucesso.style.display = "block";
            
        },
        error: function(xhr, status, error) {
            // Lógica para manipular o erro de requisição
            console.log(xhr.responseText);
            var modalProx = document.getElementById("modal");
            modalProx.style.display = "none";
            
        }
        
        
    })}
 
};











// EDITAR LISTA DE DESEJOS

function exibirModalEdicaoLista(button) {
	var id = button.querySelector('input[name="id"]').value;
	var modalLista = document.getElementById("modal");
	modalLista.style.display = "block";
	var salvarEdicao = document.getElementById("salvarEdicao");
	salvarEdicao.onclick = function() {
		if (validarLista()) {
			editarLista(id, getLista());
		} else {
			var inputsInvalidos = document.querySelectorAll('input:invalid');
			var selectsInvalidos = document.querySelectorAll('select:invalid');

			for (var i = 0; i < inputsInvalidos.length; i++) {
				inputsInvalidos[i].style.borderColor = "#ff0000";
			}

			for (var s = 0; s < selectsInvalidos.length; s++) {
				selectsInvalidos[s].style.borderColor = "#ff0000";
			}

		}
	}
};

	
	


function validarLista() {
    var livroInput = document.querySelector('input[name="livro"]');
    var autorInput = document.querySelector('input[name="autor"]');
    var generoSelect = document.querySelector('select[name="genero"]');

    if (
        livroInput.value === "" ||
        autorInput.value === "" ||
        generoSelect.value === ""

    ) {
        return false;
    }

    return true;
};


function getLista(){
	
	 var listaDesejosEdit = {
        livro: document.querySelector('input[name="livro"]').value,
        autor: document.querySelector('input[name="autor"]').value,
        genero: document.querySelector('select[name="genero"]').value,
        site: document.querySelector('input[name="site"]').value, 
        preco: document.querySelector('input[name="preco"]').value,    
    };
    
    return listaDesejosEdit
};



function editarLista(id, listaDesejosEdit) {
    console.log("Editando lista:", id);
    
    //RECEBE OS DADOS DO FORMULÁRIO
   
    
    
    //RECEBE A ROTA VIA SPRINGBOOT
    $.ajax({
        url: "/editar/lista/" + id,
        type: "PUT",
        data: JSON.stringify(listaDesejosEdit),
        contentType: "application/json",
        success: function(response) {
            // Lógica para manipular a resposta de sucesso
            console.log(response);
            var modalLista = document.getElementById("modal");
            modalLista.style.display = "none";
            var modalSucesso = document.getElementById("modal-sucesso");
            modalSucesso.style.display = "block";
        },
        error: function(xhr, status, error) {
            // Lógica para manipular o erro de requisição
            console.log(xhr.responseText);
            var modalLista = document.getElementById("modal");
            modalLista.style.display = "none";
            
        }
        
        
    })} ;
 




//EDITAR EMPRESTADOS//

function exibirModalEdicaoEmprest(button) {
    var id = button.querySelector('input[name="id"]').value;
    var modalEmprest = document.getElementById("modal");
    modalEmprest.style.display = "block";
	var salvarEdicao = document.getElementById("salvarEdicao");
	salvarEdicao.onclick = function() {
		if (validarEmprest()) {
			editarEmprest(id, getEmprestados());
		} else {
			var inputsInvalidos = document.querySelectorAll('input:invalid');
			var selectsInvalidos = document.querySelectorAll('select:invalid');

			for (var i = 0; i < inputsInvalidos.length; i++) {
				inputsInvalidos[i].style.borderColor = "#ff0000";
			}

			for (var s = 0; s < selectsInvalidos.length; s++) {
				selectsInvalidos[s].style.borderColor = "#ff0000";
			}

		}
	}
};

function validarEmprest() {
	var livroInput = document.querySelector('input[name="livro"]');
	var autorInput = document.querySelector('input[name="autor"]');
	var generoSelect = document.querySelector('select[name="genero"]');
	var pessoaInput = document.querySelector('input[name="pessoa"]');
	var dataInput = document.querySelector('input[name="data"]');
	var dataDevolInput = document.querySelector('input[name="dataDevol"]');

	if (
		livroInput.value === "" ||
		autorInput.value === "" ||
		generoSelect.value === "" ||
		pessoaInput.value === "" ||
		dataInput.value === "" ||
		dataDevolInput.value === ""

    ) {
		return false;
	}

	return true;
};



function getEmprestados(){
	 var emprestimosEdit = {
        livro: document.querySelector('input[name="livro"]').value,
        autor: document.querySelector('input[name="autor"]').value,
        genero: document.querySelector('select[name="genero"]').value,
        pessoa: document.querySelector('input[name="pessoa"]').value, 
        data: document.querySelector('input[name="data"]').value,
        dataDevol: document.querySelector('input[name="dataDevol"]').value,  
    };
    
    return emprestimosEdit
};



function editarEmprest(id, emprestimosEdit) {
    console.log("Editando emprestados:", id);
    
    //RECEBE OS DADOS DO FORMULÁRIO
   
    
    
    //RECEBE A ROTA VIA SPRINGBOOT
    $.ajax({
        url: "/editar/emprestados/" + id,
        type: "PUT",
        data: JSON.stringify(emprestimosEdit),
        contentType: "application/json",
        success: function(response) {
            // Lógica para manipular a resposta de sucesso
            console.log(response);
            var modalEmprest = document.getElementById("modal");
            modalEmprest.style.display = "none";
            var modalSucesso = document.getElementById("modal-sucesso");
            modalSucesso.style.display = "block";
          
            location.reload("/emprestados")
        },
        error: function(xhr, status, error) {
            // Lógica para manipular o erro de requisição
            console.log(xhr.responseText);
            var modalEmprest = document.getElementById("modal");
            modalEmprest.style.display = "none";
            
        }
   
    }) ;
 
}


