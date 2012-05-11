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
	background-color: #FFFFFF;
	float:left;
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
}
#fakeLiveboxWindow #left #note {
	background-color:#FFFFFF;
	width:660px;
	height:142px;
	padding:13px;
	overflow:hidden;
	border-top-width: 1px;
	border-top-style: dotted;
	border-top-color: #c5c5c5;
}
#fakeLiveboxWindow #left #note .date {
	color:#dcdcdc;
	font-weight:700;
	background-color:#525252;
	padding:4px;
	margin-right:auto;
}
#fakeLiveboxWindow #left #note #detalle {
	width:485px;
	height:142px;
	overflow:hidden;
	float:right;
}
#fakeLiveboxWindow #left #note #detalle #title {
	width:420px;
	margin-top:13px;
	margin-bottom:13px;
	overflow:hidden;
}
#fakeLiveboxWindow #left #note #detalle #title a {
	font-size:13px;
	color:#e25237;
	line-height: normal;
	font-weight: bold;
	text-transform: uppercase;
	text-decoration: none;
}
#fakeLiveboxWindow #left #note #detalle #bajada {
	color:#5b5b5b;
	font-size: 11px;
	line-height: normal;
	font-weight: normal;
	text-align:justify;
	overflow:hidden;
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
	height:868px;
	overflow:hidden;
}
#fakeLiveboxWindow #right #rightBanner {
	width:286px;
	height:868px;
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
					<% for (Section sectionIter : publicHomeBean.getSectionsForCountry()) { %>
						<li>
							<% if (SectionType.RANKING_100.equals(sectionIter.getSectiontype())) { %>
								<a href="../../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewRanking.html"><%= sectionIter.getName() %></a>
							<% } else { %>
								<% if (SectionType.VIDEOS.equals(sectionIter.getSectiontype())) { %>
									<a href="../../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewVideos.html"><%= sectionIter.getName() %></a>
								<% } else { %>
									<a href="../../../<%=publicHomeBean.getExternalLink(sectionIter)%>"><%= sectionIter.getName() %></a>
								<% } %>
							<% } %>
						</li>
					<% } %>
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
		<div id="sectionTitle"><%= section.getName()%></div>
		<div id="left">
			<% List<NoteValueObject> currentPage = publicHomeBean.getSectionsNotes(section, pageNumber);
				for (int i = 0; (i < PublicHomeBean.SECTION_PAGE_SIZE && currentPage.size() > i); i++) { 
					NoteValueObject nvo = currentPage.get(i); %>
				<div id="note">
					<div id="thmbnail"><!-- style="background-image:../../../"--><img src="../../../download.st?id=<%=nvo.getNoteImages().get(0).getId()%>&type=note&ext=<%=nvo.getNoteImages().get(0).getExtension()%>" width="154" height="142" alt="" /></div>
					<div id="detalle">
						<div id="title"><a href="../../../<%=publicHomeBean.getExternalLink(nvo)%>?s=<%=section.getId()%>&p=<%=pageNumber%>"><%=nvo.getTitle() %></a></div>
						<div id="bajada"><%=nvo.getSummary()%></div>
					</div>
				</div>
			<% } %>
			<div id="linksBottom">
				<% if (pageNumber == 0) { %>
					<div id="linkHome"><a href="../../../index.jsp">Volver a la home</a></div>
				<% } else  { %>
					<div id="linkPaging"><a href="../../../<%=publicHomeBean.getExternalLink(section)%>?pageNumber=<%=pageNumber - 1 %>">&lt;</a></div>
				<% } %>
				<% for (Integer pageToRender : publicHomeBean.getPages(section, pageNumber)) { %>
					<% if (pageToRender == pageNumber) { /*es la actual, no tiene link*/%>
						<div id="linkPaging"><%=pageToRender + 1%></div>
					<% } else { %>
						<div id="linkPaging"><a href="../../../<%=publicHomeBean.getExternalLink(section)%>?pageNumber=<%=pageToRender%>"><%=pageToRender + 1%></a></div>
					<% } %>
				<% } %>
				<% if (currentPage.size() > PublicHomeBean.SECTION_PAGE_SIZE) { %>
					<div id="linkPaging"><a href="../../../<%=publicHomeBean.getExternalLink(section)%>?pageNumber=<%=pageNumber + 1 %>">&gt;</a></div>
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
<%@ include file="../includes/noteFooter.jsp" %>
</body>
</html>
<% } %>