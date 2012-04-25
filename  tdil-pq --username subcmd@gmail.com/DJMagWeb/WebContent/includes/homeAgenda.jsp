<%@page import="com.tdil.djmag.model.Note"%>
<% if (publicHomeBean.hasAgenda()) { %>
<div id="agenda" style="border: 1px Solid Black;">
Agenda
<table>
<% 
for (Note note : publicHomeBean.getReducedAgenda()) { %>
<tr>
	<td><%=publicHomeBean.formatAgendaDate(note.getAgendaDate())%></td>
	<td><%=note.getTitle() %></td>
</tr>
<% } %>
</table>
</div>
<% } else { %>
	No hay eventos de agenda
<% } %>