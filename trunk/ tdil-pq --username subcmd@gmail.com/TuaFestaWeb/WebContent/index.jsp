<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.utils.LocalizationUtils"%>
<%@page import="com.tdil.tuafesta.model.valueobjects.AdvertisementValueObject"%>
<%@page import="com.tdil.tuafesta.utils.AdUtils"%>
<%@page import="com.tdil.tuafesta.utils.AdsForHome"%>
<%@page import="com.tdil.tuafesta.model.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.utils.DateUtils"%>
<%@page import="com.tdil.tuafesta.utils.PromotionUtils"%>
<%@page import="com.tdil.tuafesta.model.valueobjects.PromotionValueObject"%>
<%@page import="com.tdil.tuafesta.utils.CategoryUtils"%>
<%@page import="com.tdil.tuafesta.utils.GeoLevelUtils"%>
<%@page import="com.tdil.tuafesta.model.Geo4"%>
<%@ include file="includes/userLogged.jspf" %>
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
<script src="js/jquery.nivo.slider.pack.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/nivo-slider.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/nivo_theme/default.css" type="text/css" media="screen" />
<link href="css/carrousel.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.infinite-carousel.js"></script>
<style>
<!--
#slider h1 a, #slider h1 a:active, #slider h1 a:visited, #slider h1 {
	border:none;
	text-decoration:none;
	padding-bottom:6px;
}
#slider h1 a:hover {
	text-decoration:underline;
}
#slider .bajada a {
	color:#ffb631;
	border:none;
	text-decoration:none;
}
.theme-default .nivo-controlNav {
	left: 50%;
	bottom: 65px;
	margin-left: -20px;
}
.nivo-directionNav a {
	top: 35%;
}
-->
</style>
</head>

<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="central">
	<div id="centralBlock">
		<div id="promos">
			<div class="slider-wrapper theme-default" style="width:628px; height:400px; margin-left:10px; margin-top:17px; float:left;">
			    <div class="ribbon"></div>
			    <div id="slider" class="nivoSlider" style="">
				<% /*Generacion de imagenes*/
				List<PromotionValueObject> promos = PromotionUtils.getActivePromotions(); 
				for (PromotionValueObject pvo : promos) { %>
			        <img src="./downloadThumb.st?width=628&height=307&id=<%=pvo.getFirstImageid()%>&type=PUBLIC&ext=<%=pvo.getFirstImageExt()%>" alt="" title="#htmlcaption<%=pvo.getId() %>" width="628" height="307" />
				<% } %>
			    </div>
				<% /*Generacion de captions*/
				for (PromotionValueObject pvo : promos) { %>
				    <div id="htmlcaption<%=pvo.getId() %>" class="nivo-html-caption">
			    	<h1><a href="./detallePromocion.jsp?id=<%=pvo.getId() %>"><%=pvo.getName() %></a></h1>
					<div class="bajada"><a href="./detallePromocion.jsp?id=<%=pvo.getId() %>"><%=pvo.getDescription() %></a></div>
					<div class="date">$<%=pvo.getPrice() %> - Desde <%=DateUtils.formatDateSp(pvo.getStartdate()) %> hasta <%=DateUtils.formatDateSp(pvo.getEnddate()) %></div>
			 	</div>
			 	<% } %>
			</div>
		</div>
		<div id="buttons">
			<ul>
				<li class="advancedSearch"><a href="./goToSearchWizard.do" title="Arm&aacute; una b&uacute;squeda completa con todo lo que necesitas para tu evento."></a></li>
				<li class="addProfessional"><a href="./goToRegistroProfesional.do" title="Registrate ahora y comenz&aacute; a ofrecer tus productos / servicios."></a></li>
				<li class="addClientFB"><a href="registroPreRegistro.jsp" title="Registrate como usuario para contactar profesionales. Podes usar tu cuenta de Facebook."></a></li>
			</ul>
		</div>
	</div>
</div>
<% 	AdsForHome adsForHome = AdUtils.getAdsHome(); %>
<div id="preContainer">
	<div id="content">
		<% if (!adsForHome.getExtraByCategory().isEmpty()) { %>
		<div id="superHighlightedBlock" style="margin-left:20px;">
			<div id="sliderBloc">
				<a id="previous">Previous</a>
				<div style="" id="slider-stage">
					<div id="slider-list">
						<% for (AdvertisementValueObject adByCat : adsForHome.getExtraByCategory()) { %>
						<% if (adByCat.isProfesionalAd()) { %>
							<a class="theme" href="./viewProfesionalProfile.do?id=<%=adByCat.getIdProfesional()%>">
						<% } else { %>
							<a class="theme" href="./viewSellDetails.do?type=0&id=<%=adByCat.getIdSell() %>">
						<% } %>
							<% if (adByCat.hasImage()) { %>
								<div style="width:186px; height:106px; float:left; border:solid 1px #CCCCCC; overflow:hidden; 
								background-image:url(./downloadThumb.st?width=188&height=108&id=<%=adByCat.getIdBlobData()%>&type=PUBLIC&ext=<%=adByCat.getExtBlobData()%>); background-position:center center; background-repeat:no-repeat;"><img src="images/null.gif" width="188" height="108" /></div>
							<% } else { %>
								<img src="images/skin_basic/home/superdestacados_previewNoDisponible.gif" width="188" height="108" />
							<% } %>
							<span class="nameVignette"><%=adByCat.getProfesionalBusinessName() %></span><br/>
							<span class="changeTheme"><%=adByCat.getCategoryName()%></span>
							<% if (!adByCat.isProfesionalAd()) { %>
								<span class="priceTheme">Precio: <%=LocalizationUtils.formatPrice(adByCat.getSellPrice())%></span>
							<% } %>
						</a>
						<% } %>
					</div>
				</div>
				<a id="next">Next</a>
			</div>
		</div>
		<% } %>
		<% if (!adsForHome.getExtraWithoutFilter().isEmpty()) { %>
		<div id="superHighlightedBlock">
			<div id="sliderBloc">
				<a id="previous1">Previous</a>
				<div style="" id="slider-stage1">
					<div id="slider-list">
						<% for (AdvertisementValueObject adByCat : adsForHome.getExtraWithoutFilter()) { %>
							<% if (adByCat.isProfesionalAd()) { %>
								<a class="theme" href="./viewProfesionalProfile.do?id=<%=adByCat.getIdProfesional()%>">
							<% } else { %>
								<a class="theme" href="./viewSellDetails.do?type=0&id=<%=adByCat.getIdSell() %>">
							<% } %>
							<% if (adByCat.hasImage()) { %>
								<div style="width:186px; height:106px; float:left; border:solid 1px #CCCCCC; overflow:hidden; 
								background-image:url(./downloadThumb.st?width=186&height=106&id=<%=adByCat.getIdBlobData()%>&type=PUBLIC&ext=<%=adByCat.getExtBlobData()%>); background-position:center center; background-repeat:no-repeat;"><img src="images/null.gif" width="188" height="108" /></div>
							<% } else { %>
								<img src="images/skin_basic/home/superdestacados_previewNoDisponible.gif" width="188" height="108" />
							<% } %>
							<span class="nameVignette"><%=adByCat.getProfesionalBusinessName() %></span><br/>
							<span class="changeTheme"><%=adByCat.getCategoryName() == null ? "" : adByCat.getCategoryName()%></span>
							<% if (!adByCat.isProfesionalAd()) { %>
								<span class="priceTheme">Precio: <%=LocalizationUtils.formatPrice(adByCat.getSellPrice())%></span>
							<% } %>
						</a>
						<% } %>
					</div>
				</div>
				<a id="next1">Next</a>
			</div>
		</div>
		<% } %>
		<div id="punteado"></div>
		<div id="leftCentral">
			<% for (AdvertisementValueObject advertisementValueObject : adsForHome.getNormal()) { %>
				<div id="lowHighlightedAdv">
					<% if (advertisementValueObject.isProfesionalAd()) { %>
						<h5><a href="./viewProfesionalProfile.do?id=<%=advertisementValueObject.getIdProfesional()%>"><%=advertisementValueObject.getProfesionalBusinessName() %></a></h5>
					<% } else {  %>
						<h5><a href="./viewSellDetails.do?type=0&id=<%=advertisementValueObject.getIdSell() %>"><%=advertisementValueObject.getSellName() %></a></h5>
					<% } %>
					<div id="imageAtAdv" style="background-repeat:no-repeat; background-position: center center; 
						<% if (advertisementValueObject.hasImage()) { %>
							background-image:url(./downloadThumb.st?width=86&height=86&id=<%=advertisementValueObject.getIdBlobData()%>&type=PUBLIC&ext=<%=advertisementValueObject.getExtBlobData()%>);"
						<% } else { %>
							background-image:url(boImages/na.gif);
						<% } %>
					">
						<% if (advertisementValueObject.isProfesionalAd()) { %>
							<a href="./viewProfesionalProfile.do?id=<%=advertisementValueObject.getIdProfesional()%>">
						<% } else {  %>
							<a href="./viewSellDetails.do?type=0&id=<%=advertisementValueObject.getIdSell() %>">
						<% } %>
						<img src="images/skin_basic/masks/lha_mask.png" width="86" height="86" /></a>
					</div>
					<div class="detalle">
						<% if (advertisementValueObject.isProfesionalAd()) { %>
							<a href="./viewProfesionalProfile.do?id=<%=advertisementValueObject.getIdProfesional()%>"><%=advertisementValueObject.getProfesionalDescriptionUpTo(120) %><br/><br/>Ir al perfil</a>
						<% } else { %>
							<span class="category">Rubro: <a href="./viewSellDetails.do?type=0&id=<%=advertisementValueObject.getIdSell() %>"><%=advertisementValueObject.getCategoryName() %></a></span>
							<p style="padding-top:8px;"><%=advertisementValueObject.getSellDescription()%></p>
						<% } %>
						<% if (!advertisementValueObject.isProfesionalAd()) { %>
							<span class="price">Precio: <%=LocalizationUtils.formatPrice(advertisementValueObject.getSellPrice())%></span>
						<% } %>
					</div>
				</div>
			<% } %>
		</div>
		<div id="rightCentral">
			<!-- la aparici&oacute;n o el &oacute;rden de esto se puede administrar desde BO -->
			<div id="categoryNavigation">
				<h3><a href="listadoRubros.jsp">Todos los rubros</a></h3>
				<p>
					<% for (Category cat : CategoryUtils.getAllCategoriesForIndex()) { %>
						<a href="searchSellsByCategory.do?id=<%=cat.getId() %>&type=<%=cat.getType() %>"><%=cat.getName() %></a><br />
					<% } %>
				</p>
			</div>
			<div id="punteado" style="width:284px;"></div>
			<div id="zoneNavigation">
				<h3><a href="listadoZonas.jsp">Todos las zonas</a></h3>
				<p>
					<% for (Geo4 geo4 : GeoLevelUtils.getActiveGeo4LevelsForHome()) { %>
						<a href="searchSellsByGeoLevel4.do?id=<%=geo4.getId() %>"><%=geo4.getNombre() %></a><br />
					<% } %>
				</p>
			</div>
		</div>
	</div>
</div>
<%@ include file="includes/footer.jsp" %>
<script type="text/javascript">
  $(document).ready(function(){
   $('#slider').nivoSlider({
		effect: 'fold',  // Specify sets like: 'fold,fade,sliceDown');
		pauseTime: 10000
	});

   <% if (!adsForHome.getExtraByCategory().isEmpty()) { %>
   jQuery('#slider-stage').carousel('#previous', '#next');
   <% } %>
   <% if (!adsForHome.getExtraWithoutFilter().isEmpty()) { %>
   jQuery('#slider-stage1').carousel('#previous1', '#next1');
   <% } %>

  });
   </script>
</body>
</html>