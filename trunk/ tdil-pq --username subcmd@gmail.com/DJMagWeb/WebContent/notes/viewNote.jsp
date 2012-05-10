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
	width:934px;
	height:18px;
	overflow:hidden;
	padding:13px;
}
#sectionTitle {
	background-color: #e55532;
	font-family: 'Doppio One', sans-serif;
	font-size: 15px;
	font-weight: normal;
	text-transform: uppercase;
	color: #FFFFFF;
	margin-top:13px;
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
	width:620px;
}
#fakeLiveboxWindow #left #note {
	background-color:#FFFFFF;
	width:100%;
	margin:13px;
	padding:12px;
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
	font-size: 13px;
	line-height: 18px;
	font-weight: 700;
	color: #333333;
	margin-top:20px;
	padding-right: 20px;
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
	background-color:#FFFFFF;
	width:390;
	text-align:center;
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
			<div id="logo"></div>
			<div id="menu">
				<ul>
					<% for (Section section : publicHomeBean.getSectionsForCountry()) { %>
						<li>
							<% if (SectionType.RANKING_100.equals(section.getSectiontype())) { %>
								<a href="./notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewRanking.html<%=PublicHomeBean.LIGTH_BOX_PARAMS%>" rel="prettyPhoto[ranking_menu]"><%= section.getName() %></a>
							<% } else { %>
								<% if (SectionType.VIDEOS.equals(section.getSectiontype())) { %>
									<a href="./notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewVideos.html<%=PublicHomeBean.LIGTH_BOX_PARAMS%>" rel="prettyPhoto[videos_menu]"><%= section.getName() %></a>
								<% } else { %>
									<a href="<%=publicHomeBean.getExternalLink(publicHomeBean.getFirstNoteForSection(section))%><%=PublicHomeBean.LIGTH_BOX_PARAMS%>" rel="prettyPhoto[section_<%=section.getId()%>]"><%= section.getName() %></a>
								<% } %>
							<% } %>
						</li>
					<% } %>
					<li><a href="#" style="padding:0; cursor:default;"><img src="../../../images/pronto-top20.gif" width="74" height="88"></a></li>
					<li><a href="#" style="padding:0; cursor:default;"><img src="../../../images/pronto-shop.gif" width="62" height="88"></a></li>
				</ul>
			</div>
		</div>
	</div>
	<% if (publicHomeBean.hasNoteTopBanner()) {%>
		<div id="bannerHeader" align="center"><%=publicHomeBean.getNoteTop().getHtmlcontent() %></div>
	<% } %>
	<div id="fakeLiveboxWindow">
		<!-- Estructura de la interface -->
		<!-- div id="navBar">
			<!-- div id="prevNext">
				<div id="prev"></div>
				<div id="next"></div>
			</div-->
			<!--div style="margin-left:50px; width:650px; float:left; overflow:hidden;">
				<div style="float:left;"><a href="javascript:window.open('https://twitter.com/share?url=' + encodeURIComponent(location.href)); return false;"><img src="../../../images/buttons/sharetw.gif" width="59" height="20"></a>
				</div>
				<div style="float:left; margin-left:10px; margin-top:1px;"><a href="javascript:window.open('http://www.facebook.com/sharer.php?u=' + encodeURIComponent(location.href)); return false;"><img src="../../../images/buttons/shareFb.png" width="60" height="18"></a></div>
			</div>
			<!--div id="closeButton"></div->
		</div-->
		<div id="sectionTitle">Ultimas Noticias</div>
		<div id="left">
			<div id="note">
				<div class="slider-wrapper theme-default">
					<div class="ribbon"></div>
					<div id="slider" class="nivoSlider">
					<% /*Generacion de imagenes*/
						for (NoteImage noteImage : noteToShow.getNoteImages()) { %>
						<img src="../../../download.st?id=<%=noteImage.getId()%>&type=note&ext=<%=noteImage.getExtension()%>" alt="" />
					<% } %>
					</div>
				</div>
				<span class="date"><%=PublicHomeBean.formatDate(noteToShow.getFromDate()) %></span>
				<h1><%= noteToShow.getTitle()%></h1>
				<div id="bajada"><%= noteToShow.getSummary()%></div>
				<div id="fullText"><%= content%></div>
			</div>
		</div>
		<div id="right">
			<div id="subContent">
				<% if (publicHomeBean.hasNoteRightBanner()) {%>
					<div id="rightBanner"><%=publicHomeBean.getNoteRight().getHtmlcontent() %></div>
				<% } %>
			</div>
		</div>
	</div>
	<!-- div id="BlockSecondaryContent">
		<div id="leftContent">
			< %@ include file="../includes/noteLastNotesCover.jsp" %>
			< %@ include file="../includes/noteLastNotes.jsp" %>
			<h2>ultimos videos</h2>
			< %@ include file="../includes/noteVideos.jsp" %> 
		</div>
		<div id="rightContent">
			< %@ include file="../includes/noteAgenda.jsp" %>
			< %@ include file="../includes/noteTwitter.jsp" %>
			< %@ include file="../includes/noteFacebook.jsp" %>
		</div>
	</div -->
</div>
<%@ include file="../includes/noteFooter.jsp" %>
<!-- Galeria de ultimas noticias -->
<div id="newsGallery" class="hide">
<% for (NoteValueObject note : publicHomeBean.getLastNotesLinks()) { %>
	<a href="<%=publicHomeBean.getExternalLink(note)%><%=PublicHomeBean.LIGTH_BOX_PARAMS%>" rel="prettyPhoto[news_gal]"><%=note.getTitle() %></a>
<% } %>
</div>

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
<% }
} %>