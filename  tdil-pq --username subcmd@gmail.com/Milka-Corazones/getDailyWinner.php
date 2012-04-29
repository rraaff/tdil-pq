<?php
include("include/headers.php");
require("include/funcionesDB.php");
require("include/boCheckLogin.php");

if(isset($_GET['participationID'])) {
// if id is set then get the file with the id from database

$participationID    = $_GET['participationID'];
$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");
mysql_select_db(DB_NAME,$connection);

$participationID = quote_smart($participationID, $connection);

$query = "SELECT fbid, fbname, fbusername
         FROM PARTICIPATION PART, FBUSER US WHERE PART.fbuserID = US.id AND PART.id = $participationID";

$result_tickets = mysql_query($query);
$ticket = mysql_fetch_array($result_tickets);

// Cierre conexion
mysql_close($connection);
?>
<div>
fbid: <?php echo $ticket['fbid']?><br>
fbname: <?php echo $ticket['fbname']?><br>
fbusernae: <?php echo $ticket['fbusername']?><br>
</div>
<?php 
} else {
?>
	Invalid get
<?php } ?>
