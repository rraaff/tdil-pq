<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.tdil.milka.model.ClickCounter"%>
<%@page import="com.tdil.milka.web.MeltButton"%>
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<head>
<%@ include file="includes/head.jsp" %>
<link href="css/lightbox.css" rel="stylesheet" />
<script type='text/javascript' src='./js/jquery.cookie.js'></script>
<script type='text/javascript' src='./js/jquery.melt-button.js'></script>
<script type='text/javascript' src='./js/jquery.validate.min.js'></script>
<script src="./js/lightbox.js"></script>

<script>
$(document).ready(
	function(){
		$("div[id^='mb-']").each(function(indice,valor) {
		   $(valor).meltbutton();
		});
		
		function generateTooltips() {
		  //make sure tool tip is enabled for any new error label
			$("img[id*='error']").tooltip({
				showURL: false,
				opacity: 0.99,
				fade: 150,
				positionRight: true ,
				bodyHandler: function() {
					return $("#"+this.id).attr("hovertext");
				}
			});
			//make sure tool tip is enabled for any new valid label
			$("img[src*='tick.gif']").tooltip({
				showURL: false,
					bodyHandler: function() {
						return "OK";
					}
			});
		}
		
		$('form').mouseover(function(){
			      generateTooltips();
			    });
	
		$("form[name='MilkaPhotoForm']").validate({
				errorPlacement: function(error, element) {
					error.appendTo( element.parent("td").next("td") );
				},
				rules: { 'authorBean.name': {required: true},
						'authorBean.email': {required: true, email: true},
						'authorBean.acceptPolitics': {required: true},
						'photoFormFile': {required: true}
				},
				messages: {
					'authorBean.name': {required: "<img id='nameerror' src='images/unchecked.gif' hovertext='Ingrese el nombre.' />"}, 
					'authorBean.email': {required: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese el email.' />",
							email: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese un email valido.' />"},
					'authorBean.acceptPolitics': {required: "<img id='politicserror' src='images/unchecked.gif' hovertext='Debe aceptar las politicas.' />"},
					'photoFormFile': {required: "<img id='photoerror' src='images/unchecked.gif' hovertext='Seleccione una foto.' />"}
				},
				submitHandler: function() {
		            $("form[name='MilkaPhotoForm']").ajaxSubmit({
		    			type: "POST",
		    			url: "./uploadMilkaPhoto.do",
		    			dataType: "json",
		    			success: postRegisto
		    			});
		        }
			});
			
			$( "#dialog-form" ).dialog({
				autoOpen: false,
				height: 300,
				width: 350,
				modal: true
			});
			$( "#create-user" )
			.button()
			.click(function() {
				$( "#dialog-form" ).dialog( "open" );
			});
		
	}
);

function postRegisto(data) {
	$( "#dialog:ui-dialog" ).dialog( "destroy" );
		$( "#dialog-modal" ).dialog({
			height: 140,
			modal: true
		});
}
</script>
<style>
#bm_me_derrite {
	width:108px;
	height:41px;
	background:url(images/barra/me-derrite.png) no-repeat;
	margin-right: 50px;
}
#bm_personas{
	width:180px;
	height:20px;
	color:#FFF;
	font-family:Arial, Helvetica, sans-serif;
	font-size:11px;
	top:14px;
	left: 280px;
}
#bm_personas span{
	color:#b398ff;
}

#dialog-modal {
	display: none;
}
</style>
</head>
<body>

<%= MeltButton.meltButton(1) %>

<%= MeltButton.meltButton(2) %>

<br><br><br><br><br>

<div id="dialog-modal" title="Tu foto milka">
	<p>
		Gracias por subir tu foto.<br>
		Te avisaremos cuando este aprobada.
	</p>
</div>
<br>

<div id="dialog-form" title="Create new user">
	<p class="validateTips">All form fields are required.</p>

	<form>
	<fieldset>
		<label for="name">Name</label>
		<input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" />
		<label for="email">Email</label>
		<input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all" />
		<label for="password">Password</label>
		<input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all" />
	</fieldset>
	</form>
</div>

<button id="create-user">Create new user</button>
<br>

<a href="./images/video2.jpg" rel="lightbox">Imagen 1</a>

Milka Photo<br>
<html:form method="POST" action="/uploadMilkaPhoto" enctype="multipart/form-data">
	<table>
		<tr>
			<td>Nombre:<html:text name="MilkaPhotoForm" property="authorBean.name" styleClass="width180"/></td>
			<td width="25" id="authorBean.nameerr"></td>
		</tr>
		<tr>
			<td>email:<html:text name="MilkaPhotoForm" property="authorBean.email" styleClass="width180"/></td>
			<td width="25" id="authorBean.emailerr"></td>
		</tr>
		<tr>
			<td>Politicas:<html:checkbox name="MilkaPhotoForm" property="authorBean.acceptPolitics" styleClass="width180"/></td>
			<td width="25" id="authorBean.acceptPoliticserr"></td>
		</tr>
		<tr>
			<td><html:file name="MilkaPhotoForm" property="photoFormFile" /></td>
			<td width="25" id="photoFormFileerr"></td>
		</tr>
		<tr>
			<td>
				<html:submit property="operation">Upload</html:submit>
			</td>
		</tr>
	</table>	
</html:form>
	
Post-It<br>
<html:form method="POST" action="/addPostIt">
	NOmbre:<html:text name="PostItForm" property="authorBean.name" styleClass="width180"/><br>
	email:<html:text name="PostItForm" property="authorBean.email" styleClass="width180"/><br>
	Politicas:<html:checkbox name="PostItForm" property="authorBean.acceptPolitics" styleClass="width180"/><br>
	Texto:<html:text name="PostItForm" property="text" styleClass="width180"/><br>
	<html:submit property="operation">
		Salvar
	</html:submit>
</html:form>

uploadMailToParent
<html:form method="POST" action="/uploadMailToParent" enctype="multipart/form-data">
	NOmbre:<html:text name="MailToParentForm" property="authorBean.name" styleClass="width180"/><br>
	email:<html:text name="MailToParentForm" property="authorBean.email" styleClass="width180"/><br>
	Politicas:<html:checkbox name="MailToParentForm" property="authorBean.acceptPolitics" styleClass="width180"/><br>
	<html:file name="MailToParentForm" property="photoFormFile" />
	<html:submit property="operation">
		Upload
	</html:submit>
</html:form>
</body>
</html>