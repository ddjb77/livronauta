package com.livronauta.login_cadastro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuizController {
	
	@GetMapping("/quiz-page")
	public String getQuizPage() {
		return "quiz";
	}
}
