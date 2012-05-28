<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.tdil.milka.model.ClickCounter"%>
<%@page import="com.tdil.milka.web.MeltButton"%>
<%@page import="com.tdil.milka.web.MilkaErrorFormatter"%>
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<head>
<%@ include file="includes/head.jsp" %>
<script type='text/javascript' src='./js/jquery.cookie.js'></script>
<script type='text/javascript' src='./js/jquery.melt-button.js'></script>
<script>
$(document).ready(
	function(){
		$("div[id^='mb-']").each(function(indice,valor) {
		   $(valor).meltbutton();
		});
	
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
		
			$("form[name='EmailEndingForm']").validate({
					errorPlacement: function(error, element) {
						error.appendTo( element.parent("div"));
					},
					rules: { 'authorBean.name': {required: true},
							'authorBean.email': {required: true, email: true},
							'authorBean.acceptPolitics': {required: true},
							'photoFormFile': {required: true}
					},
					messages: {
						'authorBean.name': {required: "<img id='nameerror' src='images/unchecked.gif' hovertext='Ingrese el nombre.' />"}, 
						'authorBean.email': {required: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese el email.' />",
								email: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese un email valido.' />"},
						'authorBean.acceptPolitics': {required: "<img id='politicserror' src='images/unchecked.gif' hovertext='Debe aceptar las politicas.' />"},
						'photoFormFile': {required: "<img id='photoerror' src='images/unchecked.gif' hovertext='Seleccione una foto.' />"}
					},
					submitHandler: function() {
			            $("form[name='EmailEndingForm']").ajaxSubmit({
			    			type: "POST",
			    			url: "./uploadEmailEnding.do",
			    			dataType: "json",
			    			success: postUpload
			    			});
			        }
				});
		}
	);

function postUpload(data) {
	if (data.result == 'OK') {
		$( "#dialog-modal" ).dialog({
				height: 140,
				modal: true,
				close: function(event, ui) {  document.location.href='./finalesDeEmail.jsp'; }
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
</head>
<body>

Agregar final de email
<html:form method="POST" action="/uploadEmailEnding" enctype="multipart/form-data">
	<div id="Nombre">Nombre:<html:text name="EmailEndingForm" property="authorBean.name" styleClass="width180"/><%=MilkaErrorFormatter.getErrorFrom(request, "Author.name.err")%></div>
	Email:<html:text name="EmailEndingForm" property="authorBean.email" styleClass="width180"/><%=MilkaErrorFormatter.getErrorFrom(request, "Author.email.err")%><br>
	Politicas:<html:checkbox name="EmailEndingForm" property="authorBean.acceptPolitics" styleClass="width180"/><%=MilkaErrorFormatter.getErrorFrom(request, "Author.politics.err")%><br>
	<html:file name="EmailEndingForm" property="photoFormFile" />
	<html:submit property="operation">
		Upload
	</html:submit><%=MilkaErrorFormatter.getErrorFrom(request, "EmailEndingForm.photo.err")%>
</html:form>

<div id="dialog-modal" class="hide" title="Finales de email">
	<p>
		Gracias por participar.<br>
		Te avisaremos cuando este aprobada.
	</p>
</div>
<div id="dialog-modal-err" class="hide" title="Finales de email">
	Ha ocurrido un error, intentelo nuevamente.
</div>
</body>
</html>