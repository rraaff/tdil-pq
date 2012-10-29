<%@page import="com.tdil.tuafesta.struts.forms.WallModerationForm"%>
<%@page import="com.tdil.tuafesta.utils.WallUtils"%>
<%@page import="com.tdil.tuafesta.model.valueobjects.WallWrittingValueObject"%>
<%@page import="java.util.List"%>
<% 

String idprof = request.getParameter("idprof");
int idprofInt = Integer.valueOf(idprof);
String idwall = request.getParameter("idwall");
int idwallInt = Integer.valueOf(idwall);
String pageStart = request.getParameter("items");
int pageStartInt = Integer.valueOf(pageStart);
int nextPageStart = pageStartInt + 10;
List<WallWrittingValueObject> list = WallUtils.getWallWritingsModerate(pageStartInt, idwallInt, idprofInt);

boolean hasnext = list.size() > 10;
WallModerationForm wallModerationForm = (WallModerationForm)session.getAttribute("WallModerationForm");
wallModerationForm.setAjaxLoaded(true);
if (hasnext) {
	wallModerationForm.getWallWritting().addAll(list.subList(0, 9));
} else {
	wallModerationForm.getWallWritting().addAll(list);
	wallModerationForm.setHasMore(false);
}
%>
<% int index = 0;
for (WallWrittingValueObject wwvo : list) { 
	if(index < 10) { %>
	<div>
		<%=wwvo.getOriginaltext() %> (<%=wwvo.getAuthorName()%>)
		<% if (wwvo.isAuthorProfesional()) { %>
			<a href="./loadWallWrittingToEdit.do?id=<%=wwvo.getId() %>">Editar</a>
			<a href="./deleteWallWritting.do?id=<%=wwvo.getId() %>">Borrar</a>
		<% } else { %>
			<% if (wwvo.getResponsePending().equals(1)) { %>
				<a href="./loadWallWrittingToAnswer.do?id=<%=wwvo.getId() %>">Responder</a>
				<a href="./markAsRespondedWallWritting.do?id=<%=wwvo.getId() %>">Marcar como respondido</a>
			<% } %>
			<a href="./deleteWallWritting.do?id=<%=wwvo.getId() %>">Borrar</a>
		<% } %>
	</div>
	<% }
		index = index + 1;
	%>
<% } %>
<% if (hasnext) { %>
	<div id="more<%=nextPageStart %>" class="morebox">
		<a href="#" class="more" id="<%=nextPageStart %>">Ver mas</a>
	</div>
<% } %>