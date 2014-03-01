<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checkadmin.jspf" %><%--
--%>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Peugeot :: Backdoor Application - Logger</title>
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
	<h2>Loggin</h2>
</section>
<section id="content">
	<article>
		<p class="attention"><strong>Importante:</strong> Los cambios realizados al esquema de logging no persisten luego de un restart.<br>Para hacer estos cambios persistentes modifique el archivo log4j.xml del servidor.</p>
		<p style="text-align:center;">Baje el log completo haciendo <a href="./downloadLog.jsp">clic en bajar</a></p>
		<p class="information">Las categorías de logs de interfaces (Thalamus y Prevent) responden al siquiente esquema</p>
		
		<div class="nota">
			<ul>
				<li><strong>DEBUG</strong> muestra invocaciones, respuestas, tiempos y errores</li>
				<li><strong>INFO</strong> muestra tiempos y errores</li>
				<li><strong>ERROR</strong> muestra solo errores</li>
			</ul>
		</div>
		
		<div class="portaTable">
			<ul class="thead">
				<li class="Seventyper">Seccion de la app</li>
				<li class="Tenper">Nivel</li>
				<li class="Twentyper">Cambiar</li>
			</ul>
			<ul class="tbody">
				<li class="Seventyper">Interface con Thalamus</li>
				<li class="Tenper"><%=com.tdil.ljpeugeot.utils.LoggerUtils.getLevelFor(com.tdil.thalamus.client.core.ThalamusClient.class)%></li>
				<li class="Twentyper"><a href="./setLogLevel.jsp?category=com.tdil.thalamus.client.core.ThalamusClient&level=debug">debug</a>
				<a href="./setLogLevel.jsp?category=com.tdil.thalamus.client.core.ThalamusClient&level=info">info</a>
				<a href="./setLogLevel.jsp?category=com.tdil.thalamus.client.core.ThalamusClient&level=error">error</a></li>
			</ul>
			<ul class="tbody">
				<li class="Seventyper">Thalamus completo</li>
				<li class="Tenper"><%=com.tdil.ljpeugeot.utils.LoggerUtils.getLevelFor("com.tdil.thalamus.client")[1]%></li>
				<li class="Twentyper"><a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.gis&level=debug">debug</a>
					<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.gis&level=info">info</a>
					<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.gis&level=error">error</a></li>
			</ul>
			<ul class="tbody">
				<li class="Seventyper">Interface con Prevent</li>
				<li class="Tenper"><%=com.tdil.ljpeugeot.utils.LoggerUtils.getLevelFor("com.tdil.ljpeugeot.prevent")[1]%></li>
				<li class="Twentyper"><a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.prevent&level=debug">debug</a>
					<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.prevent&level=info">info</a>
					<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.prevent&level=error">error</a></li>
			</ul>
			<ul class="tbody">
				<li class="Seventyper">Servicios para apps nativas</li>
				<li class="Tenper"><%=com.tdil.ljpeugeot.utils.LoggerUtils.getLevelFor("com.tdil.ljpeugeot.rest")[1]%></li>
				<li class="Twentyper"><a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.rest&level=debug">debug</a>
					<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.rest&level=info">info</a>
					<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.rest&level=error">error</a></li>
			</ul>
			<ul class="tbody">
				<li class="Seventyper">Servlets (camaras y servicios ajax)</li>
				<li class="Tenper"><%=com.tdil.ljpeugeot.utils.LoggerUtils.getLevelFor("com.tdil.ljpeugeot.servlet")[1]%></li>
				<li class="Twentyper"><a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.servlet&level=debug">debug</a>
					<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.servlet&level=info">info</a>
					<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.servlet&level=error">error</a></li>
			</ul>
			<ul class="tbody">
				<li class="Seventyper">Struts (web y webmobile)</li>
				<li class="Tenper"><%=com.tdil.ljpeugeot.utils.LoggerUtils.getLevelFor("com.tdil.ljpeugeot.struts")[1]%></li>
				<li class="Twentyper"><a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.struts&level=debug">debug</a>
					<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.struts&level=info">info</a>
					<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.struts&level=error">error</a></li>
			</ul>
			<ul class="tbody">
				<li class="Seventyper">Web utils (fachada para servicios web)</li>
				<li class="Tenper"><%=com.tdil.ljpeugeot.utils.LoggerUtils.getLevelFor("com.tdil.ljpeugeot.utils")[1]%></li>
				<li class="Twentyper"><a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.utils&level=debug">debug</a>
					<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.utils&level=info">info</a>
					<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.utils&level=error">error</a></li>
			</ul>
			<ul class="tbody">
				<li class="Seventyper">VLU (importaciones de VLU)</li>
				<li class="Tenper"><%=com.tdil.ljpeugeot.utils.LoggerUtils.getLevelFor("com.tdil.ljpeugeot.vlu")[1]%></li>
				<li class="Twentyper"><a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.vlu&level=debug">debug</a>
					<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.vlu&level=info">info</a>
					<a href="./setLogLevel.jsp?category=com.tdil.ljpeugeot.vlu&level=error">error</a></li>
			</ul>
		</div>

		<hr>
		
		<h3>Setear log level</h3>
		
		<form action="./setLogLevel.jsp" class="fullSize">
			<fieldset class="allinone">
				<label>Categoría</label>
				<input type="text" name="category">
				<label class="spacer">&nbsp;</label>
				<label>Nivel</label>
				<select name="level">
					<option value="debug">debug</option>
					<option value="info">info</option>
					<option value="error">error</option> 
				</select>
			</fieldset>
			<fieldset class="botonera">
				<input type="submit" value="Grabar">
			</fieldset>
		</form>

		<hr>
		
		<h3>Ver log level</h3>
		
		<form action="./viewLogLevel.jsp">
			<fieldset>
				<label>Categoría</label>
				<input type="text" name="category">
			</fieldset>
			<fieldset class="botonera">
				<input type="submit" value="Ver">
			</fieldset>
		</form>
	</article>
</section>
</body>
</html>