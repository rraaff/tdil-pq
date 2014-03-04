<%@ include file="includes/tryModal.jspf" %><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.ContactForm"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertiesKeys"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertyUtils"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><%@ page info="home"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><script>
<%ContactForm contactForm = (ContactForm)session.getAttribute("ContactForm");%>

$("form[name='ContactForm']").validate({
	errorPlacement: function(error, element) {
		error.appendTo( element.parent("fieldset").next("div"));
	},
	<%if (contactForm.isRegisteredUser()) {%>
		rules: { 'content': {required: true}
		},
		messages: {
			'content': {required: "<span>Ingrese el contenido.</span>"}
		},
	<%} else {%>
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
	<%}%>
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
		$('#errcontact').css('display', 'block');
		$('#errcontact').prop('innerHTML','Ha ocurrido un error');
	}
}

</script>
<!-- contacto -->
<div id="changePassLayer" class="layerOnTop" style="z-index:1502;">
	<div id="centradorModalesContactLayer" class="layerModal width600">
		<section class="modal_header">
			<h2>Contacto</h2>
			<button class="close" id="closecontactLayer">Cerrar <span></span></button>
		</section>
		<section class="modal_content">
			<span class="modal_subtitle">Complete el formulario con los datos requeridos</span>
			<div class="alert alert-error" id="errcontact" style="display:none;"></div>
			<html:form method="POST" action="/contact" styleClass="modal_wrapper">
				<% if (!contactForm.isRegisteredUser()) { %>
					<div class="column">
						<fieldset>
							<label>Nombre</label>
							<html:text name="ContactForm" property="firstname"></html:text>
						</fieldset>
						<div class="errorInForm"></div>
						<fieldset>
							<label>Documento</label>
							<html:text name="ContactForm" property="documentNumber"></html:text>
						</fieldset>
						<div class="errorInForm"></div>
						<fieldset>
							<label>Teléfono</label>
							<html:text name="ContactForm" property="phone"></html:text>
						</fieldset>
						<div class="errorInForm"></div>
					</div>
					<div class="column">
						<fieldset>
							<label>Apellido</label>
							<html:text name="ContactForm" property="lastname"></html:text>
						</fieldset>
						<div class="errorInForm"></div>
						<fieldset>
							<label>E-mail</label>
							<html:text name="ContactForm" property="email"></html:text>
						</fieldset>
						<div class="errorInForm"></div>

				<% } %>
					<fieldset class="width100per">
						<label>Comentario</label>
						<html:textarea name="ContactForm" property="content"></html:textarea>
					</fieldset>
					<div class="errorInForm"></div>
				</div>
				<fieldset class="button_bar pOnlyTop25">
					<button class="link_back" cl="contactLayer"><span></span>Cerrar</button>
					<button class="botton_ahead" id="submitregister">Enviar</button>
				</fieldset>
			</html:form>
		</section>
	</div>
</div>
<div id="contactLayerThanks" class="layerOnTop" style="display:none; z-index:1502;">
	<div class="layerModal width400">
		<section class="modal_header">
			<h2>Contacto</h2>
			<button class="close" id="closecontactLayerThanks">Cerrar <span></span></button>
		</section>
		<section class="modal_content">
			<span class="modal_subtitle">Gracias por su mensaje</span>
			<form>
				<fieldset class="button_bar pOnlyTop25">
					<button class="link_back" cl="contactLayerThanks"><span></span>Cerrar</button>
				</fieldset>
			</form>
		</section>
	</div>
</div>
<%@ include file="includes/catchModal.jspf" %>