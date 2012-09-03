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
	<div id="boCentral">
		<div id="formulariosBase">
			<h1>Validar email del profesional</h1>
			<div class="renglon width950">
				<div class="label width950"><span class="errorText">Desde esta secci&oacute;n podr&aacute; validar manualmente el E-mail del profesional. Si lo bloquea el profesional no podra ingresar al sistema ni registrarse nuevamente.</span></div>
			</div>
			<html:form method="POST" action="/manualValidateProfesionalEmail">
				<div class="renglon width950 bordeGris bordeBottomNo">
					<div class="label width100">Nombre</div>
					<div class="label width850"><bean:write name="ReviewProfesionalForm" property="profesional.firstname"/></div>
				</div>
				<div class="renglon width950 bordeGris bordeBottomNo">
					<div class="label width100">Apellido</div>
					<div class="label width850"><bean:write name="ReviewProfesionalForm" property="profesional.lastname"/></div>
				</div>
				<div class="renglon width950 bordeGris bordeBottomNo">
					<div class="label width100">Razon Social</div>
					<div class="label width850"><bean:write name="ReviewProfesionalForm" property="profesional.businessname"/></div>
				</div>
				<div class="renglon width950 bordeGris">
					<div class="label width100">E-mail</div>
					<div class="label width850"><bean:write name="ReviewProfesionalForm" property="profesional.email"/></div>
				</div>
				<div class="renglon width950">
					<div class="label width950" align="center">
						<html:submit property="operation">
							<bean:message key="Approve" />
						</html:submit>
						<html:submit property="operation">
							<bean:message key="Block" />
						</html:submit>
					</div>
				</div>
			</html:form>
			<div class="renglon width950">
				<div class="label width950" align="center"><a href="profesionalAdministration.jsp">Volver</a></div>
			</div>
		</div>
	</div>
</div>
</body>
</html>