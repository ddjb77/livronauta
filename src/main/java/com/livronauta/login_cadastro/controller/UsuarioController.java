package com.livronauta.login_cadastro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.livronauta.login_cadastro.config.CustomUserDetails;
import com.livronauta.login_cadastro.models.Emprestimos;
import com.livronauta.login_cadastro.models.InfoUsuario;
import com.livronauta.login_cadastro.models.Usuario;
import com.livronauta.login_cadastro.repository.InfoUsuarioRepository;
import com.livronauta.login_cadastro.repository.ListaDesejosRepository;
import com.livronauta.login_cadastro.repository.LivrosLidosRepository;
import com.livronauta.login_cadastro.repository.UsuarioRepository;
import com.livronauta.login_cadastro.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private InfoUsuarioRepository infoUsuarioRepository;

	@Autowired
	private LivrosLidosRepository livrosLidosRepository;

	@Autowired
	private ListaDesejosRepository listaDesejosRepository;
	


	@GetMapping("/login-page")
	public String getLoginPage() {
		return "login_page";
	}

	@PostMapping("/login")
	public String login(Authentication authentication, Model model) {
		if (authentication != null && authentication.isAuthenticated()) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			Usuario usuario = userDetails.getUsuario();

			// Verificar se o usuário tem dados no sistema
			InfoUsuario infoUsuario = infoUsuarioRepository.findByUsuario(usuario);

			if (infoUsuario != null) {
				model.addAttribute("cadastrado", true);
			} else {
				model.addAttribute("cadastrado", false);
			}

			return "redirect:/user/profile";
		}

		return "login_page";
	}

	@GetMapping("/anuncios")
	public String getAnuncios() {
		return "anuncios";
	}

	@GetMapping("/register")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String getRegisterPage(Model model) {
		model.addAttribute("registerRequest", new Usuario());
		return "register";
	}

	// rota principal de usuario

	@GetMapping("/user/profile")
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	public String userPage(Authentication authentication, Model model) {
		if (authentication != null && authentication.isAuthenticated()) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			Usuario usuario = userDetails.getUsuario();

			// Usuario usuario = usuarioRepository.findById(id).orElse(null);

			if (usuario != null) {
				// Verificar se o usuário tem dados no sistema
				InfoUsuario infoUsuario = infoUsuarioRepository.findByUsuario(usuario);

				if (infoUsuario != null) {
					model.addAttribute("infoUsuarioEditado", infoUsuario);
					usuario.setInfoUsuario(infoUsuario);
					usuarioRepository.save(usuario);

					int quantidadeLivrosLidos = livrosLidosRepository.contarLivrosLidos(usuario);
					int quantidadeLista = listaDesejosRepository.contarListaDesejos(usuario);

					model.addAttribute("livrosLidos", quantidadeLivrosLidos);
					model.addAttribute("listaDesejos", quantidadeLista);
					model.addAttribute("userLivroAtual", infoUsuario.getLivroAtual());
					model.addAttribute("userNumeroPaginas", infoUsuario.getNumeroPaginas());
					model.addAttribute("userPaginasTotais", infoUsuario.getPaginasTotais());
					model.addAttribute("userGenero", infoUsuario.getGenero());
					model.addAttribute("userCpf", usuario.getInfoUsuario().getCpf());
					model.addAttribute("userTel", usuario.getInfoUsuario().getTelefone());
					model.addAttribute("userAvatar", usuario.getInfoUsuario().getCaminhoImagem());
					
					
				}
				
				

				List<Usuario> usuarios = usuarioService.findAllUsuarios();
				model.addAttribute("usuarios", usuarios);
				model.addAttribute("user", usuario);
				model.addAttribute("userLogin", usuario.getLogin());
				model.addAttribute("userNome", usuario.getNome());

				return "user";
			}
		}

		return "error";
	}


	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String adminPage(Model model, Authentication authentication) {
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Usuario usuario = userDetails.getUsuario();

		List<Usuario> usuarios = usuarioService.findAllUsuarios();
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("admin", usuario);
		model.addAttribute("userLogin", usuario.getLogin());

		return "admin";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute Usuario usuario) {
		// System.out.println("register request:" + usuario);
		Usuario registeredUser = usuarioService.registrarUsuario(usuario.getNome(), usuario.getLogin(),
				usuario.getEmail(), usuario.getPassword(), usuario.getUsername());
		return registeredUser == null ? "error" : "redirect:/login-page";
	}

	@PostMapping("/save-info")
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	public String salvarInformacoes(Model model,

			@RequestParam(name = "nome", required = true) String nome,
			@RequestParam(name = "cpf", required = true) String cpf,
			@RequestParam(name = "telefone", required = true) String telefone,
			@RequestParam(name = "livroAtual", required = true) String livroAtual,
			@RequestParam(name = "numeroPaginas", required = true) int numeroPaginas,
			@RequestParam(name = "genero", required = true) String genero,
			@RequestParam(name = "paginasTotais", required = true) int paginasTotais,
			@RequestParam(name="imagemSrc", required = true) String imagemSrc,

			Authentication authentication) {

		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Usuario usuarioExistente = userDetails.getUsuario();

		InfoUsuario infoUsuario = usuarioExistente.getInfoUsuario();

		if (infoUsuario == null) {
			infoUsuario = new InfoUsuario();
			infoUsuario.setUsuario(usuarioExistente);
			usuarioExistente.setInfoUsuario(infoUsuario);
		}

		infoUsuario.setNome(nome);
		infoUsuario.setCpf(cpf);
		infoUsuario.setTelefone(telefone);
		infoUsuario.setLivroAtual(livroAtual);
		infoUsuario.setPaginasTotais(paginasTotais);
		infoUsuario.setGenero(genero);
		infoUsuario.setNumeroPaginas(numeroPaginas);
		infoUsuario.setCaminhoImagem(imagemSrc);

		infoUsuario.setId(usuarioExistente.getId());
		infoUsuarioRepository.save(infoUsuario); // Salva as informações do infoUsuario na tabela info_usuario

		model.addAttribute("userNome", infoUsuario.getNome());
		model.addAttribute("userTel", infoUsuario.getTelefone());
		model.addAttribute("userCpf", infoUsuario.getCpf());
		model.addAttribute("userAvatar", infoUsuario.getCaminhoImagem());
		model.addAttribute("userNumeroPaginas", infoUsuario.getNumeroPaginas());

		return "redirect:/user/profile";
	}}
	/*
	@PostMapping("/editar-perfil")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> editarPerfil(Authentication authentication, @RequestBody InfoUsuario infoUsuarioEditado) {
	    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
	    Usuario usuarioExistente = userDetails.getUsuario();

	    InfoUsuario infoUsuario = usuarioExistente.getInfoUsuario();

	    if (infoUsuario != null) {
	        infoUsuario.setLivroAtual(infoUsuarioEditado.getLivroAtual());
	        infoUsuario.setGenero(infoUsuarioEditado.getGenero());
	        infoUsuario.setPaginasTotais(infoUsuarioEditado.getPaginasTotais());
	        infoUsuario.setNumeroPaginas(infoUsuarioEditado.getNumeroPaginas());

	        infoUsuarioRepository.save(infoUsuario);

	        return ResponseEntity.ok("Perfil atualizado com sucesso.");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	    
	}
	
}
	*/













	
