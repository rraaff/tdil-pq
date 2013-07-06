<%@page import="com.tdil.lojack.model.SystemProperty"%>
<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checksyspropaccess.jsp" %><%--
--%><html>
<body>
<h1>Home</h1>
<%@ include file="includes/menu.jspf" %>

<a href="doLoadProperties.jsp">Actualizar</a> el sistema con las properties<br><br>

<table border="1">
<tr>
	<td>Clave</td>
	<td>Valor</td>
	<td>Borrada</td>
	<td></td>
</tr>
<% for (SystemProperty sp : com.tdil.lojack.utils.SystemPropertyUtils.getSystemProperties()) { %>
<tr>
	<td><%=sp.getPropkey()%></td>
	<td><%=sp.getPropvalue()%></td>
	<td><%=sp.getDeleted()%></td>
	<td><% if (sp.getDeleted() == 1) { %>
		<a href="doToggleDeleteSysProperty.jsp?id=<%=sp.getId() %>">Activar</a>
		<% } else { %>
		<a href="doToggleDeleteSysProperty.jsp?id=<%=sp.getId() %>">Borrar</a>
		<% } %>
	</td>
</tr>
<% } %>
</table>
<hr>
Cambiar sysproperty
<form action="./doUpdateSysProperty.jsp">
Clave <input type="text" name="propkey"><br>
Valor <input type="text" name="propvalue"><br><br>
<input type="submit">
</form>

</body>
</html>