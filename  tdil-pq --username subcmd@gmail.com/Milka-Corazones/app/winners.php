<?php
include("../include/headers.php");
require("../include/funcionesDB.php");
include_once('../phpmail/class.phpmailer.php');
include("../include/constantes_mail.php");

require '../include/facebook.php';
include("../include/appconstants.php");
// Create our Application instance (replace this with your appId and secret).
$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");

mysql_select_db(DB_NAME,$connection);

// me fijo si existe
$SQL = "SELECT prizeDate, fbname
FROM DAILY_PRIZE db, FBUSER fb, PARTICIPATION pa
WHERE db.participationID = pa.id
AND pa.fbuserID = fb.id
order by prizeDate desc";

$result = mysql_query($SQL) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());

?>
<html>
<script language="javascript">AC_FL_RunContent = 0;</script>
<script src="../js/AC_RunActiveContent.js" language="javascript"></script>

<link href="../css/tdil.css" rel="stylesheet" type="text/css">

</head>
<body>
<table>
<tr><td>fecha</td><td>ganador</td></tr>
<?php while ( $aRow = mysql_fetch_array( $result ) ) { ?>
						<tr><td><?php echo $aRow["prizeDate"] ?></td><td><?php echo $aRow["fbname"] ?></td></tr>
					<?php }	?>
</table>
</body>
</html>
<?php closeConnection($connection); ?>