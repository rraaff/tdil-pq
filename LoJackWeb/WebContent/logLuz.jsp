<%@page import="com.tdil.lojack.gis.model.ChangeLog"%>
<%@page import="java.util.Collection"%>
<%@page import="com.tdil.lojack.gis.LoJackServicesConnector"%>
<%@ include file="includes/userLogged.jspf" %><!--
--><%@ include file="includes/mustBeLogged.jspf" %>
<%
String lightId = request.getParameter("lightId"); 
Collection<ChangeLog> logCollection = LoJackServicesConnector.getLightLog(websiteUser.getGuid(), lightId);
%>
Log de cambios <br>
<% for (ChangeLog log : logCollection) { %>
	<% if (log.hasAvatar()) { %>
		<img src="<%=log.getAvatarURL()%>"/>
	<% } else { %>
		no image
	<% } %> -
	<%= log.getDate() %> - <%= log.getHour() %> - <%= log.getAction() %> - <%= log.getUser() %><hr> 
<% } %>
