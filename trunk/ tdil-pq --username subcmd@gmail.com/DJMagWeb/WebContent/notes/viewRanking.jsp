<%@page import="com.tdil.djmag.model.RankingPositions"%>
<%@page import="com.tdil.djmag.model.RankingNote"%>
<%@page import="com.tdil.djmag.model.NoteImage"%>
<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%
String country = request.getParameter("country");
if (session == null || session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN) == null) {
	// todo aca primero seteo el pais, luego redirecciono
	String theURL = "../../selectCountry.st?iso_code_2="+ country + "&action=viewRanking";
	theURL = response.encodeRedirectURL(theURL);
	response.sendRedirect(theURL);
} else {
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
/*
div {
	border:dotted 1px #00FF00;
}
#fakeLiveboxWindow {
	width:960px;
	height:700px;
	margin-left:auto;
	margin-right:auto;
	margin-top:50px;
	border:solid 4px #545454;
	/* redondeado *	
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
	border-radius: 6px;
}
#navBar {
	width:934px;
	height:28px;
	overflow:hidden;
	padding:13px;
}
#navBar #prevNext {
	float:left;
	width:67px;
	height:28px;
}
#navBar #prevNext #prev {
	background-image:url(images/buttons/navBar_prev.gif);
	width:32px;
	height:28px;
	float:left;
}
#navBar #prevNext #prev:hover {
	background-image:url(images/buttons/navBar_prev_over.gif);
	cursor:hand;
}
#navBar #prevNext #next {
	background-image:url(images/buttons/navBar_next.gif);
	width:35px;
	height:28px;
	float:right;
}
#navBar #prevNext #next:hover {
	background-image:url(images/buttons/navBar_next_over.gif);
	cursor:hand;
}
#navBar #closeButton {
	float:right;
	background-image:url(images/buttons/navBar_close.gif);
	width:37px;
	height:28px;
}
#navBar #closeButton:hover {
	background-image:url(images/buttons/navBar_close_over.gif);
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
}*/
#rankingHeader {
	width:95%;
	height:87px;
}
#rankingHeader #rankingLogo {
	width:232px;
	height:87px;
	float:left;
}
#rankingHeader #social {
	float:right;
}
#top100LB {
	font-family: Arial, Helvetica, sans-serif;
	width:400px;
	padding:0;
	margin-top: 25;
	margin-right: 0;
	margin-bottom: 0;
	margin-left: 0;
}
#top100LB #position {
	font-size: 12px;
	line-height: 25px;
	font-weight: normal;
	color: #e25237;
	text-align: right;
	float: left;
	width: 30px;
}
#top100LB #ranked {
	font-size: 13px;
	line-height: 25px;
	font-weight: normal;
	font-variant: normal;
	color: #FFFFFF;
	float: left;
	width: 360px;
	margin-left:10px;
	overflow: hidden;
}
</style>
</head>
<body style="background:#000000; background-image:none;">
<div id="rankingHeader">
	<div id="rankingLogo"><img src="../../images/header-top100.jpg" alt="Top 100" width="232" height="87"></div>
	<div id="social">
				<a href="javascript:window.open('http://twitter.com/home?status=' + encodeURIComponent(location.href)); return false;">
				<img src="images/buttons/tw.gif" border="0"></a>
			<a href="javascript:window.open('http://www.facebook.com/sharer.php?u=' + encodeURIComponent(location.href)); return false;">
				<img src="images/buttons/fb.gif" border="0"></a>
	</div>
</div>
<div id="top100LB">
	<% int positionIndex = 1;
	for (String position : positions.getPositions()) { %>
		<div id="position"><%=positionIndex++ %>.</div><div id="ranked"><%=position %></div>
	<% } %>
</div>
</body>
</html>
<% }
} %>