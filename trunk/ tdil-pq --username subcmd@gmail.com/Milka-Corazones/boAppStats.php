<?php 
	include("include/headers.php");
	require("include/funcionesDB.php");
	require("include/boCheckLogin.php");
	
	$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");
	mysql_select_db(DB_NAME,$connection);
	
	$SQL = "SELECT 'Usuarios' AS DATA_DESC, COUNT(1) AS DATA_VALUE
		FROM FBUSER
		UNION ALL
		SELECT 'Participaciones', COUNT(1)
		FROM PARTICIPATION
		UNION ALL
		SELECT 'Premios asignados', COUNT(1)
		FROM DAILY_PRIZE
		WHERE participationID IS NOT NULL";

	$result = mysql_query($SQL) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
	$num_rows = mysql_num_rows($result);
	

?>
<html>
<head>
<?php include("include/title_meta.php"); ?>
<!-- Contact Form CSS files -->
<link type='text/css' href='css/tdil_bo.css' rel='stylesheet' media='screen' />
<?php include("include/headerBO.php"); ?>
<style>
	#content #page {
	width: 850px;
	padding: 0px;
	margin-top: 20px;
	line-height: 30px;
}
</style>	
</head>
<body>
<div id="content">
	<div id="header">
		<div id="block">
			<div id="portaMenu"><?php include("include/menuBO.php"); ?><div id="hello">Hola <span class="remarcado"><?php echo($_SESSION['boNombre']);?></span></div></div>
		</div>
	</div>
	<div id="formulariosBase">
		<h1>Estado de la aplicaci&oacute;n</h1>
		<div class="renglon width500 height300">
			<div class="label width500 height300">
				<table width="350" cellspacing="2" cellpadding="0" align="center" border="0">
					<?php while ( $aRow = mysql_fetch_array( $result ) ) { ?>
						<tr><td><?php echo $aRow["DATA_DESC"] ?></td><td><?php echo $aRow["DATA_VALUE"] ?></td></tr>
					<?php }	?>
				</table>
            </div>
		</div>
	</div>
</div>
<?php 	closeConnection($connection); ?>
</body>
</html>