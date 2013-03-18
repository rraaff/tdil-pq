<%@page import="com.facebook.FacebookTestData"%>
<%
	FacebookTestData facebookTestData = (FacebookTestData)session.getAttribute("FacebookTestData");
	if (facebookTestData == null) {
		facebookTestData = new FacebookTestData();
		session.setAttribute("FacebookTestData", facebookTestData);
	}
	boolean connected = facebookTestData.getAppName() != null;
%>
<html>
<body>

<% if (connected) { %>
	Conectado a <%=facebookTestData.getAppName()%>
<% } else { %>
	No conectado
<% } %> | <a href="initWith.jsp?appId=520056664702384&clientSecret=e5929ff3d78f50a6e19cfe44e48e899a&appName=ThalamusTest">Inicializar para Thalamus Test</a>

<hr>
Crear usuario
<form action="createTestUser.jsp">
Nombre:<input type="text" name="firstname">
Apellido:<input type="text" name="lastname">
<input type="submit">
</form>

<table border="1">
	<tr>
		<td>Id</td>
		<td>Nombre</td>
		<td>Email</td>
		<td>Login</td>
		<td>Delete</td>
	</tr>
</table>

</body>
</html>