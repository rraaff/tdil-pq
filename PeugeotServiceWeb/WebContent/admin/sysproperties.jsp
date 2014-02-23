<%@page import="com.tdil.utils.StringUtils"%>
<%@page import="com.tdil.ljpeugeot.model.SystemProperty"%>
<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checkadmin.jspf" %><%--
--%>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Peugeot :: Backdoor Application - Sys Properties</title>
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
	<h2>Administración de Sys Properties</h2>
</section>
<section id="content">
	<article>
		<p style="text-align:center;"><a href="doLoadProperties.jsp">Actualizar</a> el sistema con las properties</p>
		<p class="attention"><strong>Importante</strong>: Los cambios realizados a las sys properties no son tomados automaticamente.<br>Luego de realizar los cambios cliquee actualizar para que el sistema los tome.</p>
		
		<h3>Cambiar sysproperty</h3>

		<form action="./doUpdateSysProperty.jsp">
			<fieldset>
				<label>Clave</label>
				<input type="text" style="width: 250px;" name="propkey" value="<%=StringUtils.notNullValueOf(request.getParameter("propkey"))%>">				
			</fieldset>
			<fieldset>
				<label>Valor</label>
				<input type="text" style="width: 250px;" name="propvalue" value="<%=StringUtils.notNullValueOf(request.getParameter("propvalue"))%>">
			</fieldset>
			<fieldset class="botonera">
				<input type="submit">
			</fieldset>
		</form>
		
		<div class="portaTable">
			<ul class="thead">
				<li class="key">Clave</li>
				<li class="value">Valor</li>
				<li class="description">Descripción</li>
				<li class="erased">Borrada</li>
				<li class="edit">Editar</li>
				<li class="delete">Borrar</li>
			</ul>
			<% for (SystemProperty sp : com.tdil.ljpeugeot.utils.SystemPropertyUtils.getSystemProperties()) { %>
				<ul class="tbody">
					<li class="key"><%=sp.getPropkey()%></li>
					<li class="value"><%=sp.getPropvalue()%></li>
					<li class="description"><%=sp.getDescription()%></li>
					<li class="erased"><%=sp.getDeleted()%></li>
					<li class="edit"><a href="./sysproperties.jsp?propkey=<%=sp.getPropkey()%>&propvalue=<%=sp.getPropvalue()%>">Editar</a></li>
					<li class="delete"><% if (sp.getDeleted() == 1) { %>
						<a href="doToggleDeleteSysProperty.jsp?id=<%=sp.getId() %>">Activar</a>
						<% } else { %>
						<a href="doToggleDeleteSysProperty.jsp?id=<%=sp.getId() %>">Borrar</a>
						<% } %>
					</li>
				</ul>
			<% } %>
		</div>
	</article>
</section>
</body>
</html>