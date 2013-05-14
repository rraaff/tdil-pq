<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="contacto"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Contacto</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<script>
$(document).ready(
	function(){

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

			$("form[name='ContactForm']").validate({
					errorPlacement: function(error, element) {
						error.appendTo( element.parent("td").next("td") );
					},
					rules: { 'name': {required: true},
							'email': {required: true, email: true},
							'content': {required: true}
					},
					messages: {
						'name': {required: "<img id='nameerror' src='images/unchecked.gif' hovertext='Ingrese el nombre.' />"},
						'email': {required: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese el email.' />",
								email: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese un email valido.' />"},
						'content': {required: "<img id='politicserror' src='images/unchecked.gif' hovertext='Ingrese el contenido.' />"}
					},
					submitHandler: function() {
			            $("form[name='ContactForm']").ajaxSubmit({
			    			type: "POST",
			    			url: "./contact.do",
			    			dataType: "json",
			    			success: postContact
			    			});
			        }
				});
		}
	);

function postContact(data) {
	if (data.result == 'OK') {
		$( "#dialog-modal" ).dialog({
				height: 140,
				modal: true,
				close: function(event, ui) {  document.location.href='./index.jsp'; }
			});
	} else {
		$( "#dialog-modal-err" ).dialog({
				height: 140,
				modal: true
			});
	}
}
</script>
<style>
input[type=button].buttonContactForm,
input[type=submit].buttonContactForm {
	border:none;
	-webkit-border-radius: 0px;
	-moz-border-radius: 0px;
	border-radius: 0px;
	background:none;
	box-shadow: none; /* FF, Chrome, Safari */
	background-image: none;
	background-image: none;
	-o-box-shadow: none;
	
	/*end reset*/
	background-image: url(images/skin_basic/buttons/contactFormSubmit_off.png);
	width: 169px;
	height: 65px;
	margin:0 auto;
	cursor:pointer;
}
input[type=button].buttonContactForm:hover,
input[type=submit].buttonContactForm:hover {
	background-image: url(images/skin_basic/buttons/contactFormSubmit_on.png);
	cursor:pointer;
}
</style>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<div id="titleArea">
			<h1>CONTACTO</h1>
			<h2>Envianos tus preguntas. Te contestaremos a la brevedad.</h2>
		</div>
		<div id="formContent" class="height450">
			<div id="leftBar">
				<p>Envianos tu pregunta, duda o sugerencia a trav&eacute;s de este formulario. Existe una p&aacute;gina con preguntas frecuentes donde contestamos las dudas m&aacute;s repetidas de los usuarios. &iquest;Ya las revisaste?</a></p>
				<div style="padding-top:115px;">
					<h2 style="padding-left:0;">Seguinos en</h2>
					<div style="margin-right:25px; float:left;"><a href="http://www.facebook.com/TuaFesta" target="_blank"><img src="images/skin_basic/buttons/facebookPlaca.gif" alt="Seguinos en Facebook" /></a></div>
					<div style="float:left;"><a href="https://twitter.com/TuaFesta" target="_blank"><img src="images/skin_basic/buttons/twitterPlaca.gif" alt="Seguinos en Twitter" width="184" height="121" /></a></div>
				</div>
			</div>
			<div id="rightBar">
				<div id="formWrapper">
				<html:form method="POST" action="/contact">
					<div class="myRow">
						<div class="myLabel width60">Nombre</div>
						<div class="myLabel width400"><html:text name="ContactForm" property="name" styleClass="input_form width400"/></div>
					</div>
					<div class="myRow" style="padding-bottom:2px;">
						<div class="myLabel width60">E-Mail</div>
						<div class="myLabel width400"><html:text name="ContactForm" property="email" styleClass="input_form width400"/></div>
					</div>
					<div class="myRow">
						<div class="myLabel width450"><span class="comment" style="color:#333333;">(Si es usuario del sitio utilice el mismo E-Mail que us&oacute; para registrarse)</span></div>
					</div>
					<div class="myRow">
						<div class="myLabel width60">Texto</div>
						<div class="myLabel width400 height100"><html:textarea name="ContactForm" property="content" styleClass="comment_form width400" cols="45" rows="5" /></div>
					</div>
					<div class="myRow" align="center"><html:submit property="operation" styleClass="buttonContactForm">&nbsp;</html:submit></div>
					</html:form>
				</div>
			</div>
		</div>
	</div>
</div>
	<div id="dialog-modal" class="hide" title="Contacto">Gracias por su mensaje.</div>
	<div id="dialog-modal-err" class="hide" title="Contacto">Ha ocurrido un error, intentelo nuevamente.</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>