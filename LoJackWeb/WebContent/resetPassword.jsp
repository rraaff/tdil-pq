<%@page import="com.tdil.thalamus.client.facade.json.beans.DocumentTypeBean"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.LoginForm"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.ResetPasswordForm"%><%--
--%><%@ include file="includes/agentInfo.jspf" %><%--
--%><%@page import="com.tdil.lojack.utils.ThalamusErrorFormatter"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><%@ page info="resetPassword"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="includes/checkThalamusUp.jspf" %><%--
--%><%@ include file="includes/userLogged.jspf" %><%--
--%><% if (websiteUser != null && websiteUser.isLogged()) { %> 
	<jsp:forward page="home.jsp"></jsp:forward>
<% 	return;
	} %><html>
<head>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/reset-styles.css" rel="stylesheet" media="screen">
<link href="css/sizers.css" rel="stylesheet" media="screen">
<link type="text/css" rel="stylesheet" href="css/tdil.bootstrap.modifier.css" />
<link type="text/css" href="css/index_modales.css" rel="stylesheet" />
<link type="text/css" href="css/index_social.css" rel="stylesheet" />
<link type="text/css" href="css/copyright.css" rel="stylesheet" />
<link type="text/css" href="css/laruedita.css" rel="stylesheet" />
<link type="text/css" href="css/flexi-background.css" rel="stylesheet" media="screen" />
<link type="text/css" href="css/mediaQueries.css" rel="stylesheet" />
<%@ include file="includes/head.jsp" %>
<script type="text/javascript" src="js/jstz.js"></script>
<script>
<%@ include file="includes/centerLayerJS.jspf" %>
$(document).ready(
	function(){
	
		$( "#closeparkingsNotLoggedLayer" ).click(function() {
			$( "#parkingsNotLoggedLayer" ).fadeOut();
		});
	}
);

function register() {
	centerLayer($(window), $( "#parkingsNotLoggedLayer" ));
	centerLayer($(window), $( "#resetPassLayer" ));
}
</script>
<!-- /*
function centerLayer(objWin, objLayer) {
	var top = (objWin.height() / 2) - (objLayer.height() / 2);
	var left = (objWin.width() / 2) - (objLayer.width() / 2);
	objLayer.css({
		top: '0px',
		left: '0px'
	}).fadeIn(500);
}*/ -->
<script src="js/flexi-background.js" type="text/javascript" charset="utf-8"></script>
</head>
<body onLoad="javascript:register();">
<div id="menu" style="display:none;">
	<ul class="menu">
		<li class="first"><a href="#" class="parent"><span>Ingresa</span></a>
			<div>
				<ul>

				</ul>
			</div>
		</li>
	</ul>
</div>
<%@ include file="includes/laRuedita.jsp" %>

<div id="flyingObjectContainer"> 
	<div id="logoIndex"><img src="images/skin_lj_rl/logos/lo-jack_index.png" /></div>
	
	<!-- div id="socialSingleSignOn">
		<div><span class="textInside">Ingresá con tus cuentas</span></div>
		<div>
			<ul>
				<li class="sofacebook"><a href="< %=ThalamusClientBeanFacade.getFacebookLogin().getUrl()%>" id="fb" title="Ingresá con tu cuenta de Facebook"></a></li>
				<li class="sotwitter"><a href="< %=twitterUrl.getUrl()%>" id="fb" title="Ingresá con tu cuenta de Twitter"></a></li>
			</ul>
		</div>
	</div-->
</div>

<!-- Edit password layer -->
<div id="parkingsNotLoggedLayer" class="layerOnTop" style="display:none; z-index:1500;">
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
					<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "RegisterForm.document.err")%>
				</fieldset>
				<%=ThalamusErrorFormatter.getErrorFrom(request, "principal.err")%>
				<fieldset>
					<label>Clave</label>
					<html:password name="ResetPasswordForm" property="password"/>
					<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "RegisterForm.password.err")%>
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
<%@ include file="includes/version.jspf" %></body>
</html>