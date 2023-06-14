package com.livronauta.login_cadastro.models;

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
import lombok.NoArgsConstructor;

@Entity
@Table(name="proximas_leituras")
@NoArgsConstructor
@AllArgsConstructor
public class ProximasLeituras {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // ...

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

	private String livro;
	
	private String autor;
	
	private String genero;
	
	private int ordem;
	
	private int numeroPaginas;

	@Override
	public int hashCode() {
		return Objects.hash(autor, genero, id, livro, numeroPaginas, ordem, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProximasLeituras other = (ProximasLeituras) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(genero, other.genero)
				&& Objects.equals(id, other.id) && Objects.equals(livro, other.livro)
				&& numeroPaginas == other.numeroPaginas && ordem == other.ordem
				&& Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "ProximasLeituras [id=" + id + ", usuario=" + usuario + ", livro=" + livro + ", autor=" + autor
				+ ", genero=" + genero + ", ordem=" + ordem + ", numeroPaginas=" + numeroPaginas + "]";
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	
}
