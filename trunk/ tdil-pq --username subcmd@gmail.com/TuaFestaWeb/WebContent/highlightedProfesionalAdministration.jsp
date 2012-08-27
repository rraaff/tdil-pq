<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.model.valueobjects.HighlightedProfesionalValueObject"%>
<%@page import="com.tdil.tuafesta.model.HighlightedProfesional"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.tuafesta.struts.forms.HighlightedProfesionalForm"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@ page info="highlightedProfesionalAdministration"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="includes/checkBoLogin.jsp"%>
<html>
<head>
<%@ include file="includes/boHead.jsp"%>
<script>
$(document).ready(
	function(){

			$("input[name=fromDate]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
				changeYear: true, yearRange: "1900:2012"});
			$("input[name=toDate]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
				changeYear: true, yearRange: "1900:2012"});

			$( "#openSearchProfToHigh" ).click(function() {
				$("#searchProfToHighLayer").css('display', 'block');
			});
			$( "#closeSearchProfToHigh" ).click(function() {
				$("#searchProfToHighLayer").css('display', 'none');
			});

	}	
);

</script>
<%@ include file="includes/boErrorJS.jsp"%>
</head>

<body>
<%@ include file="includes/boMenu.jsp"%>
<div id="boWrapper">
	<div id="boCentral">
		<div id="formulariosBase">
			<html:form method="POST" action="/saveHighlightedProfesional">
				<h1>Administraci&oacute;n de Profesionales destacados</h1>
				<div class="renglon width950">
					<div class="label width950"><span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span></div>
				</div>
				TODO aca va el profesional, mas un link que muestre un layer de busqueda de profesional
				Desde el layer tengo un link que selecciona el profesional
				
				<a id="openSearchProfToHigh">Buscar</a>
				<div id="searchProfToHighLayer" style="display: none;">
					Nombre: <html:text name="HighlightedProfesionalForm" property="firstNameSearch"/><br>
					Apellido: <html:text name="HighlightedProfesionalForm" property="lastNameSearch"/><br>
					BusinessName: <html:text name="HighlightedProfesionalForm" property="businessNameSearch"/><br>	
					<div class="myRow">
					<%
					HighlightedProfesionalForm highlightedProfesionalForm = (HighlightedProfesionalForm)session.getAttribute("HighlightedProfesionalForm");
					java.util.List source = highlightedProfesionalForm.getProfesionalSearch();
					com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
					request.setAttribute( "highlightedProfesionals",  paginated);
					%>
					<display:table name="highlightedProfesionals" sort="external" pagesize="10" id="highlightedProfesionals" requestURI="./highlightedProfesionalAdministration.jsp">
						<display:column title="Nombre" sortable="true" sortName="Nombre" headerClass="sortable width250" property="firstname"></display:column>
						<display:column title="Apellido" sortable="true" sortName="Apellido" headerClass="sortable width250" property="lastname"></display:column>
						<display:column title="BusinessName" sortable="true" sortName="BusinessName" headerClass="sortable width100" property="businessname"></display:column>
						<display:column title="acciones" headerClass="sortable width50"><a class="nonelyLink" href="javascript:document.HighlightedProfesionalForm.action='./selectProfesionalToHighlight.do?index=<%= ((Profesional)pageContext.getAttribute("highlightedProfesionals")).getId()%>';document.HighlightedProfesionalForm.submit();">Seleccionar</a>
						</display:column>
					</display:table>
					</div>
					<%=DisplayTagParamHelper.getFields(request)%>	
					<a id="closeSearchProfToHigh">Cerrar</a>
				</div>
				<div class="renglon width950">					
					<div class="label width100">Inicio</div>
					<div class="label width250"><html:text name="HighlightedProfesionalForm" property="fromDate" styleClass="width250"/></div>
					<div class="label width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "HighlightedProfesional.fromDate.err")%></div>
					<div class="label width100">Fin</div>
					<div class="label width250"><html:text name="HighlightedProfesionalForm" property="toDate" styleClass="width250"/></div>
					<div class="label width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "HighlightedProfesional.toDate.err")%></div>
				</div>
				<div class="renglon width950 height50" align="center">
					<logic:equal name="HighlightedProfesionalForm" property="objectId" value="0">
						<html:submit property="operation">
							<bean:message key="save" />
						</html:submit>
					</logic:equal>
					<logic:notEqual name="HighlightedProfesionalForm" property="objectId" value="0">
						<html:submit property="operation">
							<bean:message key="modify" />
						</html:submit>
					</logic:notEqual>
					<html:submit property="operation">
						<bean:message key="reset" />
					</html:submit>
				</div>
				<h1>Listado de Profesionales destacados</h1>
				<div class="renglon width950" style="margin-bottom:50px;">
					TODO aca va la busqueda de profesionales
					<table width="100%">
						<tr>
							<td class="headerTablas" width="140">Profesional</td>
							<td class="headerTablas" width="140">Desde</td>
							<td class="headerTablas" width="140">Hasta</td>
							<td class="headerTablas" width="50">Acciones</td>
						</tr>
						<logic:iterate name="HighlightedProfesionalForm" property="search"
							id="iterSection" indexId="iterIndex">
							<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
								<td
									<%=((HighlightedProfesionalValueObject) iterSection).getDeleted() == 1 ? "class=\"notActive\"" : "" %>
									align="left"><bean:write name="iterSection" property="profesionalBusinessName" />
								</td>
								<td
									<%=((HighlightedProfesionalValueObject) iterSection).getDeleted() == 1 ? "class=\"notActive\"" : "" %>
									align="left"><bean:write name="iterSection" property="formatedfromdate" />
								</td>
								<td
									<%=((HighlightedProfesionalValueObject) iterSection).getDeleted() == 1 ? "class=\"notActive\"" : "" %>
									align="left"><bean:write name="iterSection" property="formatedtodate" />
								</td>
								<td>
									<html:link action="editHighlightedProfesional.st?" paramName="iterSection" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
									<html:link action="/toggleDeletedHighlightedProfesional" paramName="iterSection"
										paramProperty="id" paramId="id">
										<% if (((HighlightedProfesional) iterSection).getDeleted() == 1) { %>
											<img src="boImages/activar.png" alt="Activar">
										<% } else { %>
											<img src="boImages/desactivar.png" alt="Desactivar">
										<% } %>
									</html:link>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</div>
			</html:form>
		</div>
	</div>
</div>
</body>
</html>