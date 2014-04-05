<%@page import="com.tdil.lojack.services.LoJackService"%><%--
--%><%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checknativeappsaccess.jsp" %><%--
--%><%
	String idST = request.getParameter("id");
	String code = request.getParameter("code");
	String title = request.getParameter("title");
	String version = request.getParameter("version");
	String url = request.getParameter("url");
	String image = request.getParameter("image");
	String summary = request.getParameter("summary");
	
	LoJackService.updateNativeApp(idST, code, title, version, url, image, summary);
response.sendRedirect(request.getContextPath() + "/admin/nativeApps.jsp");%>