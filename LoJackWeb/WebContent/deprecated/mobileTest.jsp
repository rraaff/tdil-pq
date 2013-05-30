<% 
	com.tdil.mobile.UAgentInfo agentInfo = new com.tdil.mobile.UAgentInfo(request.getHeader("User-Agent"), request.getHeader("Accept"));
%>
User-agent:<%=request.getHeader("User-Agent")%><br>
Accept:<%=request.getHeader("Accept")%><br>
<% if (agentInfo.detectMobileLong()) { %>
	Es mobile <br>
<% } %>
<% if (agentInfo.isIphone) { %>
	Es iphone<br>
<% } %>
<% if (agentInfo.isAndroidPhone) { %>
	Es android phone<br>
<% } %>