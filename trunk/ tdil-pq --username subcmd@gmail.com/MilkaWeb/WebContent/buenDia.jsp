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

		$( "#a1" ).mouseenter(function() {
			$( "#a1data" ).fadeIn();
		});
		$( "#a1" ).mouseleave(function() {
			$( "#a1data" ).fadeOut();
		});

		$( "#c1" ).mouseenter(function() {
			$( "#c1data" ).fadeIn();
		});
		$( "#c1" ).mouseleave(function() {
			$( "#c1data" ).fadeOut();
		});
		$( "#c2" ).mouseenter(function() {
			$( "#c2data" ).fadeIn();
		});
		$( "#c2" ).mouseleave(function() {
			$( "#c2data" ).fadeOut();
		});
		$( "#d1" ).mouseenter(function() {
			$( "#d1data" ).fadeIn();
		});
		$( "#d1" ).mouseleave(function() {
			$( "#d1data" ).fadeOut();
		});

		$( "#d2" ).mouseenter(function() {
			$( "#d2data" ).fadeIn();
		});
		$( "#d2" ).mouseleave(function() {
			$( "#d2data" ).fadeOut();
		});

		$( "#b3" ).mouseenter(function() {
			$( "#b3data" ).fadeIn();
		});
		$( "#b3" ).mouseleave(function() {
			$( "#b3data" ).fadeOut();
		});

		$( "#a3" ).mouseenter(function() {
			$( "#a3data" ).fadeIn();
		});
		$( "#a3" ).mouseleave(function() {
			$( "#a3data" ).fadeOut();
		});

		$( "#d3" ).mouseenter(function() {
			$( "#d3data" ).fadeIn();
		});
		$( "#d3" ).mouseleave(function() {
			$( "#d3data" ).fadeOut();
		});

		$( "#a4" ).mouseenter(function() {
			$( "#a4data" ).fadeIn();
		});
		$( "#a4" ).mouseleave(function() {
			$( "#a4data" ).fadeOut();
		});

		$( "#d4" ).mouseenter(function() {
			$( "#d4data" ).fadeIn();
		});
		$( "#d4" ).mouseleave(function() {
			$( "#d4data" ).fadeOut();
		});

		$( "#a5" ).mouseenter(function() {
			$( "#a5data" ).fadeIn();
		});
		$( "#a5" ).mouseleave(function() {
			$( "#a5data" ).fadeOut();
		});

		$( "#b5" ).mouseenter(function() {
			$( "#b5data" ).fadeIn();
		});
		$( "#b5" ).mouseleave(function() {
			$( "#b5data" ).fadeOut();
		});

		$( "#c5" ).mouseenter(function() {
			$( "#c5data" ).fadeIn();
		});
		$( "#c5" ).mouseleave(function() {
			$( "#c5data" ).fadeOut();
		});

		$( "#d5" ).mouseenter(function() {
			$( "#d5data" ).fadeIn();
		});
		$( "#d5" ).mouseleave(function() {
			$( "#d5data" ).fadeOut();
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
.data {
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
				<div id="a1data" class="data" style="display: none;">
					<%@ include file="includes/buenDiaBlock.jspf" %>
				</div>
			</div>
		<% } else { %>
			<div id="a1" class="basecolor floater spaceR spaceB"></div>
		<% } %>
		
		<% goodMorningValueObject = mailPage.getItemAt(1); %>
		<% if (goodMorningValueObject != null) { %>
			<div id="c1" class="basecolor floater spaceR spaceB" style="background-image:url(./downloadThumb.st?id=<%=goodMorningValueObject.getIdApprovedData()%>&width=222&height=173&type=PUBLIC&ext=<%=goodMorningValueObject.getExtApprovedData()%>);">
				<div id="c1data" class="data" style="display: none;">
					<%@ include file="includes/buenDiaBlock.jspf" %>
				</div>
			</div>
		<% } else { %>
			<div id="c1" class="basecolor floater spaceR spaceB"></div>
		<% } %>
		
		<% goodMorningValueObject = mailPage.getItemAt(3); %>
		<% if (goodMorningValueObject != null) { %>
			<div id="d1" class="basecolor floater spaceB" style="background-image:url(./downloadThumb.st?id=<%=goodMorningValueObject.getIdApprovedData()%>&width=222&height=173&type=PUBLIC&ext=<%=goodMorningValueObject.getExtApprovedData()%>);">
				<div id="d1data" class="data" style="display: none;">
					<%@ include file="includes/buenDiaBlock.jspf" %>
				</div>
			</div>
		<% } else { %>
			<div id="d1" class="basecolor floater spaceB"></div>
		<% } %>
		
		<% goodMorningValueObject = mailPage.getItemAt(2); %>
		<% if (goodMorningValueObject != null) { %>
			<div id="c2" class="basecolor floater spaceR" style="background-image:url(./downloadThumb.st?id=<%=goodMorningValueObject.getIdApprovedData()%>&width=222&height=173&type=PUBLIC&ext=<%=goodMorningValueObject.getExtApprovedData()%>);">
				<div id="c2data" class="data" style="display: none;">
					<%@ include file="includes/buenDiaBlock.jspf" %>
				</div>
			</div>
		<% } else { %>
			<div id="c2" class="basecolor floater spaceR"></div>
		<% } %>
		
		<% goodMorningValueObject = mailPage.getItemAt(4); %>
		<% if (goodMorningValueObject != null) { %>
			<div id="d2" class="basecolor floater spaceB" style="background-image:url(./downloadThumb.st?id=<%=goodMorningValueObject.getIdApprovedData()%>&width=222&height=173&type=PUBLIC&ext=<%=goodMorningValueObject.getExtApprovedData()%>);">
				<div id="d2data" class="data" style="display: none;">
					<%@ include file="includes/buenDiaBlock.jspf" %>
				</div>
			</div>
		<% } else { %>
			<div id="d2" class="basecolor floater spaceB"></div>
		<% } %>
		
		<div id="a34" class="floater spaceR spaceB">
		
			<% goodMorningValueObject = mailPage.getItemAt(6); %>
			<% if (goodMorningValueObject != null) { %>
				<div id="a3" class="basecolor floater spaceB" style="background-image:url(./downloadThumb.st?id=<%=goodMorningValueObject.getIdApprovedData()%>&width=222&height=173&type=PUBLIC&ext=<%=goodMorningValueObject.getExtApprovedData()%>);">
					<div id="a3data" class="data" style="display: none;">
						<%@ include file="includes/buenDiaBlock.jspf" %>
					</div>
				</div>
			<% } else { %>
				<div id="a3" class="basecolor floater spaceB"></div>
			<% } %>
		
			<% goodMorningValueObject = mailPage.getItemAt(8); %>
			<% if (goodMorningValueObject != null) { %>
				<div id="a4" class="basecolor floater" style="background-image:url(./downloadThumb.st?id=<%=goodMorningValueObject.getIdApprovedData()%>&width=222&height=173&type=PUBLIC&ext=<%=goodMorningValueObject.getExtApprovedData()%>);">
					<div id="a4data" class="data" style="display: none;">
						<%@ include file="includes/buenDiaBlock.jspf" %>
					</div>
				</div>
			<% } else { %>
				<div id="a4" class="basecolor floater"></div>
			<% } %>
		</div>
		
		<% goodMorningValueObject = mailPage.getItemAt(5); %>
		<% if (goodMorningValueObject != null) { %>
			<div id="b3" class="basecolor floater spaceR spaceB" style="background-image:url(./downloadThumb.st?id=<%=goodMorningValueObject.getIdApprovedData()%>&width=464&height=366&type=PUBLIC&ext=<%=goodMorningValueObject.getExtApprovedData()%>);">
				<div id="b3data" class="data" style="display: none;">
					<%@ include file="includes/buenDiaBlock.jspf" %>
				</div>
			</div>
		<% } else { %>
			<div id="b3" class="basecolor floater spaceR spaceB"></div>
		<% } %>
		
		<% goodMorningValueObject = mailPage.getItemAt(7); %>
		<% if (goodMorningValueObject != null) { %>
			<div id="d3" class="basecolor floater spaceB"" style="background-image:url(./downloadThumb.st?id=<%=goodMorningValueObject.getIdApprovedData()%>&width=222&height=173&type=PUBLIC&ext=<%=goodMorningValueObject.getExtApprovedData()%>);">
				<div id="d3data" class="data" style="display: none;">
					<%@ include file="includes/buenDiaBlock.jspf" %>
				</div>
			</div>
		<% } else { %>
			<div id="d3" class="basecolor floater spaceB"></div>
		<% } %>
			
		<% goodMorningValueObject = mailPage.getItemAt(9); %>
		<% if (goodMorningValueObject != null) { %>
			<div id="d4" class="basecolor floater" style="background-image:url(./downloadThumb.st?id=<%=goodMorningValueObject.getIdApprovedData()%>&width=222&height=173&type=PUBLIC&ext=<%=goodMorningValueObject.getExtApprovedData()%>);">
				<div id="d4data" class="data" style="display: none;">
					<%@ include file="includes/buenDiaBlock.jspf" %>
				</div>
			</div>
		<% } else { %>
			<div id="d4" class="basecolor floater"></div>
		<% } %>
		
		<% goodMorningValueObject = mailPage.getItemAt(10); %>
		<% if (goodMorningValueObject != null) { %>
			<div id="a5" class="basecolor floater spaceR" style="background-image:url(./downloadThumb.st?id=<%=goodMorningValueObject.getIdApprovedData()%>&width=222&height=173&type=PUBLIC&ext=<%=goodMorningValueObject.getExtApprovedData()%>);">
				<div id="a5data" class="data" style="display: none;">
					<%@ include file="includes/buenDiaBlock.jspf" %>
				</div>
			</div>
		<% } else { %>
			<div id="a5" class="basecolor floater spaceR"></div>
		<% } %>
		
		<% goodMorningValueObject = mailPage.getItemAt(11); %>
		<% if (goodMorningValueObject != null) { %>
			<div id="b5" class="basecolor floater spaceR" style="background-image:url(./downloadThumb.st?id=<%=goodMorningValueObject.getIdApprovedData()%>&width=222&height=173&type=PUBLIC&ext=<%=goodMorningValueObject.getExtApprovedData()%>);">
				<div id="b5data" class="data" style="display: none;">
					<%@ include file="includes/buenDiaBlock.jspf" %>
				</div>
			</div>
		<% } else { %>
			<div id="b5" class="basecolor floater spaceR"></div>
		<% } %>
		
		<% goodMorningValueObject = mailPage.getItemAt(12); %>
		<% if (goodMorningValueObject != null) { %>
			<div id="c5" class="basecolor floater spaceR" style="background-image:url(./downloadThumb.st?id=<%=goodMorningValueObject.getIdApprovedData()%>&width=222&height=173&type=PUBLIC&ext=<%=goodMorningValueObject.getExtApprovedData()%>);">
				<div id="c5data" class="data" style="display: none;">
					<%@ include file="includes/buenDiaBlock.jspf" %>
				</div>
			</div>
		<% } else { %>
			<div id="c5" class="basecolor floater spaceR"></div>
		<% } %>
		
		<% goodMorningValueObject = mailPage.getItemAt(13); %>
		<% if (goodMorningValueObject != null) { %>
			<div id="d5" class="basecolor floater" style="background-image:url(./downloadThumb.st?id=<%=goodMorningValueObject.getIdApprovedData()%>&width=222&height=173&type=PUBLIC&ext=<%=goodMorningValueObject.getExtApprovedData()%>);">
				<div id="d5data" class="data" style="display: none;">
					<%@ include file="includes/buenDiaBlock.jspf" %>
				</div>
			</div>
		<% } else { %>
			<div id="d5" class="basecolor floater"></div>
		<% } %>
		<div class="floater" style="width:948px; height:20px; border-bottom:dashed 1px #000000;"></div>
		<div class="floater" style="width:948px; height:40px;"></div>
	</div>
</div>
<%@ include file="includes/fbShare.jsp" %>
<div id="bottomLayer" class="hide"><!-- --></div>
<%@ include file="includes/buenDiaDialogs.jspf" %>
</body>
</html>
