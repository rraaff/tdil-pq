<html>
<body>
<style>
</style>

<?php 
$url = $facebook->getLoginUrl(array(
		'redirect_uri' => APPLICATION_URL . '/logincallback.php'
));
echo '<a href="' . $url . '" target="_top"><img src="../images/permisos_canvas.jpg" width="795" height="780" border="0"></a> ';
?>
</body>
</html>