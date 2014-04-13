<%@page import="com.tdil.lojack.vlu.VLUUtils"%><%--
--%><%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checkvluaccess.jspf" %><%--
--%><% 
	String id = request.getParameter("id");
	VLUUtils.deleteVLUImport(id);
	response.sendRedirect(request.getContextPath() + "/lojackadm/vlu.jsp");
%>