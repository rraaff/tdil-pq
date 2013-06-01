<%@ page info="home"%><!--
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
<link rel="icon" href="favicon.ico" type="icon"/>
</head>
<body>
Home

<span class="userSaludation">Hola:&nbsp;</span><span class="userName"><%=websiteUser.getName()%></span><a href="../logoutMobile.do" title="Salir del sistema">Salir</a><br>

<a href="../goToEditProfileMobile.do">
<% if (websiteUser.getModelUser().getIdAvatar() != null && !websiteUser.getModelUser().getIdAvatar().equals(0)) { %>
	<img id="avatarImg" src="../download.st?id=<%=websiteUser.getModelUser().getIdAvatar()%>&type=PUBLIC&ext=<%=websiteUser.getModelUser().getExtAvatar()%>" width="30" height="30" align="absmiddle"> 
<% } else { %>
	<img id="avatarImg" src="images/skin_lj_rl/logos/avatarBase.png" width="32" height="32" align="absmiddle"> 
<% } %></a>

<a href="../goToChangePasswordMobile.do" title="Cambiar mis clave">Cambiar mi clave</a><br>
<a href="../goToUpdatePersonMobile.do" title="Cambiar mis clave">Cambiar mis datos</a><br>

</body>
</head>