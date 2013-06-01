<%@ page info="index"%><!--
--><%@page import="com.tdil.lojack.struts.forms.LoginForm"%><!--
--><%@page import="com.tdil.thalamus.client.facade.json.beans.DocumentTypeBean"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="../includes/checkThalamusUp.jspf" %><!--
--><%@ include file="../includes/userLogged.jspf" %><!--
--><% if (websiteUser != null && websiteUser.isLogged()) { %>
	<jsp:forward page="home.jsp"></jsp:forward>
<% 	return;
	} %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
</head>
<body>
<html:form method="POST" action="/requestResetPasswordMobile">
	<h3>Recuperá tu clave</h3>
	<p style="padding-bottom:15px;">Ingresá tu DNI y te enviaremos por E-Mail un link de acceso exclusivo, para generar tu nueva clave.</p>
	<fieldset>
		<label>DNI</label>
		<html:text name="RequestResetPasswordFormMobile" property="username" styleClass="width240" />
	</fieldset>
	<fieldset>
		<button type="submit" id="submitforgotPassword" class="indexButtonBase">Enviar</button>
	</fieldset>
</html:form>
</body>
</html>