<%@page import="com.tdil.djmag.model.Note"%>
<h2>calendario de eventos</h2>
<% if (publicHomeBean.hasAgenda()) { %>
	<div id="BlockHomeCalendar">
	<% for (Note note : publicHomeBean.getReducedAgenda()) { %>
		<div id="calendarEvent">
			<div id="image"><img src="./download.st?id=<%=note.getAgendaId()%>&type=PUBLIC&ext=<%=note.getAgendaext()%>" height="30" width="30"></div>
			<div id="renglon"><span class="date"><%=publicHomeBean.formatAgendaDate(note.getAgendaDate())%></span><span><%=note.getTitle() %></span></div>
		</div>
	<% } %>
	</div>
<% } else { %>
	<div id="BlockHomeCalendar"><%@ include file="homeBannerRight.jsp" %></div>
<% } %>