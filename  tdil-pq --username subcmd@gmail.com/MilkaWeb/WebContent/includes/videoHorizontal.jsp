<%@page import="com.tdil.milka.model.Video"%>
<%
java.util.List<Video> videos = com.tdil.milka.web.VideoUtils.getVideos();
%>
			<ul>
			<% for (Video video : videos ) { %>
				<li class=""><a href="#" title="Ingresar a Youtube para ver el playlist" class="activo"><img src="./download.st?id=<%=video.getFrontcoverId()%>&type=PUBLIC&ext=<%=video.getFrontcoverext()%>" width="133" height="83" /></a><h3><%=video.getTitle()%></h3><a href="<%=video.getUrl()%>" class="playlist">Playlist</a></li>
			<% } %>
			</ul>