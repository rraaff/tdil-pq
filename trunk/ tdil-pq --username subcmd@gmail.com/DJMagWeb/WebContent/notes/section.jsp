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
String pageNumberParam = request.getParameter("pageNumber");
int pageNumber = PublicHomeBean.parsePageParam(pageNumberParam);
String country = request.getParameter("country");
String sectionId = request.getParameter("sectionId");
if (session == null || session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN) == null) {
	// todo aca primero seteo el pais, luego redirecciono
	String theURL = "../../../selectCountry.st?iso_code_2="+ country + "&action=section&=" + sectionId;
	theURL = response.encodeRedirectURL(theURL);
	response.sendRedirect(theURL);
} else {
	PublicHomeBean publicHomeBean = (PublicHomeBean)session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN);
	Section section = publicHomeBean.getSectionForId(sectionId);
%>
<html>
<head>
<title><%= section.getName()%></title>
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
	background-color: #000;
	float:left;
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
#navBar {
	width:934px;
	height:18px;
	overflow:hidden;
	padding:13px;
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
	margin-left:13px;
}
#fakeLiveboxWindow #left #note {
	width:660px;
	height:142px;
	padding:13px;
	overflow:hidden;
	border-top-width: 1px;
	border-top-style: dotted;
	border-top-color: #666666;
}/*
#fakeLiveboxWindow #left #note .date {
	color:#dcdcdc;
	font-weight:700;
	background-color:#525252;
	padding:4px;
	margin-right:auto;
}*/
#fakeLiveboxWindow #left #note #detalle {
	width:485px;
	height:142px;
	overflow:hidden;
	float:right;
}
#fakeLiveboxWindow #left #note #detalle #title, #fakeLiveboxWindow #left #note #detalle #title a, #fakeLiveboxWindow #left #note #detalle #title a:active, #fakeLiveboxWindow #left #note #detalle #title a:visited {
	font-size:13px;
	color:#e21e26;
	line-height: normal;
	font-weight: bold;
	text-transform: uppercase;
	text-decoration: none;
	width:420px;
	margin-top:13px;
	margin-bottom:13px;
	overflow:hidden;
}
#fakeLiveboxWindow #left #note #detalle #title a:hover {
	text-decoration:underline;
}
#fakeLiveboxWindow #left #note #detalle #bajada, #fakeLiveboxWindow #left #note #detalle #bajada a , #fakeLiveboxWindow #left #note #detalle #bajada a:active, #fakeLiveboxWindow #left #note #detalle #bajada a:visited {
	color:#CCCCCC;
	font-size: 11px;
	line-height: normal;
	font-weight: normal;
	text-align:justify;
	text-decoration: none;
	overflow:hidden;
}
#fakeLiveboxWindow #left #note #detalle #bajada a:hover {
	text-decoration:underline;
}
#fakeLiveboxWindow #left #note #thmbnail {
	border:solid 1px #525252;
	width:154px;
	height:142px;
	background-image: url(../../../images/thmbn_default.jpg);
	background-repeat: no-repeat;
	background-position: center center;
	float:left;
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
#fakeLiveboxWindow #left #linksBottom #linkPaging {
	float:right;
}
#fakeLiveboxWindow #left #linksBottom #linkPage {
	float:left;
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
					<% for (Section sectionIter : publicHomeBean.getSectionsForCountry()) { %>
						<li>
							<% if (SectionType.RANKING_100.equals(sectionIter.getSectiontype())) { %>
								<a href="../../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewRanking.html"><%= sectionIter.getName() %></a>
							<% } else { %>
								<% if (SectionType.VIDEOS.equals(sectionIter.getSectiontype())) { %>
									<a href="../../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewVideos.html"><%= sectionIter.getName() %></a>
								<% } else { %>
									<% if (SectionType.IMAGE_GALLERY.equals(sectionIter.getSectiontype())) { %>
										<a href="../../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewPhotoGalleries.html"><%= sectionIter.getName() %></a>
									<% } else { %>
										<a href="../../../<%=publicHomeBean.getExternalLink(sectionIter)%>"><%= sectionIter.getName() %></a>
									<% } %>
								<% } %>
							<% } %>
						</li>
					<% } %>
				</ul>
			</div>
		</div>
	</div>
	<% if (publicHomeBean.hasNoteTopBanner()) {%>
		<div id="bannerHeader" align="center"><%=publicHomeBean.getNoteTop().getHtmlcontent() %></div>
	<% } %>
	<div id="fakeLiveboxWindow">
		<div id="sectionTitle"><%= section.getName()%></div>
		<div id="left">
			<% List<NoteValueObject> currentPage = publicHomeBean.getSectionsNotes(section, pageNumber);
				for (int i = 0; (i < PublicHomeBean.SECTION_PAGE_SIZE && currentPage.size() > i); i++) { 
					NoteValueObject nvo = currentPage.get(i); %>
				<div id="note">
					<div id="thmbnail" style="background:url(../../../downloadThumb.st?id=<%=nvo.getNoteImages().get(0).getId()%>&width=154&height=154&type=PUBLIC&ext=<%=nvo.getNoteImages().get(0).getExtension()%>);"><a href="../../../<%=publicHomeBean.getExternalLink(nvo)%>?s=<%=section.getId()%>&p=<%=pageNumber%>"><img src="../../../images/null.gif" width="154" height="142" /></a></div>
					<div id="detalle">
						<div id="title"><a href="../../../<%=publicHomeBean.getExternalLink(nvo)%>?s=<%=section.getId()%>&p=<%=pageNumber%>"><%=nvo.getTitle() %></a></div>
						<div id="bajada"><a href="../../../<%=publicHomeBean.getExternalLink(nvo)%>?s=<%=section.getId()%>&p=<%=pageNumber%>"><%=nvo.getSummary()%></a></div>
					</div>
				</div>
			<% } %>
			<div id="linksBottom">
				<% if (pageNumber == 0) { %>
					<div id="linkHome"><a href="../../../index.jsp?r=0">Volver a la home</a></div>
				<% } else  { %>
					<div id="linkPaging">
						<div id="linkPage"><a href="../../../<%=publicHomeBean.getExternalLink(section)%>?pageNumber=<%=pageNumber - 1 %>">Anterior</a></div>
					</div>
				<% } %>
				<div id="linkPaging">
					<% for (Integer pageToRender : publicHomeBean.getPages(section, pageNumber)) { %>
						<% if (pageToRender == pageNumber) { /*es la actual, no tiene link*/%>
							<div id="linkPage"><%=pageToRender + 1%></div>
						<% } else { %>
							<div id="linkPage"><a href="../../../<%=publicHomeBean.getExternalLink(section)%>?pageNumber=<%=pageToRender%>"><%=pageToRender + 1%></a></div>
						<% } %>
					<% } %>
					<% if (currentPage.size() > PublicHomeBean.SECTION_PAGE_SIZE) { %>
						<div id="linkPaging"><a href="../../../<%=publicHomeBean.getExternalLink(section)%>?pageNumber=<%=pageNumber + 1 %>">Siguiente</a></div>
					<% } %>
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
<%@ include file="../includes/noteFooter.jsp" %>
</body>
</html>
<% } %>