package com.livronauta.login_cadastro.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.livronauta.login_cadastro.config.CustomUserDetails;
import com.livronauta.login_cadastro.models.LivrosLidos;
import com.livronauta.login_cadastro.models.Usuario;
import com.livronauta.login_cadastro.repository.ListaDesejosRepository;
import com.livronauta.login_cadastro.repository.LivrosLidosRepository;
import com.livronauta.login_cadastro.service.LivrosLidosService;

@Controller
public class LivrosLidosController {
	
	private final LivrosLidosService livrosLidosService;
	
    private final LivrosLidosRepository livrosLidosRepository;
    
    private final ListaDesejosRepository listaDesejosRepository;

    @Autowired
    public LivrosLidosController(LivrosLidosService livrosLidosService, LivrosLidosRepository livrosLidosRepository, ListaDesejosRepository listaDesejosRepository) {
        this.livrosLidosService = livrosLidosService;
        this.livrosLidosRepository = livrosLidosRepository;
        this.listaDesejosRepository = listaDesejosRepository;
    }
	
    @GetMapping("/livros-lidos")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getLivrosLidosPage(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Usuario usuario = userDetails.getUsuario();

            // Recuperar a lista de livros lidos do repositório para o usuário autenticado
            List<LivrosLidos> livrosLidos = livrosLidosRepository.findByUsuario(usuario);

            int quantidadeLivrosLidos = livrosLidosRepository.contarLivrosLidos(usuario);
            int quantidadeLista = listaDesejosRepository.contarListaDesejos(usuario);
            
            // mostra a quantidade de livros lidos e de items na lista de desejo na página

            model.addAttribute("livrosLidos", quantidadeLivrosLidos);
            model.addAttribute("listaDesejos", quantidadeLista);
            
            //adiciona os livros lidos à pagina
            model.addAttribute("qtlivrosLidos", livrosLidos);
            model.addAttribute("userLogin", usuario.getLogin());
            

            return "livros-lidos";
        }

        return "error";
    }
    
    
	
	@GetMapping("/lidos")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String getFormLivrosLidosPage() {
		return "formlidos";
	}
	
	
	
	
	@PostMapping("/apagar-lidos/{id}")
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	public String deletarLivroLido(@PathVariable("id") Long Id) {
	    // Verificar se o livro lido existe no repositório
	    Optional<LivrosLidos> livroLidoOptional = livrosLidosRepository.findById(Id);
	    
	    if (livroLidoOptional.isPresent()) {
	        LivrosLidos livroLido = livroLidoOptional.get();
	        
	        // Deletar o livro lido do repositório
	        livrosLidosRepository.delete(livroLido);
	        
	        return "redirect:/livros-lidos";
	    }
	    
	    // Retornar uma mensagem de erro, redirecionar para uma página de erro, etc.
	    return "error";
	}
	
	
	
	@PostMapping("/save-livro-lido")
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	public String salvarLivroLido(Model model,
	                              @RequestParam(name = "livro", required = true) String livro,
	                              @RequestParam(name = "autor", required = true) String autor,
	                              @RequestParam(name = "genero", required = true) String genero,
	                              @RequestParam(name = "ano", required = true) int ano,
	                              @RequestParam(name = "avaliacao", required = true) int avaliacao,
	                              Authentication authentication) {

	    // Obter o usuário autenticado
	    if (authentication != null && authentication.isAuthenticated()) {
	        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
	        Usuario usuario = userDetails.getUsuario();

	        // Criar uma instância de LivrosLidos e definir os atributos
	        LivrosLidos livroLido = new LivrosLidos();
	        livroLido.setLivro(livro);
	        livroLido.setAutor(autor);
	        livroLido.setGenero(genero);
	        livroLido.setAno(ano);
	        livroLido.setAvaliacao(avaliacao);
	        livroLido.setUsuario(usuario);

	        // Salvar o livro lido no repositório
	        livrosLidosRepository.save(livroLido);
	        
	        model.addAttribute("nomelivro", livroLido.getLivro());
	        model.addAttribute("autorLivro", livroLido.getAutor());
	        model.addAttribute("generoLivro", livroLido.getGenero());
	        model.addAttribute("anoLivro", livroLido.getAno());
	        model.addAttribute("avalicaoLivro", livroLido.getAvaliacao());
            model.addAttribute("userLogin", usuario.getLogin());


	        return "redirect:/livros-lidos";
	    }


	    return "error";
	}
	
	@DeleteMapping("/excluir/livros/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> removerLivroLido(@PathVariable Long id) {
		Optional<LivrosLidos> livrosLidosOptional = livrosLidosRepository.findById(id);

		if (livrosLidosOptional.isPresent()) {
			livrosLidosRepository.deleteById(id);
			return ResponseEntity.ok("Livro excluído");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}



