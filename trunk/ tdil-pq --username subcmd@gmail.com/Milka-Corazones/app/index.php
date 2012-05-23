<?php
include("../include/headers.php");
require("../include/funcionesDB.php");
include_once('../phpmail/class.phpmailer.php');
include("../include/constantes_mail.php");

require '../include/facebook.php';
include("../include/appconstants.php");
// Create our Application instance (replace this with your appId and secret).
$facebook = new Facebook(array(
		'appId'  => APPLICATION_ID,
		'secret' => APPLICATION_SECRET,
));
$app_token = get_app_access(APPLICATION_ID,APPLICATION_SECRET);
// Get User ID
$user = $facebook->getUser();
?>
<?php 
/* si el usuario es nulo, no lo autorizo */
if ($user == 0) {
	include("askpermissioncanvas.php");
	return;
}
?>

<?php 
	$redirect = 'https://www.facebook.com/'. PAGE_NAME . '?sk=app_'. APPLICATION_ID;
?>
<html>
<link href="../css/tdil.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	background-image: url(../images/acceptBGApp2.jpg);
	background-repeat: no-repeat;
	background-position: left top;
	overflow:hidden !important;
}
#textContent{
	width: 159px;
	margin-top: 307px;
	margin-right: 90px;
	text-align: center;
}
-->
</style>

<body>
<div id="textContent">
	<div id="contentSuccessfull"><a href="<?php echo $redirect;?>" target="_top"><img src="../images/button_here.png" alt="clic ac&aacute;" width="159" height="58" border="0"></a></div>
</div>
</body>
</html>