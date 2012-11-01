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
					<div class="myRow">
						<div class="myLabel width60">Nombre</div>
						<div class="myLabel width160"><input type="text" class="normalField width150" /></div>
						<div class="myLabel width20">&nbsp;</div>
						<div class="myLabel width60">Apellido</div>
						<div class="myLabel width160"><input type="text" class="normalField width150" /></div>
					</div>
					<div class="myRow" style="padding-bottom:2px;">
						<div class="myLabel width60">E-Mail</div>
						<div class="myLabel width400"><input type="text" class="normalField width390" /></div>
					</div>
					<div class="myRow">
						<div class="myLabel width450"><span class="comment" style="color:#333333;">(Si es usuario del sitio utilice el mismo E-Mail que us&oacute; para registrarse)</span></div>
					</div>
					<div class="myRow">
						<div class="myLabel width60">Asunto</div>
						<div class="myLabel width400"><input type="text" class="normalField width390" /></div>
					</div>
					<div class="myRow">
						<div class="myLabel width60">Texto</div>
						<div class="myLabel width400 height100"><textarea class="normalField width390 height100"></textarea></div>
					</div>
					<div class="myRow" style="padding-bottom:2px;">
						<div id="buttonContactForm"><a href="#"><img src="images/null.gif" width="169" height="65" /></a></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>