<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checksyspropaccess.jsp" %><%--
--%><%com.tdil.ljpeugeot.utils.SystemPropertyUtils.reloadSysProperties();
response.sendRedirect(request.getContextPath() + "/admin/sysproperties.jsp");%>