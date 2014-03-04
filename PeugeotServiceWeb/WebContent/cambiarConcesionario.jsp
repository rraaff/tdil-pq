<%@page import="com.tdil.ljpeugeot.model.State"%>
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
<title>Peugeot AXS :: Seleccionar locación para services</title>
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
<style>
.rowSelected {
background-color: green;
}
</style>
<script>
	$(document).ready(
		function(){
	<%@ include file="includes/closeLegalesLayer.jsp" %>
	<%@ include file="includes/closeLayers.jspf" %>
	<%@ include file="includes/externalLogins.jspf" %>

	$("form[name='ChangeDealerForm']").validate({
		errorPlacement: function(error, element) {
			error.appendTo( element.next("div"));
		},
		rules: { 
			'email': {required: true, email: true}
		},
		messages: {	
			'email': {required: "<span>Ingrese el E-Mail.</span>",
				email: "<span>Ingrese un E-Mail válido.</span>"}
		}
	});

		}
	);

	<%@ include file="includes/updatePersonChangePasswordJS.jspf" %>
	<%@ include file="includes/errorAjaxJS.jspf" %>
	<%@ include file="includes/centerLayerJS.jspf" %>

	<%@ include file="includes/openLegalesLayer.jsp" %>
	<%@ include file="includes/contactJS.jspf" %>
	

	function selectVehicle(vehicleId) {
		$("[name='idVehicle']").val(vehicleId);
		$('#addServiceButton').prop('disabled', false);
		$('#vehicleTable').find('li').each(function() {
		    $(this).removeClass("rowSelected");
		});
		$('li[rel="ve-'+vehicleId+'"]').each(function() {
		    $(this).addClass("rowSelected");
		});
	}
	
</script>
</head>
<%
com.tdil.web.breadcrum.Breadcrum breadcrums = new com.tdil.web.breadcrum.Breadcrum()
	.titles("Inicio","Services","Seleccionar service", "Determinar vehículo")
	.pages("home.jsp","servicesDashboard.jsp","buscarConcesionario.jsp","");
%>
<% MENU_ACTIVE_SECTION = "SERVICES"; %>
<!-- WEBSITE CONTENT -->
<%@ include file="includes/header.jspf" %>
<%@ include file="includes/page_title.jspf" %>
<%@ include file="includes/service_section_menu.jspf" %>
<%@ include file="includes/under_shade.jspf" %>
<section id="main_content_regular_page">
	<div class="template_half">
		<div class="table_container">
			<% List<VehicleValueObject> myVehicles = PeugeotService.getMyVehicles(websiteUser.getModelUser().getId()); %>
			<html:form method="POST" action="/changeDealer" styleClass="add_service_form">
				<html:hidden name="ChangeDealerForm" property="idVehicle"/>
				<div class="table_services width650 fleft">
					<h1>Determinar el vehículo para la locación elegida</h1>
					<p class="bajada">Una vez seleccionada la locación del service, en este paso, determine el vehículo al que relacionará con dicha locación</p> 
					<ul class="table_header">
						<li class="cardesc width100per">Seleccionar Vehículo/s</li>
					</ul>
					<ul id="vehicleTable" class="table_body">
						<li class="cardesc width100per" rel="ve-0" onclick="selectVehicle(0)">Todos</li>
					</ul>
					<% for (VehicleValueObject vehicleValueObject : myVehicles)  { %>
						<ul class="table_body">
							<li class="cardesc width100per" rel="ve-<%=vehicleValueObject.getVehicle().getId()%>" onclick="selectVehicle(<%=vehicleValueObject.getVehicle().getId()%>)"><%=vehicleValueObject.getVehicle().getDomain()%></li>
						</ul>
					<% } %>
				</div>
				<div class="add_cartoservices_info width300 fright">
					<h2>Cargar E-Mail de contacto</h2>
					<p class="bajada">Los avisos de service serán enviados a esta casilla</p>
					<div class="add_service_form_wrapper width100per heightAuto">
						<fieldset class="textLeft">
							<label class="width100">E-mail</label>
							<html:text name="ChangeDealerForm" property="email" styleClass="width150"/>
						</fieldset>
					</div>
				</div>
				<fieldset class="button_bar pOnlyTop25">
					<button class="botton_ahead" type="submit" disabled id="addServiceButton">Determinar<span></span></button>
				</fieldset>
			</html:form>
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