<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>

<html>
<head>
<%@ include file="includes/head.jsp" %>
<script type='text/javascript' src='js/jquery.cookie.js'></script>
<script type='text/javascript' src='js/jquery.melt-button.js'></script>
<script>
$(document).ready(
	function(){
		$("#meltbutton").meltbutton({buttonType: 'photomilka', buttonId: '2', 'quantity' : 150});
		$("#meltbutton1").meltbutton({buttonType: 'photomilka', buttonId: '3', 'quantity' : 75});
	}
);
</script>
</head>
<body>

<div id="meltbutton"></div>

<div id="meltbutton1"></div>

</body>
</html>			