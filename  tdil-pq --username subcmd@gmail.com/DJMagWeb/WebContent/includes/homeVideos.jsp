<%@page import="com.tdil.djmag.model.Video"%>
<h2>Videos</h2>
<% if (publicHomeBean.hasVideos()) { %>
	<div id="mainContent">
		<% for (Video video : publicHomeBean.getLastVideos()) { %>
			<div>
			<%=video.getHtmlcontent() %></span>
			</div>
		<% } %>
	</div>
<% } else { %>
	<div id="BlockHomeCalendar">No hay videos</div>
<% } %>