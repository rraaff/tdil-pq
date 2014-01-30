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
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" media="screen" href="css/reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/tdil.bootstrap.modifier.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/index_menu.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/index_modales.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/index_social.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/copyright.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/laruedita.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/home_styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/flexi-background.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/mediaQueries.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/font_embeder.css" />
<!--[if lt IE 9]>
	<link type="text/css" rel="stylesheet" href="css/ie8-fixes.css" />
<![endif]-->
<%@ include file="includes/headNotLogged.jsp" %>
<script type="text/javascript" src="js/jstz.js"></script>
<script>
<%@ include file="includes/centerLayerJS.jspf" %>
$(document).ready(
	function(){
	
		$( "#closeresetPassLayerBase" ).click(function() {
			$( "#resetPassLayerBase" ).fadeOut();
		});
	}
);

function resetPasswordOpen() {
	centerLayer($(window), $( "#resetPassLayerBase" ));
	centerLayer($(window), $( "#resetPassLayer" ));
}
</script>
<style type="text/css">
	div.errorInForm { width: 100%; float:left; }
	.defaultLayerContent { width:auto; }
</style> 
</head>
<body onload="javascript:resetPasswordOpen();">
<script src="js/flexi-background.js" type="text/javascript" charset="utf-8"></script>
<header>
	<div id="floatyMenu">
		<div class="wrapper">
			<ul>
				<li><a href="index.jsp" title="Volver al inicio"><span>Inicio</span></a></li>
			</ul>
		</div>
	</div>
</header>

<%@ include file="includes/laRuedita.jsp" %>

<%@ include file="includes/contactLayers.jspf" %>
<%@ include file="includes/copyright.jsp" %>

<!-- Edit password layer -->
<div id="resetPassLayerBase" class="layerOnTop" style="display:none; z-index:1500;">
	<div id="centradorModalesParkingNo" class="defaultLayerStyles">
		<div id="resetPassLayer" class="defaultLayerContent">
			<h3>Crea una nueva clave</h3>
			<div class="myRow">Completá los campos para modificar tu clave</div>
			<html:form method="POST" action="/resetPassword">
				<%=ThalamusErrorFormatter.getErrorFrom(request, "token.err")%>
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
				<fieldset>
					<button type="submit" id="submitregister" class="indexButtonBase">Modificar</button>
				</fieldset>
			</html:form>
		</div>
	</div>
</div>
<%@ include file="includes/version.jspf" %>
</body>
</html>