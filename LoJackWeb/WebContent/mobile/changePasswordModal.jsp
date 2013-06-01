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
</head>
<body>
	<h3>Cambiar clave</h3>
	<html:form method="POST" action="/changePasswordMobile">
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
		<div class="myRow" align="center"><input type="submit" value="Grabar" /></div>

	</html:form>
</body>
</html>