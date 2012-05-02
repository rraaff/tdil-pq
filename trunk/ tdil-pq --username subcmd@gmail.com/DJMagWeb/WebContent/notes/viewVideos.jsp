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
#fakeLiveboxWindow #topvideo {
	float:left;
	width:670px;
	height:500px;
}
#fakeLiveboxWindow #topvideo #note {
	background-color:#FFFFFF;
	width:606px;
	height:446px;
	margin:20px;
	padding:12px;
	overflow:scroll;
}
#fakeLiveboxWindow #topvideo #note .date {
	color:#dcdcdc;
	font-weight:700;
	background-color:#525252;
	padding:4px;
	margin-right:auto;
}
#fakeLiveboxWindow #topvideo #note h1 {
	font-size:18px;
	color:#e25237;
	line-height: normal;
	font-weight: bold;
	text-transform: uppercase;
	text-decoration: none;
	margin-top:18px;
	margin-bottom:16px;
}
#fakeLiveboxWindow #topvideo #note #bajada {
	color:#000000;
	font-size: 14px;
	line-height: normal;
	font-weight: bold;
	margin-bottom:20px;
}
#fakeLiveboxWindow #topvideo #note #images {
	border:solid 7px #525252;
	width:585px;
	height:303px;
	margin-left:auto;
	margin-right:auto;
}
#fakeLiveboxWindow #topvideo #note #fullText {
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
	overflow:auto;
}
#fakeLiveboxWindow #right #thmbnVideo {
	width:200px;
	height:144px;
	margin-left:auto;
	margin-right:auto;
	margin-top:5px;
}
</style>
</head>
<body style="background:#000000; background-image:none;">
<div id="fakeLiveboxWindow">
	<div id="navBar">
		<div style="margin-left:50px; width:650px; float:left; overflow:hidden;">
			<div style="float:left;"><a href="javascript:window.open('https://twitter.com/share?url=' + encodeURIComponent(location.href)); return false;"><img src="../../images/buttons/sharetw.gif" width="59" height="20"></a>
			</div>
			<div style="float:left; margin-left:10px; margin-top:1px;"><a href="javascript:window.open('http://www.facebook.com/sharer.php?u=' + encodeURIComponent(location.href)); return false;"><img src="../../images/buttons/shareFb.png" width="60" height="18"></a></div>
		</div>
	</div>
	<div id="topvideo">
		<%=(first != null) ? first.getHtmlcontent() : ""%>
	</div>
	<div id="right">
		<% for (Video video : allVideos) { %>
			<div id="thmbnVideo"><img onClick="setAsTopVideo('topvideo-<%=video.getId()%>')" src="../../download.st?id=<%=video.getFrontcoverId()%>&type=PUBLIC&ext=<%=video.getFrontcoverext()%>" width="200" height="144">
				<div id="topvideo-<%=video.getId()%>" style="display: none;"><%=video.getHtmlcontent() %></div>
			</div>
		<% } %>
	</div>
</div>
</body>
</html>
<% 
} %>