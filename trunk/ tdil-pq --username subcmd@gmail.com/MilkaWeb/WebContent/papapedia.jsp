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
<title>Milka.com.ar | Sitio Oficial | Experiencia Papapedia</title>
<% 
	if (!"true".equals(request.getParameter("dnc"))) {
		MeltButton.incrementCounter(MeltButton.PAPAPEDIA_RENDER);
	}
	String lnk = StringUtils.isEmpty(request.getParameter("lnk")) ? "" : request.getParameter("lnk");
%>
<%
	String nextPage = "finalesDeEmail.jsp";
	String prevPage = "cartasDeHijosAPadres.jsp";
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
	}
	
);
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
<style>
<!-- 
body {
	font-family: Georgia, "Times New Roman", Times, serif;
}
#header {
	background-image: url(images/experiencias/papapedia/header.gif);
	background-repeat: no-repeat;
	background-position: center top;
	width:950px;
	height:227px;
	margin:0 auto;
	margin-top:20px;
}
#content h1 {
	font-family: Georgia, "Times New Roman", Times, serif;
	font-size: 22px;
	font-style: italic;
	color: #FFFFFF;
	padding-top:16px;
	margin-left:16px;
}
#insertContentBlock {
	background-color: #f2583c;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	width:606px;
	height:98px;
	margin-right: auto;
	margin-left: auto;
	margin-top:3px;
	margin-bottom:8px;
}
#insertContentBlock #complete {
	margin-left:16px;
	margin-right:16px;
	margin-top:3px;
	width:100%;
	height:33px;
}
#insertContentBlock #complete input[type="text"], #insertContentBlock #complete input[type="password"], #insertContentBlock #complete select {
	font-family: Georgia, "Times New Roman", Times, serif;
	font-size:14px;
	font-style: italic;
	color:#5a5a5a;
	float:left;
	margin:0;
	padding:2px;
	line-height: normal;
	width:480px;
	height:23px;
	border: solid 1px #999999;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border-radius: 2px;
}
input[type="button"], input[type="submit"] {
	font-family: Georgia, "Times New Roman", Times, serif;
	font-size:15px;
	color: #FFFFFF;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: none;
	padding-top: 5px;
	padding-right: 11px;
	padding-bottom: 5px;
	padding-left: 11px;
	margin: 5px;
	margin-top:0px;
	float:left;
	cursor:hand;
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
	border-radius: 6px;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.9);
	background-color: #1c1c1c;
}
#counter {
	color:#f2583c;
	size:12px;
	height: 20px;
	width: 606px;
	margin-top: 8px;
	margin-right: auto;
	margin-bottom: 8px;
	margin-left: auto;
}
.line {
	height: 2px;
	width: 606px;
	margin-top: 8px;
	margin-right: auto;
	margin-bottom: 8px;
	margin-left: auto;
	border-bottom-width: 1px;
	border-bottom-style: dotted;
	border-bottom-color: #999999;
}
.texto {
	color:#5a5a5a;
	font-size:12px;
	width: 606px;
	padding-top: 14px;
	margin-right: auto;
	padding-bottom: 14px;
	margin-left: auto;
	border-bottom-width: 1px;
	border-bottom-style: dotted;
	border-bottom-color: #999999;
}
.texto a {
	color:#5a5a5a;
	text-decoration:underline;
}
.texto a:hover {
	color:#f2583c;
	text-decoration:underline;
}
.resaltado {
	font-weight:bold;
}
#paginado {
	width: 606px;
	height:25px;
	margin-top: 14px;
	margin-right: auto;
	margin-bottom: 68px;
	margin-left: auto;
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #f2583c;
}
#paginado #previuos {
	width:250px;
	height:20px;
	padding-left:10px;
	float:left;
	background-image: url(images/experiencias/papapedia/prev.gif);
	background-repeat: no-repeat;
	background-position: left top;
}
#paginado #previuos a {
	color:#f2583c;
}
#paginado #next {
	width:250px;
	height:20px;
	padding-right:10px;
	text-align:right;
	float:right;
	background-image: url(images/experiencias/papapedia/next.gif);
	background-repeat: no-repeat;
	background-position: right top;
}
#paginado #next a {
	color:#f2583c;
}
#backToTop {
	width:606px;
	height:60px;
	margin:0 auto;
	line-height:60px;
	text-align:right;
}
#backToTop a, #backToTop a:active, #backToTop a:visited {
	color:#f2583c;
	text-decoration:none;
}
#backToTop a:hover {
	text-decoration:underline;
}
-->
</style>
</head>

<body>
<a name="top" id="top"></a>
<%
List<WallWritting> papapediaList = PapapediaUtils.getPapapediaList();
int totalItems = papapediaList.size();
int linkId = 0;
if (lnk != null && !StringUtils.isEmpty(lnk)) {
	linkId = Integer.valueOf(lnk);
}
%>
<% int barClickCounter = MeltButton.PAPAPEDIA_COUNTER; %>
<div id="floater">
	<%@ include file="includes/barraExperiencias.jsp" %>
</div>
<div id="flashin">
	<div id="content">
		<div id="header"></div>
		<div id="cuerpoCentral">
			<p style="width:440px; margin:0 auto; padding-bottom:20px; text-align:center; font-size:12px; color:#626262;">Un padre son muchas cosas en una sola. No importa si estuvo o est&aacute;, si vive cerca o lejos, si habla mucho o poco. Un padre es un &aacute;rbol, una roca y un sol.<br /><span style="color:#f2583c;">¿Qu&eacute; es para vos?</span></p>
			<div id="insertContentBlock">
				<h1>Papa es...</h1>
				<div id="complete">
					<html:form method="POST" action="/addPapapedia">
						<input type="hidden" name="dnc" value="true"/>
						<html:text name="PapapediaForm" property="text" styleClass="width180"/>
						<html:submit property="operation">Enviar</html:submit>
					</html:form>	
				</div>
			</div>
			<div class="line"></div>
			<div id="counter"><%=totalItems%> Definiciones</div>
			<% for (WallWritting ww : papapediaList) { %>
<% if (ww.getId() == linkId) { %>
					<div class="texto resaltado">
				<% } else { %>
					<div class="texto">
				<% } %>
					<% if (!StringUtils.isEmpty(ww.getUrlLink())) { %><a href="<%=ww.getUrlLink()%>" target="<%=ww.getUrlTarget()%>"><% } %>
					<%=ww.getOriginaltext()%>
					<% if (!StringUtils.isEmpty(ww.getUrlLink())) { %></a><% } %>
				</div>
			<% } %>
			<div id="backToTop"><a href="#top">Subir</a></div>
		</div>
	</div>
</div>
	<%@ include file="includes/fbShare.jsp" %>
</body>
</html>