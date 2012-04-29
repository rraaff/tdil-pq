<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%@page import="com.tdil.djmag.model.valueobjects.NoteValueObject"%>
<%@page import="com.tdil.djmag.model.Note"%>
	<h2>ultimas noticias</h2>
	<div id="BlockLastNews">
		<% if (publicHomeBean.hasLastNoteFirst()) { 
			NoteValueObject first = publicHomeBean.getLastNoteFirst();
		%>
			<div id="lastNews" style="float:left; margin-right:15px;">
				<img src="./download.st?id=<%=first.getLastnewscoverId()%>&type=PUBLIC&ext=<%=first.getLastnewscoverext()%>" width="308" height="222">
				<h3><a href="<%=publicHomeBean.getExternalLink(first)%><%=PublicHomeBean.LIGTH_BOX_PARAMS%>" rel="prettyPhoto[news_gal]"><%=first.getTitle() %></a></h3>
				<div class="bajada"><%=first.getSummary() %></div>
				<div class="date"><%=PublicHomeBean.formatDate(first.getFromDate()) %></div>
			</div>
		<% } %>
		<% if (publicHomeBean.hasLastNoteSecond()) { 
			NoteValueObject second = publicHomeBean.getLastNoteSecond();
		%>
		<div id="lastNews" style="float:right;">
			<img src="./download.st?id=<%=second.getLastnewscoverId()%>&type=PUBLIC&ext=<%=second.getLastnewscoverext()%>" width="308" height="222">
			<h3><a href="<%=publicHomeBean.getExternalLink(second)%><%=PublicHomeBean.LIGTH_BOX_PARAMS%>" rel="prettyPhoto[news_gal]"><%=second.getTitle() %></a></h3>
			<div class="bajada"><%=second.getSummary() %></div>
			<div class="date"><%=PublicHomeBean.formatDate(second.getFromDate()) %></div>
		</div>
		<% } %>
	</div>