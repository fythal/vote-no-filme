package br.com.votenofilme.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import br.com.votenofilme.dao.FilmeDAO;
import br.com.votenofilme.dao.FilmeRankingDAO;
import br.com.votenofilme.dao.UsuarioDAO;
import br.com.votenofilme.dao.VotoDAO;
import br.com.votenofilme.data.PersistFilmes;
import br.com.votenofilme.facade.FilmeFacade;
import br.com.votenofilme.facade.FilmeRankingFacade;
import br.com.votenofilme.facade.UsuarioFacade;
import br.com.votenofilme.facade.VotoFacade;
import br.com.votenofilme.json.VotacaoWrapper;
import br.com.votenofilme.json.VotoWrapper;
import br.com.votenofilme.model.FilmeRankingTO;
import br.com.votenofilme.model.FilmeTO;
import br.com.votenofilme.model.UsuarioTO;

public class TestRelatorioRanking extends TestCase{
	
	private FilmeRankingFacade facade;
	private FilmeFacade filmeFacade;
	private VotoFacade votoFacade;
	private UsuarioFacade usuarioFacade;
	
	private void initFacades(){
		facade = new FilmeRankingFacade(new FilmeRankingDAO());
		filmeFacade = new FilmeFacade(new FilmeDAO());
		votoFacade = new VotoFacade(new VotoDAO(), filmeFacade);
		usuarioFacade = new UsuarioFacade(new UsuarioDAO());
	}
	
	private UsuarioTO createUser(){
		UsuarioTO usuario = new UsuarioTO();
		usuario.setNome("Stanley Kubrick");
		usuario.setEmail("stanley.kubick@kubrick.com");
		
		try {
			usuario = usuarioFacade.create(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	private UsuarioTO createOtherUser(){
		UsuarioTO usuario = new UsuarioTO();
		usuario.setNome("Stephen King");
		usuario.setEmail("stephen.king@king.com");
		
		try {
			usuario = usuarioFacade.create(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	private void createVotos(UsuarioTO usuario){
		FilmeTO filmeVotoUm = filmeFacade.find(1);
		FilmeTO filmeVotoDois = filmeFacade.find(2);
		FilmeTO filmeVotoTres = filmeFacade.find(3);
		
		List<VotoWrapper> votos = new ArrayList<VotoWrapper>();
				
		VotoWrapper voto = new VotoWrapper();
		voto.setVoto(filmeVotoDois.getId());
		voto.setVs(filmeVotoUm.getId());
		
		VotoWrapper votoDois = new VotoWrapper();
		votoDois.setVoto(filmeVotoDois.getId());
		votoDois.setVs(filmeVotoTres.getId());
		
		VotoWrapper votoTres = new VotoWrapper();
		votoTres.setVoto(filmeVotoTres.getId());
		votoTres.setVs(filmeVotoDois.getId());				
		
		votos.add(voto);
		votos.add(votoDois);
		votos.add(votoTres);
		
		VotacaoWrapper votacao = new VotacaoWrapper();
		votacao.setVotos(votos);
		
		votoFacade.createVotos(votacao, usuario);
	}
	
	@Before
	public void setUp() throws Exception{
		initFacades();
		
		UsuarioTO usuario = createUser();
		
		List<FilmeTO> filmes = PersistFilmes.persistFilmes();
			
		for(FilmeTO filme : filmes){
			filmeFacade.create(filme);
		}
		
		createVotos(usuario);		
	}

	
	@Test
	public void testRankingGeneralFilmeTO() {
		List<FilmeRankingTO> filmesRanking = facade.rankingGeneral();
		
		assertEquals(5, filmesRanking.size());
		assertEquals(2, filmesRanking.get(0).getFilme().getId());
		assertEquals(3, filmesRanking.get(1).getFilme().getId());
	}

	
	
	@Test
	public void testRakingByUser(){
		createVotos(createOtherUser());
		
		List<FilmeRankingTO> filmesRanking = facade.rankingGeneralByEmail("stanley.kubick@kubrick.com");
		
		assertEquals(5, filmesRanking.size());
		assertEquals(2, filmesRanking.get(0).getFilme().getId());
		assertEquals(3, filmesRanking.get(1).getFilme().getId());
	}

		
	

}
