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
	background-image: url(images/skin_nrg/catalog_01.jpg);
	background-repeat: no-repeat;
	background-position: center top;
	width:1000px;
	height:580px;
	margin:0 auto;
}
#select {
	width:181px;
	height:125px;
	top:128px;
	left:410px;
	position:relative;
	z-index:5;
}
#btn_unselected {
	width:234px;
	height:82px;
	top:306px;
	left:590px;
	position:relative;
	z-index:4;
}
#links {
	font-family: 'Gill Sans MT', Genova, Arial, Helvetica, sans-serif;
	font-size: 18px;
	color: #2d0703;
	width:100%;
	top:308px;
	left:0px;
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
	<div id="select"><img src="images/skin_nrg/selected.jpg" width="181" height="125"></div>
	<div id="btn_unselected"><a href="tradeoff.jsp" title="Trade-off now!"><img src="images/skin_nrg/btn_tradeOff_on.png" width="234" height="82"></a></div>
	<div id="links" align="center"><a href="index.jsp">back to the home page</a><a href="myAccount.jsp">My Account</a><a href="shipments.jsp">Shipment Status</a><a href="legal.jsp">Privacy</a></div>
</div>
</body>
</html>