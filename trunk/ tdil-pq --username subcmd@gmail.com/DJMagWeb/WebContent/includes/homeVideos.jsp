<%@page import="com.tdil.djmag.model.Video"%>
<div id="BlockVideos">
	<% if (publicHomeBean.hasVideos()) { 
		Video top = publicHomeBean.getTopVideo();
		%>
		<div id="topvideo">
			<%=top.getHtmlcontent() %>
		</div>
		<div id="right">
			<% for (Video video : publicHomeBean.getLastVideos()) { %>
				<div id="thmbnVideo" style="margin-top:5px;"><img onclick="setAsTopVideo('topvideo-<%=video.getId()%>')" src="./download.st?id=<%=video.getFrontcoverId()%>&type=PUBLIC&ext=<%=video.getFrontcoverext()%>" width="200" height="144">
					<div id="topvideo-<%=video.getId()%>" style="display: none;"><%=video.getHtmlcontent() %></div>
				</div>
			<% } %>
		</div>
	<% } else { %>
		<div>No hay videos</div>
	<% } %>
</div>