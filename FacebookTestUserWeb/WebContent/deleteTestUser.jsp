<%@page import="com.facebook.util.DeleteTestUser"%>
<%@page import="com.facebook.util.CreateTestUser"%>
<%@page import="com.facebook.FacebookTestData"%>
<%@page import="com.facebook.util.GetAccessToken"%>
<%
	FacebookTestData facebookTestData = (FacebookTestData)session.getAttribute("FacebookTestData");
	DeleteTestUser.delete(request.getParameter("id"), facebookTestData.getAccessToken());
	facebookTestData.refreshTestUsers();
%>
<jsp:forward page="index.jsp"></jsp:forward>