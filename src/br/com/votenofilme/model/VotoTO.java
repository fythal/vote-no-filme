package br.com.votenofilme.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TB_VOTOS")
public class VotoTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_VOTO")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private UsuarioTO usuario;
	
	@ManyToOne
	@JoinColumn(name="ID_FILMECOMP")
	private FilmeTO filmeComp;
	
	@ManyToOne
	@JoinColumn(name="ID_FILMEVOTO")
	private FilmeTO filmeVoto;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UsuarioTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioTO usuario) {
		this.usuario = usuario;
	}

	public FilmeTO getFilmeComp() {
		return filmeComp;
	}

	public void setFilmeComp(FilmeTO filmeComp) {
		this.filmeComp = filmeComp;
	}

	public FilmeTO getFilmeVoto() {
		return filmeVoto;
	}

	public void setFilmeVoto(FilmeTO filmeVoto) {
		this.filmeVoto = filmeVoto;
	}
}
