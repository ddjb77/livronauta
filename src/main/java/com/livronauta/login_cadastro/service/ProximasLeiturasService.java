package com.livronauta.login_cadastro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livronauta.login_cadastro.repository.ProximasLeiturasRepository;

@Service
public class ProximasLeiturasService {
	private final ProximasLeiturasRepository proximasLeiturasRepository;

    @Autowired
    public ProximasLeiturasService(ProximasLeiturasRepository proximasLeiturasRepository) {
        this.proximasLeiturasRepository = proximasLeiturasRepository;
    }}


