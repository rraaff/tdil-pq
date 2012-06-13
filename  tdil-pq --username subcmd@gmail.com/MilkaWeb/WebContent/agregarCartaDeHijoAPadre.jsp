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
		
			$("form[name='MailToParentForm']").validate({
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
			            $("form[name='MailToParentForm']").ajaxSubmit({
			    			type: "POST",
			    			url: "./uploadMailToParent.do",
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
				close: function(event, ui) {  document.location.href='./cartasDeHijosAPadres.jsp?dnc=true'; }
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
<!--
body {
	
}
#formContent {
	background-image: url(images/experiencias/cartasDePaH/upload_bg.jpg);
	background-repeat: no-repeat;
	background-position: center top;
	width:900px;
	height:400px;
	margin:0 auto;
}
#Nombre {
	height: 25px;
	width: 170px;
	left: 368px;
	top: 139px;
	position: relative;
}
#E-Mail {
	height: 25px;
	width: 170px;
	left: 598px;
	top: 114px;
	position: relative;
}
#Politicas {
	height: 25px;
	width: 160px;
	left: 309px;
	top: 141px;
	position: relative;
}
#SubirImagen {
	height: 25px;
	width: 260px;
	left: 408px;
	top: 158px;
	position: relative;
}
.normalField {
	width:150px;
	height:25px;
	line-height:22px;
	border: solid 1px #333333;
}
#buttonHolder {
	height: 130px;
	width: 130px;
	left: 742px;
	top: 128px;
	position: relative;
}
#buttonHolder .okCircle, .okCircle a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 1px;
	line-height:14px;
	color: #FFFFFF;
	background:transparent;
	text-decoration: none;
	background-image: url(images/experiencias/cartasDePaH/upload.png);
	background-repeat: no-repeat;
	background-position: center center;
	width:130px;
	height:130px;
	border:none;
	cursor:hand;
	margin:0;
	padding:0;
	
	-webkit-border-radius: 0;
	-moz-border-radius: 0;
	border-radius: 0;
	text-shadow: none;
	box-shadow: none;
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	-o-box-shadow: none;
}
-->
</style>
</head>
<body>
<div id="formContent">
<html:form method="POST" action="/uploadMailToParent" enctype="multipart/form-data">
	<div id="Nombre"><html:text name="MailToParentForm" property="authorBean.name" styleClass="normalField"/><%=MilkaErrorFormatter.getErrorFrom(request, "Author.name.err")%></div>
	<div id="E-Mail"><html:text name="MailToParentForm" property="authorBean.email" styleClass="normalField"/><%=MilkaErrorFormatter.getErrorFrom(request, "Author.email.err")%></div>
	<div id="Politicas"><%=MilkaErrorFormatter.getErrorFrom(request, "Author.politics.err")%><html:checkbox name="MailToParentForm" property="authorBean.acceptPolitics"/></div>
	<div id="SubirImagen"><html:file name="MailToParentForm" property="photoFormFile" /><%=MilkaErrorFormatter.getErrorFrom(request, "MailToParentForm.photo.err")%></div>
	<div id="buttonHolder"><html:submit property="operation" styleClass="okCircle"></html:submit></div>
</html:form>
</div>
<div id="dialog-modal" class="hide" title="Cartas de Hijos a Padres">
	<p>Gracias por participar.<br>Te avisaremos cuando este aprobada.</p>
</div>
<div id="dialog-modal-err" class="hide" title="Cartas de Hijos a Padres">
	Ha ocurrido un error, intentelo nuevamente.
</div>
</body>
</html>