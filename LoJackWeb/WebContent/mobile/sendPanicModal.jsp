<%@page import="com.tdil.lojack.struts.forms.SendPanicForm"%><%--
--%><%@ page info="index"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.RegisterForm"%><%--
--%><%@page import="com.tdil.lojack.utils.AsyncJobUtils"%><%--
--%><%@page import="com.tdil.lojack.gis.model.Alarm"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.AlarmsForm"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.LoginForm"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.DocumentTypeBean"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.StateBean"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.BrandBean"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.CountryBean"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.fields.PersonFieldNames"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.PersonFields"%><%--
--%><%@page import="com.tdil.struts.resources.ApplicationResources"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="../includes/checkThalamusUp.jspf" %><%--
--%><%@ include file="../includes/userLogged.jspf" %><%--
--%><%@ include file="./includes/mustBeLogged.jspf" %><%--
--%><%@ include file="./includes/mustBeHomeUser.jspf" %><%--
--%><!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
</head>
<body>
<% SendPanicForm alarmsForm = (SendPanicForm)session.getAttribute("SendPanicFormMobile"); %>
<% for (Alarm alarm : alarmsForm.getAlarms()) { %>
	Alarma: <%= alarm.getDescription() %><a href="./sendPanicMobile.do?idEntidad=<%=alarm.getIdEntidad()%>">Enviar senial de panico</a><br>
	<hr>
<% } %>

<%@ include file="../includes/version.jspf" %></body>
</html>