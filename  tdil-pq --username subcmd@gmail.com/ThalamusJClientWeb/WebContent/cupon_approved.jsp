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
	background-image: url(images/skin_nrg/cupon_congrats.jpg);
	background-repeat: no-repeat;
	background-position: center top;
	width:1000px;
	height:580px;
	margin:0 auto;
}
#links {
	font-family: 'Gill Sans MT', Genova, Arial, Helvetica, sans-serif;
	font-size: 20px;
	line-height:22px;
	color: #2d0703;
	width:430px;
	top:348px;
	left:290px;
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
	<div id="links" align="center"><a href="index.jsp">Go back to home</a> or <a href="cupon.jsp">Insert another code</a></div>
</div>
</body>
</html>