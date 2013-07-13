package br.com.votenofilme.json;

import java.io.Serializable;

public class VotoWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	private int voto;
	
	private int vs;

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public int getVs() {
		return vs;
	}

	public void setVs(int vs) {
		this.vs = vs;
	}
}
