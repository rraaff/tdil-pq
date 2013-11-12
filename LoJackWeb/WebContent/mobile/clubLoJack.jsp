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
<link href="css/reset-styles.css" rel="stylesheet" type="text/css">
<link href="css/index_menu.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src='../js/jquery-1.8.2.min.js'></script>
<script type="text/javascript">
function enterPets() {
	var userDate = new Date();
	var userTimeZone = ( userDate.getTimezoneOffset()/60 )*( -1 );
	window.open('<%=com.tdil.lojack.pets.PetsConnector.getPetsMobileLoginUrl(websiteUser)%>SESSIONID=<%=websiteUser.getJSESSIONID()%>&TIMEZONEOFFSET=' +userTimeZone+ '&LOJACKTOKEN=<%=com.tdil.lojack.pets.PetsConnector.getPetsToken()%>&AWSELB=<%=websiteUser.getAWSELB()%>', 'Lojack Pets');
}

function enterPrevent() {
	var userDate = new Date();
	var userTimeZone = ( userDate.getTimezoneOffset()/60 )*( -1 );
	document.location.href = '../goToPreventLogin.do?timezone=' +userTimeZone;
}
</script>
<style type="text/css">
@media only screen and (max-height : 320px) {
	#user { display:none; }
}
@media all and (orientation:portrait) {
	#tarjeta { background:#fff; width:100%; height:100%; padding:10px; display: inline-block; }
	#tarjeta img { width:100%; }
	#tarjeta span.nameoncard { color:#fff; text-align:left; position:absolute; z-index:1499; font-size:100%; top:40%; left:8%; width:70%; }
}
@media all and (orientation:landscape) {
	#tarjeta { background:#fff; width:100%; padding:10px; margin:0 auto; text-align:center; display:inline-block; }
	#tarjeta img { width:100%; }
	#tarjeta span.nameoncard { color:#fff; text-align:left; position:absolute; z-index:1499; font-size:120%; top:85%; left:8%; width:50%; }
}
</style>
<%@ include file="includes/head.jsp"%>
</head>
<body>
<div id="user"><span class="userSaludation">Hola:&nbsp;</span><span class="userName"><%=websiteUser.getName()%></span></div>
<div id="menu">
	<ul>
		<li><a href="./goToChangePasswordMobile.do" title="Cambiar mis clave">Mi clave</a></li>
		<li><a href="home.jsp" title="Volver">< Volver</a></li>
		<li><a href="./logoutMobile.do" class="last" title="Salir del sistema">Salir</a></li>
	</ul>
</div>
<div id="tarjeta">
	<span id="tagOnCard" class="nameoncard"><%=websiteUser.getName()%></span>
	<img src="../images/skin_lj_rl/clubLoJack/tarjeta-club-lojack_black_hi-res.jpg" />
</div>
<%@ include file="../includes/version.jspf" %>
<script>
	var checkHeight = function() {
		var elemToChange = document.getElementById("tagOnCard");
		var objH = elemToChange.style.width;
		var winW = $(window).width();
		var winH = $(window).height();
		//var objH = $("tarjeta").height();
	//	window.alert(winH + "  //  " + objH);
		
	//	elemToChange.style.width = winW + "px"
	//	elemToChange.style.height = winH + "px"
	}
	
	window.onload=function() {
		checkHeight();
	}		
	window.onresize=function() {
		recheckHeight();
	}
</script>
</body>
</head>