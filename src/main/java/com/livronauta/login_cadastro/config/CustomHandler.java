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
     
        request.setAttribute("userId", usuario.getId()); 
        // adiciona o ID do usuário ao objeto HttpServletRequest
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        //se o usuario autenticado tiver o papel de administrador ele redirecionada para a rota admin
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            redirectStrategy.sendRedirect(request, response, "/admin/");
            
            //caso contrário redirecionada para a rota user
        } else {
            String targetUrl = "/user/" + usuario.getId();
            request.setAttribute("userId", usuario.getId());
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }
    }

}
