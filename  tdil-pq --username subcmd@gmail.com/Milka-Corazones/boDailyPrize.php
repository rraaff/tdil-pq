<?php 
	include("include/headers.php");
	require("include/funcionesDB.php");
	require("include/boCheckLogin.php");
	
	$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");
	mysql_select_db(DB_NAME,$connection);
	if ($_SERVER['REQUEST_METHOD'] == 'POST'){
		// Inicio conexion
		
		$coord = $_POST['coord'];
		$prizeDate = $_POST['prizeDate'];
		$active = 1;
		
		$coord = quote_smart($coord, $connection);
		$prizeDate = quote_smart($prizeDate, $connection);
		$active = quote_smart($active, $connection);
		
		$SQL = "INSERT INTO DAILY_PRIZE (prizeDate, activationTimestamp , coord, active) 
			VALUES ($prizeDate, $prizeDate, $coord, $active)";
		$res = mysql_query($SQL,$connection)  or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
		
		header("Location: boDailyPrize.php");
	} else {
	
	$query = "SELECT DP.*, 
		CASE WHEN (participationID is not NULL) THEN 'Adjudicado'
		WHEN (DP.prizeDate < CURDATE()) THEN 'Vencido'
		ELSE 'Pendiente' END state,
		SU.fbid, SU.fbname, SU.fbusername
		FROM DAILY_PRIZE DP 
		LEFT JOIN PARTICIPATION TI ON (DP.participationID = TI.id) 		
		LEFT JOIN FBUSER SU ON (TI.fbuserID = SU.id) ORDER BY DP.activationTimestamp";
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
						coord: {required: true}
				},
				messages: {
					prizeDate: {required: "Ingrese la fecha."}, 
					coord: {required: "Ingrese la coordenada ."},
				}
			});
		}
	);
</script>
</head>
<body>
<div id="content">
	<div id="header">
		<div id="block">
			<div id="portaMenu"><?php include("include/menuBO.php"); ?><div id="hello">Hola <span class="remarcado"><?php echo($_SESSION['boNombre']);?></span></div></div>
		</div>
	</div>
	<div id="formulariosBase">
		<h1>Manejo de premios</h1>
	<form action="boDailyPrize.php" name="altaIWForm" id="altaIWForm" method="POST">
		<div class="renglon width450">
			<div class="label width50">Fecha</div>
			<div class="label width150"><input type="text" name="prizeDate" id="prizeDate"></div>
			<div class="label width50"></div>
			<div class="label width80">Coordenada</div>
			<div class="label width80"><select name="coord" id="coord" class="width50"><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option><option value="32">32</option><option value="33">33</option></select></div>
		</div>
		<div class="renglon width180 height50"><input type="submit" name="submit2" value="Generar Premio" class="saveButton"></div>
	</form>
	<div class="renglon width850 height250" style="overflow:auto;">
		<table width="100%" cellpadding="5" cellspacing="2" border="0">
			<tr bgcolor="#CCCCCC">
				<td>Id</td>
				<td>Fecha</td>
				<td>Activacion</td>
				<td># bombon</td>
				<td>Estado</td>
				<td>Ganador</td>
				<td>Aprobado</td>
				<td>Borrar</td>
			</tr>	
			<?php
				$today = strtotime(date('d.m.y', time()));
				while ($iw = mysql_fetch_array($res)){
			?>
			<tr>
				<td><?php echo $iw['id'] ?></td>
				<td><?php echo $iw['prizeDate'] ?></td>
				<td><?php echo $iw['activationTimestamp'] ?></td>
				<td><?php echo $iw['coord'] ?></td>
				<td><?php echo $iw['state'] ?></td>
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
						echo '-';
					} else {
						if ($iw['approved'] == 0) { ?>
							No <a href="doApproveDailyPrize.php?id=<?php echo $iw['id'] ?>">Aprobar</a>
						<?php } else { ?>
							Si
						<?php }	
						} ?>
				</td>
				<td><?php 
					if (is_null($iw['participationID'])) {
						if ($iw['state'] == 'Pendiente') {
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
</div>
</body>
</html>
<?php 
mysql_close($connection);
	} ?>