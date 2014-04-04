<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.tdil.ljpeugeot.services.PeugeotService"%>
<%@page import="com.tdil.ljpeugeot.model.NativeApp"%>
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
	<h2>Administración de Apps Nativas</h2>
	<h3>Versión: <%@ include file="../includes/version_view.jspf" %></h3>
</section>
<% 
	String code = request.getParameter("code");
	NativeApp app = null;
	if (!StringUtils.isEmpty(code)) {
		app = PeugeotService.getNativeAppByCode(code);
	}
%>
<section id="content">
	<article>
		
		<h3>Cambiar app nativa</h3>

		<form action="./doUpdateNativeApp.jsp">
			<input type="hidden" name="id" value="<%=app != null ? app.getId() : ""%>">
			<fieldset>
				<label>Titulo</label>
				<input type="text" name="title" value="<%=app != null ? app.getTitle() : ""%>">				
			</fieldset>
			<fieldset>
				<label>Version</label>
				<input type="text" name="version" value="<%=app != null ? app.getVersion() : ""%>">
			</fieldset>
			<fieldset>
				<label>URL</label>
				<input type="text" name="url" value="<%=app != null ? app.getUrl() : ""%>">
			</fieldset>
			<fieldset class="botonera">
				<input type="submit" <%=app == null ? "disabled" : ""%>>
			</fieldset>
		</form>
		
		<div class="portaTable">
			<ul class="thead">
				<li class="key">Codigo</li>
				<li class="value">Titulo</li>
				<li class="description">Version</li>
				<li class="erased">Borrada</li>
				<li class="edit">Editar</li>
				<li class="delete">Borrar</li>
			</ul>
			<% for (NativeApp sp : PeugeotService.getNativeApps()) { %>
				<ul class="tbody">
					<li class="key"><%=sp.getCode()%></li>
					<li class="value"><%=sp.getTitle()%></li>
					<li class="description"><%=sp.getVersion()%></li>
					<li class="erased"><%=sp.getDeleted()%></li>
					<li class="edit"><a href="./nativeApps.jsp?code=<%=sp.getCode()%>">Editar</a></li>
					<li class="delete"><% if (sp.getDeleted() == 1) { %>
						<a href="doToggleDeleteNativeApp.jsp?id=<%=sp.getId() %>">Activar</a>
						<% } else { %>
						<a href="doToggleDeleteNativeApp.jsp?id=<%=sp.getId() %>">Borrar</a>
						<% } %>
					</li>
				</ul>
			<% } %>
		</div>
	</article>
</section>
</body>
</html>