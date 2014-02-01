<%@page import="com.tdil.ljpeugeot.feeds.ImportUtils"%>
<%@page import="com.tdil.ljpeugeot.model.DataImport"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checkadmin.jspf" %><%--
--%><html>
<body>
<h1>Concesionarios</h1>
<%@ include file="includes/menu.jspf" %>

<table border="1">
<tr>
<td>Importacion</td><td>Estado</td><td>Procesados</td><td>Inicio</td><td>Ultima modif</td><td>Borrar</td></tr>
<%
	List<DataImport> imports = ImportUtils.getImports(com.tdil.ljpeugeot.feeds.dealer.DealerImportSpec.TYPE);
for (DataImport vluImport : imports) {
%>

<tr>
<td><%=vluImport.getId()%> - <%=vluImport.getFilename()%></td>
<td><%=vluImport.getStatus()%></td>
<td><%=vluImport.getProcessed()%></td>
<td><%=vluImport.getStarttime() == null ? "-" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vluImport.getStarttime())%></td>
<td><%=vluImport.getEndtime() == null ? "-" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vluImport.getEndtime())%></td>
<td><a href="./doDeleteDealer.jsp?id=<%=vluImport.getId()%>">Borrar</a></td>
</tr>

<% } %>
</table>
<hr>
Importar base de concesionarios <br>
El archivo debe ser un csv con el siguiente formato:<br>
state,city,name,address,email,phone<br><br>

<form action="doImportDealer.jsp" method="post"
                        enctype="multipart/form-data">
<input type="file" name="file" size="50" />
<br />
<input type="submit" value="Importar" />
</form>
</body>
</html>