package br.com.votenofilme.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="TB_USUARIOS")
@NamedQuery(name="UsuarioTO.findUserByEmail", query="SELECT u FROM UsuarioTO u WHERE u.email = :email")
public class UsuarioTO implements Serializable{
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_EMAIL = "UsuarioTO.findUserByEmail"; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_USUARIO")
	private int id;
	
	private String email;
	
	private String nome;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
