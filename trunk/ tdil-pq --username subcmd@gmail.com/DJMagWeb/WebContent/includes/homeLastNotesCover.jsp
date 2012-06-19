<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.model.Note"%>
<style>
#BlockLastNews {
	width:635px;
	height:390px;
	position:relative;
	overflow: hidden;
}
#BlockLastNews #lastNews {
	width:310px;
}
#BlockLastNews #lastNews img {

}
#BlockLastNews #lastNews h3, #BlockLastNews #lastNews h3 a, #BlockLastNews #lastNews h3 a:visited, #BlockLastNews #lastNews h3 a:active {
	font-size:18px;
	color:#fef455;
	font-weight:bold;
	margin:0;
	padding:0;
	width:310px;
	height:45px;
	overflow:hidden;
	text-decoration:none;
}
#BlockLastNews #lastNews h3 a:hover {
	text-decoration:underline;
}
.bajada, .bajada a, .bajada a:visited, .bajada a:active {
	color:#FFFFFF;
	font-size:13px;
	line-height:18px;
	font-weight:700;
	height:55px;
	margin-top:8px;
	margin-bottom:10px;
	overflow:hidden;
	text-decoration:none;
}
.bajada a:hover {
	text-decoration:underline;
}
#BlockLastNews #lastNews .date {
	width:305px;
	font-size:11px;
	line-height:20px;
	background-color:#525252;
	padding-left:5px;
}
.fotoHelperHLNC {
	background-repeat: no-repeat;
	background-position: center center; 
	border: solid 1px #3e3e3e;
	margin-bottom:15px;
	margin-top:15px;
	width:308px;
	height:222px;
	float:left;
}
</style>
	<h2>ultimas noticias</h2>
	<div id="BlockLastNews">
		<% if (publicHomeBean.hasLastNoteFirst()) { 
			NoteValueObject first = publicHomeBean.getLastNoteFirst();
		%>
			<div id="lastNews" style="float:left; margin-right:15px;">
				<div class="fotoHelperHLNC" style="background:url(./downloadThumb.st?id=<%=first.getLastnewscoverId()%>&width=308&height=308&type=PUBLIC&ext=<%=first.getLastnewscoverext()%>); background-repeat:no-repeat; background-position:center center;"><a href="<%=publicHomeBean.getExternalLink(first)%>"></a></div>
				<h3><a href="<%=publicHomeBean.getExternalLink(first)%>"><%=first.getTitle() %></a></h3>
				<div class="bajada"><a href="<%=publicHomeBean.getExternalLink(first)%>"><%=first.getSummary() %></a></div>
				<div class="date"><%=PublicHomeBean.formatDate(first.getFromDate()) %></div>
			</div>
		<% } %>
		<% if (publicHomeBean.hasLastNoteSecond()) { 
			NoteValueObject second = publicHomeBean.getLastNoteSecond();
		%>
		<div id="lastNews" style="float:right;">
			<div class="fotoHelperHLNC" style="background:url(./downloadThumb.st?id=<%=second.getLastnewscoverId()%>&width=308&height=308&type=PUBLIC&ext=<%=second.getLastnewscoverext()%>); background-repeat:no-repeat; background-position:center center;"><a href="<%=publicHomeBean.getExternalLink(second)%>"></a></div>
			<h3><a href="<%=publicHomeBean.getExternalLink(second)%>"><%=second.getTitle() %></a></h3>
			<div class="bajada"><a href="<%=publicHomeBean.getExternalLink(second)%>"><%=second.getSummary() %></a></div>
			<div class="date"><%=PublicHomeBean.formatDate(second.getFromDate()) %></div>
		</div>
		<% } %>
	</div>