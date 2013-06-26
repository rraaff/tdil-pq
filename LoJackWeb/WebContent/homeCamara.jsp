<%@ include file="includes/agentInfo.jspf" %><%--
--%><%@page import="com.tdil.lojack.utils.SystemPropertiesKeys"%><%--
--%><%@page import="com.tdil.lojack.utils.SystemPropertyUtils"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.CameraForm"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><%@ page info="home"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="includes/checkThalamusUp.jspf" %><%--
--%><%@ include file="includes/userLogged.jspf" %><%--
--%><%@ include file="includes/mustBeLogged.jspf" %><%--
--%><%@ include file="includes/mustBeHomeUser.jspf" %><%--
--%><!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<% if (usingMobile || isAndroid) { %>
	<link href="css/index_modales.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="css/bootstrapSwitch.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="css/unified_mobile.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="css/homeProduct_mobile.css" rel="stylesheet" type="text/css" media="screen" />
<% } else { %>
	<link href="css/reset-styles.css" rel="stylesheet" media="screen" />
	<link href="css/sizers.css" rel="stylesheet" media="screen" />
	<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link href="css/tdil.bootstrap.modifier.css" rel="stylesheet" media="screen" />
	<link href="css/index_modales.css" rel="stylesheet"  type="text/css" />
	<link href="css/index_social.css" rel="stylesheet"  type="text/css" />
	<link href="css/copyright.css" rel="stylesheet"  type="text/css" />
	<link href="css/bootstrapSwitch.css" rel="stylesheet" />
	<link type="text/css" href="css/mediaQueries.css" rel="stylesheet" />
<% } %>
<style type="text/css">
#productsMenu ul li.tabHome {
	background:#f05224;
}
@media only screen and (max-width: 350px) {
	#productHomeContent.col1_798 { padding: 0; }
}
@media only screen and (max-width: 968px) {
	#productHomeMenu.col1_170 { position: relative; }
} 
</style>
<%@ include file="includes/headLogged.jsp" %>
<script>
$(function() {
	<%@ include file="includes/closeLayers.jspf" %>
	<%@ include file="includes/externalLogins.jspf" %>
});

<%@ include file="includes/centerLayerJS.jspf" %>
<%@ include file="includes/errorAjaxJS.jspf" %>
<%@ include file="includes/updatePersonChangePasswordJS.jspf" %>


function up() {
		$.ajax({
				type: "GET",
				cache: false,
				url: "./moveCamera?dir=up",
				success: function(data) {
					$('#cameraImg').attr('src', './viewCamera?img=' + Math.random());
			 },
				error: function() {
					alert('err');
				}
		});
}
function down() {
		$.ajax({
			type: "GET",
			cache: false,
			url: "./moveCamera?dir=down",
			success: function(data) {
				$('#cameraImg').attr('src', './viewCamera?img=' + Math.random());
		 },
			error: function() {
				alert('err');
			}
	});
}
function left() {
		$.ajax({
		type: "GET",
		cache: false,
		url: "./moveCamera?dir=left",
		success: function(data) {
			$('#cameraImg').attr('src', './viewCamera?img=' + Math.random());
	 },
		error: function() {
			alert('err');
		}
});
}
function right() {
		$.ajax({
	type: "GET",
	cache: false,
	url: "./moveCamera?dir=right",
	success: function(data) {
		$('#cameraImg').attr('src', './viewCamera?img=' + Math.random());
 },
	error: function() {
		alert('err');
	}
});
}

<%@ include file="includes/panicJS.jspf" %>
</script>
</head>
<body>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/clientMainManu.jsp" %>
<section id="content">
	<div class="pageWrapper">
		<div id="productHomeMenu" class="col1_170">
			<div id="tab"></div>
			<ul class="tabServices">
				<li class="tabAlarms" ><a href="./goToHomeAlarms.do">Mis Alarmas</a></li>
				<li class="tabLights" ><a href="./goToHomeLights.do">Mis Luces</a></li>
				<li class="tabCameras active"><a href="./goToHomeCamera.do">Mi Camara</a></li>
			</ul>
		</div>
		<div id="productHomeContent" class="camarasBG">
			<div id="cameraTitle">
				<h1>Mi C�mara</h1>
			</div>
			<% CameraForm cameraForm = (CameraForm)session.getAttribute("CameraForm"); %>
			<% if (cameraForm.isUseApplet()) { %>
				<div id="appletHolder">
					<object classid="clsid:CAFEEFAC-0016-0000-0000-ABCDEFFEDCBA">
						<param name="wmode" value="transparent" />
						<param name="code" value="com.tdil.lojack.camera.applet.AppletCamara.class">
						<PARAM NAME="TYPE" VALUE="application/x-java-applet;version=1.6">
						<PARAM NAME="ARCHIVE" VALUE="cameraviewer-b201306262018.jar">
						<comment>
							<embed code="com.tdil.lojack.camera.applet.AppletCamara.class" type="application/x-java-applet;jpi-version=1.6"
								ARCHIVE="cameraviewer-b201306262018.jar" width="100%" height="100%" wmode="transparent">
								<noembed>
									No Java Support.
								</noembed>
							</embed>
						</comment>
					</object>
				</div>
				<div id="linksAside">
					<a href="./toggleCameraView.do"><span>Cambiar Simple >></span></a>
					<!--  a href="./goToHomeCamera.do"><span><< Volver</span></a> -->
				</div>
			<% } else { %>
				<div id="pictureContainer">
					<img id="cameraImg" src="./viewCamera">
					<div class="controlsBasicView">
						<a href="javascript:left()" id="right"><img src="images/skin_lj_rl/buttons/AppletCamera/applet_right_off.png" /></a>
						<a href="javascript:up()" id="up"><img src="images/skin_lj_rl/buttons/AppletCamera/applet_up_off.png" /></a>
						<a href="javascript:down()" id="down"><img src="images/skin_lj_rl/buttons/AppletCamera/applet_down_off.png" /></a>
						<a href="javascript:right()" id="left"><img src="images/skin_lj_rl/buttons/AppletCamera/applet_left_off.png" /></a>
					</div>
				</div>
				<script>
					setInterval(function() {
							$('#cameraImg').attr('src', './viewCamera?img=' + Math.random());
					},<%=SystemPropertyUtils.getSystemPropertValue(SystemPropertiesKeys.camera_mobile_refreshTime)%>);
				</script>
				<div id="linksAside">
					<% if (usingMobile || isAndroid) { %>
						<!-- a href="./goToHomeCamera.do"><span><< Volver</span></a-->
					<% } else { %>
						<a href="./toggleCameraView.do">Vista Avanzada</a>
						<!-- a href="./goToHomeCamera.do"><span><< Volver</span></a-->
					<% } %>
				</div>
			<% } %>
		</div>
	</div>
</section>
<%@ include file="includes/panicButton.jspf" %>
<%@ include file="includes/footerProductoHome.jsp" %>
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/videoLayers.jsp" %>
<%@ include file="includes/panicLayers.jspf" %>
<%@ include file="includes/version.jspf" %></body>
</html>
