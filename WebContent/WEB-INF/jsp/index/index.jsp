<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>Vote no Filme!</title>
	
	<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen" />
	<link href="resources/css/styleIndex.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="resources/js/jquery-1.10.1.js"></script>
	<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/bootstrap/js/bootstrap-tooltip.js"></script>
	<script type="text/javascript" src="resources/bootstrap/js/bootstrap-popover.js"></script>
	<script type="text/javascript" src="resources/bootstrap/js/jqBootstrapValidation.js"></script>
	<script type="text/javascript" src="resources/js/utils/Validator.js"></script>
	<script type="text/javascript" src="resources/js/appVotacao.js"></script>
	
	
</head>
<body>
	<div id="alert">
	</div>

	<div id="header">
		<label class="lblHeader">Vote em seus filmes prediletos!</label>
	</div>	

	<div id="content">			 
		<ul id="movies" class="thumbnails">
		  <li class="span4">
		    <a id="linkimage1" onmouseover="votacaoView.onFocusImageOne()" href="#" class="btn thumbnail"  rel="popover" >
		      <img id="image1" class="imgMovie" src="" alt="" />
		    </a>
		  </li>	  
	
		  <li>
		  	<div id="versus">
		  		<img height="50" src="resources/images/vs.png" alt="" />
		  	</div>
		  </li>
		  <li class="span4">	  	
		    <a id="linkimage2" onmouseover="votacaoView.onFocusImageTwo()" href="#" class="btn thumbnail" rel="popover" >
		      <img id="image2" class="imgMovie" src="" alt="" />
		    </a>
		  </li>
		</ul>	  		   
	</div>
	
	<div id="personInformationModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">		
		<div class="modal-header">
			<h3 id="modalLabel">Envie seus votos</h3>
		</div>
		<form id="formPersonInformation"  class="form-horizontal">
			<div id="formDiv" class="modal-body">			
				<fieldset>
					<div class="control-group">
							<label class="control-label" for="txtName">Nome:</label>			  
	                    <div class="controls">					  	
							<input id="txtName" name="txtName" type="text" placeholder="Ex: Stanley Kubrick" class="input-xlarge" maxlength="30" 
							required minlength="2" data-validation-minlength-message="Nome de 2 à 30 caracteres"  >			  
					  	</div>					  					  
					</div>
					
					<br />
					
					<div class="control-group">
						<label class="control-label" for="txtEmail">E-mail:</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-envelope"></i></span>
								<input id="txtEmail" name="txtEmail" type="email" placeholder="Ex: stanley.kubrick@kubrick.com" class="input-xlarge" 
								required data-validation-email-message="Insira um e-mail válido">
							</div>
						</div>
					</div>
				</fieldset>				
			</div>
		</form>
		<div class="modal-footer">
			<button id="btnSendVotes" class="btn" class="btn btn-primary" data-loading-text="Loading...">Enviar</button>
		</div>
	</div>
				
</body>
</html>