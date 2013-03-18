<%@page import="com.facebook.util.CreateTestUser"%>
<%@page import="com.facebook.FacebookTestData"%>
<%@page import="com.facebook.util.GetAccessToken"%>
<%
	FacebookTestData facebookTestData = (FacebookTestData)session.getAttribute("FacebookTestData");
	CreateTestUser.create(facebookTestData.getAppId(), facebookTestData.getAccessToken(), request.getParameter("firstname") + "%20" + request.getParameter("lastname"));
	facebookTestData.refreshTestUsers();
%>
<jsp:forward page="index.jsp"></jsp:forward>