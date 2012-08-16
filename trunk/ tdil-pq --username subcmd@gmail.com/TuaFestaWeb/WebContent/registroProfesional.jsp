<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.struts.forms.beans.ServiceBean"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.ServiceAreaBean"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.ProductBean"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="com.tdil.tuafesta.model.Geo4"%>
<%@page import="com.tdil.tuafesta.model.Geo3"%>
<%@page import="com.tdil.tuafesta.model.Geo2"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalForm"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | R008-M1- Registro - Registro Profesional Normal (paso 1)</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<script>
$(document).ready(
	function(){
	
		function generateTooltips() {
			  //make sure tool tip is enabled for any new error label
				$("img[id*='error']").tooltip({
					showURL: false,
					opacity: 0.99,
					fade: 150,
					positionRight: true ,
					bodyHandler: function() {
						return $("#"+this.id).attr("hovertext");
					}
				});
				//make sure tool tip is enabled for any new valid label
				$("img[src*='tick.gif']").tooltip({
					showURL: false,
						bodyHandler: function() {
							return "OK";
						}
				});
			}
			
			$('form').mouseover(function(){
				      generateTooltips();
				    });
		
			$("form[name='ProfesionalForm']").validate({
					errorPlacement: function(error, element) {
						error.appendTo( element.parent("div"));
					},
					rules: { 'firstname': {required: true},
							'lastname': {required: true},
							'email': {required: true, email: true}
					},
					messages: {
						'firstname': {required: "<img id='firstnameerror' src='images/unchecked.gif' hovertext='Ingrese el nombre.' />"}, 
						'lastname': {required: "<img id='lastnameerror' src='images/unchecked.gif' hovertext='Ingrese el apellido.' />"}, 
						'email': {required: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese el email.' />",
								email: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese un email valido.' />"}
					}
				});

			
			$("input[name=birthdate]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
				changeYear: true, yearRange: "1900:2012"});

			function productSelected(prodLabel, prodValue, prodCat) {
				$("input[name=productSelectedText]").attr('value', prodLabel);
				$("input[name=productAutocompleter]").attr('value','');
				$("input[name=productAutocompleter]").css('display', 'none');
				$("input[name=productCategorySelected]").attr('value', prodCat);
				$("#productSelectedDiv").prop('innerHTML', prodLabel + ' (' + prodCat + ')');
				$("#productSelectedDiv").css('display', 'block');
				$("input[name=productId]").attr('value', prodValue);
			}

			function serviceSelected(prodLabel, prodValue, prodCat) {
				$("input[name=serviceSelectedText]").attr('value', prodLabel);
				$("input[name=serviceAutocompleter]").attr('value','');
				$("input[name=serviceAutocompleter]").css('display', 'none');
				$("input[name=serviceCategorySelected]").attr('value', prodCat);
				$("#serviceSelectedDiv").prop('innerHTML', prodLabel + ' (' + prodCat + ')');
				$("#serviceSelectedDiv").css('display', 'block');
				$("input[name=serviceId]").attr('value', prodValue);
			}
			
			$( "input[name=productAutocompleter]" ).autocomplete({
				source: function( request, response ) {
					$.ajax({
						url: "searchProduct.do",
						data: {
							name: request.term
						},
						dataType: "json",
						success: function( data ) {
							response( $.map( data, function( item ) {
								return {
									label: item.name,
									value: item.id,
									path: item.path
								}
							}));
						}
					});
				},
				minLength: 2,
				select: function( event, ui ) {
					if (ui.item) {
						productSelected(ui.item.label, ui.item.value, ui.item.path);
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


			$( "input[name=serviceAutocompleter]" ).autocomplete({
				source: function( request, response ) {
					$.ajax({
						url: "searchService.do",
						data: {
							name: request.term
						},
						dataType: "json",
						success: function( data ) {
							response( $.map( data, function( item ) {
								return {
									label: item.name,
									value: item.id,
									path: item.path
								}
							}));
						}
					});
				},
				minLength: 2,
				select: function( event, ui ) {
					if (ui.item) {
						serviceSelected(ui.item.label, ui.item.value, ui.item.path);
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


			function serviceAreaSelected(serviceAreaLabel, serviceAreaValue) {
				$("input[name=serviceAreaSelectedText]").attr('value', serviceAreaLabel);
				$("input[name=serviceAreaAutocompleter]").attr('value','');
				$("input[name=serviceAreaAutocompleter]").css('display', 'none');
				$("#serviceAreaSelectedDiv").prop('innerHTML', serviceAreaLabel);
				$("#serviceAreaSelectedDiv").css('display', 'block');
				$("input[name=geoLevel4Id]").attr('value', serviceAreaValue);
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
									value: item.id
								}
							}));
						}
					});
				},
				minLength: 2,
				select: function( event, ui ) {
					if (ui.item) {
						serviceAreaSelected(ui.item.label, ui.item.value);
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

function limpiarProducto() {
	$("input[name=productAutocompleter]").attr('value','');
	$("input[name=productAutocompleter]").css('display', 'block');
	$("input[name=productSelectedText]").attr('value', '');
	$("#productSelectedDiv").prop('innerHTML', '');
	$("#productSelectedDiv").css('display', 'none');
	$("input[name=productId]").attr('value', '');
	$("input[name=referenceprice]").attr('value', '');
}

function limpiarServicio() {
	$("input[name=serviceAutocompleter]").attr('value','');
	$("input[name=serviceAutocompleter]").css('display', 'block');
	$("input[name=serviceSelectedText]").attr('value', '');
	$("#serviceSelectedDiv").prop('innerHTML', '');
	$("#serviceSelectedDiv").css('display', 'none');
	$("input[name=serviceId]").attr('value', '');
	$("input[name=serviceReferenceprice]").attr('value', '');
}

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
<%@ include file="includes/designHeader.jsp" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Registro de profesionales (Gratuito 100%)</h1>
			<h2>Complet&aacute; los datos del formulario y segu&iacute; los pasos de la registraci&oacute;n para poder publicar</h2>
		</div>
		<div id="formContent">
		<html:form method="POST" action="/addProfesional">
			<div id="formSection">
				<div class="myRow">
					<div class="myLabel width50">Nombre</div>
					<div class="myLabel width160" id="Nombre"><html:text name="ProfesionalForm" property="firstname" styleClass="normalField width150"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.firstname.err")%></div>
					<div class="myLabel width50">&nbsp;</div>
					<div class="myLabel width50">Apellido</div>
					<div class="myLabel width160" id="Apellido"><html:text name="ProfesionalForm" property="lastname" styleClass="normalField width150"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.lastname.err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width50">Cod. &aacute;rea</div>
					<div class="myLabel width60" id="Telefono"><html:text name="ProfesionalForm" property="phoneAreaCode" styleClass="normalField width50"/></div>
					<div class="myLabel width20">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.phoneareacode_key + ".err")%></div>
					<div class="myLabel width50">N&uacute;mero</div>
					<div class="myLabel width160" id="Telefono"><html:text name="ProfesionalForm" property="phoneNumber" styleClass="normalField width150"/></div>
					<div class="myLabel width20">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.phonenumber_key + ".err")%></div>
					<div class="myLabel width20">Int.</div>
					<div class="myLabel width50" id="Telefono"><html:text name="ProfesionalForm" property="phoneExtension" styleClass="normalField width40"/></div>
					<div class="myLabel width20">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.phoneextension_key + ".err")%></div>
					<div class="myLabel width20">Tipo</div>
					<div class="myLabel width110" id="Telefono"><html:text name="ProfesionalForm" property="phoneType" styleClass="normalField width100"/></div>
					<div class="myLabel width20">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.phonetype_key + ".err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width50">Sexo</div>
					<div class="myLabel width200" id="Sexo"><html:radio property="sex" value="m" /> Masculino&nbsp;&nbsp;&nbsp;<html:radio property="sex" value="f" /> Femenino</div>
					<div class="myLabel width110">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.sex.err")%></div>
					<div class="myLabel width60">Fecha Nac.</div>
					<div class="myLabel width150" id="Fecha Nac."><html:text name="ProfesionalForm" property="birthdate" styleClass="normalField width150"/></div>
					<div class="myLabel width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.birthdate.err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width50">E-Mail</div>
					<div class="myLabel width280" id="Email"><html:text name="ProfesionalForm" property="email" styleClass="normalField width250"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.email.err")%></div>
					<div class="myLabel width40">Clave</div>
					<div class="myLabel width150" id="Password"><html:password name="ProfesionalForm" property="password" styleClass="normalField width150"/></div>
					<div class="myLabel width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.password.err")%></div>
					<div class="myLabel width250">TODO If por facebook // Borrar este div...</div>
				</div>
			</div>
			<div id="formSection" class="width650">
				<div class="myRow">
					<div class="myLabel width200">Nombre profesional o de la empresa</div>
					<div class="myLabel width380" id=""><html:text name="ProfesionalForm" property="businessname" styleClass="normalField width350"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.businessname_key + ".err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width50">CUIT</div>
					<div class="myLabel width100" id=""><html:text name="ProfesionalForm" property="cuit" styleClass="normalField width100"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.cuit_key + ".err")%></div>
					<div class="myLabel width30">IIBB</div>
					<div class="myLabel width100" id=""><html:text name="ProfesionalForm" property="iibb" styleClass="normalField"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.iibb_key + ".err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width50">Ubicaci&oacute;n</div>
					<div class="myLabel width160">
						<% ProfesionalForm profesionalForm = (ProfesionalForm)session.getAttribute("ProfesionalForm"); %>
						<html:select name="ProfesionalForm" property="geo2Id" onchange="this.form.action='./refreshGeoLevel2Profesional.do';this.form.submit()" styleClass="normalField width150">
							<option value="0">Seleccione<option>
							<% for (Geo2 geo2 : profesionalForm.getLevel2()) { %>	
								<option	<%=	geo2.getId() == profesionalForm.getGeo2Id() ? "selected" : ""%>
									value="<%=String.valueOf(geo2.getId())%>">
									<%=geo2.getNombre()%></option>
							<% } %>
						</html:select>
					</div>
					<div class="myLabel width20"></div>
					<div class="myLabel width210">
						<html:select name="ProfesionalForm" property="geo3Id" onchange="this.form.action='./refreshGeoLevel3Profesional.do';this.form.submit()" styleClass="normalField width200">
							<option value="0">Seleccione<option>
							<% for (Geo3 geo3 : profesionalForm.getLevel3()) { %>	
								<option	<%=	geo3.getId() == profesionalForm.getGeo3Id() ? "selected" : ""%>
									value="<%=String.valueOf(geo3.getId())%>">
									<%=geo3.getNombre()%></option>
							<% } %>
						</html:select>
					</div>
					<div class="myLabel width20"></div>
					<div class="myLabel width210">
						<html:select name="ProfesionalForm" property="geo4Id" styleClass="normalField width200">
							<option value="0">Seleccione<option>
							<% for (Geo4 geo4 : profesionalForm.getLevel4()) { %>	
								<option	<%=	geo4.getId() == profesionalForm.getGeo4Id() ? "selected" : ""%>
									value="<%=String.valueOf(geo4.getId())%>">
									<%=geo4.getNombre()%></option>
							<% } %>
						</html:select>
					</div>
				</div>
				<div class="myRow">
					<div class="myLabel width50">Web</div>
					<div class="myLabel width310" id="Web"><html:text name="ProfesionalForm" property="website" styleClass="normalField width300"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.website.err")%></div>
					<div class="myLabel width50">Facebook</div>
					<div class="myLabel width100" id="Facebook"><html:text name="ProfesionalForm" property="facebook" styleClass="normalField width100"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.facebook.err")%></div>
				</div>
				<div class="myRow height50">
					<div class="myLabel width100">Horario de Atenci&oacute;n<br/><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.businesshours.err")%></div>
					<div class="myLabel width210 height50" id="Horario de Atencion"><html:textarea name="ProfesionalForm" property="businesshours" styleClass="normalField width200 height50"/></div>
					<div class="myLabel width20">&nbsp;</div>
					<div class="myLabel width80">Descripci&oacute;n<br/><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.description.err")%></div>
					<div class="myLabel width210 height50" id="Descripcion"><html:textarea name="ProfesionalForm" property="description" styleClass="normalField width200 height50"/></div>
				</div>
			</div>
			
			<div id="formSection" class="width650">
				<h2 style="float:left; padding-left:0; padding-bottom:0; margin-bottom:10px;">Agregue productos y servicios que desee ofrecer</h2>
				<div class="myRow">
					<div class="myLabel width600 comment">Agregue todos productos  que desee. Si el producto ya ha sido tipificado, aparecer&aacute; dentro de las opciones de texto predictivo. Es mejor para su negocio y sus posibilidades, que todos los productos que agregue est&eacute;n tipificados. Los administradores del sitio se encargan de tipificar los productos para su comodidad.</div>
				</div>
				<div class="myRow">
					<div class="myLabel width50">Producto</div>
					<div class="ui-widget">
						<div class="myLabel width320">
							<html:hidden name="ProfesionalForm" property="productId"/>
							<html:hidden name="ProfesionalForm" property="productSelectedText"/>
							<html:hidden name="ProfesionalForm" property="productCategorySelected"/>
						
							<logic:equal name="ProfesionalForm" property="productSelected" value="false">
								<html:text name="ProfesionalForm" property="productAutocompleter" styleClass="normalField width300" style="display: block;"/>
								<div id="productSelectedDiv" style="display: none;"></div>
							</logic:equal>
							<logic:equal name="ProfesionalForm" property="productSelected" value="true">
								<html:text name="ProfesionalForm" property="productAutocompleter" styleClass="normalField width300" style="display: none;"/>
								<div id="productSelectedDiv" style="display: block;"><bean:write name="ProfesionalForm" filter="false" property="productSelectedText"/> (<bean:write name="ProfesionalForm" filter="false" property="productCategorySelected"/>)</div>
							</logic:equal>
						</div>
						<div class="myLabel width80">Precio unitario</div>
						<div class="myLabel width60"><html:text name="ProfesionalForm" property="referenceprice" styleClass="normalField width50"/></div>
						<div class="myLabel width50"><a class="nonelyLink" href="javascript:document.ProfesionalForm.action='./addProduct.do';document.ProfesionalForm.submit();">Agregar</a><a class="nonelyLink" href="javascript:limpiarProducto()">Limpiar</a></div>
					</div>
				</div>
				<div class="myRow">
					<%
					java.util.List source = profesionalForm.getProducts();
					com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
					request.setAttribute( "products",  paginated);
					%>
					<display:table name="products" sort="external" pagesize="10" id="products" requestURI="./registroProfesional.jsp">
						<display:column title="Producto" sortable="true" sortName="Producto" headerClass="sortable width250" property="profesionalProductText"></display:column>
						<display:column title="Categoria" sortable="true" sortName="Categoria" headerClass="sortable width250" property="productCategoryText"></display:column>
						<display:column title="Precio Unitario" sortable="true" sortName="precio" headerClass="sortable width100" property="referencePrice"></display:column>
						<display:column title="acciones" headerClass="sortable width50"><a class="nonelyLink" href="javascript:document.ProfesionalForm.action='./removeProduct.do?index=<%= ((ProductBean)pageContext.getAttribute("products")).getIndex()%>';document.ProfesionalForm.submit();">Quitar</a>
						</display:column>
					</display:table>
					
				<div class="myRow" style="margin-top:15px; border-top:dotted 1px #FFFFFF;">
					<div class="myLabel width600 comment" style="margin-top:15px;">Agregue todos servicios que desee. Si el producto ya ha sido tipificado, aparecer&aacute; dentro de las opciones de texto predictivo. Es mejor para su negocio y sus posibilidades, que todos los servicios que agregue est&eacute;n tipificados. Los administradores del sitio se encargan de tipificar los servicios para su comodidad.</div>
				</div>
				<div class="myRow">
					<div class="myLabel width50">Servicio</div>
					<div class="ui-widget">
						<div class="myLabel width320">
							<html:hidden name="ProfesionalForm" property="serviceId"/>
							<html:hidden name="ProfesionalForm" property="serviceSelectedText"/>
							<html:hidden name="ProfesionalForm" property="serviceCategorySelected"/>
							<logic:equal name="ProfesionalForm" property="serviceSelected" value="false">
								<html:text name="ProfesionalForm" property="serviceAutocompleter" styleClass="normalField width300" style="display: block;"/>
								<div id="serviceSelectedDiv" style="display: none;"></div>
							</logic:equal>
							<logic:equal name="ProfesionalForm" property="serviceSelected" value="true">
								<html:text name="ProfesionalForm" property="serviceAutocompleter" styleClass="normalField width300" style="display: none;"/>
								<div id="serviceSelectedDiv" style="display: block;"><bean:write name="ProfesionalForm" filter="false" property="serviceSelectedText"/> (<bean:write name="ProfesionalForm" filter="false" property="serviceCategorySelected"/>)</div>
							</logic:equal>
						</div>
						<div class="myLabel width50">Precio</div>
						<div class="myLabel width60"><html:text name="ProfesionalForm" property="serviceReferenceprice" styleClass="normalField width50"/></div>
						<div class="myLabel width50"><a class="nonelyLink" href="javascript:document.ProfesionalForm.action='./addService.do';document.ProfesionalForm.submit();">Agregar</a><a class="nonelyLink" href="javascript:limpiarServicio()">Limpiar</a></div>
					</div>
				</div>
				<div class="myRow">
					<%
					java.util.List servicesSource = profesionalForm.getServices();
					com.tdil.struts.pagination.PaginatedListImpl paginatedServices = new com.tdil.struts.pagination.PaginatedListImpl(servicesSource, request, 10);
					request.setAttribute( "services",  paginatedServices);
					%>
					<display:table name="services" sort="external" pagesize="10" id="services" requestURI="./registroProfesional.jsp">
						<display:column title="Servicio" sortable="true" sortName="Servicio" headerClass="sortable width150" property="profesionalServiceText"></display:column>
						<display:column title="Categoria" sortable="true" sortName="Categoria" headerClass="sortable width150" property="serviceCategoryText"></display:column>
						<display:column title="Precio Ref." sortable="true" sortName="precio" headerClass="sortable width150" property="referencePrice"></display:column>
						<display:column title="acciones" headerClass="sortable width150"><a class="nonelyLink" href="javascript:document.ProfesionalForm.action='./removeService.do?index=<%= ((ServiceBean)pageContext.getAttribute("services")).getIndex()%>';document.ProfesionalForm.submit();">Quitar</a></display:column>
					</display:table>
					<%=DisplayTagParamHelper.getFields(request)%>
				</div>					
					
				<div class="myRow" style="margin-top:15px; border-top:dotted 1px #FFFFFF;">
					<div class="myLabel width600 comment"  style="margin-top:15px;">Agregue todas las &aacute;reas de cobertura para este producto/servicio</div>
				</div>
				<div class="myRow">
					<div class="myLabel width100">Localidad/barrio</div>
					<div class="ui-widget">
						<div class="myLabel width400">
						<html:hidden name="ProfesionalForm" property="geoLevel4Id"/>
						<html:hidden name="ProfesionalForm" property="serviceAreaSelectedText"/>
							<logic:equal name="ProfesionalForm" property="serviceAreaSelected" value="false">
								<html:text name="ProfesionalForm" property="serviceAreaAutocompleter" styleClass="normalField width350" style="display: block;"/>
								<div id="serviceAreaSelectedDiv" style="display: none;"></div>
							</logic:equal>
							<logic:equal name="ProfesionalForm" property="serviceAreaSelected" value="true">
								<html:text name="ProfesionalForm" property="serviceAreaAutocompleter" styleClass="normalField width350" style="display: none;"/>
								<div id="serviceAreaSelectedDiv" style="display: block;"><bean:write name="ProfesionalForm" filter="false" property="serviceAreaSelectedText"/></div>
							</logic:equal>
						</div>
						<div class="myLabel width100">
							<logic:equal name="ProfesionalForm" property="serviceAreaSelected" value="true">
								<a class="nonelyLink" id="addGeoLink" href="javascript:document.ProfesionalForm.action='./addServiceArea.do';document.ProfesionalForm.submit();">Agregar</a>
							</logic:equal>
							<logic:equal name="ProfesionalForm" property="serviceAreaSelected" value="false">
								<a class="nonelyLink" id="addGeoLink" style="display: none;" href="javascript:document.ProfesionalForm.action='./addServiceArea.do';document.ProfesionalForm.submit();">Agregar</a>
							</logic:equal>
							<a class="nonelyLink" id="cleanGeoLink" href="javascript:limpiarServiceArea()">Limpiar</a>
						</div>
					</div>
				</div>
				<div class="myRow">
					<div class="myLabel width600">
						<%
						java.util.List sourceServiceAreas = profesionalForm.getServiceAreas();
						com.tdil.struts.pagination.PaginatedListImpl paginatedServiceAreas = new com.tdil.struts.pagination.PaginatedListImpl(sourceServiceAreas, request, 10);
						request.setAttribute( "serviceAreas",  paginatedServiceAreas);
						%>
						<display:table name="serviceAreas" sort="external" pagesize="10" id="serviceAreas" requestURI="./registroProfesional.jsp">
							<display:column title="&Aacute;reas de cobertura" sortable="true" sortName="Zona" headerClass="sortable width500" property="serviceAreaText"></display:column>
							<display:column title="Acciones disponibles" headerClass="sortable width150"><a class="nonelyLink" href="javascript:document.ProfesionalForm.action='./removeServiceArea.do?index=<%= ((ServiceAreaBean)pageContext.getAttribute("serviceAreas")).getIndex()%>';document.ProfesionalForm.submit();">Quitar</a></display:column>
						</display:table>
					</div>
				</div>
				<div class="myRow">
					<div class="myLabel" align="center"><input type="submit" value="ENVIAR DATOS DE REGISTRO<br/>Le enviaremos un E-Mail para validar la casilla de correo" class="buttonSubmit" /></div>
				</div>
			</div>
		</html:form>
		</div>
		<!-- aca Termina el formulario -->
		</div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>