<%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
	.smallmap { width:968px; height:450px; }
	#tags { display: none; }
	#docs p { font-size:12px; margin-bottom:0.5em; }
	#placaLoader { display:none; }
@media only screen and (orientation: landscape) and (max-width: 600px) {
	#shortdesc { float:right; width:25%; }
	#map { width:100%; height:100%; }
	#docs { font-size:12px; }
}
</style>
</head>
<body>

<html:form method="POST" action="/cc/login">
	<fieldset>
		<label>usuario</label>
		<html:text name="LoginCCForm" property="username" />
	</fieldset>
	<fieldset>
		<label>contrase�a</label>
		<html:password name="LoginCCForm" property="password" />
	</fieldset>
	<fieldset>
		<input type="submit" id="submitregister" value="Login" class="buttonSend">
	</fieldset>
</html:form>

</body>
</html>