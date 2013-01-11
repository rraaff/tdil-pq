<?php
include("include/headers.php");
require("include/funcionesDB.php");

if(isset($_GET['id'])) {
// if id is set then get the file with the id from database

$id    = $_GET['id'];
$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");
mysql_select_db(DB_NAME,$connection);

$id = quote_smart($id, $connection);

$query = "SELECT name, type, size, content " .
         "FROM INSTANT_WIN WHERE id = $id";

$res = mysql_query($query, $connection);
$iw = mysql_fetch_array($res);

// Cierre de conexion
mysql_close($connection);

$name = $iw['name'];
$size = $iw['size'];
$type = $iw['type'];

header("Content-length: $size");
header("Content-type: $type]");
header("Content-Disposition: attachment; filename=$name");

echo $iw['content'];

} else {
	echo "Invalid get";
}
?>