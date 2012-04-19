<%@ page info="boLogin"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<html>
<head>
<%@ include file="includes/boHead.jsp"%>
<%@ include file="includes/boErrorJS.jsp"%>
<style>
	.renglon{
		width:250px;
		height:35px;
	}
	.label {
		width:80px;
	}
</style>
</head>

<body>
<div id="header"></div>
<div id="container">
	<div id="loginBase">
		<h1>Ingreso al Administrador</h1>
		<html:form method="POST" action="/login">
		<div class="renglon">
			<html:hidden name="LoginForm" property="operation" value=""/>
			<div class="label">Usuario</div><html:text name="LoginForm" property="username"/><span class="errorText"><html:errors property="general" /></span>
		</div>
		<div class="renglon">
			<div class="label">Contrase&ntilde;a</div><html:password name="LoginForm" property="password"/>
		</div>
		<html:submit property="operation">Login</html:submit>
	
		</html:form>
	</div>
</div>
</body>
</html>