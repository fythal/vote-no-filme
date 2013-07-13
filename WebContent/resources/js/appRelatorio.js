var app = {
	init: function(){
		app.importScripts();
		
		relatorioView.init();
	},
	
	importScripts: function(){
		var oScript = document.createElement('script');
		oScript.src = '../../resources/js/model/RelatorioModel.js';
		oScript.type = 'text/javascript';
		$("head").append(oScript);
		
		oScript = document.createElement('script');
		oScript.src = '../../resources/js/controller/RelatorioController.js';
		oScript.type = 'text/javascript';
		$("head").append(oScript);
		
		oScript = document.createElement('script');
		oScript.src = '../../resources/js/view/RelatorioView.js';
		oScript.type = 'text/javascript';
		$("head").append(oScript);
		
		oScript = document.createElement('script');
		oScript.src = '../../resources/js/model/UsuarioModel.js';
		oScript.type = 'text/javascript';
		$("head").append(oScript);
		
		oScript = document.createElement('script');
		oScript.src = '../../resources/js/controller/UsuarioController.js';
		oScript.type = 'text/javascript';
		$("head").append(oScript);
	}
		
};

window.onload = app.init;