<%@ page info="home"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="../includes/checkThalamusUp.jspf" %><%--
--%><%@ include file="../includes/userLogged.jspf" %><%--
--%><!DOCTYPE html>
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
#laRuedita { top:25%; }
#flyingObjectContainer { width:50%; margin:0 auto; }
#logoIndex { width:50%; top:75%; margin:0 auto; }
@media only screen and (max-height : 320px) {
	#copyright {
		display: none;
	}
	#user {
		display: none;
	}
	#laRuedita { top: 15%; }
	#flyingObjectContainer { width:25%; margin:0 auto; }
	#logoIndex {width:25%; top:75%; margin:0 auto; }
}
.alert { background:#fff; text-align:center; }
.alert-error {}
</style>
</head>
<body>
<div class="alert alert-error">Se le ha enviado la clave</div>
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
		<div id="iconoParkings"><a href="./loginModal.jsp" title="Ingresá y utilizá la App gratuita para estacionar en CABA"><img src="../images/null.gif" /></a></div>
		<div id="iconoProfile"><a href="./goToRegistrationMobile.do" title="Registrate gratis"><img src="../images/null.gif" /></a></div>
		<div id="iconoCar"><a href="videoPageCar.jsp" title="Más sobre CAR"><img src="../images/null.gif" /></a></div>
		<div id="iconoHome"><a href="videoPageHome.jsp" title="Más sobre HOME"><img src="../images/null.gif" /></a></div>
		<div id="iconoPets"><a href="videoPagePets.jsp" title="Más sobre PETS"><img src="../images/null.gif" /></a></div>
	</div>
</div>
<div id="flyingObjectContainer">
	<div id="logoIndex"><img src="../images/skin_lj_rl/logos/lo-jack_mainLogo.png" /></div>
</div>
<div id="copyright">
	<div class="copy">
		<p>2013 lojack | <a href="legal.jsp" title="Legales">legales</a> | <a href="./goToContactMobile.do" title="Envianos tu consulta">Contactanos</a></p>
	</div>
</div>
<%@ include file="../includes/version.jspf" %></body>
</html>