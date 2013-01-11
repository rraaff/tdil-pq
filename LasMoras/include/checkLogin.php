<?PHP
session_start();
if (!(isset($_SESSION['Login']) && $_SESSION['Login'] == 1)) {
	header ("Location: notLogged.php");
// TODO como hago el return aca
}
?>