
/*package com.livronauta.login_cadastro.controller;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.livronauta.login_cadastro.models.InfoUsuario;
import com.livronauta.login_cadastro.models.Usuario;
import com.livronauta.login_cadastro.service.UsuarioService;

@RestController
public class ImagemController {

	@GetMapping("/user-image")
	public ResponseEntity<byte[]> getUserImage() throws IOException {
		// Recupere a opção de gênero do usuário do banco de dados
		InfoUsuario usuario = usuario.getUsuario(); // Exemplo hipotético de serviço de usuário
		String genero = usuario.getUsuario().getGenero(); // Supondo que o gênero seja uma propriedade do objeto User

		// Monte o caminho da imagem com base no gênero
		String imageUrl = "/img/genero-livros/" + genero + ".png";

		// Lógica para obter o recurso da imagem e retornar a resposta
		Resource resource = new ClassPathResource(imageUrl);
		if (resource.exists()) {
			byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
	*/

	

	
