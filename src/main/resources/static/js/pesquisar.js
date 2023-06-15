$("#form-pesquisa").submit(function(e) {
  $("#books").html("");

  e.preventDefault();

  var searchQuery = $("#barra-pesquisa").val();
  searchQuery = searchQuery.split(" ").join("+");

  if (!searchQuery) {
    searchQuery = "paleo";
  }

  $.ajax({
    url: "https://www.googleapis.com/books/v1/volumes?q=" + searchQuery,
    success: function(json) {
      var htmlcontent = "";

      for (i = 0; i < json.items.length; i++) {
        var thumb = "";
        var author = "";
        var description = "";
        var page = "";
        var preview = "";

        if (typeof json.items[i].volumeInfo.imageLinks != "undefined") {
          thumb = json.items[i].volumeInfo.imageLinks.thumbnail;
        } else {
          thumb = "https://i.imgur.com/PQWm3v7.jpg";
        }

        if (typeof json.items[i].volumeInfo.authors != "undefined") {
          author = json.items[i].volumeInfo.authors[0];
        }

        if (typeof json.items[i].volumeInfo.pageCount != "undefined") {
          page = json.items[i].volumeInfo.pageCount;
        }

        if (typeof json.items[i].volumeInfo.description != "undefined") {
          description = json.items[i].volumeInfo.description;
        }

        if (typeof json.items[i].volumeInfo.previewLink != "undefined") {
          preview = json.items[i].volumeInfo.previewLink;
        }

        htmlcontent +=
          "<li class='result'>" +
          '<img src="' +
          thumb +
          '" class="capa-livro" alt="' +
          json.items[i].volumeInfo.title +
          '">' +
          "<div class='nome-livro'>" +
          json.items[i].volumeInfo.title +
          "</div>" +
          "<div class='mais-inf'>" +
          "<b>Autor: </b>" +
          author +
          "<br> <b>Páginas: </b>" +
          page +
          " páginas" +
          "<br>" +
          "<div class='ver-livro'>" +
          "<a href='' class='sin' data-description='" +
          description +
          "' data-title='" +
          json.items[i].volumeInfo.title +
          "'>" +
          "Sinopse" +
          "</a>" +
          "<a href='" +
          preview +
          "' target='_blank'>Ler Livro</a>" +
          "</div>" +
          "</div>" +
          "</li>";
      }

      document.getElementById("livros-pesquisa").innerHTML = htmlcontent;

      $('.sin').click(function(e) {
        e.preventDefault();
        var description = $(this).data('description');
        var title = $(this).data('title');
        var modalSinopse =
          '<div class="modal-container">' +
          '<div class="modalpopup">' +
          '<div class="topo-form">' +
          '<h1>' +
          'Sinopse ' +
          '</h1>' +
          '</div>' +
          '<div class="sucesso">' +
          '<h1>' +  title + '</h1>' +
          '<p class="descricao">' +
          description +
          '</p>' +
          '<div class="btn-ok">' +
          '<a href="#" class="ok-sinopse" title="Terminei de ler a sinopse" alt="Terminei de ler a sinopse" >' +
          'OK' +
          '</a>' +
          '<a href="#" class="ler-voz" title="Ler em voz alta" alt="Ler em voz alta">' +
          'Ler em Voz Alta' +
          '</a>' +
          '</div>';

        $('body').append(modalSinopse);
        $('.modal-container').show();
      });

      $(document).on('click', '.ok-sinopse', function(e) {
        e.preventDefault();
        $(this).closest('.modal-container').remove();
        pausarLeitura();
      }); 

      $(document).on('click', '.ler-voz', function(e) {
        e.preventDefault();
        var texto = $(this).closest('.sucesso').find('p').text();
        lerTextoEmVozAlta(texto);
        $(this).addClass('ler-voz-disabled').off('click');
      });
    },
  });
});

var utterance;
var leituraAtiva = true;

function lerTextoEmVozAlta(texto) {
  utterance = new SpeechSynthesisUtterance(texto);
  speechSynthesis.speak(utterance);
}

function pausarLeitura() {
  if (leituraAtiva) {
    speechSynthesis.pause();
    leituraAtiva = false;
  } else {
    speechSynthesis.resume();
    leituraAtiva = true;
  }
}
