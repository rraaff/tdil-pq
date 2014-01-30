<%@page import="com.tdil.ljpeugeot.VLUImportThread"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.tdil.ljpeugeot.VLUUtils"%>
<%@page import="com.tdil.ljpeugeot.model.VLUImport"%>
<%@page import="java.util.List"%>
<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checklogaccess.jspf" %><%--
--%><html>
<body>
<h1>VLU</h1>
<%@ include file="includes/menu.jspf" %>

<table border="1">
<tr>
<td>Importacion</td><td>Estado</td><td>Procesados</td><td>Inicio</td><td>Ultima modif</td><td>Borrar</td></tr>
<% List<VLUImport> imports = VLUUtils.getImports();
for (VLUImport vluImport : imports) { %>

<tr>
<td><%=vluImport.getId()%> - <%=vluImport.getFilename()%></td>
<td><%=vluImport.getStatus()%></td>
<td><%=vluImport.getProcessed()%></td>
<td><%=vluImport.getStarttime() == null ? "-" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vluImport.getStarttime())%></td>
<td><%=vluImport.getEndtime() == null ? "-" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vluImport.getEndtime())%></td>
<td><a href="./doDeleteVLU.jsp?id=<%=vluImport.getId()%>">Borrar</a></td>
</tr>

<% } %>
</table>
<hr>
Importar base de vlu <br>
El archivo debe ser un csv con el siguiente formato:<br>
dni,domain,message<br><br>

<form action="doImportVLU.jsp" method="post"
                        enctype="multipart/form-data">
<input type="file" name="file" size="50" />
<br />
<input type="submit" value="Importar" />
<br>Recuerde que la importacion se ejecutara entre las <%=VLUImportThread.getStartHour()%>:<%=VLUImportThread.getStartMinutes()%> y las 
<%=VLUImportThread.getEndHour()%>:<%=VLUImportThread.getEndMinutes()%> 
</form>
</body>
</html>