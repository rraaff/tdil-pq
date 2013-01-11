<?php
include("include/headers.php");
require("include/funcionesDB.php");
require("include/boCheckLogin.php");

if(isset($_GET['ticketID'])) {
// if id is set then get the file with the id from database

$ticketID    = $_GET['ticketID'];
$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");
mysql_select_db(DB_NAME,$connection);

$ticketID = quote_smart($ticketID, $connection);

$query = "SELECT ticket, fechaCarga, nombre, apellido, documento, email, usuario
         FROM TICKETS TK, SYSTEMUSER SU WHERE TK.systemUserID = SU.id AND TK.id = $ticketID";

$result_tickets = mysql_query($query);
$ticket = mysql_fetch_array($result_tickets);

// Cierre conexion
mysql_close($connection);
?>
<div>
Ticket: <?php echo $ticket['ticket']?><br>
Fecha: <?php echo $ticket['fechaCarga']?><br>
Nombre: <?php echo $ticket['nombre']?><br>
Apellido: <?php echo $ticket['apellido']?><br>
Documento: <?php echo $ticket['documento']?><br>
Email: <?php echo $ticket['email']?><br>
Usuario: <?php echo $ticket['usuario']?><br>
</div>
<?php 
} else {
?>
	Invalid get
<?php } ?>
