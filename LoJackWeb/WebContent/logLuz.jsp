<%@page import="com.tdil.lojack.utils.WebsiteUserUtils"%>
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
<% for (ChangeLog log : logCollection) {
	com.tdil.lojack.model.WebsiteUser logUsr = WebsiteUserUtils.getWebSiteUser(log.getLojackUserId()); %>
	<% if (WebsiteUserUtils.hasAvatar(logUsr)) { %>
		<img src="./download.st?id=<%=logUsr.getIdAvatar()%>&type=PUBLIC&ext=<%=logUsr.getExtAvatar()%>" width="30" height="30" align="absmiddle">
	<% } else { %>
		<img src="images/na.gif" width="30" height="30" align="absmiddle">
	<% } %> -
	<%= log.getDate() %> - <%= log.getHour() %> - <%= log.getAction() %> - <%= log.getUser() %><hr> 
<% } %>
