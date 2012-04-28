<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.model.Note"%>
	<h2>ultimas noticias</h2>
	<div id="BlockLastNews">
		<% if (publicHomeBean.hasLastNoteFirst()) { 
			Note first = publicHomeBean.getLastNoteFirst();
		%>
			<div id="lastNews" style="float:left; margin-right:15px;">
				<img src="./download.st?id=<%=first.getLastnewscoverId()%>&type=PUBLIC&ext=<%=first.getLastnewscoverext()%>" width="308" height="222">
				<h3><%=first.getTitle() %></h3>
				<div class="bajada"><%=first.getSummary() %></div>
				<div class="date"><%=publicHomeBean.formatDate(first.getFromDate()) %></div>
			</div>
		<% } %>
		<% if (publicHomeBean.hasLastNoteSecond()) { 
			Note second = publicHomeBean.getLastNoteSecond();
		%>
		<div id="lastNews" style="float:right;">
			<img src="./download.st?id=<%=second.getLastnewscoverId()%>&type=PUBLIC&ext=<%=second.getLastnewscoverext()%>" width="308" height="222">
			<h3><%=second.getTitle() %></h3>
			<div class="bajada"><%=second.getSummary() %></div>
			<div class="date"><%=publicHomeBean.formatDate(second.getFromDate()) %></div>
		</div>
		<% } %>
	</div>