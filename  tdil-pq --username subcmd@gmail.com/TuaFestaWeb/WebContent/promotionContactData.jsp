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
<title>Tua Festa | Promoci&oacute; (Datos de los profesionales)</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<style>
<!--
#formSection {
	width:920px;
}
#formSectionPart {
	width:300px;
	
	overflow:hidden;
	float:left;
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
			<h1>Promocion: <bean:write name="PromotionContactForm" property="promotion.name"/></h1>
			<h2>En esta p&aacute;gina est&aacute;n los datos de contacto de todos los profesionales incluidos en la promoci&oacute;n</h2>
		</div>
		<% PromotionContactForm promotionContactForm = (PromotionContactForm)session.getAttribute("PromotionContactForm"); 
			for (Profesional profesional : promotionContactForm.getProfesionals()) {
		%>
		<div id="formContent">
			<div id="formSection">
				<div id="formSectionPart">
					<h2 style="font-size:18px; line-height:18px; padding-top:0;"><%=profesional.getCompleteName()%></h2>
					<div class="myRow">
						<div class="myLabel">Tel&eacute;fono <%=profesional.getPhonetype()%>: <%=profesional.getPhoneareacode()%>-<%=profesional.getPhonenumber()%></div>
					</div>
					<div class="myRow">
						<div class="myLabel">E-Mail: <%=profesional.getEmail()%></div>
					</div>
				</div>
				<div id="formSectionPart" style="width:600px; margin-left:20px;">
					<h2 style="font-size:18px; line-height:18px; padding-top:0;"><%=profesional.getBusinessname()%></h2>
					<div class="myRow">
						<div class="myLabel width600">Ubicaci&oacute;n: <strong><%=GeoLevelUtils.getPath(profesional.getIdGeolevel())%></strong></div>
					</div>
					<div class="myRow">
						<div class="myLabel width300">Web: <strong><%= com.tdil.utils.StringUtils.nvl(profesional.getWebsite(),"-") %></strong></div>
						<div class="myLabel width300">Facebook: <strong><%= com.tdil.utils.StringUtils.nvl(profesional.getFacebook(),"-") %></strong></div>
					</div>
					<div class="myRow">
						<div class="myLabel width600">Horario de Atenci&oacute;n: <strong><%=com.tdil.utils.StringUtils.nvl(profesional.getBusinesshours(), "-")%></strong></div>
					</div>
					<div class="myRow">
						<div class="myLabel width600">Descripci&oacute;n: <span style="color:#666666; font-style:italic;"><%=com.tdil.utils.StringUtils.nvl(profesional.getDescription(),"-")%></span></div>
					</div>
					
				</div>
			</div>
		</div>
		<% } %>
		<div class="myRow height30" align="center" style="padding-top:20px;"><a class="inputButtonHelper" style="color:#000000; text-decoration:none;" href="javascript:window.history.back();">Volver</a></div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>