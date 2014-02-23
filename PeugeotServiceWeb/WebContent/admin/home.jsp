<%@ page info="home"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ include file="includes/checklogin.jsp" %><%--
--%>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Peugeot :: Backdoor Application</title>
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
	<h2>Bienvenido (Inicio)</h2>
</section>
<section id="content">
	<article>
		<p class="information"><strong>Importante: </strong>Desde aquí puede acceder a la administración de la aplicación.</p>
		<p>Cliquee en el link "Configuracion de sysproperties" para ver, editar y recargar las system properties.</p>
		<p>Cliquee en el link "Configuracion del logging " para cambiar los niveles de logging o bajar el log actual.</p>
	</article>
</section> 
</body>
</html>