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
List<AdviceValueObject> advices = new ArrayList<AdviceValueObject>();
Boolean rememberClicked = (Boolean)session.getAttribute(DismissAdvicesAjaxAction.ADVICES_ALREADY_SHOWN);
if (rememberClicked == null) {
	advices = PeugeotService.getAdvices(websiteUser.getModelUser().getId());
	request.getSession().setAttribute(DismissAdvicesAjaxAction.ADVICES_ALREADY_SHOWN, Boolean.TRUE);
}
%>
<script>
	$(document).ready(
			function(){
	<%@ include file="includes/closeLegalesLayer.jsp" %>
	<%@ include file="includes/closeLayers.jspf" %>
	<%@ include file="includes/externalLogins.jspf" %>
				<% if (!advices.isEmpty()) { %>
				centerLayer($(window), $( "#advicesLayer" ));
				$( "#closeadvicesLayer" ).click(function() {
					$( "#advicesLayer" ).fadeOut();
				});
				<% } %>
			}
	);
	function dismiss(idAdvices) {
		$.ajax({
	          type: "GET",
	          cache: false,
	          url: "./dismissAdvices.do",
	          data: {idAdvices: idAdvices},
	          contentType: "application/json; charset=utf-8",
	          success: function(data) {
	        	  $( "#advicesLayer" ).fadeOut();
	          },
	          error: function() {
	        	  $( "#advicesLayer" ).fadeOut();
	        	  errorAjax();
	          }
	      });
	}

	<%@ include file="includes/updatePersonChangePasswordJS.jspf" %>
	<%@ include file="includes/errorAjaxJS.jspf" %>
	<%@ include file="includes/centerLayerJS.jspf" %>

	<%@ include file="includes/openLegalesLayer.jsp" %>
	<%@ include file="includes/contactJS.jspf" %>
	

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

<a href="misServices.jsp">Mis Services</a><br>
<a href="misVehiculos.jsp">Mis Vehiculos</a><br>
<a href="servicesYGarantia.jsp">Ver services oficiales</a><br>
Agencias/Service autorizados<br>

<%@ include file="includes/layer_contact.jspf" %>

<!-- Layer de muestra de avisos -->
<% StringBuilder sb = new StringBuilder();
	if (!advices.isEmpty()) { %>
<div id="advicesLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="advices">
		Aviso <div id="xContainer"><button id="closeadvicesLayer">X</button></div>
		<% for (AdviceValueObject adviceValueObject : advices) { %>
			<% if (adviceValueObject.getAdvice().getServicedate() == null) { %>
				Su vehiculo <%=adviceValueObject.getVehicle().getDomain()%> debe realizar el service a los <%=adviceValueObject.getAdvice().getKm() %> km<br>
			<% } else { %>
			Su vehiculo <%=adviceValueObject.getVehicle().getDomain()%> debe realizar el service antes de la fecha <%=DateUtils.formatDateSp(adviceValueObject.getAdvice().getServicedate())%><br>
			<% } %>
		<% sb.append(adviceValueObject.getAdvice().getId()).append(",");
			} %>
		<a href="javascript:dismiss('<%=sb.toString()%>')">Dismiss</a> Ya los hizo? <a href="./goToMyServices.do">Ver mis services</a>
	</div>
</div>
<% } %>

<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<!-- Layer legales -->
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/layer_legales.jspf" %>

<%@ include file="includes/version.jspf" %>
</body>
</html>