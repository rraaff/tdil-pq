<?php 
	include("include/headers.php");
	require("include/funcionesDB.php");
	require("include/boCheckLogin.php");
?>
<html>
<head>
<?php include("include/title_meta.php"); ?>
<!-- Contact Form CSS files -->
<link type='text/css' href='css/tdil_bo.css' rel='stylesheet' media='screen' />
<?php include("include/headerBO.php"); ?>
</head>
<body>
<div id="content">
	<div id="header">
		<div id="block">
			<div id="portaMenu"><?php include("include/menuBO.php"); ?><div id="hello">Hola <span class="remarcado"><?php echo($_SESSION['boNombre']);?></span></div></div>
		</div>
	</div>
	<div id="container" align="center">Seleccione una acci&oacute;n</div>
</div>
</body>
</html>