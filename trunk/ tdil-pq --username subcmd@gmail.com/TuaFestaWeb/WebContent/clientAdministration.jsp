<%@page import="com.tdil.tuafesta.model.Client"%>
<%@page import="com.tdil.tuafesta.struts.forms.ClientStatusHelper"%>
<%@page import="com.tdil.tuafesta.struts.forms.ClientAdministrationForm"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalStatusHelper"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ClientAdministrationForm"%>
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
	ClientAdministrationForm postItAdministrationForm = (ClientAdministrationForm)session.getAttribute("ClientAdministrationForm");
java.util.List source = postItAdministrationForm.getSearch();
com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
request.setAttribute( "test",  paginated);
%>
<body>
<%@ include file="includes/boMenu.jsp" %>
<div id="boWrapper">
	<div id="boCentral" class="height450">
		<h1>Administraci&oacute;n de Clientes</h1>
		<div id="formulariosBase" class="height350">
			<div class="renglon width100per">
				<div class="label width100per"><span class="comment">Desde esta secci&oacute;n podr&aacute; operar sobre los clientes.</span></div>
			</div>
			<html:form method="POST" action="/searchClientAdministration">
				<div style="float:left;" class="width350">
					<h2>Busqueda de clientes</h2>
					<div class="myRow"><span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span></div>
					<logic:equal name="ClientAdministrationForm" property="tooMany" value="true">
						El resultado de la busqueda es demasiado grande y la misma ah sido truncado, por favor refine su busqueda.
					</logic:equal>
					<div class="myRow">
						<div class="myLabel width80">Nombre</div>
						<div class="myLabel width250"><html:text name="ClientAdministrationForm" property="name" styleClass="normalField width200"/></div>
					</div>
					Estado:
							<html:select name="ClientAdministrationForm" property="status" styleClass="normalField width240">
							<% int selected = ((ClientAdministrationForm)session.getAttribute("ClientAdministrationForm")).getStatus(); %>
							<option <%=	selected == 0 ? "selected" : ""%> value="0">Validacion de email pendiente</option>
							<option <%=	selected == 2 ? "selected" : ""%> value="2">Aprobado</option>
							<option <%=	selected == 4 ? "selected" : ""%> value="4">Bloqueados</option>
						</html:select>
					<html:submit property="operation">
						Buscar
					</html:submit>
				</div>
			</html:form>
			<div class="renglon width950" style="margin-bottom:50px;">
				<display:table name="test" sort="external" pagesize="10" id="testit" requestURI="./clientAdministration.jsp">
					<display:column title="Nombre" sortable="true" sortName="Nombre" headerClass="sortable width250" property="firstname"></display:column>
					<display:column title="Apellido" sortable="true" sortName="Apellido" headerClass="sortable width250" property="lastname"></display:column>
					<display:column title="E-mail" sortable="true" sortName="Email" headerClass="sortable width180" property="email"></display:column>
					<display:column title="Estado" headerClass="sortable width150"><%= ClientStatusHelper.getStatusFor((Client)pageContext.getAttribute("testit"))%></display:column>
					<display:column title="Acciones" headerClass="sortable width80"><a href="reviewClient.do?id=<%=((Client)pageContext.getAttribute("testit")).getId()%>">Revisar</display:column>
				</display:table>
			</div>
		</div>
	</div>
</div>
</body>
</html>