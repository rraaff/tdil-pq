<%@page import="com.tdil.ljpeugeot.services.PeugeotService"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="org.apache.poi.util.StringUtil"%>
<%@page import="com.tdil.ljpeugeot.model.NotificationEmail"%>
<%@page import="javax.management.Notification"%>
<%@page import="com.tdil.ljpeugeot.model.SystemProperty"%>
<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checkadmin.jspf" %><%--
--%><html>
<body>
<h1>Notificaciones por email</h1>
<%@ include file="includes/menu.jspf" %>

Cambiar notificacion
<form action="./doUpdateEmail.jsp">
<% String id =  request.getParameter("id");
	NotificationEmail notificationEmail = null;
	if (!StringUtils.isEmpty(id)) {
		notificationEmail = PeugeotService.getNotificationEmail(Integer.parseInt(id));
	}
	if (notificationEmail != null && !("1".equals(request.getParameter("test")))) { %>
<input type="hidden" name="id" value="<%=id%>">
Tipo <input type="text" style="width: 250px;" readonly="true" name="notificationtype" value="<%=notificationEmail.getNotificationtype()%>"><br>
Asunto <input type="text" style="width: 250px;" name="subject" value="<%=notificationEmail.getSubject()%>"><br>
Desde <input type="text" style="width: 250px;" name="from" value="<%=notificationEmail.getFrom()%>"><br>
Reemplazos: <%=notificationEmail.getReplacements()%><br>
Contenido <textarea rows="10" cols="80" name="content"><%=notificationEmail.getContent()%></textarea><br>
<input type="submit" value="Guardar"><br>
</form>
<% } %>

<hr>

<table border="1">
<tr>
	<td>Tipo</td>
	<td>Asunto</td>
	<td>Desde</td>
	<td>Editar</td>
</tr>
<%
	for (NotificationEmail ne : PeugeotService.getNotificationEmails()) {
%>
<tr>
	<td><%=ne.getNotificationtype()%></td>
	<td><%=ne.getSubject()%></td>
	<td><%=ne.getFrom()%></td>
	<td><a href="./notificationEmails.jsp?id=<%=ne.getId()%>">Editar</a></td>
</tr>
<% } %>
</table>
</body>
</html>