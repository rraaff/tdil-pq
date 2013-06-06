<%@ include file="includes/tryPage.jspf" %>
<%@ include file="includes/agentInfo.jspf" %>
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
<meta charset="ISO-8859-1"/>
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
<script type="text/javascript">
$(function() {
	<%@ include file="includes/closeLayers.jspf" %>
	<%@ include file="includes/externalLogins.jspf" %>
});
<%@ include file="includes/panicJS.jspf" %>
<%@ include file="includes/centerLayerJS.jspf" %>
<%@ include file="includes/errorAjaxJS.jspf" %>
<%@ include file="includes/updatePersonChangePasswordJS.jspf" %>
</script>
<link type="text/css" href="css/mediaQueries.css" rel="stylesheet" />
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
		<% CameraForm cameraForm = (CameraForm)session.getAttribute("CameraForm"); %>
		<div id="productHomeContent" class="col1_798 camarasBG">
			<div id="cameraTitle">
				<h1>Mis Cámaras</h1>
			</div>
			<div id="camerasList">
				<h3>Seleccioná la cámara</h3>
				<ul class="cameraListUl">
					<% int camIndex = 0;
						for (Camera camera : cameraForm.getAllCameras()) { %>
							<li class="cameraLink"><a href="./selectCamera.do?pos=<%=camIndex%>"><%=camera.getUrl()%></a></li>
					<% camIndex = camIndex + 1;
						} %>
				</ul>
			</div>
		</div>
	</div>
</section>
<%@ include file="includes/panicButton.jspf" %>
<%@ include file="includes/footerProductoHome.jsp" %>
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/videoLayers.jsp" %>
<%@ include file="includes/panicLayers.jspf" %>
</body>
</html>
<%@ include file="includes/catchPage.jspf" %>