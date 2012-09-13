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
#registrationSucessful {
	background-image: url(images/skin_nrg/registration_successful.jpg);
	background-repeat: no-repeat;
	width: 990px;
	height: 580px;
	margin: 0 auto;
}
#textMessage {
	width:440px;
	height:60px;
	font-family: 'Gill Sans MT', Genova, Arial, Helvetica, sans-serif;
	font-size: 18px;
	color: #2d0703;
	left:276px;
	top:260px;
	position:relative;
}
#button {
	width:440px;
	height:58px;
	left:280px;
	top:318px;
	position:relative;
	text-align:right;
}
-->
</style>
</head>
<body>
<div id="registrationSucessful">
	<div id="textMessage" align="center"></div>
	<div id="button"><a href="index.jsp" title="Continue"><img src="images/skin_nrg/btn_continue.png" width="198" height="56"></a></div>
</div>
</body>
</html>			