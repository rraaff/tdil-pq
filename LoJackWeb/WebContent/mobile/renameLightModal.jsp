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
	<h3>Renombrar luz</h3>
	<html:form method="POST" action="/renameLightMobile">
		<div class="myRow">
			<div class="myLabel width120">Descripcion</div>
			<div class="myLabel width270"><html:text name="RenameLightFormMobile" property="description" styleClass="normalField width250"/></div>
		</div>
		<div class="myRow" align="center"><input type="submit" value="renombrar" /></div>
	</html:form>
</body>
</html>