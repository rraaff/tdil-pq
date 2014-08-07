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
<title>LoJack :: Peugeot :: Backdoor Application - KMs</title>
<link rel="icon" href="../favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_font_embeder.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_backdoor.css" />
</head>
<body>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jspf" %>
<section id="titles">
	<h1>LoJack Peugeot BackDoor Application</h1>
	<h2>Kilometrajes</h2>
	<h3>Versi�n: <%@ include file="../includes/version_view.jspf" %></h3>
</section>
<section id="content">
	<article>
		<div class="portaTable">
			<ul class="thead">
				<li class="Twentyper">Importaci�n</li>
				<li class="Twentyper">Estado</li>
				<li class="Tenper">Procesados</li>
				<li class="Twentyper">Inicio</li>
				<li class="Twentyper">�ltima Modif.</li>
				<li class="Tenper">Borrar</li>
			</ul>
			<% List<DataImport> imports = ImportUtils.getImports(com.tdil.ljpeugeot.feeds.km.KMImportSpec.TYPE);
				for (DataImport vluImport : imports) { %>
				<ul class="tbody">
					<li class="Twentyper"><%=vluImport.getId()%> - <%=vluImport.getFilename()%></li>
					<li class="Twentyper"><%=vluImport.getStatus()%></li>
					<li class="Tenper"><%=vluImport.getProcessed()%></li>
					<li class="Twentyper"><%=vluImport.getStarttime() == null ? "-" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vluImport.getStarttime())%></li>
					<li class="Twentyper"><%=vluImport.getEndtime() == null ? "-" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vluImport.getEndtime())%></li>
					<li class="Tenper"><a href="./doDelete.jsp?dest=km.jsp&id=<%=vluImport.getId()%>">Borrar</a></li>
				</ul>
			<% } %>
		</div>
		<p class="attention"><strong>Importar base de kms de autos</strong></p>
		<p class="information">El archivo debe ser un csv con el siguiente formato: domain,km</p>

		<hr>
		
		<h3>Important</h3>
		<form action="doImport.jsp" method="post" enctype="multipart/form-data">
			<fieldset>
				<input type="hidden" name="type" value="com.tdil.ljpeugeot.feeds.km.KMImportSpec">
				<input type="hidden" name="dest" value="km.jsp">
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