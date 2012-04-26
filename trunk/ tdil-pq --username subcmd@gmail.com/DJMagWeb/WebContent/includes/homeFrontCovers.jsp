<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.model.Note"%>
		<h2>ultimas noticias</h2>
		<div id="BlockLastNews">
		<% if (publicHomeBean.hasFrontCovers()) { %>
			<% for (NoteValueObject note : publicHomeBean.getFrontCoverNotes()) { %>
				<div id="lastNews" style="float:left; margin-right:15px;">
					<img src="./download.st?id=<%=note.getNoteImages().get(0).getId()%>&type=note&ext=<%=note.getNoteImages().get(0).getExtension()%>" width="308" height="222">
					<h3><%=note.getTitle() %></h3>
					<div class="bajada"><%=note.getSummary() %></div>
					<div class="date"><%=publicHomeBean.formatDate(note.getFromDate()) %></div>
				</div>
			<% } %>
		<% } else { %>
			NO hay front covers
		<% } %>
		</div>