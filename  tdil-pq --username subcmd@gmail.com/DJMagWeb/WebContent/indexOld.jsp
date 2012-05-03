<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>

<html>
<head>
<%@ include file="includes/head.jsp" %>
<script src="js/jquery.ajaxfileupload.js" type="text/javascript"></script>
<script>
$(document).ready(
	function(){
	$('input[type="file"]').ajaxfileupload({
		  'action': './ajaxUpload.do',
		  'params': {
		    'extra': 'xxx'
		  },
		  'onComplete': function(response) {
		    console.log('custom handler for file:');
		    alert(JSON.stringify(response));
		  },
		  'onCancel': function() {
		    console.log('no file selected');
		  }
		});			
	}
);
</script>
</head>
<body>
<form action="newsletter" name="newsletterForm" id="newsletterForm" method="POST">
	<table>
		<tr>
			<td><input type="file" name="fileup" id="fileup"></td>
		</tr>
		<tr>
		</tr>
	</table>
</form>

</body>
</html>			