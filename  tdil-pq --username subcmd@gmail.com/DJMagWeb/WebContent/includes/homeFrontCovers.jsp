<%@page import="com.tdil.djmag.model.Note"%>
<% if (publicHomeBean.hasFrontCovers()) { %>
<div id="frontCovers" style="border: 1px Solid Black;">
<% for (Note note : publicHomeBean.getFrontCoverNotes()) { %>
	<div><%=note.getTitle() %></div>
	<div><%=note.getSummary() %></div>
<% } %>
</div>
<% } else { %>
	NO hay front covers
<% } %>