<%@page import="java.util.ArrayList"%>
<%@page import="com.tdil.ljpeugeot.struts.action.DismissAdvicesAjaxAction"%>
<%@page import="com.tdil.utils.DateUtils"%>
<%@page import="com.tdil.ljpeugeot.model.valueobjects.AdviceValueObject"%>
<%@page import="com.tdil.ljpeugeot.services.PeugeotService"%>
<%@page import="com.tdil.ljpeugeot.model.Advice"%>
<%@page import="java.util.List"%>
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
<title>Peugeot AXS :: Services</title>
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

	<%@ include file="includes/advicesQueryAndOpen.jspf" %>
			}
	);
	<%@ include file="includes/advicesJS.jspf" %>

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
	Boolean apk = (Boolean)session.getAttribute("USING_APK");
if (apk != null && apk) {
	isAndroid = true;
}
%>
<%
com.tdil.web.breadcrum.Breadcrum breadcrums = new com.tdil.web.breadcrum.Breadcrum()
	.titles("Inicio","Services")
	.pages("home.jsp","");
%>
<% MENU_ACTIVE_SECTION = "SERVICES"; %>
<!-- WEBSITE CONTENT -->
<%@ include file="includes/header.jspf" %>
<%@ include file="includes/page_title.jspf" %>
<%@ include file="includes/action_bar.jspf" %>
<%@ include file="includes/service_section_menu.jspf" %>
<%@ include file="includes/under_shade.jspf" %>
<section id="main_content_regular_page">
	<div class="template_half">
		<div class="column width450">
			<div class="dashboard_item">
				<img class="icon" src="images/skn_peugeot/icons/icon_my_services_dashboard.png" />
				<h3><a href="misServices.jsp" title="Ingresar a Mis Services">Mis Services</a></h3>
				<p>En esta sección podés cargar los services que hayas realizado a tus vehículos. Te recomendamos mantener actualizada esta lista.</p>
				<button class="link" onclick="window.location='misServices.jsp';"><span></span>Ingresar</button>
			</div>
			<div class="dashboard_item">
				<img class="icon" src="images/skn_peugeot/icons/icon_my_vehicles_dashboard.png" />
				<h3><a href="misVehiculos.jsp" title="Ingresar a Mis Vehículos">Mis Vehiculos</a></h3>
				<p>Aquí te proporcionamos la información actualizada respecto al estado de tus vehículos, services, kilometrajes.</p>
				<button class="link" onclick="window.location='misVehiculos.jsp';"><span></span>Ingresar</button>
			</div>
		</div>
		<div class="column width550 pLeft100">
			<div class="dashboard_item">
				<img class="icon" src="images/skn_peugeot/icons/icon_official_services_dashboard.png" />
				<h3><a href="servicesYGarantia.jsp" title="Ingresar a la información de services oficiales">Ver services oficiales</a></h3>
				<p>Accedé a una lista completa de la información de services para cada uno de tus vehículos.</p>
				<button class="link" onclick="window.location='servicesYGarantia.jsp';"><span></span>Ingresar</button>
			</div>
			<div class="dashboard_item">
				<img class="icon" src="images/skn_peugeot/icons/icon_services_locations_dashboard.png" />
				<h3><a href="buscarConcesionario.jsp" title="Ingresar a Services autorizados para sus vehículos">Agencias/Service autorizados</a></h3>
				<p>Seleccioná la agencia o service autorizado dónde deseas realizar el próximo service para tu vehículo. El sistema le avisará al centro elegido que lo has elegido para llevar a cabo la revisión períodica de tu vehículo.</p>
				<button class="link" onclick="window.location='buscarConcesionario.jsp';"><span></span>Ingresar</button>
			</div>
		</div>
	</div>
</section>
<%@ include file="includes/footer_web.jspf" %>

<!-- ALL LAYERS -->
<%@ include file="includes/layer_contact.jspf" %>
<%@ include file="includes/layer_legales.jspf" %>
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>

<%@ include file="includes/advicesLayers.jspf" %>

</body>
</html>