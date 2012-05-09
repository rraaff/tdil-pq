<html>
<head>
<link href="../css/tdil.css" rel="stylesheet" type="text/css">
<style type="text/css">
#content {
	margin:0;
	padding:0;
	width:790px;
	height:700px;
	overflow:hidden;
}
</style>
</head>
<body>
<div id="content"><img src="../images/permisos.png" width="790" height="700" border="0" usemap="#Map">
<?php 
$url = $facebook->getLoginUrl(array(
		'redirect_uri' => APPLICATION_URL . '/logincallback.php'
));
echo '<map name="Map"><area shape="rect" coords="335,463,456,497" href="' . $url . '"></map>';
?>
</div>
</body>
</html>