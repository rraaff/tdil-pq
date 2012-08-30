<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.struts.forms.beans.SellBean"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.ServiceBean"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.ServiceAreaBean"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.ProductBean"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="com.tdil.tuafesta.model.Geo4"%>
<%@page import="com.tdil.tuafesta.model.Geo3"%>
<%@page import="com.tdil.tuafesta.model.Geo2"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalForm"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="registroProfesional"%>
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
	
			$("form[name='ProfesionalForm']").validate({
					errorPlacement: function(error, element) {
						error.appendTo( element.parent("div"));
					},
					rules: { 'firstname': {required: true},
							'lastname': {required: true},
							'birthdate': {required: true},
							'email': {required: true, email: true},
							'phoneAreaCode': {required: true, digits: true},
							'phoneNumber': {required: true, digits: true},
							'phoneExtension': {required: false, digits: true},
							'phoneType': {required: true},
							'sex': {required: true},
							'password': {required: true},
							'retypepassword': {required: true},
							'businessname': {required: true},
							'cuit': {required: true},
							'iibb': {required: true},
							'geo2Id': {required: true},
							'geo3Id': {required: true},
							'geo4Id': {required: true}
					},
					messages: {
						'firstname': {required: "<img id='firstnameerror' src='images/unchecked.gif' hovertext='Ingrese el nombre.' />"}, 
						'lastname': {required: "<img id='lastnameerror' src='images/unchecked.gif' hovertext='Ingrese el apellido.' />"}, 
						'birthdate': {required: "<img id='birthdateerror' src='images/unchecked.gif' hovertext='Ingrese la fecha de nacimiento.' />"}, 
						'email': {required: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese el email.' />",
								email: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese un email valido.' />"},
						'phoneAreaCode': {required: "<img id='phoneAreaCodeerrorreq' src='images/unchecked.gif' hovertext='Ingrese el codigo de area.' />",
							digits: "<img id='phoneAreaCodeerrordig' src='images/unchecked.gif' hovertext='Ingrese solo numeros.' />"} ,
						'phoneNumber': {required: "<img id='phoneNumbererrorreq' src='images/unchecked.gif' hovertext='Ingrese el numero de telefono.' />",
							digits: "<img id='phoneNumbererrordig' src='images/unchecked.gif' hovertext='Ingrese solo numeros.' />"},
						'phoneExtension': {digits: "<img id='phoneExtensionerrordig' src='images/unchecked.gif' hovertext='Ingrese solo numeros.' />"},
						'phoneType': {required: "<img id='phoneTypeerror' src='images/unchecked.gif' hovertext='Seleccione el tipo de telefono.' />"},
						'sex': {required: "<img id='sexerror' src='images/unchecked.gif' hovertext='Seleccione el sexo.' />"},
						'password': {required: "<img id='passworderror' src='images/unchecked.gif' hovertext='Ingrese el password.' />"},
						'retypepassword': {required: "<img id='retypepassworderror' src='images/unchecked.gif' hovertext='Reingrese el password.' />"},
						'businessname': {required: "<img id='businessnameerror' src='images/unchecked.gif' hovertext='Ingrese el nombre comercial.' />"},
						'cuit': {required: "<img id='cuiterror' src='images/unchecked.gif' hovertext='Ingrese el cuit.' />"},
						'iibb': {required: "<img id='iibberror' src='images/unchecked.gif' hovertext='Ingrese el iibb.' />"},
						'geo2Id': {required: "<img id='geo2iderror' src='images/unchecked.gif' hovertext='Seleccione la provincia.' />"},
						'geo3Id': {required: "<img id='geo3iderror' src='images/unchecked.gif' hovertext='Seleccione el partido.' />"},
						'geo4Id': {required: "<img id='geo4iderror' src='images/unchecked.gif' hovertext='Seleccione la localidad.' />"}
						
					}
				});

			
			$("input[name=birthdate]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
				changeYear: true, yearRange: "1900:2012"});

			$( "#addproductlink" ).click(function() {
				$("#addproductlayer").css('display', 'block');
				$("#addservicelayer").css('display', 'none');
			});

			$( "#addservicelink" ).click(function() {
				$("#addproductlayer").css('display', 'none');
				$("#addservicelayer").css('display', 'block');
			});

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
			<h1>Registro de profesionales (Gratuito 100%)</h1>
			<h2>Complet&aacute; los datos del formulario y segu&iacute; los pasos de la registraci&oacute;n para poder publicar</h2>
		</div>
		<div id="formContent">
		<html:form method="POST" action="/addProfesional">
			<% ProfesionalForm profesionalForm = (ProfesionalForm)session.getAttribute("ProfesionalForm"); %>
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
					<div class="myLabel width110" id="Telefono"><html:select name="ProfesionalForm" property="phoneType" styleClass="normalField width150">
							<option value="">Seleccione</option><%-- 
							--%><% for (String type : profesionalForm.getPhoneTypes()) { %><%--
								--%><option <%=	type.equals(profesionalForm.getPhoneType()) ? "selected" : ""%> value="<%=type%>"><%--
										--%><%=type%></option>
							<% } %>
						</html:select>
					</div>
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
				<div class="myRow">
					<div class="myLabel width40">Retype</div>
					<div class="myLabel width150" id="Password"><html:password name="ProfesionalForm" property="retypepassword" styleClass="normalField width150"/></div>
					<div class="myLabel width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.retypepassword.err")%></div>
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
						<html:select name="ProfesionalForm" property="geo2Id" onchange="this.form.action='./refreshGeoLevel2Profesional.do';this.form.submit()" styleClass="normalField width150">
							<option value="0">Seleccione</option><%-- 
							--%><% for (Geo2 geo2 : profesionalForm.getLevel2()) { %><%--
								--%><option <%=	geo2.getId() == profesionalForm.getGeo2Id() ? "selected" : ""%> value="<%=String.valueOf(geo2.getId())%>"><%--
										--%><%=geo2.getNombre()%></option>
							<% } %>
						</html:select>
						<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.geo2_key + ".err")%>
					</div>
					<div class="myLabel width20"></div>
					<div class="myLabel width210">
						<html:select name="ProfesionalForm" property="geo3Id" onchange="this.form.action='./refreshGeoLevel3Profesional.do';this.form.submit()" styleClass="normalField width200">
							<option value="0">Seleccione</option>
							<% for (Geo3 geo3 : profesionalForm.getLevel3()) { %>	
								<option	<%=	geo3.getId() == profesionalForm.getGeo3Id() ? "selected" : ""%>
									value="<%=String.valueOf(geo3.getId())%>">
									<%=geo3.getNombre()%></option>
							<% } %>
						</html:select>
						<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.geo3_key + ".err")%>
					</div>
					<div class="myLabel width20"></div>
					<div class="myLabel width210">
						<html:select name="ProfesionalForm" property="geo4Id" styleClass="normalField width200">
							<option value="0">Seleccione</option>
							<% for (Geo4 geo4 : profesionalForm.getLevel4()) { %>	
								<option	<%=	geo4.getId() == profesionalForm.getGeo4Id() ? "selected" : ""%>
									value="<%=String.valueOf(geo4.getId())%>">
									<%=geo4.getNombre()%></option>
							<% } %>
						</html:select>
						<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.geo4_key + ".err")%>
					</div>
				</div>
			</div>
			
			<div id="formSection" class="width650">
				<h2 style="float:left; padding-left:0; padding-bottom:0; margin-bottom:10px;">Agregue productos y servicios que desee ofrecer</h2>
				<div class="myRow">
					<div class="myLabel width600 comment">Agregue todos productos que desee. Si el producto ya ha sido tipificado, aparecer&aacute; dentro de las opciones de texto predictivo. Es mejor para su negocio y sus posibilidades, que todos los productos que agregue est&eacute;n tipificados. Los administradores del sitio se encargan de tipificar los productos para su comodidad.</div>
					<a class="nonelyLink" id="addproductlink">Agregar producto</a><a class="nonelyLink" id="addservicelink">Agregar servicio</a>
				</div>
				<div class="myRow" id="addproductlayer" style="display: none;">
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
						<div class="myLabel width50"><a class="nonelyLink" href="javascript:document.ProfesionalForm.action='./addProduct.do';document.ProfesionalForm.submit();">Agregar</a><a class="nonelyLink" href="javascript:limpiarProducto()">Cancelar</a></div>
					</div>
				</div>
				
				<div class="myRow" id="addservicelayer" style="display: none;">
					<div class="myLabel width600 comment" style="margin-top:15px;">Agregue todos servicios que desee. Si el producto ya ha sido tipificado, aparecer&aacute; dentro de las opciones de texto predictivo. Es mejor para su negocio y sus posibilidades, que todos los servicios que agregue est&eacute;n tipificados. Los administradores del sitio se encargan de tipificar los servicios para su comodidad.</div>
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
							<div class="myLabel width50"><a class="nonelyLink" href="javascript:document.ProfesionalForm.action='./addService.do';document.ProfesionalForm.submit();">Agregar</a><a class="nonelyLink" href="javascript:limpiarServicio()">Cancelar</a></div>
						</div>
					</div>
				</div>
				
				<div class="myRow">
					<%
					java.util.List source = profesionalForm.getSells();
					com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
					request.setAttribute( "sells",  paginated);
					%>
					<display:table name="sells" sort="external" pagesize="10" id="sells" requestURI="./registroProfesional.jsp">
						<display:column title="Nombre" sortable="true" sortName="Producto" headerClass="sortable width250" property="name"></display:column>
						<display:column title="Tipo" sortable="true" sortName="Tipo" headerClass="sortable width250" property="sellTypeDescription"></display:column>
						<display:column title="Categoria" sortable="true" sortName="Categoria" headerClass="sortable width250" property="categoryText"></display:column>
						<display:column title="Precio Unitario" sortable="true" sortName="precio" headerClass="sortable width100" property="referencePrice"></display:column>
						<display:column title="acciones" headerClass="sortable width50"><a class="nonelyLink" href="javascript:document.ProfesionalForm.action='./removeSell.do?index=<%= ((SellBean)pageContext.getAttribute("sells")).getIndex()%>';document.ProfesionalForm.submit();">Quitar</a>
						</display:column>
					</display:table>
					<%=DisplayTagParamHelper.getFields(request)%>
				</div>
				<div class="myLabel width20">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.sells_key + ".err")%></div>
				<div class="myRow">
					<div class="myLabel" align="center"><input type="submit" value="ENVIAR DATOS DE REGISTRO<br/>Le enviaremos un E-Mail para validar la casilla de correo" class="buttonSubmit" /></div>
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