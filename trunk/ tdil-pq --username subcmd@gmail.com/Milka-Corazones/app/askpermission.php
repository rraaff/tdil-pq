<html>
<body>
<?php 
$url = $facebook->getLoginUrl(array(
		'redirect_uri' => APPLICATION_URL . '/logincallback.php'
));
echo '<a href="' . $url . '" target="_top"><img src="../images/permisos.jpg" width="790" height="700" border="0"></a> ';
?>
</body>
</html>