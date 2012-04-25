<% if (publicHomeBean.hasTwitterFeed()) { %>
<div id="twitter" style="border: 1px Solid Black;">
	<%= publicHomeBean.getTwitterFeed().getHtmlcontent()%>
</div>
<% } else { %>
	No tiene twitter
<% } %>