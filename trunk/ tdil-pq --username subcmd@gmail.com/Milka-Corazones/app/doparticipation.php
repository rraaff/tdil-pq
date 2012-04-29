<?php 
	include("../include/headers.php");
	require("../include/funcionesDB.php");
	
	// Inicio conexion
	$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");
	mysql_select_db(DB_NAME,$connection);

	$userid = $_REQUEST['userid'];
	$xcoord = $_REQUEST['xcoord'];
	$ycoord = $_REQUEST['ycoord'];
	
	$userid = quote_smart($userid, $connection);
	$xcoord = quote_smart($xcoord, $connection);
	$ycoord = quote_smart($ycoord, $connection);
	
	$SQL = "INSERT INTO PARTICIPATION (creationDate, fbuserID,xcoord,ycoord) VALUES (CURDATE(), $userid,$xcoord,$ycoord)";
	$res = mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
	$returnInsert = mysql_insert_id($connection);
	
	$SQL = "UPDATE DAILY_PRIZE SET participationID = $returnInsert WHERE xcoord = $xcoord AND ycoord = $ycoord AND prizeDate = CURDATE() AND participationID IS NULL LIMIT 1";
	mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
	// si gano
	if (mysql_affected_rows() == 1) {
		$output = '{ "success": "yes", "error": "" , "win": "yes" }';
	} else {
		$output = '{ "success": "yes", "error": "" , "win": "no"}';
	}
		
	// Cierre conexion
	mysql_close($connection);
	//validar todo lo que haga falta, campo a campo
	
	$output = str_replace("\r", "", $output);
	$output = str_replace("\n", "", $output);
	echo $output;
?>			