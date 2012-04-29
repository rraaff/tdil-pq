<?php /*
* Output
*/
include("include/headers.php");
require("include/funcionesDB.php");
//require("include/boCheckLogin.php");

$sInicial = $_REQUEST["sInicial"];
$sParticipation = $_REQUEST["sParticipation"];
$sGender = $_REQUEST["sGender"];
$sMinFans = $_REQUEST["sMinFans"];
$sMaxFans = $_REQUEST["sMaxFans"];

$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");

$sGender = quote_smart($sGender, $connection);
mysql_select_db(DB_NAME,$connection);
	
$SQL = "SELECT * FROM (SELECT fbid, fbname, fbusername, fbgender, inv_email, origin, participation, FANS_RECOMENDADOS.CANTIDAD fans, '' groupOwner
FROM USER_APP1, (SELECT groupowner_fbid, count(1) - 1 CANTIDAD FROM GROUP_APP1 GROUP BY groupowner_fbid) FANS_RECOMENDADOS
WHERE USER_APP1.ORIGIN = 1
AND USER_APP1.fbid = FANS_RECOMENDADOS.groupowner_fbid
UNION ALL
SELECT fbid, fbname, fbusername, fbgender, inv_email, origin, participation, 0, '' groupOwner
FROM USER_APP1
WHERE ORIGIN = 1
AND PARTICIPATION = 0
UNION ALL
SELECT u1.fbid, u1.fbname, u1.fbusername, u1.fbgender, u1.inv_email, u1.origin, u1.participation, 0, u2.fbname groupOwner
FROM USER_APP1 u1, GROUP_APP1 g1, USER_APP1 u2
WHERE u1.ORIGIN != 1
AND u1.PARTICIPATION = 1
AND u1.fbid = g1.groupmember_fbid
AND g1.groupowner_fbid = u2.fbid) USERS WHERE 1=1 ";

if ($sInicial != "-1") {
	$SQL = $SQL . " AND USERS.origin = $sInicial";
}
if ($sParticipation != "-1") {
	$SQL = $SQL . " AND USERS.participation = $sParticipation";
}
if ($sGender != "'all'") {
	$SQL = $SQL . " AND USERS.fbgender = $sGender";
}
if ($sMinFans != "" && is_numeric($sMinFans)) {
	$SQL = $SQL . " AND USERS.fans >= $sMinFans";
}
if ($sMaxFans != "" && is_numeric($sMaxFans)) {
	$SQL = $SQL . " AND USERS.fans <= $sMaxFans";
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
$aColumns = array( 'fbid', 'fbname', 'fbusername', 'fbgender', 'inv_email', 'origin', 'participation', 'fans', 'groupOwner');

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