<?php include './includes/getequivalencia.php';?>

<?php

$lang = $_REQUEST['lang'];
$page = $_REQUEST['page'];
$destpath = ereg_replace('(.*)/[a-z|A-Z][a-z|A-Z]/.*', '\1/'. $lang, $page);
$originlang = ereg_replace('.*/([a-z|A-Z][a-z|A-Z])/.*', '\1', $page);
$originpage = ereg_replace('.*/[a-z|A-Z][a-z|A-Z]/(.*)', '\1', $page);
$destinationpage = getequivalencia($originpage, $originlang, $lang);
header ('Location: ' . $destpath . '/' . $destinationpage);
?>