<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="../favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/reset-styles.css" rel="stylesheet" media="screen">
<link href="css/sizers.css" rel="stylesheet" media="screen">
<!-- Bootstrap -->
<link type="text/css" href="../css/bootstrap.min.css" rel="stylesheet" />
<link type="text/css" href="css/tdil.mobile.bootstrap.modifier.css" rel="stylesheet" />
<script type='text/javascript' src='../js/jquery-1.7.min.js'></script>
<script type='text/javascript' src='../js/jquery.form.js'></script>
<script type='text/javascript' src='../js/jquery-ui-1.10.2.custom.min.js'></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery.tooltip.js"></script>
<script type="text/javascript" src="../js/jquery.jstepper.js"></script>
<script type="text/javascript" src="../js/jquery.jeditable.js"></script>

<script type="text/javascript" src="../js/jquery-latest.js"></script>
<link href="css/slider.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/slideshow.js"></script>
<script>
	var t=setInterval(function(){$("#right").click()},10000);
	$(document).ready(function()
	{
		var present=1;
		var next=2;
		var total_slide=document.getElementById("slider").childElementCount;

		$("#right").click(function()
		{

			present_slide="#slide"+present;
			next_slide="#slide"+next;
			$(present_slide).css("top","842px");
			$(next_slide).css("top","0px");
			present++;
			next++;
			if(present==(total_slide+1))
			{
				present=1;
				next=2;
				for(i=1;i<=total_slide;i++)
				{
					$("#slide"+i).css("top","842px");
				}
				$("#slide1").css("top","0px");
			}

		});

		$("#left").click(function()
		{
			if(present==1)
			{
			next_slide="#slide"+total_slide;
			present_slide="#slide"+present;
			$(present_slide).css("top","842px");
			$(next_slide).css("top","0px");

			present=total_slide;
			next=1;
			}else
			{
			next_slide="#slide"+(present-1);
			present_slide="#slide"+present;
			$(present_slide).css("top","842px");
			$(next_slide).css("top","0px");
			present--;
			next--;
			}
			if(next==0)
			{
				present=(total_slide-1);
				next=total_slide;

			}
		});
	});

</script>
<!-- End Slider -->


<link type="text/css" href="css/index_menu.css" rel="stylesheet" />
<link type="text/css" href="css/index_modales.css" rel="stylesheet" />
<link type="text/css" href="css/index_social.css" rel="stylesheet" />
<link type="text/css" href="css/copyright.css" rel="stylesheet" />
<link type="text/css" href="css/laruedita.css" rel="stylesheet" />
<link type="text/css" href="css/laruedita_animation.css" rel="stylesheet" />
</head>
<body>
<div id="menu" style="display: none;">
	<ul class="menu">
		<li class="first"><a href="#" class="parent"><span>Ingresa</span></a>
			<div>
				<ul>
					<li><a href="javascript:login();" id="login" title="Ingresar ahora"><span>Ingresar</span></a></li>
					<li><a href="javascript:forgotPassword();" id="forgotPassword" title="Recuperar clave"><span>Recuperar clave</span></a></li>
					<li><a href="javascript:register();" id="register" title="Registrate gratis"><span>Registrate Gratis</span></a></li>
					<li><a href="" id="fb" title="Ingresá con tu cuenta de Facebook"><span>Ingresá con tu FB</span></a></li>
					<li><a href="" id="fb" title="Ingresá con tu cuenta de Twitter"><span>Ingresá con tu Tw</span></a></li>
				</ul>
			</div>
		</li>
	</ul>
</div>
<%@ include file="includes/sectionSlider.jsp" %>
<%@ include file="includes/laRuedita.jsp" %>

<div id="logoIndex"><img src="../images/skin_lj_rl/logos/lo-jack_index.png" /></div>

<div id="socialSingleSignOn">
	<div><span class="textInside">Ingresá con tus cuentas</span></div>
	<div>
		<ul>
			<li class="sofacebook"><a href="<!-- %=ThalamusClientBeanFacade.getFacebookLogin().getUrl()% -->" id="fb" title="Ingresá con tu cuenta de Facebook"><img src="../images/skin_lj_rl/buttons/icon_white_facebook.png" /></a></li>
			<li class="sotwitter"><a href="<!-- %=twitterUrl.getUrl()% -->" id="fb" title="Ingresá con tu cuenta de Twitter"><img src="../images/skin_lj_rl/buttons/icon_white_twitter.png" /></a></li>
		</ul>
	</div>
</div>

<section id="copyright">
	<div class="copy">
		<p>2013 lojack - todos los derechos reservados política de privacidad | <a href="javascript:verLegales();" id="legales" title="Legales">legales</a> | dirección general de defensa y protección al consumidor</p>
	</div>
</section>

</body>
</html>