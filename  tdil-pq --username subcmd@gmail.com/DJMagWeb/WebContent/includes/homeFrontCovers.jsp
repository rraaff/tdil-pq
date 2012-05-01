<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.model.Note"%>
<style>
/*#BlockMain #mainContent #frontNote {
.noteSizeing {
	width:428px;
	height:400px;
}/*
#BlockMain #mainContent #frontNote #noteContentBase {
	width:428px;
	height:133px;
	background-image: url(images/bg-headerHome.png);
	background-repeat: repeat;
	overflow:hidden;
	top: 252px;
	position: relative;
}
#BlockMain #mainContent a:hover {
	cursor:hand;
}
*/
#slider h1 a {
	color:#FFFF33;
}
#slider h1 a:hover {
	text-decoration:underline;
}
</style>
<% if (publicHomeBean.hasFrontCovers()) { %>
	<div class="slider-wrapper theme-default" style="width:428px; height:400px; margin-left:15px; float:left;">
	    <div class="ribbon"></div>
	    <div id="slider" class="nivoSlider">
		<% /*Generacion de imagenes*/
			for (NoteValueObject note : publicHomeBean.getFrontCoverNotes()) { %>
	        <a href="<%=publicHomeBean.getExternalLink(note)%><%=PublicHomeBean.LIGTH_BOX_PARAMS%>" rel="prettyPhoto[cover_gal]"><img src="./download.st?id=<%=note.getFrontcoverId()%>&type=PUBLIC&ext=<%=note.getFrontcoverext()%>" alt="" title="#htmlcaption<%=note.getId() %>" width="428" height="385" /></a>
		<% } %>
	    </div>
		<% /*Generacion de captions*/
		for (NoteValueObject note : publicHomeBean.getFrontCoverNotes()) { %>
		    <div id="htmlcaption<%=note.getId() %>" class="nivo-html-caption">
	    	<h1><%=note.getTitle() %></h1>
			<div class="bajada"><%=note.getSummary() %></div>
			<div class="date"><%=PublicHomeBean.formatDate(note.getFromDate()) %></div>
	 	</div>
	 	<% } %>
	</div>
<% } else { %>
	NO hay front covers
<% } %>