<html>
<head>
<link href="../css/tdil.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	background-image: url(../images/errorinCanvas.jpg);
	background-repeat: no-repeat;
	background-position: center center;
}
#content {
	width: 790px;
	height: 700px;
	margin-left:auto;
	margin-right:auto;
	position:relative;
}
#contentError {
	color:#FFFFFF;
	font-family:Georgia, "Times New Roman", Times, serif;
	font-size:15px;
	width: 400px;
	margin-top: 300px;
	margin-right: auto;
	margin-left: auto;
	text-align: center;
	position:relative;
}
#boton {
	width: 400px;
	height: 33px;
	margin-top: 75px;
	margin-right: auto;
	margin-left: auto;
	text-align: center;
	position:relative;
}
-->
</style>
</head>
<body>
<div id="contentError"><?php echo $errorMessage;?></div>
<?php 
$redirect = 'https://www.facebook.com/'. PAGE_NAME . '?sk=app_'. APPLICATION_ID;
?>
<div id="boton"><a href="<?php echo $redirect;?>" target="_top"><img src="../images/btn_volver.png" alt="Volver" width="122" height="33"></a></div>
</body>
</html>
