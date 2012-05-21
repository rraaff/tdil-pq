<?php 
	include("include/headers.php");
	require("include/funcionesDB.php");
	
	$errorMessage = "";
	if ($_SERVER['REQUEST_METHOD'] == 'POST'){
		// Inicio conexion
		$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");
		
		mysql_select_db(DB_NAME,$connection);
		
		$usuario = $_POST['usuario'];
		$password = $_POST['password'];
		
		$usuario = quote_smart($usuario, $connection);
		$password = quote_smart(sha1($password), $connection);
		
		$SQL = "SELECT * FROM SYSTEMUSER WHERE username = $usuario AND password = $password";
		$result = mysql_query($SQL);
		$num_rows = mysql_num_rows($result);
		
		if ($result) {
			if ($num_rows > 0) {
				session_start();
				$user = mysql_fetch_array($result);
				$_SESSION['boLogin'] = "1";
				$_SESSION['boNombre'] = $user['name'];
				header ("Location: boHome.php");
			} else {
				$errorMessage = "El usuario no es valido";
			}
		} else {
			$errorMessage = "Error de conexion";
		}
		
		closeConnection($connection);
	}
?>
<html>
<head>
<?php include("include/title_meta.php"); ?>
<link rel="icon" href="./favicon.ico" type="icon"/>
<!-- Contact Form CSS files -->
<link type='text/css' href='css/tdil_bo.css' rel='stylesheet' media='screen' />
</head>
<body>
<div id="header"></div>
<div id="container">
	<div id="loginBase">
		<h1 style="margin-bottom:0;">Administraci&oacute;n de la FB App &iquest;D&oacute;nde est&aacute;s bomb&oacute;n?</h1>
		<FORM NAME="form1" METHOD="POST" ACTION="boLogin.php">
			<div class="renglon width230">
				<div class="label width230" style="text-align:center;"><span class="errorText"><?PHP print $errorMessage;?></span></div>
			</div>
			<div class="renglon width230">
				<div class="label width80">Usuario</div>
				<div class="label width150"><INPUT TYPE='TEXT' Name='usuario' value="" maxlength="20" class="width150"/></div>
			</div>
			<div class="renglon width230" style="margin-bottom:20px;">
				<div class="label width80">Contrase&ntilde;a</div>
				<div class="label width150"><input type='password' name='password' value="" maxlength="20" class="width150" AUTOCOMPLETE="off"></div>
			</div>
			<input type="submit" name="Submit1" value="Ingresar">
		</FORM>
	</div>
</div>
</body>
</html>