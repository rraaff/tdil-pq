<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checkadmin.jspf" %><%--
--%><%@ include file="includes/checklogaccess.jspf" %><%--
--%><%String category = request.getParameter("category");
	String level = request.getParameter("level");
	com.tdil.ljpeugeot.utils.LoggerUtils.setLogLevel(category, level);
	response.sendRedirect(request.getContextPath() + "/lojackadm/logger.jsp");%>