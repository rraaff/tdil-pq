<%@page import="com.facebook.FacebookTestUser"%>
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
<% } %> <br> <a href="initWith.jsp?appId=520056664702384&clientSecret=e5929ff3d78f50a6e19cfe44e48e899a&appName=ThalamusTest">Inicializar para Thalamus Test</a>
<br>
<a href="initWith.jsp?appId=103292516498840&clientSecret=bf071b86cf483bb62ae0494cd56496ec&appName=TuaFesta">Inicializar para Tua Festa Test</a>
<br>
<a href="initWith.jsp?appId=520886374635218&clientSecret=94f191d5758f8387a834ac1e41010f6d&appName=LoJackDev">Inicializar para LoJackDev</a>

<hr>
Crear usuario
<form action="createTestUser.jsp">
Nombre:<input type="text" name="firstname">
Apellido:<input type="text" name="lastname">
<input type="submit">
</form>
<hr>
Listado | <a href="getAll.jsp">Cargar todos</a>
<table border="1">
	<tr>
		<td>Id</td>
		<td>Nombre</td>
		<td>Email</td>
		<td>Login</td>
		<td>Cargar</td>
		<td>Borrar</td>
	</tr>
	<% for (FacebookTestUser ftu : facebookTestData.getTestUsers()) { %>
	<tr>
		<td><%=ftu.getId()%></td>
		<td><%=ftu.getName()%></td>
		<td><%=ftu.getEmail()%></td>
		<td><a href="<%=ftu.getLogin_url()%>"><%=ftu.getLogin_url()%></a></td>
		<td><a href="getUser.jsp?id=<%=ftu.getId()%>">Cargar</a></td>
		<td><a href="deleteTestUser.jsp?id=<%=ftu.getId()%>">Borrar</a></td>
	</tr>
	<% } %>
</table>

</body>
</html>