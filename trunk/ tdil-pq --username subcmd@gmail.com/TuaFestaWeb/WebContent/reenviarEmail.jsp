<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="reenviarEmail"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Recupero de clave</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<script>
$(document).ready(
	function(){
		
			$("form[name='ResendEmailForm']").validate({
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
			<h1>Reenv&iacute;o de email</h1>
			<h2>Ingrese su E-Mail y le enviaremos un nuevo E-mail de verificaci&oacute;n.</h2>
		</div>
		<div id="formContent">
			<html:form method="POST" action="/resendEmail">
				<div id="loginBase" style="width:300px; height:100px; margin:75px auto; padding-top:25px;">
					<div class="myRow">
						<div class="myLabel width60" align="right">E-Mail</div>
						<div class="myLabel width240" id="Email"><html:text name="ResendEmailForm" property="email" styleClass="normalField width200"/> <%=TuaFestaErrorFormatter.getErrorFrom(request, "ResendEmailForm.email.err")%></div>
					</div>
					<div class="myRow">
						<div class="myLabel width100" align="center" style="padding-top:20px;"><a href="index.jsp">Volver</a></div>
						<div class="myLabel width200" align="center"><input type="submit" value="Enviar email" class="buttonSubmit" /></div>
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