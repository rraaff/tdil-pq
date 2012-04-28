<% if (publicHomeBean.hasHomeRightBanner()) { %>
	<%= publicHomeBean.getHomeRight().getHtmlcontent()%>
<% } else { %>
	No tiene banner rigth
<% } %>
