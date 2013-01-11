<?php 
	include("include/headers.php");
	require("include/funcionesDB.php");
	require("include/checkLogin.php");
	
	// Inicio conexion
	$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");
	mysql_select_db(DB_NAME,$connection);

	$userID = $_SESSION['Id'];
	$ticket = $_POST['codigo'];
	$supermercado = $_POST['supermercado'];
	$botellas = $_POST['botellas'];
	
	$userID = quote_smart($userID, $connection);
	$ticket = quote_smart($ticket, $connection);
	$supermercado = quote_smart($supermercado, $connection);
	$botellas = quote_smart($botellas, $connection);	

	$SQL_tickets = "SELECT * FROM TICKETS WHERE ticket = $ticket";
	$result_tickets = mysql_query($SQL_tickets);
	$num_rows_tickets = mysql_num_rows($result_tickets);
	if ($result_tickets) {
		// Si no fue cargado, lo cargo
		if ($num_rows_tickets == 0) {
			$insertTicket = "INSERT INTO TICKETS (systemuserID, ticket,supermercado,botellas, fechaCarga) VALUES ($userID, $ticket,$supermercado,$botellas, NOW())";
			$res = mysql_query($insertTicket,$connection);// or die ("Error en insert ".mysql_error()."\n".$query);
			$returnInsert = mysql_insert_id($connection);
			
			$updateInstantWin = "UPDATE INSTANT_WIN SET ticketID = $returnInsert WHERE NOW() >= inicio AND NOW() <= FIN AND ticketID IS NULL LIMIT 1";
			mysql_query($updateInstantWin,$connection);// or die ("Error en insert ".mysql_error()."\n".$query);
			// si gano
			if (mysql_affected_rows() == 1) {
				$query = "SELECT id, mensaje FROM INSTANT_WIN WHERE ticketID = $returnInsert";
				$result_win = mysql_fetch_array(mysql_query($query));
				$id_win = $result_win['id'];
				$descripcion_win = $result_win['mensaje'];
				$output = '{ "success": "yes", "error": "" , "win": "yes", "iw_id": "' . $id_win . '", "iw_desc": "' . $descripcion_win . '"}';
			} else {
				$output = '{ "success": "yes", "error": "" }';
			}
		} else {
			// Si ya fue cargado, doy mensaje de error
			$output = '{ "success": "no", "codigo": "Ticket ya cargado." }';
		}
	} else {
		$output = '{ "success": "no", "error": "Error generico." }';
	}
	// Cierre conexion
	mysql_close($connection);
	//validar todo lo que haga falta, campo a campo
	
	$output = str_replace("\r", "", $output);
	$output = str_replace("\n", "", $output);
	echo $output;
?>			