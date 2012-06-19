<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
					},
					submitHandler: function() {
			            $("form[name='ProfesionalForm']").ajaxSubmit({
			    			type: "POST",
			    			url: "./addProfesional.do",
			    			dataType: "json",
			    			success: postAdd
			    			});
			        }
				});
		}
	);

function postAdd(data) {
	if (data.result == 'OK') {
		$( "#dialog-modal" ).dialog({
				height: 140,
				modal: true }
			});
	} else {
		$( "#dialog-modal-err" ).dialog({
				height: 140,
				modal: true
			});
	}
}
</script>
<%@ include file="includes/boErrorJS.jsp" %>

<style>

</style>
</head>
<body>
<div id="formContent">
<html:form method="POST" action="/addProfesional">
	Nombre<div id="Nombre"><html:text name="ProfesionalForm" property="firstname" styleClass="normalField"/></div>
	Apellido<div id="Apellido"><html:text name="ProfesionalForm" property="lastname" styleClass="normalField"/></div>
	Sexo<div id="Sexo"><html:text name="ProfesionalForm" property="sex" styleClass="normalField"/></div>
	Fecha Nac.<div id="Fecha Nac."><html:text name="ProfesionalForm" property="birthdate" styleClass="normalField"/></div>
	Telefono<div id="Telefono"><html:text name="ProfesionalForm" property="phone" styleClass="normalField"/></div>
	Email<div id="Email"><html:text name="ProfesionalForm" property="email" styleClass="normalField"/></div>
	Password<div id="Password"><html:text name="ProfesionalForm" property="password" styleClass="normalField"/></div>
	Web<div id="Web"><html:text name="ProfesionalForm" property="website" styleClass="normalField"/></div>
	Facebook<div id="Facebook"><html:text name="ProfesionalForm" property="facebook" styleClass="normalField"/></div>
	Horario de Atencion<div id="Horario de Atencion"><html:text name="ProfesionalForm" property="businesshours" styleClass="normalField"/></div>
	Descripcion<div id="Descripcion"><html:text name="ProfesionalForm" property="description" styleClass="normalField"/></div>
	<div id="buttonHolder"><input type="submit" value="Enviar" class="okCircle" /></div>
</html:form>
</div>

<div id="dialog-modal" class="hide" title="Registro">
	<p>
		Gracias por registrarte.<br>
		Te avisaremos cuando te hayamos aprobado.
	</p>
</div>
<div id="dialog-modal-err" class="hide" title="Registro">
	Ha ocurrido un error, intentelo nuevamente.
</div>
</body>
</html>