<%@page import="com.tdil.djmag.model.Video"%>
<div id="BlockVideos">
	<% if (publicHomeBean.hasVideos()) { %>
		<div id="mainContent">
			<% for (Video video : publicHomeBean.getLastVideos()) { %>
				<div id="left"><%=video.getHtmlcontent() %></div>
			<% } %>
			<div id="right">
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
			</div>
		</div>
	<% } else { %>
		<div>No hay videos</div>
	<% } %>
</div>