<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.utils.GeoLevelUtils"%>
<%@page import="com.tdil.tuafesta.struts.forms.PromotionContactForm"%>
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
			<h1>Datos de contacto de la promocion <bean:write name="PromotionContactForm" property="promotion.name"/></h1>
		</div>
		<% PromotionContactForm promotionContactForm = (PromotionContactForm)session.getAttribute("PromotionContactForm"); 
			for (Profesional profesional : promotionContactForm.getProfesionals()) {
		%>
		<div id="formContent">
			<div id="formSection">
				<div class="myRow">
					<div class="myLabel width400"><%=profesional.getCompleteName()%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width400">Tel&eacute;fono <%=profesional.getPhonetype()%>: <%=profesional.getPhoneareacode()%>-<%=profesional.getPhonenumber()%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width400">E-Mail: <%=profesional.getEmail()%></div>
				</div>
			</div>
			<div id="formSection" class="width650">
				<div class="myRow">
					<div class="myLabel width400">Nombre profesional/empresa: <%=profesional.getBusinessname()%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width400">Ubicaci&oacute;n: <%=GeoLevelUtils.getPath(profesional.getIdGeolevel())%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width200">Web: <%= com.tdil.utils.StringUtils.nvl(profesional.getWebsite(),"-") %></div>
					<div class="myLabel width200">Facebook: <%= com.tdil.utils.StringUtils.nvl(profesional.getFacebook(),"-") %></div>
				</div>
				<div class="myRow height50">
					<div class="myLabel width200">Horario de Atenci&oacute;n: <%=com.tdil.utils.StringUtils.nvl(profesional.getBusinesshours(), "-")%></div>
					<div class="myLabel width200">Descripci&oacute;n: <%=com.tdil.utils.StringUtils.nvl(profesional.getDescription(),"-")%></div>
				</div>
			</div>
		</div>
		<% } %>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>