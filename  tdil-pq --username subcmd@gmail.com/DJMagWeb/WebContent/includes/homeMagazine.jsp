<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%@page import="com.tdil.djmag.model.Magazine"%>
<div id="BlockMagazine">
<% if (publicHomeBean.hasMagazine()) { 
	Magazine magazine = publicHomeBean.getMagazine();
	boolean canDownload = magazine.getMagazinecontentId() != null && magazine.getMagazinecontentId() != 0;
	%>
	<div id="magazine" style="background-image:url(./download.st?id=<%=magazine.getFrontcoverId()%>&type=PUBLIC&ext=<%=magazine.getFrontcoverext()%>);">
		<div id="magazineContentBase">
			<% if (canDownload) { %>
				<a href="./downloadAttach.st?id=<%=magazine.getMagazinecontentId()%>&type=PUBLIC&ext=<%=magazine.getMagazinecontentext()%>">DJMAG del Mes</a>
			<% } %>
			<div id="bajada"><%=magazine.getDescription() %></div>
			<div id="date"><%=publicHomeBean.formatMagazineDate(magazine.getPublishDate()) %></div>
		</div>
	</div>
<% } else { %>
	No tiene magazine
<% } %>
</div>