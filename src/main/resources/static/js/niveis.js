function abrirNivelCard(){
	var modalNivel = document.getElementById("modal-nivel");
	 modalNivel.style.display = "block";
	 
	    var okNivel = document.getElementById("okNivel");
    okNivel.addEventListener("click", fecharModalnv);
}

function fecharModalnv() {
    var modalNivel = document.getElementById("modal-nivel");
    modalNivel.style.display = "none";
}
