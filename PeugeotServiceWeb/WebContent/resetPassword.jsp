<%@page import="com.tdil.thalamus.client.facade.json.beans.DocumentTypeBean"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.LoginForm"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.ResetPasswordForm"%><%--
--%><%@ include file="includes/agentInfo.jspf" %><%--
--%><%@page import="com.tdil.ljpeugeot.utils.ThalamusErrorFormatter"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><%@ page info="resetPassword"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="includes/checkThalamusUp.jspf" %><%--
--%><%@ include file="includes/userLogged.jspf" %><%--
--%><%if (websiteUser != null && websiteUser.isLogged()) {%> 
	<jsp:forward page="home.jsp"></jsp:forward>
<%
	return;
	}
%><!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>Peugeot AXS :: Recupero de Clave</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" media="screen" href="css/reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/website.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/website_logged.css" />
<!--[if lt IE 9]>
	<link type="text/css" rel="stylesheet" href="css/ie8-fixes.css" />
<![endif]-->
<%@ include file="includes/headNotLogged.jsp" %>
<script>
<%@ include file="includes/centerLayerJS.jspf" %>
$(document).ready(
	function(){
	
		$( "#closeresetPassLayerBase" ).click(function() {
			$( "#resetPassLayerBase" ).fadeOut();
			window.location = './index.jsp';
		});
	}
);

function resetPasswordOpen() {
	centerLayer($(window), $( "#resetPassLayerBase" ));
	centerLayer($(window), $( "#resetPassLayer" ));
}
</script>
</head>
<%@ include file="includes/version.jspf" %>
<body onload="javascript:resetPasswordOpen();">
<!-- Edit password layer -->
<div id="resetPassLayerBase" class="layerOnTop" style="display:none; z-index:1500;">
	<div id="resetPassLayer" class="layerModal width400">
		<section class="modal_header">
			<h2>Información importante</h2>
			<h3>Crea una nueva clave</h3>
			<button class="close" id="closeresetPassLayerBase">Cerrar <span></span></button>
		</section>
		<section class="modal_content">
			<span class="modal_subtitle">Completá los campos para modificar tu clave</span>
			<html:form method="POST" action="/resetPassword" styleClass="modal_wrapper">
				<div class="errorInForm"><%=ThalamusErrorFormatter.getErrorFrom(request, "token.err")%></div>
				<fieldset>
					<label>Tipo doc</label>
					<% ResetPasswordForm loginForm = (ResetPasswordForm)session.getAttribute("ResetPasswordForm"); %>
					<html:select name="ResetPasswordForm" property="documentType" >
						<option value="">Seleccione...</option>
						<% for (DocumentTypeBean codBean : LoginForm.getDocumentTypes()) { %>
							<option value="<%=codBean.getId()%>" <%=loginForm.getDocumentType() == codBean.getId() ? "selected" : ""%>>
								<%=codBean.getName()%></option>
						<% } %>
					</html:select>
				</fieldset>
				<fieldset>
					<label>Número</label>
					<html:text name="ResetPasswordForm" property="username"/>
					<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "RegisterForm.document.err")%></div>
				</fieldset>
				<div class="errorInForm"><%=ThalamusErrorFormatter.getErrorFrom(request, "principal.err")%></div>
				<fieldset>
					<label>Clave</label>
					<html:password name="ResetPasswordForm" property="password"/>
					<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "RegisterForm.password.err")%></div>
				</fieldset>
				<fieldset>
					<label>Repetir clave</label>
					<html:password name="ResetPasswordForm" property="retypePassword"/>
				</fieldset>
				<fieldset class="button_bar pOnlyTop25">
					<button class="botton_ahead" id="submitregister" type="submit">Modificar<span></span></button>
				</fieldset>
			</html:form>
		</section>
	</div>
</div>
</body>
</html>