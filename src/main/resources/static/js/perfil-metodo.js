
	function salvarEdicaoPerfil() {
    // Obter os dados do formulário
    var livroAtual = $("#livroEdit").val();
    var genero = $("#generoEdit").val();
    var numeroPaginas = $("#qtpaginas-edit").val();
    var paginasTotais = $("#qtpaginasTotais").val();

    // Obter outros dados do formulário

    // Criar um objeto com os dados do perfil
    var infoUsuarioEditado = {
	
		
        livroAtual: livroAtual,
        genero: genero,
        numeroPaginas: numeroPaginas,
        paginasTotais: paginasTotais
        
        // Outros campos do objeto InfoUsuario
    };

    // Enviar requisição POST para o backend
    $.ajax({
        url: "/editar-perfil",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(infoUsuarioEditado),
        success: function(response) {
            // Manipular a resposta do backend em caso de sucesso
         var modalSucesso = document.getElementById("modal-sucesso");
         modalSucesso.style.display = "block";
            console.log(response);
         
        },
        error: function(error) {
            // Manipular o erro em caso de falha na requisição
            console.log(error);
        }
    });
}
