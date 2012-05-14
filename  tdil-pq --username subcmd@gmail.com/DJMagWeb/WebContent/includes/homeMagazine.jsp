<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<%@page import="com.tdil.djmag.model.Magazine"%>
<style>
#BlockMagazine {
	margin-top:12px;
	width:250px;
	height:268px;
	position:relative;
	float: right;
}
#BlockMagazine #magazine {
	width:250px;
	height:268px;
	position:relative;
	padding-top:142px;
	text-decoration:none;
}
#BlockMagazine #magazine a:hover {
	text-decoration:underline;
}
#BlockMagazine #magazine #magazineContentBase {
	width:230px;
	height:106px;
	padding:10px;
	overflow:hidden;
	background-image: url(images/bg-headerHome.png);
	background-repeat: repeat;
}
#BlockMagazine #magazine #magazineContentBase .title, #BlockMagazine #magazine #magazineContentBase .title a, #BlockMagazine #magazine #magazineContentBase .title a:active, #BlockMagazine #magazine #magazineContentBase .title a:visited {
	color:#FFFF33;
	font-size:18px;
	text-decoration:none;
}
#BlockMagazine #magazine #magazineContentBase .title a:hover {
	text-decoration:underline;
}
#BlockMagazine #magazine #magazineContentBase #bajada, #BlockMagazine #magazine #magazineContentBase #bajada a, #BlockMagazine #magazine #magazineContentBase #bajada a:active, #BlockMagazine #magazine #magazineContentBase #bajada a:visited {
	color:#FFFFFF;
	font-size:12px;
	height:45px;
	padding-top:15px;
	padding-bottom:15px;
	overflow:hidden;
	text-decoration:none;
}
#BlockMagazine #magazine #magazineContentBase #bajada a:hover {
	text-decoration:underline;
}
#BlockMagazine #magazine #magazineContentBase #date {
	color:#e25237;
	font-size:11px;
}
</style>

<div id="BlockMagazine">
<% if (publicHomeBean.hasMagazine()) { 
	Magazine magazine = publicHomeBean.getMagazine();
	boolean canDownload = magazine.getMagazinecontentId() != null && magazine.getMagazinecontentId() != 0;
	%>
	<div id="magazine" style="background-image:url(./download.st?id=<%=magazine.getFrontcoverId()%>&type=PUBLIC&ext=<%=magazine.getFrontcoverext()%>);background-repeat: no-repeat;">
		<div id="magazineContentBase">
			<% if (canDownload) { %>
				<span class="title"><a href="./downloadAttach.st?id=<%=magazine.getMagazinecontentId()%>&type=PUBLIC&ext=<%=magazine.getMagazinecontentext()%>">DJMAG del Mes</a></span>
				<div id="bajada"><a href="./downloadAttach.st?id=<%=magazine.getMagazinecontentId()%>&type=PUBLIC&ext=<%=magazine.getMagazinecontentext()%>"><%=magazine.getDescription() %></a></div>
				<div id="date"><%=publicHomeBean.formatMagazineDate(magazine.getPublishDate()) %></div>
			<% } else { %>
				<span class="title">DJMAG del Mes</span>
				<div id="bajada"><%=magazine.getDescription() %></div>
				<div id="date"><%=publicHomeBean.formatMagazineDate(magazine.getPublishDate()) %></div>
			<% } %>
		</div>
	</div>
<% } else { %>
	<%@ include file="../includes/homeBannerRight.jsp"%>
<% } %>
</div>