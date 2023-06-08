package com.livronauta.login_cadastro.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.livronauta.login_cadastro.models.Usuario;
import com.livronauta.login_cadastro.repository.UsuarioRepository;

public class CustomHandler implements AuthenticationSuccessHandler{

	@Autowired
    private UsuarioRepository usuarioRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	        Authentication authentication) throws IOException, ServletException {

	    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    Usuario usuario = usuarioRepository.findByLogin(userDetails.getUsername())
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    request.setAttribute("userId", usuario.getId()); // opcional, caso precise do ID em outros lugares
	    request.setAttribute("userLogin", usuario.getLogin()); // atribui o login do usuário ao objeto HttpServletRequest

	    RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	    if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
	        redirectStrategy.sendRedirect(request, response, "/admin/");
	    } else {
	        redirectStrategy.sendRedirect(request, response, "/user/profile");
	    }
	}
}
