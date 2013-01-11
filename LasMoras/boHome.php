<?php 
	include("include/headers.php");
	require("include/funcionesDB.php");
	require("include/boCheckLogin.php");
?>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Finca Las Moras - Premia tu forma de disfrutar la vida - BACKOFFICE</title>
<meta name="keywords" content="Vino, Tinto, Blanco, Finca Las Moras, Beber con moderaci�n" />
<meta name="description" content="Finca Las Moras recompensa tu forma de disfrutar la vida" />
<meta name="AUTHOR" content="That Day in London - Agencia Interactiva & Dise�o - para Publiquest" />
<link rel="icon" href="./favicon.ico" type="icon"/>
<!-- Contact Form CSS files -->
<link type='text/css' href='css/tdil.css' rel='stylesheet' media='screen' />
<?php include("include/headerBO.php"); ?>
</head>
<body>
<div id="content">
	<div id="hello">Hola <span class="remarcado"><?php echo($_SESSION['boNombre']);?> <?php echo($_SESSION['boApellido']);?></span></div>
	<div id="portaMenu"><?php include("include/menuBO.php"); ?></div>
	<div id="page">Seleccione una acci&oacute;n</div>
</div>
</body>
</html>