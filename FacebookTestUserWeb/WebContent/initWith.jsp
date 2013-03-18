<%@page import="com.facebook.FacebookTestData"%>
<%@page import="com.facebook.util.GetAccessToken"%>
<%
	String appId = request.getParameter("appId");
	String appName = request.getParameter("appName");
	String clientSecret = request.getParameter("clientSecret");
	
	String accessToken = GetAccessToken.get(appId, clientSecret);
	
	FacebookTestData facebookTestData = (FacebookTestData)session.getAttribute("FacebookTestData");
	facebookTestData.setAppName(appName);
	facebookTestData.setAppId(appId);
	facebookTestData.setAccessToken(accessToken);
%>
<jsp:forward page="index.jsp"></jsp:forward>