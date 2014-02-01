<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checkadmin.jspf" %><%--
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
<td>Seccion de la app</td><td>Nivel</td><td>Cambiar</td></tr>
<tr>
<td>Interface con Thalamus</td>
<td><%=com.tdil.ljpeugeot.utils.LoggerUtils.getLevelFor(com.tdil.thalamus.client.core.ThalamusClient.class)%></td>
<td><a href="./setLogLevel.jsp?category=com.tdil.thalamus.client.core.ThalamusClient&level=debug">debug</a>
<a href="./setLogLevel.jsp?category=com.tdil.thalamus.client.core.ThalamusClient&level=info">info</a>
<a href="./setLogLevel.jsp?category=com.tdil.thalamus.client.core.ThalamusClient&level=error">error</a></td>
</tr>
<tr>
<td>Thalamus completo</td>
<td><%=com.tdil.ljpeugeot.utils.LoggerUtils.getLevelFor("com.tdil.thalamus.client")[1]%></td>
<td><a href="./setLogLevel.jsp?category=com.tdil.thalamus.client&level=debug">debug</a>
<a href="./setLogLevel.jsp?category=com.tdil.thalamus.client&level=info">info</a>
<a href="./setLogLevel.jsp?category=com.tdil.thalamus.client&level=error">error</a></td>
</tr>
<tr>
<td>Interface con Middleware</td>
<td><%=com.tdil.ljpeugeot.utils.LoggerUtils.getLevelFor("com.tdil.ljpeugeot.gis")[1]%></td>
<td><a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.gis&level=debug">debug</a>
<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.gis&level=info">info</a>
<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.gis&level=error">error</a></td>
</tr>
<tr>
<td>Interface con Prevent</td>
<td><%=com.tdil.ljpeugeot.utils.LoggerUtils.getLevelFor("com.tdil.ljpeugeot.prevent")[1]%></td>
<td><a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.prevent&level=debug">debug</a>
<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.prevent&level=info">info</a>
<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.prevent&level=error">error</a></td>
</tr>

<tr>
<td>Servicios para apps nativas</td>
<td><%=com.tdil.ljpeugeot.utils.LoggerUtils.getLevelFor("com.tdil.ljpeugeot.rest")[1]%></td>
<td><a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.rest&level=debug">debug</a>
<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.rest&level=info">info</a>
<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.rest&level=error">error</a></td>
</tr>

<tr>
<td>Servlets (camaras y servicios ajax)</td>
<td><%=com.tdil.ljpeugeot.utils.LoggerUtils.getLevelFor("com.tdil.ljpeugeot.servlet")[1]%></td>
<td><a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.servlet&level=debug">debug</a>
<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.servlet&level=info">info</a>
<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.servlet&level=error">error</a></td>
</tr>

<tr>
<td>Struts (web y webmobile)</td>
<td><%=com.tdil.ljpeugeot.utils.LoggerUtils.getLevelFor("com.tdil.ljpeugeot.struts")[1]%></td>
<td><a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.struts&level=debug">debug</a>
<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.struts&level=info">info</a>
<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.struts&level=error">error</a></td>
</tr>

<tr>
<td>Web utils (fachada para servicios web)</td>
<td><%=com.tdil.ljpeugeot.utils.LoggerUtils.getLevelFor("com.tdil.ljpeugeot.utils")[1]%></td>
<td><a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.utils&level=debug">debug</a>
<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.utils&level=info">info</a>
<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.utils&level=error">error</a></td>
</tr>

<tr>
<td>VLU (importaciones de VLU)</td>
<td><%=com.tdil.ljpeugeot.utils.LoggerUtils.getLevelFor("com.tdil.ljpeugeot.vlu")[1]%></td>
<td><a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.vlu&level=debug">debug</a>
<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.vlu&level=info">info</a>
<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.vlu&level=error">error</a></td>
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