package com.livronauta.login_cadastro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livronauta.login_cadastro.repository.EmprestimosRepository;
import com.livronauta.login_cadastro.repository.LivrosLidosRepository;

@Service
public class EmprestimosService {
	
	private final EmprestimosRepository emprestimosRepository;

    @Autowired
    public EmprestimosService(EmprestimosRepository emprestimosRepository) {
        this.emprestimosRepository = emprestimosRepository;
    }}

