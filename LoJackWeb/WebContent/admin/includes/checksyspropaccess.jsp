<% if (su.getSyspropaccess() == null || !su.getSyspropaccess().equals(1)) { %>
	<jsp:forward page="./login.jsp"></jsp:forward>
<% return;
} %>
