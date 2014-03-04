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
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_flexi-background.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_font_embeder.css" />
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
				email: "<span>Ingrese un E-Mail v�lido.</span>"}
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
<body>



<%@ include file="includes/layer_contact.jspf" %>

<% List<VehicleValueObject> myVehicles = PeugeotService.getMyVehicles(websiteUser.getModelUser().getId()); %>
<html:form method="POST" action="/changeDealer">
	<html:hidden name="ChangeDealerForm" property="idVehicle"/>
	<ul id="vehicleTable">
		<li rel="ve-0" onclick="selectVehicle(0)">Todos</li>
		<% for (VehicleValueObject vehicleValueObject : myVehicles)  { %>
			<li rel="ve-<%=vehicleValueObject.getVehicle().getId()%>" onclick="selectVehicle(<%=vehicleValueObject.getVehicle().getId()%>)"><%=vehicleValueObject.getVehicle().getDomain()%></li>
		<% } %>
	</ul>
	<div class="scrollable">
		recibir el aviso en: <html:text name="ChangeDealerForm" property="email" />
	</div>
	<fieldset>
		<input type="submit" disabled id="addServiceButton" value="Cambiar" class="buttonSend">
	</fieldset>
</html:form>

<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<!-- Layer legales -->
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/layer_legales.jspf" %>

<%@ include file="includes/version.jspf" %>
</body>
</html>