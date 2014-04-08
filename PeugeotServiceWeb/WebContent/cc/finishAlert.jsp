<%@page import="com.tdil.ljpeugeot.model.ContactData"%>
<%@page import="com.tdil.ljpeugeot.model.valueobjects.AlertValueObject"%><%--
--%><%@page import="com.tdil.ljpeugeot.services.PeugeotService"%><%--
--%><%@page import="com.tdil.ljpeugeot.model.Alert"%><%--
--%><%@page import="com.tdil.utils.StringUtils"%><%--
--%><%@page import="com.tdil.struts.resources.ApplicationResources"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.SearchUserForm"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="includes/userLogged.jspf" %><%--
--%><%@ include file="includes/mustBeLogged.jspf" %><%--
--%><%AlertValueObject alert =  PeugeotService.getAlertInProgress(websiteUser.getId()); 
if (alert== null) { %><jsp:forward page="home.jsp"></jsp:forward><%}%>
<% ContactData contactData = alert.getContactData(); %>
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
<%@ include file="includes/headLogged.jsp" %>
<script>
$(document).ready(
		function(){
			$("form[name='finisha']").validate({
				errorPlacement: function(error, element) {
					error.appendTo( element.next("div"));
				},
				rules: { 
					'comment': {maxlength: 4000}
				},
				messages: {	
					'comment': {maxlength: "<span>Ingrese hasta 4000 caracteres.</span>"}
				}
			});
	}
);
</script>
</head>
<body>
<%@ include file="../admin/includes/header.jsp" %>
<%@ include file="includes/menu.jspf" %>
<section id="titles">
	<h1>LoJack Peugeot CallCenter Application</h1>
	<h2>Finalizar alerta</h2>
</section>
<section id="content">
	<article>
		<h3>Información del usuario</h3>
		<div class="data_group">
			<fieldset class="width100per">
				<label class="dato">Nombre</label>
				<label class="resultado"><%=StringUtils.notNullValueOf(alert.getUser().getFirstname())%></label>
			</fieldset>
			<fieldset class="width100per">
				<label class="dato">Apellido</label>
				<label class="resultado"><%=StringUtils.notNullValueOf(alert.getUser().getLastname())%></label>
			</fieldset>
		</div>
		<form action="finishProgress.jsp" name="finisha">
			<input type="hidden" name="alertId" value="<%=alert.getAlert().getId()%>">
			Comentario<br>
			<textarea name="comment" maxlength='4000'></textarea>
			<div></div>
			<input type="submit">
		</form>
	</article>
</section>
</body>
</html>