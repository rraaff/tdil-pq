<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checkadmin.jspf" %><%--
--%><%@ include file="includes/checksyspropaccess.jsp" %><%--
--%><%com.tdil.ljpeugeot.utils.SystemPropertyUtils.updateSystemProperty(request.getParameter("propkey"), request.getParameter("propvalue"));
response.sendRedirect(request.getContextPath() + "/admin/sysproperties.jsp");%>