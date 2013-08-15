<%@ page info="index"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="../includes/checkThalamusUp.jspf" %><!--
--><%@ include file="../includes/userLogged.jspf" %><!--
--><%@ include file="../includes/agentInfo.jspf" %><!--
--><% if (websiteUser != null && websiteUser.isLogged()) { %>
	<jsp:forward page="./home.jsp"></jsp:forward>
<% 	return;
	} %><%--
--%><!DOCTYPE html><%--
--%><% session.setAttribute("usingMobile", Boolean.TRUE); %><%--
--%><html lang="es">
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
#laRuedita { top:20%; }
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
@media all and (orientation:landscape) {
	#flyingObjectContainer { width:20%; }
	#logoIndex { width:20%; }
}
@media all and (orientation:landscape) and (max-height: 384px) {
	#flyingObjectContainer { width:100%; top:0; position:fixed; }
	#logoIndex { width:26%; top:49%; margin:0 auto; }
}

</style>
<script type="text/javascript">
function chbg(title, subTitle) {
	
	var id = document.getElementById("title");
	id.innerHTML=title;
	
	var id = document.getElementById("subTitle");
	id.innerHTML=subTitle;
}
</script>
</head>
<body>
<div id="menu">
	<ul>
		<li><a href="./loginModal.jsp" title="Ingresar ahora">Ingresar</a></li>
		<li><a href="./recuperarClaveModal.jsp" title="Olvidé mi clave">Mi clave</a></li>
		<li><a href="./goToRegistrationMobile.do" title="Registrarme" class="last">Registrarme</a></li>
	</ul>
</div>

<div id="laRuedita">
	<div class="fakeRuedita">
		<div id="iconoLogin"><a href="./loginModal.jsp"    onmouseover="chbg('ingresá', 'con tus datos')" onmouseout="chbg('Seleccione', 'Una Aplicación')" title="Ingresar ahora"><img src="../images/null.gif" /></a></div>
		<div id="iconoParkings"><a href="./loginModal.jsp" onmouseover="chbg('Parking', 'ingrese ahora')" onmouseout="chbg('Seleccione', 'Una Aplicación')" title="Ingresá y utilizá la App gratuita para estacionar en CABA"><img src="../images/null.gif" /></a></div>
		<!--  div id="iconoProfile"><a href="./goToRegistrationMobile.do" title="Registrate gratis"><img src="../images/null.gif" /></a></div -->
		<div id="iconoCar">
		<% if (isIpad || isIphone || isIpod) { %>
			<a href="videoPageCar.jsp"   onmouseover="chbg('Car', 'mirá el video')" onmouseout="chbg('Seleccione', 'Una Aplicación')" title="Más sobre CAR"><img src="../images/null.gif" /></a>
		<% } else { %>
			<a href="<%=com.tdil.lojack.utils.LoJackConfig.getMobilevideocar()%>" target="_blank" onmouseover="chbg('Car', 'mirá el video')" onmouseout="chbg('Seleccione', 'Una Aplicación')" title="Más sobre CAR"><img src="../images/null.gif" /></a>
		<% } %>
		</div>
		<div id="iconoHome">
		<% if (isIpad || isIphone || isIpod) { %>
			<a href="videoPageHome.jsp" onmouseover="chbg('home', 'mirá el video')" onmouseout="chbg('Seleccione', 'Una Aplicación')" title="Más sobre HOME"><img src="../images/null.gif" /></a>
		<% } else { %>
			<a href="<%=com.tdil.lojack.utils.LoJackConfig.getMobilevideohome()%>" target="_blank" onmouseover="chbg('home', 'mirá el video')" onmouseout="chbg('Seleccione', 'Una Aplicación')" title="Más sobre HOME"><img src="../images/null.gif" /></a>
		<% } %>
		</div>
		<div id="iconoPets">
		<% if (isIpad || isIphone || isIpod) { %>
			<a href="videoPagePets.jsp" onmouseover="chbg('Pets', 'mirá el video')" onmouseout="chbg('Seleccione', 'Una Aplicación')" title="Más sobre PETS"><img src="../images/null.gif" /></a>
		<% } else { %>
			<a href="<%=com.tdil.lojack.utils.LoJackConfig.getMobilevideopets()%>" target="_blank" onmouseover="chbg('Pets', 'mirá el video')" onmouseout="chbg('Seleccione', 'Una Aplicación')" title="Más sobre PETS"><img src="../images/null.gif" /></a>
		<% } %>
		</div>
		<div id="iconoTv"><a href="http://www.lojack.tv" onmouseover="chbg('LOJACK TV', 'Ingresar ahora')" onmouseout="chbg('Seleccione', 'Una Aplicación')" target="_blank"><img src="../images/null.gif" /></a></div>
		<div id="rdCentral">
			<h2 id="title">Seleccione</h2>
			<h3 id="subTitle">Una Aplicación</h3>
		</div>
	</div>
</div>
<div id="flyingObjectContainer">
	<div id="logoIndex"><img src="../images/skin_lj_rl/logos/lo-jack_2_mobile.png" /></div>
</div>
<div id="copyright">
	<div class="copy">
		<p>2013 lojack | <a href="legal.jsp" title="Legales">legales</a> | <a href="./goToContactMobile.do" title="Envianos tu consulta">Contactanos</a></p>
	</div>
</div>
<%@ include file="../includes/version.jspf" %></body>
</html>