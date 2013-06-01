<%@ page info="index"%><!--
--><%@page import="com.tdil.lojack.struts.forms.RegisterForm"%>
<%@page import="com.tdil.lojack.utils.AsyncJobUtils"%>
<%@page import="com.tdil.lojack.gis.model.Alarm"%>
<%@page import="com.tdil.lojack.struts.forms.AlarmsForm"%>
<%@page import="com.tdil.lojack.struts.forms.LoginForm"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.DocumentTypeBean"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.StateBean"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.BrandBean"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.CountryBean"%>
<%@page import="com.tdil.thalamus.client.facade.json.fields.PersonFieldNames"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.PersonFields"%>
<%@page import="com.tdil.struts.resources.ApplicationResources"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="../includes/checkThalamusUp.jspf" %><!--
--><%@ include file="../includes/userLogged.jspf" %><!--
--><%@ include file="./includes/mustBeLogged.jspf" %><!--
--><%@ include file="./includes/mustBeHomeUser.jspf" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
</head>
<body>
<% AlarmsForm alarmsForm = (AlarmsForm)session.getAttribute("AlarmsFormMobile"); %>
<% for (Alarm alarm : alarmsForm.getAlarms()) { %>

Alarma: <%= alarm.getDescription() %><a href="../renameAlarmMobile.do?idEntidad=<%=alarm.getIdEntidad()%>">Cambiar</a><br>
Estado: <%=alarm.getStatus()%><% if (AsyncJobUtils.hasJobInProgress(alarm, websiteUser)) { %>*<% } %><br>
<% if (alarm.isInactive() ) { %>
		<a href="../goToActivateAlarmMobile.do?idEntidad=<%=alarm.getIdEntidad()%>">Activar</a>
	<% } else { %>
		<a href="../goToDeactivateAlarmMobile.do?idEntidad=<%=alarm.getIdEntidad()%>">Desactivar</a>
	<% } %>
<% if (alarm.hasChangeData()) { %>
 			<span class="lastChange">Último cambio: <%=alarm.getLastChangeDate() %></span>
 			<span class="lastAction"><%=alarm.getLastChangeAction() %> por: <%=alarm.getLastChangeUser() %></span>
 			<span class="changesLog"><a href="javascript:seeAlarmLog(<%= alarm.getIdEntidad() %>)">Ver log completo</a></span>
 			<span class="notifyme"><input type="checkbox" onchange="toggleEmailNotification(this, <%=alarm.getIdEntidad()%>)" <%= alarm.isEmailnotification() ? "checked" : ""%>> Quiero que me notifique los cambios de estado por E-Mail</span>
 			<span class="linkToAgenda"><a href="./goToHomeAlarmAgenda.do?idEntidad=<%=alarm.getIdEntidad()%>">Configurar horarios</a> de Armado/Desarmado</span>
 		<% } %>
 		<hr>
<% } %>

</body>
</html>