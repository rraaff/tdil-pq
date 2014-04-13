<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checksyspropaccess.jsp" %><%--
--%><%
com.tdil.lojack.utils.SystemPropertyUtils.reloadSysProperties();
response.sendRedirect(request.getContextPath() + "/lojackadm/sysproperties.jsp");
%>