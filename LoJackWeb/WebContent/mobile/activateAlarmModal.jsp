<%@page import="com.tdil.lojack.web.LoJackErrorFormatter"%><!--
--><%@ page info="home"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="../includes/checkThalamusUp.jspf" %><!--
--><%@ include file="../includes/userLogged.jspf" %><!--
--><%@ include file="./includes/mustBeLogged.jspf" %><!--
--><%@ include file="./includes/mustBeHomeUser.jspf" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
</head>
<body>
	<h3>Activar alarma</h3>
	<html:form method="POST" action="/activateAlarmMobile">
		<div class="myRow">
			<div class="myLabel width120">Clave</div>
			<div class="myLabel width270"><html:password name="ActivateAlarmFormMobile" property="password" styleClass="normalField width250"/></div>
		</div>
		<div class="myRow" align="center"><input type="submit" value="Activar" /></div>
	</html:form>
</body>
</html>