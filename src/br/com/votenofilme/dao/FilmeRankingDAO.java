package br.com.votenofilme.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.ioc.Component;
import br.com.votenofilme.model.FilmeRankingTO;
import br.com.votenofilme.model.FilmeTO;

@Component
public class FilmeRankingDAO extends FilmeDAO {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	public List<FilmeRankingTO> rankingGeneral(){
		List list = findManyResultByNativeQuery(FilmeRankingTO.RANKING_GENERAL, null);
						
		return listToRanking(list);
	}
	
	@SuppressWarnings("rawtypes")
	public List<FilmeRankingTO> rankingByEmail(String email){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("EMAIL", email);
		
		List list = findManyResultByNativeQuery(FilmeRankingTO.RANKING_BY_EMAIL, parameters);
						
		return listToRanking(list);
	}
	
	@SuppressWarnings("rawtypes")
	private List<FilmeRankingTO> listToRanking(List list){
		List<FilmeRankingTO> filmesRanking = new ArrayList<FilmeRankingTO>();
		FilmeRankingTO filmeRanking = null;
		for(Object object : list){
			filmeRanking = createFilmeRanking((Object[]) object);
			filmesRanking.add(filmeRanking);
		}
		
		return filmesRanking;
	}

	private FilmeRankingTO createFilmeRanking(Object[] objects){ 
		FilmeTO filme = new FilmeTO();
		filme.setId((Integer) objects[1]);
		filme.setTitulo((String) objects[2]);
		filme.setSinopse((String) objects[3]);
		filme.setAno((Integer) objects[4]);
		filme.setDiretor((String) objects[5]);
		filme.setImagem((String) objects[6]);
		
		FilmeRankingTO filmeRanking = new FilmeRankingTO();
		filmeRanking.setFilme(filme);
		
		BigInteger votos = (BigInteger) objects[0];
		filmeRanking.setVotos(votos.longValue());
		
		return filmeRanking;
	}
	
}
