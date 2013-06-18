<%@page import="com.tdil.lojack.struts.forms.ContactForm"%><%--
--%><%@ page info="home"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.RegisterForm"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.LoginForm"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.DocumentTypeBean"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.StateBean"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.BrandBean"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.CountryBean"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.fields.PersonFieldNames"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.PersonFields"%><%--
--%><%@page import="com.tdil.struts.resources.ApplicationResources"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="../includes/checkThalamusUp.jspf" %><%--
--%><%@ include file="../includes/userLogged.jspf" %><%--
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
<style type="text/css">
@media only screen and (max-height : 320px) {
	#user {
		display: none;
	}
}
</style>
</head>
<body>
<% if (websiteUser != null && websiteUser.isLogged()) { %>
	<div id="user"><span class="userSaludation">Hola:&nbsp;</span><span class="userName"><%=websiteUser.getName()%></span></div>
<% } %>
<div id="internalHeader">
	<ul>
		<% if (websiteUser != null && websiteUser.isLogged()) { %>
			<li><a href="./goToChangePasswordMobile.do" title="Cambiar mis clave">Cambiar clave</a></li>
			<li><a href="home.jsp" title="Volver">< Volver</a></li>
			<li><a href="./logoutMobile.do" class="back" title="Salir del sistema">Salir</a></li>
		<% } else { %>
			<li><a href="./goToRegistrationMobile.do" title="No tengo cuenta, registrame.">Registrarme</a></li>
			<li><a href="legal.jsp">Legales</a></li>
			<li><a href="index.jsp" class="back" title="Volver al inicio">< volver</a></li>
		<% } %>
	</ul>
</div>

<% ContactForm contactForm = (ContactForm)session.getAttribute("ContactFormMobile"); %>
<div id="registrationContent">
	<h1>Contacto</h1>
	<html:form method="POST" action="/mobile/contactMobile">
		<% if (!contactForm.isRegisteredUser()) { %>
			<fieldset>
				<label>Nombre</label>
				<html:text name="ContactFormMobile" property="firstname"></html:text>
			</fieldset>
			<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "ContactForm.firstname.err")%>
			<div class="errorInForm"></div>
			<fieldset>
				<label>Apellido</label>
				<html:text name="ContactFormMobile" property="lastname"></html:text>
			</fieldset>
			<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "ContactForm.lastname.err")%>
			<div class="errorInForm"></div>
			<fieldset>
				<label>DNI</label>
				<html:text name="ContactFormMobile" property="documentNumber"></html:text>
			</fieldset>
			<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "ContactForm.document.err")%>
			<div class="errorInForm"></div>
			<fieldset>
				<label>E-mail</label>
				<html:text name="ContactFormMobile" property="email"></html:text>
			</fieldset>
			<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "ContactForm.email.err")%>
			<div class="errorInForm"></div>
			<fieldset>
				<label>Teléfono</label>
				<html:text name="ContactFormMobile" property="phone"></html:text>
			</fieldset>
			<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "ContactForm.phone.err")%>
			<div class="errorInForm"></div>
		<% } %>
		<fieldset>
			<label>Comentario</label>
			<html:textarea name="ContactFormMobile" property="content"></html:textarea>
		</fieldset>
		<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "ContactForm.content.err")%>
		<div class="errorInForm"></div>
		<fieldset>
			<input type="submit" id="submitregister" class="indexLogin" value="Enviar" />
		</fieldset>
	</html:form>
</div>
<%@ include file="../includes/version.jspf" %></body>
</html>