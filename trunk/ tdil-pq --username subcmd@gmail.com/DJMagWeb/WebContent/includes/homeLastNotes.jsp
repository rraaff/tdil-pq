<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.model.Note"%>
<div id="BlockPopularNews">
	<% if (publicHomeBean.hasLastNotes()) { %>
		<% for (NoteValueObject note : publicHomeBean.getReducedLastNotes()) { %>
			<div id="lastNews" style="margin-right:14px;">
				<img src="./download.st?id=<%=note.getLastnewsthumbId()%>&type=PUBLIC&ext=<%=note.getLastnewsthumbext()%>" width="200" height="143">
				<h3><%=note.getTitle() %></h3>
				<div class="bajada"><%=note.getSummary() %></div>
				<div class="date"><%=publicHomeBean.formatDate(note.getFromDate()) %></div>
			</div>
		<% } %>
	<% } else { %>
		No hay last notes
	<% } %>
</div>