<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
<head>
<%@ include file="includes/head.jsp" %>
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

			$( "#product" ).autocomplete({
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
					alert(ui.item ?
							"Selected: " + ui.item.label  + "-" + ui.item.value + "-" + ui.item.path :
								"Nothing selected, input was " + this.value);
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


</script>
<%@ include file="includes/boErrorJS.jsp" %>

<style>

</style>
</head>
<body>
<div id="formContent">
<html:form method="POST" action="/addProfesional">
	Nombre<div id="Nombre"><html:text name="ProfesionalForm" property="firstname" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.firstname.err")%></div>
	Apellido<div id="Apellido"><html:text name="ProfesionalForm" property="lastname" styleClass="normalField"/></div>
	
	Codigo de area<div id="Telefono"><html:text name="ProfesionalForm" property="phoneAreaCode" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.phoneareacode_key + ".err")%></div>
	Numero<div id="Telefono"><html:text name="ProfesionalForm" property="phoneNumber" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.phonenumber_key + ".err")%></div>
	Extension<div id="Telefono"><html:text name="ProfesionalForm" property="phoneExtension" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.phoneextension_key + ".err")%></div>
	Tipo<div id="Telefono"><html:text name="ProfesionalForm" property="phoneType" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.phonetype_key + ".err")%></div>
	
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.lastname.err")%></div>
	Sexo<div id="Sexo"><html:radio property="sex" value="m" />Masculino <html:radio property="sex" value="f" />Femenino</div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.sex.err")%></div>
	Fecha Nac.<div id="Fecha Nac."><html:text name="ProfesionalForm" property="birthdate" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.birthdate.err")%></div>
	Email<div id="Email"><html:text name="ProfesionalForm" property="email" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.email.err")%></div>

	TODO If por facebook: <br>
	Password<div id="Password"><html:password name="ProfesionalForm" property="password" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.password.err")%></div>
	
	Nombre de la empresa<div id="Telefono"><html:text name="ProfesionalForm" property="businessname" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.businessname_key + ".err")%></div>
	Cuit<div id="Telefono"><html:text name="ProfesionalForm" property="cuit" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.cuit_key + ".err")%></div>
	IIBB<div id="Telefono"><html:text name="ProfesionalForm" property="iibb" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.iibb_key + ".err")%></div>
	
	Direccion <br>
	<% ProfesionalForm profesionalForm = (ProfesionalForm)session.getAttribute("ProfesionalForm"); %>
	<html:select name="ProfesionalForm" property="geo2Id" onchange="this.form.action='./refreshGeoLevel2Profesional.do';this.form.submit()">
		<option value="0">-<option>
		<% for (Geo2 geo2 : profesionalForm.getLevel2()) { %>	
			<option	<%=	geo2.getId() == profesionalForm.getGeo2Id() ? "selected" : ""%>
				value="<%=String.valueOf(geo2.getId())%>">
				<%=geo2.getNombre()%></option>
		<% } %>
	</html:select>
	<html:select name="ProfesionalForm" property="geo3Id" onchange="this.form.action='./refreshGeoLevel3Profesional.do';this.form.submit()">
		<option value="0">-<option>
		<% for (Geo3 geo3 : profesionalForm.getLevel3()) { %>	
			<option	<%=	geo3.getId() == profesionalForm.getGeo3Id() ? "selected" : ""%>
				value="<%=String.valueOf(geo3.getId())%>">
				<%=geo3.getNombre()%></option>
		<% } %>
	</html:select>
	<html:select name="ProfesionalForm" property="geo4Id">
		<option value="0">-<option>
		<% for (Geo4 geo4 : profesionalForm.getLevel4()) { %>	
			<option	<%=	geo4.getId() == profesionalForm.getGeo4Id() ? "selected" : ""%>
				value="<%=String.valueOf(geo4.getId())%>">
				<%=geo4.getNombre()%></option>
		<% } %>
	</html:select>
	
	Web<div id="Web"><html:text name="ProfesionalForm" property="website" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.website.err")%></div>
	Facebook<div id="Facebook"><html:text name="ProfesionalForm" property="facebook" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.facebook.err")%></div>
	Horario de Atencion<div id="Horario de Atencion"><html:textarea name="ProfesionalForm" property="businesshours" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.businesshours.err")%></div>
	Descripcion<div id="Descripcion"><html:textarea name="ProfesionalForm" property="description" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.description.err")%></div>
	
	
	<div class="ui-widget">
		<label for="product">Productos: </label>
		<input id="product" />
	</div>
	
	<div id="buttonHolder"><input type="submit" value="Enviar" class="okCircle" /></div>
</html:form>
</div>

</body>
</html>