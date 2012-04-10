<% 	if (session == null) { %>
	<jsp:forward page="./login.jsp">
		<jsp:param name="error" value="notlogged"/>
	</jsp:forward>
<%	}
	com.tdil.djmag.model.SystemUser user = (com.tdil.djmag.model.SystemUser)session.getAttribute("user");
	if (user == null) { %>
	<jsp:forward page="./boLogin.jsp">
		<jsp:param name="error" value="notlogged"/>
	</jsp:forward>
<% 	} %>