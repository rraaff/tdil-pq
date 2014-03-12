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
<link type="text/css" rel="stylesheet" media="screen" href="css/reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/index_menu.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/laruedita.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/copyright.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/index_modales.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/sizers.css" />
<!-- link type="text/css" rel="stylesheet" media="screen" href="../css/ws_modal.css" /> -->
<script type='text/javascript' src='../js/jquery-1.8.2.min.js'></script>
<script type="text/javascript" src="../js/jquery.blockUI.js"></script>
<script type="text/javascript">

$(document).ready(
		function(){
			$( "#enterPets" ).click(function() {
				<%@ include file="includes/blockUI.jspf" %>
				$.ajax({
		          type: "GET",
		          cache: false,
		          async: false,
		          url: "./refreshThalamusLoginCacheServlet.st",
		          dataType: "json",
		          success: function(data) {
		        	  <%@ include file="includes/unblockUI.jspf" %>
		        	  if (data.result == 'OK') {
		        		  var userDate = new Date();
		        			var userTimeZone = ( userDate.getTimezoneOffset()/60 )*( -1 );
		        			window.open('<%=com.tdil.lojack.pets.PetsConnector.getPetsMobileLoginUrl(websiteUser)%>SESSIONID=<%=websiteUser.getJSESSIONID()%>&TIMEZONEOFFSET=' +userTimeZone+ '&LOJACKTOKEN=<%=com.tdil.lojack.pets.PetsConnector.getPetsToken()%>&AWSELB=<%=websiteUser.getAWSELB()%>', 'Lojack Pets');
						} else {
							errorAjax();
						}
		       	   },
			          error: function() {
			        	  <%@ include file="includes/unblockUI.jspf" %>
			        	  errorAjax();
			          }
			      });
				});
		}
);
// div.wsmodal

function enterPrevent() {
	var userDate = new Date();
	var userTimeZone = ( userDate.getTimezoneOffset()/60 )*( -1 );
	document.location.href = '../goToPreventLogin.do?timezone=' +userTimeZone;
}

<%@ include file="../includes/errorAjaxJS.jspf" %>
<%@ include file="../includes/centerLayerJS.jspf" %>

	function showVluMessages(dni) {
		//alert(dni);
		<%@ include file="../includes/blockUI.jspf" %>
		$('#vluMessagesLayer').load('../vluMessagesNoPrevent.jsp?dni=' + dni, function(response, status, xhr) {
			<%@ include file="../includes/unblockUI.jspf" %>
			if (status == "error") {
				errorAjax();
			} else {
				centerLayer($(window), $( "#vluMessagesLayer" ));
				centerLayer($(window), $( "#centradorModalesVluMessages" ));
			}
			adjustModalHeight();
		});
	}
	function showVluNoMessages() {
		centerLayer($(window), $( "#vluNoMessagesLayer" ));
		centerLayer($(window), $( "#vluMessages" ));
		adjustModalHeight();
	}
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

#iconoTv			{ top:-622px; left:178px; }
#iconoClubLJ		{ top:-6px; left:128px; }
#iconoHome			{ top:-385px; left:6px; }
#iconoParkings		{ top:91px; left:156px; }
#iconoPets			{ top:-433px; left:196px; }
#rdCentral			{ top:-584px; left:77px; }


</style>
<script type="text/javascript">
function chbg(title, subTitle) {
	
	var id = document.getElementById("title");
	id.innerHTML=title;
	
	var id = document.getElementById("subTitle");
	id.innerHTML=subTitle;
}
var adjustModalHeight = function() {
	var elemToChangeX = document.getElementById("vluMessages");
	var winH = $(window).height();
	elemToChangeX.style.height = winH + "px"

}
window.onload=function() {
	adjustModalHeight();
}		
window.onresize=function() {
	adjustModalHeight();
}
</script>
<script>
function hideVluNoMessages() {
	$( "#vluNoMessagesLayer" ).fadeOut();
	
}
$( "#closevluNoMessagesLayer" ).click(function() {
	$( "#vluNoMessagesLayer" ).fadeOut();
});
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
		<% if (websiteUser.isClientClubLoJack() && LoJackConfig.isClubLoJackShow() ) { %>
			<div id="iconoClubLJ"><a href="clubLoJack.jsp" onmouseover="chbg('Club', 'LoJack')" onmouseout="chbg('Seleccione', 'Una Aplicación')"><img src="../images/null.gif" /></a></div>
		<% } else { %>
			<div id="iconoLogout"><a href="./logoutMobile.do" onmouseover="chbg('Salir', 'del sistema')" onmouseout="chbg('Seleccione', 'Una Aplicación')"><img src="../images/null.gif" /></a></div>
		<% } %>
		<div id="iconoParkings"><a href="../productoParkings.jsp" onmouseover="chbg('Parking', 'Estacioná en CABA y GBA')" onmouseout="chbg('Seleccione', 'Una Aplicación')"><img src="../images/null.gif" /></a></div>
		<!-- div id="iconoProfile"><a href="./goToUpdatePersonMobile.do" title="Cambiar mis datos"><img src="../images/null.gif" /></a></div -->
		<% if (websiteUser.isPreventUser()) { %>
			<div id="iconoCar"><a id="loginPreventLink" href="javascript:enterPrevent()" onmouseover="chbg('Car', 'ingresá ahora')" onmouseout="chbg('Seleccione', 'Una Aplicación')"><img src="../images/null.gif" /></a></div>
		<% } else { %>
			<div id="iconoCar"><a href="videoPageCar.jsp" onmouseover="chbg('Car', 'mirá el video')" onmouseout="chbg('Seleccione', 'Una Aplicación')"><img src="../images/null.gif" /></a></div>
		<% } %>
		<%if (websiteUser != null && websiteUser.isLogged() && websiteUser.vluIsClient() && websiteUser.getVluMessages() == 0) { %>
			<div id="liVluMessages"><a href="javascript:showVluNoMessages();" onmouseover="chbg('Car', 'Mensaje de LoJack')" onmouseout="chbg('Seleccione', 'Una Aplicación')"><img src="../images/skin_lj_rl/vlu/badge_ok.png" /></a></div>
		<% } else if (websiteUser != null && websiteUser.isLogged() && websiteUser.getVluMessages() > 0) { %>
			<div id="liVluMessages"><a href="javascript:showVluMessages('<%=websiteUser.getDni()%>');" onmouseover="chbg('Car', 'Mensaje de LoJack')" onmouseout="chbg('Seleccione', 'Una Aplicación')"><img src="../images/skin_lj_rl/vlu/badge_alert.png" /></a></div>
		<% } else { %>
			<div id="liVluMessages" style="opacity:0;"><a href="#" onmouseover="chbg('Car', 'No hay mensajes')" onmouseout="chbg('Seleccione', 'Una Aplicación')"><img src="../images/skin_lj_rl/vlu/badge_ok.png" /></a></div>
		<% } %>
		<% if (websiteUser.isHomeUser()) { %>
			<div id="iconoHome"><a href="../productoHome.jsp" onmouseover="chbg('Home', 'ingresá ahora')" onmouseout="chbg('Seleccione', 'Una Aplicación')"><img src="../images/null.gif" /></a></div>
		<% } else { %>
			<div id="iconoHome"><a href="videoPageHome.jsp" onmouseover="chbg('home', 'mirá el video')" onmouseout="chbg('Seleccione', 'Una Aplicación')"><img src="../images/null.gif" /></a></div>
		<% } %>
		<% if (websiteUser.isPetUser()) { %>
			<div id="iconoPets"><a id="enterPets" href="#" onmouseover="chbg('Pet', 'ingresá ahora')" onmouseout="chbg('Seleccione', 'Una Aplicación')"><img src="../images/null.gif" /></a></div>
		<% } else { %>
			<div id="iconoPets"><a href="videoPagePets.jsp" onmouseover="chbg('Pet', 'mirá el video')" onmouseout="chbg('Seleccione', 'Una Aplicación')"><img src="../images/null.gif" /></a></div>
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

<div id="vluMessagesLayer" class="layerOnTop" style="display: none; z-index:3000; position:fixed;">
	<div id="vluMessages">
		Consultando datos...
	</div>
</div>
<div id="vluNoMessagesLayer" class="layerOnTop" style="display: none; z-index:3000; position:fixed;">
	<div id="vluMessages">
		<div id="xContainer"><button class="buttonLink" onclick="javascript:hideVluNoMessages();">X</button></div>
		<h3>Mensaje de LoJack</h3>
		<div id="tableStyle">
			<p class="information">Tu equipo Lojack funciona correctamente</p>
		</div>
	</div>
</div>
</body>
</head>