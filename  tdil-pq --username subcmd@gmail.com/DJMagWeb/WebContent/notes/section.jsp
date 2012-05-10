<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.tdil.djmag.model.SectionType"%>
<%@page import="com.tdil.djmag.model.Country"%>
<%@page import="com.tdil.djmag.model.Section"%>
<%@page import="com.tdil.djmag.model.NoteImage"%>
<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%
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
							<a href="../../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewRanking.html<%=PublicHomeBean.LIGTH_BOX_PARAMS%>" rel="prettyPhoto[ranking_menu]"><%= sectionIter.getName() %></a>
						<% } else { %>
							<% if (SectionType.VIDEOS.equals(sectionIter.getSectiontype())) { %>
								<a href="../../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewVideos.html<%=PublicHomeBean.LIGTH_BOX_PARAMS%>" rel="prettyPhoto[videos_menu]"><%= sectionIter.getName() %></a>
							<% } else { %>
								<a href="../../../<%=publicHomeBean.getExternalLink(sectionIter)%><%=PublicHomeBean.LIGTH_BOX_PARAMS%>" rel="prettyPhoto[section_<%=sectionIter.getId()%>]"><%= sectionIter.getName() %></a>
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
<% for (NoteValueObject nvo : publicHomeBean.getSectionsNotes(section)) { %>
	<a href="../../../<%=publicHomeBean.getExternalLink(nvo)%><%=PublicHomeBean.LIGTH_BOX_PARAMS%>" rel="prettyPhoto[news_gal]"><%=nvo.getTitle() %></a><br>
<% } %>
</body>
</html>
<% } %>