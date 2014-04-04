<%@page import="com.tdil.ljpeugeot.services.PeugeotService"%>
<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checkadmin.jspf" %><%--
--%><%@ include file="includes/checknativeappsaccess.jsp" %><%--
--%><%
	String idST = request.getParameter("id");
	String title = request.getParameter("title");
	String version = request.getParameter("version");
	String url = request.getParameter("url");
	
	PeugeotService.updateNativeApp(idST, title, version, url);
response.sendRedirect(request.getContextPath() + "/admin/nativeApps.jsp");%>