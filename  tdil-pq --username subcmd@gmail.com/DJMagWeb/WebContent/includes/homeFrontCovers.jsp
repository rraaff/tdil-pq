<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.model.Note"%>
<style>
#BlockMain #mainContent #frontNote {
	width:428px;
	height:400px;
}
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
#BlockMain #mainContent h1 {
	width:100%;
	font-size:22px;
	color:#f1e752;
	padding-top:8px;
	padding-left:25px;
	padding-bottom:5px;
	padding-right:25px;
	text-decoration:underline;
}
#BlockMain #mainContent #bajada {
	width:378px;
	height:45px;
	font-size:13px;
	line-height:16px;
	padding-top:0px;
	margin-left:25px;
	padding-bottom:5px;
	margin-right:25px;
	overflow:hidden;
}
#BlockMain #mainContent #date {
	width:100%;
	font-size:13px;
	color:#8c8c8c;
	font-weight:700;
	line-height:16px;
	padding-top:8px;
	padding-left:25px;
	padding-bottom:5px;
	padding-right:25px;
}
</style>
<% if (publicHomeBean.hasFrontCovers()) { %>
	<div class="slider-wrapper theme-default">
	    <div class="ribbon"></div>
	    <div id="slider" class="nivoSlider">
		<% /*Generacion de imagenes*/
			for (NoteValueObject note : publicHomeBean.getFrontCoverNotes()) { %>
	        <img src="./download.st?id=<%=note.getFrontcoverId()%>&type=PUBLIC&ext=<%=note.getFrontcoverext()%>" alt="" title="#htmlcaption<%=note.getId() %>" />
		<% } %>
	    </div>
		<% /*Generacion de captions*/
		for (NoteValueObject note : publicHomeBean.getFrontCoverNotes()) { %>
	    <div id="htmlcaption<%=note.getId() %>" class="nivo-html-caption">
	    		<h1><%=note.getTitle() %></h1>
				<%=note.getSummary() %>
				<%=PublicHomeBean.formatDate(note.getFromDate()) %>
	 	</div>
	 	<% } %>
	</div>
	<% /*Generacion de links a las notas*/
		for (NoteValueObject note : publicHomeBean.getFrontCoverNotes()) { %>
		<a href="<%=publicHomeBean.getExternalLink(note)%>"><%=note.getTitle() %></a>
	<% } %>
<% } else { %>
	NO hay front covers
<% } %>
