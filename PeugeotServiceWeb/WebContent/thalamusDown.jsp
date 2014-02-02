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
<link type="text/css" rel="stylesheet" media="screen" href="css/reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/tdil.bootstrap.modifier.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/index_menu.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/index_modales.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/index_social.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/copyright.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/laruedita.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/home_styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/flexi-background.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/mediaQueries.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/font_embeder.css" />
<%@ include file="includes/userLogged.jspf" %>
<%@ include file="includes/head.jsp"%>


<style>
#logoIndex { width:250px; margin:0 0 0 -125PX; top:7%; left:50%; position:absolute; }
#thalamusDown { width:450px; margin:0 0 0 -225px; padding:50px; top:30%; left:50%; position:absolute; }
</style>
</head>

<body>
<script src="js/flexi-background.js" type="text/javascript" charset="utf-8"></script>
<div id="logoIndex"><img src="images/skin_lj_rl/logos/lo-jack_2_mobile.png" /></div>
<div id="thalamusDown">
	<h1>Sitio en mantenimiento</h1>
	<p class="errorCode">C�digo de error (TH:-524800-coxn)</p>
	<p>Por favor int�ntelo m�s tarde. Gracias por su paciencia.</p>
</div>
<%@ include file="includes/version.jspf" %></body>
</html>