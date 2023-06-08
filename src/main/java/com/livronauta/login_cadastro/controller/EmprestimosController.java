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
import com.livronauta.login_cadastro.models.Emprestimos;
import com.livronauta.login_cadastro.models.ListaDesejos;
import com.livronauta.login_cadastro.models.Usuario;
import com.livronauta.login_cadastro.repository.EmprestimosRepository;
import com.livronauta.login_cadastro.repository.ListaDesejosRepository;
import com.livronauta.login_cadastro.repository.LivrosLidosRepository;
import com.livronauta.login_cadastro.repository.ProximasLeiturasRepository;
import com.livronauta.login_cadastro.service.EmprestimosService;

@Controller
public class EmprestimosController {

	private final EmprestimosRepository emprestimosRepository;

	private EmprestimosService emprestimosService;
	
	
	private final ProximasLeiturasRepository proximasLeiturasRepository;
	
	private final ListaDesejosRepository listaDesejosRepository;
	
	private final LivrosLidosRepository livrosLidosRepository;

	@Autowired
	public EmprestimosController(EmprestimosService emprestimosService,
	                             EmprestimosRepository emprestimosRepository, 
	                             ListaDesejosRepository listaDesejosRepository,
	                             LivrosLidosRepository livrosLidosRepository,
	                             ProximasLeiturasRepository proximasLeiturasRepository
	                             ) {
	    this.emprestimosService = emprestimosService;
	    this.emprestimosRepository = emprestimosRepository;
	    this.listaDesejosRepository = listaDesejosRepository;
        this.livrosLidosRepository = livrosLidosRepository;
        this.proximasLeiturasRepository = proximasLeiturasRepository;
	}

	
	
	
	@GetMapping("/emprestados")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String getEmprestadossPage(Model model, Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			Usuario usuario = userDetails.getUsuario();

			// Recuperar a lista de livros emprestados para o usuario logado
			List<Emprestimos> emprestimos = emprestimosRepository.findByUsuario(usuario);
			
			
			int quantidadeLivrosLidos = livrosLidosRepository.contarLivrosLidos(usuario);
            int quantidadeLista = listaDesejosRepository.contarListaDesejos(usuario);
            
            // mostra a quantidade de livros lidos e de items na lista de desejo na página

            model.addAttribute("livrosLidos", quantidadeLivrosLidos);
            model.addAttribute("listaDesejos", quantidadeLista);
			model.addAttribute("userLogin", usuario.getLogin());
			model.addAttribute("userLogin", usuario.getLogin());
			// Adicionar a lista ao modelo
			model.addAttribute("emprestimos", emprestimos);
			model.addAttribute("userLogin", usuario.getLogin());

			return "emprestados";
		}

		return "error";
	}
	
	
	@GetMapping("/emprestimo")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String getFormEmprestimo() {
		return "formemprestimo";
	}
	
	
	@PostMapping("/salvar-emprestados")
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	public String getEmprestados(Model model, @RequestParam(name = "livro", required = true) String livro,
			@RequestParam(name = "autor", required = true) String autor,
			@RequestParam(name = "genero", required = true) String genero,
			@RequestParam(name = "pessoa", required = true) String pessoa,
			@RequestParam(name = "data", required = true) String data, Authentication authentication) {

		// Obter o usuário autenticado
		if (authentication != null && authentication.isAuthenticated()) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			Usuario usuario = userDetails.getUsuario();

			// Criar uma instância de emprestados e definir os atributos
			Emprestimos emprestimos = new Emprestimos();
			emprestimos.setLivro(livro);
			emprestimos.setAutor(autor);
			emprestimos.setGenero(genero);
			emprestimos.setPessoa(pessoa);
			emprestimos.setData(data);


			emprestimos.setUsuario(usuario);

			// Salvar o livro emprestadoo no repositório
			emprestimosRepository.save(emprestimos);

			model.addAttribute("nomelivro", emprestimos.getLivro());
			model.addAttribute("autorLivro", emprestimos.getAutor());
			model.addAttribute("generoLivro", emprestimos.getGenero());
			model.addAttribute("pessoa", emprestimos.getPessoa());
			model.addAttribute("data", emprestimos.getData());
			model.addAttribute("userLogin", usuario.getLogin());

			return "redirect:/emprestados";
		}

		return "error";
	}

	@DeleteMapping("/excluir/emprestados/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> excluirEmprestados(@PathVariable Long id, Authentication authentication) 
	{
		Optional<Emprestimos> emprestimos = emprestimosRepository.findById(id);
		if (authentication != null && authentication.isAuthenticated()) {
	        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
	        Usuario usuario = userDetails.getUsuario();
		if (emprestimos.isPresent()) {
			emprestimosRepository.deleteById(id);
			return ResponseEntity.ok("Empréstimo excluído da lista");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
		return null;
	}
	
	
	
	private void setEmprestimosService(EmprestimosService emprestimosService2) {
		// TODO Auto-generated method stub
		
	}

}
