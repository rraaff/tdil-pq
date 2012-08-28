<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.djmag.model.GalleryCategory"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tdil.djmag.model.ImageInGallery"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.djmag.model.ImageGallery"%>
<%@page import="com.tdil.djmag.model.SectionType"%>
<%@page import="com.tdil.djmag.model.Section"%>
<%@page import="com.tdil.djmag.web.servlets.SelectCountryServlet"%>
<%@page import="com.tdil.djmag.model.deprecated.RankingPosition"%>
<%@page import="com.tdil.djmag.model.deprecated.RankingPositions"%>
<%@page import="com.tdil.djmag.model.RankingNote"%>
<%@page import="com.tdil.djmag.model.NoteImage"%>
<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%
String country = request.getParameter("country");
String categoryId = request.getParameter("c");
if (session == null || session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN) == null) {
	SelectCountryServlet.initForCountry(request, country, "");
} 
	PublicHomeBean publicHomeBean = (PublicHomeBean)session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN);
	GalleryCategory category = PublicHomeBean.getGalleryCategory(categoryId);
	List<ImageGallery> galleries = publicHomeBean.getGalleriesForCountryAndCategory(categoryId);
	if (category == null || galleries == null) {
		// TODO mando a la home
	} else {
%>
<html>
<head>
<title><%=publicHomeBean.getImageGallerySection().getName() %> > <%=category.getTitle() %></title>
<link href='http://fonts.googleapis.com/css?family=Doppio+One' rel='stylesheet' type='text/css'>
<link href="../../css/style.css" rel="stylesheet" type="text/css"/>
<script src='../../js/jquery-1.7.min.js' type='text/javascript'></script>

<style>
div { /*border:dotted 1px #00FF00;*/ }
#supercontainer {
	width:1010px;
	margin:0 auto;
}
#fakeLiveboxWindow {
	width:1010px;
	background-color: #000;
	float:left;
}
#navBar {
	color:#CCC;
	width:620px;
	height:18px;
	overflow:hidden;
	padding:13px;
	margin-left:13px;
	border-top-width: 1px;
	border-bottom-width: 1px;
	border-top-style: dotted;
	border-bottom-style: dotted;
	border-top-color: #666;
	border-bottom-color: #666;
}
#sectionTitle {
	background-color: #e21e26;
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
	width:1010px;
	height:90px;
	margin-left:auto;
	margin-right:auto;
	padding:0;
}
#fakeLiveboxWindow #left {
	float:left;
	width:687px;
}
#fakeLiveboxWindow #left #note {
	width:654px;
	padding:13px;
}
#fakeLiveboxWindow #left #note h1 {
	font-size:18px;
	color:#e21e26;
	line-height: normal;
	font-weight: bold;
	text-transform: uppercase;
	text-decoration: none;
	margin-top:18px;
	margin-bottom:16px;
}
#fakeLiveboxWindow #left #note #top100LB {
	font-family: Arial, Helvetica, sans-serif;
	width:650px;
	padding:0;
	overflow:auto;
}
#fakeLiveboxWindow #left #note #top100LB #renglonRank {
	width:650px;
	border-bottom:dotted 1px #666;
	float:left;
}
#fakeLiveboxWindow #left #note #top100LB #renglonRank #photo {
	width:78px;
	height:78px;
	background-image:url(../../images/thmbn_default.jpg);
	background-repeat: no-repeat;
	background-position: center center;
	border:solid 1px #CCCCCC;
	margin:8px;
	float: left;
}
#fakeLiveboxWindow #left #note #top100LB #renglonRank #ranked {
	font-size: 13px;
	line-height: 25px;
	font-weight: normal;
	font-variant: normal;
	color: #FFFFFF;
	float: left;
	width: 538px;
	margin-top:8px;
	margin-left:10px;
	overflow: hidden;
}
#fakeLiveboxWindow #left #note #top100LB #renglonRank #ranked .title, #fakeLiveboxWindow #left #note #top100LB #renglonRank #ranked .title a, #fakeLiveboxWindow #left #note #top100LB #renglonRank #ranked .title a:active, #fakeLiveboxWindow #left #note #top100LB #renglonRank #ranked .title a:visited {
	color:#e21e26;
	font-size:13px;
	font-weight:bold;
	width: 520px;
	float: left;
	text-decoration:none;
	overflow:hidden;
}
#fakeLiveboxWindow #left #note #top100LB #renglonRank #ranked .title a:hover {
	text-decoration:underline;
}
#fakeLiveboxWindow #left #note #top100LB #renglonRank #ranked .description, #fakeLiveboxWindow #left #note #top100LB #renglonRank #ranked .description a, #fakeLiveboxWindow #left #note #top100LB #renglonRank #ranked .description a:active, #fakeLiveboxWindow #left #note #top100LB #renglonRank #ranked .description a:visited {
	color:#CCC;
	font-size:11px;
	line-height:16px;
	text-align:justify;
	font-weight:normal;
	width: 530px;
	padding-bottom:13px;
	float: left;
	text-decoration:none;
}
#fakeLiveboxWindow #left #note #top100LB #renglonRank #ranked .description a:hover {
	text-decoration:underline;
}
.vermas, .vermas a, .vermas a:active, .vermas a:visited {
	color:#e21e26;
	font-size:11px;
	font-weight:normal;
	text-decoration:none;
	width: 420px;
	float: left;
}
.vermas a:hover {
	text-decoration:underline;
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
#fakeLiveboxWindow #left #linksBottom {
	border-top:solid 1px #e5e5e5;
	height:50px;
	margin-left:13px;
	margin-right:13px;
	padding-top:13px;
}
#fakeLiveboxWindow #left #linksBottom a {
	color:#e21e26;
	padding:5px;
}
#fakeLiveboxWindow #left #linksBottom #linkHome {
	float:left;
}
</style>
</head>
<body>
<a name="top"></a>
<div id="supercontainer">
	<div id="portaHeader">
		<div id="header">
			<div id="logo"><a href="../../index.jsp"><img src="../../images/logo.png" width="197" height="123" /></a></div>
			<div id="menu">
			<ul>
				<% for (Section section : publicHomeBean.getSectionsForCountry()) { %>
					<li>
						<% if (SectionType.RANKING_100.equals(section.getSectiontype())) { %>
							<a href="../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewRanking.html"><%= section.getName() %></a>
						<% } else { %>
							<% if (SectionType.VIDEOS.equals(section.getSectiontype())) { %>
								<a href="../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewVideos.html"><%= section.getName() %></a>
							<% } else { %>
								<% if (SectionType.IMAGE_GALLERY.equals(section.getSectiontype())) { %>
									<a href="../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewPhotoGalleries.html"><%= section.getName() %></a>
								<% } else { %>
									<a href="../../<%=publicHomeBean.getExternalLink(section)%>"><%= section.getName() %></a>
								<% } %>
							<% } %>
						</li>
					<% }
						} %>
				</ul>
			</div>
		</div>
	</div>
	<% if (publicHomeBean.hasNoteTopBanner()) {%>
		<div id="bannerHeader" align="center"><%=publicHomeBean.getNoteTop().getHtmlcontent() %></div>
	<% } %>
	<div id="fakeLiveboxWindow">
		<div id="sectionTitle"><%=publicHomeBean.getImageGallerySection().getName() %> > <%=category.getTitle() %></div>
		<div id="left">
			<div id="note">
				<div id="top100LB">
					<% for (ImageGallery imageGallery : galleries) { %>
						<div id="renglonRank">
							<!-- PABLO ejemplo de imagen -->
							<div id="photo" style="background:url(../../downloadThumb.st?id=<%=imageGallery.getImageId()%>&width=78&height=78&type=PUBLIC&ext=<%=imageGallery.getImageext()%>); background-repeat:no-repeat; background-position:center center;"><a href="../<%=publicHomeBean.getCountry().getIsoCode2()%>/viewGallery<%=imageGallery.getId()%>.html"><img src="../../images/null.gif" width="78" height="78"></a></div>
							<div id="ranked">
								<span class="title"><a href="../<%=publicHomeBean.getCountry().getIsoCode2()%>/viewGallery<%=imageGallery.getId()%>.html"><%= imageGallery.getTitle()%></a></span>
								<span class="description"><a href="../<%=publicHomeBean.getCountry().getIsoCode2()%>/viewGallery<%=imageGallery.getId()%>.html"><%= imageGallery.getDescription()%></a></span>
							</div>
						</div>
					<% } %>
				</div>
			</div>
			<div id="linksBottom">
				<div id="linkHome"><a href="../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewPhotoGalleries.html">Volver a las galer&iacute;as</a></div>
			</div>
			<div id="navBar">
				<div style="float:left;"><a href="https://twitter.com/share" class="twitter-share-button" data-lang="en">Tweet</a>
					<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="https://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script></div>
				<div style="float:left; margin-left:10px; margin-top:1px;">
					<a href="javascript:facebookShare('DJMAGLA | Sitio oficial | ' + document.title ,'Comparti','www.djmagla.com/',location.href);" title="Facebook">
						<img src="../../images/buttons/compartir_facebook.gif" width="82" height="18">
					</a>
				</div>
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
<%@ include file="../includes/rankingFooter.jsp" %>
 <%@ include file="../../../includes/fbShare.jspf" %>
</body>
</html>
<% }
 %>