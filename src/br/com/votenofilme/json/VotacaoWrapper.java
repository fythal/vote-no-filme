package br.com.votenofilme.json;

import java.io.Serializable;
import java.util.List;

public class VotacaoWrapper implements Serializable{
	private static final long serialVersionUID = 1L;

	private String nome;
	
	private String email;
	
	private List<VotoWrapper> votos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<VotoWrapper> getVotos() {
		return votos;
	}

	public void setVotos(List<VotoWrapper> votos) {
		this.votos = votos;
	}
}
