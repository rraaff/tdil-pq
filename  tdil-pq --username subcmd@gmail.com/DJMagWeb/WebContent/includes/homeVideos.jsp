<%@page import="com.tdil.djmag.model.Video"%>
<div id="BlockVideos">
	<% if (publicHomeBean.hasVideos()) { 
		Video top = publicHomeBean.getTopVideo();
		%>
		<div id="topvideo">
			<%=top.getHtmlcontent() %>
		</div>
		<div id="mainContent">
			<% for (Video video : publicHomeBean.getLastVideos()) { %>
				<img onclick="setAsTopVideo('topvideo-<%=video.getId()%>')" src="./download.st?id=<%=video.getFrontcoverId()%>&type=PUBLIC&ext=<%=video.getFrontcoverext()%>" height="50" width="70">
				<div id="topvideo-<%=video.getId()%>" style="display: none;">
					<%=video.getHtmlcontent() %>
				</div>
			<% } %>
			<!--div id="right">
				<div id="thmbnVideo" style="background-image:url(images/demo/videoChico1.jpg);">
					<div id="base">
						<h4>Solomun</h4>
						<span>DJ Mag Recession Sessions</span>
					</div>
				</div>
				<div id="thmbnVideo" style="background-image:url(images/demo/videoChico2.jpg); margin-top:5px;">
					<div id="base">
						<h4>Solomun</h4>
						<span>DJ Mag Recession Sessions</span>
					</div>
				</div>
			</div-->
		</div>
	<% } else { %>
		<div>No hay videos</div>
	<% } %>
</div>