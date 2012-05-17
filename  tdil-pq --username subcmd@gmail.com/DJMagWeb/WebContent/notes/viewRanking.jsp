<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.djmag.model.SectionType"%>
<%@page import="com.tdil.djmag.model.Section"%>
<%@page import="com.tdil.djmag.web.servlets.SelectCountryServlet"%>
<%@page import="com.tdil.djmag.model.RankingPosition"%>
<%@page import="com.tdil.djmag.model.RankingPositions"%>
<%@page import="com.tdil.djmag.model.RankingNote"%>
<%@page import="com.tdil.djmag.model.NoteImage"%>
<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%
String country = request.getParameter("country");
if (session == null || session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN) == null) {
	SelectCountryServlet.initForCountry(request, country, "");
} 
	PublicHomeBean publicHomeBean = (PublicHomeBean)session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN);
	RankingPositions positions = publicHomeBean.getRankingPositions();
	if (positions == null) {
		
	} else {
		
%>
<html>
<head>
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<script src='../../js/jquery-1.7.min.js' type='text/javascript'></script>
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
	width:1010px;
	height:90px;
	margin-left:auto;
	margin-right:auto;
	padding:0;
}
#fakeLiveboxWindow #left {
	float:left;
	width:680px;
}
#fakeLiveboxWindow #left #note {
	width:654px;
	padding:13px;
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
#fakeLiveboxWindow #left #note #top100LB {
	font-family: Arial, Helvetica, sans-serif;
	width:650px;
	height:1630px;
	padding:0;
	overflow:auto;
}
#fakeLiveboxWindow #left #note #top100LB #renglonRank {
	width:630px;
	border-bottom:dotted 1px #666;
	float:left;
}
#fakeLiveboxWindow #left #note #top100LB #renglonRank #position {
	font-size: 15px;
	line-height: 80px;
	font-weight: bold;
	color: #FFFFCC;
	text-align: center;
	float: left;
	width: 34px;
	height: 80px;
	background-color:#333333;
	margin-top:8px;
}
#fakeLiveboxWindow #left #note #top100LB #renglonRank #photo {
	width:78px;
	height:78px;
	background-image:url(../../images/thmbn_default.jpg);
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
	width: 428px;
	margin-top:8px;
	margin-left:10px;
	overflow: hidden;
}
#fakeLiveboxWindow #left #note #top100LB #renglonRank #ranked .title {
	color:#e25237;
	font-size:13px;
	font-weight:bold;
	width: 420px;
	float: left;
}
#fakeLiveboxWindow #left #note #top100LB #renglonRank #ranked .description {
	color:#CCC;
	font-size:11px;
	line-height:16px;
	font-weight:bold;
	width: 420px;
	padding-bottom:13px;
	float: left;
}
.vermas, .vermas a, .vermas a:active, .vermas a:visited {
	color:#e25237;
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
		<div id="sectionTitle"><%=publicHomeBean.getRankingSection().getName() %></div>
		<div id="left">
			<div id="note">
				<div id="top100LB">
					<% int positionIndex = 1;
					for (RankingPosition position : positions.getPositions()) { %>
						<div id="renglonRank">
							<div id="position"><%=positionIndex++ %></div>
							<div id="photo">
								<% if (position.hasImage()) { %>
									<img src="../../download.st?id=<%=position.getImageid()%>&type=PUBLIC&ext=<%=position.getImageext()%>" width="78" height="78">
								<% } else { %>
									<img src="../../null.gif" width="78" height="78">
								<% } %>
							</div>
							<div id="ranked">
								<span class="title"><%=position.getPosition()%></span>
								<span class="description"><%= position.getDescription() %></span>
								<!--span class="vermas"><a href="#">Ver m&aacute;s</a></span-->
							</div>
						</div>
					<% } %>
				</div>
			</div>
			<div id="navBar">
				<div style="float:left;"><a href="javascript:window.open('https://twitter.com/share?url=' + encodeURIComponent(location.href)); return false;"><img src="../../images/buttons/sharetw.gif" width="70" height="20" align="absmiddle"></a>
				</div>
				<div style="float:left; margin-left:10px; margin-top:1px;"><a href="javascript:window.open('http://www.facebook.com/sharer.php?u=' + encodeURIComponent(location.href)); return false;"><img src="../../images/buttons/compartir_facebook.gif" width="82" height="18"></a></div>
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
</body>
</html>
<% 
} %>