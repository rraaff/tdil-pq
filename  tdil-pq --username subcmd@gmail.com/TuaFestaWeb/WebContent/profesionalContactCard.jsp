<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="includes/userLogged.jspf" %>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalProfileForm"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="profesionalContactCard"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | R009-M1- Mi cuenta - Perfil de usuario profesional</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<style>
<!--
.myRow {
	padding-bottom:5px;
}
#muroContainer {
	background:#CCCCCC;
	
	width:270px;
	height:500px;
	
	float:right;
}
-->
</style>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Perfil de <bean:write name="ProfesionalProfileForm" property="profesional.businessname"/></h1>
			<h2>Estos son tus datos, productos y servicios publicados. Tambi&eacute;n podr&aacute;s acceder a tu muro.</h2>
		</div>
		<% ProfesionalProfileForm profesionalProfileForm = (ProfesionalProfileForm)session.getAttribute("ProfesionalProfileForm"); 
			Profesional profesional = profesionalProfileForm.getProfesional();
		%>
		<div id="formContent">
			<div id="muroContainer">
				<html:form method="POST" action="/addWallComment">
					<html:text name="ProfesionalProfileForm" property="content" />
					MURO INSERT
					<input type="submit" value="Postear"/>
				</html:form>
			</div>
			<div id="formSection">
				<div class="myRow">
					<div class="myLabel width400"><bean:write name="ProfesionalProfileForm" property="profesional.completeName"/></div>
				</div>
				<div class="myRow">
					<div class="myLabel width400">Tel&eacute;fono <bean:write name="ProfesionalProfileForm" property="profesional.phonetype"/>: <bean:write name="ProfesionalProfileForm" property="profesional.phoneareacode"/>-<bean:write name="ProfesionalProfileForm" property="profesional.phonenumber"/></div>
				</div>
				<div class="myRow">
					<div class="myLabel width200">Sexo: <%=profesional.getSex().equals("m") ? "Masculino" : "Femenino"%></div>
					<div class="myLabel width200">Fecha Nac.: <bean:write name="ProfesionalProfileForm" property="birthDate"/></div>
				</div>
				<div class="myRow">
					<div class="myLabel width400">E-Mail: <bean:write name="ProfesionalProfileForm" property="profesional.email"/></div>
				</div>
			</div>
			<div id="formSection" class="width650">
				<div class="myRow">
					<div class="myLabel width400">Nombre profesional/empresa: <bean:write name="ProfesionalProfileForm" property="profesional.businessname"/></div>
				</div>
				<div class="myRow">
					<div class="myLabel width200">CUIT: <bean:write name="ProfesionalProfileForm" property="profesional.cuit"/></div>
					<div class="myLabel width200">IIBB: <bean:write name="ProfesionalProfileForm" property="profesional.iibb"/></div>
				</div>
				<div class="myRow">
					<div class="myLabel width400">Ubicaci&oacute;n: <bean:write name="ProfesionalProfileForm" property="geoLevelPath"/></div>
				</div>
				<div class="myRow">
					<div class="myLabel width200">Web: <%= (profesional.getWebsite() != null) ? profesional.getWebsite() : "-" %></div>
					<div class="myLabel width200">Facebook: <%= (profesional.getFacebook() != null) ? profesional.getFacebook() : "-" %></div>
				</div>
				<div class="myRow height50">
					<div class="myLabel width200">Horario de Atenci&oacute;n: <bean:write name="ProfesionalProfileForm" property="profesional.businesshours"/></div>
					<div class="myLabel width200">Descripci&oacute;n: <bean:write name="ProfesionalProfileForm" property="profesional.description"/></div>
				</div>
			</div>
			
			<div id="formSection" class="width650">
				<h2 style="float:left; padding-left:0; padding-bottom:0; margin-bottom:10px;">Productos y Servicios</h2>
				<div class="myRow">
					<div class="myLabel width600 comment">Recuerde que a cada producto o servicio le puede adjuntar una imagen, precios de referencia, &aacute;reas de covertura y otros detalles una vez finalizado el proceso de registraci&oacute;n.</div>
				</div>
				<div class="myRow">ACA VA EL LISTADO</div>
				Pendiente
			</div>
		</div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>