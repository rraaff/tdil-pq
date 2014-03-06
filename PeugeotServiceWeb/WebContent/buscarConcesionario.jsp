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
<title>Peugeot AXS :: Concesionarios oficiales</title>
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

	$('select[name=idState]').change(
			function() {
				var selectToLoad = $('select[name=idCity]');
				selectToLoad.empty();
	        	$('<option>Cargando...</option>').appendTo(selectToLoad);
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
			            	$('<option value="0">Seleccione...</option>').appendTo(select);
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
	            	$('<option value="0">Seleccione...</option>').appendTo(select);
				}
			}
		);
		}
	);

	function searchDealers() {
		var idCity = $('select[name=idCity]').val();
		if (idCity != 0) {
			<%@ include file="includes/blockUI.jspf" %>
			$('#dealersLayer').load('searchDealers.jsp?idCity=' + idCity, function(response, status, xhr) {
				<%@ include file="includes/unblockUI.jspf" %>
				if (status == "error") {
					errorAjax();
				} 
			});
		}
	}
	
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
<% } %>
<%
com.tdil.web.breadcrum.Breadcrum breadcrums = new com.tdil.web.breadcrum.Breadcrum()
	.titles("Inicio","Services","Seleccionar service")
	.pages("home.jsp","servicesDashboard.jsp","");
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
		<div class="illustration">
			<img src="images/skn_peugeot/bgs/photos/locations.png" />
		</div>
		<div class="content_template">
			<h2>Seleccione la agencia o service oficial donde desea realizar el próximo service</h2>
			<p>Determine la provincia, luego localidad y seleccione de la lista que aparecerá a continuación.</p>
			<div class="template_form_wrapper">
				<fieldset>
					<label class="width25per">Provincia</label>
					<select class="width70per" name="idState" id="idState">
						<option value="">Seleccione...</option>
						<% for (State state : PeugeotService.getStates()) { %>
							<option value="<%=state.getId()%>">
							<%=state.getName()%></option>
						<% } %>
					</select>
				</fieldset>
				<fieldset>
					<label class="width25per">Ciudades</label>
					<select class="width70per" name="idCity" id="idCity">
						<option value="0">Seleccione...</option>
					</select>
				</fieldset>
			</div>
			<fieldset class="button_bar pOnlyTop25">
				<button class="botton_ahead" onclick="searchDealers()">Buscar<span></span></button>
			</fieldset>
		</div>
	</div>
	<div id="resultsFullTable" class="results_content">
		<h3>Resultados » <span>Service oficial</span></h3>
		<button class="print">Imprimir</button>
		<div class="table_container">
			<div id="dealersLayer"></div>
		</div>
	</div>
</section>
<%@ include file="includes/footer_web.jspf" %>

<!-- ALL LAYERS -->
<%@ include file="includes/layer_contact.jspf" %>
<%@ include file="includes/layer_legales.jspf" %>
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>

</body>
</html>