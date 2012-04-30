<%@ page info="printExceptions"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<html>
<body>
<table border="1">
<% for (com.tdil.log4j.LogError logError : com.tdil.log4j.LoggerMonitorAppender.getErrors()) { %>
	<tr>
		<td>
			<%=logError.getExceptionMessage()%> at <%=logError.getDate()%><br>
			<%=logError.getExceptionStackTrace()%>
		</td>
	</tr>
<% } %>
</table>
</body>
</html>