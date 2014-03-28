<%@page import="com.tdil.ljpeugeot.model.valueobjects.AlertValueObject"%>
<%@page import="com.tdil.ljpeugeot.model.Alert"%>
<%@page import="com.tdil.ljpeugeot.services.PeugeotService"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%List<AlertValueObject> alertsPending = PeugeotService.getAlertsPending();%>
<% if (alertsPending.size() == 0) { %>
	<span class="no_alerts">No hay alertas...</span>
<% } else { %>
	<h3>Alerta Pendiente</h3>
	<% for (AlertValueObject alert : alertsPending)  { %>
		<ul class="alert_row">
			<li class="alert_id"><%=alert.getAlert().getId()%></li>
			<li class="alert_userfirstname"><%=alert.getUser().getFirstname()%></li>
			<li class="alert_userlassname"><%=alert.getUser().getLastname()%></li> 
			<li class="alert_actions"><a class="link_as_button" href="takeAlert.jsp?alertId=<%=alert.getAlert().getId()%>">Tomar</a></li>
		</ul>
	<% } %>
<% } %>