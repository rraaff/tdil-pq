<?php 
	include("../include/headers.php");
	require("../include/funcionesDB.php");
	
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
	
	$fbid = $user;
	
	// Inicio conexion
	$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");
	mysql_select_db(DB_NAME,$connection);

	$xcoord = $_REQUEST['xcoord'];
	$ycoord = $_REQUEST['ycoord'];
	
	$fbid = quote_smart($fbid, $connection);
	$xcoord = quote_smart($xcoord, $connection);
	$ycoord = quote_smart($ycoord, $connection);
	
	$SQL = "SELECT * FROM FBUSER where fbid = $fbid";
	$result = mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
	$num_rows = mysql_num_rows($result);
	if ($num_rows == 0) {
		$output = '0';
	} else {
		$dbuser = mysql_fetch_array($result);
		$userid = $dbuser['id'];
		$SQL = "SELECT * FROM PARTICIPATION WHERE fbuserid = $userid AND creationDate >= CURDATE()";
		$result = mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
		$num_rows = mysql_num_rows($result);
		if ($num_rows > 0) {
			$output = '0';
		} else {	
			$SQL = "INSERT INTO PARTICIPATION (creationDate, fbuserID,xcoord,ycoord) VALUES (NOW(), $userid,$xcoord,$ycoord)";
			$res = mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
			$returnInsert = mysql_insert_id($connection);
			
			$SQL = "UPDATE DAILY_PRIZE SET participationID = $returnInsert WHERE xcoord = $xcoord AND ycoord = $ycoord AND prizeDate = CURDATE() AND activationTimestamp <= NOW() AND participationID IS NULL LIMIT 1";
			mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
			// si gano
			if (mysql_affected_rows() == 1) {
				$output = '1';
			} else {
				$output = '0';
			}
				
		}
	}
	
	// Cierre conexion
	mysql_close($connection);
	$output = str_replace("\r", "", $output);
	$output = str_replace("\n", "", $output);
	echo $output;
?>