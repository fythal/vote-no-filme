package br.com.votenofilme.model;

import java.io.Serializable;

public class FilmeRankingTO implements Serializable{
	private static final long serialVersionUID = 1L;

	public static final String RANKING_GENERAL = "SELECT COUNT(v.ID_FILMEVOTO) votos, f.ID_FILME, f.TITULO, f.SINOPSE, f.ANO, f.DIRETOR, f.IMAGEM FROM TB_FILMES f LEFT JOIN TB_VOTOS v ON v.ID_FILMEVOTO = f.ID_FILME GROUP BY f.ID_FILME, f.TITULO, f.SINOPSE, f.ANO, f.DIRETOR, f.IMAGEM ORDER BY 1 DESC";
	public static final String RANKING_BY_EMAIL = "SELECT COUNT(v.ID_FILMEVOTO) votos, f.ID_FILME, f.TITULO, f.SINOPSE, f.ANO, f.DIRETOR, f.IMAGEM FROM TB_FILMES f JOIN TB_VOTOS v ON v.ID_FILMEVOTO = f.ID_FILME JOIN TB_USUARIOS u ON u.ID_USUARIO = v.ID_USUARIO WHERE u.EMAIL = :EMAIL GROUP BY f.ID_FILME, f.TITULO, f.SINOPSE, f.ANO, f.DIRETOR, f.IMAGEM UNION SELECT 0 votos, fil.ID_FILME, fil.TITULO, fil.SINOPSE, fil.ANO, fil.DIRETOR, fil.IMAGEM FROM TB_FILMES fil WHERE fil.ID_FILME NOT IN (SELECT vo.ID_FILMEVOTO FROM TB_VOTOS vo INNER JOIN TB_USUARIOS us ON us.ID_USUARIO = vo.ID_USUARIO WHERE us.EMAIL = :EMAIL) ORDER BY 1 DESC";
	
	private FilmeTO filme;
	
	private long votos;

	public FilmeTO getFilme() {
		return filme;
	}

	public void setFilme(FilmeTO filme) {
		this.filme = filme;
	}

	public long getVotos() {
		return votos;
	}

	public void setVotos(long votos) {
		this.votos = votos;
	}
}
