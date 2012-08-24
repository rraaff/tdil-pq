<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalStatusHelper"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalAdministrationForm"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@ page info="index"%>
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
<%
	ProfesionalAdministrationForm postItAdministrationForm = (ProfesionalAdministrationForm)session.getAttribute("ProfesionalAdministrationForm");
java.util.List source = postItAdministrationForm.getSearch();
com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
request.setAttribute( "test",  paginated);
%>
<body>
<%@ include file="includes/boMenu.jsp" %>
<div id="boWrapper">
	<div id="boCentral">
		<div id="formulariosBase">
		<h1>Profesionales</h1>
		<div class="renglon width950" style="margin-bottom:20px;">
			<div class="label width950"><span class="comment">Desde esta sección podrá operar sobre los profesionales.</span></div>
		</div>
		<div class="renglon width950" style="margin-bottom:50px;">
			<display:table name="test" sort="external" pagesize="10" id="testit" requestURI="./profesionalAdministration.jsp">
				<display:column title="Nombre" sortable="true" sortName="Nombre" headerClass="sortable width200" property="firstname"></display:column>
				<display:column title="Apellido" sortable="true" sortName="Apellido" headerClass="sortable width200" property="lastname"></display:column>
				<display:column title="E-mail" sortable="true" sortName="Email" headerClass="sortable width200" property="email"></display:column>
				<display:column title="Estado" headerClass="sortable width150"><%= ProfesionalStatusHelper.getStatusFor((Profesional)pageContext.getAttribute("testit"))%></display:column>
				<display:column title="Acciones" headerClass="sortable width150"><a href="reviewProfesional.do?id=<%=((Profesional)pageContext.getAttribute("testit")).getId()%>">Revisar</display:column>
			</display:table>
		</div>
	</div>
</div>
</body>
</html>