<%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><!--
--><%@ page info="home"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="includes/checkThalamusUp.jspf" %><!--
--><%@ include file="includes/userLogged.jspf" %><!--
--><%@ include file="includes/mustBeLogged.jspf" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/reset-styles.css" rel="stylesheet" media="screen">
<link href="css/sizers.css" rel="stylesheet" media="screen">
<link href="css/tdil.bootstrap.modifier.css" rel="stylesheet" media="screen">

<%@ include file="includes/headLogged.jsp" %>

<link href="css/slider.css" rel="stylesheet" type="text/css" />
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

	$(document).ready(
			function(){
	<%@ include file="includes/closeLegalesLayer.jsp" %>
			}
	);

	<%@ include file="includes/updatePersonChangePasswordJS.jspf" %>
	<%@ include file="includes/centerLayerJS.jspf" %>

	<%@ include file="includes/openLegalesLayer.jsp" %>
</script>
<!-- End Slider -->
<script>
$(function() {
		$( "#accessPreventPortal" ).click(function() {
			 $.ajax({
					type: "POST",
					url: "http://test.lojackgis.com.ar:8080/webgis/Prevent_WebUI/loginportal.ashx",
					data: {
						SESSIONID: '<%=websiteUser.getToken().getCookie("JSESSIONID").getValue()%>',
						TIMEZONEOFFSET: -3
					},
					beforeSend: function (request) {
						request.setRequestHeader("Lojack-Token", "a5b0981a0188bb9a5b7fe44b6c32d894");
					},
					success: function (data) {
						alert(data);
						if (data.status == 200)
							window.location = decodeURI(data.url);
						else
							alert(data.error);
					}
				});
		});
});
</script>

<link type="text/css" href="css/index_menu.css" rel="stylesheet" />
<link type="text/css" href="css/index_modales.css" rel="stylesheet" />
<link type="text/css" href="css/index_social.css" rel="stylesheet" />
<link type="text/css" href="css/copyright.css" rel="stylesheet" />
<link type="text/css" href="css/laruedita.css" rel="stylesheet" />
<style>
header {
	background: url(images/skin_lj_rl/backs/topLayer.png);
	background-repeat: repeat;
	width: 100%;
	min-height: 63px;
	position: fixed;
	z-index: 1005;
	top: 0px;
	display: inline-block;
}
#floatyMenu {
	/*height: 63px;*/
	width: 100%;
	/*min-height: 63px;*/

}
#floatyMenu .wrapper {
	text-align: right;
/*	min-width: 968px;*/
/*	height: 63px;*/
	margin: 0 auto;
}
#floatyMenu ul {
	margin: 0;
	padding: 0;
	list-style: none;
}
#floatyMenu ul li {
	color: #fff;
	font-size: 8px;
	font-weight: bold;
	text-transform: uppercase;
	line-height: 63px;
	display: block;
	position: relative;	
}
#floatyMenu ul li a {
	color: #fff;
	font-size: 8px;
	font-weight: bold;
	text-transform: uppercase;
	line-height: 63px;
	margin: 0;
	padding: 0 20px;
	float: right;
	display: block;
	position: relative;
}
#floatyMenu ul li a:hover, #floatyMenu ul li a:focus {
	background: #ee5222;
}
#floatyMenu ul li.avatarLi {
	float: left;
}
#floatyMenu ul li.avatarLi a,
#floatyMenu ul li.avatarLi a:hover,
#floatyMenu ul li.avatarLi a:focus {
	background:none;
	padding:0;
	margin:0;
	float: left;
}
#floatyMenu ul li.avatarLi img {
	width: 48px;
	height: 48px;
	margin: 0 10px 0 10px;
}
#floatyOpener {
	background: url(images/skin_lj_rl/backs/tabMenu_index.png);
	background-repeat: no-repeat;
	background-position: 731px 0;
	font-size: 10px;
	line-height: 22px;
	font-weight: bold;
	text-transform: uppercase;
	text-align: center;
	width: 237px;
	height: 22px;
	padding-left: 731px;
	margin: 0 auto;
}
#floatyOpener a {
	color: #fff;
}
span.userName {
	color: #f05223;
	float: left;
}
span.userSaludation { float: left; }

#flyingObjectContainer {
	border:dotted 3px red;
	max-width:1568px;
	margin:0 auto;
}
</style>

<link type="text/css" href="css/mediaQueries.css" rel="stylesheet" />
</head>
<body>
<header>
	<div id="floatyMenu">
		<div class="wrapper">
			<ul>
				<li class="avatarLi">
					<% if (websiteUser.getModelUser().getIdAvatar() != null && !websiteUser.getModelUser().getIdAvatar().equals(0)) { %>
						<a href="./goToEditProfile.do" title="Cambiar imagen">
							<img src="./download.st?id=<%=websiteUser.getModelUser().getIdAvatar()%>&type=PUBLIC&ext=<%=websiteUser.getModelUser().getExtAvatar()%>" align="absmiddle">
						</a>
					<% } %>
					<span class="userSaludation">Hola:&nbsp;</span><span class="userName"><%=websiteUser.getName()%></span>
				</li>
				<li><a href="logout.do" title="Salir del sistema">Salir</a></li>
				<li><a href="javascript:changePassword();" title="Cambiar mis clave">Cambiar mi clave</a></li>
				<li><a href="javascript:updatePerson();" title="Cambiar mis datos">Cambiar mis datos</a></li>
			</ul>
		</div>
	</div>
	<div id="floatyOpener"><a href="#" title="Abrir menú">Menu</a></div>
</header>
<%@ include file="includes/sectionSlider.jsp" %>
<%@ include file="includes/laRuedita.jsp" %>

<div id="flyingObjectContainer"> 
	<div id="logoIndex"><img src="images/skin_lj_rl/logos/lo-jack_index.png" /></div>
	
	<div id="socialSingleSignOn">
		<div><span class="textInside">Ingresá con tus cuentas</span></div>
		<div>
			<ul>
				<li class="sofacebook"><a href="<%=ThalamusClientBeanFacade.getFacebookLogin().getUrl()%>" id="fb" title="Ingresá con tu cuenta de Facebook"></a></li>
				<li class="sotwitter"><a href="<%=ThalamusClientBeanFacade.getTwitterLogin().getUrl()%>" id="fb" title="Ingresá con tu cuenta de Twitter"></a></li>
			</ul>
		</div>
	</div>
</div>

<section id="copyright">
	<div class="copy">
		<p>2013 lojack - todos los derechos reservados política de privacidad | <a href="javascript:verLegales();" id="legales" title="Legales">legales</a> | dirección general de defensa y protección al consumidor</p>
	</div>
</section>
<!-- div id="accessPreventPortal" style="z-index:3000; position: fixed;"><a href="#" style="color:#000;">test link</a></div -->

<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<!-- Layer legales -->
<%@ include file="includes/legalesLayer.jsp" %>
</body>
</html>