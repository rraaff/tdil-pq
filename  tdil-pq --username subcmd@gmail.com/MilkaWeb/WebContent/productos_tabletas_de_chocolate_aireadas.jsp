<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Milka :: Nuestros Chocolates</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href='http://fonts.googleapis.com/css?family=Sue+Ellen+Francisco' rel='stylesheet' type='text/css'>

<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/galeria.js" type="text/javascript"></script>
<script type="text/javascript"> jQuery.noConflict();</script>

<script language="javascript" type="text/javascript" src="js/mootools-1.2-core.js"></script>
<script language="javascript" type="text/javascript" src="js/mootools-1.2-more.js"></script>
<script language="javascript" type="text/javascript" src="js/SlideItMoo.js"></script>
<script language="javascript" type="text/javascript">
window.addEvent('domready', function(){	
	
	/* thumbnails example , div containers */
	new SlideItMoo({itemsVisible:2, // the number of thumbnails that are visible
					currentElement: 0, // the current element. starts from 0. If you want to start the display with a specific thumbnail, change this
					thumbsContainer: 'thumbs2',
					elementScrolled: 'thumb_container2',
					overallContainer: 'gallery_container2'});					
	
});
</script>
<style>
#galeria_productos #galeria_parte_1 {
	background:url(images/productos/tabletas_aireadas_comp1.jpg) no-repeat 0 0;	
	width:488px;
	height:423px;
	margin:5px 0 0 5px;
}
#galeria_productos #galeria_parte_2 {
	background:url(images/productos/tabletas_aireadas_comp2.jpg) no-repeat 0 0;	
	width:488px;
	height:423px;
	margin:5px 0 0 5px;
}
</style>
</head>
<body>
<div id="content">
	<div id="header">
		<div id="logo"><a href="index.jsp" title="Milka"></a></div>
		<div id="box">
			<div id="subi"><a href="#" title="SUB&Aacute; tu foto con chocolate"></a></div>
			<div id="social">
				<ul>
					<li class="faqs"><a href="faqs.jsp" title="FAQS"></a></li>
					<li class="t"><a href="#" title="t"><img src="images/t.png" width="22" height="22" alt="t" border="none" /></a></li>
					<li class="facebook"><a href="#" title="Facebook"><img src="images/facebook.png" width="22" height="22" alt="Facebook" border="none" /></a></li>
					<li class="twitter"><a href="#" title="Twitter"><img src="images/twitter.png" width="22" height="22" alt="Twitter" border="none" /></a></li>
					<li class="youtube"><a href="#" title="Youtube"><img src="images/youtube.png" width="22" height="22" alt="Youtube" border="none" /></a></li>
				</ul>
			</div>
			<!-- end social-->
		</div>
		<!-- end box-->
	</div>
	<!-- end header-->
	<div id="menu-wrapper">
		<div id="menu">
			<ul>
				<li class="activo"><a href="#" title="Productos" class="activo">Productos</a></li>
				<li class=""><a href="historia.jsp" title="Historia" class="">Historia</a></li>
				<li class="fin"><a href="contacto.jsp" title="Contacto" class="">Contacto</a></li>
			</ul>
		</div> 
		<!-- end menu -->
	</div>
	<!-- end menu-wrapper-->
	<div id="wrapper2-internas">
		<div id="titulo">
			<h2>Nuestros <span class="violeta">Chocolates</span></h2>
			<h3>Tabletas de chocolate s&oacute;lidas</h3>
		</div>
		<!-- end titulo-->
		<div id="menu-productos">
			<ul>
				<li><a href="productos_alfajores.jsp" title="Alfajores" class="bt1">Alfajores</a></li>
				<li><a href="productos_tabletas_de_chocolate_rellenas.jsp" title="Tabletas de chocolate Rellenas" class="bt2">Tabletas de chocolate Rellenas</a></li>
				<li><span class="selectedLi">Tabletas de chocolate Aireadas</span></li>
				<li><a href="productos_tabletas_de_chocolate_solidas.jsp" title="Tabletas de chocolate S&oacute;lidas" class="bt2">Tabletas de chocolate S&oacute;lidas</a></li>
				<li><a href="productos_bombones_de_Chocolate.jsp" title="Bombones de Chocolate" class="bt2">Bombones de Chocolate</a></li>
			</ul>
		</div>
		<!-- menu-productos-->
		<div id="galeria_productos">
			<div class="leftArrow panel_galeria_prev" id="leftArrow"><a rel="history" id="leftArrowPrev" class="prev" href="#" title="Anterior"></a></div>
			<div class="rightArrow" id="rightArrow"><a rel="history" id="rightArrowNext" class="next panel_galeria_next" href="#" title="Siguiente"></a></div>
			<div id="galeria_parte_1" class="panel_galeria"></div>
			<!-- end parte_1-->
			<div id="galeria_parte_2" class="panel_galeria"></div>
			<!-- end parte_2-->
		</div>
		<!-- end galeria_productos-->
		<div id="bloque_texto">
			<h3>La suavidad en su<br />estado m&aacute;s divertido</h3>
			<p>El delicioso sabor del chocolate Milka combinando en tu boca con burbujas aireadas, logrando que lo rico tambi&eacute;n pueda ser liviano.</p>
		<div id="gallery_container2">
			<div id="thumb_container2">
				<div id="thumbs2">
					<div class="thumbnail"><a href="#" rel="lightbox[galerie]"><img src="images/productos/thmb/thmb_Milka_Chocolate_Blanco_100_gr.jpg"		width="56" height="119" /></a>Milka Chocolate Blanco 100 gr.</div>
					<div class="thumbnail"><a href="#" rel="lightbox[galerie]"><img src="images/productos/thmb/thmb_Milka_Chocolate_con_Leche_100_gr.jpg"	width="56" height="119" /></a>Milka Chocolate con Leche 100 gr.</div>
					<div class="thumbnail"><a href="#" rel="lightbox[galerie]"><img src="images/productos/thmb/thmb_Milka_Combinado_100_gr.jpg"				width="56" height="119" /></a>Milka Combinado 100 gr.</div>
					<div class="thumbnail"><a href="#" rel="lightbox[galerie]"><img src="images/productos/thmb/thmb_Milka_con_Almendras_100_gr.jpg"			width="56" height="119" /></a>Milka con Almendras 100 gr.</div>
					<div class="thumbnail"><a href="#" rel="lightbox[galerie]"><img src="images/productos/thmb/thmb_Milka_Combinado_45_gr.jpg"				width="56" height="119" /></a>Milka Combinado 45 gr.</div>
					<div class="thumbnail"><a href="#" rel="lightbox[galerie]"><img src="images/productos/thmb/thmb_Milka_con_Almendras_45_gr.jpg"			width="56" height="119" /></a>Milka con Almendras 45 gr.</div>
					<div class="thumbnail"><a href="#" rel="lightbox[galerie]"><img src="images/productos/thmb/thmb_Milka_Chocolate_Blanco_45_gr.jpg"		width="56" height="119" /></a>Milka Chocolate Blanco 45 gr.</div>
					<div class="thumbnail"><a href="#" rel="lightbox[galerie]"><img src="images/productos/thmb/thmb_Milka_Chocolate_con_Leche_45_gr.jpg"	width="56" height="119" /></a>Milka Chocolate con Leche 45 gr.</div>
					<div class="thumbnail"><a href="#" rel="lightbox[galerie]"><img src="images/productos/thmb/thmb_Milka_Chocolate_con_Leche_25_gr.jpg"	width="56" height="119" /></a>Milka Chocolate con Leche 25 gr.</div>
					<div class="thumbnail"><a href="#" rel="lightbox[galerie]"><img src="images/productos/thmb/thmb_Milka_Combinado_25_gr.jpg"				width="56" height="119" /></a>Milka Combinado 25 gr.</div>
					<div class="thumbnail"><a href="#" rel="lightbox[galerie]"><img src="images/productos/thmb/thmb_Milka_con_Almendras_25_gr.jpg"			width="56" height="119" /></a>Milka con Almendras 25 gr.</div>
				</div>
			</div>
		</div>
		<!-- banner rotator start -->
	</div>
	<!-- end bloque-texto-->
</div>
<!-- end wrapper2-->
<div id="footer">
	<div id="left"><p>&copy; 2012 Kraft Foods &reg; Todos los derechos reservados</p></div>
	<div id="right"><p><a href="#" title="Politicas de Privacidad">Politicas de Privacidad</a> - <a href="#" title="FAQS">FAQS</a></p></div>
</div>
<!-- end footer-->
</div>
<!-- end content-->
<div id="montania"></div>	
</body>
</html>