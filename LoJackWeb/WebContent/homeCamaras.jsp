<%@ include file="includes/agentInfo.jspf" %><%--
--%><%@page import="com.tdil.lojack.gis.model.Camera"%><%--
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
	<link type="text/css" rel="stylesheet" media="screen" href="css/index_modales.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/bootstrapSwitch.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/unified_mobile.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/homeProduct_mobile.css" />
<% } else { %>
	<link type="text/css" rel="stylesheet" media="screen" href="css/reset-styles.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/sizers.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/bootstrap-combined.min.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/bootstrapSwitch.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/tdil.bootstrap.modifier.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/index_modales.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/index_social.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/copyright.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/mediaQueries.css" />
<% } %>
<style type="text/css">
#productsMenu ul li.tabHome {
	background:#f05224;
}
</style>
<%@ include file="includes/headLogged.jsp" %>
<script type="text/javascript">
$(function() {
	<%@ include file="includes/closeLayers.jspf" %>
	<%@ include file="includes/externalLogins.jspf" %>

	  $('.editable').editable(function(value, settings) {
		     return doRenameCamera($(this).attr('url'), value);
		  }, {
		     type    : 'textarea',
		     submit  : 'Renombrar',
		 });
});
function doRenameCamera(url, alarmDesc) {
	  <%@ include file="includes/blockUI.jspf" %>
	  $.ajax({
        type: "GET",
        cache: false,
        url: "./renameCamera.do",
        data: {url: url, description: alarmDesc},
        contentType: "application/json; charset=utf-8",
        success: function(data) {
      	  <%@ include file="includes/unblockUI.jspf" %>
      	  if (data.result == 'OK') {
				} else {
					 $('#'+alarmId).prop('innerHTML', 'Error');
				}
        },
        error: function() {
      	  <%@ include file="includes/unblockUI.jspf" %>
      	  $('#'+alarmId).prop('innerHTML', 'Error');
        }
    });
    return alarmDesc;
}
<%@ include file="includes/panicJS.jspf" %>
<%@ include file="includes/centerLayerJS.jspf" %>
<%@ include file="includes/errorAjaxJS.jspf" %>
<%@ include file="includes/updatePersonChangePasswordJS.jspf" %>
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
							<li class="cameraLink"><a href="./selectCamera.do?pos=<%=camIndex%>" title="ver cámara: <%= camera.getDescription() %>"><img src="images/null.gif" /></a><div url="<%=camera.getUrl()%>" class="editable"><%= camera.getDescription() %></div> <span class="rename">(Renombrar)</span></li>
					<% camIndex = camIndex + 1;
						} %>
				</ul>
			</div>
		</div>
	</div>
</section>
<%@ include file="includes/footerProductoHome.jsp" %>
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/videoLayers.jsp" %>
<%@ include file="includes/panicLayers.jspf" %>
<%@ include file="includes/version.jspf" %></body>
</html>
