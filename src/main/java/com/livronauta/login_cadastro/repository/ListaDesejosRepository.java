package com.livronauta.login_cadastro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.livronauta.login_cadastro.models.ListaDesejos;
import com.livronauta.login_cadastro.models.ProximasLeituras;
import com.livronauta.login_cadastro.models.Usuario;

@Repository
public interface ListaDesejosRepository  extends JpaRepository<ListaDesejos, Long>{
	List<ListaDesejos> findByUsuario(Usuario usuario);
	
	@Query("SELECT COUNT(l) FROM ListaDesejos l WHERE l.usuario = :usuario")
	int contarListaDesejos(@Param("usuario") Usuario usuario);


}
