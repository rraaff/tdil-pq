<% 
	com.tdil.mobile.UAgentInfo agentInfo = new com.tdil.mobile.UAgentInfo(request.getHeader("User-Agent"), request.getHeader("Accept"));
%>
<% if (agentInfo.isIphone) { %>
	Es iphone
<% } %>
<% if (agentInfo.isAndroidPhone) { %>
	Es android phone
<% } %>