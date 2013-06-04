<%@page import="com.tdil.lojack.utils.AsyncJobUtils"%>
<%@page import="com.tdil.lojack.gis.model.Light"%>
<%@page import="com.tdil.lojack.struts.forms.LightsForm"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><!--
--><%@ page info="home"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="../includes/checkThalamusUp.jspf" %><!--
--><%@ include file="../includes/userLogged.jspf" %><!--
--><%@ include file="includes/mustBeLogged.jspf" %><!--
--><%@ include file="includes/mustBeHomeUser.jspf" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Fin Switches -->
<% LightsForm lightsForm = (LightsForm)session.getAttribute("LightsFormMobile"); %>
</head>
<body>

<% for (Light light : lightsForm.getLights()) { %>
Luz: <%= light.getDescription() %><a href="./goToRenameLightMobile.do?idEntidad=<%=light.getIdEntidad()%>&idLuz=<%=light.getIdLuz()%>">Cambiar</a><br>

<% if (AsyncJobUtils.displayRandom(light, websiteUser)) { %>
	RANDOM <% if (AsyncJobUtils.hasJobInProgress(light, websiteUser)) { %>*<% } %>
	<a href="./deactivateRandomSequenceMobile.do?idEntidad=<%=light.getIdEntidad()%>&idLuz=<%=light.getIdLuz()%>">RANDOM OFF</a>
<% } else  { %>
	<% if (AsyncJobUtils.displayOn(light, websiteUser)) { %>
		Encendida<% if (AsyncJobUtils.hasJobInProgress(light, websiteUser)) { %>*<% } %>
		<a href="./turnOffLightMobile.do?idEntidad=<%=light.getIdEntidad()%>&idLuz=<%=light.getIdLuz()%>">Apagar</a>
		<a href="./activateRandomSequenceMobile.do?idEntidad=<%=light.getIdEntidad()%>&idLuz=<%=light.getIdLuz()%>">RANDOM ON</a>
	<% } else  { %>
		<% if (AsyncJobUtils.displayOff(light, websiteUser)) { %>
			Apagada<% if (AsyncJobUtils.hasJobInProgress(light, websiteUser)) { %>*<% } %>
			<a href="./turnOnLightMobile.do?idEntidad=<%=light.getIdEntidad()%>&idLuz=<%=light.getIdLuz()%>">Encender</a>
			<a href="./activateRandomSequenceMobile.do?idEntidad=<%=light.getIdEntidad()%>&idLuz=<%=light.getIdLuz()%>">RANDOM ON</a>
		<% } else  { %>
			Estado desconocido<% if (AsyncJobUtils.hasJobInProgress(light, websiteUser)) { %>*<% } %>
			<a href="./turnOnLightMobile.do?idEntidad=<%=light.getIdEntidad()%>&idLuz=<%=light.getIdLuz()%>">Encender</a>
			<a href="./turnOffLightMobile.do?idEntidad=<%=light.getIdEntidad()%>&idLuz=<%=light.getIdLuz()%>">Apagar</a>
			<a href="./activateRandomSequenceMobile.do?idEntidad=<%=light.getIdEntidad()%>&idLuz=<%=light.getIdLuz()%>">RANDOM ON</a>
			<a href="./deactivateRandomSequenceMobile.do?idEntidad=<%=light.getIdEntidad()%>&idLuz=<%=light.getIdLuz()%>">RANDOM OFF</a>
		<% } %>
	<% } %>
<% } %>


<% if (light.hasChangeData()) { %>
	Último cambio: <%=light.getLastChangeDate() %>
	<%=light.getLastChangeAction() %> por: <%=light.getLastChangeUser() %><br>
<% } %>
<a href="javascript:seeLightLog(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">Ver log completo</a><br>

<% if (light.isEmailnotification()) { %>
	Email notificacion  <a href="./deactivateLightNotificacionMobile.do?idEntidad=<%=light.getIdEntidad()%>&idLuz=<%=light.getIdLuz()%>">Desactivar email</a>
<% } else { %>
	No Email notificacion <a href="./activateLightNotificacionMobile.do?idEntidad=<%=light.getIdEntidad()%>&idLuz=<%=light.getIdLuz()%>">Activar email</a>
<% } %>

<a href="./goToHomeLightAgendaMobile.do?idEntidad=<%=light.getIdEntidad()%>&idLuz=<%=light.getIdLuz()%>">Configurar horarios</a> de Encendido/Apagado</span>


 		<hr>
<% } %>

	
</body>
</html>