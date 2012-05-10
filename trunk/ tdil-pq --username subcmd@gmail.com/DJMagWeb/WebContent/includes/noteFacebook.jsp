<div id="BlockFbFeed">
<% if (publicHomeBean.hasFacebookFeed()) { %>
	<%= publicHomeBean.getFacebookFeed().getHtmlcontent()%>
<% } else { %>
	No tiene facebook
<% } %>
</div>