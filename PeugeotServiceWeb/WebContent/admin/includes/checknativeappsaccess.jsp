<% if (su.getNativeappaccess() == null || !su.getNativeappaccess().equals(1)) { %>
	<jsp:forward page="./login.jsp"></jsp:forward>
<% return;
} %>
