<div id="bannerHeader">
<% if (publicHomeBean.hasHomeTopBanner()) { %>
	<%= publicHomeBean.getHomeTop().getHtmlcontent()%>
<% } else { %>
	No tiene banner
<% } %>
</div>