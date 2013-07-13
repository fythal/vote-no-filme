package br.com.votenofilme.test;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import br.com.votenofilme.dao.FilmeDAO;
import br.com.votenofilme.facade.FilmeFacade;
import br.com.votenofilme.model.FilmeTO;

public class TestFilmePersist extends TestCase{
	
	private FilmeFacade facade;
	
	@Before
	public void setUp() throws Exception{
		facade = new FilmeFacade(new FilmeDAO());
	}

	@Test
	public void testCreateFilmeTO() {
		FilmeTO filme = new FilmeTO();
		filme.setTitulo("Blade Runner");
		filme.setSinopse("The film depicts a dystopian Los Angeles in November 2019 in " +
				"which genetically engineered organic robots called replicants—visually " +
				"indistinguishable from adult humans—are manufactured by the powerful Tyrell [...]");
		filme.setImagem("bladerunner.jpg");
		
		facade.create(filme);		
		
		List<FilmeTO> filmes = facade.listAll();
		
		assertEquals(1, filmes.size());
	}

}
