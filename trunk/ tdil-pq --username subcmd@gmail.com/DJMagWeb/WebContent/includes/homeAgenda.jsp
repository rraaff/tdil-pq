<%@page import="com.tdil.djmag.model.Note"%>
<h2>calendario de eventos</h2>
<% if (publicHomeBean.hasAgenda()) { %>
	<div id="BlockHomeCalendar">
	<% for (Note note : publicHomeBean.getReducedAgenda()) { %>
		<div id="calendarEvent">
			<span class="date"><%=publicHomeBean.formatAgendaDate(note.getAgendaDate())%></span>
			<span><%=note.getTitle() %></span>
		</div>
	<% } %>
	</div>
<% } else { %>
	<div id="BlockHomeCalendar">No hay eventos de agenda</div>
<% } %>