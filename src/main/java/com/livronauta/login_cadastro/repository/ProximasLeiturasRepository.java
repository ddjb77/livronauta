package com.livronauta.login_cadastro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livronauta.login_cadastro.models.ProximasLeituras;
import com.livronauta.login_cadastro.models.Usuario;
@Repository
public interface ProximasLeiturasRepository extends JpaRepository<ProximasLeituras, Long> {
	List<ProximasLeituras> findByUsuario(Usuario usuario);
}
