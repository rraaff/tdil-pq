<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.milka.utils.SystemPropertiesKeys"%>
<%@page import="com.tdil.milka.web.SystemPropertyUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Milka.com.ar | Historia Milka</title>
<meta name="keywords" content="Milka, chocolate, vaca, vaca lila, vaca violeta, chocolate aireado, chocolate relleno, relleno de dulce de leche, bombones, corazón, alfajor, tableta, almendras, castañas con caramelo, caramel, oreo, leger, baño maría, fondue, ternura, soft cappuccino, soft combinado, soft chocolate, bajón, regalo, amor, enamorados, avellanas, Cadbury, sabor, Shot, Tofi, Bon o bon, Cofler, golosinas, kiosco, postre, aniversario, cumpleaños, Kraft Foods, rico, sabor, cacao">
<meta name="description" content="Bienvenidos al sitio oficial de Milka Argentina: los invitamos a deleitarse con el más rico chocolate con leche o con el Milka Dulce de Leche o con el Milka Almendras o con el Milka Oreo Blanco o Milka Castañas con Caramelo o Milka Oreo Leche o Milka Te Quiero Mucho! o Milka Feliz Cumple Atrasado! o Milka Suerte! o Milka Vos Sabés Por qué! o Milka Feliz Día! o Milka Gracias! o Milka Dulce de Leche Blanco o Milka Bombón o Milka Sahne Creme o Milka Caramel o Milka Soft Avellanas o Milka Soft Cappucciono o Milka Soft o Milka Soft Combinado o Milka Leger o Leger con Almedras o con el Leger Combinado. Los invitamos a probar el sabor de la ternura." />
<script type="text/javascript">
	var _gaq = _gaq || [];
	_gaq.push(['_setAccount', 'UA-32381287-1']);
	_gaq.push(['_trackPageview']);
	(function() {
		var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	})();
</script>
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href='http://fonts.googleapis.com/css?family=Sue+Ellen+Francisco' rel='stylesheet' type='text/css'>
<style>
.spacerHeigh {
	height:70px;
	float:left;
}
</style>
</head>
<body>
<div id="content">
	<%@ include file="includes/designHeader.jsp" %>
	<div id="menu-wrapper">
		<div id="menu">
			<ul>
				<li><a href="productos_alfajores.jsp" title="Productos" class="">Productos</a></li>
				<li><a href="#" title="Historia" class="activo">Historia</a></li>
				<li class="fin"><a href="contacto.jsp" title="Contacto" class="">Contacto</a></li>
			</ul>
		</div>
		<!-- end menu -->
	</div>
	<!-- end menu-wrapper-->
	<div id="wrapper2-internas">
		<div id="col_derecha">
			<h2><span class="violeta">Milka </span>en el tiempo</h2>
			<span class="spacerHeigh"></span>
			<%@ include file="includes/videoHorizontal.jsp" %>
		</div>
		<!-- end col_derecha-->
		<div id="titulo-historia">
			<h2>Historia <span class="violeta">Milka</span></h2>	
		</div>
		<!-- end titulo-->
		<div id="destacado">La historia de Milka comienza en Suiza a mediados del siglo XIX.  Es ah&iacute; donde Philippe Suchard decide convertir al chocolate en un placer para todos y no para unos pocos.</div>
		<!-- end destacado-->
		<div id="contenidos"><p>Es tal su &eacute;xito que a finales de siglo, su yerno comienza a expandir el negocio r&aacute;pidamente, introduciendo nuevas t&eacute;cnicas de fabricaci&oacute;n, incluida la f&oacute;rmula para mezclar leche y cacao.</p><p>La idea, que luego se convertir&iacute;a en un slogan exitoso, era sencilla: <span class="violeta">Convertir a Milka en la m&aacute;s tierna tentaci&oacute;n, por estar hecha con leche alpina.</span>As&iacute; es como en 1901 Suchard lanza la primera tableta de chocolate con leche, llamada Suchard Milka, con un envoltorio revolucionario de color lila adornado con una vaca de color negro y blanco sobre un fondo de un paisaje alpino.</p>
		<h2>¿Y por qu&eacute; una vaca de color lila?</h2><p>Porque Philippe Suchard cre&iacute;a firmemente que para llamar la atenci&oacute;n era necesario <strong>"familiarizarse con aquello que es extra&ntilde;o y convertir en extra&ntilde;o a aquello que es familiar"</strong>. Y pocas cosas llaman m&aacute;s la atenci&oacute;n que una Vaca color Lila.</p><p>Desde entonces, la vaca ha sido la protagonista de todas las publicidades de Milka jugado el papel principal en m&aacute;s de 110 anuncios televisivos al punto tal que hoy en d&iacute;a representa la identidad visual de todos los productos de la marca Milka pero adem&aacute;s se ha convertido de una de las m&aacute;s grandes estrellas de la publicidad y est&aacute; solidamente anclada en la sociedad actual.</p></div>
	</div>
	<!-- end wrapper2-->
	<%@ include file="includes/fbShare.jsp" %>
	<%@ include file="includes/footer.jsp" %>