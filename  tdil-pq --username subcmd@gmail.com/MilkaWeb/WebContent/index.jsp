<%@page import="com.tdil.milka.model.ClickCounter"%>
<%@page import="com.tdil.milka.web.MeltButton"%>
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
		$("div[id^='mb-']").each(function(indice,valor) {
		   $(valor).meltbutton();
		});
	}
	
);
</script>
</head>
<body>
<% ClickCounter c1 = new ClickCounter();
	c1.setId(1);
	c1.setOwnertype("photomilka");
	c1.setOwnerid(2);
	c1.setClicks(150);
%>
<%= MeltButton.meltButton(c1) %>
<%	c1.setId(2);
	c1.setOwnerid(3);
	c1.setClicks(75);
%>
<%= MeltButton.meltButton(c1) %>

</body>
</html>			