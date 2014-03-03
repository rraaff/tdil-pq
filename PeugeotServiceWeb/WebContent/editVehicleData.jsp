<%@page import="com.tdil.ljpeugeot.model.Dealer"%>
<%@page import="com.tdil.ljpeugeot.model.City"%>
<%@page import="com.tdil.ljpeugeot.model.State"%>
<%@page import="com.tdil.ljpeugeot.struts.forms.prevent.EditVehicleDataForm"%>
<%@page import="com.tdil.struts.resources.ApplicationResources"%>
<%@page import="com.tdil.ljpeugeot.struts.forms.EditContactDataForm"%>
<%@page import="com.tdil.ljpeugeot.prevent.model.SatellitePosition"%>
<%@ include file="includes/agentInfo.jspf" %><%--
--%><%@page import="com.tdil.ljpeugeot.utils.LJPeugeotConfig"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.prevent.SelectVehiclesForm"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertiesKeys"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertyUtils"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><%@ page info="home"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="includes/userLogged.jspf" %><%--
--%><%@ include file="includes/mustBeLogged.jspf" %><%--
--%><%@ include file="includes/mustBePreventUser.jspf" %><%--
--%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>Peugeot AXS :: Inicio</title>
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
			$('select[name=idState]').change(
				function() {
					var selectToLoad = $('select[name=idCity]');
					selectToLoad.empty();
		        	$('<option>Loading</option>').appendTo(selectToLoad);
					var countrySelected = Number($(this).attr('value'));
					if (countrySelected > 0) {
						$.ajax({
				            type: "GET",
				            cache: false,
				            url: "./searchCities.do",
				            data: {stateId: countrySelected },
				            contentType: "application/json; charset=utf-8",
				            success: function(msg) {
				            	var select = $('select[name=idCity]');
				            	select.empty();
				            	$('<option>Select one option</option>').appendTo(select);
				            	$.each(msg, function(index, item) {
					                $('<option value="'+item.id+'">'+item.name+'</option>').appendTo(select);
				                });
				            },
				            error: function() {
				                alert("error consultando las ciudades");
				            }
				        });
					} else {
						var select = $('select[name=idCity]');
		            	select.empty();
		            	$('<option>Select one option</option>').appendTo(select);
					}
				}
			);
			$('select[name=idCity]').change(
				function() {
					var selectToLoad = $('select[name=idDealer]');
					selectToLoad.empty();
		        	$('<option>Loading</option>').appendTo(selectToLoad);
					var countrySelected = Number($(this).attr('value'));
					if (countrySelected > 0) {
						$.ajax({
				            type: "GET",
				            cache: false,
				            url: "./searchDealers.do",
				            data: {cityId: countrySelected },
				            contentType: "application/json; charset=utf-8",
				            success: function(msg) {
				            	var select = $('select[name=idDealer]');
				            	select.empty();
				            	$('<option>Select one option</option>').appendTo(select);
				            	$.each(msg, function(index, item) {
					                $('<option value="'+item.id+'">'+item.name+'</option>').appendTo(select);
				                });
				            },
				            error: function() {
				                alert("error consultando las concesionarias");
				            }
				        });
					} else {
						var select = $('select[name=idDealer]');
		            	select.empty();
		            	$('<option>Select one option</option>').appendTo(select);
					}
				}
			);
});
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
<% } %>
<!-- WEBSITE CONTENT -->
<%@ include file="includes/header.jspf" %>
<%@ include file="includes/page_title.jspf" %>
<%@ include file="includes/service_section_menu.jspf" %>
<section id="main_content_regular_page">
	<div class="template_half">
		<div class="illustration">
			<img src="images/skn_peugeot/bgs/photos/locations.png" />
		</div>
		<div class="content_template">
			<h2>Seleccione la agencia o service oficial donde desea realizar el próximo service</h2>
			<p>Determine la provincia, luego localidad y seleccione de la lista que aparecerá a continuación.</p>
			<% EditVehicleDataForm editVehicleDataForm = (EditVehicleDataForm)session.getAttribute("EditVehicleDataForm");%>
			<html:form method="POST" action="/saveVehicleData" styleClass="template_form_page">
				<div class="template_form_wrapper">
					<fieldset>
						<label>Provincia</label>
						<html:select name="EditVehicleDataForm" property="idState">
							<option value="">Seleccione...</option>
							<% for (State state : editVehicleDataForm.getStates()) { %>
								<option <%=	state.getId() == editVehicleDataForm.getIdState()? "selected" : ""%> value="<%=state.getId()%>">
								<%=state.getName()%></option>
							<% } %>
						</html:select>
					</fieldset>
					<fieldset>
						<label>Ciudades</label>
						<html:select name="EditVehicleDataForm" property="idCity">
							<option value="">Seleccione...</option>
							<% for (City city : editVehicleDataForm.getCities()) { %>
								<option <%=	city.getId() == editVehicleDataForm.getIdCity()? "selected" : ""%> value="<%=city.getId()%>">
								<%=city.getName()%></option>
							<% } %>
						</html:select>
					</fieldset>
					<fieldset>
						<label>Concesionarias</label>
						<html:select name="EditVehicleDataForm" property="idDealer">
							<option value="">Seleccione...</option>
							<% for (Dealer dealer : editVehicleDataForm.getDealers()) { %>
								<option <%=	dealer.getId() == editVehicleDataForm.getIdDealer()? "selected" : ""%> value="<%=dealer.getId()%>">
								<%=dealer.getName()%></option>
							<% } %>
						</html:select>
					</fieldset>
				</div>
				<fieldset class="button_bar pOnlyTop25">
					<button class="botton_ahead" type="submit" id="submitregister">Guardar<span></span></button>
				</fieldset>
			</html:form>			
			<a href="./viewServices.do?id=<%=editVehicleDataForm.getSelectedVehicle().getId()%>">Services</a>
		</div>
	</div>
</section>
<%@ include file="includes/copyright.jspf" %>
<%@ include file="includes/footer_web.jspf" %>

<!-- ALL LAYERS -->
<%@ include file="includes/layer_parking_not_logged.jspf" %>
<%@ include file="includes/layer_contact.jspf" %>
<%@ include file="includes/layer_legales.jspf" %>

</body>
</html>