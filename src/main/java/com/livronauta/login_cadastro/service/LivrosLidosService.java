package com.livronauta.login_cadastro.service;

import com.livronauta.login_cadastro.config.CustomUserDetails;
import com.livronauta.login_cadastro.models.InfoUsuario;
import com.livronauta.login_cadastro.models.LivrosLidos;
import com.livronauta.login_cadastro.models.Usuario;
import com.livronauta.login_cadastro.repository.LivrosLidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class LivrosLidosService {

    private final LivrosLidosRepository livrosLidosRepository;

    @Autowired
    public LivrosLidosService(LivrosLidosRepository livrosLidosRepository) {
        this.livrosLidosRepository = livrosLidosRepository;
    }}
    
    

