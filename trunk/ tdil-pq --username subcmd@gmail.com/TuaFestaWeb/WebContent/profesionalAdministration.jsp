<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalStatusHelper"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalAdministrationForm"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@ page info="profesionalAdministration"%>
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
		<h1>Administraci&oacute;n de Profesionales</h1>
		<div id="formulariosBase">
			<div class="renglon">
				<div class="label"><span class="comment">Desde esta secci&oacute;n podr&aacute; operar sobre los profesionales.</span></div>
			</div>
			<html:form method="POST" action="/searchProfesionalAdministration">
				<h2>Busqueda de profesionales</h2>
				<div class="myRow"><span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span></div>
				<logic:equal name="ProfesionalAdministrationForm" property="tooMany" value="true">
					<div class="myRow"><p>El resultado de la busqueda es demasiado grande y la misma ah sido truncado, por favor refine su busqueda.</p></div>
				</logic:equal>
				<div class="myRow">
					<div class="myLabel width80">Nombre</div>
					<div class="myLabel width250"><html:text name="ProfesionalAdministrationForm" property="name" styleClass="normalField width200"/></div>
					<div class="myLabel width20"><html:checkbox name="ProfesionalAdministrationForm" property="dataModified"/></div>
					<div class="myLabel width250">Solo con datos pendientes de revision</div>
					<div class="myLabel width50">Estado</div>
					<div class="myLabel width200">
						<html:select name="ProfesionalAdministrationForm" property="status" styleClass="normalField width200">
						<% int selected = ((ProfesionalAdministrationForm)session.getAttribute("ProfesionalAdministrationForm")).getStatus(); %>
							<option <%=	selected == -1 ? "selected" : ""%> value="-1">Todos</option>
							<option <%=	selected == 0 ? "selected" : ""%> value="0">Validacion de email pendiente</option>
							<option <%=	selected == 1 ? "selected" : ""%> value="1">Verificacion pendiente</option>
							<option <%=	selected == 2 ? "selected" : ""%> value="2">Aprobado</option>
							<option <%=	selected == 4 ? "selected" : ""%> value="4">Bloqueados</option>
						</html:select>
					</div>
				</div>
				<div class="myRow" align="center"><html:submit property="operation">Buscar</html:submit></div>
			</html:form>
			<div class="renglon" align="center">
				<display:table name="test" sort="external" pagesize="10" id="testit" requestURI="./profesionalAdministration.jsp">
					<display:column title="Nombre" sortable="true" sortName="Nombre" headerClass="sortable width250" property="firstname"></display:column>
					<display:column title="Apellido" sortable="true" sortName="Apellido" headerClass="sortable width250" property="lastname"></display:column>
					<display:column title="E-mail" sortable="true" sortName="Email" headerClass="sortable width180" property="email"></display:column>
					<display:column title="Estado" headerClass="sortable width150"><%= ProfesionalStatusHelper.getStatusFor((Profesional)pageContext.getAttribute("testit"))%></display:column>
					<display:column title="Acciones" headerClass="sortable width80"><a href="reviewProfesional.do?id=<%=((Profesional)pageContext.getAttribute("testit")).getId()%>">Revisar</display:column>
				</display:table>
			</div>
		</div>
	</div>
</div>
</body>
</html>