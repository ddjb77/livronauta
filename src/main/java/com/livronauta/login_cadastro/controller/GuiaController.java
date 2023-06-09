package com.livronauta.login_cadastro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuiaController {
	
	@GetMapping("/guia-page")
	public String getGuiaPage() {
		return "guia";
	}
	
	
	@GetMapping("/forum-page")
	public String getForumPage() {
		return "forum";
	}

}
