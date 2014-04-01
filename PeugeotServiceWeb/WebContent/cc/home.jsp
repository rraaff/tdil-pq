<%@page import="com.tdil.ljpeugeot.model.valueobjects.AlertValueObject"%><%--
--%><%@page import="com.tdil.ljpeugeot.services.PeugeotService"%><%--
--%><%@page import="com.tdil.ljpeugeot.model.Alert"%><%--
--%><%@page import="com.tdil.utils.StringUtils"%><%--
--%><%@page import="com.tdil.struts.resources.ApplicationResources"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.SearchUserForm"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="includes/userLogged.jspf" %><%--
--%><%@ include file="includes/mustBeLogged.jspf" %><%--
--%><%AlertValueObject alert =  PeugeotService.getAlertInProgress(websiteUser.getId()); 
if (alert!= null) { %><jsp:forward page="alertInProgress.jsp"></jsp:forward><%}%>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Peugeot :: CallCenter Application</title>
<link rel="icon" href="../favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_website.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_font_embeder.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_backdoor.css" />
<%@ include file="includes/headLogged.jsp" %>
</head>
<body>
<%@ include file="../admin/includes/header.jsp" %>
<%@ include file="includes/menu.jspf" %>
<section id="titles">
	<h1>LoJack Peugeot CallCenter Application</h1>
	<h2>Atencion de alertas</h2>
</section>
<section id="content">
	<article>
		<div id="alertContent"></div>
		<!--  p class="information"><strong>Importante: </strong></p -->
	</article>
</section>

<script type="text/javascript">
$('#alertContent').load('getAlerts.jsp').fadeIn("slow");
var auto_refresh = setInterval(
function () {
	$('#alertContent').load('getAlerts.jsp').fadeIn("slow");
}, 10000); // refresh every 10000 milliseconds
</script>
</body>
</html>