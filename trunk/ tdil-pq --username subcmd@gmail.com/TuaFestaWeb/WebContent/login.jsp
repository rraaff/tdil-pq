<%@ include file="includes/userLogged.jspf" %>
<%@ page info="login"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<html>
<head>
<%@ include file="includes/head.jsp"%>
<%@ include file="includes/boErrorJS.jsp"%>
</head>

<body>
<div id="headers">
	<div id="header">
		<div id="logo"><a href="./boHome.jsp" title="Volver al Inicio"><img src="images/skin_basic/logos/headerLogo.png" /></a></div>
		<div id="centralhead">
			<div id="border"></div>
			<div id="taglineAndMenu">
				<p>Login</p>
			</div>
		</div>
	</div>
</div>
<div id="boWrapper">
	<div id="boCentral">
		<div id="loginBase" class="width250">
			<html:form method="POST" action="/websiteLogin">
				<div class="renglon width250">
					<div class="label width250"><span class="errorText"><html:errors property="general" /></span></div>
				</div>
				<div class="renglon width250">
					<html:hidden name="WebsiteLoginForm" property="operation" value=""/>
					<div class="label width100">Email</div>
					<div class="label width150"><html:text name="WebsiteLoginForm" property="email" styleClass="width150"/></div>
				</div>
				<div class="renglon width250">
					<div class="label width100">Contrase&ntilde;a</div>
					<div class="label width150"><html:password name="WebsiteLoginForm" property="password" styleClass="width150"/></div>
				</div>
				<div class="renglon width250">
					<div class="label width250" align="center"><html:submit property="operation">Login</html:submit></div>
				</div>
			</html:form>
		</div>
	</div>
</div>
</body>
</html>