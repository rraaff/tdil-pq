<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checklogaccess.jspf" %><%--
--%><html>
<body>
<h1>Logging</h1>
<%@ include file="includes/menu.jspf" %>

<b>Importante</b>: Los cambios realizados al esquema de logging no persisten luego de un restart.<br>
Para hacer estos cambios persistentes modifique el archivo log4j.xml del servidor.<br><br>

Baje el log completo cliqueando <a href="./downloadLog.jsp">bajar</a><br><br>

Las categorias de logs de interfaces (Thalamus, Middleware y Prevent)  siguen el siquiente esquema
<ul>
<li>debug muestra invocaciones, respuestas, tiempos y errores</li>
<li>info muestra tiempos y errores</li>
<li>error muestra solo errores</li>
</ul>

<table border="1">
<tr>
<td>Interfaz</td><td>Nivel</td><td>Cambiar</td></tr>
<tr>
<td>Thalamus</td>
<td><%=com.tdil.lojack.utils.LoggerUtils.getLevelFor(com.tdil.thalamus.client.core.ThalamusClient.class)%></td>
<td><a href="./setLogLevel.jsp?category=com.tdil.thalamus.client.core.ThalamusClient&level=debug">debug</a>
<a href="./setLogLevel.jsp?category=com.tdil.thalamus.client.core.ThalamusClient&level=info">info</a>
<a href="./setLogLevel.jsp?category=com.tdil.thalamus.client.core.ThalamusClient&level=error">error</a></td>
</tr>
<tr>
<td>Middleware</td>
<td><%=com.tdil.lojack.utils.LoggerUtils.getLevelFor(com.tdil.lojack.gis.LoJackServicesConnector.class)%></td>
<td><a href="./setLogLevel.jsp?category=com.tdil.lojack.gis.LoJackServicesConnector&level=debug">debug</a>
<a href="./setLogLevel.jsp?category=com.tdil.lojack.gis.LoJackServicesConnector&level=info">info</a>
<a href="./setLogLevel.jsp?category=com.tdil.lojack.gis.LoJackServicesConnector&level=error">error</a></td>
</tr>
<tr>
<td>Prevent</td>
<td><%=com.tdil.lojack.utils.LoggerUtils.getLevelFor(com.tdil.lojack.prevent.PreventConnector.class)%></td>
<td><a href="./setLogLevel.jsp?category=com.tdil.lojack.prevent.PreventConnector&level=debug">debug</a>
<a href="./setLogLevel.jsp?category=com.tdil.lojack.prevent.PreventConnector&level=info">info</a>
<a href="./setLogLevel.jsp?category=com.tdil.lojack.prevent.PreventConnector&level=error">error</a></td>
</tr>
</table>
<hr>
Setear log level
<form action="./setLogLevel.jsp">
Categoria <input type="text" name="category">
Nivel <select name="level">
	<option value="debug">debug</option>
	<option value="info">info</option>
	<option value="error">error</option> 
</select>
<input type="submit">
</form>
<hr>
Ver log level
<form action="./viewLogLevel.jsp">
Categoria <input type="text" name="category">
<input type="submit">
</form>
</body>
</html>