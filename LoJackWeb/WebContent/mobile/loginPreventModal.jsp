<%@ page info="loginModal"%><!--
--><%@page import="com.tdil.lojack.struts.forms.LoginForm"%><!--
--><%@page import="com.tdil.thalamus.client.facade.json.beans.DocumentTypeBean"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="../includes/checkThalamusUp.jspf" %><!--
--><%@ include file="../includes/userLogged.jspf" %>
<%@ include file="includes/mustBeLogged.jspf" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="../favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/reset-styles.css" rel="stylesheet" type="text/css">
<link href="css/internal_menu.css" rel="stylesheet" type="text/css">
<link href="css/mobile_main.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="loginContent">
	<html:form method="POST" action="/mobile/loginPreventMobile">
		<fieldset>
			<label>CLAVE</label>
			<html:password name="PreventLoginFormMobile" property="password"/>
		</fieldset>
		<div class="errorInForm"></div>
		<fieldset>
			<input type="submit" id="submitlogin" value="Ingresar" class="indexLogin">
		</fieldset>
	</html:form>
</div>
</body>
</html>