<%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
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
<section id="titles">
	<h1>LoJack Peugeot CallCenter Application</h1>
	<h2>Ingreso de usuarios</h2>
	<h3>Versión: <%@ include file="../includes/version_view.jspf" %></h3>
</section>
<section id="content">
	<article>
		<html:form method="POST" action="/cc/login">
			<fieldset>
				<label>usuario</label>
				<html:text name="LoginCCForm" property="username" />
			</fieldset>
			<fieldset>
				<label>contraseña</label>
				<html:password name="LoginCCForm" property="password" />
			</fieldset>
			<fieldset class="botonera">
				<input type="submit" id="submitregister" value="Ingresar">
			</fieldset>
		</html:form>
	</article>
</section>
</body>
</html>