<% 
	session.invalidate();
	response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
%>