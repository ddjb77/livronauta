package com.livronauta.login_cadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.livronauta.login_cadastro.models.Usuario;
import com.livronauta.login_cadastro.repository.UsuarioRepository;

@Controller
public class AdminController {
		
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@DeleteMapping("/excluir/user/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> removerUsuario(@PathVariable Long id) {
		java.util.Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

		if (usuarioOptional.isPresent()) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.ok("Usuário removido com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}

	

