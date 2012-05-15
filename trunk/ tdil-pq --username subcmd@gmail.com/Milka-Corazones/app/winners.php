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
<style type="text/css">
<!--
body {
	background-image: none;
	background-repeat: no-repeat;
	background-position: left top;
	overflow:hidden !important;
}
#content {
	width:790px;
	height:700px;
	margin:0 auto;
	overflow:hidden;
	background-image: url(../images/winners.jpg);
	background-repeat: no-repeat;
	background-position: center center;
}
#content #tableHead {
	margin:0 auto;
	margin-top:150px;
	width:400px;
	height:28px;
	font-weight:bold;
}
#content #tableHead div {
	font-size:15px;
	border:solid 1px #665395;
	width:190px;
	height:20px;
	margin:2px;
	float:left;
}
#content #tableBody {
	margin:0 auto;
	width:400px;
	height:360px;
	overflow:auto;
}
#content #tableRow {
	width:400px;
	height:28px;
}
#content #tableRow div {
	font-size:15px;
	border:solid 1px #665395;
	width:190px;
	height:20px;
	margin:2px;
	float:left;
}
#backButton {
	margin:0 auto;
	width:122px;
	height:33px;
	margin-top:45px;
}
</style>
</head>
<body>
<div id="content">
	<div id="tableHead"><div>fecha</div><div>ganador</div></div>
	<div id="tableBody">
		<?php while ( $aRow = mysql_fetch_array( $result ) ) { ?>
			<div id="tableRow"><div><?php echo $aRow["prizeDate"] ?></div><div><?php echo $aRow["fbname"] ?></div></div>
		<?php }	?>
	</div>
	<div id="backButton"><a href="index.php"><img src="../images/btn_volver_rosa.png" width="122" height="33"/></a></div>
</div>
</body>
</html>
<?php closeConnection($connection); ?>