<%@ page info="boStatisticReport"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ include file="includes/checkBoLogin.jsp" %>
<html>
<head>
<%@ include file="includes/boHead.jsp" %>
<link href="css/table.css" media="screen" rel="stylesheet" type="text/css" />
<link href="css/report_page.css" media="screen" rel="stylesheet" type="text/css" />
<script>
$(document).ready(
	function(){
		$("#sFrom").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true,
			changeYear: true, yearRange: "1900:2020"});
		$("#sTo").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true,
			changeYear: true, yearRange: "1900:2020"});

		$('#sStatType').change(
			function() {
				var selValue = Number($(this).attr('value'));
				if (selValue == 8 || selValue == 9) {
					$('#sGroup').attr('disabled', 'true');
				} else {
					$('#sGroup').removeAttr('disabled');
				}
			}
		);
	}
);

var oTable = null;
function doSearch() {
	if (oTable != null) {
		oTable.fnDestroy();
	}
 	var sStatType = document.getElementById('sStatType').value;
 	var sGroup = document.getElementById('sGroup').value;
 	var sFrom = document.getElementById('sFrom').value;
 	var sTo = document.getElementById('sTo').value;
 	var sSum = document.getElementById('sSum').value;
	oTable = $('#reportTable').dataTable( {
					"bProcessing": true,
					"sAjaxSource": "./statisticReport.do",
						"fnServerParams": function ( aoData ) {
			            	aoData.push( { "name" : "sStatType","value" : sStatType },
			            			{ "name" : "sGroup","value" : sGroup },
			            			{ "name" : "sFrom","value" : sFrom },
			            			{ "name" : "sTo","value" : sTo },
			            			{ "name" : "sSum","value" : sSum } );
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
									"sPdfMessage": "Reporte.pdf"
								},
								"print"
							]
						}
				} );
			}

</script>
</head>
<body>
<%@ include file="includes/boMenu.jsp" %>
<div id="boWrapper">
	<div id="boCentral">
		<h1>Reportes estadisticos</h1>
		<div id="formulariosBase">
			<div class="renglon">
				<div class="label width150">Tipo de estadistica</div>
				<div class="label width300">
					<select id="sStatType" class="width250">
						<option value="0">Busquedas por geo level</option>
						<option value="1">Busquedas por categoria de productos</option>
						<option value="2">Busquedas por categoria de servicios</option>
						<option value="3">Busquedas por productos</option>
						<option value="4">Busquedas por servicios</option>
						<option value="5">Busquedas simples</option>
						<option value="6">Visitas al perfil del profesional</option>
						<option value="7">Contactos al perfil del profesional</option>
						<option value="8">Registraciones de profesionales</option>
						<option value="9">Registraciones de clientes</option>
						<option value="10">Contactos a promociones</option>
					</select>
				</div>
				<div class="label width50">Agrupar:</div>
				<div class="label width100">
					<select id="sGroup" class="width50">
						<option value="0">No</option>
						<option value="1">Si</option>
					</select>
				</div>
			</div>
			<div class="renglon">
				<div class="label width50">Desde:</div>
				<div class="label width100"><input type="text" name="sFrom" id="sFrom" class="width80"></div>
				<div class="label width50">Hasta:</div>
				<div class="label width100"><input type="text" name="sTo" id="sTo" class="width80"></div>
				<div class="label width100">Sumarizar por:</div>
				<div class="label width100">
					<select id="sSum" class="width80">
						<option value="0">No</option>
						<option value="1">Mes</option>
						<option value="2">Dia</option>
					</select>
				</div>
			</div>
			<div class="renglon" align="center"><input type="button" onClick="doSearch();" value="Buscar"></div>
			<div id="dynamic">
				<table class="display" id="reportTable">
					<thead>
						<tr>
							<th class="headerTablas" width="50%">Data</th>
							<th class="headerTablas" width="30%">Fecha</th>
							<th class="headerTablas" width="20%">Cantidad</th>
						</tr>
					</thead>
					<tbody>			
					</tbody>
					<tfoot>
						<tr>
							<th class="headerTablas" width="50%">Data</th>
							<th class="headerTablas" width="30%">Fecha</th>
							<th class="headerTablas" width="20%">Cantidad</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
</html>