<?php
function getequivalencia($fromText, $from, $to) {
	$header = array();
	$fila = 1;
	$fromIndex = 0;
	$toIndex = 0;
	if (($gestor = fopen("includes/equivalencias.csv", "r")) !== FALSE) {
	    while (($datos = fgetcsv($gestor, 1000, ",")) !== FALSE) {
	        $numero = count($datos);
	        if ($fila == 1) {
	        	for ($c=0; $c < $numero; $c++) {
	        		if($datos[$c] == $from) {
	        			$fromIndex = $c;
	        		}
	        		if($datos[$c] == $to) {
	        			$toIndex = $c;
	        		}
	        	}
	        	$fila++;
	        } else {
	        	$fila++;
	        	if ($datos[$fromIndex] == $fromText) {
	        		fclose($gestor);
	        		return $datos[$toIndex];
	        	}
	        }
	    }
	    fclose($gestor);
	    return '';
	}
}
?>