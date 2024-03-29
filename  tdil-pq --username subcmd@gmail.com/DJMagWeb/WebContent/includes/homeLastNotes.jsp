<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.model.Note"%>
<style>
#BlockPopularNews {
	width:635px;
	height:290px;
	position:relative;
	overflow:hidden;
}
#BlockPopularNews #lastNews {
	width:202px;
	float:left;
}
#BlockPopularNews #lastNews img {

}
#BlockPopularNews #lastNews h3, #BlockPopularNews #lastNews h3 a {
	font-size:15px;
	color:#fef455;
	font-weight:bold;
	margin:0;
	padding:0;
	width:202px;
	height:38px;
	overflow:hidden;
	text-decoration:none;
}

#BlockPopularNews #lastNews h3 a:hover {
	text-decoration:underline;
}
#BlockPopularNews #lastNews #bajada, #BlockPopularNews #lastNews #bajada a, #BlockPopularNews #lastNews #bajada a:active, #BlockPopularNews #lastNews #bajada a:visited {
	color:#FFFFFF;
	font-size:13px;
	line-height:16px;
	font-weight:700;
	height:50px;
	margin-top:3px;
	margin-bottom:5px;
	text-decoration:none;
	overflow:hidden;
}
#BlockPopularNews #lastNews #bajada a:hover {
	text-decoration:underline;
}
#BlockPopularNews #lastNews .date {
	width:197px;
	font-size:11px;
	line-height:20px;
	background-color:#525252;
	padding-left:5px;
}
.fotoHelperHLN {
	background-image: url(../images/foto2.jpg);
	background-repeat: no-repeat;
	background-position: center center;
	border: solid 1px #3e3e3e;
	margin-bottom:15px;
	margin-top:15px;
}
</style>
<div id="BlockPopularNews">
	<% if (publicHomeBean.hasLastNotes()) { 
		int index = 0;%>
		<% for (NoteValueObject note : publicHomeBean.getReducedLastNotes()) { %>
			<div id="lastNews" style="<%= (index < 2) ? "margin-right:14px;" : ""%>">
				<div class="fotoHelperHLN" style="background:url(./downloadThumb.st?id=<%=note.getLastnewsthumbId()%>&width=200&height=200&type=PUBLIC&ext=<%=note.getLastnewsthumbext()%>); background-repeat:no-repeat; background-position:center center;"><a href="<%=publicHomeBean.getExternalLink(note)%>"><img src="images/null.gif" width="200" height="143"></a></div>
				<h3><a href="<%=publicHomeBean.getExternalLink(note)%>"><%=note.getTitle() %></a></h3>
				<div id="bajada"><a href="<%=publicHomeBean.getExternalLink(note)%>"><%=note.getSummary() %></a></div>
				<div class="date"><%=PublicHomeBean.formatDate(note.getFromDate()) %></div>
			</div>
		<% index = index + 1;
			} %>
	<% } else { %>
		No hay Ultimas Noticias (no destacadas).
	<% } %>
</div>