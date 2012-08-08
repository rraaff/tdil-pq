<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Inicio | El sitio #1 en contactos entre profesionales y clientes del rubro eventos</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<style>
<!--
div, span, p, a { /*border:dotted 1px #00FF00;*/ }

#central {
	background-image: url(images/skin_basic/backgrounds/home_promoBase.jpg);
	background-repeat: repeat-x;
	width:100%;
	height:367px;
	margin:0px;
	float:left;
}
#central #centralBlock {
	background-image:url(images/skin_basic/backgrounds/borders.png);
	background-repeat:repeat-y;
	width:1000px;
	height:367px;
	margin:0 auto;
}
#central #centralBlock #promos {
	background-image: url(images/skin_basic/home/basePromos.png);
	background-repeat: no-repeat;
	width:643px;
	height:367px;
	margin-left:10px;
	float:left;
}
#central #centralBlock #buttons {
	width:337px;
	height:367px;
	float:left;
}
#central #centralBlock #buttons a {
	padding:10px;
	float:left;
}
/*#content {
	background-image: url(images/skin_basic/backgrounds/body.gif);
	background-repeat: repeat;
	margin: 0px;
	float: left;
	width: 100%;
}*/
-->
</style>
</head>

<body>
<%@ include file="includes/designHeader.jsp" %>
<div id="central">
	<div id="centralBlock">
		<div id="promos"></div>
		<div id="buttons"><a href="">busqueda avanzada</a><a href="addProfesional.jsp">Registrar Profesional</a><a href="">Registrar usuario con facebok</a><a href="promotionList.jsp">Ver todas las promociones</a></div>
	</div>
</div>
<div id="content"></div>
<%@ include file="includes/fbShare.jsp" %>
<%@ include file="includes/footer.jsp" %>
</body>
</html>