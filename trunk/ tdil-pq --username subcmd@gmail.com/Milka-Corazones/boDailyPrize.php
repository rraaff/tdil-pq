<?php 
	include("include/headers.php");
	require("include/funcionesDB.php");
	require("include/boCheckLogin.php");
	
	if ($_SERVER['REQUEST_METHOD'] == 'POST'){
		// Inicio conexion
		$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");
		mysql_select_db(DB_NAME,$connection);
		
		$xcoord = $_POST['xcoord'];
		$ycoord = $_POST['ycoord'];
		$prizeDate = $_POST['prizeDate'];
		$active = 1;
		
		$xcoord = quote_smart($xcoord, $connection);
		$ycoord = quote_smart($ycoord, $connection);
		$prizeDate = quote_smart($prizeDate, $connection);
		$active = quote_smart($active, $connection);
		
		$insertDailyPrize = "INSERT INTO DAILY_PRIZE (prizeDate, xcoord, ycoord, active) 
			VALUES ($prizeDate, $xcoord, $ycoord, $active)";
		$res = mysql_query($insertDailyPrize,$connection);// or die ("Error en insert ".mysql_error()."\n".$query);
		
		mysql_close($connection);
		
		header("Location: boDailyPrize.php");
	} else {
	
	$query = "SELECT DP.*, UNIX_TIMESTAMP(DP.prizeDate) prizeDateUnix, SU.fbid, SU.fbname, SU.fbusername
		FROM DAILY_PRIZE DP 
		LEFT JOIN PARTICIPATION TI ON (DP.participationID = TI.id) 		
		LEFT JOIN FBUSER SU ON (TI.fbuserID = SU.id) 
		ORDER BY DP.prizeDate";
	$res = doSelect($query);
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
<style>
	#content #page {
	width: 850px;
	padding: 0px;
	margin-top: 20px;
}
</style>
<?php include("include/headerBO.php"); ?>
<script>
$(document).ready(
		function(){
			$("#altaIWForm").validate({
				rules: { descripcion: {required: true},
						mensaje: {required: true},
						imagen: {required: true},
						inicio: {required: true},
						fin: {required: true}
				},
				messages: {
					descripcion: {required: "Ingrese la descripcion."}, 
					mensaje: {required: "Ingrese el mensaje."},
					imagen: {required: "Seleccione la imagen."},
					inicio: {required: "Ingrese la fecha inicio."},
					fin: {required: "Ingrese la fecha fin."},
				}
			});
		}
	);
</script>
</head>
<body>
<div id="content">
	<div id="hello">Hola <span class="remarcado"><?php echo($_SESSION['boNombre']);?> <?php echo($_SESSION['boApellido']);?></span></div>
	<div id="portaMenu"><?php include("include/menuBO.php"); ?></div>
	<div id="page">

	<form action="boDailyPrize.php" name="altaIWForm" id="altaIWForm" method="POST">
		<div align="center">
			<table cellpadding="5" cellspacing="5" border="0" align="center">
				<tr><td>Fecha</td><td><input type="text" name="prizeDate" id="prizeDate"></td><td class="remarcado"></td></tr>
				<tr><td>Coordenada X</td><td><input type="text" name="xcoord"></td><td class="remarcado"></td></tr>
				<tr><td>Coordenada Y</td><td><input type="text" name="ycoord"></td><td class="remarcado"></td></tr>
				<tr><td colspan="2" align="center"><input type="submit" name="submit2" value=" " class="saveButton"></td></tr>
			</table>
		</div>
	</form>

	<table width="100%" cellpadding="5" cellspacing="5" border="0">
		<tr bgcolor="#CCCCCC">
			<td>Id</td>
			<td>Fecha</td>
			<td>Coordenada x</td>
			<td>Coordenada y</td>
			<td>Estado</td>
			<td>Ganador</td>
			<td>Borrar</td>
		</tr>
<?php
	$today = time();
	while ($iw = mysql_fetch_array($res)){
?>
	<tr>
		<td><?php echo $iw['id'] ?></td>
		<td><?php echo $iw['prizeDate'] ?></td>
		<td><?php 
			if (is_null($iw['participationID'])) {
				if ($iw['prizeDateUnix'] > $today) {
     				echo 'Pendiente';
				} else {
				    echo 'Vencido';
				}
			} else {
				echo 'Adjudicado';
			}
		?></td>
		<td><?php echo $iw['xcoord'] ?></td>
		<td><?php echo $iw['ycoord'] ?></td>
		<td><?php 
			if (!is_null($iw['fbname'])) { ?>
				<a href="javascript:showWinner('<?php echo $iw['participationID'];?>')">
				<?php echo $iw['fbname'];?>
				</a>
			<?php } else {
				echo '-';
			}
		?></td>
		<td><?php 
			if (is_null($iw['participationID'])) {
				if ($iw['prizeDateUnix'] > $today) {
     				?>
     				<a href="doDeleteDailyPrize.php?id=<?php echo $iw['id'];?>">
						Borrar 
					</a>
     				<?php 
				} else {
				    echo '-';
				}
			} else {
				echo '-';
			}
		?></td>
	</tr>
<?php 		
	} 
?>
</table>
<script>

function showWinner(ticketID) {
	$.get("getTicketWinner.php?ticketID=" + ticketID, function(data){
		// create a modal dialog with the data
		$(data).modal({
			overlayId: 'winner-overlay',
			containerId: 'winner-container'
		});
	});
}

$(document).ready(
		function(){
			$('#prizeDate').datepicker({dateFormat: 'yy-mm-dd'});
		}
	);
</script>
	</div>
</div>
</body>
</html>
<?php } ?>