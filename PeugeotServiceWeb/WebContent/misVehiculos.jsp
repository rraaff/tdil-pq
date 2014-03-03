<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="com.tdil.ljpeugeot.model.valueobjects.VehicleValueObject"%>
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
<title>Peugeot AXS :: Mis vehículos</title>
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
<% 

%>
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
	Boolean apk = (Boolean)session.getAttribute("USING_APK");
if (apk != null && apk) {
	isAndroid = true;
}
%>
<% if (usingMobile || isAndroid) { %>
	<div style="background:#99ECD6; line-height:20px; text-align:center; color:#000;">android or mobile</div>
	</ul>
<% } %>
<!-- WEBSITE CONTENT -->
<%@ include file="includes/header.jspf" %>
<%@ include file="includes/page_title.jspf" %>
<%@ include file="includes/service_section_menu.jspf" %>
<section id="main_content_regular_page">
	<div class="template_half">
		<h1>Mis vehículos</h1>
		<div class="table_container">

			<% List<VehicleValueObject> myVehicles = PeugeotService.getMyVehicles(websiteUser.getModelUser().getId()); 
			DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
			simbolos.setPerMill('.');
			DecimalFormat formateador = new DecimalFormat("###,###,###",simbolos);
			%>
			<% if (myVehicles.isEmpty()) { %>
				<h2>No posee ningún vehículo asociado</h2>
			<% } else { %>
				<div class="table_services">
					<ul class="table_header">
						<li class="cardesc">Vehículo (Dominio)</li>
						<li class="kilometers">Kilometraje</li>
						<li class="services">Requiere Service</li>
						<li class="lastservkm">Kilometraje del último service</li>
						<li class="lastservdate">Fecha de último service</li>
					</ul>
					<% for (VehicleValueObject vehicleValueObject : myVehicles)  { %>
						<ul class="table_body">
							<%if (vehicleValueObject.getModel() !=  null) { %>
								<li class="cardesc"><%=vehicleValueObject.getModel().getName() %> (<%=vehicleValueObject.getVehicle().getDomain() %>)</li>
							<% } else { %>
								<li class="cardesc"><%=vehicleValueObject.getVehicle().getDomain() %></li>
							<% } %>
							<li class="kilometers"><%=vehicleValueObject.getVehicle().getKm() != null ? formateador.format(vehicleValueObject.getVehicle().getKm()) : "-"%></li>
							<%if (vehicleValueObject.getVehicle().getNeedsService()) { %>
								<li class="service_required services">Si</li>
							<% } else { %>
								<li class="service_not_required services">No</li>
							<% } %>
							<li class="lastservkm"><%=vehicleValueObject.getVehicle().getLastservicekm() != null ? formateador.format(vehicleValueObject.getVehicle().getLastservicekm()) : "-"%></li>
							<li class="lastservdate"><%=vehicleValueObject.getVehicle().getLastservicedate() != null ? DateUtils.formatDateSp(vehicleValueObject.getVehicle().getLastservicedate()) : "-"%></li>
						</ul>
					<% } %>
				</div>
			<% } %>
		</div>
	</div>
</section>
<%@ include file="includes/copyright.jspf" %>
<%@ include file="includes/footer_web.jspf" %>

<!-- ALL LAYERS -->
<%@ include file="includes/layer_contact.jspf" %>
<%@ include file="includes/layer_legales.jspf" %>
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>

</body>
</html>