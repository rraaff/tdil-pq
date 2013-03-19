<%@page import="com.facebook.util.CreateTestUser"%>
<%@page import="com.facebook.FacebookTestData"%>
<%@page import="com.facebook.util.GetAccessToken"%>
<%
	FacebookTestData facebookTestData = (FacebookTestData)session.getAttribute("FacebookTestData");
	facebookTestData.loadUser(request.getAttribute("id"));
%>
<jsp:forward page="index.jsp"></jsp:forward>