<%@page import="com.tdil.lojack.gis.model.Camera"%>
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
<meta charset="utf-8"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/reset-styles" rel="stylesheet" media="screen">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="js/bootstrap.min.js"></script>
<%@ include file="includes/headLogged.jsp" %>
<link href="css/tdil.bootstrap.modifier.css" rel="stylesheet" media="screen">
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
			<div class="tab"><img src="images/skin_lj_rl/tabs/servicion.png"></div>
			<ul class="tabServices">
				<li class="tabAlarms" ><a href="./goToHomeAlarms.do">Mis Alarmas</a></li>
				<li class="tabLights" ><a href="./goToHomeLights.do">Mis Luces</a></li>
				<li class="tabCameras active"><a href="./goToHomeCamera.do">Mi Camara</a></li>
			</ul>
		</div>
		<% CameraForm cameraForm = (CameraForm)session.getAttribute("CameraForm"); %>
		<div class="col1_794 camarasBG">
			<div id="cameraTitle">
				<h1>Mis C&aacute;maras</h1>
			</div>
			<% int camIndex = 0;
				for (Camera camera : cameraForm.getAllCameras()) { %>
				<a href="./selectCamera.do?pos=<%=camIndex%>"><%=camera.getUrl()%></a><br>
			<% camIndex = camIndex + 1;
				} %>
		</div>
	</div>
</section>
<footer>
	<div class="pageWrapper">
		<div class="col1_300 marginRight_60">
			<h3>ENTRÁ TRANQUILO<br/>A TU CASA</h3>
			<p>Con escolta Lojack te acompañamos telefónicamente a tu casa cuando entrás.</p>
			<button class="btn btn-mini btn-primary" type="button">Mas info >></button>
		</div>
		<div class="col1_300 marginRight_60">
			<h3>LoJack for<br/>Laptopts</h3>
			<p>Con LoJack for Laptopts sabés que si te roban la computadora, te la encontramos.</p>
			<button class="btn btn-mini btn-primary" type="button">Mas info >></button>
		</div>
		<div class="col_social">
			<ul class="nav nav-pills nav-social">
				<li><a href="#" class="fb"></a></li>
				<li><a href="#" class="tw"></a></li>
				<li><a href="#" class="ln"></a></li>
				<li><a href="#" class="gp"></a></li>
			</ul>
		</div>
	</div>
</footer>
</body>
</html>