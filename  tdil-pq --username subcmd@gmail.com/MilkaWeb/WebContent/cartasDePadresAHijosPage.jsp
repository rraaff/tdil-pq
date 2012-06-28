<%@page import="com.tdil.milka.web.MeltButton"%>
<%@page import="com.tdil.milka.web.MailToChildUtils"%>
<%@page import="com.tdil.milka.model.valueobjects.MailToChildValueObject"%>
<%@page import="com.tdil.web.SearchPage"%>
<% String pageStart = request.getParameter("items");
int pageStartInt = Integer.valueOf(pageStart);
SearchPage<MailToChildValueObject> mailPage = MailToChildUtils.getPage(pageStartInt / 10);
%>
<% for (MailToChildValueObject mtc : mailPage.getPage()) { %>
	<div id="moduleContent">
		<div id="date"><%=mtc.getDate()%><br/><%=mtc.getMonth()%></div>
		<h1><%=mtc.getAuthorValueObject().getName()%></h1>
		<p><%=mtc.getDescription()%></p>
		<a href="./downloadThumb.st?id=<%=mtc.getIdApprovedData()%>&width=800&height=600&type=PUBLIC&ext=<%=mtc.getExtApprovedData()%>" title="<%=mtc.getAuthorValueObject().getName()%>" id="lk-<%=mtc.getIdClickCounter()%>" rel="lightbox" button="<%=mtc.getIdClickCounter()%>-<%=MeltButton.meltButtonCount(mtc.getIdClickCounter())%>" class="activo">
		<img src="./downloadThumb.st?id=<%=mtc.getIdApprovedData()%>&width=415&height=300&type=PUBLIC&ext=<%=mtc.getExtApprovedData()%>" width="415" height="300" />
		</a>
	</div>
<% } %>
<% if (!mailPage.isHasNext()) { %>
	<div id="moduleContent">
		<h1>No hay mas datos</h1>
	</div>
<% } %>
