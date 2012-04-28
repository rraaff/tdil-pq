<% if (publicHomeBean.hasHomeTopBanner()) { %>
	<%= publicHomeBean.getHomeTop().getHtmlcontent()%>
<% } else { %>
	No tiene banner
<% } %>
