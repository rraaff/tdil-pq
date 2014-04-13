<%@page import="com.tdil.ljpeugeot.services.PeugeotService"%>
<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checkadmin.jspf" %><%--
--%><%@ include file="includes/checknativeappsaccess.jsp" %><%--
--%><%PeugeotService.toggleDeleteNativeApp(request.getParameter("id"));
response.sendRedirect(request.getContextPath() + "/lojackadm/nativeApps.jsp");%>