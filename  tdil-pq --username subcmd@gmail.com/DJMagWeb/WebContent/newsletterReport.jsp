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
<div id="header"></div>
<div id="container">
	<div style="height:50px; display:block;"><%@ include file="includes/boMenu.jsp"%></div>
	<div id="formulariosBase">
		<h1>Reporte de subscriptos al newsletter</h1>
		<div id="conteinerScrollable">
			<div class="renglon width860 height50">
				<div class="label width860 height50" style="text-align:center;"><input type="button" onClick="doSearch();" value="Buscar"></div>
			</div>
			<div class="renglon width860 height250">
				<div class="label width860"><div id="dynamic">
					<table class="display" id="reportTable">
						<thead>
							<tr>
								<th class="headerTablas" width="100%">email</th>
							</tr>
						</thead>
						<tbody>
			
						</tbody>
						<tfoot>
							<tr>
								<th class="headerTablas" width="100%">email</th>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>