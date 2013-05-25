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
		error.appendTo( element.parent("div").next("div"));
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
   			success: postContact
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
	<div class="registerLayerStyles editProfileLayer">
		<div class="registerLayerContent">
			<div id="xContainer"><button id="closecontactLayer" style="margin-left:60px;">X</button></div>
			<h3>Contacto</h3>
			<div id="errcontact"></div>
			<html:form method="POST" action="/contact">
			<% if (!contactForm.isRegisteredUser()) { %>
				<div>Nombre: <html:text name="ContactForm" property="firstname"></html:text></div>
				<div></div><br>
				<div>Apellido: <html:text name="ContactForm" property="lastname"></html:text></div>
				<div></div><br>
				<div>DNI: <html:text name="ContactForm" property="documentNumber"></html:text></div>
				<div></div><br>
				<div>Email: <html:text name="ContactForm" property="email"></html:text></div>
				<div></div><br>
				<div>Telefono: <html:text name="ContactForm" property="phone"></html:text></div>
				<div></div><br>
			<% } %>
			Contenido: <div></div><html:textarea name="ContactForm" property="content"></html:textarea></div>
			<div></div><br>
				<fieldset><button id="submitregister" class="indexButtonBase">Grabar</button></fieldset>
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
				<fieldset>
					<div style="padding:20px 0 0 0; float:right;"><button type="button" id="buttonclosecontactLayerThanks" class="indexButtonBase">Cerrar</button></div>
				</fieldset>
		</div>
	</div>
</div>