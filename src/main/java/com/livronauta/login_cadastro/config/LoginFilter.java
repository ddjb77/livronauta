/*package com.livronauta.login_cadastro.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginFilter extends OncePerRequestFilter {
	
	private final AuthenticationManager authenticationManager;
	
	public LoginFilter() {
		authenticationManager = new AuthenticationManager() {
			
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				return null;
			}
		};}
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		var username = request.getHeader("login");
		var password = request.getHeader("password");

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password));
		
		response.setHeader(HttpHeaders.AUTHORIZATION, username);
		
	}
	

	
	
	@Override 
	
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		
		var method = request.getMethod();
		var uri = request.getRequestURI();
		
		var isLogin = HttpMethod.POST.matches(method) && uri.startsWith("/login");
		return !isLogin;
	
		
	}

}
*/