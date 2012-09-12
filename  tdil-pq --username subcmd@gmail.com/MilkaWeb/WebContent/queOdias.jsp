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
			<% if ("1".equals(request.getParameter("odias"))) { %>
				$.cookie('odias', "set", { expires: 1, path: "/" });
			<% } %>
		<% } %>

  	  	if ($.cookie('odias')) {
  	  	// deshabilito el alta de odias
	  	}
		
		$( "#closegracias" ).click(function() {
			$( "#graciasporsubir" ).fadeOut();
			$( "#bottomLayer" ).fadeOut();
		});
		$( "#cancelaltahate" ).click(function() {
			$( "#altahate" ).fadeOut();
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
-->
</style>
</head>
<body>
<div id="floater">
	<%@ include file="includes/barraExperiencias.jsp" %>
</div>
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
				'src', 'swf/ExpQAQO/qo', 
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
				'name', 'qo', 
				'menu', 'true', 
				'allowScriptAccess', 'sameDomain', 
				'movie', 'swf/ExpQAQO/qo',
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