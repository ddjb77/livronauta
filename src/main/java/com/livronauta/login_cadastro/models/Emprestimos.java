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
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="emprestimos")
public class Emprestimos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	@Override
	public String toString() {
		return "Emprestimos [id=" + id + ", usuario=" + usuario + ", livro=" + livro + ", autor=" + autor + ", genero="
				+ genero + ", pessoa=" + pessoa + ", data=" + data + ", dataDevol=" + dataDevol + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(autor, data, dataDevol, genero, id, livro, pessoa, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emprestimos other = (Emprestimos) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(data, other.data)
				&& Objects.equals(dataDevol, other.dataDevol) && Objects.equals(genero, other.genero)
				&& Objects.equals(id, other.id) && Objects.equals(livro, other.livro)
				&& Objects.equals(pessoa, other.pessoa) && Objects.equals(usuario, other.usuario);
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
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPessoa() {
		return pessoa;
	}

	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	private String livro;
	
	private String autor;
	
	private String genero;
	
	private String pessoa;
	
	private String data;
	
	private String dataDevol;

	public String getDataDevol() {
		return dataDevol;
	}

	public void setDataDevol(String dataDevol) {
		this.dataDevol = dataDevol;
	}
	
	
}