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
import com.livronauta.login_cadastro.models.ProximasLeituras;
import com.livronauta.login_cadastro.models.Usuario;
import com.livronauta.login_cadastro.repository.ListaDesejosRepository;
import com.livronauta.login_cadastro.repository.LivrosLidosRepository;
import com.livronauta.login_cadastro.repository.ProximasLeiturasRepository;
import com.livronauta.login_cadastro.service.ProximasLeiturasService;

@Controller
public class ProximasLeiturasController {

	private final ProximasLeiturasRepository proximasLeiturasRepository;
	
	private final ListaDesejosRepository listaDesejosRepository;
	
	private final LivrosLidosRepository livrosLidosRepository;

	private ProximasLeiturasService proximasLeiturasService;

	@Autowired
	public ProximasLeiturasController(ProximasLeiturasService proximasLeiturasService,
			ProximasLeiturasRepository proximasLeiturasRepository, ListaDesejosRepository listaDesejosRepository, LivrosLidosRepository livrosLidosRepository) {
		this.setProximasLeiturasService(proximasLeiturasService);
		this.proximasLeiturasRepository = proximasLeiturasRepository;
		this.listaDesejosRepository = listaDesejosRepository;
		this.livrosLidosRepository = livrosLidosRepository;
	}

	@GetMapping("/proximas-leituras")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String getLivrosLidosPage(Model model, Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			Usuario usuario = userDetails.getUsuario();

			// Recuperar a lista de livros lidos do repositório para o usuário autenticado
			List<ProximasLeituras> proximasLeituras = proximasLeiturasRepository.findByUsuario(usuario);

			// Adicionar a lista ao modelo
			
			int quantidadeLivrosLidos = livrosLidosRepository.contarLivrosLidos(usuario);
            int quantidadeLista = listaDesejosRepository.contarListaDesejos(usuario);
            
            // mostra a quantidade de livros lidos e de items na lista de desejo na página

            model.addAttribute("livrosLidos", quantidadeLivrosLidos);
            model.addAttribute("listaDesejos", quantidadeLista);
			model.addAttribute("proximasLeituras", proximasLeituras);
			model.addAttribute("userLogin", usuario.getLogin());

			return "proximas-leituras";
		}

		return "error";
	}

	@PostMapping("/save-prox-leitura")
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	public String salvarLivroLido(Model model, @RequestParam(name = "livro", required = true) String livro,
			@RequestParam(name = "autor", required = true) String autor,
			@RequestParam(name = "genero", required = true) String genero, Authentication authentication) {

		// Obter o usuário autenticado
		if (authentication != null && authentication.isAuthenticated()) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			Usuario usuario = userDetails.getUsuario();

			// Criar uma instância de LivrosLidos e definir os atributos
			ProximasLeituras proximaLeitura = new ProximasLeituras();
			proximaLeitura.setLivro(livro);
			proximaLeitura.setAutor(autor);
			proximaLeitura.setGenero(genero);
			proximaLeitura.setUsuario(usuario);

			// Salvar o livro lido no repositório
			proximasLeiturasRepository.save(proximaLeitura);

			model.addAttribute("nomelivro", proximaLeitura.getLivro());
			model.addAttribute("autorLivro", proximaLeitura.getAutor());
			model.addAttribute("generoLivro", proximaLeitura.getGenero());
			model.addAttribute("userLogin", usuario.getLogin());

			return "redirect:/proximas-leituras";
		}

		return "error";
	}

	@GetMapping("/proximas")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String getFormProxLeituras() {
		return "formprox";
	}

	public ProximasLeiturasService getProximasLeiturasService() {
		return proximasLeiturasService;
	}

	public void setProximasLeiturasService(ProximasLeiturasService proximasLeiturasService) {
		this.proximasLeiturasService = proximasLeiturasService;
	}

}
