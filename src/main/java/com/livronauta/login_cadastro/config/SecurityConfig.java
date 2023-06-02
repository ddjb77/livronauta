package com.livronauta.login_cadastro.config;


import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.livronauta.login_cadastro.models.Usuario;
import com.livronauta.login_cadastro.repository.UsuarioRepository;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;
	
	
    @Autowired
    public UsuarioRepository usuarioRepository;
    
   
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
   
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.antMatchers("/confirmacao-email").permitAll()
        	.antMatchers("/excluir/{id}").hasRole("ADMIN")
            .antMatchers("/login",  "/js/**", "/css/**", "/img/**").permitAll() //permite o acesso a todos 
            .antMatchers("/register").permitAll()//permite o acesso a todos 
            .antMatchers("/admin/**").hasRole("ADMIN")
            //permite o acesso a todos os usuários com o papel de administrador
            .antMatchers("/user/**").hasRole("USER")
            .antMatchers("/user/{id}").access("hasAnyRole('USER', 'ADMIN') and #id == authentication.principal.id")//permite o acesso a todos os usuários com o papel de usuário caso este esteja autentitacado
            .and()
            .formLogin()
            .loginPage("/login")
            .successHandler(customHandler()) 
            .permitAll()
            .and()
            .logout()
            .invalidateHttpSession(true) // invalida a sessão do usuário após o mesmo deslogar
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .permitAll()
            .and()
            .csrf().disable(); // desabilita CSRF para facilitar o desenvolvimento
    }
    
    @Bean
    public CustomHandler customHandler() { // Create a new CustomHandler bean
        return new CustomHandler();
    }
    
   
   
   
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(username -> {
            Optional<Usuario> userOptional = usuarioRepository.findByUsername(username);
            return userOptional.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        });
        return authProvider;
    }
    
   
   

        //código para envio de email//

        @Bean
        public JavaMailSender javaMailSender() {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(host);
            mailSender.setPort(port);
            mailSender.setUsername(username);
            mailSender.setPassword(password);

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");

            return mailSender;
        }
    }


