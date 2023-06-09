package com.livronauta.login_cadastro.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.livronauta.login_cadastro.config.CustomUserDetails;
import com.livronauta.login_cadastro.models.ListaDesejos;
import com.livronauta.login_cadastro.models.LivrosLidos;
import com.livronauta.login_cadastro.models.ProximasLeituras;
import com.livronauta.login_cadastro.models.Usuario;
import com.livronauta.login_cadastro.repository.ListaDesejosRepository;
import com.livronauta.login_cadastro.repository.LivrosLidosRepository;
import com.livronauta.login_cadastro.repository.ProximasLeiturasRepository;
import com.livronauta.login_cadastro.service.LivrosLidosService;

@Controller
public class ListaDesejosController {
	
	private final ProximasLeiturasRepository proximasLeiturasRepository;
	
	private final ListaDesejosRepository listaDesejosRepository;
	
	private final LivrosLidosRepository livrosLidosRepository;
	
	
	@Autowired
    public ListaDesejosController(ListaDesejosRepository listaDesejosRepository, ProximasLeiturasRepository proximasLeiturasRepository,LivrosLidosRepository livrosLidosRepository ) {
        this.listaDesejosRepository = listaDesejosRepository;
        this.livrosLidosRepository = livrosLidosRepository;
        this.proximasLeiturasRepository = proximasLeiturasRepository;
    }
	
	@GetMapping("/lista-desejos")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String getLivrosLidosPage(Model model, Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			Usuario usuario = userDetails.getUsuario();

			// Recuperar a lista de livros lidos do repositório para o usuário autenticado
			List<ListaDesejos> listaDesejos = listaDesejosRepository.findByUsuario(usuario);

			// Adicionar a lista ao modelo
			
			int quantidadeLivrosLidos = livrosLidosRepository.contarLivrosLidos(usuario);
            int quantidadeLista = listaDesejosRepository.contarListaDesejos(usuario);
            
            // mostra a quantidade de livros lidos e de items na lista de desejo na página

            model.addAttribute("livrosLidos", quantidadeLivrosLidos);
            model.addAttribute("listaDesejos", quantidadeLista);
			model.addAttribute("userLogin", usuario.getLogin());
			model.addAttribute("qtlistaDesejos", listaDesejos);
			model.addAttribute("userLogin", usuario.getLogin());

			return "lista-desejos";
		}

		return "error";
	}
	
	
	@GetMapping("/lista")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String getFormListaDesejosPage() {
		return "formlista";
	}
	
	@PostMapping("/save-lista-desejos")
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	public String salvarLivroLido(Model model,
	                              @RequestParam(name = "livro", required = true) String livro,
	                              @RequestParam(name = "autor", required = true) String autor,
	                              @RequestParam(name = "genero", required = true) String genero,
	                              @RequestParam(name = "site", required = true) String site,
	                              @RequestParam(name = "preco", required = true) BigDecimal preco,
	                              Authentication authentication) {

	    // Obter o usuário autenticado
	    if (authentication != null && authentication.isAuthenticated()) {
	        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
	        Usuario usuario = userDetails.getUsuario();

	        // Criar uma instância de LivrosLidos e definir os atributos
	        ListaDesejos listaDesejos = new ListaDesejos();
	        listaDesejos.setLivro(livro);
	        listaDesejos.setAutor(autor);
	        listaDesejos.setGenero(genero);
	        listaDesejos.setSite(site);
	        listaDesejos.setPreco(preco);
	        listaDesejos.setUsuario(usuario);

	        // Salvar o livro lido no repositório
	        listaDesejosRepository.save(listaDesejos);
	        
	        model.addAttribute("nomelivro", listaDesejos.getLivro());
	        model.addAttribute("autorLivro", listaDesejos.getAutor());
	        model.addAttribute("generoLivro", listaDesejos.getGenero());
	        model.addAttribute("siteLista", listaDesejos.getSite());
	        model.addAttribute("precoLivro", listaDesejos.getSite());
            model.addAttribute("userLogin", usuario.getLogin());


	        return "redirect:/lista-desejos";
	    }


	    return "error";
	}
	
	@DeleteMapping("/excluir/lista/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> excluirLista(@PathVariable Long id, Authentication authentication) 
	{
		Optional<ListaDesejos> listaDesejosOptional = listaDesejosRepository.findById(id);
		if (authentication != null && authentication.isAuthenticated()) {
	        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
	        Usuario usuario = userDetails.getUsuario();
		if (listaDesejosOptional.isPresent()) {
			listaDesejosRepository.deleteById(id);
			return ResponseEntity.ok("Livro excluído da lista");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
		return null;
	}
	
	@PutMapping("/editar/lista/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> editarLista(@PathVariable Long id, @RequestBody ListaDesejos listaDesejosEdit) {
	    Optional<ListaDesejos> listaDesejosOptional = listaDesejosRepository.findById(id);

	    if (listaDesejosOptional.isPresent()) {
	    	ListaDesejos listaDesejos = listaDesejosOptional.get();

	        // Atualizar os campos do livro lido com os valores do livroEditado
	    	listaDesejos.setLivro(listaDesejosEdit.getLivro());
	    	listaDesejos.setAutor(listaDesejosEdit.getAutor());
	    	listaDesejos.setGenero(listaDesejosEdit.getGenero());
	    	listaDesejos.setSite(listaDesejosEdit.getSite());
	    	listaDesejos.setPreco(listaDesejosEdit.getPreco());
	    	

	    	listaDesejosRepository.save(listaDesejos);

	        return ResponseEntity.ok("Lista de desejos atualizada");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
}	


