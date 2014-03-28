<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Peugeot :: Backdoor Application</title>
<link rel="icon" href="../favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_font_embeder.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_backdoor.css" />
</head>
<body>
<%@ include file="includes/header.jsp" %>
<section id="titles">
	<h1>LoJack Peugeot BackDoor Application</h1>
	<h2>Ingreso de usuarios</h2>
	<h3>Versión: <%@ include file="../includes/version_view.jspf" %></h3>
</section>
<section id="content">
	<article>
		<form action="./doLogin.jsp">
			<fieldset>
				<label>Usuario</label>
				<input type="text" name="username">
			</fieldset>
			<fieldset>
				<label>Clave</label>
				<input type="password" name="password">
			</fieldset>
			<fieldset class="botonera">
				<input type="submit" value="Ingresar">
			</fieldset>
		</form>
	</article>
</section>
</body>
</html>