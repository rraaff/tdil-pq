<%@page import="com.tdil.ljpeugeot.prevent.model.Vehicle"%>
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
<link type="text/css" rel="stylesheet" media="screen" href="fonts/peugeot/fonts.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_website.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_website_logged.css" />
<!--[if lt IE 9]>
	<link type="text/css" rel="stylesheet" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_ie8-fixes.css" />
<![endif]-->
<%@ include file="includes/headLogged.jsp" %>
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
	<div class="page_header">
		<h2>Seleccione el vehículo</h2>
		<p></p>
	</div>
	<div class="template_baratleft">
		<div class="bar">
			<ul class="selection_module">
				<li><a href="#">Todos</a></li>
				<% EditVehicleDataForm editVehicleDataForm = (EditVehicleDataForm)session.getAttribute("EditVehicleDataForm");%>
				<% for (Vehicle vehicle : editVehicleDataForm.getVehicles()) { %>
					<li><a href="./editVehicleData.do?id=<%=vehicle.getId() %>"><%=vehicle.getDescription() %></a></li>
				<% } %>
			</ul>
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