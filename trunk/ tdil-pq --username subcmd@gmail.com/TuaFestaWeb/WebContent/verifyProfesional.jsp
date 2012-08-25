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
<div id="header"><%@ include file="includes/boMenu.jsp" %></div>
<div id="container">
	<h1 align="center">Verificar profesional</h1>
	<div class="renglon width860" style="margin-bottom:20px;">
		<div class="label width860"><span class="comment">Desde esta sección podrá aprobar o desaprobar nuevos profesionales.</span></div>
	</div>
	<html:form method="POST" action="/verifyProfesional">
		Nombre: <bean:write name="ReviewProfesionalForm" property="profesional.firstname"/> <br>
		Apellido: <bean:write name="ReviewProfesionalForm" property="profesional.lastname"/> <br>
		Razon Social: <bean:write name="ReviewProfesionalForm" property="profesional.businessname"/> <br>
		Email: <bean:write name="ReviewProfesionalForm" property="profesional.email"/> <br>
	
		<html:submit property="operation">
			<bean:message key="Approve" />
		</html:submit>
		<html:submit property="operation">
			<bean:message key="Block" />
		</html:submit>
	</html:form>
	
<a href="profesionalAdministration.jsp">Volver</a>
</div>
</body>
</html>