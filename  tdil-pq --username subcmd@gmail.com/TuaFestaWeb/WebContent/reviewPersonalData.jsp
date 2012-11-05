<%@page import="com.tdil.tuafesta.model.ProfesionalChange"%>
<%@page import="com.tdil.tuafesta.struts.forms.ReviewProfesionalForm"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalStatusHelper"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalAdministrationForm"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
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
	<div id="boCentral" class="height450">
		<h1>Revisar datos personales del profesional</h1>
		<% ReviewProfesionalForm reviewProfesionalForm = (ReviewProfesionalForm)session.getAttribute("ReviewProfesionalForm");
			Profesional profesional = reviewProfesionalForm.getProfesional(); 
			ProfesionalChange change = reviewProfesionalForm.getProfesionalChange(); %>
		<div id="formulariosBase" class="height350">
			<div class="renglon width100per">
				<div class="label width100per"><span class="comment">Desde esta sección podrá revisar los datos del profesional.</span></div>
			</div>
			Nombre: <%=profesional.getFirstname()%> <%=!StringUtils.isEmpty(change.getFirstname()) ? " -> " + change.getFirstname() : ""%><br>
			Apellido: <%=profesional.getLastname()%> <%=!StringUtils.isEmpty(change.getLastname()) ? " -> " + change.getLastname() : ""%><br>
			Sexo: <%=profesional.getSex().equals("m") ? "Masculino" : "Femenino"%> <%=!StringUtils.isEmpty(change.getSex()) ? " -> " + (change.getSex().equals("m") ? "Masculino" : "Femenino") : ""%><br>
			Cod Area: <%=profesional.getPhoneareacode()%> <%=!StringUtils.isEmpty(change.getPhoneareacode()) ? " -> " + change.getPhoneareacode() : ""%><br>
			Numero: <%=profesional.getPhonenumber()%> <%=!StringUtils.isEmpty(change.getPhonenumber()) ? " -> " + change.getPhonenumber() : ""%><br>
			Extension: <%=profesional.getPhoneextension()%> <%=!StringUtils.isEmpty(change.getPhoneextension()) ? " -> " + change.getPhoneextension() : ""%><br>
			Tipo: <%=profesional.getPhonetype()%> <%=!StringUtils.isEmpty(change.getPhonetype()) ? " -> " + change.getPhonetype() : ""%><br>
		</div>
	</div>
	<html:form method="POST" action="/approveProfesionalPersonalDataChange">
		<div class="myRow width350" style="margin:0 auto; float:none;">
			<div class="myLabel width50" style="padding-top:16px;"><a href="reviewProfesional.jsp">Volver</a></div>
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
</body>
</html>