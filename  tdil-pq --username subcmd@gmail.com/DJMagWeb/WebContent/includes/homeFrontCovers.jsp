<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.model.Note"%>
<style>
#slider h1 a, #slider h1 a:active, #slider h1 a:visited, #slider h1 {
	border:none;
	color:#FFFF33;
	text-decoration:none;
}
#slider h1 a:hover {
	text-decoration:underline;
}
#slider .bajada a {
	border:none;
	text-decoration:none;
}
</style>
<% if (publicHomeBean.hasFrontCovers()) { %>
	<div class="slider-wrapper theme-default" style="width:428px; height:400px; margin-left:15px; float:left;">
	    <div class="ribbon"></div>
	    <div id="slider" class="nivoSlider">
		<% /*Generacion de imagenes*/
			for (NoteValueObject note : publicHomeBean.getFrontCoverNotes()) { %>
	        <a href="<%=publicHomeBean.getExternalLink(note)%>"><img src="./download.st?id=<%=note.getFrontcoverId()%>&type=PUBLIC&ext=<%=note.getFrontcoverext()%>" alt="" title="#htmlcaption<%=note.getId() %>" width="428" height="385" /></a>
		<% } %>
	    </div>
		<% /*Generacion de captions*/
		for (NoteValueObject note : publicHomeBean.getFrontCoverNotes()) { %>
		    <div id="htmlcaption<%=note.getId() %>" class="nivo-html-caption">
	    	<h1><a href="<%=publicHomeBean.getExternalLink(note)%>"><%=note.getTitle() %></a></h1>
			<div class="bajada"><a href="<%=publicHomeBean.getExternalLink(note)%>"><%=note.getSummary() %></a></div>
			<div class="date"><%=PublicHomeBean.formatDate(note.getFromDate()) %></div>
	 	</div>
	 	<% } %>
	</div>
<% } else { %>
	NO hay front covers
<% } %>