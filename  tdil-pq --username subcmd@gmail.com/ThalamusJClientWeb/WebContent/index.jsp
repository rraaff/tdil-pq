<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>

<html>
<head>
<%@ include file="includes/userLogged.jspf" %>
<%@ include file="includes/head.jsp" %>
</head>
<body>
<h1>Thalamus Home</h1>

<% if (!logged) { %>
	<a href="goToRegister.do">Registrate</a>
<% } else { %>
	Registrate
<% } %>
<% if (!logged) { %>
	<a href="login.jsp">Ingresa</a>
<% } else { %>
	Hola <%= websiteUser.getName() %>
<% } %>
<a href="catalogo.jsp">Catalogo</a>
<a href="legal.jsp">Legal</a>
<% if (!logged) { %>
	Salir
<% } else { %>
	<a href="logout.do">Salir</a>
<% } %>

</body>
</html>			