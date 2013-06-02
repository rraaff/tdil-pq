<%@page import="com.tdil.lojack.struts.forms.AlarmLogForm"%>
<%@page import="com.tdil.lojack.utils.WebsiteUserUtils"%>
<%@page import="com.tdil.lojack.gis.model.ChangeLog"%>
<%@page import="java.util.Collection"%>
<%@page import="com.tdil.lojack.gis.LoJackServicesConnector"%>
<%@ include file="../includes/userLogged.jspf" %><!--
--><%@ include file="includes/mustBeLogged.jspf" %><!--
--><%@ include file="includes/mustBeHomeUser.jspf" %>
<%
AlarmLogForm alarmLogForm = (AlarmLogForm)session.getAttribute("AlarmLogFormMobile");
Collection<ChangeLog> logCollection = alarmLogForm.getLogCollection();
%>
<div class="logLayerContent">
	<% for (ChangeLog log : logCollection) {
		com.tdil.lojack.model.WebsiteUser logUsr = WebsiteUserUtils.getWebSiteUserByHomeUserId(log.getLojackUserId()); %>
		<% if (logUsr != null && WebsiteUserUtils.hasAvatar(logUsr)) { %>
			<img src="./download.st?id=<%=logUsr.getIdAvatar()%>&type=PUBLIC&ext=<%=logUsr.getExtAvatar()%>" width="42" height="42" align="absmiddle">
		<% } else { %>
			<img src="images/na.gif" width="42" height="42" align="absmiddle">
		<% } %> -
		<%= log.getDate() %> - <%= log.getHour() %> - <%= log.getAction() %> - <%= log.getUser() %><hr>
	<% } %>
</div>