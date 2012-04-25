<% if (publicHomeBean.hasFacebookFeed()) { %>
<div id="facebook" style="border: 1px Solid Black;">
	<%= publicHomeBean.getFacebookFeed().getHtmlcontent()%>
</div>
<% } else { %>
	No tiene facebook
<% } %>