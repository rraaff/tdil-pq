<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.model.Note"%>
<style>
#BlockMain #mainContent #frontNote {
	width:428px;
	height:385px;
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
<div id="mainContent">
	<% if (publicHomeBean.hasFrontCovers()) { %>
		<% for (NoteValueObject note : publicHomeBean.getFrontCoverNotes()) { %>
			<div id="frontNote" style="background-image:url(./download.st?id=<%=note.getNoteImages().get(0).getId()%>&type=note&ext=<%=note.getNoteImages().get(0).getExtension()%>);">
				<div id="noteContentBase">
					<h1><%=note.getTitle() %></h1>
					<div id="bajada"><%=note.getSummary() %></div>
					<div id="date"><%=publicHomeBean.formatDate(note.getFromDate()) %></div>
				</div>
			</div>
		<% } %>
	<% } else { %>
		NO hay front covers
	<% } %>
</div>