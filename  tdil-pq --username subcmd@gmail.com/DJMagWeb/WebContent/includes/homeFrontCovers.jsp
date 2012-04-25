<%@page import="com.tdil.djmag.model.Note"%>
		<h2>ultimas noticias</h2>
		<div id="BlockLastNews">
		<% if (publicHomeBean.hasFrontCovers()) { %>
			<% for (Note note : publicHomeBean.getFrontCoverNotes()) { %>
				<div id="lastNews" style="float:left; margin-right:15px;">
					<img src="images/demo/ultimasNoticias.jpg" width="308" height="222">
					<h3><%=note.getTitle() %></h3>
					<div class="bajada"><%=note.getSummary() %></div>
					<div class="date">03 de mayo 2012 FALTA</div>
				</div>
			<% } %>
		<% } else { %>
			NO hay front covers
		<% } %>
		</div>