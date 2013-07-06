<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checklogaccess.jspf" %><%--
--%><html>
<body>
<h1>Home</h1>
<%@ include file="includes/menu.jspf" %>

Baje el log completo cliqueando <a href="./downloadLog.jsp">bajar</a><br><br>

Las caterias de logs de interfaces siguen el siquiente esquema
<ul>
<li>debug muestra invocaciones, respuestas y tiempos</li>
<li>info muestra tiempos</li>
<li>error muestra errores</li>
</ul>

Thalamus <%=com.tdil.lojack.utils.LoggerUtils.getLevelFor(com.tdil.thalamus.client.core.ThalamusClient.class)%>
<a href="./setLogLevel.jsp?category=com.tdil.thalamus.client.core.ThalamusClient&level=debug">debug</a>
<a href="./setLogLevel.jsp?category=com.tdil.thalamus.client.core.ThalamusClient&level=info">info</a>
<a href="./setLogLevel.jsp?category=com.tdil.thalamus.client.core.ThalamusClient&level=error">error</a><br>
<hr>
Middleware <%=com.tdil.lojack.utils.LoggerUtils.getLevelFor(com.tdil.lojack.gis.LoJackServicesConnector.class)%>
<a href="./setLogLevel.jsp?category=com.tdil.lojack.gis.LoJackServicesConnector&level=debug">debug</a>
<a href="./setLogLevel.jsp?category=com.tdil.lojack.gis.LoJackServicesConnector&level=info">info</a>
<a href="./setLogLevel.jsp?category=com.tdil.lojack.gis.LoJackServicesConnector&level=error">error</a><br>
<hr>
Prevent <%=com.tdil.lojack.utils.LoggerUtils.getLevelFor(com.tdil.lojack.prevent.PreventConnector.class)%>
<a href="./setLogLevel.jsp?category=com.tdil.lojack.prevent.PreventConnector&level=debug">debug</a>
<a href="./setLogLevel.jsp?category=com.tdil.lojack.prevent.PreventConnector&level=info">info</a>
<a href="./setLogLevel.jsp?category=com.tdil.lojack.prevent.PreventConnector&level=error">error</a><br>
<hr>
Setear log level
<form action="./setLogLevel.jsp">
Categoria <input type="text" name="category"><br>
Nivel <select name="level">
	<option value="debug">debug</option>
	<option value="info">info</option>
	<option value="error">error</option> 
</select><br>
<input type="submit">
</form>
<hr>
Ver log level
<form action="./viewLogLevel.jsp">
Categoria <input type="text" name="category"><br>
<input type="submit">
</form>
</body>
</html>