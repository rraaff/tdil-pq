<%@page import="com.tdil.djmag.model.Video"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.djmag.model.RankingPositions"%>
<%@page import="com.tdil.djmag.model.RankingNote"%>
<%@page import="com.tdil.djmag.model.NoteImage"%>
<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%
String country = request.getParameter("country");
if (session == null || session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN) == null) {
	// todo aca primero seteo el pais, luego redirecciono
	String theURL = "../../selectCountry.st?iso_code_2="+ country + "&action=viewVideos";
	theURL = response.encodeRedirectURL(theURL);
	response.sendRedirect(theURL);
} else {
	PublicHomeBean publicHomeBean = (PublicHomeBean)session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN);
	List<Video> allVideos = publicHomeBean.getAllVideosForCountry();
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
/*div {
	border:dotted 1px #00FF00;
}*/
#fakeLiveboxWindow {
	width:960px;
	height:700px;
	margin-left:auto;
	margin-right:auto;
	margin-top:50px;
	border:solid 4px #545454;
	/* redondeado */	
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
}
</style>
</head>
<body style="background:#000000; background-image:none;">
				<a href="javascript:window.open('http://twitter.com/home?status=' + encodeURIComponent(location.href)); return false;">
				<img src="images/buttons/tw.gif" border="0"></a>
			<a href="javascript:window.open('http://www.facebook.com/sharer.php?u=' + encodeURIComponent(location.href)); return false;">
				<img src="images/buttons/fb.gif" border="0"></a>
<div id="topvideo">
	<%=(first != null) ? first.getHtmlcontent() : ""%>
</div>
<div id="right">
	<% for (Video video : allVideos) { %>
		<div id="thmbnVideo" style="margin-top:5px;"><img onclick="setAsTopVideo('topvideo-<%=video.getId()%>')" src="../../download.st?id=<%=video.getFrontcoverId()%>&type=PUBLIC&ext=<%=video.getFrontcoverext()%>" width="200" height="144">
			<div id="topvideo-<%=video.getId()%>" style="display: none;"><%=video.getHtmlcontent() %></div>
		</div>
	<% } %>
</div>
</body>
</html>
<% 
} %>