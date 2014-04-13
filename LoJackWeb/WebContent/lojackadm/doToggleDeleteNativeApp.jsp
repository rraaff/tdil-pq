<%@page import="com.tdil.lojack.services.LoJackService"%><%--
--%><%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checknativeappsaccess.jsp" %><%--
--%><%LoJackService.toggleDeleteNativeApp(request.getParameter("id"));
response.sendRedirect(request.getContextPath() + "/lojackadm/nativeApps.jsp");%>