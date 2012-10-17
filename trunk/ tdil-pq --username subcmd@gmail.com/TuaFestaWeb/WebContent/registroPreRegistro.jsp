<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.struts.action.LoginProfesionalFacebookAction"%>
<%@page import="com.tdil.tuafesta.web.SystemPropertyUtils"%>
<%@page import="com.tdil.tuafesta.utils.SystemPropertiesKeys"%>
<%@page import="com.tdil.tuafesta.struts.action.LoginClientFacebookAction"%>
<%@page import="com.tdil.facebook.Facebook"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.ServiceBean"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.ServiceAreaBean"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.ProductBean"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="com.tdil.tuafesta.model.Geo4"%>
<%@page import="com.tdil.tuafesta.model.Geo3"%>
<%@page import="com.tdil.tuafesta.model.Geo2"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalForm"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="registroPreRegistro"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Registro</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<!--link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" /-->
<%@ include file="includes/boErrorJS.jsp" %>
<style>
<!-- 
#moduloPreReg {
	background-repeat: no-repeat;
	background-position: center center;
	
	margin-top:20px;
	margin-bottom:20px;
	padding:30px;
	width:404px;
	height:194px;
	float:left;
}
#moduloPreReg h2 {
	font-size:20px;
	line-height:25px;
	padding-top:25px;
	padding-left:0;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.6);
}
#moduloPreReg p {
	color:#252e35;
	font-size:13px;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.6);
}
#moduloPreReg a {
	color:#004089;
}
#moduloPreReg a:hover {
	color:#000;
	text-shadow:none;
}
.cliente {
	background-image: url(images/skin_basic/backgrounds/preRegistroUsers.gif);
	margin-right:10px;
}
.cliente_title {
	color:#035b9c;
}
.profesional {
	background-image: url(images/skin_basic/backgrounds/preRegistroProfesionales.gif);
}
.profesional_title {
	color:#FFF;
	font-weight:normal;
	text-shadow: 0 1px 0 rgba(0, 0, 0, 0.6);
}
.remarcadoDCF6FC {
	color:#dcf6fc;
	font-weight:bold;
	text-shadow: 0 1px 0 rgba(0, 0, 0, 0.6);
}
#buttonFB {
	background-image: url(images/skin_basic/buttons/registerClientFB_off.png);
	width:324px;
	height:65px;
	margin:26px auto 0 auto;
}
#buttonFB:hover {
	background-image: url(images/skin_basic/buttons/registerClientFB_on.png);
}
#buttonProf {
	background-image: url(images/skin_basic/buttons/preReg_registerProfessional_off.png);
	height: 65px;
	width: 229px;
	margin:0 auto;
}
#buttonProf:hover {
	background-image: url(images/skin_basic/buttons/preReg_registerProfessional_on.png);
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
			<h1>Registro</h1>
			<h2>Busc&aacute;s productos y servicios para organizar tu fiesta o queres ofrecerlos para contactarte con clientes</h2>
		</div>
		<% 
			String apikey = SystemPropertyUtils.getSystemPropertValue(SystemPropertiesKeys.FB_API_KEY);
			String redirect = SystemPropertyUtils.getSystemPropertValue(SystemPropertiesKeys.SERVER_NAME);
		%>
		<div id="formContent" style="margin-top:0; padding-top:0;">
			<div id="moduloPreReg" class="cliente">
				<h2>&#191;Te queres registrar para entran en contacto con los mejores profesionales?</h2>
				<p>Podes registrarte sin costo alguno usando tu cuenta de Facebook o <a href="./goToRegistroCliente.do" title="Registrate sin usar tu cuenta de Facebook">simplemente cargando unos datos b&aacute;sicos.</a></p>
				<div id="buttonFB"><a href="<%=Facebook.getLoginRedirectURL(apikey,redirect + LoginClientFacebookAction.redirect_uri, Facebook.email_perms)%>"><img src="images/null.gif" width="324" height="65" /></a></div>
			</div>
			<div id="moduloPreReg" class="profesional">
				<h2><span class="profesional_title">&#191;Queres ofrecer tus productos o servicios?</span><br/><span class="remarcadoDCF6FC">Registrate gratis</span></h2>
				<p>Podes hacerlo utilizando tu cuenta de <a href="<%=Facebook.getLoginRedirectURL(apikey,redirect + LoginProfesionalFacebookAction.redirect_uri, Facebook.email_perms)%>">facebook</a> o cargando tus datos como profesional.<br/><br/></p>
				<div id="buttonProf"><a href="./goToRegistroProfesional.do"><img src="images/null.gif" width="229" height="65" /></a></div>
			</div>
		</div>
		<!-- aca Termina el formulario -->
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>