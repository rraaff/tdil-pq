<%@page import="com.tdil.ljpeugeot.model.SystemUser"%>
<%  if (!com.tdil.ljpeugeot.servlet.SimpleCaptchaServlet.validateCaptchaWithSession(request.getParameter("code"), session)) {
		response.sendRedirect(request.getContextPath() + "/lojackadm/login.jsp?err=invalidCode");
	} else {
		SystemUser su = com.tdil.ljpeugeot.utils.SystemUserUtils.getSystemUser(request.getParameter("username"), request.getParameter("password"));
		if (su == null || !su.isAdmin()) {
			response.sendRedirect(request.getContextPath() + "/lojackadm/login.jsp");
		} else {
			session.setAttribute("sysuser", su);
			response.sendRedirect(request.getContextPath() + "/lojackadm/home.jsp");
		}
	}
%>