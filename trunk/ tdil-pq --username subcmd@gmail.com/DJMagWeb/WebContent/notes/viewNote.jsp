<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.djmag.web.servlets.SelectCountryServlet"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.tdil.djmag.model.SectionType"%>
<%@page import="com.tdil.djmag.model.Country"%>
<%@page import="com.tdil.djmag.model.Section"%>
<%@page import="com.tdil.djmag.model.NoteImage"%>
<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%
String sectionId = request.getParameter("s");
String pageNumber = request.getParameter("p");
String country = request.getParameter("country");
String date = request.getParameter("date");
String webTitle = request.getParameter("webTitle");
if (session == null || session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN) == null) {
	SelectCountryServlet.initForCountry(request, country, "");
} 
	PublicHomeBean publicHomeBean = (PublicHomeBean)session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN);
	Section actualsection = publicHomeBean.getSectionForId(sectionId);
	NoteValueObject noteToShow = publicHomeBean.getNoteByParams(country, date, webTitle);
	if (noteToShow == null) {
		
	} else {
		String content = PublicHomeBean.getNoteContent(noteToShow);
%>
<html>
<head>
<title><%= noteToShow.getTitle()%></title>
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
#supercontainer {
	width:1010px;
	margin:0 auto;
}
#fakeLiveboxWindow {
	width:1010px;
	background-color: #FFFFFF;
	float:left;
}
#navBar {
	color:#5b5b5b;
	width:640px;
	height:18px;
	overflow:hidden;
	padding:13px;
	margin-left:13px;
	border-top-width: 1px;
	border-bottom-width: 1px;
	border-top-style: dotted;
	border-bottom-style: dotted;
	border-top-color: #b5b5b5;
	border-bottom-color: #b5b5b5;
}
#sectionTitle {
	background-color: #e55532;
	font-family: 'Doppio One', sans-serif;
	font-size: 15px;
	font-weight: normal;
	text-transform: uppercase;
	color: #FFFFFF;
	margin:13px;
	padding-bottom:13px;
	padding-left:13px;
	padding-right:13px;
	padding-bottom:10px;
	padding-top:10px;
	height:16px;
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
	width:690px;
}
#fakeLiveboxWindow #left #note {
	background-color:#FFFFFF;
	width:660px;
	padding:13px;
	overflow:hidden;
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
	font-size: 13px;
	line-height: 16px;
	font-weight: bold;
	margin-bottom:20px;
	padding-right: 20px;
}
#fakeLiveboxWindow #left #note #images {
	border:solid 7px #525252;
	width:585px;
	height:303px;
	margin-left:auto;
	margin-right:auto;
}
#fakeLiveboxWindow #left #note #fullText {
	font-size: 12px;
	line-height: 18px;
	font-weight: normal;
	text-align:justify;
	color: #333333;
	margin-top:20px;
	padding-right: 20px;
}
#fakeLiveboxWindow #left #linksBottom {
	height:50px;
	margin-left:13px;
	margin-right:13px;
	padding-top:13px;
}
#fakeLiveboxWindow #left #linksBottom a {
	color:#e25237;
}
#fakeLiveboxWindow #left #linksBottom #linkHome {
	float:left;
}
#fakeLiveboxWindow #left #linksBottom #linkPaging {
	float:right;
}
#fakeLiveboxWindow #right {
	float:left;
	width:286px;
}
#fakeLiveboxWindow #right #rightBanner {
	width:286px;
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
<body>

<a name="top"></a>
<div id="supercontainer">
	<div id="portaHeader">
		<div id="header">
			<div id="logo"><a href="../../../index.jsp"><img src="../../../images/logo.gif" width="197" height="123" /></a></div>
			<div id="menu">
			<ul>
				<% for (Section section : publicHomeBean.getSectionsForCountry()) { %>
					<li>
						<% if (SectionType.RANKING_100.equals(section.getSectiontype())) { %>
							<a href="../../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewRanking.html"><%= section.getName() %></a>
						<% } else { %>
							<% if (SectionType.VIDEOS.equals(section.getSectiontype())) { %>
								<a href="../../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewVideos.html"><%= section.getName() %></a>
							<% } else { %>
								<a href="../../../<%=publicHomeBean.getExternalLink(section)%>"><%= section.getName() %></a>
							<% } %>
						</li>
					<% }
						} %>
					<li><a href="#" style="padding:0; cursor:default;"><img src="../../../images/pronto-top20.gif" width="74"></a></li>
					<li><a href="#" style="padding:0; cursor:default;"><img src="../../../images/pronto-shop.gif" width="62"></a></li>
				</ul>
			</div>
		</div>
	</div>
	<% if (publicHomeBean.hasNoteTopBanner()) {%>
		<div id="bannerHeader" align="center"><%=publicHomeBean.getNoteTop().getHtmlcontent() %></div>
	<% } %>
	<div id="fakeLiveboxWindow">
		<div id="sectionTitle"><%= noteToShow.getTitle()%></div>
		<div id="left">
			<div id="note">
				<div class="slider-wrapper theme-default" style="margin-bottom:20px; background-color:#000000;">
					<div class="ribbon"></div>
					<div id="slider" class="nivoSlider">
						<% /*Generacion de imagenes*/
							for (NoteImage noteImage : noteToShow.getNoteImages()) { %>
							<img src="../../../download.st?id=<%=noteImage.getId()%>&type=note&ext=<%=noteImage.getExtension()%>" alt="" width="654" height="400" />
						<% } %>
					</div>
				</div>
				<span class="date"><%=PublicHomeBean.formatDate(noteToShow.getFromDate()) %></span>
				<h1><%= noteToShow.getTitle()%></h1>
				<div id="bajada"><%= noteToShow.getSummary()%></div>
				<div id="fullText"><%= content%></div>
			</div>
			<div id="navBar">
				<div style="float:left;"><a href="javascript:window.open('https://twitter.com/share?url=' + encodeURIComponent(location.href)); return false;"><img src="../../../images/buttons/sharetw.gif" width="70" height="20" align="absmiddle"></a>
				</div>
				<div style="float:left; margin-left:10px; margin-top:1px;"><a href="javascript:window.open('http://www.facebook.com/sharer.php?u=' + encodeURIComponent(location.href)); return false;"><img src="../../../images/buttons/compartir_facebook.gif" width="82" height="18"></a></div>
			</div>
			<div id="linksBottom">
				<% if (actualsection == null) { %>
					<div id="linkHome"><a href="../../../index.jsp">Volver a la home</a></div>
				<% } else { %>
					<div id="linkPaging"><a href="../../../<%=publicHomeBean.getExternalLink(actualsection)%>?pageNumber=<%=pageNumber %>">Volver a <%=actualsection.getName() %></a></div>
				<% } %>
			</div>
		</div>
		<div id="right">
			<% if (publicHomeBean.hasNoteRightBanner()) {%>
				<div id="rightBanner"><%=publicHomeBean.getNoteRight().getHtmlcontent() %></div>
			<% } %>
			<%@ include file="../includes/homeTwitter.jsp" %>
			<div id="spacer"></div>
			<%@ include file="../includes/homeFacebook.jsp" %>
			<div id="spacer"></div>
		</div>
	</div>
</div>

<%@ include file="../includes/noteFooter.jsp" %>

<!-- cargo el slider -->
	<script type="text/javascript">
    $(document).ready(function(){
        $('#slider').nivoSlider({
			effect: 'fold', // Specify sets like: 'fold,fade,sliceDown');
			pauseTime: 30000
		});
    });
    </script>
</body>
</html>
<% 
} %>