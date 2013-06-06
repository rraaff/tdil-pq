<%@ include file="includes/tryModal.jspf" %>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.tdil.lojack.gis.model.Alarm"%>
<%@page import="java.util.Collection"%>
<%@page import="com.tdil.lojack.gis.LoJackServicesConnector"%>
<%@ include file="includes/userLogged.jspf" %><!--
--><%@ include file="includes/mustBeLogged.jspf" %><!--
--><%@ include file="includes/mustBeHomeUser.jspf" %>
<%
Collection<Alarm> alarms = LoJackServicesConnector.getAlarms(websiteUser);
%>
<h4>Haga clic en la alarma en la que quiere enviar una señal de pánico</h4>

<% for (Alarm alarm : alarms) { %>
<a href="javascript:doSendPanic('<%=StringEscapeUtils.escapeJavaScript(alarm.getDescription()) %>', <%= alarm.getIdEntidad() %>)"><%= alarm.getIdEntidad() %> - <%=alarm.getDescription() %></a><hr> 
<% } %>
<%@ include file="includes/catchModal.jspf" %>