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

<%
	ProfesionalAdministrationForm postItAdministrationForm = (ProfesionalAdministrationForm)session.getAttribute("ProfesionalAdministrationForm");
java.util.List source = postItAdministrationForm.getSearch();
com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
request.setAttribute( "test",  paginated);
%>
<body>
<div id="header"><%@ include file="includes/boMenu.jsp" %></div>
<div id="container">
	<h1 align="center">Profesionales</h1>
	<div class="renglon width860" style="margin-bottom:20px;">
		<div class="label width860"><span class="comment">Desde esta sección podrá operar sobre los profesionales.</span></div>
	</div>
	<div class="renglon width860">
		<display:table name="test" sort="external" pagesize="10" id="testit" requestURI="./profesionalAdministration.jsp">
			<display:column title="Nombre" sortable="true" sortName="Nombre" headerClass="sortable" property="firstname"></display:column>
			<display:column title="Apellido" sortable="true" sortName="Apellido" headerClass="sortable" property="lastname"></display:column>
			<display:column title="Email" sortable="true" sortName="Email" headerClass="sortable" property="email"></display:column>
			<display:column title="Estado" headerClass="width100"><%= ProfesionalStatusHelper.getStatusFor((Profesional)pageContext.getAttribute("testit"))%></display:column>
			<display:column title="Acciones" headerClass="width100"><a href="reviewProfesional.do?id=<%=((Profesional)pageContext.getAttribute("testit")).getId()%>">Revisar</display:column>
		</display:table>
	</div>
</div>
</body>
</html>