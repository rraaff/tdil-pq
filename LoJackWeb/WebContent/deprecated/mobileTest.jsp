<% 
	com.tdil.mobile.UAgentInfo agentInfo = new com.tdil.mobile.UAgentInfo(request.getHeader("User-Agent"), request.getHeader("Accept"));
%>
<% if (agentInfo.detectMobileLong()) { %>
	Es mobile <br>
<% } %>
<% if (agentInfo.isIphone) { %>
	Es iphone<br>
<% } %>
<% if (agentInfo.isAndroidPhone) { %>
	Es android phone<br>
<% } %>