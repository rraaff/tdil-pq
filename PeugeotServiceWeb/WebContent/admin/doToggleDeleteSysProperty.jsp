<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checksyspropaccess.jsp" %><%--
--%><%com.tdil.ljpeugeot.utils.SystemPropertyUtils.toggleDelete(Integer.parseInt(request.getParameter("id")));
response.sendRedirect(request.getContextPath() + "/admin/sysproperties.jsp");%>