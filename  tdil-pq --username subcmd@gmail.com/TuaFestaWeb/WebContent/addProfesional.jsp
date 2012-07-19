<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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

			
			$("input[name=birthdate]").datepicker({dateFormat: 'yy-mm-dd'});
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
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.lastname.err")%></div>
	Sexo<div id="Sexo"><html:radio property="sex" value="m" />Masculino <html:radio property="sex" value="f" />Femenino</div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.sex.err")%></div>
	Fecha Nac.<div id="Fecha Nac."><html:text name="ProfesionalForm" property="birthdate" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.birthdate.err")%></div>
	Telefono<div id="Telefono"><html:text name="ProfesionalForm" property="phone" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.phone.err")%></div>
	Email<div id="Email"><html:text name="ProfesionalForm" property="email" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.email.err")%></div>
	Password<div id="Password"><html:password name="ProfesionalForm" property="password" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.password.err")%></div>
	Web<div id="Web"><html:text name="ProfesionalForm" property="website" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.website.err")%></div>
	Facebook<div id="Facebook"><html:text name="ProfesionalForm" property="facebook" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.facebook.err")%></div>
	Horario de Atencion<div id="Horario de Atencion"><html:textarea name="ProfesionalForm" property="businesshours" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.businesshours.err")%></div>
	Descripcion<div id="Descripcion"><html:textarea name="ProfesionalForm" property="description" styleClass="normalField"/></div>
	<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.description.err")%></div>
	<div id="buttonHolder"><input type="submit" value="Enviar" class="okCircle" /></div>
</html:form>
</div>

</body>
</html>