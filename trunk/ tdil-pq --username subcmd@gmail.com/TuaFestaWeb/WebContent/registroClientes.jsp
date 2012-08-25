<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="registroClientes"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>

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
			<h1>Registro de Clientes (Gratuito 100%)</h1>
			<h2>Complet&aacute; los datos del formulario y segu&iacute; los pasos de la registraci&oacute;n.</h2>
		</div>
		<div id="formContent">
			<div id="formSection">
				<div class="myRow">
					<div class="myLabel width50">Nombre</div>
					<div class="myLabel width200" id="Nombre"><input type="text" class="normalField width150"/></div>
					<div class="myLabel width50">&nbsp;<!-- % =TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.firstname.err")% --></div>
					<div class="myLabel width60">&nbsp;</div>
					<div class="myLabel width60">Apellido</div>
					<div class="myLabel width160" id="Apellido"><input type="text" class="normalField width150"/></div>
					<div class="myLabel width40">&nbsp;<!-- % =TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.lastname.err")% --></div>
				</div>
				<div class="myRow">
					<div class="myLabel width50">Sexo</div>
					<div class="myLabel width200" id="Sexo"><!--html:radio property="sex" value="m" /--> Masculino&nbsp;&nbsp;&nbsp;<!--html:radio property="sex" value="f" / --> Femenino</div>
					<div class="myLabel width110">&nbsp;<!-- %=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.sex.err")% --></div>
					<div class="myLabel width60">Fecha Nac.</div>
					<div class="myLabel width150" id="Fecha Nac."><input type="text" class="normalField width150"/></div>
					<div class="myLabel width50"><!-- %=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.birthdate.err")% --></div>
				</div>
				<div class="myRow">
					<div class="myLabel width50">E-Mail</div>
					<div class="myLabel width280" id="Email"><input type="text" class="normalField width250"/></div>
					<div class="myLabel width50">&nbsp;<!-- %=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.email.err")% --></div>
					<div class="myLabel width40">Clave</div>
					<div class="myLabel width150" id="Password"><input type="text" class="normalField width150"/></div>
					<div class="myLabel width50"><!-- %=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.password.err")% --></div>
					<div class="myLabel width250">TODO If por facebook // Borrar este div...</div>
				</div>
				<div class="myRow">
					<div class="myLabel width50">Ubicaci&oacute;n</div>
					<div class="myLabel width160">
						<select name="" property="" onchange="" class="normalField width150">
							<option value="0">Seleccione</option>
						</select>
					</div>
					<div class="myLabel width210">
						<select name="" property="" onchange="" class="normalField width200">
							<option value="0">Seleccione</option>
						</select>
					</div>
					<div class="myLabel width210">
						<select name="" property="" onchange="" class="normalField width200">
							<option value="0">Seleccione</option>
						</select>
					</div>
				</div>
				<div class="myRow">
					<div class="myLabel" align="center"><input type="submit" value="ENVIAR DATOS DE REGISTRO<br/>Le enviaremos un E-Mail para validar la casilla de correo" class="buttonSubmit" /></div>
				</div>
			</div>
		</div>
		<!-- aca Termina el formulario -->
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>