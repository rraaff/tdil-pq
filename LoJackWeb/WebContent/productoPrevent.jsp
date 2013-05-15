<%@page import="com.tdil.lojack.utils.SystemPropertiesKeys"%>
<%@page import="com.tdil.lojack.utils.SystemPropertyUtils"%>
<%@page import="com.tdil.lojack.struts.forms.CameraForm"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><!--
--><%@ page info="home"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="includes/checkThalamusUp.jspf" %><!--
--><%@ include file="includes/userLogged.jspf" %><!--
--><%@ include file="includes/mustBeLogged.jspf" %><!--
--><%@ include file="includes/mustBePreventUser.jspf" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/reset-styles" rel="stylesheet" media="screen">
<link href="css/sizers.css" rel="stylesheet" media="screen">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="js/bootstrap.min.js"></script>

<%@ include file="includes/headLogged.jsp" %>


<link href="css/tdil.bootstrap.modifier.css" rel="stylesheet" media="screen" />
<link href="css/index_modales.css" rel="stylesheet" type="text/css" />
<link href="css/index_social.css" rel="stylesheet" type="text/css" />
<link href="css/copyright.css" rel="stylesheet" type="text/css" />

<style type="text/css">
#productsMenu ul li.tabCar {
	background:#f05224;
}
button.iconBackHome,
button.iconMaxSpeed,
button.iconZSeguras,
button.iconGetPosit,
button.iconPhoneAdm {
	border:none;
	background: transparent;
	background: url(images/skin_lj_rl/webApp/car/control_home_32x32.png);
	background-repeat: no-repeat;
	background-position: 0 0;
	width: 32px;
	height: 32px;
	margin: 0 10px;
	padding: 0;
	cursor: pointer;
}
button.iconMaxSpeed { background: url(images/skin_lj_rl/webApp/car/control_maxSpeed_32x32.png); }
button.iconZSeguras { background: url(images/skin_lj_rl/webApp/car/control_zSeguras_32x32.png); }
button.iconGetPosit { background: url(images/skin_lj_rl/webApp/car/control_getPosit_32x32.png); }
button.iconPhoneAdm { background: url(images/skin_lj_rl/webApp/car/control_phoneAdm_32x32.png); }
</style>
</head>
<body>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/clientMainManu.jsp" %>
<section id="content">
	<div class="pageWrapper" style="height:460px; background:#000;">
	 	<span style="color:#fff;">acá va el mapa</span>
	</div>
</section>
<section id="controls">
	<div class="basicControls">
		<button class="iconBackHome" href="./goToVehiculesSpeedLimits.do">&nbsp;</button>
		<button class="iconMaxSpeed" href="./goToVehiculesSpeedLimits.do">&nbsp;</button>
		<button class="iconZSeguras" href="./goToVehiculesSecureZones.do">&nbsp;</button>
		<button class="iconGetPosit" href="./goToVehiculesForMap.do">&nbsp;</button>
		<button class="iconPhoneAdm" href="./goToVehiculesForPhone.do">&nbsp;</button>
	</div>
</section>
<section id="zoomSection">
	<div class="zoomControls">
		<button class="icon_zoom_in" onclick="javascript:Mapa.ZoomIn();" value="ZoomIn">&nbsp;</button>
		<button class="icon_zoom_out" onclick="javascript:Mapa.ZoomOut();" value="ZoomOut">&nbsp;</button>
	</div>
</section>

<%@ include file="includes/footerProductoHome.jsp" %>

</body>
</html>