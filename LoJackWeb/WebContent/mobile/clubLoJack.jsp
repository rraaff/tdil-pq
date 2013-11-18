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
	#tarjeta img { height:50px; width:auto; }
	#tarjeta span.nameoncard { color:#fff; text-align:center; position:absolute; z-index:1499; font-size:100%; width:100%; padding:0; left:0; top:0; }
}
#buttonOnCard { text-align:center; width:100%; padding:20px; display:inline-block; }
#buttonOnCard a.linkAsButton {
	-webkit-border-radius:5px;
	-moz-border-radius:5px;
	border-radius:5px;
	border:1px solid;
	border-color:rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
	background-color:#f9a123;
	background-image:-moz-linear-gradient(top, #f89406, #fbb450);
	background-image:-webkit-gradient(linear, 0 0, 0 100%, from(#f89406), to(#fbb450));
	background-image:-webkit-linear-gradient(top, #f89406, #fbb450);
	background-image:-o-linear-gradient(top, #f89406, #fbb450);
	background-image:linear-gradient(to bottom, #f89406, #fbb450);
	background-repeat:repeat-x;
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#fff89406', endColorstr='#fffbb450', GradientType=0);
	border-color:#fbb450 #fbb450 #f89406;
	border-color:rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
	filter:progid:DXImageTransform.Microsoft.gradient(enabled = false);
	color:#FFF;
	font-size:15px;
	font-weight:bold;
	text-shadow:0 -1px 0 rgba(0, 0, 0, 0.25);
	padding:10px;
	margin:10px 0 20px;
	cursor:pointer;
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
	<img id="cardImage" src="../images/skin_lj_rl/clubLoJack/tarjeta-club-lojack_black_hi-res.jpg" />
	<div id="buttonOnCard"><a class="linkAsButton" href="<%=LoJackConfig.getClubLoJackUrl()%>" target="_blank">Ver Beneficios</a></div>
</div>
<%@ include file="../includes/version.jspf" %>
<script>
	var checkHeight = function() {
		var elemTagOnCard = document.getElementById("tagOnCard");
		var elemTarjeta = document.getElementById("tarjeta");
		var elemCardImage = document.getElementById("cardImage");
		var objH = $(cardImage).height();
		var winH = $(window).height();
		var winW = $(window).width();
		// window.alert(winH + "  //  " + objH);
		
		if (winW < winH) {
			//window.alert("portrait");
			elemCardImage.style.height = "auto";
			elemTarjeta.style.height = winH + "px";
			elemTagOnCard.style.top = (objH + 10) + "px";
		} else {
			//window.alert("Landscape");
			elemCardImage.style.height = "70%";
			elemTarjeta.style.height = (winH - 34) + "px";
			elemTagOnCard.style.top = (objH + 10) + "px";
		}
	}
	
	window.onload=function() {
		checkHeight();
	}		
	window.onresize=function() {
		checkHeight();
	}
</script>
</body>
</head>