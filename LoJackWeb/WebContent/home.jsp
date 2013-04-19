<%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><!--
--><%@ page info="home"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="includes/checkThalamusUp.jspf" %><!--
--><%@ include file="includes/userLogged.jspf" %><!--
--><%@ include file="includes/mustBeLogged.jspf" %>
<html>
<head>
<%@ include file="includes/head.jsp" %>
<script>
</script>
</head>
<body>
<a href="./goToEditProfile.do" title="Cambiar">
	<% if (websiteUser.getModelUser().getIdAvatar() != null && !websiteUser.getModelUser().getIdAvatar().equals(0)) { %>
		<img src="./download.st?id=<%=websiteUser.getModelUser().getIdAvatar()%>&type=PUBLIC&ext=<%=websiteUser.getModelUser().getExtAvatar()%>" width="30" height="30" align="absmiddle"> 
	<% } else { %>
		<img src="boImages/na.gif" width="30" height="30" align="absmiddle"> 
	<% } %>
	</a>
Cambiar mis datos | Cambiar mi password | hola <%=websiteUser.getName()%> | <a href="logout.do">Salir</a>
<hr> 
<% if (websiteUser.isHomeUser()) { %>
	<a href="productoHome.jsp">Producto Home</a>
<% } else { %>
	Producto Home
<% } %> | 
<% if (websiteUser.isHomeUser()) { %>
	<a href="#">Producto Prevent</a>
<% } else { %>
	Producto Prevent
<% } %> | 
<% if (websiteUser.isPetUser()) { %>
	<a href="#">Producto Pet</a>
<% } else { %>
	Producto Pet
<% } %> | 
 Boton de panico
</body>
</html>