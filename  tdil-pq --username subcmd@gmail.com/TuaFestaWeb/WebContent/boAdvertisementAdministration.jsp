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

			$("input[name=fromDate]").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true,
				changeYear: true, yearRange: "1900:2020"});
			$("input[name=toDate]").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true,
				changeYear: true, yearRange: "1900:2020"});

			$("input[name=profesionalDateSearch]").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true,
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
<style>
h2 {
	margin-top:15px;
	margin-bottom:15px;
}
</style>
</head>

<body>
<%@ include file="includes/boMenu.jsp"%>
<div id="boWrapper">
	<div id="boCentral">
		<h1>Administraci&oacute;n de Avisos</h1>
		<div id="formulariosBase" class="height500">
			<html:form method="POST" action="/saveAdvertisement">
				<div class="myRow" align="center"><span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span></div>
				<% AdvertisementForm adForm = (AdvertisementForm)session.getAttribute("AdvertisementForm");%>
				<% if (adForm.getProfesional() == null) { %>
					<h2>Buscar el profesional</h2>
					<div class="myRow">
						<div id="searchProfToHighLayer">
							<div class="myLabel width80">Profesional</div>
							<div class="myLabel width210"><html:text name="AdvertisementForm" property="profesionalNameSearch" styleClass="normalfield width200" /></div>
							<div class="myLabel width210"><%=DisplayTagParamHelper.getFields(request)%><a class="nonelyLink" href="javascript:document.AdvertisementForm.action='./searchProfesionalsForAd.do';document.AdvertisementForm.submit();">Buscar</a></div>
						</div>
					</div>
					<div class="myRow" style="margin-top:20px; margin-bottom:20px;">
						<%
						java.util.List source = adForm.getProfesionalSearch();
						com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
						request.setAttribute( "highlightedProfesionals",  paginated);
						%>
						<display:table name="highlightedProfesionals" sort="external" pagesize="10" id="highlightedProfesionals" requestURI="./highlightedProfesionalAdministration.jsp">
							<display:column title="Nombre" sortable="true" sortName="Nombre" headerClass="sortable width250" property="firstname"></display:column>
							<display:column title="Apellido" sortable="true" sortName="Apellido" headerClass="sortable width250" property="lastname"></display:column>
							<display:column title="Nombre Profesional" sortable="true" sortName="BusinessName" headerClass="sortable width250" property="businessname"></display:column>
							<display:column title="acciones" headerClass="sortable width150"><a class="nonelyLink" href="javascript:document.AdvertisementForm.action='./selectProfesionalForAd.do?id=<%= ((Profesional)pageContext.getAttribute("highlightedProfesionals")).getId()%>';document.AdvertisementForm.submit();">Seleccionar</a></display:column>
						</display:table>
					</div>
				<% } else {  %>
					<div class="myRow">
						<div class="myLabel width920">Aviso de <strong><%=  adForm.getProfesional().getBusinessname() %></strong></div>
					</div>
					<div class="myRow">
						<div class="myLabel width920">Tipo de aviso: <html:radio property="adTarget" value="1" onchange="document.AdvertisementForm.action='./adTypeChangedAction.do';document.AdvertisementForm.submit();"/> Profesional&nbsp;&nbsp;&nbsp;<html:radio property="adTarget" value="2" onchange="document.AdvertisementForm.action='./adTypeChangedAction.do';document.AdvertisementForm.submit();"/>Producto/Servicio</div>
					</div>
					<% if (adForm.getAdTarget() == AdTarget.PROFESIONAL) { %>
						<h2>Por profesional</h2>
						<div class="myRow">					
							<div class="label width50">Inicio</div>
							<div class="label width150"><html:text name="AdvertisementForm" property="fromDate" styleClass="normalfield width120"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "HighlightedProfesional.fromDate.err")%></div>
							<div class="label width50">Fin</div>
							<div class="label width150"><html:text name="AdvertisementForm" property="toDate" styleClass="normalfield width120"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "HighlightedProfesional.toDate.err")%></div>
							<div class="label width20"><html:checkbox name="AdvertisementForm" property="paidup"/></div>
							<div class="label width60">Pago</div>
							<div class="label width50">Costo</div>
							<div class="label width80"><html:text name="AdvertisementForm" property="price" styleClass="normalfield width60"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "HighlightedProfesional.payment.err")%></div>
							<div class="label width300">Exposici&oacute;n <html:radio property="type" value="1"/> Destacado&nbsp;&nbsp;&nbsp;<html:radio property="type" value="2" />Superdestacado</div>
						</div>
						<div class="myRow height50" align="center">
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
						<h2>Por producto/servicio</h2>
						<% if (adForm.getSell() == null) { %>
							<div class="myRow">
								<%
								java.util.List source = adForm.getSells();
								com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
								request.setAttribute( "sells",  paginated);
								%>
								<display:table name="sells" sort="external" pagesize="10" id="sells" requestURI="./homeProfesional.jsp">
									<display:column title="Nombre" sortable="true" sortName="Producto" headerClass="sortable width800" property="name"></display:column>
									<display:column title="Acciones" headerClass="sortable width120">
										<a class="nonelyLink" href="javascript:document.AdvertisementForm.action='./selectSellForAd.do?id=<%= ((Sell)pageContext.getAttribute("sells")).getId()%>';document.AdvertisementForm.submit();">Seleccionar</a>
									</display:column>
								</display:table>
								<%=DisplayTagParamHelper.getFields(request)%>
							</div>
						<% } else { %>
							<h3>Producto o servicio del aviso: <strong><%=adForm.getSell().getName() %></strong></h3>
							<div class="myRow">
								<div class="label width50">Inicio</div>
								<div class="label width150"><html:text name="AdvertisementForm" property="fromDate" styleClass="normalfield width120"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "HighlightedProfesional.fromDate.err")%></div>
								<div class="label width50">Fin</div>
								<div class="label width150"><html:text name="AdvertisementForm" property="toDate" styleClass="normalfield width120"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "HighlightedProfesional.toDate.err")%></div>
								<div class="label width20"><html:checkbox name="AdvertisementForm" property="paidup"/></div>
								<div class="label width60">Pago</div>
								<div class="label width50">Costo</div>
								<div class="label width80"><html:text name="AdvertisementForm" property="price" styleClass="normalfield width60"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "HighlightedProfesional.payment.err")%></div>
								<div class="label width300">Exposici&oacute;n <html:radio property="type" value="1"/> Destacado&nbsp;&nbsp;&nbsp;<html:radio property="type" value="2" />Superdestacado</div>
							</div>
							<div class="myRow width950 height50" align="center">
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
				<h2>Listado de avisos destacados</h2>
				<h3>buscar avisos</h3>
				<div class="myRow" style="width:920px; border:dotted 1px #CCCCCC; padding:10px; margin-bottom:15px;">
					<div class="myLabel width50">Nombre</div>
					<div class="myLabel width150"><html:text name="AdvertisementForm" property="profesionalNameSearchAd" styleClass="normalfield width120"/></div>
					<div class="myLabel width70">Activo en</div>
					<div class="myLabel width150"><html:text name="AdvertisementForm" property="profesionalDateSearch" styleClass="normalfield width120"/></div>
					<div class="myLabel width50"><a class="nonelyLink" href="javascript:document.AdvertisementForm.action='./searchAds.do';document.AdvertisementForm.submit();">Buscar</a></div>
				</div>
				<div class="myRow height300" style="overflow:auto;">
					<table width="100%">
						<tr>
							<td class="headerTablas" width="300">Profesional</td>
							<td class="headerTablas" width="300">Producto/Servicio</td>
							<td class="headerTablas" width="300">Tipo</td>
							<td class="headerTablas" width="100">Desde</td>
							<td class="headerTablas" width="100">Hasta</td>
							<td class="headerTablas" width="100">Acciones</td>
						</tr>
						<logic:iterate name="AdvertisementForm" property="search" id="iterSection" indexId="iterIndex">
							<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
								<td <%=((AdvertisementValueObject) iterSection).getDeleted() == 1 ? "class=\"notActive\"" : "" %> align="left"><bean:write name="iterSection" property="profesionalBusinessName" /></td>
								<td <%=((AdvertisementValueObject) iterSection).getDeleted() == 1 ? "class=\"notActive\"" : "" %> align="left"><bean:write name="iterSection" property="sellName" /></td>
								<td <%=((AdvertisementValueObject) iterSection).getDeleted() == 1 ? "class=\"notActive\"" : "" %> align="left"><bean:write name="iterSection" property="translatedType" /></td>
								<td <%=((AdvertisementValueObject) iterSection).getDeleted() == 1 ? "class=\"notActive\"" : "" %> align="left"><bean:write name="iterSection" property="formatedfromdate" /></td>
								
								
								<td <%=((AdvertisementValueObject) iterSection).getDeleted() == 1 ? "class=\"notActive\"" : "" %> align="left"><bean:write name="iterSection" property="formatedtodate" /></td>
								<td>
									<html:link action="editAd.st?" paramName="iterSection" paramProperty="id" paramId="id">
										<img src="boImages/editar.png" alt="Editar">
									</html:link>
									<html:link action="/toggleDeletedAd" paramName="iterSection" paramProperty="id" paramId="id">
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