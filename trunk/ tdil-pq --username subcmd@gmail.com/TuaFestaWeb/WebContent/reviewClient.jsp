<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalStatusHelper"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalAdministrationForm"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@ page info="verifyProfesional"%>
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
<style>
th.sorted a,th.sortable a {
	background-position: right;
	display: block;
	width: 100%;
}

th.sortable a {
	background-image: url(img/displaytag/arrow_off.png);
}

th.order1 a {
	background-image: url(img/displaytag/arrow_down.png);
}

th.order2 a {
	background-image: url(img/displaytag/arrow_up.png);
}

tr.odd {
	background-color: #fff
}

tr.tableRowEven,tr.even {
	background-color: #fea
}
th.sorted {
	background-color: orange;
}
</style>
</head>

<body>
<%@ include file="includes/boMenu.jsp" %>
<div id="boWrapper">
	<div id="boCentral">
		<h1>Cliente aprobado</h1>
		<div id="formulariosBase">
			<div class="renglon"><span class="comment">Desde esta sección podrá revisar un cliente aprobado.</span></div>
			<html:form method="POST" action="/manualValidateClientEmail">
				<div class="renglon">Nombre: <strong><bean:write name="ReviewClientForm" property="client.firstname"/></strong><br/>Apellido: <strong><bean:write name="ReviewClientForm" property="client.lastname"/></strong><br/>E-mail: <strong><bean:write name="ReviewClientForm" property="client.email"/></strong></div>
				<div class="renglon" align="center">
					<a href="clientAdministration.jsp" style="margin-right:50px;">Volver</a>
					<html:submit property="operation">
						<bean:message key="Block" />
					</html:submit>
				</div>
			</html:form>
		</div>
	</div>
</div>
</body>
</html>