<%@page import="com.tdil.lojack.utils.SystemPropertiesKeys"%>
<%@page import="com.tdil.lojack.utils.SystemPropertyUtils"%>
<%@page import="com.tdil.lojack.struts.forms.CameraForm"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><!--
--><%@ page info="home"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="includes/checkThalamusUp.jspf" %><!--
--><%@ include file="includes/userLogged.jspf" %><!--
--><%@ include file="includes/mustBeLogged.jspf" %><!--
--><%@ include file="includes/mustBeHomeUser.jspf" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/reset-styles.css" rel="stylesheet" media="screen">
<link href="css/sizers.css" rel="stylesheet" media="screen">
<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet" />

<script src="js/bootstrap.min.js"></script>

<%@ include file="includes/headLogged.jsp" %>

<link href="css/tdil.bootstrap.modifier.css" rel="stylesheet" media="screen">
<link href="css/index_modales.css" rel="stylesheet"  type="text/css"/>
<link href="css/index_social.css" rel="stylesheet"  type="text/css"/>
<link href="css/copyright.css" rel="stylesheet"  type="text/css"/>

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
					$('#cameraImg').attr('src', './viewCamera');
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
				$('#cameraImg').attr('src', './viewCamera');
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
			$('#cameraImg').attr('src', './viewCamera');
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
		$('#cameraImg').attr('src', './viewCamera');
 },
	error: function() {
		alert('err');
	}
});
}
	</script>
<style type="text/css">
#productsMenu ul li.tabHome {
	background:#f05224;
}
</style>
</head>
<body>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/clientMainManu.jsp" %>
<section id="content">
	<div class="pageWrapper">
		<div class="col1_170">
			<div class="tab"></div>
			<ul class="tabServices">
				<li class="tabAlarms" ><a href="./goToHomeAlarms.do">Mis Alarmas</a></li>
				<li class="tabLights" ><a href="./goToHomeLights.do">Mis Luces</a></li>
				<li class="tabCameras active"><a href="./goToHomeCamera.do">Mi Camara</a></li>
			<ul>
		</div>
		<div class="col1_794 camarasBG">
			<div id="cameraTitle">
				<h1>Mi C&aacute;mara</h1>
			</div>
			<div id="appletHolder">
				<% CameraForm cameraForm = (CameraForm)session.getAttribute("CameraForm"); %>
				<% if (cameraForm.isUseApplet()) { %>
					<object classid="clsid:CAFEEFAC-0016-0000-0000-ABCDEFFEDCBA">
						<param name="code" value="com.tdil.lojack.camera.applet.AppletCamara.class">
						<PARAM NAME="TYPE" VALUE="application/x-java-applet;version=1.6">
						<PARAM NAME="ARCHIVE" VALUE="cameraviewer-b201305181729.jar">
							<comment>
								<embed code="com.tdil.lojack.camera.applet.AppletCamara.class" type="application/x-java-applet;jpi-version=1.6"
									ARCHIVE="cameraviewer-b201305181729.jar" width="561" height="297">
									<noembed>
										No Java Support.
									</noembed>
								</embed>
							</comment>
						</object>

				<% } else { %>
					<img id="cameraImg" src="./viewCamera" width="561" height="297"><br>
					<a href="javascript:up()" id="up">Up</a><br>
					<a href="javascript:down()" id="down">Down</a><br>
					<a href="javascript:left()" id="left">Left</a><br>
					<a href="javascript:right()" id="right">Right</a><br>
					<!-- a href="./toggleCameraView.do">Vista Avanzada</a -->
					<script>
					setInterval(function() {
							$('#cameraImg').attr('src', './viewCamera');
					},<%=SystemPropertyUtils.getSystemPropertValue(SystemPropertiesKeys.camera_mobile_refreshTime)%>);
					</script>
				<% } %>
			</div>
			<div id="linksAside">
				<a href="./toggleCameraView.do"><span>Cambiar vista >></span></a>
				<br/>
				<a href="./goToHomeCamera.do"><span><< Volver</span></a>
			</div>
		</div>
	</div>
</section>
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/footerProductoHome.jsp" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/videoLayers.jsp" %>

</body>
</html>