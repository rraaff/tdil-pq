<?php /*
* Output
*/
include("include/headers.php");
require("include/funcionesDB.php");
//require("include/boCheckLogin.php");

$sGender = $_REQUEST["sGender"];
$sMinParticipations = $_REQUEST["sMinParticipations"];
$sMaxParticipations = $_REQUEST["sMaxParticipations"];
$sMinWins = $_REQUEST["sMinWins"];
$sMaxWins = $_REQUEST["sMaxWins"];

$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");

$sGender = quote_smart($sGender, $connection);
$sMinParticipations = quote_smart($sMinParticipations, $connection);
$sMaxParticipations = quote_smart($sMaxParticipations, $connection);

$sMinWins = quote_smart($sMinWins, $connection);
$sMaxWins = quote_smart($sMaxWins, $connection);
mysql_select_db(DB_NAME,$connection);
	
$SQL = "SELECT *, (SELECT COUNT(1) FROM PARTICIPATION WHERE  fbuserID = fbuser.id) participations,
	(SELECT COUNT(1) FROM DAILY_PRIZE WHERE  participationID IN (SELECT id FROM PARTICIPATION WHERE  fbuserID = fbuser.id)) wins
FROM FBUSER fbuser WHERE 1=1 ";

if ($sGender != "'all'") {
	$SQL = $SQL . " AND fbuser.fbgender = $sGender";
}
if ($sMinParticipations != "" && is_numeric($sMinParticipations)) {
	$SQL = $SQL . " AND (SELECT COUNT(1) FROM PARTICIPATION WHERE fbuserID = fbuser.id) >= $sMinParticipations";
}
if ($sMaxParticipations != "" && is_numeric($sMaxParticipations)) {
	$SQL = $SQL . " AND (SELECT COUNT(1) FROM PARTICIPATION WHERE fbuserID = fbuser.id) <= $sMaxParticipations";
}

if ($sMinWins != "" && is_numeric($sMinWins)) {
	$SQL = $SQL . " AND (SELECT COUNT(1) FROM DAILY_PRIZE WHERE  participationID IN (SELECT id FROM PARTICIPATION WHERE  fbuserID = fbuser.id)) >= $sMinWins";
}
if ($sMaxWins != "" && is_numeric($sMaxWins)) {
	$SQL = $SQL . " AND (SELECT COUNT(1) FROM DAILY_PRIZE WHERE  participationID IN (SELECT id FROM PARTICIPATION WHERE  fbuserID = fbuser.id)) <= $sMaxWins";
}

$result = mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
$iTotal = mysql_num_rows($result);

$sEcho = 1;
if (isset($_GET['sEcho'])) {
	$sEcho = intval($_GET['sEcho']);
}

$output = array(
		"sEcho" => $sEcho,
		"iTotalRecords" => $iTotal,
		"iTotalDisplayRecords" => $iTotal,
		"aaData" => array()
);
$aColumns = array( 'fbid', 'fbname', 'fbusername', 'fbgender', 'firstname','participations', 'wins');

while ( $aRow = mysql_fetch_array( $result ) ) {
	$row = array();
	for ( $i=0 ; $i<count($aColumns) ; $i++ ) {
		if ( $aColumns[$i] != ' ' )	{
			/* General output */
			$row[] = $aRow[ $aColumns[$i] ];
		}
	}
	$output['aaData'][] = $row;
}
mysql_close($connection);
echo json_encode( $output );
?>