<%@page import="com.tdil.ljpeugeot.feeds.ImportUtils"%><%--
--%><%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checkadmin.jspf" %><%--
--%><%String id = request.getParameter("id");
	ImportUtils.deleteImport(id);
	response.sendRedirect(request.getContextPath() + "/lojackadm/" + request.getParameter("dest"));%>