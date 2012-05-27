<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.tdil.milka.model.ClickCounter"%>
<%@page import="com.tdil.milka.web.MeltButton"%>
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Milka.com.ar | Sitio Oficial</title>
<meta name="keywords" content="Milka, chocolate, vaca, vaca lila, vaca violeta, chocolate aireado, chocolate relleno, relleno de dulce de leche, bombones, corazón, alfajor, tableta, almendras, castañas con caramelo, caramel, oreo, leger, baño maría, fondue, ternura, soft cappuccino, soft combinado, soft chocolate, bajón, regalo, amor, enamorados, avellanas, Cadbury, sabor, Shot, Tofi, Bon o bon, Cofler, golosinas, kiosco, postre, aniversario, cumpleaños, Kraft Foods, rico, sabor, cacao">
<meta name="description" content="Bienvenidos al sitio oficial de Milka Argentina: los invitamos a deleitarse con el más rico chocolate con leche o con el Milka Dulce de Leche o con el Milka Almendras o con el Milka Oreo Blanco o Milka Castañas con Caramelo o Milka Oreo Leche o Milka Te Quiero Mucho! o Milka Feliz Cumple Atrasado! o Milka Suerte! o Milka Vos Sabés Por qué! o Milka Feliz Día! o Milka Gracias! o Milka Dulce de Leche Blanco o Milka Bombón o Milka Sahne Creme o Milka Caramel o Milka Soft Avellanas o Milka Soft Cappucciono o Milka Soft o Milka Soft Combinado o Milka Leger o Leger con Almedras o con el Leger Combinado. Los invitamos a probar el sabor de la ternura." />
<%@ include file="includes/head.jsp" %>
<link rel="stylesheet" href="./css/lightbox.css" type="text/css" media="screen" />
<script src="./js/lightbox-melt.js"></script>

<script>
$(document).ready(
	function(){
		$("div[id^='mb-']").each(function(indice,valor) {
		   $(valor).meltbutton();
		});
	
		function generateTooltips() {
			  //make sure tool tip is enabled for any new error label
				$("img[id*='error']").tooltip({
					showURL: false,
					opacity: 0.99,
					fade: 150,
					positionRight: true ,
					bodyHandler: function() {
						return $("#"+this.id).attr("hovertext");
					}
				});
				//make sure tool tip is enabled for any new valid label
				$("img[src*='tick.gif']").tooltip({
					showURL: false,
						bodyHandler: function() {
							return "OK";
						}
				});
			}
			
			$('form').mouseover(function(){
				      generateTooltips();
				    });
		
			$("form[name='MilkaPhotoForm']").validate({
					errorPlacement: function(error, element) {
						error.appendTo( element.parent("td").next("td") );
					},
					rules: { 'authorBean.name': {required: true},
							'authorBean.email': {required: true, email: true},
							'authorBean.acceptPolitics': {required: true},
							'photoFormFile': {required: true}
					},
					messages: {
						'authorBean.name': {required: "<img id='nameerror' src='images/unchecked.gif' hovertext='Ingrese el nombre.' />"}, 
						'authorBean.email': {required: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese el email.' />",
								email: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese un email valido.' />"},
						'authorBean.acceptPolitics': {required: "<img id='politicserror' src='images/unchecked.gif' hovertext='Debe aceptar las politicas.' />"},
						'photoFormFile': {required: "<img id='photoerror' src='images/unchecked.gif' hovertext='Seleccione una foto.' />"}
					},
					submitHandler: function() {
			            $("form[name='MilkaPhotoForm']").ajaxSubmit({
			    			type: "POST",
			    			url: "./uploadMilkaPhoto.do",
			    			dataType: "json",
			    			success: postUploadFotoMilka
			    			});
			        }
				});
				
				$( "#dialog-form" ).dialog({
					autoOpen: false,
					height: 300,
					width: 350,
					modal: true
				});
				$( "#subifotomilka" ).click(function() {
					$( "#dialog-form" ).dialog( "open" );
				});
			
		}
	);

function postUploadFotoMilka(data) {
	$( "#dialog-form" ).dialog("destroy" );
	$( "#dialog:ui-dialog" ).dialog( "destroy" );
		$( "#dialog-modal" ).dialog({
			height: 140,
			modal: true
		});
}
</script>

<!-- FB share metas >
<meta property="og:title" content="Milka Alfajores" />
<meta property="og:description" content="Compartir la dulzura de nuestros alfajores con tus amigos es un acto de dulzura." />
<meta property="og:image" content="http://localhost:8180/MilkaWeb/images/productos/alfajores_comp4.jpg" /-->
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/es_LA/all.js#xfbml=1&appId=159591494155451";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

<script charset="utf-8" src="http://widgets.twimg.com/j/2/widget.js"></script>
<script type="text/javascript">
	var _gaq = _gaq || [];
	_gaq.push(['_setAccount', 'UA-29166792-1']);
	_gaq.push(['_trackPageview']);
	(function() {
		var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	})();
</script>
<script src="swf/homeCentral/scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<style>
 #bm_me_derrite a{
	float:right;
	width:108px;
	height:41px;
	background:url(images/barra/me-derrite.png) no-repeat;
	position:relative;
	margin-right: 50px;
}
#bm_personas{
	float:left;
	width:180px;
	height:20px;
	position:relative;
	color:#FFF;
	font-family:Arial, Helvetica, sans-serif;
	font-size:11px;
	top:14px;
	left: 280px;
}
#bm_personas span{
	color:#b398ff;
}
</style>
</head>
<body>
<div id="content">
	<div id="header">
		<div id="logo"><a href="index.jsp" title="Milka"></a></div>
		<div id="box">
			<div id="subi"><a href="#" id="subifotomilka" title="SUB&Iacute; tu foto con chocolate"></a></div>
			<div id="social">
				<ul>
					<li class="faqs"><a href="faqs.jsp" title="FAQS"></a></li>
					<li class="t"><a href="#" title="t"><img src="images/t.png" width="22" height="22" alt="t" border="none" /></a></li>
					<li class="facebook"><a href="http://www.facebook.com/milka.com.ar" title="Facebook"><img src="images/facebook.png" width="22" height="22" alt="Facebook" border="none" /></a></li>
					<li class="twitter"><a href="#" title="Twitter"><img src="images/twitter.png" width="22" height="22" alt="Twitter" border="none" /></a></li>
					<li class="youtube"><a href="http://www.youtube.com/user/ChocolateMilkaArg" title="Youtube"><img src="images/youtube.png" width="22" height="22" alt="Youtube" border="none" /></a></li>
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
			<div id="homeSlider">
				<script>
					if (AC_FL_RunContent == 0) {
						alert("This page requires AC_RunActiveContent.js.");
					} else {
						AC_FL_RunContent( 'codebase', 'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0', 
							'width', '627', 
							'height','309',
							'FlashVars', 'XMLFile=swf/homeCentral/xml/config.xml',
							'src', 'swf/homeCentral/slider', 
							'quality', 'best', 
							'pluginspage', 'http://www.macromedia.com/go/getflashplayer', 
							'align', 'middle', 
							'play', 'true', 
							'loop', 'true', 
							'scale', 'showall', 
							'wmode', 'transparent', 
							'devicefont', 'true', 
							'id', 'ACTest', 
							'bgcolor', '#ffffff', 
							'name', 'slider', 
							'menu', 'true', 
							'allowScriptAccess', 'sameDomain', 
							'movie', 'swf/homeCentral/slider',
							'salign', '' ); 
							 //end AC code 
					}
				</script>
			</div>
			<!-- end parte_1-->
		</div>
		<!-- header-home-->
		<%@ include file="includes/tuFotoMilka.jsp" %>
		<!-- end wrapper2-->
		<div id="galeria-videos">
			<h2>Frecuencia <span class="violeta">Ternura</span></h2>
			<span class="derecha-videos"><!--2256 <span class="violeta">personas se encuentran en la misma frecuencia</span--></span>
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
					<li class="facebook"><a href="http://www.facebook.com/milka.com.ar" title="Facebook"><img src="images/facebook.png" width="22" height="22" alt="Facebook" border="none" /></a></li>
					<!-- li class="facebook"><a name="fb_share" type="icon_link" share_url="http://localhost:8180/MilkaWeb/productos_alfajores.jsp"><img src="images/facebook.png" width="22" height="22" alt="Facebook" border="none" /></a></li>
					<script src="http://static.ak.fbcdn.net/connect.php/js/FB.Share" type="text/javascript"></script-->
					<li class="twitter"><a href="#" title="Twitter"><img src="images/twitter.png" width="22" height="22" alt="Twitter" border="none" /></a></li>
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
    
<div id="dialog-modal" title="Tu foto milka">
	<p>
		Gracias por subir tu foto.<br>
		Te avisaremos cuando este aprobada.
	</p>
</div>
<br>

<div id="dialog-form" title="Subi tu foto con chocolate">
	<html:form method="POST" action="/uploadMilkaPhoto" enctype="multipart/form-data">
		<table>
			<tr>
				<td>Nombre:<html:text name="MilkaPhotoForm" property="authorBean.name" styleClass="width180"/></td>
				<td width="25" id="authorBean.nameerr"></td>
			</tr>
			<tr>
				<td>email:<html:text name="MilkaPhotoForm" property="authorBean.email" styleClass="width180"/></td>
				<td width="25" id="authorBean.emailerr"></td>
			</tr>
			<tr>
				<td>Politicas:<html:checkbox name="MilkaPhotoForm" property="authorBean.acceptPolitics" styleClass="width180"/></td>
				<td width="25" id="authorBean.acceptPoliticserr"></td>
			</tr>
			<tr>
				<td><html:file name="MilkaPhotoForm" property="photoFormFile" /></td>
				<td width="25" id="photoFormFileerr"></td>
			</tr>
			<tr>
				<td>
					<html:submit property="operation">Upload</html:submit>
				</td>
			</tr>
		</table>	
	</html:form>
</div>
    
<%@ include file="includes/footer.jsp" %>