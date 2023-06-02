package com.livronauta.login_cadastro.service;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.livronauta.login_cadastro.config.CustomUserDetails;
import com.livronauta.login_cadastro.models.Usuario;
import com.livronauta.login_cadastro.repository.UsuarioRepository;


@Service
public class CustomUserDetailService implements UserDetailsService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws 
            UsernameNotFoundException {
	  
    Usuario usuario = usuarioRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuário inexistente :" + username));
    
    return new CustomUserDetails(usuario);
        
  }
  
  
  
}


  


