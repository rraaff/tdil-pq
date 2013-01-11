<?php 
	include("include/headers.php");
	require("include/funcionesDB.php");
	session_start();
	
	// Inicio conexion
	$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");
	mysql_select_db(DB_NAME,$connection);
	
	$nombre = $_POST['nombre'];
	$apellido = $_POST['apellido'];
	$documento = $_POST['documento'];
/*	$edad = $_POST['edad'];*/
	$email = $_POST['email'];
	/*$usuario = $_POST['usuario'];
	$password = $_POST['password'];*/
	
	$nombre = quote_smart($nombre, $connection);
	$apellido = quote_smart($apellido, $connection);
	$documento = quote_smart($documento, $connection);
/*	$edad = quote_smart($edad, $connection);*/
	$email = quote_smart($email, $connection);
/*	$usuario = quote_smart($usuario, $connection);
	$password = quote_smart($password, $connection);*/
	
	$SQL = "SELECT * FROM SYSTEMUSER WHERE documento = $documento";
	$result = mysql_query($SQL);
	$num_rows = mysql_num_rows($result);
	if ($result) {
		if ($num_rows > 0) {
			$output = '{ "success": "no", "documento": "El documento ya esta registrado." }';
		} else {
			$SQL = "SELECT * FROM SYSTEMUSER WHERE email = $email";
			$result = mysql_query($SQL);
			$num_rows = mysql_num_rows($result);
			if ($result) {
				if ($num_rows > 0) {
					$output = '{ "success": "no", "email": "El email ya esta registrado." }';
				} else {
					$query = "INSERT INTO SYSTEMUSER (nombre, apellido, documento, edad, email,usuario,password, fechaCreacion)
					VALUES ($nombre, $apellido, $documento, 0, $email,'usuario','password', NOW() )";
					$res = mysql_query($query,$connection);// or die ("Error en insert ".mysql_error()."\n".$query);
					$returnInsert = mysql_insert_id($connection);
					// login
					$query = "INSERT INTO LOGINS (systemUserID, fechaLogin)
					VALUES ($returnInsert, NOW() )";
					$res = mysql_query($query,$connection);// or die ("Error en insert ".mysql_error()."\n".$query);
					// login
					$_SESSION['Login'] = "1";
					$_SESSION['Nombre'] = $_POST['nombre'];
					$_SESSION['Apellido'] = $_POST['apellido'];
					$_SESSION['Id'] = $returnInsert;
					$output = '{ "success": "yes", "error": "" , "nombre": "' .$_POST['nombre'] . '", "apellido": "' .$_POST['apellido']. '"}';
				}
			} else {
				$output = '{ "success": "no", "error": "Error generico." }';
			}
		}
	} else {
		$output = '{ "success": "no", "error": "Error generico." }';
	}
	// Cierre conexion
	closeConnection($connection);
// validar todo lo que haga falta, campo a campo
// usuario ya existente	
// password en md5?
// } else {
// $output = '{ "success": "no", "message": "This is not working" }';
// }

$output = str_replace("\r", "", $output);
$output = str_replace("\n", "", $output);

echo $output;
?>