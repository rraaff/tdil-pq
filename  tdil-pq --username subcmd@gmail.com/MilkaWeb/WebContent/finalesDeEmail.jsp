<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.util.List"%>
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
<title>Milka.com.ar | Sitio Oficial | Experiencia Finales de E-Mail</title>
<% 
	if (!"true".equals(request.getParameter("dnc"))) {
		MeltButton.incrementCounter(MeltButton.FINALES_DE_EMAIL_RENDER);
	}
	String lnk = StringUtils.isEmpty(request.getParameter("lnk")) ? "" : request.getParameter("lnk");
%>
<%
	String nextPage = "apodosDeAmor.jsp";
	String prevPage = "papapedia.jsp";
%>
<link href='http://fonts.googleapis.com/css?family=Sue+Ellen+Francisco' rel='stylesheet' type='text/css'>
<%@ include file="includes/head.jsp" %>
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/es_LA/all.js#xfbml=1&appId=159591494155451";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

<script charset="utf-8" src="http://widgets.twimg.com/j/2/widget.js"></script>
<script src="swf/ExpCartasHaP/scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script>
$(document).ready(
	function(){
		$("div[id^='mb-']").each(function(indice,valor) {
		   $(valor).meltbutton();
		});
		<%@ include file="includes/finalesDeEmailReady.jspf" %>

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
}

function postUpload(data) {
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
<style>
<!-- 
body {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #1e1e1e;
	padding:0;
	margin:0;
	height:100%;
	overflow:hidden;
}
#graciasporsubir, #erroralta {
	color:#FFFFFF;
	background-color:#000000;
	width:230px;
		
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
}
#altalayer {
	background-image: url(images/experiencias/finalesEmails/upload.png);
	background-repeat: no-repeat;
	background-position: center center;
	width:489px;
	height:600px;
	padding:0px;
}
#lineadecontenido1 {
	width: 302px;
	margin-left:88px;
	margin-top:175px;
	margin-bottom:5px;
	top:176qpx;
	left:48px;
	position:relative;
}
#lineadecontenido2 {
	width: 302px;
	margin-left:88px;
	margin-bottom:5px;
	top:206qpx;
	left:48px;
	position:relative;
}
#lineadecontenido3 {
	width: 302px;
	margin-left:20px;
	margin-top:10px;
	top:5px;
	left:48px;
	position:relative;
}
#lineadecontenido4 {
	width: 302px;
	margin-left:20px;
	margin-top:10px;
	top:5px;
	left:48px;
	position:relative;
}
.specialFields {
	width:229px;
	height:19px;
	border:solid 1px #9d9fa1;
}
#textoEspecial {
	top:-3px;
	left:75px;
	position:relative;
	float:left;	
	width:360px;
}
.spacer{
	width:100px;
}
#buttonHolder .okCircle, .okCircle a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 1px;
	line-height:14px;
	color: #FFFFFF;
	background:transparent;
	text-decoration: none;
	background-image: url(images/experiencias/finalesEmails/boton.gif);
	background-repeat: no-repeat;
	background-position: center center;
	width:49px;
	height:48px;
	border:none;
	cursor:hand;
	margin:0;
	padding:0;
	top:-104px;
	left:380px;
	position:relative;
	
	-webkit-border-radius: 0;
	-moz-border-radius: 0;
	border-radius: 0;
	text-shadow: none;
	box-shadow: none;
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	-o-box-shadow: none;
}
#buttonCloseHolder {
	width:49px;
	height:21px;
	margin:0;
	padding:0;
	top:-360px;
	left:405px;
	position:relative;
}
#buttonCloseHolder .closeButton, .closeButton a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 1px;
	line-height:14px;
	color: #FFFFFF;
	text-decoration: none;
	background-image: url(images/experiencias/finalesEmails/close.gif);
	background-repeat: no-repeat;
	background-position: left top;
	width:49px;
	height:21px;
	border:none;
	border:transparent;
	cursor:hand;
	-webkit-border-radius: 0;
	-moz-border-radius: 0;
	border-radius: 0;
	text-shadow: none;
	box-shadow: none;
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	-o-box-shadow: none;
	background-color: transparent;
}
-->
</style>
</head>

<body>
<% int barClickCounter = MeltButton.FINALES_DE_EMAIL_COUNTER; %>
<div id="bottomLayer" class="hide"><!-- --></div>
<div id="floater">
	<%@ include file="includes/barraExperiencias.jsp" %>
</div>
<div id="flashin">
	<script>
		if (AC_FL_RunContent == 0) {
			alert("This page requires AC_RunActiveContent.js.");
		} else {
			AC_FL_RunContent( 'codebase', 'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0', 
				'width', '100%', 
				'height','100%',
				'FlashVars', 'XMLFile=finalesDeEmail.xml&URLtoUploads=javascript:altaExperiencia()',
				'src', 'swf/ExpFinalesDeMails/slider', 
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
				'movie', 'swf/ExpFinalesDeMails/slider',
				'salign', '' ); 
				//end AC code 
		} 
	</script>
</div>
<%@ include file="includes/fbShare.jsp" %>
<%@ include file="includes/finalesDeEmailDialogs.jspf" %>
</body>
</html>