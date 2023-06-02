window.onload = function form() {
    let perfil = document.querySelector('#modal')
    let modal = document.querySelector('#modal-user')
    perfil.style.display = "none"
    setTimeout(() => {
        modal.style.display = "none"
        perfil.style.display = "block"
     }, 2500 );
     
}

/*   
function updateImage() {
    var genero = document.getElementById("generos").value;
    var imagePlaceholder = document.getElementById('imagePlaceholder');
    var imagePath = "/img/genero-livros/";

    // Verifique a opção selecionada e defina o caminho da imagem correspondente
    switch (genero) {
      case "aventura":
        imagePath += "icone-livros-aventura.png";
        break;
      case "autoajuda":
        imagePath += "icone-livros-autoajuda.png";
        break;
      case "biografia":
        imagePath += "icone-livros-biografia.png";
        break;
      case "conto":
        imagePath += "icone-livros-conto.png";
        break;
      case "didatico":
        imagePath += "icone-livros-didatico.png";
        break;
      case "drama":
        imagePath += "icone-livros-drama.png";
        break;
      case "ficcao":
        imagePath += "icone-livros-ficcao.png";
        break;
      case "fantasia":
        imagePath += "icone-livros-fantasia.png";
        break;
      case "poema":
        imagePath += "icone-livros-poema.png";
        break;
      case "policial":
        imagePath += "icone-livros-policial.png";
        break;
      case "romance":
        imagePath += "icone-livros-romance.png";
        break;
      case "suspense":
        imagePath += "icone-livros-suspense.png";
        break;
      case "terror":
        imagePath += "icone-livros-terror.png";
        break;
      default:
        imagePath += "icone-livros-outros.png";
        break;
    }

    imagePlaceholder.src = imagePath;
  }
*/


const handlePhone = (event) => {
    let input = event.target
    input.value = phoneMask(input.value)
  }
  
  const phoneMask = (value) => {
    if (!value) return ""
    value = value.replace(/\D/g,'')
    value = value.replace(/(\d{2})(\d)/,"($1) $2")
    value = value.replace(/(\d)(\d{4})$/,"$1-$2")
    return value
  }


  const handleCPF = (event) => {
    let input = event.target
    input.value = cpfMask(input.value)
  }
  
  const cpfMask = (value) => {
    if (!value) return ""
    value = value.replace(/\D/g,'')
    value = value.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4")
    return value
  }