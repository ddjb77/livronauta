package com.livronauta.login_cadastro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuizController {
	
	@GetMapping("/quiz-page")
	public String getQuizPage() {
		return "quiz";
	}}
	
	
	/*@GetMapping("/userpage/profile")
    public String showUserProfile() {
        return "user/profile"; // Substitua "user/profile" pelo caminho correto do template da página do perfil
    }}

*/