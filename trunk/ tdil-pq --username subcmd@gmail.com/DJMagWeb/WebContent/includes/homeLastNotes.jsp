<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.model.Note"%>
<h2>ultimas noticias</h2>
<div id="BlockLastNews">
	<% if (publicHomeBean.hasLastNotes()) { %>
		<% for (NoteValueObject note : publicHomeBean.getReducedLastNotes()) { %>
			<div id="lastNews" style="float:left; margin-right:15px;">
				<img src="./download.st?id=<%=note.getLastnewsthumbId()%>&type=PUBLIC&ext=<%=note.getLastnewsthumbext()%>" width="308" height="222">
				<h3><%=note.getTitle() %></h3>
				<div class="bajada"><%=note.getSummary() %></div>
				<div class="date"><%=publicHomeBean.formatDate(note.getFromDate()) %></div>
			</div>
		<% } %>
	<% } else { %>
		No hay last notes
	<% } %>
</div>