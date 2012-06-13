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
		
			$("form[name='PostItForm']").validate({
					errorPlacement: function(error, element) {
						error.appendTo( element.parent("div"));
					},
					rules: { 'authorBean.name': {required: true},
							'authorBean.email': {required: true, email: true},
							'authorBean.acceptPolitics': {required: true},
							'text': {required: true}
					},
					messages: {
						'authorBean.name': {required: "<img id='nameerror' src='images/unchecked.gif' hovertext='Ingrese el nombre.' />"}, 
						'authorBean.email': {required: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese el email.' />",
								email: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese un email valido.' />"},
						'authorBean.acceptPolitics': {required: "<img id='politicserror' src='images/unchecked.gif' hovertext='Debe aceptar las politicas.' />"},
						'text': {required: "<img id='photoerror' src='images/unchecked.gif' hovertext='Ingrese el texto.' />"}
					},
					submitHandler: function() {
			            $("form[name='PostItForm']").ajaxSubmit({
			    			type: "POST",
			    			url: "./addPostIt.do",
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
				close: function(event, ui) {  document.location.href='./postits.jsp?dnc=true'; }
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
	background-image: url(images/experiencias/postits/bg_upload.jpg);
	background-repeat: no-repeat;
	background-position: center top;
}
#formContent {
	width:900px;
	height:400px;
	margin:0 auto;
}
#Nombre {
	height: 25px;
	width: 170px;
	left: 375px;
	top: 136px;
	position: relative;
}
#E-Mail {
	height: 25px;
	width: 170px;
	left: 375px;
	top: 145px;
	position: relative;
}
#Politicas {
	height: 25px;
	width: 160px;
	left: 300px;
	top: 163px;
	position: relative;
}
#SubirImagen {
	height: 160px;
	width: 180px;
	left: 300px;
	top: 198px;
	position: relative;
}
.normalField {
	font-family:"Trebuchet MS", Arial, sans-serif;
	width:150px;
	height:25px;
	line-height:22px;
	border: dotted 1px #ad9d1f;
	background:transparent;
}
.normalTextArea {
	width:200px;
	height:120px;
}
#buttonHolder {
	border:none;
	height: 85px;
	width: 82px;
	left: 512px;
	top: 70px;
	position: relative;
}
input[type="button"], input[type="submit"] {
	color:#684bb5;
	background:none;
	background-color:transparent;
	background-image: url(images/experiencias/postits/boton.png);
	background-repeat: no-repeat;
	background-position: center center;
	width:85px;
	height:82px;
	cursor:hand;
	margin:0;
	padding:0;
	border:none;
	-webkit-border-radius: 0px;
	-moz-border-radius: 0px;
	border-radius: 0px;
	text-shadow: 0 0 0 rgba(255, 255, 255, 0.0);
	box-shadow: 0 0 0 0 rgba(44, 28, 89, 0.0);
	-webkit-box-shadow: 0 0 0 0 rgba(44, 28, 89, 0.0);
	-moz-box-shadow: 0 0 0 0 rgba(44, 28, 89, 0.0);
	-o-box-shadow: 0 0 0 0 rgba(44, 28, 89, 0.0);
	position: relative;
}
input[type="button"]:hover, input[type="submit"]:hover {
	/* gradient */
	background:none;
	/* For Internet Explorer 8 */
	-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr=#5b488d, endColorstr=#806bb3)";
	color:#684bb5;
	background:none;
	background:transparent;
	background-image: url(images/experiencias/postits/boton.png);
	background-repeat: no-repeat;
	background-position: center center;
}
.okCircle, .okCircle a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 1px;
	line-height:14px;
	color: #FFFFFF;
	text-decoration: none;
	background-image: url(images/experiencias/postits/boton.png);
	background-repeat: no-repeat;
	background-position: center center;
	width:85px;
	height:82px;
	border:none;
	cursor:hand;
	margin:0;
	padding:0;
}
#buttonMeArrepentiHolder {
	height: 52px;
	width: 160px;
	margin:0;
	padding:0;
	left: 370px;
	top: 120px;
	position: relative;
}
/* recuperados de home styles*/
.hide {
	display: none;
}
@charset "utf-8";
/* CSS Document */
/*   Reseteo CSS   ***************************************************/

body,div,dl,dt,dd,ul,ol,li,h1,pre,form,fieldset,input,textarea,p,blockquote,th,td {
	margin:0;
	padding:0;
}
table {
	border-collapse:collapse;
	border-spacing:0;
}
fieldset,img { 
	border:0;
}
address,caption,cite,code,dfn,th,var {
	font-style:normal;
	font-weight:normal;
}
ol,ul {
	margin:0;
	padding:0;
}
caption,th {
	text-align:left;
}
h1,h2,h3,h4,h5,h6 {
	font-size:100%;
	font-weight:normal;
}
q:before,q:after {
	content:'';
}
abbr,acronym { 
	border:0;
}
a img{border:0; outline:none;}
a{
	color:#c8c8c8;
	outline:none;
	cursor:pointer;
	text-decoration:none;
}
a:hover{
	color:#FFF;
	outline:none;
	text-decoration:underline;
}
p, ul{
	margin-bottom:10px;	
}
.derecha{
	float:right;
	width:200px;
	text-align:right;
	margin:40px 20px 5px 0;
}
.violeta{
	color:#715da5;	
}
h2{
	margin:0;
	padding:0;	
	font-family: 'Sue Ellen Francisco', cursive;
	text-transform:uppercase;
	font-size:18px;
	line-height:22px;
	width:350px;
}
#titulo{
	width:900px;
	background:url(../images/linea-titulos.png) no-repeat 0 bottom;
	margin:20px 0 0 48px;
	padding:0 0 10px 0;
	float:left;
	clear:both;
}
#titulo h2{
	font-family: 'Sue Ellen Francisco', cursive;
	font-size:25px;
	line-height:22px;
	text-transform:none;
	width:300px;
	float:left;
}
#titulo h3{
	float:right;
	width:300px;
	font-family: 'Sue Ellen Francisco', cursive;
	font-size:25px;
	line-height:22px;
	margin:0;
	padding:0;
	text-align:right;
}
#header_home h2{
	margin:35px 0 0 20px;
	float:left;	
}
-->
</style>
</head>
<body>
<div id="formContent">
<html:form method="POST" action="/addPostIt">
	<div id="Nombre"><html:text name="PostItForm" property="authorBean.name" styleClass="normalField"/><%=MilkaErrorFormatter.getErrorFrom(request, "Author.name.err")%></div>
	<div id="E-Mail"><html:text name="PostItForm" property="authorBean.email" styleClass="normalField"/><%=MilkaErrorFormatter.getErrorFrom(request, "Author.email.err")%></div>
	<div id="Politicas"><%=MilkaErrorFormatter.getErrorFrom(request, "Author.politics.err")%><html:checkbox name="PostItForm" property="authorBean.acceptPolitics"/></div>
	<div id="SubirImagen"><html:textarea name="PostItForm" property="text" styleClass="normalField normalTextArea"/></div>
	<div id="buttonHolder"><input type="submit" value="Enviar" class="okCircle" /></div>
	<div id="buttonMeArrepentiHolder"><a href="postits.jsp" title="Volver a Postits"><img src="images/experiencias/postits/boton_me-arrepenti_out.png" width="160" height="52" /></a></div>
</html:form>
</div>

<div id="dialog-modal" class="hide" title="Post-It">
	<p>
		Gracias por participar.<br>
		Te avisaremos cuando este aprobada.
	</p>
</div>
<div id="dialog-modal-err" class="hide" title="Post-It">
	Ha ocurrido un error, intentelo nuevamente.
</div>
</body>
</html>