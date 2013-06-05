<%@page import="com.tdil.lojack.utils.WebsiteUserUtils"%>
<%@page import="com.tdil.lojack.gis.model.ChangeLog"%>
<%@page import="java.util.Collection"%>
<%@page import="com.tdil.lojack.gis.LoJackServicesConnector"%>
<%@ include file="includes/userLogged.jspf" %><!--
--><%@ include file="includes/mustBeLogged.jspf" %><!--
--><%@ include file="includes/mustBeHomeUser.jspf" %>
<%
int idEntidad = Integer.valueOf(request.getParameter("idEntidad"));
int idLuz = Integer.valueOf(request.getParameter("idLuz"));
Collection<ChangeLog> logCollection = LoJackServicesConnector.getLightLog(websiteUser, idEntidad, idLuz);
%>
<div class="logLayerContent">
	<% for (ChangeLog log : logCollection) {
		com.tdil.lojack.model.WebsiteUser logUsr = WebsiteUserUtils.getWebSiteUserByHomeUserId(log.getLojackUserId()); %>
		<div id="logRow">
			<span>
				<% if (logUsr != null && WebsiteUserUtils.hasAvatar(logUsr)) { %>
					<img src="./download.st?id=<%=logUsr.getIdAvatar()%>&type=PUBLIC&ext=<%=logUsr.getExtAvatar()%>" />
				<% } else { %>
					<img src="images/skin_lj_rl/logos/avatarBase.png" />
				<% } %>
				<p> - <%= log.getDate() %> - <%= log.getHour() %> - <%= log.getAction() %> - <%= log.getUser() %></p>
			</span>
		</div>
	<% } %>
</div>