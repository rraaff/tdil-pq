<?php 
	include("include/headers.php");
	require("include/funcionesDB.php");
	require("include/boCheckLogin.php");
	
	$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");
	mysql_select_db(DB_NAME,$connection);
	if ($_SERVER['REQUEST_METHOD'] == 'POST'){
		// Inicio conexion
		
		$xcoord = $_POST['xcoord'];
		$ycoord = $_POST['ycoord'];
		$prizeDate = $_POST['prizeDate'];
		$active = 1;
		
		$xcoord = quote_smart($xcoord, $connection);
		$ycoord = quote_smart($ycoord, $connection);
		$prizeDate = quote_smart($prizeDate, $connection);
		$active = quote_smart($active, $connection);
		
		$SQL = "INSERT INTO DAILY_PRIZE (prizeDate, activationTimestamp , xcoord, ycoord, active) 
			VALUES ($prizeDate, $prizeDate, $xcoord, $ycoord, $active)";
		$res = mysql_query($SQL,$connection)  or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
		
		header("Location: boDailyPrize.php");
	} else {
	
	$query = "SELECT DP.*, UNIX_TIMESTAMP(DP.prizeDate) prizeDateUnix, SU.fbid, SU.fbname, SU.fbusername
		FROM DAILY_PRIZE DP 
		LEFT JOIN PARTICIPATION TI ON (DP.participationID = TI.id) 		
		LEFT JOIN FBUSER SU ON (TI.fbuserID = SU.id) 
		ORDER BY DP.prizeDate";
	$res = mysql_query($query, $connection);
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
}
</style>
<script>
$(document).ready(
		function(){
			$("#altaIWForm").validate({
				rules: { prizeDate: {required: true},
						xcoord: {required: true},
						ycoord: {required: true}
				},
				messages: {
					prizeDate: {required: "Ingrese la fecha."}, 
					xcoord: {required: "Ingrese la coordenada x."},
					ycoord: {required: "Seleccione la coordenada y."}
				}
			});
		}
	);
</script>
</head>
<body>
<div id="content">
	<div id="hello">Hola <span class="remarcado"><?php echo($_SESSION['boNombre']);?></span></div>
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
			<td>Activacion</td>
			<td>Coordenada x</td>
			<td>Coordenada y</td>
			<td>Estado</td>
			<td>Ganador</td>
			<td>Borrar</td>
		</tr>
	
<?php
	$today = strtotime(date('d.m.y', time()));
	while ($iw = mysql_fetch_array($res)){
		$pdate = strtotime(date('d.m.y', $iw['prizeDateUnix']));
?>
	<tr>
		<td><?php echo $iw['id'] ?></td>
		<td><?php echo $iw['prizeDate'] ?></td>
		<td><?php echo $iw['activationTimestamp'] ?></td>
		<td><?php echo $iw['xcoord'] ?></td>
		<td><?php echo $iw['ycoord'] ?></td>
		<td><?php 
			if (is_null($iw['participationID'])) {
				if ($pdate >= $today) {
     				echo 'Pendiente';
				} else {
				    echo 'Vencido';
				}
			} else {
				echo 'Adjudicado';
			}
		?></td>
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
				if ($pdate > $today) {
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
	$.get("getDailyWinner.php?participationID=" + ticketID, function(data){
		// create a modal dialog with the data
		$(data).modal({
			overlayId: 'winner-overlay',
			containerId: 'winner-container'
		});
	});
}

$(document).ready(
		function(){
			$('#prizeDate').datetimepicker(
					{showSecond: true,
						showMillisec: false,
						dateFormat: 'yy-mm-dd',
						timeFormat: 'hh:mm:ss'});
		}
	);
</script>
	</div>
</div>
</body>
</html>
<?php 
mysql_close($connection);
	} ?>