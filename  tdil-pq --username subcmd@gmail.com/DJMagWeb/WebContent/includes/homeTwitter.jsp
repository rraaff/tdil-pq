<% if (publicHomeBean.hasTwitterFeed()) { %>
		<div id="BlockTwFeed">
			<div id="TwTitle"><img src="images/blockTwHead.gif" width="309" height="76"></div>
			<div id="TwFeed"><%= publicHomeBean.getTwitterFeed().getHtmlcontent()%></div>
		</div>
<% } else { %>
	<div id="BlockTwFeed">No tiene twitter</div>
<% } %>