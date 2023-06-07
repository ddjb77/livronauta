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
}
