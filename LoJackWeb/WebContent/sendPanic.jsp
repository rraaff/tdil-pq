<%@page import="com.tdil.lojack.gis.model.Alarm"%>
<%@page import="java.util.Collection"%>
<%@page import="com.tdil.lojack.gis.LoJackServicesConnector"%>
<%@ include file="includes/userLogged.jspf" %>
<%
Collection<Alarm> alarms = LoJackServicesConnector.getAlarms(websiteUser.getGuid());
%>
Enviar senial de panico <br>
<% for (Alarm alarm : alarms) { %>
<a href="javascript:doSendPanic('<%= alarm.getId() %>')"><%= alarm.getId() %> - <%=alarm.getDescription() %></a><hr> 
<% } %>
