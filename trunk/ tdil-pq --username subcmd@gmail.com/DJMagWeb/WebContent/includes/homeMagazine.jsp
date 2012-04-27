<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%@page import="com.tdil.djmag.model.Magazine"%>
<div id="BlockFbFeed">
<% if (publicHomeBean.hasMagazine()) { 
	Magazine magazine = publicHomeBean.getMagazine();
	boolean canDownload = magazine.getMagazinecontentId() != null && magazine.getMagazinecontentId() != 0;
	%>
	<div id="magazine" style="background-image:url(./download.st?id=<%=magazine.getFrontcoverId()%>&type=PUBLIC&ext=<%=magazine.getFrontcoverext()%>);">
		<div id="magazineContentBase">
			<h1><% if (canDownload) { %>
					<a href="./downloadAttach.st?id=<%=magazine.getMagazinecontentId()%>&type=PUBLIC&ext=<%=magazine.getMagazinecontentext()%>">
				<% } %>
					<%=magazine.getDescription() %></h1>
				<% if (canDownload) { %>
					</a>
				<% } %>
			<div id="date"><%=publicHomeBean.formatMagazineDate(magazine.getPublishDate()) %></div>
		</div>
	</div>
<% } else { %>
	No tiene magazine
<% } %>
</div>