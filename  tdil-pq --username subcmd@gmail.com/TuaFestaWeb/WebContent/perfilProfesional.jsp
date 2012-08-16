<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | R009-M1- Mi cuenta - Perfil de usuario profesional</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<style>
<!--
.myRow {
	padding-bottom:5px;
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
<%@ include file="includes/designHeader.jsp" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Perfil de #La Empresa</h1>
			<h2>Estos son tus datos, productos y servicios publicados. Tambi&eacute;n podr&aacute;s acceder a tu muro.</h2>
		</div>
		<div id="formContent">
			<div id="muroContainer">MURO INSERT</div>
			<div id="formSection">
				<div class="myRow">
					<div class="myLabel width400">#Nombre completo del usuario</div>
				</div>
				<div class="myRow">
					<div class="myLabel width400">Tel&eacute;fono #Personal: 011 15 5876 3153</div>
				</div>
				<div class="myRow">
					<div class="myLabel width200">Sexo: Masculino</div>
					<div class="myLabel width200">Fecha Nac.:22/11/1979</div>
				</div>
				<div class="myRow">
					<div class="myLabel width400">E-Mail: pablo@tdil.com.ar</div>
				</div>
			</div>
			<div id="formSection" class="width650">
				<div class="myRow">
					<div class="myLabel width400">Nombre profesional/empresa: That Day in London</div>
				</div>
				<div class="myRow">
					<div class="myLabel width200">CUIT: 27768647</div>
					<div class="myLabel width200">IIBB: 11111111</div>
				</div>
				<div class="myRow">
					<div class="myLabel width400">Ubicaci&oacute;n: Capital Federal > Villa Devoto</div>
				</div>
				<div class="myRow">
					<div class="myLabel width200">Web: www.thatdayinlondon.com</div>
					<div class="myLabel width200">Facebook: 15555555555</div>
				</div>
				<div class="myRow height50">
					<div class="myLabel width200">Horario de Atenci&oacute;n: De lunes a viernes de 9 a 18hs Sabados de 9 a 12hs</div>
					<div class="myLabel width200">Descripci&oacute;n: Local a la calle</div>
				</div>
			</div>
			
			<div id="formSection" class="width650">
				<h2 style="float:left; padding-left:0; padding-bottom:0; margin-bottom:10px;">Productos y Servicios</h2>
				<div class="myRow">
					<div class="myLabel width600 comment">Recuerde que a cada producto o servicio le puede adjuntar una imagen, precios de referencia, &aacute;reas de covertura y otros detalles una vez finalizado el proceso de registraci&oacute;n.</div>
				</div>
				<div class="myRow">ACA VA EL LISTADO</div>
			</div>
		</div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>