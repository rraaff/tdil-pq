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
<script type="text/javascript">
function enterPets() {
	var userDate = new Date();
	var userTimeZone = ( userDate.getTimezoneOffset()/60 )*( -1 );
	window.open('<%=com.tdil.lojack.pets.PetsConnector.getPetsLoginUrl()%>SESSIONID=<%=websiteUser.getJSESSIONID()%>&TIMEZONEOFFSET=' +userTimeZone+ '&LOJACKTOKEN=<%=com.tdil.lojack.pets.PetsConnector.getPetsToken()%>&AWSELB=<%=websiteUser.getAWSELB()%>', 'Lojack Pets');
}
</script>
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

<% if (websiteUser.isHomeUser()) { %>
	<a href="./productoHome.jsp">Home</a><br>
<% } %>
<% if (websiteUser.isPetUser()) { %>
	<a href="javascript:enterPets()">Pets</a>
<% } %>
<% if (websiteUser.isPreventUser()) { %>
	<% if (websiteUser.isPreventLogged()) { %>
		<a href="./productoPrevent.jsp" title="Administrar tus autos">Car</a>
	<% } else { %>
		<a href="./productoPreventLoginModal.jsp" title="Administrar tus autos">Car</a>
	<% } %>
<% } %>

</body>
</head>