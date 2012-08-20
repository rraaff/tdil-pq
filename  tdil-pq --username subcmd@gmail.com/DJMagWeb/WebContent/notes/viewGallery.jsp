<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.djmag.model.GalleryCategory"%>
<%@page import="com.tdil.djmag.model.SectionType"%>
<%@page import="com.tdil.djmag.model.Section"%>
<%@page import="com.tdil.djmag.model.ImageInGallery"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.djmag.model.ImageGallery"%>
<%@page import="com.tdil.djmag.web.servlets.SelectCountryServlet"%>
<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%
String galleryId = request.getParameter("g");
String pageNumber = request.getParameter("p");
String country = request.getParameter("country");
if (session == null || session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN) == null) {
	SelectCountryServlet.initForCountry(request, country, "");
} 
	PublicHomeBean publicHomeBean = (PublicHomeBean)session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN);
	ImageGallery imageGallery = publicHomeBean.getImageGallery(galleryId);
	List<ImageInGallery> photos = publicHomeBean.getGalleryPhotos(imageGallery);
	GalleryCategory category = null;
	if (!imageGallery.getCategoryId().equals(0)) {
		category = PublicHomeBean.getGalleryCategory(String.valueOf(imageGallery.getCategoryId()));
	}
%>
<html lang="en-us">
<head>
<meta charset="utf-8">
<title><%=imageGallery.getTitle()%></title>
<link rel="stylesheet" href="../../css/lightbox.css" type="text/css" media="screen" />
<link href='http://fonts.googleapis.com/css?family=Doppio+One' rel='stylesheet' type='text/css'>
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<script src="../../js/jquery-1.7.min.js"></script>
<script src="../../js/lightbox.js"></script>
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
	color:#5b5b5b;
	width:660px;
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
	width:728px;
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
	width:680px;
	padding:0px;
	overflow:hidden;
}
#fakeLiveboxWindow #left #note #top100LB {
	font-family: Arial, Helvetica, sans-serif;
	width:680px;
	padding:0;
	overflow:auto;
}
#fakeLiveboxWindow #left #note #top100LB #galleryThmbnail {
	width:198px;
	height:120px;
	margin:12px;
	float:left;
}/*
#fakeLiveboxWindow #left #note #fullText {
	font-size: 12px;
	line-height: 18px;
	font-weight: normal;
	text-align:justify;
	color: #333333;
	margin-top:20px;
	padding-right: 20px;
}*/
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
		<div id="sectionTitle"><%=publicHomeBean.getImageGallerySection().getName() %><% if (category != null) { %> > <%=category.getTitle()%><% } %> > <%=imageGallery.getTitle()%></div>
		<div id="left">
			<div id="note">
				<div id="top100LB">
					<% for (ImageInGallery gallery : photos ) { %>
						<div id="galleryThmbnail">
						  <a href="../../download.st?id=<%=gallery.getImageId()%>&type=PUBLIC&ext=<%=gallery.getImageext()%>" rel="lightbox[gal]" title="<%=imageGallery.getTitle()%>"><img src="../../download.st?id=<%=gallery.getImageId()%>&type=PUBLIC&ext=<%=gallery.getImageext()%>" height="120" width="198" alt="Plants: image 1 0f 4 thumb" /></a>
						</div>
					<% } %>
				</div>
			</div>
			<div id="linksBottom">
				<% if (imageGallery.getCategoryId().equals(0)) { %>
					<div id="linkHome"><a href="../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewPhotoGalleries.html">Volver a las galer&iacute;as</a></div>
				<% } else { %>
					<div id="linkHome"><a href="../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewCategory<%=imageGallery.getCategoryId()%>.html">Volver a las galer&iacute;as</a></div>
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
<%@ include file="../includes/rankingFooter.jsp" %>
<%@ include file="../../../includes/fbShare.jspf" %>
</body>
</html>
