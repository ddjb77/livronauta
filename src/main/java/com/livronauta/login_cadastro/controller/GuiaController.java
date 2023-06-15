package com.livronauta.login_cadastro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.livronauta.login_cadastro.config.CustomUserDetails;
import com.livronauta.login_cadastro.models.InfoUsuario;
import com.livronauta.login_cadastro.models.LivrosLidos;
import com.livronauta.login_cadastro.models.Usuario;
import com.livronauta.login_cadastro.repository.InfoUsuarioRepository;
import com.livronauta.login_cadastro.repository.ListaDesejosRepository;
import com.livronauta.login_cadastro.repository.LivrosLidosRepository;
import com.livronauta.login_cadastro.repository.UsuarioRepository;

@Controller
public class GuiaController {
	
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private InfoUsuarioRepository infoUsuarioRepository;

	@Autowired
	private LivrosLidosRepository livrosLidosRepository;

	@Autowired
	private ListaDesejosRepository listaDesejosRepository;
	
	
	@GetMapping("/guia-page")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String getGuiaPage(Authentication authentication, Model model) {
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

			// adiciona os livros lidos à pagina
			model.addAttribute("qtlivrosLidos", livrosLidos);
			model.addAttribute("userLogin", usuario.getLogin());
			model.addAttribute("userAvatar", usuario.getInfoUsuario().getCaminhoImagem());
			return "guia";
		}
		return "error";
	}
		
	
	@GetMapping("/forum-page")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String getForumPage(Authentication authentication, Model model) {
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

			// adiciona os livros lidos à pagina
			model.addAttribute("qtlivrosLidos", livrosLidos);
			model.addAttribute("userLogin", usuario.getLogin());
			model.addAttribute("userAvatar", usuario.getInfoUsuario().getCaminhoImagem());
			return "forum";
		}

		return "error";
	}
}