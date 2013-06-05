<%@ page info="index"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="../includes/checkThalamusUp.jspf" %><!--
--><%@ include file="../includes/userLogged.jspf" %>
<!DOCTYPE html>
<% session.setAttribute("usingMobile", Boolean.TRUE); %>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="../favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/reset-styles.css" rel="stylesheet" type="text/css">
<link href="css/index_menu.css" rel="stylesheet" type="text/css">
<link href="css/laruedita.css" rel="stylesheet" type="text/css">
<link href="css/copyright.css" rel="stylesheet" type="text/css">
<style type="text/css">
#laRuedita { top: 28%; }
@media only screen and (max-height : 320px) {
	#copyright {
		display: none;
	}
	#user {
		display: none;
	}
	#laRuedita { top: 15%; }
}
</style>
</head>
<body>
<div id="menu">
	<ul>
		<li><a href="./loginModal.jsp" title="Ingresar ahora">Ingresar</a></li>
		<li><a href="./recuperarClaveModal.jsp">Mi clave</a></li>
		<li><a href="./goToRegistrationMobile.do" class="last">Registrarme</a></li>
	</ul>
</div>
<div id="laRuedita">
	<div class="fakeRuedita">
		<div id="iconoLogin"><a href="./loginModal.jsp" title="Ingresar ahora"><img src="../images/null.gif" /></a></div>
		<div id="iconoParkings"><a href="#" title="Ingres� y utiliz� la App gratuita para estacionar en CABA"><img src="../images/null.gif" /></a></div>
		<div id="iconoProfile"><a href="./goToRegistrationMobile.do" title="Registrate gratis"><img src="../images/null.gif" /></a></div>
		<div id="iconoCar"><a href="videoPageCar.jsp" title="M�s sobre CAR"><img src="../images/null.gif" /></a></div>
		<div id="iconoHome"><a href="videoPageHome.jsp" title="M�s sobre HOME"><img src="../images/null.gif" /></a></div>
		<div id="iconoPets"><a href="videoPagePets.jsp" title="M�s sobre PETS"><img src="../images/null.gif" /></a></div>
	</div>
</div>
<div id="flyingObjectContainer"> 
	<div id="logoIndex"><img src="../images/skin_lj_rl/logos/lo-jack_index.png" /></div>
</div>
<div id="copyright">
	<div class="copy">
		<p>2013 lojack - todos los derechos reservados pol�tica de privacidad | <a href="legal.jsp" title="Legales">legales</a> | direcci�n general de defensa y protecci�n al consumidor. Si queres enviarnos un mensaje hac�lo <a href="javascript:contactLoJack();" title="Envianos tu consulta">clic ac�</a>.</p>
	</div>
</div>
</body>
</html>