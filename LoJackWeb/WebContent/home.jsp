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
<script>
$(function() {
		$( "#accessPrevent" ).click(function() {
			 $.ajax({
					type: "POST",
					url: "http://test.lojackgis.com.ar:8080/webgis/Prevent_WebUI/loginportal.ashx",
					data: {
						SESSIONID: '<%=websiteUser.getToken().getCookie("JSESSIONID").getValue()%>',
						TIMEZONEOFFSET: -3
					},
					beforeSend: function (request) {
						alert(1);
						request.setRequestHeader("Lojack-Token", "a5b0981a0188bb9a5b7fe44b6c32d894");
					},
					success: function (data) {
						alert(data);
						if (data.status == 200)
							window.location = decodeURI(data.url);
						else
							alert(data.error);
					}
				});
		});
});
</script>
</head>
<body>
<header>
	<div class="userLoggedThalamusMenu">
		<!-- if user logged -->
		<ul class="correctNav">
			<li>
				<% if (websiteUser.getModelUser().getIdAvatar() != null && !websiteUser.getModelUser().getIdAvatar().equals(0)) { %>
					<a href="./goToEditProfile.do" title="Cambiar imagen"><img src="./download.st?id=<%=websiteUser.getModelUser().getIdAvatar()%>&type=PUBLIC&ext=<%=websiteUser.getModelUser().getExtAvatar()%>" width="42" height="42" align="absmiddle"></a>
				<% } %>
				Usuario: <span class="userName">Nombre</span></li>
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
				<li class="toRight"><a href="#" id="accessPrevent" title="Monitoreá sus vehículos">Prevent</a></li>
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
</body>
</html>