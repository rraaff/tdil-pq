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
<script type='text/javascript' src='../js/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_jquery-1.8.2.min.js'></script>
<script type="text/javascript" src="../js/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_jquery.validate.min.js"></script>
<script>
$(document).ready(
		function(){
			$("form[name='napp']").validate({
				errorPlacement: function(error, element) {
					error.appendTo( element.next("div"));
				},
				rules: { 
					'code': {required: true, maxlength: 20},
					'title': {required: true, maxlength: 100},
					'version': {required: true, maxlength: 100},
					'url': {required: true, maxlength: 400},
					'image': {required: true, maxlength: 400},
					'summary': {required: true, maxlength: 4000}
				},
				messages: {	
					'code': {required: "<span>Ingrese el codigo.</span>",
						maxlength: "<span>Ingrese hasta 20 caracteres.</span>"},
					'title': {required: "<span>Ingrese el titulo.</span>",
						maxlength: "<span>Ingrese hasta 100 caracteres.</span>"},
					'version': {required: "<span>Ingrese la version.</span>",
						maxlength: "<span>Ingrese hasta 100 caracteres.</span>"},
					'url': {required: "<span>Ingrese la url.</span>",
						maxlength: "<span>Ingrese hasta 400 caracteres.</span>"},
					'image': {required: "<span>Ingrese la imagen.</span>",
						maxlength: "<span>Ingrese hasta 400 caracteres.</span>"},
					'summary': {required: "<span>Ingrese la bajada.</span>",
						maxlength: "<span>Ingrese hasta 4000 caracteres.</span>"}
				}
			});
	}
);
</script>
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

		<form name="napp" action="./doUpdateNativeApp.jsp">
			<input type="hidden" name="id" value="<%=app != null ? app.getId() : "0"%>">
			<% if (app != null) { %>
			<fieldset>
				<label>Codigo</label>
				<input type="text" name="code" value="<%=app.getCode()%>" readonly>	
				<div></div>
				</fieldset>
			<% } else { %>
				<fieldset>
					<label>Codigo</label>
					<input type="text" name="code" value="">
					<div></div>	
				</fieldset>
			<% } %>
			<fieldset>
				<label>Titulo</label>
				<input type="text" name="title" value="<%=app != null ? app.getTitle() : ""%>">
				<div></div>
			</fieldset>
			<fieldset>
				<label>Version</label>
				<input type="text" name="version" value="<%=app != null ? app.getVersion() : ""%>">
				<div></div>
			</fieldset>
			<fieldset>
				<label>Bajada</label>
				<textarea name="summary"><%=app != null ? app.getSummary() : ""%></textarea>
				<div></div>
			</fieldset>
			<fieldset>
				<label>URL</label>
				<input type="text" name="url" value="<%=app != null ? app.getUrl() : ""%>">
				<div></div>
			</fieldset>
			<fieldset>
				<label>Imagen</label>
				<input type="text" name="image" value="<%=app != null ? app.getImage() : ""%>">
				<div></div>
			</fieldset>
			<fieldset class="botonera">
				<input type="submit">
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