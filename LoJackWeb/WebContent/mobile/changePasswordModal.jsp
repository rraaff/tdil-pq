<%@page import="com.tdil.lojack.struts.forms.ChangePasswordForm"%>
<%@page import="com.tdil.lojack.web.LoJackErrorFormatter"%>
<%@page import="com.tdil.lojack.struts.forms.EditProfileForm"%>
<%@page import="com.tdil.lojack.utils.SystemPropertiesKeys"%>
<%@page import="com.tdil.lojack.utils.SystemPropertyUtils"%>
<%@page import="com.tdil.lojack.struts.forms.CameraForm"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><!--
--><%@ page info="home"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="../includes/checkThalamusUp.jspf" %><!--
--><%@ include file="../includes/userLogged.jspf" %><!--
--><%@ include file="./includes/mustBeLogged.jspf" %>
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
<div id="user"><span class="userSaludation">Hola:&nbsp;</span><span class="userName"><%=websiteUser.getName()%></span></div>
<div id="internalHeader">
	<ul>
		<li><a href="./goToChangePasswordMobile.do" title="Cambiar mis clave">Cambiar clave</a></li>
		<li><a href="home.jsp" title="Volver">< Volver</a></li>
		<li><a href="./logoutMobile.do" class="back" title="Salir del sistema">Salir</a></li>
	</ul>
</div>
<div id="registrationContent">
	<h1>Cambiar clave</h1>
	<html:form method="POST" action="/mobile/changePasswordMobile">
		<div class="myRow">
			<div class="myLabel width120">Clave actual</div>
			<div class="myLabel width270"><html:password name="ChangePasswordFormMobile" property="oldPassword" styleClass="normalField width250"/></div>
			<%=LoJackErrorFormatter.getErrorFrom(request, ChangePasswordForm.oldPassword_key + ".err")%>
		</div>
		<div class="myRow">
			<div class="myLabel width120">Nueva clave</div>
			<div class="myLabel width270"><html:password name="ChangePasswordFormMobile" property="newPassword" styleClass="normalField width250"/></div>
			<%=LoJackErrorFormatter.getErrorFrom(request, ChangePasswordForm.newPassword_key + ".err")%>
		</div>
		<div class="myRow">
			<div class="myLabel width120">Repetir claeve</div>
			<div class="myLabel width270"><html:password name="ChangePasswordFormMobile" property="confirmNewPassword" styleClass="normalField width250"/></div>
			<%=LoJackErrorFormatter.getErrorFrom(request, ChangePasswordForm.confirmNewPassword_key + ".err")%>
		</div>	
		<div class="myRow" align="center"><input type="submit" class="buttonSend" /></div>
	</html:form>
</div>
</body>
</html>