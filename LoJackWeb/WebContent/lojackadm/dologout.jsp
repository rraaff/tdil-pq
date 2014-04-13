<% 
	session.invalidate();
	response.sendRedirect(request.getContextPath() + "/lojackadm/login.jsp");
%>