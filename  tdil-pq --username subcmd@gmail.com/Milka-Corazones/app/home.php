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

$savingData = 0;
$fan = 0;
// Si esta salvando la contact data no chequeo el signed request
if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['savecontactdata'])) {
	$savingData = 1;
} else {
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

if ($savingData) {
	$firstname = quote_smart($_POST['firstname'], $connection);
	$address = quote_smart($_POST['address'], $connection);
	$phone = quote_smart($_POST['phone'], $connection);
	$SQL = "UPDATE FBUSER SET hascontactdata = 1,firstname = $firstname,address = $address,phone = $phone where fbid = $fbid";
	$result = mysql_query($SQL) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
}

// me fijo si existe
$SQL = "SELECT * FROM FBUSER WHERE fbid = $fbid";
$result = mysql_query($SQL) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
$num_rows = mysql_num_rows($result);
$hascontactdata = 0;
$alreadyParticipated = 0;
$isTodayWinner = 0;
if ($num_rows == 0) {
	$SQL = "INSERT INTO FBUSER (fbid,fbname, fbusername, fbgender,hascontactdata) VALUES($fbid, $fbname,$fbusername,$fbgender,0)"; // 3 is fb invitation
	$result = mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
	$userid = mysql_insert_id($connection);
} else {
	$dbuser = mysql_fetch_array($result);
	$userid = $dbuser['id'];
	$hascontactdata = $dbuser['hascontactdata'];
}
// me fijo si ya participo
$SQL = "SELECT * FROM PARTICIPATION WHERE fbuserid = $userid AND creationDate >= CURDATE()";
$result = mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
$num_rows = mysql_num_rows($result);
if ($num_rows > 0) {
	$alreadyParticipated = 1;
	// si participo, me fijo si gano
	$SQL = "SELECT * FROM DAILY_PRIZE WHERE prizeDate = CURDATE() AND participationId in (SELECT id FROM PARTICIPATION WHERE fbuserid = $userid AND creationDate >= CURDATE());";
	$result = mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
	$num_rows = mysql_num_rows($result);
	if ($num_rows > 0) {
		$isTodayWinner = 1;
	}
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
<script language="javascript">AC_FL_RunContent = 0;</script>
<script src="../js/AC_RunActiveContent.js" language="javascript"></script>
<script type='text/javascript' src='../js/jquery-1.7.min.js'></script>

<script>
	function checkContactData() {
		var objdiv = document.getElementById("errmessage");
		if (document.getElementById("firstname").value == "") {
			objdiv.innerHTML = "Debe ingresar el nombre";
			return false;
		}
		if (document.getElementById("lastname").value == "") {
			objdiv.innerHTML = "Debe ingresar el apellido";
			return false;
		}
		if (document.getElementById("address").value == "") {
			objdiv.innerHTML = "Debe ingresar la direccion";
			return false;
		}
		if (document.getElementById("phone").value == "") {
			objdiv.innerHTML = "Debe ingresar el telefono";
			return false;
		}
		return true;
	}
</script>
<link href="../css/tdil.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
#content {
	margin:0;
	padding:0;
	width:790px;
	height:700px;
	overflow:hidden;
}
#contentDatos {
	margin:0;
	padding:0;
	width:790px;
	height:700px;
	overflow:hidden;
	background-image: url(../images/acertasteDatos.jpg);
	background-repeat: no-repeat;
	background-position: left top;
}
#contentDatos #acomodador {
	width:395px;
	margin-left:200px;
	margin-top:250px;
}
#contentDatos #acomodador #renglon {
	width:250px;
	height:41px;
	margin-left:auto;
	margin-right:auto;
	margin-top:5px;
	margin-bottom:0;
}
#errmessage{
	font-family:Georgia, "Times New Roman", Times, serif;
	color: #FF9999;
	font-size:15px;
	text-align:center;
	width: 380px;
	height: 20px;
	margin-bottom:10px;
}
input[type="text"], input[type="password"], select {
	font-family: Georgia, "Times New Roman", Times, serif;
	font-size:14px;
	color:#5a5a5a;
	float:left;
	margin:0;
	padding:2px;
	line-height: normal;
	width:248px;
	height:33px;
	border: solid 1px #999999;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border-radius: 2px;
}
input[type="button"], input[type="submit"] {
	background:none;
	background-image: url(../images/btn_enviar_off.png);
	background-repeat: repeat;
	background-position: center center;
	width:122px;
	height:33px;
	margin:0;
	padding:0;
}
-->
</style>
</head>
<body>
	<?php if ($isTodayWinner == 1) { ?>
		<?php if ($hascontactdata == 0) { ?>
			<form method="POST" action="<?php echo $_SERVER['PHP_SELF'] ?>" onSubmit="return checkContactData();">
					<div id="contentDatos">
						<div id="acomodador">
							<div id="errmessage"></div>
							<div id="renglon"><input type="text" name="firstname" id="firstname" placeholder="Nombre"></div>
							<!--div id="renglon"><input type="text" name="lastname" id="lastname" placeholder=" Este no va"></div-->
							<div id="renglon"><input type="text" name="address" id="address" placeholder="email@domain.com"></div>
							<div id="renglon"><input type="text" name="phone" id="phone" placeholder="Tel&eacute;fono"></div>
							<div id="renglon" style="margin-top:15px;" align="center"><input type="hidden" name="savecontactdata" value="true"><input type="submit" value=" "></div>
						</div>
					</div>
				</form>
		<?php } else { ?>
			<div id="content"><img src="../images/felicitaciones.png" width="790" height="700" border="0"></div>
		<?php } ?>
	<?php } else { ?>
		<?php if ($alreadyParticipated == 1) { ?>
			<div id="content"><img src="../images/yaParticipaste.png" width="790" height="700" border="0"></div>
		<?php } else { 
			if ($promoToday == 1) { 
				if ($allPrizesGiven == 1) { ?>
						<div id="content"><img src="../images/yaSeEntregaron.png" width="790" height="700" border="0"></div>
					<?php } else { ?>
						<script language="javascript">
							if (AC_FL_RunContent == 0) {
								alert("This page requires AC_RunActiveContent.js.");
							} else {
								AC_FL_RunContent(
									'codebase', 'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0',
									'width', '790',
									'height', '700',
									'src', '../swf/homeApp',
									'quality', 'high',
									'pluginspage', 'http://www.macromedia.com/go/getflashplayer',
									'align', 'middle',
									'play', 'true',
									'loop', 'true',
									'scale', 'showall',
									'wmode', 'window',
									'devicefont', 'false',
									'id', 'homeApp',
									'bgcolor', '#ffffff',
									'name', 'homeApp',
									'menu', 'true',
									'allowFullScreen', 'false',
									'allowScriptAccess','always',
									'FlashVars','urlToShare=<?php echo APP_URL_TO_SHARE;?>&nameApp=<?php echo APP_NAME_TO_SHARE;?>',
									'movie', '../swf/homeApp',
									'salign', ''
									); //end AC code
							}
						</script>
						<noscript>
							<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0" width="790" height="700" id="homeApp" align="middle">
							<param name="allowScriptAccess" value="Always" />
							<param name="allowFullScreen" value="false" />
							<param name="FlashVars" value="urlToShare=<?php echo APP_URL_TO_SHARE;?>&nameApp=<?php echo APP_NAME_TO_SHARE;?>"/>
							<param name="movie" value="../swf/homeApp.swf" />
							<param name="quality" value="high" />
							<param name="bgcolor" value="#ffffff" />
							<embed src="../swf/homeApp.swf" FlashVars="urlToShare=<?php echo APP_URL_TO_SHARE;?>&nameApp=<?php echo APP_NAME_TO_SHARE;?>" quality="high" bgcolor="#ffffff" width="790" height="700" name="homeApp" align="middle" allowScriptAccess="Always" allowFullScreen="false" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
							</object>
						</noscript>
				<?php } ?>
			<?php } else { ?>
					<div id="content"><img src="../images/noHayHoy.png" width="790" height="700" border="0"></div>
			<?php } ?>
		<?php } ?>
	<?php } ?>
</body>
</html>