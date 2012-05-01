<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.model.Note"%>
<h2>calendario de eventos</h2>
<% if (publicHomeBean.hasAgenda()) { %>
	<div id="BlockHomeCalendar">
	<% for (NoteValueObject note : publicHomeBean.getReducedAgenda()) { %>
		<div id="calendarEvent">
			<div id="image"><img src="./download.st?id=<%=note.getAgendaId()%>&type=PUBLIC&ext=<%=note.getAgendaext()%>" height="30" width="30"></div>
			<div id="renglon"><span class="date"><a href="<%=publicHomeBean.getExternalLink(note)%><%=PublicHomeBean.LIGTH_BOX_PARAMS%>" rel="prettyPhoto[agenda_gal]"><%=publicHomeBean.formatAgendaDate(note.getAgendaDate())%></a></span><span><%=note.getTitle() %></span></div>
		</div>
	<% } %>
	</div>
	<!-- Galeria de agenda -->
	<div id="agendaGallery" class="hide">
	<% for (NoteValueObject note : publicHomeBean.getAgendaNotesLinks()) { %>
		<a href="<%=publicHomeBean.getExternalLink(note)%><%=PublicHomeBean.LIGTH_BOX_PARAMS%>" rel="prettyPhoto[agenda_gal]"><%=note.getTitle() %></a>
	<% } %>
	</div>
<% } else { %>
	<div id="BlockHomeCalendar"><%@ include file="homeBannerRight.jsp" %></div>
<% } %>