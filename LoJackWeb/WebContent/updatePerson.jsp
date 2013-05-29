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
<%@page import="com.tdil.lojack.struts.forms.RegisterForm"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%>
<script>

<%@ include file="includes/datePickerES.jspf" %>

		$("input[name=birthDate]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
			changeYear: true, minDate: "-100Y", maxDate: "+0D"});
		
		$("form[name='UpdatePersonForm']").validate({
			errorPlacement: function(error, element) {
				error.appendTo( element.parent("div"));
			},
			rules: { 			},
			messages: {			},
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

function clearErrors() {
	$("div[id^='err.']").each(function(index, valor) {
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
	<div class="registerLayerStyles editProfileLayer">
		<div id="updatePersonLayerID" class="registerLayerContent">
			<div id="xContainer"><button id="closeregisterLayer">X</button></div>
			<h3>Modificar mis datos</h3>
			<div class="myRow">Los campos marcados con * son requeridos para la registración</div>
				<html:form method="POST" action="/updatePerson">
				<% RegisterForm registerForm = (RegisterForm)session.getAttribute("UpdatePersonForm");
				%>
				<div class="scrollable">
					<fieldset>
						<label>* DNI</label>
						<label class="readOnly"><bean:write name="UpdatePersonForm" property="document"/></label>
						<div class="myRow errorField" style="display: none;" id="p.profile.document">
							<div id="err.profile.document"></div>
						</div>
					</fieldset>
					<fieldset>
						<label>* Nombre</label>
						<html:text name="UpdatePersonForm" property="firstName" />
						<%=(registerForm.isRequired(PersonFieldNames.firstName)) ? "" : ""%><div id="err.profile.firstName"></div>
					</fieldset>
					<fieldset>
						<label>* Apellido</label>
						<html:text name="UpdatePersonForm" property="lastName" />
						<%=(registerForm.isRequired(PersonFieldNames.lastName)) ? "" : ""%><div id="err.profile.lastName"></div>
					</fieldset>
					<fieldset>
						<label class="sexLabel">* Sexo:</label>
						<html:radio property="gender" value="Male" />
						<span>Masculino</span>
						<html:radio property="gender" value="Female" />
						<span>Femenino</span>
						<%=(registerForm.isRequired(PersonFieldNames.gender)) ? "" : ""%><div id="err.profile.gender"></div>
					</fieldset>
					<% if (registerForm.isPrincipal(PersonFieldNames.email)) { %>
						<fieldset>
							<label>* E-mail</label>
							<label class="readOnly"><bean:write name="UpdatePersonForm" property="email"/></label>
						</fieldset>
					<% } else { %>
						<fieldset>
							<label>* Clave</label>
							<html:text name="UpdatePersonForm" property="email" />
							<div id="err.profile.email"></div>
						</fieldset>
					<% } %>
					<fieldset>
						<label>* Fecha de nac.</label>
						<html:text name="UpdatePersonForm" property="birthDate" />
						<%=(registerForm.isRequired(PersonFieldNames.birthDate)) ? "" : ""%>
					</fieldset>
					<fieldset>
						<label>Código de área</label>
						<html:text name="UpdatePersonForm" property="phoneAreaCode" />
						<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneAreaCode)) ? "*" : ""%><div id="err.profile.phone.areaCode"></div>
					</fieldset>
					<fieldset>
						<label>Teléfono celular</label>
						<html:text name="UpdatePersonForm" property="phoneNumber" />
						<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneNumber)) ? "*" : ""%><div id="err.profile.phone.number"></div>
					</fieldset>
					<fieldset>
						<label>País</label>
						<span><%=registerForm.getCountrySelected()%></span>
					</fieldset>
					<fieldset>
						<label>Provincia</label>
						<html:select name="UpdatePersonForm" property="stateId">
							<option value="">Seleccione...</option>
							<% for (StateBean state : registerForm.getStates()) { %>
								<option <%=	state.getId() == registerForm.getStateId() ? "selected" : ""%> value="<%=state.getId()%>">
								<%=state.getName()%></option>
							<% } %>
						</html:select>
						<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.stateId)) ? "*" : ""%><div id="err.profile.address.stateId"></div>
					</fieldset>
					<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.street1)) { %>
						<fieldset>
							<label>Calle 1</label>
							<html:text name="UpdatePersonForm" property="street1" />
							<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.street1)) ? "*" : ""%><div id="err.profile.address.street1"></div>
						</fieldset>
					<% } %>
					<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.street2)) { %>
						<fieldset>
							<label>Calle 2</label>
							<html:text name="UpdatePersonForm" property="street2" />
							<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.street2)) ? "*" : ""%><div id="err.profile.address.street2"></div>
						</fieldset>
					<% } %>
					<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.addressType)) { %>
						<fieldset>
							<label>Tipo</label>
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
					<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.postalCode)) { %>
						<fieldset>
							<label>Código postal</label>
							<html:text name="UpdatePersonForm" property="postalCode" />
							<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.postalCode)) ? "*" : ""%><div id="err.profile.address.postalCode"></div>
						</fieldset>
					<% } %>
					<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.city)) { %>
						<fieldset>
							<label>Ciudad</label>
							<html:text name="UpdatePersonForm" property="city" />
							<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.city)) ? "*" : ""%><div id="err.profile.address.city"></div>
						</fieldset>
					<% } %>
				</div>
				<fieldset><input type="submit" id="submitregister" value="Guardar" class="indexButtonBase"></fieldset>
			</html:form>
		</div>
	</div>
</div>