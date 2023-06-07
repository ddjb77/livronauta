/*window.onload = function form() {
    let perfil = document.querySelector('#modal')
    let modal = document.querySelector('#modal-user')
    perfil.style.display = "none"
    setTimeout(() => {
        modal.style.display = "none"
        perfil.style.display = "block"
     }, 2500 );
     
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
    
 

 