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
--%><!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" media="screen" href="css/reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/flexi-background.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/font_embeder.css" />
<!--[if lt IE 9]>
	<link type="text/css" rel="stylesheet" href="css/ie8-fixes.css" />
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
	
	function addService(idVehicle) {
		$("input[name=idVehicle]").val(idVehicle);
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
<body>
<script src="js/flexi-background.js" type="text/javascript" charset="utf-8"></script>
<header>
	<div id="floatyMenu">
		<div class="wrapper">
			<ul>
				<li class="avatarLi"><a href="javascript:changeAvatar();">
					<%
						if (websiteUser.getModelUser().getIdAvatar() != null && !websiteUser.getModelUser().getIdAvatar().equals(0)) {
					%>
						<img id="avatarImg" src="./download.st?id=<%=websiteUser.getModelUser().getIdAvatar()%>&type=PUBLIC&ext=<%=websiteUser.getModelUser().getExtAvatar()%>" width="30" height="30" align="absmiddle"> 
					<%
 						} else {
 					%>
						<img id="avatarImg" src="images/skin_lj_rl/logos/avatarBase.png" width="32" height="32" align="absmiddle"> 
					<%
 						}
 					%></a></li>
				<li class="saludationAndUsername"><span class="userSaludation">Hola:&nbsp;</span><span class="userName"><%=websiteUser.getName()%></span></li>
				<li><a href="javascript:updatePerson();" title="Cambiar mis datos">Cambiar mis datos</a></li>
				<li><a href="javascript:changePassword();" title="Cambiar mis clave">Cambiar mi clave</a></li>
				<li><a href="./goToEditContactData.do" title="Datos de contacto">Datos de contacto</a></li>
				<li><a href="./selectVehicleForEditData.do" title="Vehiculos">Vehiculos</a></li>
				<li><a href="logout.do" title="Salir del sistema">Salir</a></li>
			</ul>
		</div>
	</div>
</header>



<% List<VehicleValueObject> myVehicles = PeugeotService.getMyVehicles(websiteUser.getModelUser().getId()); 
DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
simbolos.setPerMill('.');
DecimalFormat formateador = new DecimalFormat("###,###,###",simbolos);
%>
<% if (myVehicles.isEmpty()) { %>
	Usted no tiene ningun vehiculo asociado
<% } else { %>
	Vehiculo (Dominio) | KMs | Requiere Service | Ultimo Service KM | Fecha de ultimo service <br>
	<% for (VehicleValueObject vehicleValueObject : myVehicles)  { %>
		<input type="radio" name="selectradio" onclick="addService(<%=vehicleValueObject.getVehicle().getId()%>)">
		<%if (vehicleValueObject.getModel() !=  null) { %>
			<%=vehicleValueObject.getModel().getName() %>(<%=vehicleValueObject.getVehicle().getDomain() %>)
		<% } else { %>
			<%=vehicleValueObject.getVehicle().getDomain() %>
		<% } %> |
		<%=vehicleValueObject.getVehicle().getKm() != null ? formateador.format(vehicleValueObject.getVehicle().getKm()) : "-"%> |
		<%if (vehicleValueObject.getVehicle().getNeedsService()) { %>
			Si
		<% } else { %>
			No
		<% } %> |
		<%=vehicleValueObject.getVehicle().getLastservicekm() != null ? formateador.format(vehicleValueObject.getVehicle().getLastservicekm()) : "-"%> |
		<%=vehicleValueObject.getVehicle().getLastservicedate() != null ? DateUtils.formatDateSp(vehicleValueObject.getVehicle().getLastservicedate()) : "-"%>
		<br>
	<% } %>
<% } %>

<div id="addServiceLayer">
<html:form method="POST" action="/addService">
	<div class="scrollable">
		<html:hidden name="AddServiceForm" property="idVehicle" />
		<fieldset>
			<label>km</label>
			<html:text name="AddServiceForm" property="serviceKm" />
			<div id="err.serviceKm" class="errorText textCenter"></div>
		</fieldset>
		<fieldset>
			<label>fecha</label>
			<html:text name="AddServiceForm" property="serviceDate" />
			<div id="err.serviceDate" class="errorText textCenter"></div>
		</fieldset>
	</div>
	<fieldset>
		<input type="submit" id="addServiceButton" value="Agregar" class="buttonSend">
	</fieldset>
</html:form>
</div>

<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<!-- Layer legales -->
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/layer_legales.jspf" %>

<%@ include file="includes/version.jspf" %>
</body>
</html>