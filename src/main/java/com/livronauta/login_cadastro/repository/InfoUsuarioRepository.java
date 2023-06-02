package com.livronauta.login_cadastro.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livronauta.login_cadastro.models.InfoUsuario;

@Repository
public interface InfoUsuarioRepository extends JpaRepository<InfoUsuario, Long> {
    // Aqui você pode adicionar métodos personalizados, se necessário
	
	
	
}
