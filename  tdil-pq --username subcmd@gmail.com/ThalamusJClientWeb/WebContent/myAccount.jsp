<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>

<html>
<head>
<%@ include file="includes/userLogged.jspf" %>
<%@ include file="includes/head.jsp" %>
<style type="text/css">
<!--
#catalog {
	background-image: url(images/skin_nrg/myAccount.jpg);
	background-repeat: no-repeat;
	background-position: center top;
	width:1000px;
	height:580px;
	margin:0 auto;
}
#links {
	font-family: 'Gill Sans MT', Genova, Arial, Helvetica, sans-serif;
	font-size: 16px;
	color: #2d0703;
	width:100%;
	top:456px;
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
	<div id="links" align="center"><a href="index.jsp">back to the home page</a>  .  <a href="catalog.jsp">Rewards Catalog</a>  .  <a href="shipments.jsp">Shipment Status</a>  .  <a href="legal.jsp">Privacy</a></div>
</div>
</body>
</html>