<%@ page info="home"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="../includes/checkThalamusUp.jspf" %><%--
--%><%@ include file="../includes/userLogged.jspf" %><%--
--%><%@ include file="./includes/mustBeLogged.jspf" %><%--
--%><!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="../favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_reset-styles.css" rel="stylesheet" type="text/css">
<link href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_index_menu.css" rel="stylesheet" type="text/css">
<link href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_laruedita.css" rel="stylesheet" type="text/css">
<link href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_copyright.css" rel="stylesheet" type="text/css">
<link href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_index_modales.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src='../js/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_jquery-1.8.2.min.js'></script>
<script type="text/javascript" src="../js/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_jquery.blockUI.js"></script>
<script type="text/javascript">

function enterPrevent() {
	var userDate = new Date();
	var userTimeZone = ( userDate.getTimezoneOffset()/60 )*( -1 );
	document.location.href = '../goToPreventLogin.do?timezone=' +userTimeZone;
}
<%@ include file="../includes/errorAjaxJS.jspf" %>
<%@ include file="../includes/centerLayerJS.jspf" %>


</script>
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
	#logoIndex { width:26%; top:46%; margin:0 auto; }
}
input[type="submit"].buttonDefault,
input[type="button"].buttonDefault,
button.buttonDefault {
	border:none;
	background:#dc2c27;
	color:#fff;
	font-size:100%;
	line-height:normal;
	text-transform:uppercase;
	padding:10px 20px;
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
<%@ include file="includes/head.jsp"%>
</head>
<body>
<div id="user"><span class="userSaludation">Hola:&nbsp;</span><span class="userName"><%=websiteUser.getName()%></span></div>
<div id="menu">
	<ul>
		<li><a href="./goToChangePasswordMobile.do" title="Cambiar mis clave">Mi clave</a></li>
		<li><a href="./goToUpdatePersonMobile.do" title="Cambiar mis datos">Mis datos</a></li>
		<li><a href="./logoutMobile.do" class="last" title="Salir del sistema">Salir</a></li>
	</ul>
</div>
<div id="laRuedita">
	<div class="fakeRuedita">
		<div id="iconoLogout"><a href="./logoutMobile.do" onmouseover="chbg('Salir', 'del sistema')" onmouseout="chbg('Seleccione', 'Una Aplicación')"><img src="../images/null.gif" /></a></div>
		<div id="iconoParkings"><a href="../productoParkings.jsp" onmouseover="chbg('Parking', 'Estacioná en CABA y GBA')" onmouseout="chbg('Seleccione', 'Una Aplicación')"><img src="../images/null.gif" /></a></div>
		<!-- div id="iconoProfile"><a href="./goToUpdatePersonMobile.do" title="Cambiar mis datos"><img src="../images/null.gif" /></a></div -->
		<% if (websiteUser.isPreventUser()) { %>
			<div id="iconoCar"><a id="loginPreventLink" href="javascript:enterPrevent()" onmouseover="chbg('Car', 'ingresá ahora')" onmouseout="chbg('Seleccione', 'Una Aplicación')"><img src="../images/null.gif" /></a></div>
		<% } %>
		<div id="iconoTv"><a href="http://www.lojack.tv" onmouseover="chbg('LOJACK TV', 'Ingresar ahora')" onmouseout="chbg('Seleccione', 'Una Aplicación')" target="_blank"><img src="../images/null.gif" /></a></div>
		<div id="rdCentral">
			<h2 id="title">Seleccione</h2>
			<h3 id="subTitle">Una Aplicación</h3>
		</div>
	</div>
</div>
<div id="flyingObjectContainer"> 
	<div id="logoIndex"><img src="../images/skin_lj_rl/logos/lo-jack_mainLogo.png" /></div>
</div>
<div id="copyright">
	<div class="copy">
		<p>2014 lojack | <a href="legal.jsp" title="Legales">legales</a> | <a href="./goToContactMobile.do" title="Envianos tu consulta">Contactanos</a></p>
	</div>
</div>
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="../includes/version.jspf" %>
</body>
</head>