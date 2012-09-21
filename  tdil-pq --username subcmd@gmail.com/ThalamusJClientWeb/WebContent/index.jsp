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
	background-position:center top;
	width: 990px;
	height: 580px;
	margin: 0 auto;
}
#homeLogged {
	background-image: url(images/skin_nrg/homeLogged.jpg);
	background-repeat: no-repeat;
	background-position:center top;
	height: 580px;
	width: 1050px;
	margin: 0 auto;
}
#nav {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 15px;
	font-weight:bold;
	line-height:22px;
	width:950px;
	height:22px;
	padding-top:10px;
}
#nav a {
	color:#FFFFFF;

	box-shadow: 0 0 2px 2px rgba(0, 0, 0, 0.4);
	-webkit-box-shadow: 0 0 2px 2px rgba(0, 0, 0, 0.4);
	-moz-box-shadow: 0 0 2px 2px rgba(0, 0, 0, 0.4);
	-o-box-shadow: 0 0 2px 2px rgba(0, 0, 0, 0.4);
}
#nav a .remarcado, .remarcado { color:#f1ce0b; }
#bottomBar {
	width: 970px;
	height: 119px;
	top: 10px;
	position:relative;
}
#registerButton {
	width: 388px;
	height: 110px;
	margin-left:20px;
	margin-top:10px;
	float:left;
}
#CodeButton {
	width: 314px;
	height: 99px;
	margin-left:470px;
	margin-top:2px;
	float:left;
}
#menuText {
	width: 413px;
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
body {
	color:#FFFFFF;
}
#activities {
	width:990px;
	height:410px;
	margin:0 auto;
}
#activities #activity01 {
	background-image: url(images/skin_nrg/act_01.png);
	background-repeat: no-repeat;
	background-position: center top;
	width:990px;
	height:410px;
	z-index:1;
	position:absolute;
}
#activities #activity02 {
	background-image: url(images/skin_nrg/act_02.png);
	background-repeat: no-repeat;
	background-position: center top;
	width:990px;
	height:410px;
	z-index:2;
	position:absolute;
}
#activities #activity03 {
	background-image: url(images/skin_nrg/act_03.png);
	background-repeat: no-repeat;
	background-position: -85px -10px;
	width:990px;
	height:410px;
	z-index:3;
	position:absolute;
}
#activities #activityNN {
	background-image: url(images/skin_nrg/act_NN.png);
	background-repeat: no-repeat;
	background-position: center top;
	width:990px;
	height:410px;
	z-index:4;
	position:absolute;
}
-->
</style>
</head>
<body>
<% if (logged) { %>
	<div id="homeLogged">
		<% if (logged && websiteUser.appliesToActivity(4)) { com.tdil.thalamusweb.utils.ThalamusWebUtils.loginToActivity(websiteUser, 4); %>
			<div id="nav" align="center"><a href="myAccount.jsp"><span class="remarcado">You have 100 points in your account</span></a>&nbsp;&nbsp;.&nbsp;&nbsp;<a href="myAccount.jsp">My&nbsp;Account</a>&nbsp;&nbsp;.&nbsp;&nbsp;<a href="catalog.jsp">Rewards Catalog</a>&nbsp;&nbsp;.&nbsp;&nbsp;<a href="shipments.jsp">Shipment Status</a>&nbsp;&nbsp;.&nbsp;&nbsp;<a href="legal.jsp">Privacy</a>&nbsp;&nbsp;.&nbsp;&nbsp;<a href="logout.do">Logout</a></div>
		<% } else { %>
			<div id="nav" align="center"><span class="remarcado">Hi <%= websiteUser.getName() %></span>&nbsp;&nbsp;.&nbsp;&nbsp;<a href="legal.jsp">Privacy</a>&nbsp;&nbsp;.&nbsp;&nbsp;<a href="logout.do">Logout</a></div>
		<% } %>
		<div id="activities">
			<% if (websiteUser.appliesToActivity(1)) { com.tdil.thalamusweb.utils.ThalamusWebUtils.loginToActivity(websiteUser, 1); %>
				<% if (websiteUser.appliesToActivity(2)) { com.tdil.thalamusweb.utils.ThalamusWebUtils.loginToActivity(websiteUser, 2); %>
					<% if (websiteUser.appliesToActivity(3)) { com.tdil.thalamusweb.utils.ThalamusWebUtils.loginToActivity(websiteUser, 3); %>
						<!-- composición 1 + 2 + 3 -->
						<div id="activity01" style="background-position: center -20px;">&nbsp;</div>
						<div id="activity02" style="background-position: center -25px;">&nbsp;</div>
						<div id="activity03" style="background-position: -70px -10px;">&nbsp;</div>
					<% } else { %>
						<!-- composición 1 + 2 -->
						<div id="activity01" style="background-position: 90px -40px;">&nbsp;</div>
						<div id="activity02" style="background-position: -200px -40px;">&nbsp;</div>
					<% } %>
				<% } else { %>
					<% if (websiteUser.appliesToActivity(3)) { com.tdil.thalamusweb.utils.ThalamusWebUtils.loginToActivity(websiteUser, 3); %>
						<!-- composición las 1 + 3 -->
						<div id="activity01" style="background-position: 450px -35px;">&nbsp;</div>
						<div id="activity03" style="background-position: -240px -25px;">&nbsp;</div>
					<% } else { %>
						<!-- composición 1 -->
						<div id="activity01" style="background-position: 270px -40px;">&nbsp;</div>
					<% } %>
				<% } %>
			<% } else { %>
				<% if (websiteUser.appliesToActivity(2)) { com.tdil.thalamusweb.utils.ThalamusWebUtils.loginToActivity(websiteUser, 2); %>
					<% if (websiteUser.appliesToActivity(3)) { com.tdil.thalamusweb.utils.ThalamusWebUtils.loginToActivity(websiteUser, 3); %>
						<!-- composición 2 + 3 -->
						<div id="activity02" style="background-position: -200px -40px;">&nbsp;</div>
						<div id="activity03" style="background-position: -240px -25px;">&nbsp;</div>
					<% } else { %>
						<!-- composición 2 -->
						<div id="activity02" style="background-position: -400px -20px;">&nbsp;</div>
					<% } %>
				<% } else { %>
					<% if (websiteUser.appliesToActivity(3)) { com.tdil.thalamusweb.utils.ThalamusWebUtils.loginToActivity(websiteUser, 3); %>
						<!-- composición 3 -->
						<div id="activity03">&nbsp;</div>
					<% } else { %>
						<!-- neutral -->
						<div id="activityNN">&nbsp;</div>
					<% } %>
				<% } %>
			<% } %>
		</div>
		<div id="bottomBar" style="margin-left:45px;">
			<% if (logged && websiteUser.appliesToActivity(4)) { com.tdil.thalamusweb.utils.ThalamusWebUtils.loginToActivity(websiteUser, 4); %>
				<div id="CodeButton"><a href="cupon.jsp"><img src="images/skin_nrg/btn_cupon.png" width="314" height="99"></a></div>
			<% } else { %>
				<div id="CodeButton">&nbsp;</div>
			<% } %>
			<div id="thalamus_driven" style="margin-left:30px;"><a href="http://www.thalamuscorp.com/" title="Thalamus Driven"><img src="images/skin_nrg/thalamus_diven.png" width="125" height="50"></a></div>
		</div>
	</div>
<% } else { %>
	<div id="homeLogOff">
		<div id="bottomBar" style="top:445px;">
			<div id="registerButton"><a href="goToRegister.do"><img src="images/skin_nrg/btn_register_new.png" width="388" height="110"></a></div>
			<div id="menuText">
				<div id="menuItem"><a href="login.jsp" title="Login now!"><img src="images/skin_nrg/btn_login.png" width="46" height="32"></a></div>
				<div id="separador"><img src="images/skin_nrg/puntoMenu.png" width="21" height="32"></div>
				<div id="menuItem"><a href="legal.jsp" title="See our legal policies"><img src="images/skin_nrg/btn_legal.png" width="46" height="32"></a></div>
			</div>
			<div id="thalamus_driven"><a href="http://www.thalamuscorp.com/" title="Thalamus Driven"><img src="images/skin_nrg/thalamus_diven.png" width="125" height="50"></a></div>
		</div>
	</div>
<% } %>
</body>
</html>			