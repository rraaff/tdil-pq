<%@page import="com.tdil.ljpeugeot.model.SystemUser"%>
<%
	SystemUser su = com.tdil.ljpeugeot.utils.SystemUserUtils.getSystemUser(request.getParameter("username"), request.getParameter("password"));
	if (su == null) {
		response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
	} else {
		session.setAttribute("sysuser", su);
		response.sendRedirect(request.getContextPath() + "/admin/home.jsp");
	}
%>