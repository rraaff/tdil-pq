<?php
/* Esta pagina se encarga de procesar las aceptaciones de app request */
	include("../include/headers.php");
	require("../include/funcionesDB.php");
	
	require '../include/facebook.php';
	include("../include/app2constants.php"); 
	// Create our Application instance (replace this with your appId and secret).
	$facebook = new Facebook(array(
			'appId'  => APPLICATION2_ID,
			'secret' => APPLICATION2_SECRET,
	));
	
	// Get User ID
	$user = $facebook->getUser();
	if(!isset($_SESSION)) {
		session_start();
	}
	if(isset($_SESSION['app_data'])) { // si tiene datos de la otra pagina, entonces los desseteo
		unset($_SESSION['app_data']);
	}
	if ($user == 0) {
		if (isset($_REQUEST['request_ids'])) {
			$_SESSION['request_ids'] = $_REQUEST['request_ids']; // meto los datos en la session y redirijo
		}
		include("askpermissioncanvas.php");
		return;
	} else {
		$app_token = get_app_access(APPLICATION2_ID,APPLICATION2_SECRET);
	if(isset($_REQUEST['request_ids'])) {
		$request_ids = $_REQUEST['request_ids'];
	} else {
		$request_ids = $_SESSION['request_ids'];
	}
	$ok_to_procced = 0;
	/* Si viene con request_id, es una invitacion de facebook*/
	if(!empty($request_ids)) {
		/*
		 * Get the current user, you may use the PHP-SDK
		* or your own server-side flow implementation
		*/
		// We may have more than one request, so it's better to loop
		$requests = explode(',',$request_ids);
		$first = 0;
		$tojoin = 0;
		foreach($requests as $request_id) {
			// If we have an authenticated user, this would return a recipient specific request: <request_id>_<recipient_id>
			
			if ($tojoin == 0) {
				$tojoin = $request_id;
			}
			if($user) {
				$request_id = $request_id . "_{$user}";
			}
			// Get the request details using Graph API
			$request_content = json_decode(file_get_contents("https://graph.facebook.com/$request_id?$app_token"), TRUE);
			// An example of how to get info from the previous call
			$request_message = $request_content['message'];
			$from_id = $request_content['from']['id'];
			// An easier way to extract info from the data field
			// Now that we got the $item_id and the $item_type, process them
			// Or if the recevier is not yet a member, encourage him to claims his item (install your application)!
			if($user) {
				/*
				 * When all is done, delete the requests because Facebook will not do it for you!
				* But first make sure we have a user (OR access_token - not used here)
				* because you can't delete a "general" request, you can only delete a recipient specific request
				* <request_id>_<recipient_id>
				*/
				$deleted = file_get_contents("https://graph.facebook.com/$request_id?$app_token&method=delete"); // Should return true on success
			}
			/* Lo uno solo al primer grupo*/
			if ($first == 0) {
				$first = 1;
			}
		}
		// Esta todo ok, preparo la data para la app pendiente
		$fbid = $user;
		$connection = mysql_connect(DB_SERVER,DB_USER, DB_PASS) or die ("Problemas en la conexion");
		mysql_select_db(DB_NAME,$connection);
		
		// Si ya se unio a un amigo, error
		$SQL = "SELECT * FROM GROUP_APP2 WHERE groupmember_fbid = $user";
		$result = mysql_query($SQL) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
		$num_rows = mysql_num_rows($result);
		if ($num_rows > 0) {
			$errorMessage = "Ya te uniste a un amigo";
			include("showerrorcanvas.php");
			closeConnection($connection);
			return;
		}
		$tojoin = quote_smart($tojoin, $connection);
		$SQL = "SELECT * FROM GROUP_APP2 WHERE groupowner_fbid = $user AND groupmember_fbid = (SELECT groupowner_fbid FROM FB_INV_APP2 WHERE request_id = $tojoin AND groupmember_fbid = $fbid)";
		$result = mysql_query($SQL) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
		$num_rows = mysql_num_rows($result);
		if ($num_rows > 0) {
			$errorMessage = "Ya estas en pareja con ese amigo";
			include("showerrorcanvas.php");
			closeConnection($connection);
			return;
		}
		
		$SQL = "SELECT * FROM GROUP_APP2 WHERE groupowner_fbid = (SELECT groupowner_fbid FROM FB_INV_APP2 WHERE request_id = $tojoin AND groupmember_fbid = $fbid)";
		$result = mysql_query($SQL) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
		$num_rows = mysql_num_rows($result);
		if ($num_rows > 0) {
			$errorMessage = "Tu amigo ya tiene otro amigo";
			include("showerrorcanvas.php");
			closeConnection($connection);
			return;
		}
		
		$SQL = "SELECT * FROM USER_APP2 WHERE fbid = (SELECT groupowner_fbid FROM FB_INV_APP2 WHERE request_id = $tojoin AND groupmember_fbid = $fbid)";
		$group_owner = mysql_query($SQL) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
		$num_rows = mysql_num_rows($group_owner);
		if ($num_rows > 0) {
			$user_fbid = quote_smart($user, $connection);
			$SQL = "SELECT * FROM USER_APP2 WHERE fbid = $user_fbid";
			$group_member = mysql_query($SQL) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
			$num_rows = mysql_num_rows($group_member);
			if ($num_rows == 1) {
				$group_ownerrow = mysql_fetch_array($group_owner);
				$idgroup = $group_ownerrow['id'];
				$group_memberrow = mysql_fetch_array($group_member);
				$iduser = $group_memberrow['id'];
				// todo ok, registro la accion pendiente y muestro el link a tab para que termine de unirse al grupo
				$SQL = "DELETE FROM ACTION_APP2 WHERE fbid = $user_fbid";
				$result = mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
				// inserto la ultima
				$SQL = "INSERT INTO ACTION_APP2(fbid, userid, action, dataid) VALUES($user_fbid, $iduser, 'join_group',$idgroup)";
				$result = mysql_query($SQL,$connection) or die("MySQL-err.Query: " . $SQL . " - Error: (" . mysql_errno() . ") " . mysql_error());
				$ok_to_procced = 1;
			} else {
				$errorMessage = "Usuario inexistente";
				include("showerrorcanvas.php");
				closeConnection($connection);
				return;
			}
		} else {
			$errorMessage = "Grupo invalido";
			include("showerrorcanvas.php");
			closeConnection($connection);
			return;
		}
		closeConnection($connection);
	} else {
		$errorMessage = "La peticion ya expiro";
		include("showerrorcanvas.php");
		closeConnection($connection);
		return;
	}
	unset($_SESSION['request_ids']);
?>
<?php 
if ($ok_to_procced == 1) { 
	$redirect = 'https://www.facebook.com/'. PAGE_NAME . '?sk=app_'. APPLICATION2_ID;
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
<?php } 
} ?>