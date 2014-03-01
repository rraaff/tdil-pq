<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_reset-styles.css" rel="stylesheet" media="screen">
<link href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_sizers.css" rel="stylesheet" media="screen">
<!-- Bootstrap -->
<link type="text/css" rel="stylesheet" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_tdil.bootstrap.modifier.css" />
<link type="text/css" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_index_menu.css" rel="stylesheet" />
<link type="text/css" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_index_modales.css" rel="stylesheet" />
<link type="text/css" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_index_social.css" rel="stylesheet" />
<link type="text/css" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_flexi-background.css" rel="stylesheet" media="screen" />
<link type="text/css" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_mediaQueries.css" rel="stylesheet" />
<%@ include file="includes/userLogged.jspf" %>
<%@ include file="includes/head.jsp"%>

<style>
#logoIndex { width:250px; margin:0 0 0 -125PX; top:7%; left:50%; position:absolute; }
#thalamusDown { width:450px; margin:0 0 0 -225px; padding:50px; top:30%; left:50%; position:absolute; }
</style>
</head>

<body>
<script src="js/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_flexi-background.js" type="text/javascript" charset="utf-8"></script>
<div id="logoIndex"><img src="images/skin_lj_rl/logos/lo-jack_2_mobile.png" /></div>
<div id="thalamusDown">
	<h1>Sitio en mantenimiento</h1>
	<p class="errorCode">Código de error (APK:isGIS00000927)</p>
	<p>Por favor inténtelo más tarde. Gracias por su paciencia.</p>
</div>
<%@ include file="includes/version.jspf" %></body>
</html>