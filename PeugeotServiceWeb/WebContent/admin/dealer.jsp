<%@page import="com.tdil.ljpeugeot.feeds.ImportUtils"%>
<%@page import="com.tdil.ljpeugeot.model.DataImport"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checkadmin.jspf" %><%--
--%>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Peugeot :: Backdoor Application - Concesionarios</title>
<link rel="icon" href="../favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" media="screen" href="../css/reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/font_embeder.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/backdoor.css" />
</head>
<body>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jspf" %>
<section id="titles">
	<h1>LoJack Peugeot BackDoor Application</h1>
	<h2>Concesionarios</h2>
</section>
<section id="content">
	<article>
		<div class="portaTable">
			<ul class="thead">
				<li class="Twentyper">Importación</li>
				<li class="Twentyper">Estado</li>
				<li class="Tenper">Procesados</li>
				<li class="Twentyper">Inicio</li>
				<li class="Twentyper">Última Modif.</li>
				<li class="Tenper">Borrar</li>
			</ul>
			<% List<DataImport> imports = ImportUtils.getImports(com.tdil.ljpeugeot.feeds.dealer.DealerImportSpec.TYPE);
				for (DataImport vluImport : imports) { %>
				<ul class="tbody">
					<li class="Twentyper"><%=vluImport.getId()%> - <%=vluImport.getFilename()%></li>
					<li class="Twentyper"><%=vluImport.getStatus()%></li>
					<li class="Tenper"><%=vluImport.getProcessed()%></li>
					<li class="Twentyper"><%=vluImport.getStarttime() == null ? "-" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vluImport.getStarttime())%></li>
					<li class="Twentyper"><%=vluImport.getEndtime() == null ? "-" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vluImport.getEndtime())%></li>
					<li class="Tenper"><a href="./doDelete.jsp?dest=dealer.jsp&id=<%=vluImport.getId()%>">Borrar</a></li>
				</ul>
			<% } %>
		</div>
		
		<p class="attention"><strong>Importar base de concesionarios</strong></p>
		<p class="information">El archivo debe ser un csv con el siguiente formato: state,city,name,address,email,phone</p>

		<hr>
		
		<h3>Importar</h3>
		
		<form action="doImport.jsp" method="post" enctype="multipart/form-data">
			<fieldset>
				<input type="hidden" name="type" value="com.tdil.ljpeugeot.feeds.dealer.DealerImportSpec">
				<input type="hidden" name="dest" value="dealer.jsp">
				<input type="file" name="file" />
			</fieldset>
			<fieldset class="botonera">
				<input type="submit" value="Importar" />
			</fieldset>
		</form>
	</article>
</section>
</body>
</html>