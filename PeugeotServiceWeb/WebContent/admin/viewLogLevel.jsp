<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checklogaccess.jspf" %><%--
--%><html>
<body>
<%
	String category = request.getParameter("category");
	String level[] = com.tdil.ljpeugeot.utils.LoggerUtils.getLevelFor(category);
%>
<%=level[0] %> <%=level[1] %>
</body>
</html>