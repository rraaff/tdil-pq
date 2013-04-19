<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.tdil.lojack.gis.model.Alarm"%>
<%@page import="java.util.Collection"%>
<%@page import="com.tdil.lojack.gis.LoJackServicesConnector"%>
<%@ include file="includes/userLogged.jspf" %><!--
--><%@ include file="includes/mustBeLogged.jspf" %>
<%
Collection<Alarm> alarms = LoJackServicesConnector.getAlarms(websiteUser.getGuid(), websiteUser.getLojackUserId(), websiteUser.getTimezoneOffset(), websiteUser.getTimezoneName());
%>
Enviar senial de panico <br>
<% for (Alarm alarm : alarms) { %>
<a href="javascript:doSendPanic('<%=StringEscapeUtils.escapeJavaScript(alarm.getDescription()) %>', '<%= alarm.getId() %>')"><%= alarm.getId() %> - <%=alarm.getDescription() %></a><hr> 
<% } %>
