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
<title>Milka.com.ar | Sitio Oficial | Experiencia Postits</title>
<% 
	if (!"true".equals(request.getParameter("dnc"))) {
		MeltButton.incrementCounter(MeltButton.CARTAS_DE_PADRES_A_HIJOS_RENDER);
	}
	String lnk = StringUtils.isEmpty(request.getParameter("lnk")) ? "" : request.getParameter("lnk");
	boolean limitCookie = "true".equals(SystemPropertyUtils.getSystemPropertValue(SystemPropertiesKeys.LIMIT_COOKIE));
%>
<%
	String nextPage = "cartasDeHijosAPadres.jsp";
	String prevPage = "apodosDeAmor.jsp";
%>
<%@ include file="includes/head.jsp" %>
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='./js/jquery.cookie.js'></script>
<script type='text/javascript' src='./js/scrollpagination.js'></script>
<link rel="stylesheet" href="./css/lightbox.css" type="text/css" media="screen" />
<script src="./js/lightbox-melt.js"></script>
<% int barClickCounter = MeltButton.CARTAS_DE_PADRES_A_HIJOS_COUNTER; 
	
%>
<%
int totalItems = MailToChildUtils.getMailToChildCount();
int pageNumber = PaginationUtils.parsePageParam(request.getParameter("pn")); 
List<Integer> list = PaginationUtils.getPages(totalItems, pageNumber, MailToChildUtils.PAGE_SIZE, 1);
int first = PaginationUtils.first(list);
int last = PaginationUtils.last(list);
SearchPage<MailToChildValueObject> mailPage = MailToChildUtils.getPage(0);
int linkId = 0;
if (lnk != null && !StringUtils.isEmpty(lnk)) {
	linkId = Integer.valueOf(lnk);
	MailToChildUtils.setFirst(mailPage, linkId);
}
%>
<script>
$(document).ready(
	function(){
		$("div[id^='mb-']").each(function(indice,valor) {
		   $(valor).meltbutton();
		});
		<%@ include file="includes/cartaDePadreAHijoReady.jspf" %>

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
		$( "#closeyaparticipaste" ).click(function() {
			$( "#yaparticipaste" ).fadeOut();
		});
		
		<% 	int extraChild = 1;
			if (totalItems > 10) { /*Solo activo el scroll si tengo mas de lo que muestro*/%>
		$('#pageLeft').scrollPagination({
			'contentPage': 'cartasDePadresAHijosPage.jsp', // the page where you are searching for results
			'contentData': {items: 10}, // you can pass the children().size() to know where is the pagination
			'scrollTarget': $(window), // who gonna scroll? in this example, the full window
			'heightOffset': 10, // how many pixels before reaching end of the page would loading start? positives numbers only please
			'beforeLoad': function(){ // before load, some function, maybe display a preloader div
				$('#loading').fadeIn();	
			},
			'afterLoad': function(elementsLoaded){ // after loading, some function to animate results and hide a preloader div
				 $('#loading').fadeOut();
				 var i = 0;
				 $(elementsLoaded).fadeInWithDelay();
				 $('#pageLeft').contentData = {pageStart: $('#pageLeft').children().size()};
				 if ($('#pageLeft').children().size() - <%=extraChild%> >= <%=totalItems%>){ // if more than total data
				 	//$('#nomoreresults').fadeIn();
					$('#pageLeft').stopScrollPagination();
				 }
			}
		});
		<% } %>
		
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

function altaExperiencia() {
	<% if (limitCookie) { %>
		if ($.cookie('cdpah')) {
			$window = $(window);
		    var top = ($window.height() / 2) - ($( "#yaparticipaste" ).height() / 2);
		    var left = ($window.width() / 2) - ($( "#yaparticipaste" ).width() / 2);
			$( "#yaparticipaste" ).css({
				position: 'absolute',
		        top: top + 'px',
		        left: left + 'px'
		      }).fadeIn(500);
			return;
		}
	<% } %>
	$window = $(window);
    var top = ($window.height() / 2) - ($( "#altalayer" ).height() / 2);
    var left = ($window.width() / 2) - ($( "#altalayer" ).width() / 2);
	$("input[name='authorBean.name']").attr('value', '');
	$("input[name='authorBean.email']").attr('value', '');
	$("input[name='title']").attr('value', '');
	$("input[name='authorBean.acceptPolitics']").attr('checked', false);
	$("textarea[name='description']").attr('value', '');
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
	$("textarea[name='description']").attr('value', '');
}

function postUpload(data) {
	if (data.result == 'OK') {
		clearData();
		$.cookie('cdpah', "set", { expires: 1, path: "/" });
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
	font-family: 'Oswald', sans-serif;
	color: #FFFFFF;
}
div {/* border:dotted 1px #00CC33; */}
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
h1, h2, h3, h4 { font-family: 'Oswald', sans-serif; color:#806bb3; }
h2 {
	font-weight:400;
	font-size:13px;
	padding-bottom:5px;
	padding-top:5px;
}
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
	padding-bottom:20px;
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
	color:#000000;
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
#moduleContent #image {
	width:415px;
	height:300px;
	margin-left:68px;
	float:left;
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
	color:#000000;
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
</style>

</head>

<body>
<div id="floater">
	<%@ include file="includes/barraExperiencias.jsp" %>
</div>
<div id="flashin">
	<div id="header"></div>
	<div id="pageBody">
		<div id="pageLeft">
			<%  int linkAnchor = 0;
				for (MailToChildValueObject mtc : mailPage.getPage()) { %>
				<div id="moduleContent">
					<div id="date"><%=mtc.getDate()%><br/><%=mtc.getMonth()%></div>
					<h1 id="anchor-<%=linkAnchor++%>"><%=mtc.getAuthorValueObject().getName()%></h1>
					<p><%=mtc.getDescription()%></p>
					<div id="image" style="background-image:url(./downloadThumb.st?id=<%=mtc.getIdApprovedData()%>&width=415&height=415&type=PUBLIC&ext=<%=mtc.getExtApprovedData()%>); background-repeat:no-repeat;"><a href="./downloadThumb.st?id=<%=mtc.getIdApprovedData()%>&width=800&height=600&type=PUBLIC&ext=<%=mtc.getExtApprovedData()%>" title="<%=mtc.getAuthorValueObject().getName()%>" id="lk-<%=mtc.getIdClickCounter()%>" rel="lightbox" button="<%=mtc.getIdClickCounter()%>-<%=MeltButton.meltButtonCount(mtc.getIdClickCounter())%>" class="activo"><img src="images/null.gif" width="415" height="300" /></a></div>
				</div>
			<% } %>
			<% if (!mailPage.isHasNext()) { %>
				<div id="moduleContent">
					<h2 style="padding-left:230px; padding-bottom:0px; margin-bottom:0px;">No hay mas datos</h1>
				</div>
			<% } %>
			<!-- test -->
		</div>
		    <div class="loading" id="loading">Wait a moment... it's loading!</div>
		<div id="pageRight">
			<div id="blockLoader">
				<img src="images/experiencias/padresAHijos/webcam.gif" width="61" height="60" />
				<h2>CARGAR UNA CARTA</h2>
				<a href="javascript:altaExperiencia()">Adjunta una imagen con un mensaje para tu hijo</a>
			</div>
			<h2>&Uacute;LTIMAS ENTRADAS</h2>
			<div id="lastEntriesNames">
				<%  int linkAnchorSource = 0; 
					for (MailToChildValueObject mtc : mailPage.getPage()) { %>
					<a href="#anchor-<%=linkAnchorSource++%>"><%=mtc.getAuthorValueObject().getName()%></a>
				<% } %>
			</div>
			<div id="entryNumber"><span class="numero"><%=totalItems%></span>&nbsp;&nbsp;ENTRADAS</div>
		</div>
	</div>
</div>
<%@ include file="includes/fbShare.jsp" %>
<div id="bottomLayer" class="hide"><!-- --></div>
<%@ include file="includes/cartaDePadreAHijoDialogs.jspf" %>
</body>
</html>
