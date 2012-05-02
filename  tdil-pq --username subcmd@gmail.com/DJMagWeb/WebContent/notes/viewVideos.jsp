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
	width:570px;
	height:530px;
	margin:20px;
	padding:12px;
	background-color:#FFFFFF;
}
#fakeLiveboxWindow #left #note h1 {
	font-size:18px;
	color:#e25237;
	line-height: normal;
	font-weight: bold;
	text-transform: uppercase;
	text-decoration: none;
	margin-bottom:16px;
}
#topvideo {
	width:425px;
	height:297px;
	margin-top: 10px;
	margin-right: auto;
	margin-bottom: 10px;
	margin-left: auto;
}
#fakeLiveboxWindow #left #note #videoList {
	font-family: Arial, Helvetica, sans-serif;
	width:570px;
	height:180px;
	padding:0;
	overflow:auto;
}
#fakeLiveboxWindow #left #note #videoList #renglonVideo {
	width:550px;
	height:97px;
	border-bottom:solid 1px #cfcfcf;
}
#fakeLiveboxWindow #left #note #videoList #renglonVideo #thmbnVideo {
	width:78px;
	height:78px;
	background-image:url(../../images/thmbn_default.jpg);
	border:solid 1px #5d5d5d;
	margin:8 0 0 0px;
	float: left;
}
#fakeLiveboxWindow #left #note #videoList #renglonVideo #ranked {
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
#fakeLiveboxWindow #left #note #videoList #renglonVideo #ranked .title {
	color:#e25237;
	font-size:13px;
	font-weight:bold;
	width: 420px;
	float: left;
}
#fakeLiveboxWindow #left #note #videoList #renglonVideo #ranked .description {
	color:#000;
	font-size:11px;
	line-height:18px;
	font-weight:bold;
	width: 420px;
	height:55px;
	overflow:hidden;
	float: left;
}
#fakeLiveboxWindow #right {
	float:right;
	width:280px;
	height:550px;
	overflow:hidden;
}
#fakeLiveboxWindow #right #subContent {
	width:252px;
	height:252px;
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
			<div style="float:left;"><a href="javascript:window.open('https://twitter.com/share?url=' + encodeURIComponent(location.href)); return false;"><img src="../../images/buttons/sharetw.gif" width="59" height="20"></a>
			</div>
			<div style="float:left; margin-left:10px; margin-top:1px;"><a href="javascript:window.open('http://www.facebook.com/sharer.php?u=' + encodeURIComponent(location.href)); return false;"><img src="../../images/buttons/shareFb.png" width="60" height="18"></a></div>
		</div>
	</div>
	<% if (publicHomeBean.hasNoteTopBanner()) {%>
		<div id="bannerHeader"><%=publicHomeBean.getNoteTop().getHtmlcontent() %></div>
	<% } %>
	<div id="left">
		<div id="note">
			<h1><%=publicHomeBean.getVideoSection().getName() %></h1>
			<div id="topvideo">
				<%=(first != null) ? first.getHtmlcontent() : ""%>
			</div>
			<div id="videoList">
				<% for (Video video : allVideos) { %>
					<div id="renglonVideo">
						<div id="thmbnVideo"><img onClick="setAsTopVideo('topvideo-<%=video.getId()%>')" src="../../download.st?id=<%=video.getFrontcoverId()%>&type=PUBLIC&ext=<%=video.getFrontcoverext()%>" width="78" height="78"></div>
						<div id="ranked">
							<span class="title"><%=video.getTitle() %></span>
							<span class="description">aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción aca va la descripción</span>
							<div id="topvideo-<%=video.getId()%>" style="display: none;"><%=video.getHtmlcontent() %></div>
						</div>
					</div>
				<% } %>
			</div>
		</div>
	</div>
	<div id="right">
		<% if (publicHomeBean.hasNoteRightBanner()) {%>
			<div id="subContent"><%=publicHomeBean.getNoteRight().getHtmlcontent() %></div>
		<% } %>
		<% if (publicHomeBean.hasNoteRightBanner()) {%>
			<div id="rightBanner"><%=publicHomeBean.getNoteRight().getHtmlcontent() %></div>
		<% } %>
	</div>
</div>
</body>
</html>
<% 
} %>