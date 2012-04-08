<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>

<html:form method="POST" action="/login">
<html:hidden name="LoginForm" property="operation" value=""/>
<html:text name="LoginForm" property="username"/><br>
<html:password name="LoginForm" property="password"/><br>
<html:submit property="operation">Login</html:submit>

</html:form>
