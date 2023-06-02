/*package com.livronauta.login_cadastro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


public class SpringSecurityConfig{
	
	@Autowired
	@Lazy
	//private LoginFilter loginFilter;
	
	
	@Bean 
	public UserDetailsService userDetailsService() {
		
		var userDetail = new InMemoryUserDetailsManager();
		
		userDetail.createUser(User.builder().username("user").password("{noop}user").roles("USER").build());
		userDetail.createUser(User.builder().username("admin").password("{noop}admin").roles("ADMIN", "USER").build());

		
		return userDetail;
		
	}
	
	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
		
		
		var dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(userDetailsService);
		return new ProviderManager(dao);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.authorizeRequests().
		antMatchers("/js/*", "/css/*").permitAll().anyRequest().authenticated();
		//http.addFilterAt(loginFilter, BasicAuthenticationFilter.class);
		return http.build();
	}

}
*/
