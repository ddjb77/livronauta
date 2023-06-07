package com.livronauta.login_cadastro.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="info_usuario")
public class InfoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    // Resto da classe...
    @Column(name = "nome")
    private String nome;
    
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getLivroAtual() {
		return livroAtual;
	}
	public void setLivroAtual(String livroAtual) {
		this.livroAtual = livroAtual;
	}
	public int getPaginasTotais() {
		return paginasTotais;
	}
	public void setPaginasTotais(int paginasTotais) {
		this.paginasTotais = paginasTotais;
	}
	public int getNumeroPaginas() {
		return numeroPaginas;
	}
	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	@Column(unique=true, name="cpf")
	private String cpf;
	@Column(name = "telefone")
    private String telefone;
    private String livroAtual;
    private int paginasTotais;
    private String genero;
    private int numeroPaginas;

	@Override
	public String toString() {
		return "InfoUsuario [id=" + id + ", usuario=" + usuario + ", nome=" + nome + ", cpf=" + cpf + ", telefone="
				+ telefone + ", livroAtual=" + livroAtual + ", paginasTotais=" + paginasTotais + ", genero=" + genero
				+ ", numeroPaginas=" + numeroPaginas + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(nome, cpf, genero, id, livroAtual, paginasTotais, numeroPaginas, telefone, usuario);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InfoUsuario other = (InfoUsuario) obj;
		return Objects.equals(nome, other.nome) && Objects.equals (cpf, other.cpf) && Objects.equals(genero, other.genero) && Objects.equals(id, other.id)
				&& Objects.equals(livroAtual, other.livroAtual) && Objects.equals(paginasTotais, other.paginasTotais)
				&& numeroPaginas == other.numeroPaginas && Objects.equals(telefone, other.telefone)
				&& Objects.equals(usuario, other.usuario);
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

    // getters e setters
}