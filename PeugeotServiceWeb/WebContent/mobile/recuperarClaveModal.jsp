<%@page import="com.tdil.ljpeugeot.struts.forms.RequestResetPasswordForm"%>
<%@ page info="index"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.LoginForm"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.DocumentTypeBean"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="../includes/checkThalamusUp.jspf" %><%--
--%><%@ include file="../includes/userLogged.jspf" %><%--
--%><% if (websiteUser != null && websiteUser.isLogged()) { %>
	<jsp:forward page="home.jsp"></jsp:forward>
<% 	return;
	} %><%--
--%><!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="../favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/reset-styles.css" rel="stylesheet" type="text/css">
<link href="css/internal_menu.css" rel="stylesheet" type="text/css">
<link href="css/mobile_main.css" rel="stylesheet" type="text/css">
<%@ include file="includes/head.jsp"%>
</head>
<body onload="document.RequestResetPasswordFormMobile.username.focus();">
<div id="internalHeader">
	<div id="logo"><img src="../images/skin_lj_rl/logos/lo-jack_mainLogo.png"></div>
	<ul>
		<li><a href="./goToRegistrationMobile.do" title="No tengo cuenta, registrame.">Registrarme</a></li>
		<li><a href="legal.jsp">Legales</a></li>
		<li><a href="index.jsp" class="back" title="Volver al inicio">< volver</a></li>
	</ul>
</div>
<div id="recoverPassContent">
	<html:form method="POST" action="/mobile/requestResetPasswordMobile">
		<p>Ingresá tu DNI y te enviaremos por E-Mail un link para que crees tu nueva clave. Si no lo recibiste, por favor revisá en correo no deseado.</p>
		<fieldset>
			<div class="errorInForm"><html:errors property="general" /></div>
		</fieldset>
		<fieldset>
			<label>Tipo doc</label>
			<% RequestResetPasswordForm loginForm = (RequestResetPasswordForm)session.getAttribute("RequestResetPasswordFormMobile"); %>
			<html:select name="RequestResetPasswordFormMobile" property="documentType" >
				<option value="">Seleccione...</option>
				<% for (DocumentTypeBean codBean : LoginForm.getDocumentTypes()) { %>
					<option value="<%=codBean.getId()%>" <%=loginForm.getDocumentType() == codBean.getId() ? "selected" : ""%>>
						<%=codBean.getName()%></option>
				<% } %>
			</html:select>
		</fieldset>
		<fieldset>
			<label>Número</label>
			<html:text name="RequestResetPasswordFormMobile" property="username" />
		</fieldset>
		<fieldset>
			<input type="submit" id="submitforgotPassword" class="buttonSend" value="Enviar">
		</fieldset>
	</html:form>
</div>
<%@ include file="../includes/version.jspf" %></body>
</html>