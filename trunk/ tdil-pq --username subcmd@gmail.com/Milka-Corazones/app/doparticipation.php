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

	$coord = $_REQUEST['coord'];
	
	$fbid = quote_smart($fbid, $connection);
	$coord = quote_smart($coord, $connection);
	
	$SQL = "SELECT * FROM FBUSER where fbid = $fbid";
	$result = mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
	$num_rows = mysql_num_rows($result);
	if ($num_rows == 0) {
		$output = 'dummie=0&status=0';
	} else {
		$dbuser = mysql_fetch_array($result);
		$userid = $dbuser['id'];
		$SQL = "SELECT * FROM PARTICIPATION WHERE fbuserid = $userid AND (
					(creationDate >= CURDATE()) OR 
					EXISTS (select id from DAILY_PRIZE WHERE participationId IN (select id FROM PARTICIPATION where fbuserID =  $userid))
				)";
		$result = mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
		$num_rows = mysql_num_rows($result);
		if ($num_rows > 0) {
			$output = 'dummie=0&status=0';
		} else {	
			$SQL = "INSERT INTO PARTICIPATION (creationDate, fbuserID,coord) VALUES (NOW(), $userid,$coord)";
			$res = mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
			$returnInsert = mysql_insert_id($connection);
			
			$SQL = "SELECT id FROM DAILY_PRIZE WHERE coord = $coord AND prizeDate = CURDATE() AND activationTimestamp <= NOW() AND participationID IS NULL LIMIT 1";
			$result = mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
			$num_rows = mysql_num_rows($result);
			if ($num_rows > 0) {
				$prizeApplied = mysql_fetch_array($result);
				$idPrizeApplied = $prizeApplied['id'];
				$SQL = "UPDATE PARTICIPATION SET dailyPrizeId = $idPrizeApplied WHERE id = $returnInsert";
				$result = mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
				$output = 'dummie=0&status=1';
			} else {
				$output = 'dummie=0&status=0';
			}
		}
	}
	
	// Cierre conexion
	mysql_close($connection);
	echo $output;
?>