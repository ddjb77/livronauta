package com.livronauta.login_cadastro.models;


import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="lista_desejos")
public class ListaDesejos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
	
	private String livro;
	
	@Override
	public int hashCode() {
		return Objects.hash(autor, genero, id, livro, preco, site, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListaDesejos other = (ListaDesejos) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(genero, other.genero)
				&& Objects.equals(id, other.id) && Objects.equals(livro, other.livro)
				&& Objects.equals(preco, other.preco) && Objects.equals(site, other.site)
				&& Objects.equals(usuario, other.usuario);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getLivro() {
		return livro;
	}

	public void setLivro(String livro) {
		this.livro = livro;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "ListaDesejos [id=" + id + ", usuario=" + usuario + ", livro=" + livro + ", autor=" + autor + ", genero="
				+ genero + ", site=" + site + ", preco=" + preco + "]";
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	private String autor;
	
	private String genero;
	
	private String site;
	
	private BigDecimal preco;
}