<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ include file="includes/checkBoLogin.jsp"%>
<html>
<head>
<%@ include file="includes/boHead.jsp"%>
<%@ include file="includes/boErrorJS.jsp"%>
<link href="css/table.css" media="screen" rel="stylesheet" type="text/css" />
<link href="css/report_page.css" media="screen" rel="stylesheet" type="text/css" />
<script>
var oTable = null;
function doSearch() {
	if (oTable != null) {
		oTable.fnDestroy();
	}
	oTable = $('#reportTable').dataTable( {
					"bProcessing": true,
					"sAjaxSource": "./newsletterReport.do",
						"sDom": 'T<"clear">lfrtip',
						"oTableTools": {
							"sSwfPath": "swf/copy_cvs_xls_pdf.swf",
							"aButtons": [
								"csv",
								"xls",
								{
									"sExtends": "pdf",
									"sPdfOrientation": "landscape",
									"sPdfMessage": "Reporte de subscriptos al newsletter."
								},
								"print"
							]
						}
				} );
			}

</script>
</head>

<body>

	<%@ include file="includes/boMenu.jsp"%>

	<br>Reporte de subscriptos al newsletter
	
	<input type="button" onClick="doSearch();" value="Buscar">
	
	<div id="dynamic">
		<table cellpadding="0" cellspacing="0" border="0" class="display"
			id="reportTable">
			<thead>
				<tr>
					<th width="100%">email</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
			<tfoot>
				<tr>
					<th width="100%">email</th>
				</tr>
			</tfoot>
		</table>
	</div>

</body>
</html>