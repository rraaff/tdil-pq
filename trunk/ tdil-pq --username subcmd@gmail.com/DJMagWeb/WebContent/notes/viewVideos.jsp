<%@page import="com.tdil.djmag.web.servlets.SelectCountryServlet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tdil.djmag.model.Video"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.djmag.model.RankingPositions"%>
<%@page import="com.tdil.djmag.model.RankingNote"%>
<%@page import="com.tdil.djmag.model.NoteImage"%>
<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%
String pageNumberParam = request.getParameter("pageNumber");
int pageNumber = PublicHomeBean.parsePageParam(pageNumberParam);
String country = request.getParameter("country");
if (session == null || session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN) == null) {
	SelectCountryServlet.initForCountry(request, country, "");
} 
	PublicHomeBean publicHomeBean = (PublicHomeBean)session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN);
	ArrayList<Integer> pages = new ArrayList<Integer>();
	List<Video> allVideos = publicHomeBean.getAllVideosForCountry(pageNumber, pages);
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
				<% for (int i = 0; (i < PublicHomeBean.VIDEOS_PAGE_SIZE && allVideos.size() > i); i++) { 
					Video video = allVideos.get(i);%>
					<div id="renglonVideo">
						<div id="thmbnVideo"><img onClick="setAsTopVideo('topvideo-<%=video.getId()%>')" src="../../download.st?id=<%=video.getFrontcoverId()%>&type=PUBLIC&ext=<%=video.getFrontcoverext()%>" width="78" height="78"></div>
						<div id="ranked">
							<span class="title"><%=video.getTitle() %></span>
							<span class="description"><%=video.getDescription() %></span>
							<div id="topvideo-<%=video.getId()%>" style="display: none;"><%=video.getHtmlcontent() %></div>
						</div>
					</div>
				<% } %>
			</div>
		</div>
		<% if (pageNumber == 0) { %>
			<a href="../../index.jsp">Volver a la home</a>
		<% } else  { %>
			<a href="../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewVideos.html?pageNumber=<%=pageNumber - 1 %>">&lt;</a>
		<% } %>
		<% for (Integer pageToRender : pages) { %>
			<% if (pageToRender == pageNumber) { /*es la actual, no tiene link*/%>
				<%=pageToRender + 1%>
			<% } else { %>
				<a href="../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewVideos.html?pageNumber=<%=pageToRender%>"><%=pageToRender + 1%></a>
			<% } %>
		<% } %>
		<% if (allVideos.size() > PublicHomeBean.VIDEOS_PAGE_SIZE) { %>
			<a href="../../notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewVideos.html?pageNumber=<%=pageNumber + 1 %>">&gt;</a>
		<% } %>
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