<html>
<head>
<link href="../css/tdil.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
#content {
	background-image: url(../images/error.png);
	background-repeat: no-repeat;
	background-position: center top;
	width:100%;
	height:100%;
}
#contentError{
	width: 400px;
	margin-top: 360px;
	margin-right: auto;
	margin-left: auto;
	text-align: center;
}
#boton {
	width: 400px;
	height:33px;
	margin-top: 70px;
	margin-right: auto;
	margin-left: auto;
	text-align: center;
}
-->
</style>
<body>
<div id="content">
	<div id="contentError"><?php echo $errorMessage;?></div>
	<?php 
	$redirect = 'https://www.facebook.com/'. PAGE_NAME . '?sk=app_'. APPLICATION_ID;
	?>
	<div id="boton"><a href="<?php echo $redirect;?>" target="_top"><img src="../images/btn_volver.png" alt="Volver" width="122" height="33"></a></div>
</div>
</body>
</html>
