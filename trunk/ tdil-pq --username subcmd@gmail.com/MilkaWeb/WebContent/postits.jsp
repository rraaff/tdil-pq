<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.tdil.web.SearchPage"%>
<%@page import="com.tdil.milka.model.WallWritting"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.web.PaginationUtils"%>
<%@page import="com.tdil.milka.web.PapapediaUtils"%>
<%@page import="com.tdil.milka.model.ClickCounter"%>
<%@page import="com.tdil.milka.web.MeltButton"%>
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.milka.utils.SystemPropertiesKeys"%>
<%@page import="com.tdil.milka.web.SystemPropertyUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Milka.com.ar | Sitio Oficial | Experiencia Postits</title>
<% 
	if (!"true".equals(request.getParameter("dnc"))) {
		MeltButton.incrementCounter(MeltButton.POSTIT_RENDER);
	}
	String lnk = StringUtils.isEmpty(request.getParameter("lnk")) ? "" : request.getParameter("lnk");
%>
<%
	String nextPage = "cartasDeHijosAPadres.jsp";
	String prevPage = "apodosDeAmor.jsp";
%>
<%@ include file="includes/head.jsp" %>
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='./js/jquery.cookie.js'></script>
<script type='text/javascript' src='./js/jquery.melt-button.js'></script>
<script>
$(document).ready(
	function(){
		$("div[id^='mb-']").each(function(indice,valor) {
		   $(valor).meltbutton();
		});
		<%@ include file="includes/postItReady.jspf" %>

		$( "#closegracias" ).click(function() {
			$( "#graciasporsubir" ).fadeOut();
			$( "#bottomLayer" ).fadeOut();
		});
		$( "#cancelalta" ).click(function() {
			$( "#altalayer" ).fadeOut();
			$( "#bottomLayer" ).fadeOut();
		});
		$( "#closeerror" ).click(function() {
			$( "#erroralta" ).fadeOut();
			$( "#bottomLayer" ).fadeOut();
		});
	}
	
);

function altaExperiencia() {
	$window = $(window);
    var top = ($window.height() / 2) - ($( "#altalayer" ).height() / 2);
    var left = ($window.width() / 2) - ($( "#altalayer" ).width() / 2);
	$("input[name='authorBean.name']").attr('value', '');
	$("input[name='authorBean.email']").attr('value', '');
	$("input[name='authorBean.acceptPolitics']").attr('checked', false);
	$( "#altalayer" ).css({
		position: 'absolute',
		top: top + 'px',
		left: left + 'px'
	}).fadeIn(500);
	$( "#bottomLayer" ).css({
		position: 'absolute'
	}).fadeIn(499);
}

function clearData() {
	$("input[name='authorBean.name']").attr('value', '');
	$("input[name='authorBean.email']").attr('value', '');
	$("input[name='authorBean.acceptPolitics']").attr('checked', false);
	$("textarea[name='text']").attr('value', '');
}

function postAltaPostIt(data) {
	if (data.result == 'OK') {
		clearData();
		$( "#altalayer" ).fadeOut();
		$window = $(window);
	    var top = ($window.height() / 2) - ($( "#graciasporsubir" ).height() / 2);
	    var left = ($window.width() / 2) - ($( "#graciasporsubir" ).width() / 2);
		$( "#graciasporsubir" ).css({
			position: 'absolute',
	        top: top + 'px',
	        left: left + 'px'
	      }).fadeIn(500);
	} else {
		$( "#altalayer" ).fadeOut();
		$window = $(window);
	    var top = ($window.height() / 2) - ($( "#erroralta" ).height() / 2);
	    var left = ($window.width() / 2) - ($( "#erroralta" ).width() / 2);
		$( "#erroralta" ).css({
			position: 'absolute',
	        top: top + 'px',
	        left: left + 'px'
	      }).fadeIn(500);
	}
}
</script>

<link href='http://fonts.googleapis.com/css?family=Sue+Ellen+Francisco' rel='stylesheet' type='text/css'/>
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
	_gaq.push(['_setAccount', 'UA-32381287-1']);
	_gaq.push(['_trackPageview']);
	(function() {
		var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	})();
</script>
<script type='text/javascript' src='swf/ExpPostits/scripts/AC_RunActiveContent.js'></script>
<style>
<!-- 
body {
	font-family: Georgia, "Times New Roman", Times, serif;
	background-image: url(images/experiencias/postits/tramaDeCorcho.jpg);
	background-repeat: repeat;
	color: #FFFFFF;
	overflow:hidden;
}
div { /*border:dotted 1px #00CC33;*/ }
#altalayer {
	width:306px;
	height:406px;
	background-image: url(images/experiencias/postits/fondoAdd.png);
	background-repeat: no-repeat;
	background-position: center center;
	padding:62px;
}
#graciasporsubir, #erroralta {
	color:#FFFFFF;
	background-color:#000000;
	width:230px;
	padding:15px;
	
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
}
#lineadecontenido {
	height:30px;
	float:left;
}
#Nombre {
	height: 25px;
	width: 206px;
	left: 85px;
	top: 12px;
	position: relative;
}
#E-Mail {
	height: 25px;
	width: 206px;
	left: 85px;
	top: 22px;
	position: relative;
}
#Politicas {
	height: 25px;
	width: 160px;
	left: 0px;
	top: 40px;
	position: relative;
}
#SubirImagen {
	height: 100px;
	width: 290px;
	left: 0px;
	top: 70px;
	position: relative;
}
#buttonHolder {
	border:none;
	height: 82px;
	width: 290px;
	left: 0px;
	top: 70px;
	position: relative;
}
.normalField {
	font-family:"Trebuchet MS", Arial, sans-serif;
	width:206px;
	height:25px;
	line-height:22px;
	border: dotted 1px #ad9d1f;
	background:transparent;
}
.normalTextArea {
	width:290px;
	height:100px;
}
-->
</style>
</head>

<body>
<% int barClickCounter = MeltButton.POSTIT_COUNTER; %>
<div id="floater">
	<%@ include file="includes/barraExperiencias.jsp" %>
</div>
<div id="flashin">
	<script language="javascript">
		if (AC_FL_RunContent == 0) {
			alert("Esta p&aacute;gina requiere el archivo AC_RunActiveContent.js.");
		} else {
			AC_FL_RunContent(
				'codebase', 'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0',
				'width', '100%',
				'height', '100%',
				'src', 'swf/ExpPostits/milka',
				'quality', 'best',
				'pluginspage', 'http://www.macromedia.com/go/getflashplayer',
				'align', 'middle',
				'play', 'true',
				'loop', 'true',
				'scale', 'showall',
				'wmode', 'transparent',
				'devicefont', 'false',
				'id', 'milka',
				'bgcolor', '#000000',
				'name', 'milka',
				'menu', 'true',
				'allowFullScreen', 'false',
				'allowScriptAccess','sameDomain',
				'movie', 'swf/ExpPostits/milka',
	'flashvars','xmlfile=./postIts.xml&separacionpostit=71&scrollspeedx=7&scrollspeedy=5&opacidadshader=70&minpostits=500&imgbotonup=swf/ExpPostits/taller/enviarOut.png&imgbotonover=swf/ExpPostits/taller/enviarOver.png&imgbotondiffh=130&imgbotondiffv=40',
				'salign', ''
				); //end AC code
		}
	</script>
	<noscript>
	<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0" width="100%" height="100%" id="milka" align="middle">
		<param name="allowScriptAccess" value="sameDomain" />
		<param name="allowFullScreen" value="false" />
		<param name="movie" value="swf/ExpPostits/milka.swf" />
		<param name="quality" value="best" />
		<param name="wmode" value="transparent" />
		<PARAM NAME="flashvars" VALUE="xmlfile=./postIts.xml&separacionpostit=72&scrollspeedx=7&scrollspeedy=5&opacidadshader=70&minpostits=500&imgbotonup=swf/ExpPostits/taller/enviarOut.png&imgbotonover=swf/ExpPostits/taller/enviarOver.png&imgbotondiffh=130&imgbotondiffv=40">
	<embed src="swf/ExpPostits/milka.swf" 
	flashvars="xmlfile=./postIts.xml&separacionpostit=72&scrollspeedx=7&scrollspeedy=5&opacidadshader=70&minpostits=500&imgbotonup=swf/ExpPostits/taller/enviarOut.png&imgbotonover=swf/ExpPostits/taller/enviarOver.png&imgbotondiffh=130&imgbotondiffv=40"
	quality="best" bgcolor="#000000" width="100%" height="100%" name="milka" align="middle" wmode="transparent" allowScriptAccess="sameDomain" allowFullScreen="false" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
	</object>
	</noscript>
</div>
<%@ include file="includes/fbShare.jsp" %>
<div id="bottomLayer" class="hide"><!-- --></div>
<%@ include file="includes/postItDialogs.jspf" %>
</body>
</html>
