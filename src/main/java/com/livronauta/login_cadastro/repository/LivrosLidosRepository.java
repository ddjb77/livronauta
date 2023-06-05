package com.livronauta.login_cadastro.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.livronauta.login_cadastro.models.LivrosLidos;
import com.livronauta.login_cadastro.models.Usuario;

@Repository
public interface LivrosLidosRepository extends JpaRepository<LivrosLidos, Long>{

	List<LivrosLidos> findByUsuario(Usuario usuario);
	
	@Query("SELECT COUNT(l) FROM LivrosLidos l WHERE l.usuario = :usuario")
	int contarLivrosLidos(@Param("usuario") Usuario usuario);
    
}
	
