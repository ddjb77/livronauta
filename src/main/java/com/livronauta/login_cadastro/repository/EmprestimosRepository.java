package com.livronauta.login_cadastro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livronauta.login_cadastro.models.Emprestimos;
import com.livronauta.login_cadastro.models.Usuario;

@Repository
public interface EmprestimosRepository extends JpaRepository<Emprestimos, Long> {
	
	List<Emprestimos> findByUsuario(Usuario usuario);
}
