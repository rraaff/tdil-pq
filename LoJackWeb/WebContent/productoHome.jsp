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
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/reset-styles" rel="stylesheet" media="screen">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="http://code.jquery.com/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<%@ include file="includes/headLogged.jsp" %>
<link href="css/tdil.bootstrap.modifier.css" rel="stylesheet" media="screen">
</head>
<body>
<header>
	<div class="userLoggedThalamusMenu">
		<!-- if user logged -->
		<ul class="correctNav">
			<li>Usuario: <span class="userName"><%=websiteUser.getName()%></span></li>
			<li class="toRight"><a href="logout.do" title="Salir del sistema">Salir</a></li>
			<li class="toRight"><a href="./goToChangePassword.do" title="Cambiar mis clave">Cambiar mi clave</a></li>
			<li class="toRight"><a href="./goToUpdatePerson.do" title="Cambiar mis datos">Cambiar mis datos</a></li>
		</ul>
		<!-- End IF -->
	</div>
</header>
<section id="productsMenu">
	<div class="userLoggedThalamusMenu">
		<ul class="correctNav">
			<li class="logo" title="Lo-Jack, Lo tuyo es tuyo"></li>
			<li class="toRight"><a href="#" title="¿Dónde estacioar? Te ayudamos a encontrar un lugar">Parking</a></li>
			<% if (websiteUser.isPetUser()) { %>
				<li class="toRight"><a href="#" title="Cuidá a tu mascota">Pets</a></li>
			<% } else { %>
				<li class="toRight"><a href="#" title="¿No tenes PET? Adquirilo acá">Pets</a></li>
			<% } %>
			<% if (websiteUser.isHomeUser()) { %>
				<li class="toRight"><a href="#" title="Monitoreá sus vehículos">Prevent</a></li>
			<% } else { %>
				<li class="toRight"><a href="#" title="¿No tenes PREVENT? Adquirilo acá">Prevent</a></li>
			<% } %>
			<% if (websiteUser.isHomeUser()) { %>
				<li class="toRight optHome"><a href="productoHome.jsp" title="Administrá sus alarmas, luces y cámaras">Home</a></li>
			<% } else { %>
				<li class="toRight optHome"><a href="#" title="¿No tenes HOME? Adquirilo acá">Home</a></li>
			<% } %>
		</ul>
	</div>
</section>
<section id="content">
	<div id="col1_170">
		<div class="tab"><img src="images/skin_lj_rl/tabs/servicion.png"></div>
		<ul class="tabServices">
			<li class="tabAlarms" ><a href="./goToHomeAlarms.do">Mis Alarmas</a></li>
			<li class="tabLights" ><a href="./goToHomeLights.do">Mis Luces</a></li>
			<li class="tabCameras"><a href="./goToHomeCamera.do">Mi Camara</a></li>
		<ul>
	</div>
	<div id="col1_794">
		
	</div>
</body>
</html>