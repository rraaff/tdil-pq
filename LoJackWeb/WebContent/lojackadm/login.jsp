<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Real Life :: Backdoor Application</title>
<link rel="icon" href="../favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" media="screen" href="../css/reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/font_embeder.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/backdoor.css" />
</head>
<body>
<%@ include file="includes/header.jsp" %>
<section id="titles">
	<h1>LoJack Real Life BackDoor Application</h1>
	<h2>Ingreso de usuarios</h2>
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
			<fieldset>
				<label>Codigo</label>
				<img src="../Captcha.jpg" align="middle" alt="Ingrese los caracteres" border="1"/><a href="login.jsp">Regenerar</a>
				<input type="text" name="code">
			</fieldset>
			<% if ("invalidCode".equals(request.getParameter("err"))) { %>
				El codigo ingresado es incorrecto
			<% } %>
			<fieldset class="botonera">
				<input type="submit" value="Ingresar">
			</fieldset>
		</form>
	</article>
</section>
</body>
</html>