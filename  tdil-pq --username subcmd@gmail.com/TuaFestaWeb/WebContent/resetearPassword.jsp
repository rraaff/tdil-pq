<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="resetearPassword"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | R008-M1- Registro - Registro Clientes (paso 1)</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<script>
$(document).ready(
	function(){
		
			$("form[name='PasswordResetForm']").validate({
					errorPlacement: function(error, element) {
						error.appendTo( element.parent("div"));
					},
					rules: { 'email': {required: true, email: true}
					},
					messages: {
						'email': {required: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese el email.' />",
						email: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese un email valido.' />"}
					}
				});

		}
	);

</script>
<%@ include file="includes/boErrorJS.jsp" %>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Reseteo de clave</h1>
			<h2>Ingrese su email y le enviaremos una nueva clave.</h2>
		</div>
		<div id="formContent">
			<html:form method="POST" action="/resetPassword">
			<div id="formSection">
				<div class="myRow">
					<div class="myLabel width50">E-Mail</div>
					<div class="myLabel width280" id="Email"><html:text name="PasswordResetForm" property="email" styleClass="normalField width250"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "PasswordResetForm.email.err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel" align="center"><input type="submit" value="Resetear password" class="buttonSubmit" /></div>
				</div>
			</div>
			</html:form>
		</div>
		<!-- aca Termina el formulario -->
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>