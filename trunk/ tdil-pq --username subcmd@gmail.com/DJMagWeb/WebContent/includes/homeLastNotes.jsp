<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.model.Note"%>
<div id="BlockPopularNews">
	<% if (publicHomeBean.hasLastNotes()) { 
		int index = 0;%>
		<% for (NoteValueObject note : publicHomeBean.getReducedLastNotes()) { %>
			<div id="lastNews" style="<%= (index < 2) ? "margin-right:14px;" : ""%>">
				<img src="./download.st?id=<%=note.getLastnewsthumbId()%>&type=PUBLIC&ext=<%=note.getLastnewsthumbext()%>" width="200" height="143">
				<h3><%=note.getTitle() %></h3>
				<div class="bajada"><%=note.getSummary() %></div>
				<div class="date"><%=PublicHomeBean.formatDate(note.getFromDate()) %></div>
			</div>
		<% index = index + 1;
			} %>
	<% } else { %>
		No hay last notes
	<% } %>
</div>