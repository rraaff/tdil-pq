<?php 
	include("include/headers.php");
	require("include/funcionesDB.php");
	require("include/boCheckLogin.php");
	
	$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");
	mysql_select_db(DB_NAME,$connection);
	
	$SQL = "SELECT * FROM USER_PARTICIPATION";

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
		<h1>Listado de participaciones</h1>
		<div class="renglon width500 height300">
			<div class="label width500 height300">
				<table width="350" cellspacing="2" cellpadding="0" align="center" border="0">
					<tr><td>Id Facebook</td>
						<td>Nombre Facebook</td>
						<td>DNI</td>
						<td>Fecha participacion</td>
						<td>Corazon</td></tr>
					<?php while ( $aRow = mysql_fetch_array( $result ) ) { ?>
						<tr>
							<td><?php echo $aRow["FacebookId"] ?></td>
							<td><?php echo $aRow["FacebookName"] ?></td>
							<td><?php echo $aRow["DNI"] ?></td>
							<td><?php echo $aRow["FechaParticipacion"] ?></td>
							<td><?php echo $aRow["Corazon"] ?></td>
						</tr>
					<?php }	?>
				</table>
            </div>
		</div>
	</div>
</div>
<?php 	closeConnection($connection); ?>
</body>
</html>