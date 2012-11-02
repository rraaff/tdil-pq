<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalContactForm"%>
<%@ include file="includes/userLogged.jspf" %>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalContactForm"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="profesionalContactData"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Perfil del profesional</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
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
			<h1>Datos de <bean:write name="ProfesionalContactForm" property="profesional.businessname"/></h1>
			<h2>Contact&aacute; al profesional para solicitar presupuesto</h2>
		</div>
		<% ProfesionalContactForm profesionalContactForm = (ProfesionalContactForm)session.getAttribute("ProfesionalContactForm"); 
			Profesional profesional = profesionalContactForm.getProfesional();
		%>
		<div id="formContent">
			<div id="formSection" style="width:920px;">
				<h2><bean:write name="ProfesionalContactForm" property="profesional.completeName"/></h2>
				<div class="myRow width100per">
					<div class="myLabel width100per">Tel&eacute;fono <bean:write name="ProfesionalContactForm" property="profesional.phonetype"/>: <strong><bean:write name="ProfesionalContactForm" property="profesional.phoneareacode"/>-<bean:write name="ProfesionalContactForm" property="profesional.phonenumber"/></strong></div>
				</div>
				<div class="myRow">
					<div class="myLabel width80">E-Mail</div>
					<div class="myLabel width830"><strong><bean:write name="ProfesionalContactForm" property="profesional.email"/></strong></div>
				</div>
				<!--div class="myRow">
					<div class="myLabel width400">Nombre profesional/empresa: <bean:write name="ProfesionalContactForm" property="profesional.businessname"/></div>
				</div-->
				<div class="myRow">
					<div class="myLabel width80">Ubicaci&oacute;n</div>
					<div class="myLabel width830"><strong><bean:write name="ProfesionalContactForm" property="geoLevelPath"/></strong></div>
				</div>
				<div class="myRow">
					<div class="myLabel width80">Web</div>
					<div class="myLabel width830"><strong><%= (profesional.getWebsite() != null) ? profesional.getWebsite() : "-" %></strong></div>
				</div>
				<div class="myRow">
					<div class="myLabel width80">Facebook</div>
					<div class="myLabel width830"><strong><%= (profesional.getFacebook() != null) ? profesional.getFacebook() : "-" %></strong></div>
				</div>
				<div class="myRow">
					<div class="myLabel width80">Horario</div>
					<div class="myLabel width830"><strong><bean:write name="ProfesionalContactForm" property="profesional.businesshours"/></strong></div>
				</div>
				<div class="myRow">
					<div class="myLabel width80">Descripci&oacute;n</div>
					<div class="myLabel width830"><strong><bean:write name="ProfesionalContactForm" property="profesional.description"/></strong></div>
				</div>
				<div class="myRow height30" align="center" style="padding-top:20px;"><a class="inputButtonHelper" style="color:#000000; text-decoration:none;" href="javascript:window.history.back();">Volver</a></div>
			</div>
		</div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>