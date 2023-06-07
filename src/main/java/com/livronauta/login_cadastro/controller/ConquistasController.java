package com.livronauta.login_cadastro.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConquistasController {
	
	@GetMapping("/conquistas")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String getConquistasPage() {
		return "conquistas";
	}
	
	
	
}
