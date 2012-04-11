<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ include file="includes/checkBoLogin.jsp" %>
<html>
<head>
<%@ include file="includes/boHead.jsp" %>
</head>

<body>

<%@ include file="includes/boMenu.jsp" %>

Country Administration


<html:form method="POST" action="/saveCountry">
<html:hidden name="CountryForm" property="operation" value=""/>
<span class="errorText"><html:errors property="general" /></span>
<html:text name="CountryForm" property="name"/><br>
<html:checkbox name="CountryForm" property="deleted"/>
<html:submit property="operation">Save</html:submit>
</html:form>

<html:link action="/editCountry.st?id=1">Edit</html:link>

</body>
</html>