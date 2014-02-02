<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checkadmin.jspf" %><%--
--%><%com.tdil.ljpeugeot.utils.SystemPropertyUtils.reloadSysProperties();
response.sendRedirect(request.getContextPath() + "/admin/sysproperties.jsp");%>