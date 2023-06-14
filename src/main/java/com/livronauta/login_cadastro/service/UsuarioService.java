package com.livronauta.login_cadastro.service;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.livronauta.login_cadastro.models.InfoUsuario;
import com.livronauta.login_cadastro.models.Usuario;
import com.livronauta.login_cadastro.repository.InfoUsuarioRepository;
import com.livronauta.login_cadastro.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private InfoUsuarioRepository infoUsuarioRepository;
    
    
    
    @Autowired
    private EmailService emailService;

  
    //registra o usuário com as informações necessárias//

    public Usuario registrarUsuario(String nome, String login, String email, String password, String username) {
        if (nome == null || login == null || password == null || email == null || username == null) {
            return null;
        } else {
            if (usuarioRepository.findFirstByLogin(login).isPresent() || usuarioRepository.findByEmail(email).isPresent()) {
                System.out.println("usuário duplicado!");
                
                return null;
            }
            
  
            //cria um novo usuario
            Usuario usuario = new Usuario();
            
            //pega as informações do usuário
            usuario.setNome(nome);
            usuario.setLogin(login);
            usuario.setEmail(email);
            usuario.setPassword(passwordEncoder.encode(password)); // senha é armazenada criptografada
            usuario.setUsername(username);
            
            String codigoConfirmacao = gerarCodigo();
            usuario.setConfirmacao(codigoConfirmacao);
            
         // define a autoridade do usuário como "ROLE_USER"
            List<Usuario> usuarios = usuarioRepository.findAll();
            if (usuarios.size() == 0) {
                usuario.setRole("ROLE_ADMIN");
            } else {
                usuario.setRole("ROLE_USER");
            }
            usuario = usuarioRepository.save(usuario);
            
            try {
            	enviarEmailConfirmacao(usuario.getEmail(), usuario.getConfirmacao());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return usuario;
    }
    
    }
    
	
 public boolean alterarSenha(String email, String novaSenha, String confirmarSenha) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);
        if (optionalUsuario.isPresent() && novaSenha.equals(confirmarSenha)) {
            Usuario usuario = optionalUsuario.get();
            usuario.setPassword(passwordEncoder.encode(novaSenha)); // senha é armazenada criptografada
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }
    
    public List<Usuario> findAllUsuarios() {
		return usuarioRepository.findAll();
		
	}
    
    private String gerarCodigo() {
    	return null;
        // lógica para gerar um código de confirmação aleatório
    }

    public void enviarEmailConfirmacao(String destinatario, String emailC) throws MessagingException {
        // código para enviar o email de confirmação usando o objeto emailService injetado
        emailService.enviarConfirmacaoEmail(destinatario, emailC);
    }
    
    @Transactional
    public Usuario salvarInformacoes(Long id, String cpf, String telefone, String nome) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();

            InfoUsuario infoUsuario = usuario.getInfoUsuario();
            if (infoUsuario == null) {
                infoUsuario = new InfoUsuario();
                infoUsuario.setUsuario(usuario);
                usuario.setInfoUsuario(infoUsuario);
            }

            infoUsuario.setCpf(cpf);
            infoUsuario.setNome(nome);
            infoUsuario.setTelefone(telefone);

            // Salva o InfoUsuario primeiro para gerar o ID
            infoUsuarioRepository.save(infoUsuario);

            // Atualiza a associação no objeto Usuario
            usuario.setInfoUsuario(infoUsuario);

            // Salva o Usuario
            usuarioRepository.save(usuario);

            return usuario; // Retorna o objeto Usuario atualizado
        } else {
            // Usuário não encontrado, pode tratar esse caso de acordo com sua necessidade
            return null;
        }
    }



   

 
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return new User(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities());
    }
    
    public Usuario authenticate(String login, String password) {
        System.out.println("login: " + login + ", password: " + password);
        UserDetails userDetails = loadUserByUsername(login);
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return usuarioRepository.findByLogin(login).orElse(null);
        }
        return null;
    }
    
    


	
	

}
