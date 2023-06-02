package com.livronauta.login_cadastro.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livronauta.login_cadastro.models.InfoUsuario;
import com.livronauta.login_cadastro.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByLoginAndPassword(String login, String password);
	
	Optional<Usuario> findFirstByLogin(String login);
	
	Optional<Usuario> findByEmail(String email);
	
	Optional<Usuario> findById(Long id);

	Optional<Usuario>findByUsername(String login);

	Optional<Usuario> findByLogin(String login);
	
	Usuario findByNome(String nome);

}

