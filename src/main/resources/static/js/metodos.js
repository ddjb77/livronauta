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
        },
        error: function(xhr, status, error) {
            // Lógica para manipular o erro de requisição
            console.log(xhr.responseText);
            var modal = document.getElementById("modalConfirmacao");
            modal.style.display = "none";
        }
    });
    
    location.reload("/admin");
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
        },
        error: function(xhr, status, error) {
            // Lógica para manipular o erro de requisição
            console.log(xhr.responseText);
            var modal = document.getElementById("modalConfirmacao");
            modal.style.display = "none";
            
        }
    });
    location.reload("/lista-desejos");

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
        },
        error: function(xhr, status, error) {
            // Lógica para manipular o erro de requisição
            console.log(xhr.responseText);
            var modal = document.getElementById("modalConfirmacao");
            modal.style.display = "none";
            
        }
    });
    location.reload("/emprestados");

}
















































