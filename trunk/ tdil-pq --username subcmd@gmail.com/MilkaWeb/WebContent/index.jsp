<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.tdil.milka.model.ClickCounter"%>
<%@page import="com.tdil.milka.web.MeltButton"%>
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ include file="includes/head.jsp" %>
<script type='text/javascript' src='../js/jquery.cookie.js'></script>
<script type='text/javascript' src='../js/jquery.melt-button.js'></script>
<script>
$(document).ready(
	function(){
		$("div[id^='mb-']").each(function(indice,valor) {
		   $(valor).meltbutton();
		});
	}
	
);
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Milka</title>
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/es_LA/all.js#xfbml=1&appId=159591494155451";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

<script charset="utf-8" src="http://widgets.twimg.com/j/2/widget.js"></script>
</head>
<body>
<div id="content">
	<div id="header">
		<div id="logo"><a href="index.jsp" title="Milka"></a></div>
		<div id="box">
			<div id="subi"><a href="#" title="SUB&Iacute; tu foto con chocolate"></a></div>
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
				<li class=""><a href="productos_alfajores.jsp" title="Productos" class="">Productos</a></li>
				<li class=""><a href="historia.jsp" title="Historia" class="">Historia</a></li>
				<li class="fin"><a href="contacto.jsp" title="Contacto" class="">Contacto</a></li>
			</ul>
		</div>
		<!-- end menu -->
	</div>
	<!-- end menu-wrapper-->
	<div id="wrapper2">
		<div id="header_home">
			<h2>Prob&aacute; vos tambi&eacute;n el sabor de la <span class="violeta">ternura</span></h2>  
			<span class="derecha">2256 <span class="violeta">personas ya se animaron</span></span>
			<div id="galeria_parte_1" class="panel_galeria">
				<div class="texto_header">
					<h1></h1>
					<p></p>
				</div>
			</div>
			<!-- end parte_1-->
		</div>
		<!-- header-home-->
		<div id="fotos">
			<h2>TU FOTO MILKA</h2>
				<ul>
					<li class="f-grande"><a href="#" title="tu foto milka" class="activo"><img src="images/foto1.jpg" width="212" height="157" alt="foto1" /></a></li>
					<li class="f-grande"><a href="#" title="tu foto milka" class="activo"><img src="images/foto2.jpg" width="66" height="66" alt="foto2" /></a></li>
					<li class="f-grande"><a href="#" title="tu foto milka" class="activo"><img src="images/foto3.jpg" width="66" height="66" alt="foto3" /></a></li>
					<li class="f-grande"><a href="#" title="tu foto milka" class="activo"><img src="images/foto4.jpg" width="66" height="66" alt="foto4" /></a></li>
					<li class="f-grande"><a href="#" title="tu foto milka" class="activo"><img src="images/foto5.jpg" width="66" height="66" alt="foto5" /></a></li>
					<li class="f-grande"><a href="#" title="tu foto milka" class="activo"><img src="images/foto6.jpg" width="66" height="66" alt="foto6" /></a></li>
					<li class="f-grande"><a href="#" title="tu foto milka" class="activo"><img src="images/foto7.jpg" width="66" height="66" alt="foto7" /></a></li>
				</ul>
			</div>
			<!-- end fotos-->
		</div>
		<!-- end wrapper2-->
		<div id="galeria-videos">
			<h2>Frecuencia <span class="violeta">Ternura</span></h2>
			<span class="derecha-videos">2256 <span class="violeta">personas se encuentran en la misma frecuencia</span></span>
			<ul>
				<li class=""><a href="#" title="Video 1" class="activo"><img src="images/video1.jpg" width="133" height="83" alt="Video1" /></a><h3>Jugando con chocolate</h3><a href="#" class="playlist">Playlist</a></li>
				<li class=""><a href="#" title="Video 2" class="activo"><img src="images/video2.jpg" width="133" height="83" alt="Video2" /></a><h3>100 preguntas para la vaca</h3><a href="#" class="playlist">Playlist</a></li>
				<li class=""><a href="#" title="Video 3" class="activo"><img src="images/video3.jpg" width="133" height="83" alt="Video3" /></a><h3>UNboxings</h3><a href="#" class="playlist">Playlist</a></li>
				<li class=""><a href="#" title="Video 4" class="activo"><img src="images/video4.jpg" width="133" height="83" alt="Video4" /></a><h3>Para levantarse un domingo</h3><a href="#" class="playlist">Playlist</a></li>
				<li class=""><a href="#" title="Video 5" class="activo"><img src="images/video5.jpg" width="133" height="83" alt="Video5" /></a><h3>Videos Tiernos</h3><a href="#" class="playlist">Playlist</a></li>
				<li class=""><a href="#" title="Video 6" class="activo"><img src="images/video6.jpg" width="133" height="83" alt="Video6" /></a><h3>Tarde de lluvia</h3><a href="#" class="playlist">Playlist</a></li>
			</ul>
		</div>
		<!-- end galeria-videos-->
    
   <div id="productos">
    	<h2>NUESTROS CHOCOLATES...</h2>
       <div id="social">
       		<ul>
                <li class="facebook"><a href="#" title="Facebook"><img src="images/facebook.png" width="22" height="22" alt="Facebook" border="none" /></a></li>       			<li class="twitter"><a href="#" title="Twitter"><img src="images/twitter.png" width="22" height="22" alt="Twitter" border="none" /></a></li>
            </ul>
        </div>
       <!-- end social-->
       <div class="imagen"><a href="productos_alfajores.jsp"><img src="images/chocolates.jpg" alt="Chocolates" width="300" height="354" border="0" /></a></div>
       <div id="bt-productos"><a href="productos_alfajores.jsp" title="Ver todos"></a>
       </div>
    
    
    </div>
    <!-- end productos-->
    <div id="box-facebook">
    <div class="fb-like-box" data-href="http://www.facebook.com/chocolat" data-width="300" data-height="437" data-show-faces="false" data-border-color="#dbdbdb" data-stream="true" data-header="false"></div>
    </div>
    <div id="box-twitter">
    	<script>
new TWTR.Widget({
  version: 2,
  type: 'profile',
  rpp: 5,
  interval: 30000,
  width: 243,
  height: 352,
  theme: {
    shell: {
      background: '#8759a5',
      color: '#ffffff'
    },
    tweets: {
      background: '#ffffff',
      color: '#747474',
      links: '#8759a5'
    }
  },
  features: {
    scrollbar: true,
    loop: false,
    live: true,
    behavior: 'all'
  }
}).render().setUser('MilkaChocolate').start();
</script>

    </div>
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