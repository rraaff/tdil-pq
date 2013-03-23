<%@page import="com.tdil.lojack.gis.model.ChangeLog"%>
<%@page import="java.util.Collection"%>
<%@page import="com.tdil.lojack.gis.LoJackServicesConnector"%>
<%
	String alarmId = request.getParameter("alarmId"); 
Collection<ChangeLog> logCollection = LoJackServicesConnector.getAlarmLog(alarmId);
%>
Log de cambios <br>
<% for (ChangeLog log : logCollection) { %>
<%= log.getDate() %> - <%= log.getHour() %> - <%= log.getAction() %> - <%= log.getUser() %><hr> 
<% } %>
