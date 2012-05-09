<html>
<head>
<link href="../css/tdil.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
/*body {
	background-image: url(../images/askpermisionCanvasApp2.jpg);
	background-repeat: no-repeat;
	background-position: left top;
	overflow:hidden !important;
}*/
#content {
	margin:0;
	padding:0;
	width:740px;
	height:470px;
	overflow:hidden;
}
</style>
</head>
<body>
<div id="content"><img src="../images/permisos_canvas.png" width="740" height="470" border="0" usemap="#Map">
<?php 
$url = $facebook->getLoginUrl(array(
		'redirect_uri' => APPLICATION_URL . '/logincallback.php'
));
echo '<map name="Map"><area shape="rect" coords="310,368,432,402" href="' . $url . '"></map>';
?>
</div>
</body>
</html>