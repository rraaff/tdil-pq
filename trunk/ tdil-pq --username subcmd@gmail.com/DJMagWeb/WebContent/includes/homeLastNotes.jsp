<%@page import="com.tdil.djmag.model.Note"%>
<style>
#BlockMain #mainContent #frontNote {
	width:428px;
	height:385px;
}
#BlockMain #mainContent #frontNote #noteContentBase {
	width:428px;
	height:133px;
	background-image: url(images/bg-headerHome.png);
	background-repeat: repeat;
	overflow:hidden;
	top: 252px;
	position: relative;
}
#BlockMain #mainContent a:hover {
	cursor:hand;
}
#BlockMain #mainContent h1 {
	width:100%;
	font-size:22px;
	color:#f1e752;
	padding-top:8px;
	padding-left:25px;
	padding-bottom:5px;
	padding-right:25px;
	text-decoration:underline;
}
#BlockMain #mainContent span {
	width:100%;
	font-size:13px;
	line-height:16px;
	padding-top:8px;
	padding-left:25px;
	padding-bottom:5px;
	padding-right:25px;
}
#BlockMain #mainContent #date {
	width:100%;
	font-size:13px;
	color:#8c8c8c;
	line-height:16px;
	padding-top:8px;
	padding-left:25px;
	padding-bottom:5px;
	padding-right:25px;
}
</style>
<% if (publicHomeBean.hasLastNotes()) { %>
	<div id="mainContent">
		<% for (Note note : publicHomeBean.getReducedLastNotes()) { %>
			<div id="frontNote" style="background-image:url(images/demo/mainContentImage.jpg);"><!-- haría dinámica esta parte-->
				<div id="noteContentBase">
					<h1><%=note.getTitle() %></h1>
					<span>ACA VA LA BAJADA DE LA NOTA</span>
					<div id="date">ACA VA LA FECHA</div>
				</div>
			</div>
		<% } %>
	</div>
<% } else { %>
	No hay last notes
<% } %>