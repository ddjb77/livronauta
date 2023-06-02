package com.livronauta.login_cadastro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity

public class LoginCadastroApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginCadastroApplication.class, args);
	}
	

	
	
}
