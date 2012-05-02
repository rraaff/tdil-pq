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
<div id="header"></div>
<div id="container">
	<div id="loginBase">
		<html:form method="POST" action="/login">
			<div class="renglon width20">
				<div class="label width20"><span class="errorText"><html:errors property="general" /></span></div>
			</div>
			<div class="renglon width230">
				<html:hidden name="LoginForm" property="operation" value=""/>
				<div class="label width80">Usuario</div>
				<div class="label width150"><html:text name="LoginForm" property="username" styleClass="width150"/></div>
			</div>
			<div class="renglon width230" style="margin-bottom:20px;">
				<div class="label width80">Contrase&ntilde;a</div>
				<div class="label width150"><html:password name="LoginForm" property="password" styleClass="width150"/></div>
			</div>
			<html:submit property="operation">Login</html:submit>
		</html:form>
	</div>
</div>
</body>
</html>