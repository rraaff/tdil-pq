<%@ page info="home"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="../includes/checkThalamusUp.jspf" %><!--
--><%@ include file="../includes/userLogged.jspf" %><!--
--><%@ include file="./includes/mustBeLogged.jspf" %>
<!DOCTYPE html>
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
<script type="text/javascript">
function enterPets() {
	var userDate = new Date();
	var userTimeZone = ( userDate.getTimezoneOffset()/60 )*( -1 );
	window.open('<%=com.tdil.lojack.pets.PetsConnector.getPetsLoginUrl()%>SESSIONID=<%=websiteUser.getJSESSIONID()%>&TIMEZONEOFFSET=' +userTimeZone+ '&LOJACKTOKEN=<%=com.tdil.lojack.pets.PetsConnector.getPetsToken()%>&AWSELB=<%=websiteUser.getAWSELB()%>', 'Lojack Pets');
}
</script>
<style type="text/css">
#logoIndex { top: 80px; }
#laRuedita { top: 25%; }
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
<div id="user"><span class="userSaludation">Hola:&nbsp;</span><span class="userName"><%=websiteUser.getName()%></span></div>
<div id="menu">
	<ul>
		<li><a href="./goToChangePasswordMobile.do" title="Cambiar mis clave">Cambiar clave</a></li>
		<li><a href="./goToUpdatePersonMobile.do" title="Cambiar mis datos">Cambiar mis datos</a></li>
		<li><a href="./logoutMobile.do" class="last" title="Salir del sistema">Salir</a></li>
	</ul>
</div>
<!-- 
<a href="./goToEditProfileMobile.do">
< % if (websiteUser.getModelUser().getIdAvatar() != null && !websiteUser.getModelUser().getIdAvatar().equals(0)) { %>
	<img id="avatarImg" src="../download.st?id=< %=websiteUser.getModelUser().getIdAvatar()%>&type=PUBLIC&ext=< %=websiteUser.getModelUser().getExtAvatar()%>" width="30" height="30" align="absmiddle"> 
< % } else { %>
	<img id="avatarImg" src="images/skin_lj_rl/logos/avatarBase.png" width="32" height="32" align="absmiddle"> 
< % } %></a>
 -->
<div id="laRuedita">
	<div class="fakeRuedita">
		<div id="iconoLogout"><a href="./logoutMobile.do" title="Salir del sistema"><img src="../images/null.gif" /></a></div>
		<div id="iconoParkings"><a href="../productoParkings.jsp" title="Ingresá y utilizá la App gratuita para estacionar en CABA"><img src="../images/null.gif" /></a></div>
		<div id="iconoProfile"><a href="./goToUpdatePersonMobile.do" title="Cambiar mis datos"><img src="../images/null.gif" /></a></div>
		<% if (websiteUser.isPreventUser()) { %>
			<% if (websiteUser.isPreventLogged()) { %>
				<div id="iconoCar"><a href="../productoPrevent.jsp" title="Más sobre CAR"><img src="../images/null.gif" /></a></div>
			<% } else { %>
				<div id="iconoCar"><a href="../productoPrevent.jsp" title="Más sobre CAR"><img src="../images/null.gif" /></a></div>
			<% } %>
		<% } else { %>
			<div id="iconoCar"><a href="videoPageCar.jsp" title="Más sobre CAR"><img src="../images/null.gif" /></a></div>
		<% } %>
		<% if (websiteUser.isHomeUser()) { %>
			<div id="iconoHome"><a href="../productoHome.jsp" title="Más sobre HOME"><img src="../images/null.gif" /></a></div>
		<% } else { %>
			<div id="iconoHome"><a href="videoPageHome.jsp" title="Más sobre HOME"><img src="../images/null.gif" /></a></div>
		<% } %>
		<% if (websiteUser.isPetUser()) { %>
			<div id="iconoPets"><a href="javascript:enterPets()" title="Más sobre PETS"><img src="../images/null.gif" /></a></div>
		<% } else { %>
			<div id="iconoPets"><a href="videoPagePets.jsp" title="Más sobre PETS"><img src="../images/null.gif" /></a></div>
		<% } %>
	</div>
</div>
<div id="flyingObjectContainer"> 
	<div id="logoIndex"><img src="../images/skin_lj_rl/logos/lo-jack_index.png" /></div>
</div>
<div id="copyright">
	<div class="copy">
		<p>2013 lojack - todos los derechos reservados política de privacidad | <a href="legal.jsp" id="legales" title="Legales">legales</a> | dirección general de defensa y protección al consumidor. Si queres enviarnos un mensaje hacélo <a href="javascript:contactLoJack();" title="Envianos tu consulta">clic acá</a>.</p>
	</div>
</div>
</body>
</head>