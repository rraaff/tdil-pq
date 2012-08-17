<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.utils.CategoryUtils"%>
<%@page import="com.tdil.tuafesta.model.valueobjects.CategoryValueObject"%>
<%@page import="com.tdil.tuafesta.utils.GeoLevelUtils"%>
<%@page import="com.tdil.tuafesta.model.Geo4"%>
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
			<div class="addProfessional"><a href="registroPreRegistro.jsp"></a></div>
			<div class="addClientFB"><a href=""></a></div>
			<div class="viewPromotions"><a href="promotionList.jsp"></a></div>
		</div>
	</div>
</div>
<div id="preContainer">
	<div id="content">
		<div id="superHighlightedBlock" style="margin-left:10px;">
			<div id="navigationButtons">
				<div id="left"><a href=""><img src="images/skin_basic/buttons/superHighlited_left.gif" width="22" height="42" /></a></div>
				<div id="right"><a href=""><img src="images/skin_basic/buttons/superHighlited_right.gif" width="22" height="42" /></a></div>
			</div>
			<div id="adv">
				<a href="#"><img src="images/skin_basic/demo/superHighlightedPic.gif" /></a>
				<span class="rubro"><a href="#">Rubro: Salones/Quintas</a></span>
				<h4><a href="#">Quinta LOS DIEZ</a></h4>
				<span class="precio"><a href="#">Precio desde: $ 6.000</a></span>		</div>
			<div id="adv">
				<img src="images/skin_basic/demo/superHighlightedPic.gif" />
				<span class="rubro"><a href="#">Rubro: Salones/Quintas</a></span>
				<h4><a href="#">Finca Madero</a></h4>
				<span class="precio"><a href="#">Precio desde: $ 9.000</a></span>		</div>
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
				<span class="precio"><a href="#">Precio desde: $ 6.000</a></span>		</div>
			<div id="adv">
				<a href="#"><img src="images/skin_basic/demo/superHighlightedPic.gif" /></a>
				<span class="rubro"><a href="#">Profesional: Peinador</a></span>
				<h4><a href="#">Juan Carlos Coifeur</a></h4>
				<span class="precio"><a href="#">Precio desde: $ 6.000</a></span>		</div>
		</div>
		<div id="punteado"></div>
		<div id="leftCentral">
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>		</div>
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>		</div>
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>		</div>
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>		</div>
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>		</div>
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>		</div>
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>		</div>
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>		</div>
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>		</div>
			<div id="lowHighlightedAdv">
				<h5><a href="#">Nombre del Profesional</a></h5>
				<div id="imageAtAdv" style=""><a href="#"><img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a></div>
				<p><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum mauris, imperdiet vitae viverra sed, fringilla at nisi. Nullam ac massa neque, sed mattis odio. Vivamus consequat urna non lorem convallis dapibus. Sed blandit, felis ac sagittis viverra, velit odio.</a></p>
				<span class="category"><a href="#">Rubro: Fotograf&iacute;a</a></span>
				<span class="price"><a href="#">Precio promedio: $ 6.000</a></span>		</div>
		</div>
		<div id="rightCentral">
			<!-- la aparici&oacute;n o el &oacute;rden de esto se puede administrar desde BO -->
			<div id="zoneNavigation">
				<h3>Todos las zonas</h3>
				<p>
					<% for (Geo4 geo4 : GeoLevelUtils.getActiveGeo4Levels()) { %>
						<a href="searchProfesionalByGeoLevel4.do?id=<%=geo4.getId() %>"><%=geo4.getNombre() %></a><br />
					<% } %>
				</p>
			</div>
			<div id="punteado" style="width:284px;"></div>
			<div id="categoryNavigation">
				<h3>Todos las rubros</h3>
				<p>
					<% for (CategoryValueObject cat : CategoryUtils.getTopCategories()) { %>
						<a href="#"><%=cat.getName() %></a><br />
					<% } %>
				</p>
			</div>
		</div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>