<?php
include("../include/headers.php");
require("../include/funcionesDB.php");
include_once('../phpmail/class.phpmailer.php');
include("../include/constantes_mail.php");

require '../include/facebook.php';
include("../include/appconstants.php");
// Create our Application instance (replace this with your appId and secret).
$facebook = new Facebook(array(
		'appId'  => APPLICATION_ID,
		'secret' => APPLICATION_SECRET,
));
$app_token = get_app_access(APPLICATION_ID,APPLICATION_SECRET);
// Get User ID
$user = $facebook->getUser();
?>
<?php 
/* si el usuario es nulo, no lo autorizo */
if ($user == 0) {
	include("askpermission.php");
	return;
}
$fbid = $user;
//$page_id = $signed_request['page']['id']; /*TODO Limitar a una pagina cuanto este productivo*/
//if ($page_id != $PAGEID) {
//	die("No allowed");
//}
$signed_request = $facebook->getSignedRequest();
if (empty($signed_request['page']['liked'])) {
	// Si estoy en localhost
	if (APPLICATION_URL == 'http://localhost/Milka-Corazones/app') {
		$testuser = $facebook->api('/me');
		// Si el nombre incluye Tester entonces asumo que es fan
		if (strpos($testuser['name'], 'Tester')) {
			$fan = 1;
		} else {
			include("onlyforfans.php");
			return;
		}
	} else {
		include("onlyforfans.php");
		return;
	}
} else {
	$fan = 1;
}
?>
<?php
// en la session
try {
	// Proceed knowing you have a logged in user who's authenticated.
	$user_profile = $facebook->api('/me');
} catch (FacebookApiException $e) {
	print_r($e);
	error_log($e);
	$user = null;
}
$fbemail = '';
$fbname = $user_profile['name'];
$fbusername = "";
if (isset($user_profile['username'])) {
	$fbusername = $user_profile['username'];
}
$fbgender = $user_profile['gender'];

?>
<?php
$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");

$fbname = quote_smart($fbname, $connection);
$fbusername = quote_smart($fbusername, $connection);
$fbgender = quote_smart($fbgender, $connection);

mysql_select_db(DB_NAME,$connection);

// me fio si tiene contact data
$SQL = "SELECT * FROM FBUSER WHERE fbid = $fbid";
$result = mysql_query($SQL) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
$num_rows = mysql_num_rows($result);
if ($num_rows == 0) {
	$SQL = "INSERT INTO FBUSER (fbid,fbname, fbusername, fbgender) VALUES($fbid, $fbname,$fbusername,$fbgender)"; // 3 is fb invitation
	$result = mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
	$userid = mysql_insert_id($connection);
} else {
	$dbuser = mysql_fetch_array($result);
	$userid = $dbuser['id'];
}
// me fijo si ya participo
$SQL = "SELECT * FROM PARTICIPATION WHERE fbuserid = $userid AND creationDate >= CURDATE()";
$result = mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
$num_rows = mysql_num_rows($result);
if ($num_rows > 0) {
	$alreadyParticipated = 1;
} else {
	$alreadyParticipated = 0;
}

// Fijar todas las opciones, ya participaste hoy, hoy no esta activa, hoy ya hay un ganador
// me fijo si la promo del dia esta activa
$SQL = "SELECT * FROM DAILY_PRIZE WHERE prizeDate = CURDATE()";
$result = mysql_query($SQL) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
$num_rows = mysql_num_rows($result);
if ($num_rows > 0) {
	$promoToday = 1;
	$SQL = "SELECT * FROM DAILY_PRIZE WHERE prizeDate = CURDATE() and participationID is null";
	$result = mysql_query($SQL) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
	$num_rows = mysql_num_rows($result);
	if ($num_rows > 0) {
		$allPrizesGiven = 0;
	} else {
		$allPrizesGiven = 1;
	}
} else {
	$promoToday = 0;
}

closeConnection($connection);
?>
<html>
<head>
<script type='text/javascript' src='../js/jquery-1.7.min.js'></script>

<script>

</script>
<link href="../css/tdil.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	background-image: url(../images/homeBaseApp2.jpg);
	background-repeat: no-repeat;
	background-position: center top;
	overflow:hidden !important;
}
#baseDatosDeContacto {
	height:318px;
	margin-top:180px;
	background-image: url(../images/homeData.png);
	background-repeat: no-repeat;
	background-position: center top;
}
form, div, table, tr, td {
	font-size:12px;
}
#baseDatosDeContacto #acomodador {
	margin-left:346px;
	padding-top:100px !important;
}
.okButton {
	font-size: 1px;
	color: transparent;
	background:transparent;
	background-image: url(../images/button_ok.png);
	background-repeat: no-repeat;
	background-position: center -5px;
	height: 69px;
	width: 69px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: none;
	margin: 0px;
	cursor:hand;
}
#invitationBlock {
	margin-left:346px;
}
#buttonsLinks {
	margin-left:346px;
}
</style>
</head>
<body>
<?php if ($alreadyParticipated == 1) { ?>
	Hoy ya participaste
<?php } else { 
	if ($promoToday == 1) { 
		if ($allPrizesGiven == 1) { ?>
				Ya se entregaron todos los premios del dia.
			<?php } else { ?>
				<form method="POST" action="doparticipation.php">
					<input type="hidden" name="userid" value="<?php echo $userid;?>">
					<div id="baseDatosDeContacto" style="margin-top:100px; height:315px;">
						<div id="errmessage"></div>
						<div id="acomodador">
							<table cellpadding="5" cellspacing="0" border="0" align="left">
								<tr>
									<td>X: </td>
									<td><input type="text" name="xcoord" id="xcoord" value=""></td>
								</tr>
								<tr>
									<td>Y: </td>
									<td><input type="text" name="ycoord" id="ycoord" value=""></td>
								</tr>
								<tr>
									<td colspan="2" align="center"><input type="hidden" name="savecontactdata" value="true"><input type="submit" value="Grabar datos"></td>
								</tr>
							</table>
						</div>
					</div>
				</form>
		<?php } ?>
	<?php } else { ?>
			NO hay promo para hoy
	<?php } ?>
<?php } ?>
</body>
</html>