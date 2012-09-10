<%@page import="com.tdil.milka.model.valueobjects.MailToChildValueObject"%>
<%@page import="com.tdil.milka.web.MailToChildUtils"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.tdil.web.SearchPage"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.web.PaginationUtils"%>
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
<title>Milka.com.ar | Sitio Oficial | Experiencia Que Amas, Que Odias</title>
<% 
	if (!"true".equals(request.getParameter("dnc"))) {
		MeltButton.incrementCounter(MeltButton.QUE_AMAS_QUE_ODIAS_RENDER);
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
<% int barClickCounter = MeltButton.QUE_AMAS_QUE_ODIAS_COUNTER; 
	
%>
<script>
$(document).ready(
	function(){
		$("div[id^='mb-']").each(function(indice,valor) {
		   $(valor).meltbutton();
		});
		

		<% if ("true".equals(request.getParameter("dnc"))) { %>
			<% if ("1".equals(request.getParameter("amas"))) { %>
				$.cookie('amas', "set", { expires: 1, path: "/" });
			<% } %>
			<% if ("1".equals(request.getParameter("odias"))) { %>
				$.cookie('odias', "set", { expires: 1, path: "/" });
			<% } %>
		<% } %>

  	  	if ($.cookie('amas')) {
  	  	  	// deshabilito el alta de amas
  	  	}
  	  	if ($.cookie('odias')) {
  	  	// deshabilito el alta de odias
	  	}
		
		$( "#closegracias" ).click(function() {
			$( "#graciasporsubir" ).fadeOut();
			$( "#bottomLayer" ).fadeOut();
		});
		$( "#cancelaltalove" ).click(function() {
			$( "#altalove" ).fadeOut();
		});
		$( "#cancelaltahate" ).click(function() {
			$( "#altahate" ).fadeOut();
		});
		$( "#closeerrorlove" ).click(function() {
			$( "#erroraltalove" ).fadeOut();
		});
		$( "#closeerrorhate" ).click(function() {
			$( "#erroraltahate" ).fadeOut();
		});
		
		// code for fade in element by element with delay
		$.fn.fadeInWithDelay = function(){
			var delay = 0;
			return this.each(function(){
				$(this).delay(delay).animate({opacity:1}, 200);
				delay += 100;
			});
		};
		
	}
	
);

function altaLove() {
	$window = $(window);
    var top = ($window.height() / 2) - ($( "#altalove" ).height() / 2);
    var left = ($window.width() / 2) - ($( "#altalove" ).width() / 2);
	$("input[name='text']").attr('value', '');
	$( "#altalove" ).css({
		position: 'absolute',
		top: top + 'px',
		left: left + 'px'
	}).fadeIn(500);
}

function altaHate() {
	$window = $(window);
    var top = ($window.height() / 2) - ($( "#altahate" ).height() / 2);
    var left = ($window.width() / 2) - ($( "#altahate" ).width() / 2);
	$("input[name='text']").attr('value', '');
	$( "#altahate" ).css({
		position: 'absolute',
		top: top + 'px',
		left: left + 'px'
	}).fadeIn(500);
}

function clearData() {
	$("input[name='text']").attr('value', '');
}

function postLove(data) {
	if (data.result == 'OK') {
		clearData();
		$( "#altalove" ).fadeOut();
		$window = $(window);
	    var top = ($window.height() / 2) - ($( "#graciasporsubir" ).height() / 2);
	    var left = ($window.width() / 2) - ($( "#graciasporsubir" ).width() / 2);
		$( "#graciasporsubir" ).css({
			position: 'absolute',
	        top: top + 'px',
	        left: left + 'px'
	      }).fadeIn(500);
	} else {
		$( "#altalove" ).fadeOut();
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

function postHate(data) {
	if (data.result == 'OK') {
		clearData();
		$( "#altahate" ).fadeOut();
		$window = $(window);
	    var top = ($window.height() / 2) - ($( "#graciasporsubir" ).height() / 2);
	    var left = ($window.width() / 2) - ($( "#graciasporsubir" ).width() / 2);
		$( "#graciasporsubir" ).css({
			position: 'absolute',
	        top: top + 'px',
	        left: left + 'px'
	      }).fadeIn(500);
	} else {
		$( "#altahate" ).fadeOut();
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
<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css'>
<style>
<!-- 
body {
	font-family: 'Oswald', sans-serif;
	color: #00FF55;
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
/*
#header {
	background-color:#8c7bb5;
	background-image: url(images/experiencias/padresAHijos/header.gif);
	background-repeat: no-repeat;
	height: 182px;
	width: 828px;
	margin-top: 0px;
	margin-right: auto;
	margin-bottom: 25px;
	margin-left: auto;
	background-position: center bottom;
}
#pageBody {
	width: 828px;
	margin: 0px auto;
}
#pageLeft {
	width:523px;
	float:left;
}
#moduleContent {
	background-image: url(images/experiencias/padresAHijos/bgModule.gif);
	background-repeat: repeat-y;
	width: 523px;
	float: left;
	margin-bottom: 25px;
}
#moduleContent #date {
	background-image: url(images/experiencias/padresAHijos/dateBase.png);
	background-repeat: no-repeat;
	background-position: center center;
	float: left;
	height: 47px;
	width: 58px;
	margin-top: 28px;
	font-size: 11px;
	color: #FFFFFF;
	text-align: center;
	vertical-align: middle;
	padding-top: 10px;
}
#moduleContent h1 {
	color:#FFF;
	padding-top:25px;
	padding-left:10px;
	padding-bottom:5px;
	text-transform: uppercase;
	font-size: 14px;
	width:450px;
	float:left;
}
#moduleContent p {
	color:#806bb3;
	width:200px;
	padding-left:10px;
	padding-right:240px;
	float:left;
}
#moduleContent img {
	background-color:#FFFFFF;
	padding:10px;
	margin-left:10px;
	margin-bottom:20px;
}
#pageRight {
	width:233px;
	float:right;
}
#blockLoader {
	width:200px;
	height:58px;
	padding-bottom:10px;
	padding-top:10px;
	border-top-width: 1px;
	border-bottom-width: 1px;
	border-top-style: solid;
	border-bottom-style: solid;
	border-top-color: #e9e9e9;
	border-bottom-color: #e9e9e9;	
}
#blockLoader a, #blockLoader a:hover, #blockLoader a:visited, #blockLoader a:active {
	color:#727272;
	font-family: Arial, Helvetica, sans-serif;
	font-size:10px;
}
#blockLoader img {
	float:left;
}
#lastEntriesNames {
	width:100%;
	font-family:Arial, Helvetica, sans-serif;
}
#lastEntriesNames a {
	width:100%;
	line-height:28px;
	float:left;
}
#lastEntriesNames a:hover {
	color:#333333;
}
#entryNumber {
	color:#806bb3;
	font-size:14px;
	width:200px;
	padding-bottom:10px;
	padding-top:10px;
	margin-top:25px;
	margin-bottom:25px;
	border-top-width: 1px;
	border-bottom-width: 1px;
	border-top-style: solid;
	border-bottom-style: solid;
	border-top-color: #e9e9e9;
	border-bottom-color: #e9e9e9;
	float:left;
}
#entryNumber .numero {
	color:#FFF;
}
#scrollpaginationdemo {
	width:600px;
	margin:0px auto;
}

#scrollpaginationdemo ul {
	list-style:none;
	width:100%;
	margin:0px auto;
	padding:0px;
}

#scrollpaginationdemo ul li {
	margin:10px 0px;
	width:100%;
	background:#352828;
	padding:5px 10px;
	border-radius: 15px;
	text-shadow: 2px 1px -1px #000000;
}

.loading {
	background:#c1c39a;
	color:#303030;
	font-size:20px;
	padding:5px 10px;
	text-align:center;
	width:450px;
	margin:0px auto;
	display:none;
	border-radius: 5px;
}*/
-->
</style>
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
}
#bm_personas span{
	color:#b398ff;
}

/**/
#acaVaElFlash {
	width:700px;
	height:500px;
	margin:0 auto;
}
#controles {
	width:450px;
	
	margin:0 auto;
}
.myRenglon { width:450px; height:47px; float:left; }
.myLabel { float:left; }
.qaqoField {
	width:264px;
	height:37px;
	font-size:25px;
	border: solid 1px #834baa;
	margin-top:5px;
	margin-bottom:5px;
}
#dato {
	color:#595959;
	font-family:"Tahoma", "Trebuchet MS", Arial, sans-serif;
	font-size:11px;
	text-align:center;
	width:450px;
	float:left;
}
</style>

</head>

<body>
<div id="floater">
	<%@ include file="includes/barraExperiencias.jsp" %>
</div>
<div id="acaVaElFlash">
	<script>
		if (AC_FL_RunContent == 0) {
			alert("This page requires AC_RunActiveContent.js.");
		} else {
			AC_FL_RunContent( 'codebase', 'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0', 
				'width', '700', 
				'height','500',
				'FlashVars', 'fileToLoad=./loveWordCloudTag.st',
				'src', 'swf/ExpQAQO/qa', 
				'quality', 'best', 
				'pluginspage', 'http://www.macromedia.com/go/getflashplayer', 
				'align', 'middle', 
				'play', 'true', 
				'loop', 'true', 
				'scale', 'showall', 
				'wmode', 'opaque', 
				'devicefont', 'true', 
				'id', 'ACTest', 
				'bgcolor', '#ffffff', 
				'name', 'qa', 
				'menu', 'true', 
				'allowScriptAccess', 'sameDomain', 
				'movie', 'swf/ExpQAQO/qa',
				'salign', '' ); 
				//end AC code 
		} 
	</script>
<!-- img src=""-->
</div>
<html:form method="POST" action="/addLove" >
	<html:hidden name="LoveForm" property="love" value="on"/>
	<div id="controles">
		<div class="myRenglon">
			<div class="myLabel"><img src="images/experiencias/QAQO/tag_queAmas.gif" /><input type="image" src="images/experiencias/QAQO/btn_queAmas.gif" width="50" height="47" /></div>
			<!-- h t m l :  s u b m i t    pr  o p e r t y =  " op  e r a t i o n ">< / h tm l  : s u b m  it -->
			<div class="myLabel"><html:text name="LoveForm" property="text" styleClass="qaqoField"/></div>
		</div>
		<div class="myRenglon" style="background-image:url(images/experiencias/QAQO/divison.gif); background-repeat:repeat-x; height:18px;">&nbsp;</div>
		<div class="myRenglon">
			<div id="dato">a personas respondieron a esta pregunta</div>
		</div>
	</div>
</html:form>

<!-- a partir de acá es la parte de QUE ODIAS -->
<div id="acaVaElFlash">
	<script>
		if (AC_FL_RunContent == 0) {
			alert("This page requires AC_RunActiveContent.js.");
		} else {
			AC_FL_RunContent( 'codebase', 'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0', 
				'width', '700', 
				'height','500',
				'FlashVars', 'fileToLoad=./hateWordCloudTag.st',
				'src', 'swf/ExpQAQO/qa', 
				'quality', 'best', 
				'pluginspage', 'http://www.macromedia.com/go/getflashplayer', 
				'align', 'middle', 
				'play', 'true', 
				'loop', 'true', 
				'scale', 'showall', 
				'wmode', 'opaque', 
				'devicefont', 'true', 
				'id', 'ACTest', 
				'bgcolor', '#ffffff', 
				'name', 'qa', 
				'menu', 'true', 
				'allowScriptAccess', 'sameDomain', 
				'movie', 'swf/ExpQAQO/qa',
				'salign', '' ); 
				//end AC code 
		} 
	</script>
<!-- img src=""-->
</div>
<html:form method="POST" action="/addHate" >
	<html:hidden name="LoveForm" property="love" value="off"/>
	<div id="controles">
		<div class="myRenglon">
			<div class="myLabel"><img src="images/experiencias/QAQO/tag_queOdias.gif" /><input type="image" src="images/experiencias/QAQO/btn_queAmas.gif" width="50" height="47" /></div>
			<!-- h t m l :  s u b m i t    pr  o p e r t y =  " op  e r a t i o n ">< / h tm l  : s u b m  it -->
			<div class="myLabel"><html:text name="HateForm" property="text" styleClass="qaqoField"/></div>
		</div>
		<div class="myRenglon" style="background-image:url(images/experiencias/QAQO/divison.gif); background-repeat:repeat-x; height:18px;">&nbsp;</div>
		<div class="myRenglon">
			<div id="dato">a personas respondieron a esta pregunta</div>
		</div>
	</div>
</html:form>
<%@ include file="includes/fbShare.jsp" %>
<div id="bottomLayer" class="hide"><!-- --></div>
</body>
</html>
