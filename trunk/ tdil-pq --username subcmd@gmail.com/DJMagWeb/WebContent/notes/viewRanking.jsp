<%@page import="com.tdil.djmag.model.RankingPosition"%>
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
}*/
#fakeLiveboxWindow {
	width:920px;
	height:700px;
	margin-left:auto;
	margin-right:auto;
}
#fakeLiveboxWindow #social {
	width:920px;
	height:30px;
	overflow:hidden;
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
	width:640px;
	height:500px;
}
#fakeLiveboxWindow #left #note {
	background-color:#FFFFFF;
	width:600px;
	height:446px;
	margin:20px;
	padding:12px;
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
	width:600px;
	height:380px;
	padding:0;
	overflow:auto;
}
#fakeLiveboxWindow #left #note #top100LB #renglonRank {
	width:570px;
	height:97px;
	border-bottom:solid 1px #cfcfcf;
}
#fakeLiveboxWindow #left #note #top100LB #renglonRank #position {
	font-size: 15px;
	line-height: 80px;
	font-weight: bold;
	color: #ffea00;
	text-align: center;
	float: left;
	width: 34px;
	height: 80px;
	background-color:#000000;
	margin-top:8px;
}
#fakeLiveboxWindow #left #note #top100LB #renglonRank #photo {
	width:78px;
	height:78px;
	background-image:url(../../images/thmbn_default.jpg);
	border:solid 1px #5d5d5d;
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
	color:#000;
	font-size:11px;
	line-height:16px;
	font-weight:bold;
	width: 420px;
	height:30px;
	overflow:hidden;
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
</style>
</head>
<body style="background:#000000; background-image:none;">
<div id="fakeLiveboxWindow">
	<div id="social">
		<div style="width:135; overflow:hidden;">
			<div style="float:right; margin-left:10px; margin-top:1px;"><a href="javascript:window.open('http://www.facebook.com/sharer.php?u=' + encodeURIComponent(location.href)); return false;"><img src="../../images/buttons/shareFb.png" width="60" height="18"></a></div>
			<div style="float:right;"><a href="javascript:window.open('https://twitter.com/share?url=' + encodeURIComponent(location.href)); return false;"><img src="../../images/buttons/sharetw.gif" width="59" height="20"></a></div>
		</div>
	</div>
	<% if (publicHomeBean.hasNoteTopBanner()) {%>
		<div id="bannerHeader"><%=publicHomeBean.getNoteTop().getHtmlcontent() %></div>
	<% } %>
	<div id="left">
		<div id="note">
			<h1><%=publicHomeBean.getRankingSection().getName() %></h1>
			<div id="top100LB">
				<% int positionIndex = 1;
				for (RankingPosition position : positions.getPositions()) { %>
					<div id="renglonRank">
						<div id="position"><%=positionIndex++ %></div>
						<div id="photo">
							<% if (position.hasImage()) { %>
								<img src="../../download.st?id=<%=position.getImageid()%>&type=PUBLIC&ext=<%=position.getImageext()%>" width="78" height="78"></div>
							<% } %>
						<div id="ranked">
							<span class="title"><%=position.getPosition()%></span>
							<span class="description"><%= position.getDescription() %></span>
							<span class="vermas"><a href="#">Ver m&aacute;s</a></span>
						</div>
					</div>
				<% } %>
			</div>
		</div>
	</div>
	<div id="right">
		<div id="subContent"></div>
		<% if (publicHomeBean.hasNoteRightBanner()) {%>
			<div id="rightBanner"><%=publicHomeBean.getNoteRight().getHtmlcontent() %></div>
		<% } %>
	</div>
</div>
</body>
</html>
<% }
} %>