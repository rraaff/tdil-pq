<% 	if (session == null) { %>
	<jsp:forward page="./login.jsp">
		<jsp:param name="error" value="notlogged"/>
	</jsp:forward>
<%	}
	com.tdil.users.User user = (com.tdil.users.User)session.getAttribute("user");
	if (user == null) { %>
	<jsp:forward page="./boLogin.jsp">
		<jsp:param name="error" value="notlogged"/>
	</jsp:forward>
<% 	} %>