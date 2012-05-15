<html>
<head>
<link href="../css/tdil.css" rel="stylesheet" type="text/css">
<style type="text/css">
body {
	background-image: none;
	background-repeat: no-repeat;
	background-position: left top;
	overflow:hidden !important;
}
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
<?php 
$url = $facebook->getLoginUrl(array(
		'redirect_uri' => APPLICATION_URL . '/logincallback.php'
));
?>
<?php echo '<div id="content"><a href="' . $url . '><img src="../images/permisos.jpg" width="790" height="700" border="0"></a></div>'; ?>
</body>
</html>