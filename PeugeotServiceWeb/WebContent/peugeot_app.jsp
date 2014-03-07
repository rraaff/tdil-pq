<%@page import="com.tdil.web.breadcrum.BreadcrumItem"%>
<%@page import="com.tdil.web.breadcrum.Breadcrum"%>
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
<title>Peugeot AXS :: Inicio</title>
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
<script>
	$(document).ready(
			function(){
	<%@ include file="includes/closeLegalesLayer.jsp" %>
	<%@ include file="includes/closeLayers.jspf" %>
	<%@ include file="includes/externalLogins.jspf" %>
				
			}
	);

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
	Breadcrum breadcrums = new Breadcrum()
	.titles("Inicio","Peugeot App")
	.pages("","");
%>
<!-- WEBSITE CONTENT -->
<%@ include file="includes/header.jspf" %>
<%@ include file="includes/page_title.jspf" %>
<%@ include file="includes/action_bar.jspf" %>
<%@ include file="includes/under_shade.jspf" %>
<section id="main_content_regular_page">
	<div class="template_half">
		<h1>�Qu� es Peugeot App?</h1>
		<p class="bajada_light">La aplicaci�n Peugeot AXS es en principio un software que combina servicios provistos por la compa��a LoJack para dar seguridad en sus veh�culos sumado a utilidades relacionadas con el mundo Peugeot. Tales como una secci�n exclusiva para clientes que les permite administrar y seguir de cerca los services que el veh�culo requiere e informaci�n sobre las garant�as.</p>
		
		<h2>Preguntas frecuentes</h2>
		
		<div class="faq_item">
			<h3><a href="download_app.jsp" title="Descargar APP">�La puedo usar en mi celular?</a></h3>
			<p>�Por supuesto!</p>
			<p>Junto con los equipos de desarrollo de LoJack preparamos aplicaciones nativas para dispositivos Android, iOS y Windows Phone que se pueden descargar desde los sitios de descarga habituales como Google Play, iTunes Store y Windows Phone Store.</p>
			<button class="link" onclick="window.location='download_app.jsp';"><span></span>Descargar ahora</button>
		</div>
		<div class="faq_item">
			<h3><a href="download_bb_app.jsp" title="Descargar APP para BlackBerry">�Si tengo un Blackberry la puedo usar?</a></h3>
			<p>Perfectamente.</p>
			<p>Tambi�n hemos preparado versiones accesibles desde BlackBerry del sitio web. No solo para aquellos que posean un dispositivo BB de �ltima generaci�n, que puede acceder a la aplicaci�n nativa, sino que tambi�n hemos preparado una aplicaci�n para equipos BB con sistema operativo 7 o mayor que les permite cargar un sitio adaptado para tales equipos.</p>
			<button class="link" onclick="window.location='download_app.jsp';"><span></span>Descargar ahora</button>
		</div>
		<div class="faq_item">
			<h3><a href="#">�Para qu� sirve la APP?</a></h3>
			<p>La aplicaci�n est� separada en secciones:</p>
			<ul>
				<li><strong>POINTS OF INTERES:</strong> Aplicaci�n para ubicar puntos de inter�s proporcionados por Peugeot en el mapa.</li>
				<li><strong>CAR SECURITY:</strong> Aplicaci�n para proteger sus veh�culos y pasajeros con lo m�s moderno de la tecnolog�a LoJack</li>
				<li><strong>SERVICES:</strong> Secci�n para conocer y administrar los services que se le deben realizar a sus veh�culo e informaci�n de la garant�a de aca uno.</li>
				<li><strong>CONFIGUCACI�N DE EMERGENCIAS:</strong> LoJack provee sistemas para casos de emergencia. En esta secci�n usted deber� completar los datos que se utilizaran para contactar a quienes usted disponga durante una emergencia</li>
				<li><strong>MIS DATOS / MI CLAVE:</strong> Secciones para administras su cuenta de usuario en la APP</li>
				<li><strong>OTRAS:</strong> Accesos a un formulario de contacto y las bases legales del sitio</li> 
			</ul>
		</div>
		<div class="faq_item">
			<h3><a href="javascript:verLegales();" id="legales" title="Ingrese a las pol�ticas del sitio">�El sitio y la aplicaci�n son de libre acceso?</a></h3>
			<p>El sitio no posee restricciones de acceso espec�ficas, pero las cuentas de usuario ser�n entregadas �nicamente a clientes Peugeot.</p>
			<button class="link" onclick="javascript:verLegales();"><span></span>Ver legales</button>
		</div>
	</div>
</section>
<%@ include file="includes/footer_web.jspf" %>

<!-- ALL LAYERS -->
<%@ include file="includes/layer_parking_not_logged.jspf" %>
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/layer_contact.jspf" %>
<%@ include file="includes/layer_legales.jspf" %>
</body>
</html>