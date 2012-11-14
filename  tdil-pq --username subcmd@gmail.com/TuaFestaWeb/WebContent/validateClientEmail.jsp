<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
		<h1>Validar email del cliente</h1>
		<div id="formulariosBase" class="height350">
			<div class="myRow width100per">
				<div class="myLabel width100per"><span class="errorText">Desde esta secci&oacute;n podr&aacute; validar manualmente el E-mail del cliente. Si lo bloquea el cliente no podra ingresar al sistema ni registrarse nuevamente.</span></div>
			</div>
			<html:form method="POST" action="/manualValidateClientEmail">
				<div class="myRow">
					<div class="myLabel width90">Nombre</div>
					<div class="myLabel width800"><strong><bean:write name="ReviewClientForm" property="client.firstname"/></strong></div>
				</div>
				<div class="myRow">
					<div class="myLabel width90">Apellido</div>
					<div class="myLabel width800"><strong><bean:write name="ReviewClientForm" property="client.lastname"/></strong></div>
				</div>
				<div class="myRow">
					<div class="myLabel width90">E-mail</div>
					<div class="myLabel width800"><strong><bean:write name="ReviewClientForm" property="client.email"/></strong></div>
				</div>
				<div class="myRow width350" style="margin:0 auto; float:none;">
					<div class="myLabel width50" style="padding-top:16px;"><a href="clientAdministration.jsp">Volver</a></div>
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