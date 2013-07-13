package br.com.votenofilme.facade;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.votenofilme.dao.VotoDAO;
import br.com.votenofilme.json.VotacaoWrapper;
import br.com.votenofilme.json.VotoWrapper;
import br.com.votenofilme.model.FilmeTO;
import br.com.votenofilme.model.UsuarioTO;
import br.com.votenofilme.model.VotoTO;

@Component
public class VotoFacade implements Serializable {
	private static final long serialVersionUID = 1L;

	private VotoDAO dao;
	private FilmeFacade filmeFacade;
	
	public VotoFacade(VotoDAO dao, FilmeFacade filmeFacade){
		this.dao = dao;
		this.filmeFacade = filmeFacade;
	}
	
	public List<VotoTO> createVotos(VotacaoWrapper votacao, UsuarioTO usuario) {		
		List<VotoWrapper> votos = votacao.getVotos();
		
		FilmeTO filme = null;
		for(VotoWrapper voto : votos){
			VotoTO votoPersist = new VotoTO();
			votoPersist.setUsuario(usuario);
			
			filme = filmeFacade.find(voto.getVoto());
			votoPersist.setFilmeVoto(filme);
			
			filme = filmeFacade.find(voto.getVs());			
			votoPersist.setFilmeComp(filme);
			
			dao.save(votoPersist);
		}
		
		
		return null;
	}
	
}
