package com.livronauta.login_cadastro.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="tbl_usuarios")
public class Usuario {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	private String nome;
	
	
	@Column(unique=true)
	

	
	private String login;
	
	private String email;
	
	private String password;
	
	private String role;
	
	private String username;
	
	private String confirmacao;
	
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "info_usuario_id")
    private InfoUsuario infoUsuario;
	

    public InfoUsuario getInfoUsuario() {
        return infoUsuario;
    }

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LivrosLidos> livrosLidos = new ArrayList<>();
    
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Emprestimos> emprestimos = new ArrayList<>();
    
    
    public void setInfoUsuario(InfoUsuario infoUsuario) {
        if (infoUsuario == null) {
            if (this.infoUsuario != null) {
                this.infoUsuario.setUsuario(null);
            }
        } else {
            infoUsuario.setUsuario(this);
        }
        this.infoUsuario = infoUsuario;
    }
    
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProximasLeituras> proximasLeituras = new ArrayList<>();
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListaDesejos> listaDesejos = new ArrayList<>();
    
	@ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

	
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, login, nome, password, username);}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(login, other.login)
				&& Objects.equals(nome, other.nome) && Objects.equals(password, other.password) && Objects.equals(username, other.username);
	} 

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", login=" + login + ", email=" + email +" ,username =" + username +" ]";
	}
	
	
	//CÓDIGO ATUAL
	
	/*Usuario other = (Usuario) obj;
	return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(login, other.login)
			&& Objects.equals(nome, other.nome) && Objects.equals(password, other.password) && Objects.equals(username, other.username) && Objects.equals(cpf, other.cpf) && Objects.equals(telefone, other.telefone);
} 

@Override
public String toString() {
	return "Usuario [id=" + id + ", nome=" + nome + ", login=" + login + ", email=" + email +" ,username =" + username +" , cpf=" + cpf +", telefone =" + telefone + "]";
}
*/
	//

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.roles = authorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

        if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            this.role = "ROLE_ADMIN";
        } else {
            this.role = "ROLE_USER";
        }
    }

	

	public String getConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(String emailC) {
		this.confirmacao = emailC;
	}


	

	
	
}
