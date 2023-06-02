package com.livronauta.login_cadastro.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.livronauta.login_cadastro.config.CustomUserDetails;
import com.livronauta.login_cadastro.models.InfoUsuario;
import com.livronauta.login_cadastro.models.Usuario;
import com.livronauta.login_cadastro.repository.InfoUsuarioRepository;
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

	@GetMapping("/login")
	public String getLoginPage() {
		return "login_page";
	}

	@GetMapping("/confirmacao-email")
	public String geConfirmacaoPage() {
		return "confirmacao-email";
	}

	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("registerRequest", new Usuario());
		return "register";
	}


	@GetMapping("/user/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String userPage(Authentication authentication, @PathVariable Long id, Model model1) {
		if (id != null) {
			java.util.Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

			if (optionalUsuario.isPresent()) {
				Usuario usuario = optionalUsuario.get();
				// código para obter informações específicas do usuário, utilizando o objeto
				// "usuario"
				List<Usuario> usuarios = usuarioService.findAllUsuarios();
				model1.addAttribute("usuarios", usuarios);
				model1.addAttribute("user", usuario);
				model1.addAttribute("userLogin", usuario.getLogin());
			    model1.addAttribute("userNome", usuario.getNome());
		        model1.addAttribute("userCpf", usuario.getInfoUsuario().getCpf());
		        model1.addAttribute("userTel", usuario.getInfoUsuario().getTelefone());
		        model1.addAttribute("userLivroAtual", usuario.getInfoUsuario().getLivroAtual());
		        model1.addAttribute("userNumeroPaginas", usuario.getInfoUsuario().getNumeroPaginas());
		        model1.addAttribute("userGenero", usuario.getInfoUsuario().getGenero());


				model1.addAttribute("id", usuario.getId());
				
				return "user";
			} else {
				return "error";
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
		return registeredUser == null ? "error" : "redirect:/login";
	}
	
	@PostMapping("/save-info")
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	public String salvarInformacoes(Model model,
	                                @RequestParam(name = "nome", required = true) String nome,
	                                @RequestParam(name = "cpf", required = true) String cpf,
	                                @RequestParam(name = "telefone", required = true) String telefone,
	                                @RequestParam(name = "livroAtual", required = true) String livroAtual,
	                                @RequestParam(name = "numeroPagina", required = true) int numeroPaginas,
	                                @RequestParam(name = "genero", required = false) String genero,
	                                @RequestParam(name = "livroLido", required = false) String livroLido,


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
	    infoUsuario.setGenero(genero);
	    infoUsuario.setNumeroPaginas(numeroPaginas);

	    infoUsuarioRepository.save(infoUsuario); // Salva as informações do infoUsuario na tabela info_usuario

	    model.addAttribute("userNome", infoUsuario.getNome());
	    model.addAttribute("userTel", infoUsuario.getTelefone());
	    model.addAttribute("userCpf", infoUsuario.getCpf());
	    model.addAttribute("userCpf", infoUsuario.getNumeroPaginas());

	    return "redirect:/user/" + usuarioExistente.getId();
	}





	@PostMapping("/excluir/user/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> removerUsuario(@PathVariable Long id) {
		java.util.Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

		if (usuarioOptional.isPresent()) {
			Usuario usuario = usuarioOptional.get();
			usuarioRepository.delete(usuario);
			return ResponseEntity.ok("Usuário removido com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
