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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.livronauta.login_cadastro.config.CustomUserDetails;
import com.livronauta.login_cadastro.models.InfoUsuario;
import com.livronauta.login_cadastro.models.LivrosLidos;
import com.livronauta.login_cadastro.models.Usuario;
import com.livronauta.login_cadastro.repository.InfoUsuarioRepository;
import com.livronauta.login_cadastro.repository.ListaDesejosRepository;
import com.livronauta.login_cadastro.repository.LivrosLidosRepository;
import com.livronauta.login_cadastro.repository.UsuarioRepository;




@Controller
public class PerfilController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private InfoUsuarioRepository infoUsuarioRepository;

	@Autowired
	private LivrosLidosRepository livrosLidosRepository;

	@Autowired
	private ListaDesejosRepository listaDesejosRepository;
	
	@GetMapping("/pagina-perfil")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String editarPage(Authentication authentication, Model model) {
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
				model.addAttribute("user", usuario);
				model.addAttribute("userLogin", usuario.getLogin());
				model.addAttribute("userNome", usuario.getNome());

				return "editar-perfil";
			}
		}

		return "error";
	}
	
	
	
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
	
	
	




