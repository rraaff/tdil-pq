<html>
<head>
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
	width:740px;
	height:470px;
	overflow:hidden;
}
</style>
</head>
<body>
<?php 
$url = $facebook->getLoginUrl(array(
		'redirect_uri' => APPLICATION_URL . '/logincallbackcanvas.php'
));
echo '<div id="content"><a href="' . $url . '"><img src="../images/permisos_canvas.jpg" width="740" height="470" border="0"/></a></div>';
?>
</body>
</html>