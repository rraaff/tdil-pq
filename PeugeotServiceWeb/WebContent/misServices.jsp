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
<title>Peugeot AXS :: Mis Services</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_website.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_website_logged.css" />
<!--[if lt IE 9]>
	<link type="text/css" rel="stylesheet" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_ie8-fixes.css" />
<![endif]-->
<style>
.vehicleRowSelected {
background-color: green;
}
</style>
<%@ include file="includes/headLogged.jsp" %>
<% 

%>
<script>
	$(document).ready(
		function(){
	<%@ include file="includes/closeLegalesLayer.jsp" %>
	<%@ include file="includes/closeLayers.jspf" %>
	<%@ include file="includes/externalLogins.jspf" %>
			$("input[name=serviceDate]").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true,
				changeYear: true, minDate: "-100Y", maxDate: "+0D"});

			$("form[name='AddServiceForm']").validate({
				errorPlacement: function(error, element) {
					error.appendTo( element.next("div"));
				},
				rules: {
					'serviceKm': {required: true,digits: true},
					'serviceDate': {required: true}
				},
				messages: {
					'serviceKm': {required: "<span>Ingrese los kms.</span>",
						digits: "<span>Ingrese solo números.<span>"},
					'serviceDate': {required: "<span>Ingresa la fecha de service.</span>"}
				},
				submitHandler: function() {
					<%@ include file="includes/blockUI.jspf" %>
					clearErrors();
		            $("form[name='AddServiceForm']").ajaxSubmit({
		    			type: "POST",
		    			url: "./addService.do",
		    			dataType: "json",
		    			success: postAdd,
		    			<%@ include file="includes/openErrorLayerJS.jspf" %>
		    			});
		        }
			});
		}
	);
	
	<%@ include file="includes/updatePersonChangePasswordJS.jspf" %>
	<%@ include file="includes/errorAjaxJS.jspf" %>
	<%@ include file="includes/centerLayerJS.jspf" %>

	<%@ include file="includes/openLegalesLayer.jsp" %>
	<%@ include file="includes/contactJS.jspf" %>

	function clearErrors() {
		$("div[id^='err.']").each(function(index, valor) {
			$(valor).prop('innerHTML','');
		});
	}
	
	function addService(domain, idVehicle) {
		clearErrors();
		$('#vehicleTable').find('li').each(function() {
		    $(this).removeClass("vehicleRowSelected");
		});
		$('li[rel="ve-'+idVehicle+'"]').each(function() {
		    $(this).addClass("vehicleRowSelected");
		});
		$('#addServiceDomain').prop('innerHTML', domain);
		$('#addServiceLayer').fadeIn(500);
		$("input[name=idVehicle]").val(idVehicle);
		
	}
	function cancelAddService() {
		$('#addServiceLayer').fadeOut(500);
	}
	
	function postAdd(data) {
		<%@ include file="includes/unblockUI.jspf" %>
		if (data.result == 'OK') {
			window.location.replace('./misServices.jsp');
		} else {
			$.each(data, function(key, value) {
				var obj = document.getElementById('err.' + key);
				if (obj) {
					obj.innerHTML = value;
				}
	        });
		}
	}

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
		<h1>Mis services</h1>
		<p class="bajada">Seleccione un vehículo de la lista para agregar un service en esa unidad</p>
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
					<ul class="table_body" id="vehicleTable">
					<% for (VehicleValueObject vehicleValueObject : myVehicles)  { %>
							<%if (vehicleValueObject.getModel() !=  null) { %>
								<li rel="ve-<%=vehicleValueObject.getVehicle().getId()%>" onclick="addService('<%=vehicleValueObject.getVehicle().getDomain()%>',<%=vehicleValueObject.getVehicle().getId()%>)" class="cardesc"><%=vehicleValueObject.getModel().getName() %> (<%=vehicleValueObject.getVehicle().getDomain() %>)</li>
							<% } else { %>
								<li rel="ve-<%=vehicleValueObject.getVehicle().getId()%>" onclick="addService('<%=vehicleValueObject.getVehicle().getDomain()%>',<%=vehicleValueObject.getVehicle().getId()%>)" class="cardesc"><%=vehicleValueObject.getVehicle().getDomain() %></li>
							<% } %>
							<li rel="ve-<%=vehicleValueObject.getVehicle().getId()%>" onclick="addService('<%=vehicleValueObject.getVehicle().getDomain()%>',<%=vehicleValueObject.getVehicle().getId()%>)" class="kilometers"><%=vehicleValueObject.getVehicle().getKm() != null ? formateador.format(vehicleValueObject.getVehicle().getKm()) : "-"%></li>
							<%if (vehicleValueObject.getVehicle().getNeedsService()) { %>
								<li rel="ve-<%=vehicleValueObject.getVehicle().getId()%>" onclick="addService('<%=vehicleValueObject.getVehicle().getDomain()%>',<%=vehicleValueObject.getVehicle().getId()%>)" class="service_required services">Si</li>
							<% } else { %>
								<li rel="ve-<%=vehicleValueObject.getVehicle().getId()%>" onclick="addService('<%=vehicleValueObject.getVehicle().getDomain()%>',<%=vehicleValueObject.getVehicle().getId()%>)" class="service_not_required services">No</li>
							<% } %>
							<li rel="ve-<%=vehicleValueObject.getVehicle().getId()%>" onclick="addService('<%=vehicleValueObject.getVehicle().getDomain()%>',<%=vehicleValueObject.getVehicle().getId()%>)" class="lastservkm"><%=vehicleValueObject.getVehicle().getLastservicekm() != null ? formateador.format(vehicleValueObject.getVehicle().getLastservicekm()) : "-"%></li>
							<li rel="ve-<%=vehicleValueObject.getVehicle().getId()%>" onclick="addService('<%=vehicleValueObject.getVehicle().getDomain()%>',<%=vehicleValueObject.getVehicle().getId()%>)" class="lastservdate"><%=vehicleValueObject.getVehicle().getLastservicedate() != null ? DateUtils.formatDateSp(vehicleValueObject.getVehicle().getLastservicedate()) : "-"%></li>
					<% } %>
					</ul>
				</div>
			<% } %>
		</div>
		<div id="addServiceLayer" class="add_services_info" style="display: none;">
			<h2>Cargar información de un service</h2>
			<div class="form_with_car_image" style="background-image: url(images/skn_peugeot/vehicles/408.jpg)!important;"><!-- acá le meto a la fuerza la imagen del auto como background-image: url(../images/skn_peugeot/vehicles/408.jpg); -->
				<html:form method="POST" action="/addService" styleClass="add_service_form">
					<div class="add_service_form_wrapper">
						<html:hidden name="AddServiceForm" property="idVehicle" />
						<fieldset>
							<label>Dominio</label>
							<label class="string_domain"><span id="addServiceDomain"></span></label>
						</fieldset>
						<fieldset>
							<label>Kilometros recorridos totales al service</label>
							<html:text name="AddServiceForm" property="serviceKm" />
							<div id="err.serviceKm" class="errorText textCenter"></div>
						</fieldset>
						<fieldset>
							<label>Fecha del service</label>
							<html:text name="AddServiceForm" property="serviceDate" />
							<div id="err.serviceDate" class="errorText textCenter"></div>
						</fieldset>
					</div>
					<fieldset class="button_bar pOnlyTop25">
						<button class="link_back" onclick="cancelAddService()"><span></span>Cancelar</button>
						<button class="botton_ahead" type="submit" id="addServiceButton" >Agregar<span></span></button>
					</fieldset>
				</html:form>
			</div>
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