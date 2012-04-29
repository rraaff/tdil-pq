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
	if (APPLICATION_URL == 'http://localhost/MilkaCorazones/app') {
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

$fbname = quote_smart($fbname, $connection);
$fbusername = quote_smart($fbusername, $connection);
$fbgender = quote_smart($fbgender, $connection);
?>
<?php
$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");

mysql_select_db(DB_NAME,$connection);

// me fio si tiene contact data
$SQL = "SELECT * FROM FBUSER WHERE fbid = $fbid";
$result = mysql_query($SQL) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
$num_rows = mysql_num_rows($result);
if ($num_rows == 0) {
	$SQL = "INSERT INTO FBUSER (fbid,fbname, fbusername, fbgender) VALUES($fbid, $fbname,$fbusername,$fbgender)"; // 3 is fb invitation
	$result = mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
}

// me fijo si la promo del dia esta activa
$SQL = "SELECT * FROM DAILY_PRIZE WHERE prizeDate = CURDATE() AND participationID is null";
$result = mysql_query($SQL) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
$num_rows = mysql_num_rows($result);
if ($num_rows > 0) {
	$todayActive = 1;
} else {
	$todayActive = 0;
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
<?php if ($todayActive) { ?>
		Promo activa
<?php } else { ?>
		Promo del dia inactiva
<?php } ?>
</body>
</html>