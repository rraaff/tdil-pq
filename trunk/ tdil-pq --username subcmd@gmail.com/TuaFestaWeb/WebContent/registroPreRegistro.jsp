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
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="registroPreRegistro"%>
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
<style>
<!-- 
#moduloPreReg {
	background-repeat: no-repeat;
	background-position: center center;
	
	margin-top:20px;
	margin-bottom:20px;
	padding:30px;
	width:404px;
	height:194px;
	float:left;
}
#moduloPreReg h2 {
	font-size:20px;
	line-height:25px;
	padding-top:25px;
	padding-left:0;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.6);
}
#moduloPreReg p {
	color:#252e35;
	font-size:13px;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.6);
}
#moduloPreReg a {
	color:#004089;
}
#moduloPreReg a:hover {
	color:#000;
	text-shadow:none;
}
.cliente {
	background-image: url(images/skin_basic/backgrounds/preRegistroUsers.gif);
	margin-right:10px;
}
.cliente_title {
	color:#035b9c;
}
.profesional {
	background-image: url(images/skin_basic/backgrounds/preRegistroProfesionales.gif);
}
.profesional_title {
	color:#FFF;
	font-weight:bold;
	text-shadow: 0 1px 0 rgba(0, 0, 0, 0.6);
}
.remarcadoDCF6FC {
	color:#dcf6fc;
	font-weight:bold;
	text-shadow: 0 1px 0 rgba(0, 0, 0, 0.6);
}
#buttonFB {
	background-image: url(images/skin_basic/buttons/registerClientFB_off.png);
	width:328px;
	height:65px;
	margin:26px auto 0 auto;
}
#buttonFB:hover {
	background-image: url(images/skin_basic/buttons/registerClientFB_on.png);
}
#buttonProf {
	background-image: url(images/skin_basic/buttons/preReg_registerProfessional_off.png);
	height: 65px;
	width: 229px;
	margin:0 auto;
}
#buttonProf:hover {
	background-image: url(images/skin_basic/buttons/preReg_registerProfessional_on.png);
}
-->
</style>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Registro</h1>
		</div>
		<div id="formContent">
			<div id="moduloPreReg" class="cliente">
				<h2>Te queres registrar para contactarte y ser contactado por los profesionales?</h2>
				<p>Podes registrarte sin costo alguno usando tu cuenta de Facebook o <a href="./goToRegistroCliente.do" title="Registrate sin usar tu cuenta de Facebook">simplemente cargando unos datos b&aacute;sicos.</a></p>
				<div id="buttonFB"><a href="#"><img src="images/null.gif" width="328" height="65" /></a></div>
			</div>
			<div id="moduloPreReg" class="profesional">
				<h2><span class="profesional_title">Queres ofrecer tus productos o servicios? </span><span class="remarcadoDCF6FC">Registrate gratis</span></h2>
				<p>Podes registrarte usando tu cuenta de <a href="#">facebook</a> o cargando tus datos como profesional. Luego de corroborar tus datos podr&aacute;s publicar tus productos o servicios.</p>
				<div id="buttonProf"><a href="./goToRegistroProfesional.do"><img src="images/null.gif" width="229" height="65" /></a></div>
			</div>
		</div>
		<!-- aca Termina el formulario -->
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>