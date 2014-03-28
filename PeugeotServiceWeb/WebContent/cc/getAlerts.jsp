<%@page import="com.tdil.ljpeugeot.model.valueobjects.AlertValueObject"%>
<%@page import="com.tdil.ljpeugeot.model.Alert"%>
<%@page import="com.tdil.ljpeugeot.services.PeugeotService"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%List<AlertValueObject> alertsPending = PeugeotService.getAlertsPending();%>
<% if (alertsPending.size() == 0) { %>
	No hay alertas...
<% } else { %>
	<% for (AlertValueObject alert : alertsPending)  { %>
		<%=alert.getAlert().getId()%>
		<%=alert.getUser().getFirstname()%>
		<%=alert.getUser().getLastname()%> 
		<a href="takeAlert.jsp?alertId=<%=alert.getAlert().getId()%>">tomar</a><br>
	<% } %>
<% } %>