<% if (publicHomeBean.hasTwitterFeed()) { %>
	<div id="BlockTwFeed"><%= publicHomeBean.getTwitterFeed().getHtmlcontent()%></div>
<% } else { %>
	<div id="BlockTwFeed">No tiene twitter</div>
<% } %>