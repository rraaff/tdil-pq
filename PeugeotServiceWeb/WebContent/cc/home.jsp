<%@page import="com.tdil.utils.StringUtils"%>
<%@page import="com.tdil.struts.resources.ApplicationResources"%>
<%@page import="com.tdil.ljpeugeot.struts.forms.SearchUserForm"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="includes/userLogged.jspf" %><%--
--%><%@ include file="includes/mustBeLogged.jspf" %><%--
--%>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Peugeot :: CallCenter Application</title>
<link rel="icon" href="../favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_font_embeder.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_backdoor.css" />
</head>
<body>
<%@ include file="../admin/includes/header.jsp" %>
<%@ include file="includes/menu.jspf" %>
<section id="titles">
	<h1>LoJack Peugeot CallCenter Application</h1>
	<h2>Búsqueda de usuarios</h2>
</section>
<section id="content">
	<article>

		<html:form method="POST" action="/cc/searchUser">
			<% SearchUserForm searchUserForm = (SearchUserForm)session.getAttribute("SearchUserForm"); %>
			<fieldset>
				<label>DNI</label>
				<html:text name="SearchUserForm" property="dni" />
			</fieldset>
			<fieldset>
				<label>Email</label>
				<html:text name="SearchUserForm" property="email" />
			</fieldset>
			<fieldset class="botonera">
				<input type="submit" id="submitregister" value="Buscar" class="buttonSend">
			</fieldset>
			<% if (searchUserForm.isSearchEmpty()) { %>
				<h3>No se ha encontrado el usuario</h3>
			<% } else { %>
				<% if (searchUserForm.getUser() != null) { %>
					<% if (searchUserForm.getContactData() == null) { %>
						<h3>El usuario no ha definido sus datos de contacto</h3>
					<% } else { %>
						<h3>Información de contacto</h3>
						<fieldset>
							<label>Primer contacto</label>
							<label><%=StringUtils.notNullValueOf(searchUserForm.getContactData().getContact1name())%></label>
						</fieldset>
						<fieldset>
							<label>Relación</label>
							<label><%=!org.apache.commons.lang.StringUtils.isEmpty(searchUserForm.getContactData().getContact1relation()) ? ApplicationResources.getMessage("relation_" + searchUserForm.getContactData().getContact1relation()) : "-"%></label>
						</fieldset>
						<fieldset>
							<label>Teléfono</label>
							<label><%=StringUtils.notNullValueOf(searchUserForm.getContactData().getContact1phone())%></label>
						</fieldset>
						<fieldset>
							<label>Palabra clave</label>
							<label><%=StringUtils.notNullValueOf(searchUserForm.getContactData().getContact1secword())%></label>
						</fieldset>
						<fieldset>
							<label>Obra Social</label>
							<label><%=StringUtils.nvl(searchUserForm.getContactData().getContact1healthi(),"-")%></label>
						</fieldset>
						
						<hr/>
						
						<fieldset>
							<label>Segundo contacto</label>
							<label><%=StringUtils.notNullValueOf(searchUserForm.getContactData().getContact2name())%></label>
						</fieldset>
						<fieldset>
							<label>Relación</label>
							<label><%=!org.apache.commons.lang.StringUtils.isEmpty(searchUserForm.getContactData().getContact2relation()) ? ApplicationResources.getMessage("relation_" + searchUserForm.getContactData().getContact2relation()) : "-"%></label>
						</fieldset>
						<fieldset>
							<label>Teléfono</label>
							<label><%=StringUtils.nvl(searchUserForm.getContactData().getContact2phone(),"-")%></label>
						</fieldset>
						
						<hr/>
						
						<fieldset>
							<label>Tercer contacto</label>
							<label><%=StringUtils.notNullValueOf(searchUserForm.getContactData().getContact3name())%></label>
						</fieldset>
						<fieldset>
							<label>Relación</label>
							<label><%=!org.apache.commons.lang.StringUtils.isEmpty(searchUserForm.getContactData().getContact3relation()) ? ApplicationResources.getMessage("relation_" + searchUserForm.getContactData().getContact3relation()) : "-"%></label>
						</fieldset>
						<fieldset>
							<label>Teléfono</label>
							<label><%=StringUtils.nvl(searchUserForm.getContactData().getContact3phone(),"-")%></label>
						</fieldset>
					<% } %>
				<% } %>
			<% } 
			searchUserForm.setSearchEmpty(false);
			%>
		</html:form>
	</article>
</section>
</body>
</html>