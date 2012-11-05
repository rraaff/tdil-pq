<%@page import="com.tdil.tuafesta.struts.forms.ReviewProfesionalForm"%>
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
	<div id="boCentral" class="height450">
		<h1>Revisar datos del profesional</h1>
		<% ReviewProfesionalForm reviewProfesionalForm = (ReviewProfesionalForm)session.getAttribute("ReviewProfesionalForm");%>
		<div id="formulariosBase" class="height350">
			<div class="renglon width100per">
				<div class="label width100per"><span class="comment">Desde esta secci�n podr� revisar los datos del profesional.</span></div>
			</div>
				<div class="renglon width100per height100">
					<div class="label width100per height100">Nombre: <strong><bean:write name="ReviewProfesionalForm" property="profesional.firstname"/></strong><br>
						Apellido: <strong><bean:write name="ReviewProfesionalForm" property="profesional.lastname"/></strong><br>
						Raz&oacute;n Social: <strong><bean:write name="ReviewProfesionalForm" property="profesional.businessname"/></strong><br>
						E-mail: <strong><bean:write name="ReviewProfesionalForm" property="profesional.email"/></strong>
					</div>
				</div>
				<a href="./reviewPersonalData.jsp">Datos Personales <%=reviewProfesionalForm.isPersonalDataModified() ? "*" : ""%></a>
				<a href="./reviewBusinessData.jsp">Datos profesionales <%=reviewProfesionalForm.isBusinessDataModified() ? "*" : ""%></a>
				<a href="./reviewSellData.do?id=<%=reviewProfesionalForm.getProfesional().getId()%>">Productos/Servicios <%=reviewProfesionalForm.isSellsModified() ? "*" : ""%></a>
				<div class="renglon width100per">
					<div class="label width350 centering" style="float:none;">
						<a href="profesionalAdministration.jsp" style="margin-right:50px;">Volver</a>
					</div>
				</div>
		</div>
	</div>
</div>
</body>
</html>