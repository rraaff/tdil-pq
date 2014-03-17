<%@page import="com.tdil.lojack.vlu.VLUImportThread"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.tdil.lojack.vlu.VLUUtils"%>
<%@page import="com.tdil.lojack.model.VLUImport"%>
<%@page import="java.util.List"%>
<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checkvluaccess.jspf" %><%--
--%>

<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Real Life :: Backdoor Application - VLU Config</title>
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
	<h1>LoJack Real Life BackDoor Application</h1>
	<h2>VLU</h2>
</section>
<section id="content">
	<article>
		<div class="portaTable">
			<ul class="thead">
				<li class="value">Importación</li>
				<li class="erased">Estado</li>
				<li class="key">Procesados</li>
				<li class="key">Inicio</li>
				<li class="key">Última modif</li>
				<li class="delete">Borrar</li>
			</ul>
			<% List<VLUImport> imports = VLUUtils.getImports();
			for (VLUImport vluImport : imports) { %>
				<ul class="tbody">
					<li class="value"><%=vluImport.getId()%> - <%=vluImport.getFilename()%></li>
					<li class="erased"><%=vluImport.getStatus()%></li>
					<li class="key"><%=vluImport.getProcessed()%></li>
					<li class="key"><%=vluImport.getStarttime() == null ? "-" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vluImport.getStarttime())%></li>
					<li class="key"><%=vluImport.getEndtime() == null ? "-" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vluImport.getEndtime())%></li>
					<li class="delete"><a href="./doDeleteVLU.jsp?id=<%=vluImport.getId()%>">Borrar</a></li>
				</ul>
			<% } %>
		</div>

		<hr>

		<h3>Importar base de VLU</h3>
		<p class="information">El archivo debe ser un csv con el siguiente formato: dni,domain,message</p>

		<form action="doImportVLU.jsp" method="post" enctype="multipart/form-data" class="fullSize">
			<fieldset class="allinone">
				<label class="longer">Buscar archivo</label>
				<input type="file" name="file" />
			</fieldset>
			<fieldset class="botonera">
				<input type="submit" value="Importar" />
			</fieldset>			
		</form>
		
		<p class="attention">Recuerde que la importación se ejecutará entre las <%=VLUImportThread.getStartHour()%>:<%=VLUImportThread.getStartMinutes()%> y las <%=VLUImportThread.getEndHour()%>:<%=VLUImportThread.getEndMinutes()%></p>
		
		
		<hr>

		<h3>Borrado de dominios reparados VLU</h3>
		<p class="information">El archivo debe ser un csv con el siguiente formato: dni,domain</p>

		<form action="doDeleteRepairedDomainsVLU.jsp" method="post" enctype="multipart/form-data" class="fullSize">
			<fieldset class="allinone">
				<label class="longer">Buscar archivo</label>
				<input type="file" name="file" />
			</fieldset>
			<fieldset class="botonera">
				<input type="submit" value="Importar" />
			</fieldset>			
		</form>
		
		<p class="attention">Recuerde que la importación se ejecutará en el acto</p>
	</article>
</section>
</body>
</html>