<%@page import="com.tdil.lojack.vlu.VLUUtils"%><%--
--%><%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checklogaccess.jspf" %><%--
--%><% 
	String id = request.getParameter("id");
	VLUUtils.deleteVLUImport(id);
	response.sendRedirect(request.getContextPath() + "/admin/vlu.jsp");
%>