<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.tdil.milka.model.ClickCounter"%>
<%@page import="com.tdil.milka.web.MeltButton"%>
<%@page import="com.tdil.milka.web.MilkaErrorFormatter"%>
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<head>
<%@ include file="includes/head.jsp" %>
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='./js/jquery.cookie.js'></script>
<script type='text/javascript' src='./js/jquery.melt-button.js'></script>
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
		
			$("form[name='EmailEndingForm']").validate({
					errorPlacement: function(error, element) {
						error.appendTo( element.parent("div"));
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
			            $("form[name='EmailEndingForm']").ajaxSubmit({
			    			type: "POST",
			    			url: "./uploadEmailEnding.do",
			    			dataType: "json",
			    			success: postUpload
			    			});
			        }
				});
		}
	);

function postUpload(data) {
	if (data.result == 'OK') {
		$( "#dialog-modal" ).dialog({
				height: 140,
				modal: true,
				close: function(event, ui) {  document.location.href='./finalesDeEmail.jsp?dnc=true'; }
			});
	} else {
		$( "#dialog-modal-err" ).dialog({
				height: 140,
				modal: true
			});
	}
}
</script>
<script type="text/javascript">
	var _gaq = _gaq || [];
	_gaq.push(['_setAccount', 'UA-32381287-1']);
	_gaq.push(['_trackPageview']);
	(function() {
		var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	})();
</script>
<%@ include file="includes/boErrorJS.jsp" %>

<style>
body {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #1e1e1e;
	background-image: url(images/experiencias/finalesEmails/bg_carga.gif);
	background-repeat: no-repeat;
	background-position: center top;
	height:100%;
	overflow:hidden;
}
#formularioEmails {
	width: 390px;
	margin-top: 176px;
	margin-right: auto;
	margin-bottom: 0;
	margin-left: auto;
}
#lineadecontenido {
	width: 302px;
	margin-left:88px;
	margin-bottom:5px;
	float:left;
}
.specialFields {
	width:229px;
	height:19px;
	border:solid 1px #9d9fa1;
}
#textoEspecial {
	top:40px;
	left:20px;
	position:relative;
	float:left;	
	width:360px;
}
.spacer{
	width:100px;
}
#buttonHolder .okCircle, .okCircle a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 1px;
	line-height:14px;
	color: #FFFFFF;
	background:transparent;
	text-decoration: none;
	background-image: url(images/experiencias/finalesEmails/boton.gif);
	background-repeat: no-repeat;
	background-position: center center;
	width:49px;
	height:48px;
	border:none;
	cursor:hand;
	margin:0;
	padding:0;
	top:-52px;
	left:20px;
	position:relative;
	
	-webkit-border-radius: 0;
	-moz-border-radius: 0;
	border-radius: 0;
	text-shadow: none;
	box-shadow: none;
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	-o-box-shadow: none;
}
</style>
</head>
<body>
<div id="formularioEmails">
	<html:form method="POST" action="/uploadEmailEnding" enctype="multipart/form-data">
		<div id="lineadecontenido"><html:text name="EmailEndingForm" property="authorBean.name" styleClass="specialFields"/><%=MilkaErrorFormatter.getErrorFrom(request, "Author.name.err")%></div>
		<div id="lineadecontenido"><html:text name="EmailEndingForm" property="authorBean.email" styleClass="specialFields"/><%=MilkaErrorFormatter.getErrorFrom(request, "Author.email.err")%></div>
		<div id="lineadecontenido" style="margin-left:20px; margin-top:10px;"><html:checkbox name="EmailEndingForm" property="authorBean.acceptPolitics"/> Acepto las pol&iacute;ticas del sitio <%=MilkaErrorFormatter.getErrorFrom(request, "Author.politics.err")%></div>
		<div id="lineadecontenido" style="margin-left:20px; margin-top:10px;"><html:file name="EmailEndingForm" property="photoFormFile" /></div>
		<div id="buttonHolder"><html:submit property="operation" styleClass="okCircle">Enviar</html:submit><%=MilkaErrorFormatter.getErrorFrom(request, "EmailEndingForm.photo.err")%></div>
		<div id="textoEspecial">
			<p>Complet&aacute; los datos, carg&aacute; una captura de tu final de E-Mail<br />
				enviarlo. Una vez que est&eacute; publicado te avisaremos.</p>
			<p>XOXOXOXOXOXOXOXOXOXO</p>
			<p>Milka!</p>
		</div>
</html:form>

<div id="dialog-modal" class="hide" title="Finales de email">
	<p>
		Gracias por participar.<br>
		Te avisaremos cuando este aprobada.
	</p>
</div>
<div id="dialog-modal-err" class="hide" title="Finales de email">
	Ha ocurrido un error, intentelo nuevamente.
</div>
</div>
</body>
</html>