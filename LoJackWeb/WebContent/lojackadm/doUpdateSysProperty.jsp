<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checksyspropaccess.jsp" %><%--
--%><%
com.tdil.lojack.utils.SystemPropertyUtils.updateSystemProperty(request.getParameter("propkey"), request.getParameter("propvalue"));
response.sendRedirect(request.getContextPath() + "/lojackadm/sysproperties.jsp");
%>