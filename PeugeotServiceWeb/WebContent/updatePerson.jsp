<%@ include file="includes/tryModal.jspf" %>
<%@page import="com.tdil.struts.resources.ApplicationResources"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@page import="com.tdil.thalamus.client.facade.json.beans.StateBean"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.BrandBean"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.CountryBean"%>
<%@page import="com.tdil.thalamus.client.facade.json.fields.PersonFieldNames"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.PersonFields"%>
<%@page import="com.tdil.ljpeugeot.struts.forms.RegisterForm"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%>
<script>

<%@ include file="includes/datePickerES.jspf" %>

		$("input[name=birthDate]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
			changeYear: true, minDate: "-100Y", maxDate: "+0D", yearRange: '-120:+0'});
		
		$("form[name='UpdatePersonForm']").validate({
			errorPlacement: function(error, element) {
				error.appendTo( element.next("div"));
			},
			rules: {
				'firstName': {required: true},
				'lastName': {required: true},
				'birthDate': {required: true}
			},
			messages: {
				'firstName': {required: "<span>Ingrese el nombre.</span>"},
				'lastName': {required: "<span>Ingrese el apellido.</span>"},
				'birthDate': {required: "<span>Ingrese la fecha de nacimiento.</span>"}
			},
			submitHandler: function() {
				<%@ include file="includes/blockUI.jspf" %>
				clearErrors();
	            $("form[name='UpdatePersonForm']").ajaxSubmit({
	    			type: "POST",
	    			url: "./updatePerson.do",
	    			dataType: "json",
	    			success: postRegister,
	    			<%@ include file="includes/openErrorLayerJS.jspf" %>
	    			});
	        }
		});
		
		$( "#closeregisterLayer" ).click(function() {
			$( "#updatePersonLayer" ).fadeOut();
		});
		
		$( "#linkBackUpdatePersonLayer" ).click(function() {
			$( "#updatePersonLayer" ).fadeOut();
		});

function clearErrors() {
	$("div[id^='err.']").each(function(index, valor) {
		$(valor).prop('innerHTML','');
	});
	$(".errorInForm").each(function(index, valor) {
		$(valor).prop('innerHTML','');
	});
}

function postRegister(data) {
	<%@ include file="includes/unblockUI.jspf" %>
	if (data.result == 'OK') {
		$( "#updatePersonLayer" ).fadeOut();
	} else {
		$.each(data, function(key, value) {
			var obj = document.getElementById('err.' + key);
			if (obj) {
				obj.innerHTML = value;
			}
        });
	}
}

</script>
<div id="registerLayer" class="layerOnTop" style="z-index: 1500; top:0px; left:0px;">
	<div id="centradorModalesEditProfile" class="layerModal width600">
		<section class="modal_header">
			<h2>Cambiar los datos de mi cuenta</h2>
			<button class="close" id="closeregisterLayer">Cerrar <span></span></button>
		</section>
		<section class="modal_content">
			<span class="modal_subtitle">Complete los datos requeridos</span>
			<p class="pBottom25">Los campos marcados con * son requeridos para la registración</p>
			
			<html:form method="POST" action="/updatePerson" styleClass="modal_wrapper">
				<% RegisterForm registerForm = (RegisterForm)session.getAttribute("UpdatePersonForm");
				%>
				<div class="scrollable height300">
					<div class="column">
						<fieldset>
							<label class="">* DNI</label>
							<label class="readOnly"><bean:write name="UpdatePersonForm" property="document"/></label>
							<div class="myRow errorField" style="display: none;" id="p.profile.document">
								<div id="err.profile.document"></div>
							</div>
						</fieldset>
						<fieldset>
							<label class="">* Sexo:</label>
							<label class="readOnly">
								<html:radio property="gender" value="Male" />
								<span>Masc.</span>
								<html:radio property="gender" value="Female" />
								<span>Fem.</span>
								<%=(registerForm.isRequired(PersonFieldNames.gender)) ? "" : ""%><div id="err.profile.gender"></div>
							</label>
						</fieldset>
						<% if (registerForm.isPrincipal(PersonFieldNames.email)) { %>
							<fieldset>
								<label class="">* E-mail</label>
								<label class="readOnly"><bean:write name="UpdatePersonForm" property="email"/></label>
							</fieldset>
						<% } else { %>
							<fieldset>
								<label class="">* E-Mail</label>
								<html:text name="UpdatePersonForm" property="email"/>
								<div id="err.profile.email"></div>
							</fieldset>
						<% } %>
						<fieldset>
							<label class="">Cód. área</label>
							<html:text name="UpdatePersonForm" property="phoneAreaCode" />
							<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneAreaCode)) ? "*" : ""%><div id="err.profile.phone.areaCode"></div>
						</fieldset>
						<fieldset>
							<label class="">Celular</label>
							<html:text name="UpdatePersonForm" property="phoneNumber" />
							<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneNumber)) ? "*" : ""%><div id="err.profile.phone.number"></div>
						</fieldset>
						<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.street1)) { %>
							<fieldset>
								<label class="">Calle 1</label>
								<html:text name="UpdatePersonForm" property="street1" />
								<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.street1)) ? "*" : ""%><div id="err.profile.address.street1"></div>
							</fieldset>
						<% } %>
						<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.addressType)) { %>
							<fieldset>
								<label class="">Tipo</label>
								<html:select name="UpdatePersonForm" property="addressType">
									<option value="">Seleccione...</option>
										<% for (String type : registerForm.getAddressTypes()) { %>
											<option <%=type.equals(registerForm.getAddressType()) ? "selected" : ""%> value="<%=type%>">
											<%=ApplicationResources.getMessage("address_" + type)%></option>
										<% } %>
								</html:select>
								<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.addressType)) ? "*" : ""%><div id="err.profile.address.type"></div>
							</fieldset>
						<% } %>
					</div>
					<div class="column">
						<fieldset>
							<label class="">* Nombre</label>
							<html:text name="UpdatePersonForm" property="firstName" />
							<div class="errorInForm"></div>
							<%=(registerForm.isRequired(PersonFieldNames.firstName)) ? "" : ""%><div id="err.profile.firstname"></div>
						</fieldset>
						<fieldset>
							<label class="">* Apellido</label>
							<html:text name="UpdatePersonForm" property="lastName" />
							<div class="errorInForm"></div>
							<%=(registerForm.isRequired(PersonFieldNames.lastName)) ? "" : ""%><div id="err.profile.lastname"></div>
						</fieldset>
						<fieldset>
							<label class="">* Fecha nac.</label>
							<html:text name="UpdatePersonForm" property="birthDate" />
							<div class="errorInForm"></div>
							<%=(registerForm.isRequired(PersonFieldNames.birthDate)) ? "" : ""%>
						</fieldset>
						<fieldset>
							<label class="">País</label>
							<label class="readOnly"><%=registerForm.getCountrySelected()%></label>
						</fieldset>
						<fieldset>
							<label class="">Provincia</label>
							<html:select name="UpdatePersonForm" property="stateId">
								<option value="">Seleccione...</option>
								<% for (StateBean state : registerForm.getStates()) { %>
									<option <%=	state.getId() == registerForm.getStateId() ? "selected" : ""%> value="<%=state.getId()%>">
									<%=state.getName()%></option>
								<% } %>
							</html:select>
							<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.stateId)) ? "*" : ""%><div id="err.profile.address.stateId"></div>
						</fieldset>
						<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.city)) { %>
							<fieldset>
								<label class="">Ciudad</label>
								<html:text name="UpdatePersonForm" property="city" />
								<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.city)) ? "*" : ""%><div id="err.profile.address.city"></div>
							</fieldset>
						<% } %>
						<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.street2)) { %>
							<fieldset>
								<label class="">Calle 2</label>
								<html:text name="UpdatePersonForm" property="street2" />
								<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.street2)) ? "*" : ""%><div id="err.profile.address.street2"></div>
							</fieldset>
						<% } %>
						<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.postalCode)) { %>
							<fieldset>
								<label class="">C. postal</label>
								<html:text name="UpdatePersonForm" property="postalCode" />
								<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.postalCode)) ? "*" : ""%><div id="err.profile.address.postalCode"></div>
							</fieldset>
						<% } %>
					</div>
					<h4>Cláusula de contacto</h4>
					<fieldset class="width100per">
						<label class="width100per">
							<html:checkbox name="UpdatePersonForm" property="optInAccepted"/>
							Acepto recibir contactos de Peugeot y sus compañías asociadas
						</label>
					</fieldset>
				</div>
				<fieldset class="button_bar pOnlyTop25">
					<button class="botton_ahead" id="submitregister">Guardar<span></span></button>
				</fieldset>
			</html:form>
		</section>
	</div>
</div>
<%@ include file="includes/catchModal.jspf" %>