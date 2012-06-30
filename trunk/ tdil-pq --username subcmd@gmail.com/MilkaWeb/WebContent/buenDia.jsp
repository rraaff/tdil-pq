<%@page import="com.tdil.milka.model.valueobjects.CreationDateHelper"%>
<%@page import="com.tdil.milka.model.valueobjects.GoodMorningValueObject"%>
<%@page import="com.tdil.milka.web.GoodMorningUtils"%>
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
<title>Milka.com.ar | Sitio Oficial | Experiencia Buen Dia</title>
<% 
	if (!"true".equals(request.getParameter("dnc"))) {
		MeltButton.incrementCounter(MeltButton.BUEN_DIA_RENDER);
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
<script type='text/javascript' src='./js/scrollpagination.js'></script>
<script>
$(document).ready(
	function(){
		$("div[id^='mb-']").each(function(indice,valor) {
		   $(valor).meltbutton();
		});
		<%@ include file="includes/buenDiaReady.jspf" %>

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

		$( "#a1" ).hover(function() {
			$( "#a1data" ).fadeIn();
		});
		$( "#a1" ).mouseleave(function() {
			$( "#a1data" ).fadeOut();
		});
		
	}
	
);

function altaExperiencia() {
	$window = $(window);
    var top = ($window.height() / 2) - ($( "#altalayer" ).height() / 2);
    var left = ($window.width() / 2) - ($( "#altalayer" ).width() / 2);
	$("input[name='authorBean.name']").attr('value', '');
	$("input[name='authorBean.email']").attr('value', '');
	$("input[name='title']").attr('value', '');
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
	$("input[name='title']").attr('value', '');
	$("input[name='authorBean.acceptPolitics']").attr('checked', false);
	$("textarea[name='text']").attr('value', '');
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
	font-family:Georgia, "Times New Roman", Times, serif;
	color: #000;
}
div { /* border:dotted 1px #00CC33; */ }
#altalayer {
	color:#8c7bb5;
	width:280px;
	height:300px;
	background-color:#e9e9e9;
	padding:25px;
	
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
}
#graciasporsubir, #erroralta {
	color:#8c7bb5;
	background-color:#e9e9e9;
	width:230px;
	padding:15px;
	
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
}
#renglon {
	height: 25px;
	margin-bottom:10px;
	float:left;
}
.widthData { width:60px; }
#buttonHolder {
	border:none;
	height: 82px;
	width: 280px;
	float:left;
}
.normalField {
	font-family:"Trebuchet MS", Arial, sans-serif;
	width:200px;
	height:22px;
	line-height:22px;
	border: solid 1px #8c7bb5;
}
.normalTextArea {
	width:200px;
	height:100px;
}
/*   */
h1, h2, h3, h4 { font-family:Georgia, "Times New Roman", Times, serif }
h2 {
/*	font-weight:400;
	font-size:13px;
	padding-bottom:5px;
	padding-top:5px;*/
}
#header {
	background-image: url(images/experiencias/buenDia/header.gif);
	background-repeat: no-repeat;
	height: 186px;
	width: 948px;
	margin-top: 0px;
	margin-right: auto;
	margin-bottom: 10px;
	margin-left: auto;
	background-position: center bottom;
}
#commands {
	width:948px;
	height:44px;
	margin:0 auto 10px auto;
}
#upload {
	color:#fc767b;
	width:50%;
	float:left;
}
#upload img {
	margin:5px;
}
#paginator {
	width:50%;
	float:right;
	padding-top:15px;
	text-align: right;
	color:#fc767b;
}
#paginator a, #paginator a:active, #paginator a:visited, #upload a, #upload a:active, #upload a:visited {
	color:#fc767b;
}
#paginator a:hover, #upload a:hover {
	color:#d14349;
	text-decoration:underline;
}
#pageBody {
	width: 948px;
	margin: 0px auto;
}
.basecolor { background-color:#C8E9EE; }
.spaceR { margin-right:20px; }
.spaceB { margin-bottom:20px; }
.floater { float:left; }
#a1 {
	width:464px;
	height:366px;
}
#c1 {
	width:222px;
	height:173px;
}
#d1 {
	width:222px;
	height:173px;
}
#c2 {
	width:222px;
	height:173px;
}
#d2 {
	width:222px;
	height:173px;
}
#a34 {
	width:222px;
	height:366px;
}
#a3 {
	width:222px;
	height:173px;
}
#a4 {
	width:222px;
	height:173px;
}
#b3 {
	width:464px;
	height:366px;
}
#d3 {
	width:222px;
	height:173px;
}
#d4 {
	width:222px;
	height:173px;
}
#a5 {
	width:222px;
	height:173px;
}
#b5 {
	width:222px;
	height:173px;
}
#c5 {
	width:222px;
	height:173px;
}
#d5 {
	width:222px;
	height:173px;
}
#a1data {
	background-image: url(images/experiencias/buenDia/bgData.png);
	background-repeat: repeat;
	width: 100%;
	color:#FFFFFF;
	font-size:16px;
	overflow:hidden;
}
.dedicatoria {
	width:100%;
	margin-left:20px;
	margin-bottom:5px;
}
.usuarioFecha {
	width:100%;
	margin:20px;
	margin-top:0px;
	font-family: Arial, Helvetica, sans-serif;
	color: #d8f7ff;
	font-size:12px;
}
#socialInBlock {
	width:40px;
	height:17px;
	float:right;
	margin-top:10px;
	margin-right:10px;
}
#socialInBlock img { margin-left:3px; }
-->
</style>
</head>

<body>
<% int barClickCounter = MeltButton.BUEN_DIA_COUNTER; 
	
%>
<%
int totalItems = GoodMorningUtils.getGoodMorningCount();
int pageNumber = PaginationUtils.parsePageParam(request.getParameter("pn")); 
List<Integer> list = PaginationUtils.getPages(totalItems, pageNumber, GoodMorningUtils.PAGE_SIZE, 1);
int first = PaginationUtils.first(list);
int last = PaginationUtils.last(list);
SearchPage<GoodMorningValueObject> mailPage = GoodMorningUtils.getPage(pageNumber);
int linkId = 0;
if (lnk != null && !StringUtils.isEmpty(lnk)) {
	linkId = Integer.valueOf(lnk);
	GoodMorningUtils.setFirst(mailPage, linkId);
}
GoodMorningValueObject goodMorningValueObject = null;
%>
<div id="floater">
	<%@ include file="includes/barraExperiencias.jsp" %>
</div>
<div id="flashin">
	<div id="header"></div>
	<div id="commands">
		<div id="upload"><img src="images/experiencias/buenDia/webcamIcon.gif" width="33" height="34" align="absmiddle" /><a href="javascript:altaExperiencia()" style="margin-top:5px;">Sub&iacute; tu BUEN D&Iacute;A</a></div>
		<div id="paginator">
			<% if (first != pageNumber) { %><a href="buenDia.jsp?pn=<%=pageNumber - 1%>&dnc=true">< P&aacute;gina anterior</a><% } else {%>< P&aacute;gina anterior<%} %> | <% if (last != pageNumber) { %><a href="buenDia.jsp?pn=<%=pageNumber + 1%>&dnc=true">Siguiente p&aacute;gina ></a><% } else {%>Siguiente p&aacute;gina ><%} %></div>
	</div>
	<div id="pageBody">
		<% goodMorningValueObject = mailPage.getItemAt(0); %>
		<% if (goodMorningValueObject != null) { %>
			<div id="a1" class="basecolor floater spaceR spaceB" style="background-image:url(./downloadThumb.st?id=<%=goodMorningValueObject.getIdApprovedData()%>&width=464&height=366&type=PUBLIC&ext=<%=goodMorningValueObject.getExtApprovedData()%>);">
				<div id="a1data" style="display: none;">
					<!-- yo me copié esto de la barra de experiencias, pero lo que debería compartir con estos botones es la imagen que estás viendo -->
					<div id="socialInBlock"><a href="javascript:facebookShare('Milka Argentina | Sitio oficial | ' + document.title ,'Hay tanta ternura para compartir','www.milka.com.ar/',location.href);" title="Facebook"><img src="images/barra/facebook.png" alt="Facebook" width="17" height="17" /></a><a href="javascript:window.open('http://twitter.com/home?status=' + encodeURIComponent(document.title + ' | ') + encodeURIComponent(location.href)); return false;" title="Compartir en Twitter"><img src="images/barra/twitter.png" width="17" height="17" alt="Twitter" /></a></div>
					<span class="dedicatoria floater"><%=goodMorningValueObject.getDescription()%></span>
					<span class="usuarioFecha floater"><%=goodMorningValueObject.getAuthorValueObject().getName()%> - <%=CreationDateHelper.getDateFull(goodMorningValueObject.getCreationdate()) %></span>
				</div>
			</div>
		<% } else { %>
			<div id="a1" class="basecolor floater spaceR spaceB"></div>
		<% } %>
		<div id="c1" class="basecolor floater spaceR spaceB">
			<div id="socialInBlock"><a href="javascript:facebookShare('Milka Argentina | Sitio oficial | ' + document.title ,'Hay tanta ternura para compartir','www.milka.com.ar/',location.href);" title="Facebook"><img src="images/barra/facebook.png" alt="Facebook" width="17" height="17" /></a><a href="javascript:window.open('http://twitter.com/home?status=' + encodeURIComponent(document.title + ' | ') + encodeURIComponent(location.href)); return false;" title="Compartir en Twitter"><img src="images/barra/twitter.png" width="17" height="17" alt="Twitter" /></a></div>
			<span class="dedicatoria floater">Dedicatoria que postea el usuario</span>
			<span class="usuarioFecha floater">Nombre del usuario - fecha del upload en un formato 28 de Junio de 2012</span>
		</div>
		<div id="d1" class="basecolor floater spaceB">D1</div>
		<div id="c2" class="basecolor floater spaceR">C2</div>
		<div id="d2" class="basecolor floater spaceB">D2</div>
		<div id="a34" class="floater spaceR spaceB">
			<div id="a3" class="basecolor floater spaceB">A3</div>
			<div id="a4" class="basecolor floater">A4</div>
		</div>
		<div id="b3" class="basecolor floater spaceR spaceB">B3</div>
		<div id="d3" class="basecolor floater spaceB ">D3</div>
		<div id="d4" class="basecolor floater">D4</div>
		<div id="a5" class="basecolor floater spaceR">A5</div>
		<div id="b5" class="basecolor floater spaceR">B5</div>
		<div id="c5" class="basecolor floater spaceR">C5</div>
		<div id="d5" class="basecolor floater">D5</div>
		<div class="floater" style="width:948px; height:20px; border-bottom:dashed 1px #000000;"></div>
		<div class="floater" style="width:948px; height:40px;"></div>
	</div>
</div>
<%@ include file="includes/fbShare.jsp" %>
<div id="bottomLayer" class="hide"><!-- --></div>
<%@ include file="includes/buenDiaDialogs.jspf" %>
</body>
</html>
