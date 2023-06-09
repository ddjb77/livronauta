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
      var modalSinopse = ""; 
      var thumb = "";
      var author = "";
      var description = "";
      var page = '';
      var preview = "";
      var isbn = "";

      for (i = 0; i < json.items.length; i++) {

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
          description = json.items[i].volumeInfo.description
        }

        if (typeof json.items[i].volumeInfo.previewLink != "undefined") {
          preview = json.items[i].volumeInfo.previewLink;
        }
        
        if (typeof json.items[i].volumeInfo.industryIdentifiers != "undefined") {
          isbn = json.items[i].volumeInfo.industryIdentifiers[0].identifier;
        }
          

        htmlcontent +=
        "<li class = 'result'>" + 
             '<img  src="'  +  thumb +  "class=''capa-livro'"  + "alt=" + json.items[i].volumeInfo.title +
          '">' +
          "<div class = 'nome-livro'> " + json.items[i].volumeInfo.title + "</div>" +
          "<div class='mais-inf'>" +
               "<b>Autor: </b>" +  author +
              "<br> <b>Páginas: </b>" + page + " páginas" +
              "<br>" +
            "<div class ='ver-livro' >" +
                 "<a href=" + preview + "target='_blank'>" + " Ler Livro"+ "</a>" +
              "</div>" +
          '</div>' +
        "</li>"

   
    }


    document.getElementById("livros-pesquisa").innerHTML = htmlcontent;
    
     
    }



  });
});

     
  


