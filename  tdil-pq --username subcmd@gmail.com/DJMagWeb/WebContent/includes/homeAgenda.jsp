<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.model.Note"%>
<style>
#BlockHomeCalendar {
	width:309px;
	height:203px;
	overflow:hidden;
	margin-top:15px;
	position:relative;
}
#BlockHomeCalendar #calendarEvent {
	width:309px;
	position:relative;
	float:left;
	padding-bottom:10px;
	padding-top:10px;
	border-bottom:solid 1px #525252;
}
#BlockHomeCalendar #calendarEvent:hover {
	background-color:#525252;
	color:#FFFF33;
	cursor:hand;
}
#BlockHomeCalendar #calendarEvent #renglon {
	float:left;
}
#BlockHomeCalendar #calendarEvent #image {
	width:30px;
	height:30px;
	margin-left:8px;
	margin-right:8px;
	float:left;
}
#BlockHomeCalendar #calendarEvent #date, #BlockHomeCalendar #calendarEvent #date a, #BlockHomeCalendar #calendarEvent #date a:hover {
	background-color:#e25237;
	color:#FFFF33;
	font-size:11px;
	text-decoration:none;
	padding: 2px;
	margin:0;
	margin-top:2px;
	float:left;
}
#titleNoteInAgenda, #titleNoteInAgenda a, #titleNoteInAgenda a:active, #titleNoteInAgenda a:visited {
	color:#FFFFFF;
	font-size:11px;
	font-weight:700;
	line-height:11px;
	padding-left:4px;
	padding-top:4px;
	width:180px;
	height:18px;
	float:left;
	overflow:hidden;
	text-decoration:none;
}
#titleNoteInAgenda a:hover {
	text-decoration:underline;
}
</style>
<h2>calendario de eventos</h2>
<% if (publicHomeBean.hasAgenda()) { %>
	<div id="BlockHomeCalendar">
	<% for (NoteValueObject note : publicHomeBean.getReducedAgenda()) { %>
		<div id="calendarEvent">
			<div id="image"><a href="<%=publicHomeBean.getExternalLink(note)%>"><img src="./download.st?id=<%=note.getAgendaId()%>&type=PUBLIC&ext=<%=note.getAgendaext()%>" height="30" width="30"></a></div>
			<div id="renglon"><div id="date"><a href="<%=publicHomeBean.getExternalLink(note)%>"><%=publicHomeBean.formatAgendaDate(note.getAgendaDate())%></a></div><div id="titleNoteInAgenda"><a href="<%=publicHomeBean.getExternalLink(note)%>"><%=note.getTitle() %></div></a></div>
		</div>
	<% } %>
	</div>
	<!-- Galeria de agenda -->
	<div id="agendaGallery" class="hide">
	<% for (NoteValueObject note : publicHomeBean.getAgendaNotesLinks()) { %>
		<a href="<%=publicHomeBean.getExternalLink(note)%>"><%=note.getTitle() %></a>
	<% } %>
	</div>
<% } else { %>
	<div id="BlockHomeCalendar"><%@ include file="homeBannerRight.jsp" %></div>
<% } %>