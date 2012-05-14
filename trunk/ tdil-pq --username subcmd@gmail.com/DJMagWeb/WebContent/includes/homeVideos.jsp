<%@page import="com.tdil.djmag.model.Video"%>
<style>
#BlockVideos {
	height: 350px;
	width: 635px;
	position: relative;
	margin-top: 10px;
}
#BlockVideos #topvideo {
	float: left;
	height: 300px;
	width: 390px;
}
#BlockVideos #right {
	width:202px;
	height:297px;
	float:right;
	overflow:hidden;
}
#BlockVideos #right #thmbnVideo {
	width:200px;
	height:144px;
	border:solid 1px #525252;
}
#BlockVideos #right #thmbnVideo:hover {
	border:solid 1px #FFFF00;
	cursor:hand;
}
</style>
<div id="BlockVideos">
	<% if (publicHomeBean.hasVideos()) { 
		Video top = publicHomeBean.getTopVideo();
		%>
		<div id="topvideo">
			<%=top.getHtmlcontent() %>
		</div>
		<div id="right">
			<% 	int videoIndex = 0;
				for (Video video : publicHomeBean.getLastVideos()) { %>
				<div id="thmbnVideo" <%= videoIndex > 0 ? "style=\"margin-top:5px;\"" : ""%>><img onclick="setAsTopVideo('topvideo-<%=video.getId()%>')" src="./download.st?id=<%=video.getFrontcoverId()%>&type=PUBLIC&ext=<%=video.getFrontcoverext()%>" width="200" height="144">
					<div id="topvideo-<%=video.getId()%>" style="display: none;"><%=video.getHtmlcontent() %></div>
				</div>
			<% 	videoIndex = videoIndex + 1;
				} %>
		</div>
	<% } else { %>
		<div>No hay videos</div>
	<% } %>
</div>