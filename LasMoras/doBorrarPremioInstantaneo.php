<?php
include("include/headers.php");
require("include/funcionesDB.php");
require("include/boCheckLogin.php");

if(isset($_GET['id'])) {
// if id is set then get the file with the id from database

$id    = $_GET['id'];
$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");
mysql_select_db(DB_NAME,$connection);

$id = quote_smart($id, $connection);

$query = "DELETE FROM INSTANT_WIN WHERE ticketID IS NULL AND id = $id";

mysql_query($query, $connection);

// Cierre de conexion
mysql_close($connection);

} 
header("Location: boPremiosInstantaneos.php");
?>