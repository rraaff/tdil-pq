<%@page import="com.tdil.utils.StringUtils"%>
<%@page import="com.tdil.lojack.model.SystemProperty"%>
<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checksyspropaccess.jsp" %><%--
--%><html>
<body>
<h1>Sys Properties</h1>
<%@ include file="includes/menu.jspf" %>

<a href="doLoadProperties.jsp">Actualizar</a> el sistema con las properties<br><br>

<b>Importante</b>: Los cambios realizados a las sys properties no son tomados automaticamente.<br>
Luego de realizar los cambios cliquee actualizar para que el sistema los tome.<br><br>

Cambiar sysproperty
<form action="./doUpdateSysProperty.jsp">
Clave <input type="text" style="width: 250px;" name="propkey" value="<%=StringUtils.notNullValueOf(request.getParameter("propkey"))%>">
Valor <input type="text" style="width: 250px;" name="propvalue" value="<%=StringUtils.notNullValueOf(request.getParameter("propvalue"))%>">
<input type="submit">
</form>
<hr>

<table border="1">
<tr>
	<td>Clave</td>
	<td>Valor</td>
	<td>Descripcion</td>
	<td>Borrada</td>
	<td>Editar</td>
	<td>Borrar</td>
</tr>
<% for (SystemProperty sp : com.tdil.lojack.utils.SystemPropertyUtils.getSystemProperties()) { %>
<tr>
	<td><%=sp.getPropkey()%></td>
	<td><%=sp.getPropvalue()%></td>
	<td><%=sp.getDescription()%></td>
	<td><%=sp.getDeleted()%></td>
	<td><a href="./sysproperties.jsp?propkey=<%=sp.getPropkey()%>&propvalue=<%=sp.getPropvalue()%>">Editar</a></td>
	<td><% if (sp.getDeleted() == 1) { %>
		<a href="doToggleDeleteSysProperty.jsp?id=<%=sp.getId() %>">Activar</a>
		<% } else { %>
		<a href="doToggleDeleteSysProperty.jsp?id=<%=sp.getId() %>">Borrar</a>
		<% } %>
	</td>
</tr>
<% } %>
</table>
</body>
</html>