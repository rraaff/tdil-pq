<%@page import="com.tdil.tuafesta.model.valueobjects.AdvertisementValueObject"%>
<%@page import="com.tdil.tuafesta.model.Sell"%>
<%@page import="com.tdil.tuafesta.model.AdTarget"%>
<%@page import="com.tdil.tuafesta.struts.forms.AdvertisementForm"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.tuafesta.struts.forms.AdvertisementForm"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@ page info="boAdvertisementAdministration"%>
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

			$("input[name=profesionalDateSearch]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
				changeYear: true, yearRange: "2012:2020"});


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
			<html:form method="POST" action="/saveAdvertisement">
				<h1>Administraci&oacute;n de Avisos</h1>
				<div class="renglon width950">
					<div class="label width950"><span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span></div>
				</div>
				<% AdvertisementForm adForm = (AdvertisementForm)session.getAttribute("AdvertisementForm");%>
				<% if (adForm.getProfesional() == null) { %>
					<div id="searchProfToHighLayer">
					Profesional: <html:text name="AdvertisementForm" property="profesionalNameSearch"/><br>
					<div class="myRow">
					<%
					java.util.List source = adForm.getProfesionalSearch();
					com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
					request.setAttribute( "highlightedProfesionals",  paginated);
					%>
					<display:table name="highlightedProfesionals" sort="external" pagesize="10" id="highlightedProfesionals" requestURI="./highlightedProfesionalAdministration.jsp">
						<display:column title="Nombre" sortable="true" sortName="Nombre" headerClass="sortable width250" property="firstname"></display:column>
						<display:column title="Apellido" sortable="true" sortName="Apellido" headerClass="sortable width250" property="lastname"></display:column>
						<display:column title="BusinessName" sortable="true" sortName="BusinessName" headerClass="sortable width100" property="businessname"></display:column>
						<display:column title="acciones" headerClass="sortable width50">
							<a class="nonelyLink" href="javascript:document.AdvertisementForm.action='./selectProfesionalForAd.do?id=<%= ((Profesional)pageContext.getAttribute("highlightedProfesionals")).getId()%>';document.AdvertisementForm.submit();">Seleccionar</a>
						</display:column>
					</display:table>
					</div>
					<%=DisplayTagParamHelper.getFields(request)%>
					<a class="nonelyLink" href="javascript:document.AdvertisementForm.action='./searchProfesionalsForAd.do';document.AdvertisementForm.submit();">Buscar</a>
				</div>
				<% } else {  %>
					<%=  adForm.getProfesional().getBusinessname() %>
					<html:radio property="adTarget" value="1" onchange="document.AdvertisementForm.action='./adTypeChangedAction.do';document.AdvertisementForm.submit();"/> Profesional&nbsp;&nbsp;&nbsp;
					<html:radio property="adTarget" value="2" onchange="document.AdvertisementForm.action='./adTypeChangedAction.do';document.AdvertisementForm.submit();"/>Producto/Servicio
					<% if (adForm.getAdTarget() == AdTarget.PROFESIONAL) { %>
						<br>Por profesional<br>
						<div class="renglon width950">					
							<div class="label width100">Inicio</div>
							<div class="label width250"><html:text name="AdvertisementForm" property="fromDate" styleClass="width250"/></div>
							<div class="label width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "HighlightedProfesional.fromDate.err")%></div>
							<div class="label width100">Fin</div>
							<div class="label width250"><html:text name="AdvertisementForm" property="toDate" styleClass="width250"/></div>
							<div class="label width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "HighlightedProfesional.toDate.err")%></div>
							<div class="label width100">Pago</div>
							<div class="label width250"><html:checkbox name="AdvertisementForm" property="paidup" styleClass="width250"/></div>
							<div class="label width100">Precio</div>
							<div class="label width250"><html:text name="AdvertisementForm" property="price" styleClass="width250"/></div>
							<div class="label width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "HighlightedProfesional.payment.err")%></div>
							<html:radio property="type" value="1"/> Normal&nbsp;&nbsp;&nbsp;
							<html:radio property="type" value="2" />Superdestacado
						</div>
						<div class="renglon width950 height50" align="center">
							<logic:equal name="AdvertisementForm" property="objectId" value="0">
								<html:submit property="operation">
									<bean:message key="save" />
								</html:submit>
							</logic:equal>
							<logic:notEqual name="AdvertisementForm" property="objectId" value="0">
								<html:submit property="operation">
									<bean:message key="modify" />
								</html:submit>
							</logic:notEqual>
							<html:submit property="operation">
								<bean:message key="reset" />
							</html:submit>
						</div>
					<% } else { %>
						<br>Por producto/servicio<br>
						<% if (adForm.getSell() == null) { %>
							<div class="myRow">
								<%
								java.util.List source = adForm.getSells();
								com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
								request.setAttribute( "sells",  paginated);
								%>
								<display:table name="sells" sort="external" pagesize="10" id="sells" requestURI="./homeProfesional.jsp">
									<display:column title="Nombre" sortable="true" sortName="Producto" headerClass="sortable width250" property="name"></display:column>
									<display:column title="acciones" headerClass="sortable width50">
										<a class="nonelyLink" href="javascript:document.AdvertisementForm.action='./selectSellForAd.do?id=<%= ((Sell)pageContext.getAttribute("sells")).getId()%>';document.AdvertisementForm.submit();">Seleccionar</a>
									</display:column>
								</display:table>
								<%=DisplayTagParamHelper.getFields(request)%>
							</div>
						<% } else { %>
							<%=adForm.getSell().getName() %>
							<div class="renglon width950">					
								<div class="label width100">Inicio</div>
								<div class="label width250"><html:text name="AdvertisementForm" property="fromDate" styleClass="width250"/></div>
								<div class="label width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "HighlightedProfesional.fromDate.err")%></div>
								<div class="label width100">Fin</div>
								<div class="label width250"><html:text name="AdvertisementForm" property="toDate" styleClass="width250"/></div>
								<div class="label width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "HighlightedProfesional.toDate.err")%></div>
								<div class="label width100">Pago</div>
								<div class="label width250"><html:checkbox name="AdvertisementForm" property="paidup" styleClass="width250"/></div>
								<div class="label width100">Precio</div>
								<div class="label width250"><html:text name="AdvertisementForm" property="price" styleClass="width250"/></div>
								<div class="label width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "HighlightedProfesional.payment.err")%></div>
								<html:radio property="type" value="1"/> Normal&nbsp;&nbsp;&nbsp;
								<html:radio property="type" value="2" />Superdestacado
							</div>
							<div class="renglon width950 height50" align="center">
								<logic:equal name="AdvertisementForm" property="objectId" value="0">
									<html:submit property="operation">
										<bean:message key="save" />
									</html:submit>
								</logic:equal>
								<logic:notEqual name="AdvertisementForm" property="objectId" value="0">
									<html:submit property="operation">
										<bean:message key="modify" />
									</html:submit>
								</logic:notEqual>
								<html:submit property="operation">
									<bean:message key="reset" />
								</html:submit>
							</div>
						<% } %>
					<% } %>
					
				<% } %>
				
				
				<h1>Listado de Profesionales destacados</h1>
				<div class="renglon width950" style="margin-bottom:50px;">
					Nombre: <html:text name="AdvertisementForm" property="profesionalNameSearchAd"/><br>
					Activo en: <html:text name="AdvertisementForm" property="profesionalDateSearch"/><br>
					<a class="nonelyLink" href="javascript:document.AdvertisementForm.action='./searchAds.do';document.AdvertisementForm.submit();">Buscar</a>
					<table width="100%">
						<tr>
							<td class="headerTablas" width="140">Profesional</td>
							<td class="headerTablas" width="140">Producto/Servicio</td>
							<td class="headerTablas" width="140">Desde</td>
							<td class="headerTablas" width="140">Hasta</td>
							<td class="headerTablas" width="50">Acciones</td>
						</tr>
						<logic:iterate name="AdvertisementForm" property="search"
							id="iterSection" indexId="iterIndex">
							<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
								<td
									<%=((AdvertisementValueObject) iterSection).getDeleted() == 1 ? "class=\"notActive\"" : "" %>
									align="left"><bean:write name="iterSection" property="profesionalBusinessName" />
								</td>
								<td
									<%=((AdvertisementValueObject) iterSection).getDeleted() == 1 ? "class=\"notActive\"" : "" %>
									align="left"><bean:write name="iterSection" property="sellName" />
								</td>
								<td
									<%=((AdvertisementValueObject) iterSection).getDeleted() == 1 ? "class=\"notActive\"" : "" %>
									align="left"><bean:write name="iterSection" property="formatedfromdate" />
								</td>
								<td
									<%=((AdvertisementValueObject) iterSection).getDeleted() == 1 ? "class=\"notActive\"" : "" %>
									align="left"><bean:write name="iterSection" property="formatedtodate" />
								</td>
								<td>
									<html:link action="editAd.st?" paramName="iterSection" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
									<html:link action="/toggleDeletedAd" paramName="iterSection"
										paramProperty="id" paramId="id">
										<% if (((AdvertisementValueObject) iterSection).getDeleted() == 1) { %>
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