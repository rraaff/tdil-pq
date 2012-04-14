<%@ page info="boLogin"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<html>
<head>
<%@ include file="includes/boHead.jsp"%>
<%@ include file="includes/boErrorJS.jsp"%>
</head>

<body>
<html:form method="POST" action="/login">
<html:hidden name="LoginForm" property="operation" value=""/>
<html:text name="LoginForm" property="username"/><span class="errorText"><html:errors property="general" /></span><br>
<html:password name="LoginForm" property="password"/><br>
<html:submit property="operation">Login</html:submit>

</html:form>

</body>
</html>