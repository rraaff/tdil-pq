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
#homeLogOff {
	background-image: url(images/skin_nrg/home_logoff.jpg);
	background-repeat: no-repeat;
	width: 990px;
	height: 580px;
	margin: 0 auto;
}
#bottomBar {
	width: 990px;
	height: 119px;
	top: 440px;
	position:relative;
}
#registerButton {
	width: 314px;
	height: 99px;
	margin-left:20px;
	margin-top:10px;
	float:left;
}
#menuText {
	width: 490px;
	height: 32px;
	margin-left:20px;
	margin-top:45px;
	float:left;
}
#menuItem {
	width: 46px;
	height: 32px;
	float:left;
}
#separador {
	width: 21px;
	height: 32px;
	float:left;
}
#thalamus_driven {
	width: 125px;
	height: 50px;
	margin-top:35px;
	float:left;
}
-->
</style>
</head>
<body>
<div id="homeLogOff">
	<div id="bottomBar">
		<% if (!logged) { %>
			<div id="registerButton"><a href="goToRegister.do"><img src="images/skin_nrg/btn_register.png" width="314" height="99"></a></div>
			<div id="menuText">
				<div id="menuItem"><a href="login.jsp" title="Login now!"><img src="images/skin_nrg/btn_login.png" width="46" height="32"></a></div>
				<div id="separador"><img src="images/skin_nrg/puntoMenu.png" width="21" height="32"></div>
				<div id="menuItem"><a href="legal.jsp" title="See our legal policies"><img src="images/skin_nrg/btn_legal.png" width="46" height="32"></a></div>
			</div>
		<% } %>
		<div id="thalamus_driven"><a href="http://www.thalamuscorp.com/" title="Thalamus Driven"><img src="images/skin_nrg/thalamus_diven.png" width="125" height="50"></a></div>
	</div>
</div>
<!-- 

< % if (!logged) { %>
	<a href="login.jsp">Ingresa</a>
< % } else { %>
	Hola < % = websiteUser.getName() %>
< % } %>
< % if (!logged) { %>
	Salir
< % } else { %>
	<a href="catalogo.jsp">Catalogo</a>
	<a href="legal.jsp">Legal</a>
	<a href="logout.do">Salir</a>
< % } %>
-->
</body>
</html>			