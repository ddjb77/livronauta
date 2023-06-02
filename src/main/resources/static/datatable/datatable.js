$(document).ready( function () {

  $('table').DataTable( {

    "language": {
      lengthMenu: "Exibindo _MENU_ itens",
      empty: "Nenhum dado encontrado",
      info: "Mostrando  _END_ de _TOTAL_ registros",
      search: "Procurar:",
      paginate: {
        next: ">>",
        previous: "<<"
      }
    }
  });
    
});



 

