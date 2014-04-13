<%@page import="com.tdil.lojack.model.SystemUser"%>
<%  if (!com.tdil.lojack.servlet.SimpleCaptchaServlet.validateCaptchaWithSession(request.getParameter("code"), session)) {
		response.sendRedirect(request.getContextPath() + "/lojackadm/login.jsp?err=invalidCode");
	} else {
		SystemUser su = com.tdil.lojack.utils.SystemUserUtils.getSystemUser(request.getParameter("username"), request.getParameter("password"));
		if (su == null) {
			response.sendRedirect(request.getContextPath() + "/lojackadm/login.jsp");
		} else {
			session.setAttribute("sysuser", su);
			response.sendRedirect(request.getContextPath() + "/lojackadm/home.jsp");
		}
	}
%>