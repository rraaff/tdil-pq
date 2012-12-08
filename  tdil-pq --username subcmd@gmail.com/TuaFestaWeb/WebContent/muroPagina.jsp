<%@page import="com.tdil.utils.DateUtils"%>
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
List<WallWrittingValueObject> list = WallUtils.getWallWritings(pageStartInt, idwallInt, idprofInt);
boolean hasnext = list.size() > 10;
%>
<% int index = 0;
	for (WallWrittingValueObject wwvo : list) { 
		if(index < 10) { %>
			<div><%=wwvo.getOriginaltext() %> (<%=DateUtils.formatDateSp(wwvo.getCreationdate())%> - <%=wwvo.getAuthorName()%>)</div>
		<% }
		index = index + 1;
		%>
<% } %>
<% if (hasnext) { %>
<div id="more<%=nextPageStart %>" class="morebox">
	<a href="#" class="more" id="<%=nextPageStart %>">Ver mas</a>
</div>
<% } %>