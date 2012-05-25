<%@page import="com.tdil.milka.model.Video"%>
<%
java.util.List<Video> videos = com.tdil.milka.web.VideoUtils.getVideos();
%>
<% for (Video video : videos ) { %>
	<%=video.getTitle()%><br>
	<%=video.getUrl()%><br>
	<img src="./download.st?id=<%=video.getFrontcoverId()%>&type=PUBLIC&ext=<%=video.getFrontcoverext()%>">
<% } %>