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
--%><!DOCTYPE html>
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
<body>
<script src="js/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_flexi-background.js" type="text/javascript" charset="utf-8"></script>
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


<%@ include file="includes/layer_contact.jspf" %>

<div class="scrollable">
	<fieldset>
		<label class="ajuste">Provincia</label>
		<select name="idState" id="idState">
			<option value="">Seleccione...</option>
			<% for (State state : PeugeotService.getStates()) { %>
				<option value="<%=state.getId()%>">
				<%=state.getName()%></option>
			<% } %>
		</select>
	</fieldset>
	<fieldset>
		<label class="ajuste">Ciudades</label>
		<select name="idCity" id="idCity">
			<option value="0">Seleccione...</option>
		</select>
	</fieldset>
</div>
<input type="button" onclick="searchDealers()" Value="Buscar"><br>
<div id="dealersLayer">
</div>


<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<!-- Layer legales -->
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/layer_legales.jspf" %>

<%@ include file="includes/version.jspf" %>
</body>
</html>