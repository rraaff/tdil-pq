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
<% EditProfileForm editProfileForm = (EditProfileForm)session.getAttribute("EditProfileFormMobile");
editProfileForm.setMobile(true);%>
<body>
	<h3>Cambiar avatar</h3>
	<html:form method="POST" action="/mobile/saveProfileMobile" enctype="multipart/form-data">
		<fieldset class="aligner">
			<logic:notEqual name="EditProfileFormMobile" property="imageId" value="0">
				<label><img id="avatar_img" src="./viewAvatarMobile.do" width="100" height="100" align="absmiddle"></label>
			</logic:notEqual>
			<logic:equal name="EditProfileFormMobile" property="imageId" value="0">
				<label><img id="avatar_img" src="images/skin_lj_rl/logos/avatarBase.png" width="100" height="100" align="absmiddle"></label>
			</logic:equal>
		</fieldset>
		<fieldset class="aligner">
			<label><html:file property="formFile"/></label>
		</fieldset>
		<fieldset>
			<label><%=LoJackErrorFormatter.getErrorFrom(request,EditProfileForm.avatar_key + ".err")%></label>
		</fieldset>
		<fieldset><button type="submit" class="indexButtonBase" style="margin-left:20px;">Guardar</button></fieldset>
	</html:form>
</body>
</html>