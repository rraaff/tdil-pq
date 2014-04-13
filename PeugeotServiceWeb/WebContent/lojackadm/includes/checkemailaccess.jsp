<% if (su.getEmailtemplateaccess() == null || !su.getEmailtemplateaccess().equals(1)) { %>
	<jsp:forward page="./login.jsp"></jsp:forward>
<% return;
} %>
