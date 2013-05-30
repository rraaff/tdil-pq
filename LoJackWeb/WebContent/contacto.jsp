<%@page import="com.tdil.lojack.struts.forms.ContactForm"%>
<%@page import="com.tdil.lojack.utils.SystemPropertiesKeys"%>
<%@page import="com.tdil.lojack.utils.SystemPropertyUtils"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><!--
--><%@ page info="home"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<script>
<% ContactForm contactForm = (ContactForm)session.getAttribute("ContactForm"); %>

$("form[name='ContactForm']").validate({
	errorPlacement: function(error, element) {
		error.appendTo( element.parent("fieldset").next("div"));
	},
	<% if (contactForm.isRegisteredUser()) { %>
		rules: { 'content': {required: true}
		},
		messages: {
			'content': {required: "<span>Ingrese el contenido.</span>"}
		},
	<% } else { %>
		rules: { 'firstname': {required: true},
			'lastname': {required: true},
			'documentNumber': {required: true},
			'email': {required: true, email: true},
			'phone': {required: true},
			'content': {required: true}
		},
		messages: {
			'firstname': {required: "<span>Ingrese el nombre.</span>"},
			'lastname': {required: "<span>Ingrese el apellido.</span>"},
			'documentNumber': {required: "<span>Ingrese el numero de documento.</span>"},
			'email': {required: "<span>Ingrese el email.</span>",
					email: "<span>Ingrese un email valido.</span>"},
			'phone': {required: "<span>Ingrese el teléfono.</span>"},
			'content': {required: "<span>Ingrese el contenido.</span>"}
		},
	<% } %>
	submitHandler: function() {
		<%@ include file="includes/blockUI.jspf" %>
		clearErrors();
           $("form[name='ContactForm']").ajaxSubmit({
   			type: "POST",
   			url: "./contact.do",
   			dataType: "json",
   			success: postContact,
   			<%@ include file="includes/openErrorLayerJS.jspf" %>
   			});
       }
});

$( "#closecontactLayer" ).click(function() {
	$( "#contactLayer" ).fadeOut();
});

$( "#closecontactLayerThanks" ).click(function() {
	$( "#contactLayerThanks" ).fadeOut();
});
$( "#buttonclosecontactLayerThanks" ).click(function() {
	$( "#contactLayerThanks" ).fadeOut();
});

function clearErrors() {
	$('#errcontact').prop('innerHTML','');
	$("div[id^='err.']").each(function(index, valor) {
		$(valor).prop('innerHTML','');
	});
}

function postContact(data) {
	<%@ include file="includes/unblockUI.jspf" %>
	if (data.result == 'OK') {
		$( "#contactLayer" ).fadeOut();
		centerLayer($(window), $( "#contactLayerThanks" ));
	} else {
		$('#errcontact').prop('innerHTML','Ha ocurrido un error');
	}
}

</script>
<div id="changePassLayer" class="layerOnTop" style="z-index: 1500;">
	<div id="centradorModalesContactLayer" class="contactLayerStyle">
		<div class="contactLayerContent">
			<div id="xContainer"><button id="closecontactLayer">X</button></div>
			<h3>Contacto</h3>
			<div id="errcontact"></div>
			<html:form method="POST" action="/contact">
				<% if (!contactForm.isRegisteredUser()) { %>
					<fieldset>
						<label>Nombre</label>
						<html:text name="ContactForm" property="firstname"></html:text>
					</fieldset>
					<div class="errorInForm"></div>
					<fieldset>
						<label>Apellido</label>
						<html:text name="ContactForm" property="lastname"></html:text>
					</fieldset>
					<div class="errorInForm"></div>
					<fieldset>
						<label>DNI</label>
						<html:text name="ContactForm" property="documentNumber"></html:text>
					</fieldset>
					<div class="errorInForm"></div>
					<fieldset>
						<label>E-mail</label>
						<html:text name="ContactForm" property="email"></html:text>
					</fieldset>
					<div class="errorInForm"></div>
					<fieldset>
						<label>Teléfono</label>
						<html:text name="ContactForm" property="phone"></html:text>
					</fieldset>
					<div class="errorInForm"></div>
				<% } %>
				<fieldset>
					<label>Comentario</label>
					<html:textarea name="ContactForm" property="content"></html:textarea>
				</fieldset>
				<div class="errorInForm"></div>
				<fieldset>
					<button id="submitregister" class="indexButtonBase">Enviar</button>
				</fieldset>
			</html:form>
		</div>
	</div>
</div>
<div id="contactLayerThanks" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="defaultLayerStyles">
		<div class="loginLayerContent">
			<div id="xContainer"><button class="buttonLink" id="closecontactLayerThanks">X</button></div>
			<h3>Contacto</h3>
			<div class="alert alert-block">Gracias por dejarnos su mensaje.</div>
			<fieldset><button type="button" id="buttonclosecontactLayerThanks" class="indexButtonBase">Cerrar</button></fieldset>
		</div>
	</div>
</div>