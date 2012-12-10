<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Te ayudamos a organizar tu fiesta | El sitio #1 en contactos entre profesionales y clientes del rubro eventos</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<script>
$(document).ready(
	function(){

		
		$("input[name=maxPrice]").jStepper({minValue:0});
		
			$("form[name='OrganizeWizardForm']").validate({
					errorPlacement: function(error, element) {
						error.appendTo( element.parent("div"));
					},
					rules: { 'product': {required: true},
							'maxPrice': {digits: true}
					},
					messages: {
						'product': {required: "<img id='firstnameerror' src='images/unchecked.gif' hovertext='Ingrese el producto o servicio.' />"}, 
						'maxPrice': {digits: "<img id='phoneAreaCodeerrordig' src='images/unchecked.gif' hovertext='Ingrese solo numeros.' />"}
					}
			});

			function serviceAreaSelected(serviceAreaLabel, serviceAreaValue, level) {
				$("input[name=geoLevelSelectedText]").attr('value', serviceAreaLabel);
				$("input[name=geoLevel]").attr('value','');
				$("#geoLevelAutocompleterContainer").css('display', 'none');
				$("#geoLevelSelectedDiv").prop('innerHTML', serviceAreaLabel);
				$("#geoLevelSelectedDiv").css('display', 'block');
				$("input[name=geoLevelId]").attr('value', serviceAreaValue);
				$("input[name=level]").attr('value', level);
			}
			$( "input[name=geoLevel]" ).autocomplete({
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
				},
				open: function() {	$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );},
				close: function() {$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );}
			});

			function productSelected(serviceAreaLabel, serviceAreaValue) {
				$("input[name=product]").attr('value', serviceAreaLabel);
				$("input[name=productPath]").attr('value', serviceAreaLabel);
				$("#productAutocompleterContainer").css('display', 'none');
				$("#productSelectedText").prop('innerHTML', serviceAreaLabel);
				$("#productSelectedDiv").css('display', 'block');
				$("input[name=productId]").attr('value', serviceAreaValue);
			}
			$( "input[name=product]" ).autocomplete({
				source: function( request, response ) {
					$.ajax({
						url: "searchProductTreeAjax.do",
						data: {
							name: request.term
						},
						dataType: "json",
						success: function( data ) {
							response( $.map( data, function( item ) {
								return {
									label: item.name,
									value: item.id
								}
							}));
						}
					});
				},
				minLength: 2,
				select: function( event, ui ) {
					if (ui.item) {
						productSelected(ui.item.label, ui.item.value);
					} 
				},
				open: function() {	$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );},
				close: function() {$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );}
			});
	}
);

function limpiarServiceArea() {
	$("input[name=geoLevelId]").attr('value', '');
	$("input[name=geoLevelId]").attr('value', '');
	$("input[name=geoLevelSelectedText]").attr('value', '');
	$("input[name=geoLevel]").attr('value','');
	$("#geoLevelAutocompleterContainer").css('display', 'block');
	$("#geoLevelSelectedDiv").prop('innerHTML', '');
	$("#geoLevelSelectedDiv").css('display', 'none');
}

function limpiarProduct() {
	$("input[name=product]").attr('value', '');
	$("input[name=productId]").attr('value','');
	$("input[name=productPath]").attr('value','');
	$("#productAutocompleterContainer").css('display', 'block');
	$("#productSelectedText").prop('innerHTML', '');
	$("#productSelectedDiv").css('display', 'none');
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
			<h1>Te ayudamos a organizar tu fiesta</h1>
			<h2>Eleg&iacute; los productos/servicios que buscas, podes poner un precio m&aacute;ximo que pagar&iacute;as (si buscas un producto el precio es por unidad). Despu&eacute;s eleg&iacute;s la zona donde vas a hacer tu fiesta y hace click en buscar.</h2>
		</div>
		<div id="formContent">
			<div id="formSection" style="width:920px;">
				<h2>Paso 1: Agreg&aacute; al listado todos los productos y/o servicios que necesitas para tu evento</h2>
				<html:form method="POST" action="/addToSearch">
					<html:hidden name="OrganizeWizardForm" property="productId" />
					<html:hidden name="OrganizeWizardForm" property="productPath" />
					<div class="myRow" id="productAutocompleterContainer">
						<div class="myLabel width120">Producto/Servicio</div>
						<div class="myLabel width400"><html:text name="OrganizeWizardForm" property="product" styleClass="normalField width350"/></div>
					</div>
					<div id="productSelectedDiv" style="display: none;">
						<div id="productSelectedText" ></div>
						<div class="myLabel width50">Precio</div>
						<div class="myLabel width300"><html:text name="OrganizeWizardForm" property="maxPrice" styleClass="normalField width100"/></div>
						<html:submit property="operation">Agregar</html:submit>
					</div>
				</html:form>
				<!-- empieza la tabla de la busqueda -->
				<html:form method="POST" action="/searchWizard">
					<html:hidden name="OrganizeWizardForm" property="geoLevelId" />
					<html:hidden name="OrganizeWizardForm" property="level" />
					<div class="myRow"> <!--  style="height:200px; overflow:auto;"-->
						<table width="100%">
							<tr>
								<td class="headerTablas">Producto o Servicio</td>
								<td class="headerTablas" width="90">Precio Maximo</td>
								<td class="headerTablas" width="60">Acciones</td>
							</tr>
							<logic:iterate name="OrganizeWizardForm" property="searchSellBeans"
								id="iterSearch" indexId="iterIndex">
								<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
									<td><bean:write name="iterSearch" property="product" /></td>
									<td><bean:write name="iterSearch" property="maxPrice" /></td>
									<td><a href="./removeFromSearch.do?index=<%=iterIndex%>">Quitar</a></td>
								</tr>
							</logic:iterate>
						</table>
					</div>
					<h2>Paso 2: Determin&aacute; la ubicaci&oacute;n de tu evento para buscar aquellos profesionales que trabajen cerca tuyo</h2>
					<div class="myRow">
						<div class="myLabel width80">Ubicaci&oacute;n</div>
						
						
						<logic:equal name="OrganizeWizardForm" property="geoLevelSelected" value="false">
							<div class="myLabel width520" id="geoLevelAutocompleterContainer"><html:text name="OrganizeWizardForm" property="geoLevel" styleClass="normalField width500"/></div>
							<div id="geoLevelSelectedDiv" style="display: none;"></div>
							<div class="myLabel width50"><a id="cleanGeoLink" href="javascript:limpiarServiceArea()">Limpiar</a></div>
						</logic:equal>
						<logic:equal name="OrganizeWizardForm" property="geoLevelSelected" value="true">
							<div class="myLabel width520" id="geoLevelAutocompleterContainer"><html:text name="OrganizeWizardForm" property="geoLevel" styleClass="normalField width500"/></div>
							<div id="geoLevelSelectedDiv" style="display: block;"><bean:write name="OrganizeWizardForm" filter="false" property="geoLevelSelectedText"/></div>
							<div class="myLabel width50"><a id="cleanGeoLink" href="javascript:limpiarServiceArea()">Limpiar</a></div>
						</logic:equal>
						
					</div>
					<div class="myRow" align="center"><html:submit property="operation">Buscar</html:submit></div>
				</html:form>
			</div>
		</div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>