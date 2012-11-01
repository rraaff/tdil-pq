<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="profesionalEmailValidated"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Registro - Registro Profesional Normal (paso 2)</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<div id="titleArea">
			<h1>Registro de profesionales</h1>
			<h2>&#161;Felicitaciones! Hemos validado su direcci&oacute;n de E-Mail.</h2>
		</div>
		<div id="formContent" class="height300">
			<div style="padding-top:50px; padding-bottom:50px;">
				<p>Su registro ha sido completado. Cada producto o servicio que haya agregado, as&iacute; como las fotos, videos o dem&aacute;s contenidos de su perfil, requieren aprobaci&oacute;n de un administrador de TUA FESTA.</p>
				<p><a href="./login.jsp">Ir a Mi Cuenta</a></p>
			</div>
		</div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>