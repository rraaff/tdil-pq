<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>

<html>
<head>
<%@ include file="includes/head.jsp" %>
<style type="text/css">
<!--
#catalog {
	background-image: url(images/skin_nrg/tradeoff.jpg);
	background-repeat: no-repeat;
	background-position: center top;
	width:1000px;
	height:580px;
	margin:0 auto;
}
#btn_unselected {
	width:194px;
	height:62px;
	top:360px;
	left:540px;
	position:relative;
	z-index:4;
}
#links {
	font-family: 'Gill Sans MT', Genova, Arial, Helvetica, sans-serif;
	font-size: 15px;
	color: #2d0703;
	width:200px;
	top:402px;
	left:260px;
	position:relative;
	z-index:3;
}
#links a {
	padding-left:10px;
	padding-right:10px;
}
-->
</style>
</head>
<body>
<div id="catalog">
	<div id="links" align="center"><a href="catalog.jsp">back to the Prize selection</a></div>
	<div id="btn_unselected"><a href="checkout.jsp" title="Checkout now!"><img src="images/skin_nrg/btn_checkout.png" width="194" height="62"></a></div>
</div>
</body>
</html>