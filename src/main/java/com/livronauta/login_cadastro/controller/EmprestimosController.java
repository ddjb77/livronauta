package com.livronauta.login_cadastro.controller;

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
import com.livronauta.login_cadastro.models.Emprestimos;
import com.livronauta.login_cadastro.models.Usuario;
import com.livronauta.login_cadastro.repository.EmprestimosRepository;
import com.livronauta.login_cadastro.service.EmprestimosService;

@Controller
public class EmprestimosController {

	private final EmprestimosRepository emprestimosRepository;

	private EmprestimosService emprestimosService;

	@Autowired
	public EmprestimosController(EmprestimosService emprestimosService,
	                             EmprestimosRepository emprestimosRepository) {
	    this.emprestimosService = emprestimosService;
	    this.emprestimosRepository = emprestimosRepository;
	}

	
	
	
	@GetMapping("/emprestados")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String getEmprestadossPage(Model model, Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			Usuario usuario = userDetails.getUsuario();

			// Recuperar a lista de livros emprestados para o usuario logado
			List<Emprestimos> emprestimos = emprestimosRepository.findByUsuario(usuario);

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

	
	
	private void setEmprestimosService(EmprestimosService emprestimosService2) {
		// TODO Auto-generated method stub
		
	}

}
