/***
 * Copyright (c) 2009 Caelum - www.caelum.com.br/opensource
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.votenofilme.controller;

import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.votenofilme.dao.FilmeDAO;
import br.com.votenofilme.data.PersistFilmes;
import br.com.votenofilme.facade.FilmeFacade;
import br.com.votenofilme.model.FilmeTO;

@ApplicationScoped
@Resource
public class IndexController {

	private final FilmeFacade facade;
	
	private List<FilmeTO> filmes;

	public IndexController() {
		this.facade = new FilmeFacade(new FilmeDAO());
		
		initFilmes();
	}	

	@Path("/")
	public void index() {
		
	}

	private void initFilmes(){
		if(filmes == null){
			filmes = PersistFilmes.persistFilmes();
			
			for(FilmeTO filme : filmes){
				facade.create(filme);
			}
		}
	}
}
