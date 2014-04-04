<%@page import="com.tdil.ljpeugeot.services.PeugeotService"%>
<%@page import="com.tdil.ljpeugeot.model.NotificationEmail"%>
<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checkadmin.jspf" %><%--
--%><%@ include file="includes/checkemailaccess.jsp" %><%--
--%><%
NotificationEmail notificationEmail = PeugeotService.getNotificationEmail(Integer.parseInt(request.getParameter("id")));
notificationEmail.setContent(request.getParameter("content"));
notificationEmail.setFrom(request.getParameter("from"));
notificationEmail.setSubject(request.getParameter("subject"));
PeugeotService.udpateNotificationEmail(notificationEmail);
response.sendRedirect(request.getContextPath() + "/admin/notificationEmails.jsp");%>