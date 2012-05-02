<%@page import="com.tdil.djmag.model.NoteImage"%>
<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%
String country = request.getParameter("country");
String date = request.getParameter("date");
String webTitle = request.getParameter("webTitle");
if (session == null || session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN) == null) {
	// todo aca primero seteo el pais, luego redirecciono
	String theURL = "../../../selectCountry.st?iso_code_2="+ country + "&action=viewNote&date=" + date + "&webTitle=" +webTitle;
	theURL = response.encodeRedirectURL(theURL);
	response.sendRedirect(theURL);
} else {
	PublicHomeBean publicHomeBean = (PublicHomeBean)session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN);
	NoteValueObject noteToShow = publicHomeBean.getNoteByParams(country, date, webTitle);
	if (noteToShow == null) {
		
	} else {
		String content = PublicHomeBean.getNoteContent(noteToShow);
%>
<html>
<head>
<link href="../../../css/style.css" rel="stylesheet" type="text/css">
<script src='../../../js/jquery-1.7.min.js' type='text/javascript'></script>
<script src="../../../js/jquery.nivo.slider.pack.js" type="text/javascript"></script>
<link rel="stylesheet" href="../../../css/nivo-slider.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../../../css/nivo_theme/default.css" type="text/css" media="screen" />
<style>
/*
div {
	border:dotted 1px #00FF00;
}*/
#fakeLiveboxWindow {
	width:960px;
	height:700px;
	margin-left:auto;
	margin-right:auto;
/*	margin-top:50px;
	border:solid 4px #545454;
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
	border-radius: 6px;*/
}
#navBar {
	width:934px;
	height:18px;
	overflow:hidden;
	padding:13px;
}
#navBar #prevNext {
	float:left;
	width:67px;
	height:28px;
}
#navBar #prevNext #prev {
	background-image:url(../../../images/buttons/navBar_prev.gif);
	width:32px;
	height:28px;
	float:left;
}
#navBar #prevNext #prev:hover {
	background-image:url(../../../images/buttons/navBar_prev_over.gif);
	cursor:hand;
}
#navBar #prevNext #next {
	background-image:url(../../../images/buttons/navBar_next.gif);
	width:35px;
	height:28px;
	float:right;
}
#navBar #prevNext #next:hover {
	background-image:url(../../../images/buttons/navBar_next_over.gif);
	cursor:hand;
}
#navBar #closeButton {
	float:right;
	background-image:url(../../../images/buttons/navBar_close.gif);
	width:37px;
	height:28px;
}
#navBar #closeButton:hover {
	background-image:url(../../../images/buttons/navBar_close_over.gif);
}
#fakeLiveboxWindow #bannerHeader {
	width:728px;
	height:90px;
	margin-left:auto;
	margin-right:auto;
	padding:0;
}
#fakeLiveboxWindow #left {
	float:left;
	width:670px;
	height:500px;
}
#fakeLiveboxWindow #left #note {
	background-color:#FFFFFF;
	width:606px;
	height:446px;
	margin:20px;
	padding:12px;
	overflow:scroll;
}
#fakeLiveboxWindow #left #note .date {
	color:#dcdcdc;
	font-weight:700;
	background-color:#525252;
	padding:4px;
	margin-right:auto;
}
#fakeLiveboxWindow #left #note h1 {
	font-size:18px;
	color:#e25237;
	line-height: normal;
	font-weight: bold;
	text-transform: uppercase;
	text-decoration: none;
	margin-top:18px;
	margin-bottom:16px;
}
#fakeLiveboxWindow #left #note #bajada {
	color:#000000;
	font-size: 14px;
	line-height: normal;
	font-weight: bold;
	margin-bottom:20px;
}
#fakeLiveboxWindow #left #note #images {
	border:solid 7px #525252;
	width:585px;
	height:303px;
	margin-left:auto;
	margin-right:auto;
}
#fakeLiveboxWindow #left #note #fullText {
	font-size: 13px;
	line-height: normal;
	font-weight: 700;
	color: #333333;
	margin-top:20px;
}
#fakeLiveboxWindow #right {
	float:right;
	width:280px;
	height:500px;
}
#fakeLiveboxWindow #right #subContent {
	width:252px;
	height:200px;
	margin-left:auto;
	margin-right:auto;
	margin-bottom:20px;
	margin-top:20px;
}
#fakeLiveboxWindow #right #rightBanner {
	width:252px;
	height:252px;
	margin-left:auto;
	margin-right:auto;
}
#fakeLiveboxWindow #social {
	width:920px;
	height:20px;
	overflow:hidden;
	margin-left:auto;
	margin-right:auto;
}
#fakeLiveboxWindow #labels {
	width:920px;
	height:20px;
	overflow:hidden;
	margin-left:auto;
	margin-right:auto;
}
</style>
</head>
<body style="background:#000000; background-image:none;">
<div id="fakeLiveboxWindow">
	<!-- Estructura de la interface -->
	<div id="navBar">
		<!-- div id="prevNext">
			<div id="prev"></div>
			<div id="next"></div>
		</div-->
		<div style="margin-left:50px; width:650px; float:left; overflow:hidden;">
			<a href="javascript:window.open('http://twitter.com/home?status=' + encodeURIComponent('<%= noteToShow.getTitle()%>' + ' ') + encodeURIComponent(location.href)); return false;">
				<img src="images/buttons/tw.gif" border="0"></a>
					
			<div style="float:left;"><a href="https://twitter.com/share" class="twitter-share-button" url="http://www.google.com" data-lang="en">Tweet</a>
				<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
			</div>
			<div id="fb-root" style="float:left;"></div>
			<script>(function(d, s, id) {
			  var js, fjs = d.getElementsByTagName(s)[0];
			  if (d.getElementById(id)) return;
			  js = d.createElement(s); js.id = id;
			  js.src = "//connect.facebook.net/es_LA/all.js#xfbml=1";
			  fjs.parentNode.insertBefore(js, fjs);
			}(document, 'script', 'facebook-jssdk'));</script>
			<div class="fb-like" data-send="false" data-width="450" data-show-faces="true" style="float:left;"></div>
		</div>
		<!--div id="closeButton"></div-->
	</div>
	<% if (publicHomeBean.hasNoteTopBanner()) {%>
		<div id="bannerHeader"><%=publicHomeBean.getNoteTop().getHtmlcontent() %></div>
	<% } %>
	<div id="left">
		<div id="note">
			<span class="date"><%=PublicHomeBean.formatDate(noteToShow.getFromDate()) %></span>
			<h1><%= noteToShow.getTitle()%></h1>
			<div id="bajada"><%= noteToShow.getSummary()%></div>
			<div class="slider-wrapper theme-default">
			    <div class="ribbon"></div>
			    <div id="slider" class="nivoSlider">
				<% /*Generacion de imagenes*/
					for (NoteImage noteImage : noteToShow.getNoteImages()) { %>
			        <img src="../../../download.st?id=<%=noteImage.getId()%>&type=note&ext=<%=noteImage.getExtension()%>" alt="" />
				<% } %>
			    </div>
			</div>
			<div id="fullText"><%= content%></div>
		</div>
	</div>
	<div id="right">
		<div id="subContent"></div>
		<% if (publicHomeBean.hasNoteRightBanner()) {%>
			<div id="rightBanner"><%=publicHomeBean.getNoteRight().getHtmlcontent() %></div>
		<% } %>
	</div>
	<div id="social"></div>
	<!-- fin estructura de la interfase -->
</div>
<!-- cargo el slider -->
	<script type="text/javascript">
    $(window).load(function() {
        $('#slider').nivoSlider({
			effect: 'fold' // Specify sets like: 'fold,fade,sliceDown');
		});
    });
    </script>
</body>
</html>
<% }
} %>