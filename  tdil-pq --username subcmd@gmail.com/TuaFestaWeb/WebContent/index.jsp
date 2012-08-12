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
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<style>
<!--
div, span, p, a {/* border:dotted 1px #00FF00; */}
.advancedSearch, .addProfessional, .addClientFB, .viewPromotions {
	margin:0;
	padding:0;
}
.advancedSearch a {
	background-image: url(images/skin_basic/buttons/advancedSearch_off.png);
	background-repeat: no-repeat;
	background-position: center center;
	width:328px;
	height:109px;
	margin:0;
	padding:0;
	float:left;
}
.advancedSearch a:hover {
	background-image: url(images/skin_basic/buttons/advancedSearch_on.png);
	background-repeat: no-repeat;
	background-position: center center;
}
.addProfessional a {
	background-image: url(images/skin_basic/buttons/registerProfessional_off.png);
	background-repeat: no-repeat;
	background-position: center center;
	width:328px;
	height:129px;
	margin:0;
	padding:0;
	float:left;
}
.addProfessional a:hover {
	background-image: url(images/skin_basic/buttons/registerProfessional_on.png);
	background-repeat: no-repeat;
	background-position: center center;
}
.addClientFB a {
	background-image: url(images/skin_basic/buttons/registerClientFB_off.png);
	background-repeat: no-repeat;
	background-position: center center;
	width:328px;
	height:65px;
	margin:0;
	padding:0;
	float:left;
}
.addClientFB a:hover {
	background-image: url(images/skin_basic/buttons/registerClientFB_on.png);
	background-repeat: no-repeat;
	background-position: center center;
}
.viewPromotions a {
	background-image: url(images/skin_basic/buttons/promotionList_off.png);
	background-repeat: no-repeat;
	background-position: center center;
	width:328px;
	height:53px;
	margin:0;
	padding:0;
	float:left;
}
.viewPromotions a:hover {
	background-image: url(images/skin_basic/buttons/promotionList_on.png);
	background-repeat: no-repeat;
	background-position: center center;
}
-->
</style>
</head>

<body>
<%@ include file="includes/designHeader.jsp" %>
<div id="central">
	<div id="centralBlock">
		<div id="promos"></div>
		<div id="buttons">
			<div class="advancedSearch"><a href=""></a></div>
			<div class="addProfessional"><a href="registroProfesional.jsp"></a></div>
			<div class="addClientFB"><a href=""></a></div>
			<div class="viewPromotions"><a href="promotionList.jsp"></a></div>
		</div>
	</div>
</div>
<div id="content">
	<div id="superHighlighted">
		<div id="superHighlightedBlock" style="margin-left:10px;">
			<div id="navigationButtons">
				<div id="left"><a href=""><img src="images/skin_basic/buttons/superHighlited_left.gif" width="22" height="42" /></a></div>
				<div id="right"><a href=""><img src="images/skin_basic/buttons/superHighlited_right.gif" width="22" height="42" /></a></div>
			</div>
			<div id="adv">
				<a href="#"><img src="images/skin_basic/demo/superHighlightedPic.gif" /></a>
				<span class="rubro"><a href="#">Rubro: Salones/Quintas</a></span>
				<h4><a href="#">Quinta LOS DIEZ</a></h4>
				<span class="precio"><a href="#">Precio desde: $ 6.000</a></span>
			</div>
			<div id="adv">
				<img src="images/skin_basic/demo/superHighlightedPic.gif" />
				<span class="rubro"><a href="#">Rubro: Salones/Quintas</a></span>
				<h4><a href="#">Finca Madero</a></h4>
				<span class="precio"><a href="#">Precio desde: $ 9.000</a></span>
			</div>
		</div>
		<div id="superHighlightedBlock" style="margin-right:10px;">
			<div id="navigationButtons">
				<div id="left"><a href=""><img src="images/skin_basic/buttons/superHighlited_left.gif" width="22" height="42" /></a></div>
				<div id="right"><a href=""><img src="images/skin_basic/buttons/superHighlited_right.gif" width="22" height="42" /></a></div>
			</div>
			<div id="adv">
				<a href="#"><img src="images/skin_basic/demo/superHighlightedPic.gif" /></a>
				<span class="rubro"><a href="#">Profesional: Fotografos</a></span>
				<h4><a href="#">Horacio Carrano Foto</a></h4>
				<span class="precio"><a href="#">Precio desde: $ 6.000</a></span>
			</div>
			<div id="adv">
				<a href="#"><img src="images/skin_basic/demo/superHighlightedPic.gif" /></a>
				<span class="rubro"><a href="#">Profesional: Peinador</a></span>
				<h4><a href="#">Juan Carlos Coifeur</a></h4>
				<span class="precio"><a href="#">Precio desde: $ 6.000</a></span>
			</div>
		</div>
		<div id="punteado"></div>
	</div>
	<div id="superHighlighted" style="height:700px;">
		<div id="leftCentral">
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>
			</div>
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>
			</div>
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>
			</div>
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>
			</div>
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>
			</div>
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>
			</div>
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>
			</div>
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>
			</div>
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>
			</div>
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>
			</div>
		</div>
		<div id="rightCentral">
			<!-- la aparici&oacute;n o el &oacute;rden de esto se puede administrar desde BO -->
			<div id="zoneNavigation">
				<h3>Todos las zonas</h3>
				<p><a href="#">Abasto</a><br /><a href="#">Agronom&iacute;a</a><br /><a href="#">Almagro</a><br /><a href="#">Balvanera</a><br /><a href="#">Barracas</a><br /><a href="#">Barrio Norte</a><br /><a href="#">Belgrano</a><br /><a href="#">Boedo</a><br /><a href="#">Caballito</a><br /><a href="#">Chacarita</a><br /><a href="#">Coghlan</a><br /><a href="#">Colegiales</a><br /><a href="#">Constitucion</a><br /><a href="#">Flores</a><br /><a href="#">Floresta</a><br /><a href="#">La Boca</a><br /><a href="#">Liniers</a><br /><a href="#">Mataderos</a><br /><a href="#">Micro Centro</a><br /><a href="#">Monserrat</a><br /><a href="#">Monte Castro</a><br /><a href="#">Nu&ntilde;ez</a><br /><a href="#">Nueva Pompeya</a><br /><a href="#">Palermo</a><br /><a href="#">Palermo Viejo</a><br /><a href="#">Parque Avellaneda</a><br /><a href="#">Parque Chacabuco</a><br /><a href="#">Parque Patricios</a><br /><a href="#">Paternal</a><br /><a href="#">Puerto Madero</a><br /><a href="#">Recoleta</a><br /><a href="#">Retiro</a><br /><a href="#">Saavedra</a><br /><a href="#">San Cristobal</a><br /><a href="#">San Nicolas</a><br /><a href="#">San Telmo</a><br /><a href="#">Velez Sarsfield</a><br /><a href="#">Versalles</a><br /><a href="#">Villa Crespo</a><br /><a href="#">Villa Devoto</a><br /><a href="#">Villa General Mitre</a><br /><a href="#">Villa Lugano</a><br /><a href="#">Villa Luro</a><br /><a href="#">Villa Ortuzar</a><br /><a href="#">Villa Pueyrredon</a><br /><a href="#">Villa Real</a><br /><a href="#">Villa Riachuelo</a><br /><a href="#">Villa Santa Rita</a><br /><a href="#">Villa Soldati</a><br /><a href="#">Villa Urquiza</a><br /><a href="#">Villa del Parque</a></p>
			</div>
			<div id="punteado" style="width:284px;"></div>
			<div id="categoryNavigation">
				<h3>Todos las rubros</h3>
				<p><a href="#">Alimentos y bebidas</a><br /><a href="#">Alojamiento y hoteles</a><br /><a href="#">Animales</a><br /><a href="#">Audio, fotograf&iacute;a y video</a><br /><a href="#">Automotores</a><br /><a href="#">Belleza y cuidado personal</a><br /><a href="#">Calzados</a><br /><a href="#">Computaci&oacute;n e inform&aacute;tica</a><br /><a href="#">Construcci&oacute;n y mantenimiento</a><br /><a href="#">Deportes</a><br /><a href="#">Ense&ntilde;anza, cursos y capacitaciones</a><br /><a href="#">Entidades financieras</a><br /><a href="#">Entretenimiento</a><br /><a href="#">Estaciones de servicio</a><br /><a href="#">Gastronom&iacute;a</a><br /><a href="#">Hogar</a><br /><a href="#">Indumentaria</a><br /><a href="#">Industria</a><br /><a href="#">Inmobiliaria</a><br /><a href="#">Jard&iacute;n</a><br /><a href="#">Joyer&iacute;a y relojer&iacute;a</a><br /><a href="#">Juegos de azar</a><br /><a href="#">Juguetes</a><br /><a href="#">Kioscos y polirubros</a><br /><a href="#">Librer&iacute;a y papeler&iacute;a</a><br /><a href="#">Libros y revistas</a><br /><a href="#">M&aacute;quinas y herramientas</a><br /><a href="#">Materiales de construcci&oacute;n</a><br /><a href="#">Medicina y medicamentos</a><br /><a href="#">Medios de comunicaci&oacute;n y publicidad</a><br /><a href="#">M&uacute;sica</a><br /><a href="#">&oacute;ptica</a><br /><a href="#">Otros</a><br /><a href="#">Reparaci&oacute;n</a><br /><a href="#">Rodados</a><br /><a href="#">Seguros y seguridad</a><br /><a href="#">Servicio para eventos</a><br /><a href="#">Servicios en general</a><br /><a href="#">Servicios para Empresas</a><br /><a href="#">Servicios profesionales</a><br /><a href="#">Talleres</a><br /><a href="#">Telas</a><br /><a href="#">Telefon&iacute;a e internet</a><br /><a href="#">Transportes</a><br /><a href="#">Turismo</a></p>
			</div>
		</div>
	</div>
</div>
<%@ include file="includes/fbShare.jsp" %>
<%@ include file="includes/footer.jsp" %>
</body>
</html>