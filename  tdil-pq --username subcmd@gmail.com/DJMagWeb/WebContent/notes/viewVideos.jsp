<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.djmag.model.SectionType"%>
<%@page import="com.tdil.djmag.model.Section"%>
<%@page import="com.tdil.djmag.web.servlets.SelectCountryServlet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tdil.djmag.model.Video"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.djmag.model.RankingPositions"%>
<%@page import="com.tdil.djmag.model.RankingNote"%>
<%@page import="com.tdil.djmag.model.NoteImage"%>
<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%
String pageNumberParam = request.getParameter("pageNumber");
int pageNumber = PublicHomeBean.parsePageParam(pageNumberParam);
String country = request.getParameter("country");
if (session == null || session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN) == null) {
	SelectCountryServlet.initForCountry(request, country, "");
} 
	PublicHomeBean publicHomeBean = (PublicHomeBean)session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN);
	ArrayList<Integer> pages = new ArrayList<Integer>();
	List<Video> allVideos = publicHomeBean.getAllVideosForCountry(pageNumber, pages);
	Video first = null;
	if (!allVideos.isEmpty()) {
		first = allVideos.get(0);
	}
%>
<html>
<head>
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<script src='../../js/jquery-1.7.min.js' type='text/javascript'></script>
<script>
function setAsTopVideo(divId) {
		document.getElementById('topvideo').innerHTML = document.getElementById(divId).innerHTML;
	}
</script>
<style>
div {
	/*border:dotted 1px #00FF00;*/
}
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
#topvideo {
	background-color:#000;
	margin-left:auto;
	margin-right:auto;
}
#videoList {
	width:660px;
}
#renglonVideo {
	width:660px;
	margin-top:13px;
	float:left;
}
#thmbnVideo {
	border:solid 1px #525252;
	width:78px;
	height:78px;
	float:left;
}
#ranked {
	width:560px;
	float:right;
}
#ranked #title{
	font-size:14px;
	color:#e25237;
	line-height: normal;
	font-weight: bold;
	text-transform: uppercase;
	text-decoration: none;
	padding-bottom:16px;
}
#description {
	color:#5b5b5b;
	font-size: 11px;
	line-height: 16px;
	font-weight: normal;
	text-align:justify;
	margin-bottom:20px;
	padding-right:20px;
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
	border-top:solid 1px #e5e5e5;
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
	padding:13px;
}
#fakeLiveboxWindow #right #rightBanner {
	width:286px;
	height:868px;
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
			<div id="logo"><a href="../../index.jsp"><img src="../../images/logo.gif" width="197" height="123" /></a></div>
			<div id="menu">
			<ul>
				<% for (Section section : publicHomeBean.getSectionsForCountry()) { %>
					<li>
						<% if (SectionType.RANKING_100.equals(section.getSectiontype())) { %>
							<a href="../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewRanking.html" style="padding:0; cursor:hand;"><img src="../../images/logoTop100.gif" width="214" height="123" /></a>
						<% } else { %>
							<% if (SectionType.VIDEOS.equals(section.getSectiontype())) { %>
								<a href="../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewVideos.html"><%= section.getName() %></a>
							<% } else { %>
								<a href="../../<%=publicHomeBean.getExternalLink(section)%>"><%= section.getName() %></a>
							<% } %>
						</li>
					<% }
						} %>
					<li><a href="#" style="padding:0; cursor:default;"><img src="../../images/pronto-top20.gif" width="74"></a></li>
					<li><a href="#" style="padding:0; cursor:default;"><img src="../../images/pronto-shop.gif" width="62"></a></li>
				</ul>
			</div>
		</div>
	</div>
	<% if (publicHomeBean.hasNoteTopBanner()) {%>
		<div id="bannerHeader" align="center"><%=publicHomeBean.getNoteTop().getHtmlcontent() %></div>
	<% } %>
	<div id="fakeLiveboxWindow">
		<div id="sectionTitle"><%=publicHomeBean.getVideoSection().getName() %></div>
		<div id="left">
			<div id="note">
				<div id="topvideo" align="center">
					<%=(first != null) ? first.getHtmlcontent() : ""%>
				</div>
				<div id="videoList">
					<% for (int i = 0; (i < PublicHomeBean.VIDEOS_PAGE_SIZE && allVideos.size() > i); i++) { 
						Video video = allVideos.get(i);%>
						<div id="renglonVideo">
							<div id="thmbnVideo"><img onClick="setAsTopVideo('topvideo-<%=video.getId()%>')" src="../../download.st?id=<%=video.getFrontcoverId()%>&type=PUBLIC&ext=<%=video.getFrontcoverext()%>" width="78" height="78"></div>
							<div id="ranked">
								<div id="title"><%=video.getTitle() %></div>
								<div id="description"><%=video.getDescription() %></div>
								<div id="topvideo-<%=video.getId()%>" style="display: none;"><%=video.getHtmlcontent() %></div>
							</div>
						</div>
					<% } %>
				</div>
			</div>
			<div id="linksBottom">
				<% if (pageNumber == 0) { %>
					<div id="linkHome"><a href="../../index.jsp">Volver a la home</a></div>
				<% } else  { %>
					<div id="linkPaging"><a href="../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewVideos.html?pageNumber=<%=pageNumber - 1 %>">&lt;</a></div>
				<% } %>
				<% for (Integer pageToRender : pages) { %>
					<% if (pageToRender == pageNumber) { /*es la actual, no tiene link*/%>
						<%=pageToRender + 1%>
					<% } else { %>
						<div id="linkPaging"><a href="../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewVideos.html?pageNumber=<%=pageToRender%>"><%=pageToRender + 1%></a></div>
					<% } %>
				<% } %>
				<% if (allVideos.size() > PublicHomeBean.VIDEOS_PAGE_SIZE) { %>
					<div id="linkPaging"><a href="../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewVideos.html?pageNumber=<%=pageNumber + 1 %>">&gt;</a></div>
				<% } %>
			</div>
		</div>
		<div id="right">
			<% if (publicHomeBean.hasNoteRightBanner()) {%>
				<div id="rightBanner"><%=publicHomeBean.getNoteRight().getHtmlcontent() %></div>
			<% } %>
		</div>
	</div>
</div>
<%@ include file="../includes/rankingFooter.jsp" %>
</body>
</html>