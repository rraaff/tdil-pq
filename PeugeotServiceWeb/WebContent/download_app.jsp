<%@page import="com.tdil.ljpeugeot.services.PeugeotService"%>
<%@page import="com.tdil.ljpeugeot.model.NativeApp"%>
<%@page import="com.tdil.web.breadcrum.BreadcrumItem"%>
<%@page import="com.tdil.web.breadcrum.Breadcrum"%>
<%@ include file="includes/agentInfo.jspf" %><%--
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
--%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>Peugeot AXS :: Descargar APP</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_website.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_website_logged.css" />
<!--[if lt IE 9]>
	<link type="text/css" rel="stylesheet" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_ie8-fixes.css" />
<![endif]-->
<%@ include file="includes/headLogged.jsp" %>
<script>
	$(document).ready(
			function(){
	<%@ include file="includes/closeLegalesLayer.jsp" %>
	<%@ include file="includes/closeLayers.jspf" %>
	<%@ include file="includes/externalLogins.jspf" %>
				
			}
	);

	<%@ include file="includes/updatePersonChangePasswordJS.jspf" %>
	<%@ include file="includes/errorAjaxJS.jspf" %>
	<%@ include file="includes/centerLayerJS.jspf" %>

	<%@ include file="includes/openLegalesLayer.jsp" %>
	<%@ include file="includes/contactJS.jspf" %>
	

</script>
</head>
<%@ include file="includes/version.jspf" %>
<body>
<%
	Breadcrum breadcrums = new Breadcrum()
	.titles("Inicio","Peugeot App")
	.pages("","");
%>
<!-- WEBSITE CONTENT -->
<%@ include file="includes/header.jspf" %>
<%@ include file="includes/page_title.jspf" %>
<%@ include file="includes/action_bar.jspf" %>
<%@ include file="includes/under_shade.jspf" %>
<section id="main_content_regular_page">
	<div class="template_half">
		<h1>Descargas</h1>
		<p class="bajada_light">Desde esta página podrás seleccionar y descargar la aplicación nativa para cada dispositivo disponible.<br/>Si no encontras tu dispositivo aquí podrás navegar el sitio sin problemas con los equipos y navegadores de última generación.</p>
		<p class="bajada_light"><a href="javascript:contactLoJack();" title="Envienos una comentario">Si encontrás algún problema para navegar, te solicitamos que nos avises, para poder corregirlo a la brevedad</a></p>
		
		<h2>Versiones de la aplicación</h2>
		
		<% for (NativeApp sp : PeugeotService.getNativeApps()) { %>
			<% if (sp.getDeleted().equals(0)) { %>
			<div class="faq_item download_item">
				<h3><a href="<%=sp.getUrl()%>" title="Descargar APP para Android"><%=sp.getTitle()%></a></h3>
				<img src="<%=sp.getImage()%>" />
				<p><%=sp.getSummary()%></p>
				<button class="link" onclick="window.location='<%=sp.getUrl()%>';"><span></span>Descargar ahora</button>
			</div>
			<% } %>
		<% } %>
	</div>
</section>
<%@ include file="includes/emergency_button.jspf" %>
<%@ include file="includes/footer_web.jspf" %>

<!-- ALL LAYERS -->
<%@ include file="includes/layer_parking_not_logged.jspf" %>
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/layer_contact.jspf" %>
<%@ include file="includes/layer_legales.jspf" %>
</body>
</html>