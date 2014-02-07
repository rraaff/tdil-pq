<%@page import="com.tdil.ljpeugeot.struts.forms.SearchUserForm"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="includes/userLogged.jspf" %><%--
--%><%@ include file="includes/mustBeLogged.jspf" %><%--
--%><!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
	.smallmap { width:968px; height:450px; }
	#tags { display: none; }
	#docs p { font-size:12px; margin-bottom:0.5em; }
	#placaLoader { display:none; }
@media only screen and (orientation: landscape) and (max-width: 600px) {
	#shortdesc { float:right; width:25%; }
	#map { width:100%; height:100%; }
	#docs { font-size:12px; }
}
</style>
</head>
<body>
<a href="./logout.do">Salir</a>
<html:form method="POST" action="/cc/searchUser">
<% SearchUserForm searchUserForm = (SearchUserForm)session.getAttribute("SearchUserForm"); %>
<fieldset>
		<label>usuario</label>
		<html:text name="SearchUserForm" property="dni" />
	</fieldset>
	<fieldset>
		<label>email</label>
		<html:text name="SearchUserForm" property="email" />
	</fieldset>
	<fieldset>
		<input type="submit" id="submitregister" value="Login" class="buttonSend">
	</fieldset>
	<% if (searchUserForm.isSearchEmpty()) { %>
		No se ha encontrado el usuario
	<% } else { %>
		<% if (searchUserForm.getUser() != null) { %>
			<% if (searchUserForm.getContactData() == null) { %>
				El usuario no ha definido sus datos de contacto
			<% } else { %>
				Primer contacto: <%=searchUserForm.getContactData().getContact1name()%><br>
				Relacion: <%=searchUserForm.getContactData().getContact1relation()%><br>
				Te: <%=searchUserForm.getContactData().getContact1phone()%><br>
				Palabra clave: <%=searchUserForm.getContactData().getContact1secword()%><br>
				Obra Social: <%=searchUserForm.getContactData().getContact1healthi()%><br>
			<% } %>
		<% } %>
	<% } 
	searchUserForm.setSearchEmpty(false);
	%>
</html:form>

</body>
</html>