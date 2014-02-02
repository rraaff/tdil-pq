<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checkadmin.jspf" %><%--
--%><%com.tdil.ljpeugeot.utils.SystemPropertyUtils.toggleDelete(Integer.parseInt(request.getParameter("id")));
response.sendRedirect(request.getContextPath() + "/admin/sysproperties.jsp");%>