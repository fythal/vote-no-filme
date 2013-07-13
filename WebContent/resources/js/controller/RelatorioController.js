var relatorioController = {
	getPersonData: function(){
		$.ajax({ 
		    url: '../../filmes/ranking/person.json', 
		    dataType: 'json', 
		    data: null, 
		    async: false, 
		    success: function(data){ 
		    	relatorio.personData = data;
		    } 
		});				
	},
	
	getGeneralData: function(){
		$.ajax({ 
		    url: '../../filmes/ranking/general.json', 
		    dataType: 'json', 
		    data: null, 
		    async: false, 
		    success: function(data){ 
		    	relatorio.generalData = data;
		    } 
		});
		
	}
};