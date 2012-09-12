<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.struts.forms.beans.ServiceAreaBean"%>
<%@page import="com.tdil.tuafesta.struts.forms.EditProfesionalServiceAreaForm"%>
<%@page import="com.tdil.tuafesta.model.PhoneType"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalForm"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@page import="com.tdil.tuafesta.struts.forms.EditProfesionalPersonalDataForm"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalHomeForm"%>
<%@page import="com.tdil.tuafesta.struts.forms.ClientHomeForm"%>
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="editProfesionalServiceArea"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | R008-M1- Registro - Registro Clientes (paso 1)</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<script>
$(document).ready(
	function(){
		function serviceAreaSelected(serviceAreaLabel, serviceAreaValue, level) {
			$("input[name=serviceAreaSelectedText]").attr('value', serviceAreaLabel);
			$("input[name=serviceAreaAutocompleter]").attr('value','');
			$("input[name=serviceAreaAutocompleter]").css('display', 'none');
			$("#serviceAreaSelectedDiv").prop('innerHTML', serviceAreaLabel);
			$("#serviceAreaSelectedDiv").css('display', 'block');
			$("input[name=geoLevelId]").attr('value', serviceAreaValue);
			$("input[name=level]").attr('value', level);
			$("#addGeoLink").css('display', 'block');
		}
		$( "input[name=serviceAreaAutocompleter]" ).autocomplete({
			source: function( request, response ) {
				$.ajax({
					url: "searchGeoLevelAjax.do",
					data: {
						name: request.term
					},
					dataType: "json",
					success: function( data ) {
						response( $.map( data, function( item ) {
							return {
								label: item.name,
								value: item.id,
								level: item.level
							}
						}));
					}
				});
			},
			minLength: 2,
			select: function( event, ui ) {
				if (ui.item) {
					serviceAreaSelected(ui.item.label, ui.item.value, ui.item.level);
				}
				/*log( ui.item ?
					"Selected: " + ui.item.label :
					"Nothing selected, input was " + this.value);*/
			},
			open: function() {
				$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
			},
			close: function() {
				$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
			}
		});
			

		}
	
	);

function limpiarServiceArea() {
	$("input[name=productSelectedText]").attr('value', '');
	$("input[name=serviceAreaAutocompleter]").attr('value','');
	$("input[name=serviceAreaAutocompleter]").css('display', 'block');
	$("#serviceAreaSelectedDiv").prop('innerHTML', '');
	$("#serviceAreaSelectedDiv").css('display', 'none');
	$("input[name=geoLevel4Id]").attr('value', '');
	$("#addGeoLink").css('display', 'none');
}
</script>
<%@ include file="includes/boErrorJS.jsp" %>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Editar areas de cobertura</h1>
			<h2></h2>
		</div>
		<div id="formContent">
		<html:form method="POST" action="/saveProfesionalServiceArea">
			<div class="ui-widget">
				<html:hidden name="EditProfesionalServiceAreaForm" property="level"/>
				<html:hidden name="EditProfesionalServiceAreaForm" property="geoLevelId"/>
				<html:hidden name="EditProfesionalServiceAreaForm" property="serviceAreaSelectedText"/>
					Zona:
					<logic:equal name="EditProfesionalServiceAreaForm" property="serviceAreaSelected" value="false">
						<html:text name="EditProfesionalServiceAreaForm" property="serviceAreaAutocompleter" styleClass="normalField" style="display: block;"/>
						<div id="serviceAreaSelectedDiv" style="display: none;"></div>
					</logic:equal>
					<logic:equal name="EditProfesionalServiceAreaForm" property="serviceAreaSelected" value="true">
						<html:text name="EditProfesionalServiceAreaForm" property="serviceAreaAutocompleter" styleClass="normalField" style="display: none;"/>
						<div id="serviceAreaSelectedDiv" style="display: block;"><bean:write name="EditProfesionalServiceAreaForm" filter="false" property="serviceAreaSelectedText"/></div>
					</logic:equal>
					<logic:equal name="EditProfesionalServiceAreaForm" property="serviceAreaSelected" value="true">
						<a id="addGeoLink" href="javascript:document.EditProfesionalServiceAreaForm.action='./addServiceArea.do';document.EditProfesionalServiceAreaForm.submit();">Agregar</a> &nbsp;
					</logic:equal>
					<logic:equal name="EditProfesionalServiceAreaForm" property="serviceAreaSelected" value="false">
						<a id="addGeoLink" style="display: none;" href="javascript:document.EditProfesionalServiceAreaForm.action='./addServiceArea.do';document.EditProfesionalServiceAreaForm.submit();">Agregar</a> &nbsp;
					</logic:equal>
					 
					<a id="cleanGeoLink" href="javascript:limpiarServiceArea()">Limpiar</a>
				</div>
				<%
				EditProfesionalServiceAreaForm profesionalForm = (EditProfesionalServiceAreaForm)session.getAttribute("EditProfesionalServiceAreaForm");
			java.util.List sourceServiceAreas = profesionalForm.getServiceAreas();
			com.tdil.struts.pagination.PaginatedListImpl paginatedServiceAreas = new com.tdil.struts.pagination.PaginatedListImpl(sourceServiceAreas, request, 10);
			request.setAttribute( "serviceAreas",  paginatedServiceAreas);
			%>
				<display:table name="serviceAreas" sort="external" pagesize="10" id="serviceAreas" requestURI="./editProfesionalServiceArea.jsp">
					<display:column title="Zona" sortable="true" sortName="Zona" headerClass="sortable" property="serviceAreaText"></display:column>
					<display:column title="acciones">
						<a href="javascript:document.EditProfesionalServiceAreaForm.action='./removeServiceArea.do?index=<%= ((ServiceAreaBean)pageContext.getAttribute("serviceAreas")).getIndex()%>';document.EditProfesionalServiceAreaForm.submit();">Quitar</a>
					</display:column>
				</display:table>
			<div class="myRow width650" align="center"><input type="image" value=" " class="" src="images/skin_basic/buttons/registroClientes.png" /></div>
		</html:form>
		</div>
		<!-- aca Termina el formulario -->
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>