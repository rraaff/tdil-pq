<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalStatusHelper"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalAdministrationForm"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@ page info="validateProfesionalEmail"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="includes/checkBoLogin.jsp"%>
<html>
<head>
<%@ include file="includes/boHead.jsp"%>
<%@ include file="includes/boErrorJS.jsp"%>
</head>
<body>
<%@ include file="includes/boMenu.jsp" %>
<div id="boWrapper">
	<div id="boCentral" class="height450">
		<h1>Validar email del profesional</h1>
		<div id="formulariosBase" class="height350">
			<div class="myRow width100per">
				<div class="myLabel width100per"><span class="errorText">Desde esta secci&oacute;n podr&aacute; validar manualmente el E-mail del profesional. Si lo bloquea el profesional no podra ingresar al sistema ni registrarse nuevamente.</span></div>
			</div>
			<html:form method="POST" action="/manualValidateProfesionalEmail">
				<div class="myRow">
					<div class="myLabel width90">Nombre</div>
					<div class="myLabel width800"><strong><bean:write name="ReviewProfesionalForm" property="profesional.firstname"/></strong></div>
				</div>
				<div class="myRow">
					<div class="myLabel width90">Apellido</div>
					<div class="myLabel width800"><strong><bean:write name="ReviewProfesionalForm" property="profesional.lastname"/></strong></div>
				</div>
				<div class="myRow">
					<div class="myLabel width90">Razon Social</div>
					<div class="myLabel width800"><strong><bean:write name="ReviewProfesionalForm" property="profesional.businessname"/></strong></div>
				</div>
				<div class="myRow">
					<div class="myLabel width90">E-mail</div>
					<div class="myLabel width800"><strong><bean:write name="ReviewProfesionalForm" property="profesional.email"/></strong></div>
				</div>
				<div class="myRow width350" style="margin:0 auto; float:none;">
					<div class="myLabel width50" style="padding-top:16px;"><a href="profesionalAdministration.jsp">Volver</a></div>
					<div class="myLabel width300">
						<html:submit property="operation">
							<bean:message key="Approve" />
						</html:submit>
						<html:submit property="operation">
							<bean:message key="Block" />
						</html:submit>
					</div>
				</div>
			</html:form>
		</div>
	</div>
</div>
</body>
</html>