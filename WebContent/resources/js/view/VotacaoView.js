var votacaoView = {
	init: function(){
		this.initPopover();		
		this.initComponentEvents();
		
		votacaoController.getFilmes();		
		this.randomMovies();
	},
	
	initPopover: function(){
		$("#linkimage1").popover({ placement: "left", html: true});
		$("#linkimage2").popover({ placement: "right", html: true});
	},
	
	initComponentEvents: function(){
		$('#linkimage1').on({
			click: this.onClickImageOne
		});
		
		$('#linkimage2').on({
			click: this.onClickImageTwo
		});
		
		$('#btnSendVotes').on('click', this.onClickSendVotes);
	},
		
	randomMovies: function(){
		votacao.filmeUm = this.randomMovie('image1');
		
		votacao.filmeDois = this.randomMovie('image2');
	},
		
	randomMovie: function(idImage){
		random = SortNumber(votacao.dataMovies.length);
		
		if(random != -1){
			var imageObject = votacao.dataMovies[random];		
			votacaoView.setImagePath(imageObject.imagem, idImage);
			
			votacao.dataMovies.splice(random, 1);
			return imageObject;
		}
	},
		
	setImagePath: function(path, idImage){
		path = 'resources/images/' + path;
		idImage = '#' + idImage;
		
		$(idImage).hide().fadeIn(1000);
		
		$(idImage).attr('src', path);
	},
		
	onClickImageOne: function(){
		var printReport = false;
		
		votacao.dataVotos[votacao.dataVotos.length] = {voto: votacao.filmeUm.id, vs: votacao.filmeDois.id};
	
		if(votacao.dataMovies.length > 0){
			votacao.filmeDois = votacaoView.randomMovie('image2');
		}else{
			printReport = true;
		}
		
		if((votacao.dataMovies.length == 0) && printReport){
			$('#personInformationModal').modal({backdrop: 'static', keyboard: false});		
		}
	},
	
	onClickImageTwo: function(){
		var printReport = false;
		
		votacao.dataVotos[votacao.dataVotos.length] = {voto: votacao.filmeDois.id, vs: votacao.filmeUm.id};
			
		if(votacao.dataMovies.length > 0){
			votacao.filmeUm = votacao.filmeDois;
			votacaoView.setImagePath(votacao.filmeUm.imagem, 'image1');
			votacao.filmeDois = votacaoView.randomMovie('image2');
		}else{
			printReport = true;
		}				
		
		if((votacao.dataMovies.length == 0) && printReport){
			$('#personInformationModal').modal({backdrop: 'static', keyboard: false});		
		}
	},
		
	onFocusImageOne: function(){
		$("#linkimage1").attr("data-original-title", "<b>" + votacao.filmeUm.titulo + "</b>");
		$("#linkimage1").attr("data-content", "<b>Sinopse:</b> " + votacao.filmeUm.sinopse + 
				"<br /><b>Diretor:</b> " + votacao.filmeUm.diretor +
				"<br /><b>Ano:</b> " + votacao.filmeUm.ano);
	},
	
	onFocusImageTwo: function(){
		$("#linkimage2").attr("data-original-title", "<b>" + votacao.filmeDois.titulo + "</b>");				
		$("#linkimage2").attr("data-content", "<b>Sinopse:</b> " + votacao.filmeDois.sinopse + 
				"<br /><b>Diretor:</b> " + votacao.filmeDois.diretor +
				"<br /><b>Ano:</b> " + votacao.filmeDois.ano);
	},
	
	showMessage: function(message){
		$("#alert").html('<div class="alert alert-error"><a class="close" data-dismiss="alert">x</a><span>'+ message +'</span></div>');
	},
		
	onMessagePostVotacao: function(response){
		if(response.string == "REDIRECT"){
			votacaoView.onClickRedirectRelatorio();
		}else{
			votacaoView.showMessage(response.string + " Confira a <a href='#' onClick='votacaoView.onClickRedirectRelatorio()'>vota\u00e7\u00e3o</a>!");
			$('#personInformationModal').modal('hide');
		}
	},
	
	onClickSendVotes: function(){
		if (!($("input").jqBootstrapValidation("hasErrors"))) {
			var nome = $("#txtName").val();
			var email = $("#txtEmail").val();
			
			votacaoController.postVotacao(nome, email);
		}
	},
	
	onClickRedirectRelatorio: function(){
		var email = $("#txtEmail").val();
		
		var relURL = "filmes/relatorio/" + email;
		$(window.document.location).attr('href', relURL);
		
		votacaoView.clearParameters();
	},
	
	clearParameters: function(){
		$("#txtName").val("");
		$("#txtEmail").val("");
	}	
	
};