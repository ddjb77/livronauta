package com.livronauta.login_cadastro.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.livronauta.login_cadastro.config.CustomUserDetails;
import com.livronauta.login_cadastro.models.ListaDesejos;
import com.livronauta.login_cadastro.models.LivrosLidos;
import com.livronauta.login_cadastro.models.ProximasLeituras;
import com.livronauta.login_cadastro.models.Usuario;
import com.livronauta.login_cadastro.repository.ListaDesejosRepository;
import com.livronauta.login_cadastro.repository.LivrosLidosRepository;
import com.livronauta.login_cadastro.service.LivrosLidosService;

@Controller
public class ListaDesejosController {
	
	private final ListaDesejosRepository listaDesejosRepository;
	
	
	@Autowired
    public ListaDesejosController(ListaDesejosRepository listaDesejosRepository) {
        this.listaDesejosRepository = listaDesejosRepository;
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
			model.addAttribute("listaDesejos", listaDesejos);
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
	
	
}	


