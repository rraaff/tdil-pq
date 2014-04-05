<%@page import="com.tdil.ljpeugeot.services.PeugeotService"%>
<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checkadmin.jspf" %><%--
--%><%@ include file="includes/checknativeappsaccess.jsp" %><%--
--%><%
	String idST = request.getParameter("id");
	String code = request.getParameter("code");
	String title = request.getParameter("title");
	String version = request.getParameter("version");
	String url = request.getParameter("url");
	String summary = request.getParameter("summary");
	
	PeugeotService.updateNativeApp(idST, code, title, version, url, summary);
response.sendRedirect(request.getContextPath() + "/admin/nativeApps.jsp");%>