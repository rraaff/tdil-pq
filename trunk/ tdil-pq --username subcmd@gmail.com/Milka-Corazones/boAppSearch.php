<?php 
	include("include/headers.php");
	require("include/funcionesDB.php");
	require("include/boCheckLogin.php");
?>
<html>
<head>
<?php include("include/title_meta.php"); ?>
<!-- Contact Form CSS files -->
<link type='text/css' href='css/tdil_bo.css' rel='stylesheet' media='screen' />
<?php include("include/headerBO.php"); ?>
<style type="text/css" title="currentStyle">
			@import "css/demo_page.css";
			@import "css/demo_table.css";
			@import "css/TableTools.css";
		</style>
		<script type="text/javascript" src="js/jquery.dataTables.js"></script>
			<script type="text/javascript" charset="utf-8" src="js/ZeroClipboard.js"></script>
		<script type="text/javascript" charset="utf-8" src="js/TableTools.min.js"></script>
<style>
	#content #page {
	width: 850px;
	padding: 0px;
	margin-top: 20px;
}
</style>	
<script>
var oTable = null;
function doSearch() {
	if (oTable != null) {
		oTable.fnDestroy();
	}
 	var sGender = document.getElementById('sGender').value;
 	var sMinParticipations = document.getElementById('sMinParticipations').value;
 	var sMaxParticipations = document.getElementById('sMaxParticipations').value;
 	var sMinWins = document.getElementById('sMinWins').value;
 	var sMaxWins = document.getElementById('sMaxWins').value;
	oTable = $('#example').dataTable( {
					"bProcessing": true,
					"sAjaxSource": "doAppSearch.php",
					 "fnServerParams": function ( aoData ) {
				            aoData.push( { "name" : "sGender","value" : sGender },
				            			{ "name" : "sMinParticipations","value" : sMinParticipations },
				            			{ "name" : "sMaxParticipations","value" : sMaxParticipations },
				            			{ "name" : "sMinWins","value" : sMinWins },
				            			{ "name" : "sMaxWins","value" : sMaxWins });
				        },
						"sDom": 'T<"clear">lfrtip',
						"oTableTools": {
							"sSwfPath": "swf/copy_cvs_xls_pdf.swf",
							"aButtons": [
								"csv",
								"xls",
								{
									"sExtends": "pdf",
									"sPdfOrientation": "landscape",
									"sPdfMessage": "Consulta de app 10."
								},
								"print"
							]
						}
				} );
			}

</script>
</head>
<body><!-- id="dt_example"-->
<div id="content">
	<div id="header">
		<div id="block">
			<div id="portaMenu"><?php include("include/menuBO.php"); ?><div id="hello">Hola <span class="remarcado"><?php echo($_SESSION['boNombre']);?></span></div></div>
		</div>
	</div>
	<div id="formulariosBase" class="height400" style="overflow:auto;">
		<h1>Estado de la aplicaci&oacute;n</h1>
		<h2>Busquedas</h2>
		<div class="renglon width500">
			<div class="label width50">Sexo:</div>
			<div class="label width100"><select id="sGender" class="width100"><option value="all">Todos</option><option value="male">Hombre</option><option value="female">Mujer</option></select></div>
		</div>
		<div class="renglon width500">
			<div class="label width120">Participaciones (como minimo):</div>
			<div class="label width50"><input type="text" id="sMinParticipations" class="width50"></div>
			<div class="label width50"></div>
			<div class="label width120">Participaciones (como maximo):</div>
			<div class="label width50"><input type="text" id="sMaxParticipations" class="width50"></div>
		</div>
		<div class="renglon width500">
			<div class="label width120">Premios ganados (como minimo):</div>
			<div class="label width50"><input type="text" id="sMinWins" class="width50"></div>
			<div class="label width50"></div>
			<div class="label width120">Premios ganados (como maximo):</div>
			<div class="label width50"><input type="text" id="sMaxWins" class="width50"></div>
		</div>
		<div class="renglon width200 height50"><input type="button" onClick="doSearch();" value="Buscar"></div>
		<div class="renglon width800 height300" style="overflow:auto;">
			<div id="dynamic">
				<table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
					<thead>
						<tr>
							<th width="5%">FB id</th>
							<th width="25%">FB nombre</th>
							<th width="25%">FB usuario</th>
							<th width="25%">FB sexo</th>
							<th width="25%">Nombre</th>
							<th width="25%">Participaciones</th>
							<th width="25%">Premios</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
					<tfoot>
						<tr>
							<th width="5%">FB id</th>
							<th width="25%">FB nombre</th>
							<th width="25%">FB usuario</th>
							<th width="25%">FB sexo</th>
							<th width="25%">Nombre</th>
							<th width="25%">Participaciones</th>
							<th width="25%">Premios</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
</html>