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
	SelectCountryServlet.initForCountry(request, country, "");
} 
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
#fakeLiveboxWindow {
	width:960px;
	height:700px;
	margin-left:auto;
	margin-right:auto;
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

<div id="portaHeader">
	<div id="header">
		<div id="logo"></div>
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
				<li><a href="#" style="padding:0; cursor:default;"><img src="images/pronto-top20.gif" width="74" height="88"></a></li>
				<li><a href="#" style="padding:0; cursor:default;"><img src="images/pronto-shop.gif" width="62" height="88"></a></li>
			</ul>
		</div>
	</div>
</div>
<% List<NoteValueObject> currentPage = publicHomeBean.getSectionsNotes(section, pageNumber);
	for (int i = 0; (i < PublicHomeBean.SECTION_PAGE_SIZE && currentPage.size() > i); i++) { 
		NoteValueObject nvo = currentPage.get(i); %>
		<img src="../../../download.st?id=<%=nvo.getNoteImages().get(0).getId()%>&type=note&ext=<%=nvo.getNoteImages().get(0).getExtension()%>" alt="" />
		<a href="../../../<%=publicHomeBean.getExternalLink(nvo)%>?s=<%=section.getId()%>&p=<%=pageNumber%>"><%=nvo.getTitle() %></a>
		<%=nvo.getSummary()%>
	<br>
<% } %>
<% if (pageNumber == 0) { %>
	<a href="../../../index.jsp">Volver a la home</a>
<% } else  { %>
	<a href="../../../<%=publicHomeBean.getExternalLink(section)%>?pageNumber=<%=pageNumber - 1 %>">&lt;</a>
<% } %>
<% for (Integer pageToRender : publicHomeBean.getPages(section, pageNumber)) { %>
	<% if (pageToRender == pageNumber) { /*es la actual, no tiene link*/%>
		<%=pageToRender + 1%>
	<% } else { %>
		<a href="../../../<%=publicHomeBean.getExternalLink(section)%>?pageNumber=<%=pageToRender%>"><%=pageToRender + 1%></a>
	<% } %>
<% } %>
<% if (currentPage.size() > PublicHomeBean.SECTION_PAGE_SIZE) { %>
	<a href="../../../<%=publicHomeBean.getExternalLink(section)%>?pageNumber=<%=pageNumber + 1 %>">&gt;</a>
<% } %>

<%@ include file="../includes/noteFooter.jsp" %>
</body>
</html>