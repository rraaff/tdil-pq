<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checklogaccess.jspf" %><%--
--%><% 
	String category = request.getParameter("category");
	String level = request.getParameter("level");
	com.tdil.lojack.utils.LoggerUtils.setLogLevel(category, level);
	response.sendRedirect(request.getContextPath() + "/admin/logger.jsp");
%>