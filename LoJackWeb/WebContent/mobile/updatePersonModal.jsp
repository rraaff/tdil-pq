<%@ page info="index"%><!--
--><%@page import="com.tdil.lojack.struts.forms.RegisterForm"%>
<%@page import="com.tdil.lojack.struts.forms.LoginForm"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.DocumentTypeBean"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.StateBean"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.BrandBean"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.CountryBean"%>
<%@page import="com.tdil.thalamus.client.facade.json.fields.PersonFieldNames"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.PersonFields"%>
<%@page import="com.tdil.struts.resources.ApplicationResources"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="../includes/checkThalamusUp.jspf" %><!--
--><%@ include file="../includes/userLogged.jspf" %><!--
--><%@ include file="./includes/mustBeLogged.jspf" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
</head>
<body>
<h3>Modificar mis datos</h3>
<html:form method="POST" action="/updatePersonMobile">
	<% RegisterForm registerForm = (RegisterForm)session.getAttribute("UpdatePersonFormMobile");
	%>
	<div class="scrollable">
		<fieldset>
			<label>* DNI</label>
			<label class="readOnly"><bean:write name="UpdatePersonFormMobile" property="document"/></label>
			<div class="myRow errorField" style="display: none;" id="p.profile.document">
				<div id="err.profile.document"></div>
			</div>
		</fieldset>
		<fieldset>
			<label>* Nombre</label>
			<html:text name="UpdatePersonFormMobile" property="firstName" />
			<div class="errorInForm"></div>
			<%=(registerForm.isRequired(PersonFieldNames.firstName)) ? "" : ""%><div id="err.profile.firstName"></div>
		</fieldset>
		<fieldset>
			<label>* Apellido</label>
			<html:text name="UpdatePersonFormMobile" property="lastName" />
			<div class="errorInForm"></div>
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
				<label class="readOnly"><bean:write name="UpdatePersonFormMobile" property="email"/></label>
			</fieldset>
		<% } else { %>
			<fieldset>
				<label>* E-Mail</label>
				<html:text name="UpdatePersonFormMobile" property="email" />
				<div id="err.profile.email"></div>
			</fieldset>
		<% } %>
		<fieldset>
			<label>* Fecha de nac.</label>
			<html:text name="UpdatePersonFormMobile" property="birthDate" />
			<div class="errorInForm"></div>
			<%=(registerForm.isRequired(PersonFieldNames.birthDate)) ? "" : ""%>
		</fieldset>
		<fieldset>
			<label>C�digo de �rea</label>
			<html:text name="UpdatePersonFormMobile" property="phoneAreaCode" />
			<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneAreaCode)) ? "*" : ""%><div id="err.profile.phone.areaCode"></div>
		</fieldset>
		<fieldset>
			<label>Tel�fono celular</label>
			<html:text name="UpdatePersonFormMobile" property="phoneNumber" />
			<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneNumber)) ? "*" : ""%><div id="err.profile.phone.number"></div>
		</fieldset>
		<fieldset>
			<label>Pa�s</label>
			<span><%=registerForm.getCountrySelected()%></span>
		</fieldset>
		<fieldset>
			<label>Provincia</label>
			<html:select name="UpdatePersonFormMobile" property="stateId">
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
				<html:text name="UpdatePersonFormMobile" property="street1" />
				<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.street1)) ? "*" : ""%><div id="err.profile.address.street1"></div>
			</fieldset>
		<% } %>
		<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.street2)) { %>
			<fieldset>
				<label>Calle 2</label>
				<html:text name="UpdatePersonFormMobile" property="street2" />
				<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.street2)) ? "*" : ""%><div id="err.profile.address.street2"></div>
			</fieldset>
		<% } %>
		<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.addressType)) { %>
			<fieldset>
				<label>Tipo</label>
				<html:select name="UpdatePersonFormMobile" property="addressType">
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
				<label>C�digo postal</label>
				<html:text name="UpdatePersonFormMobile" property="postalCode" />
				<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.postalCode)) ? "*" : ""%><div id="err.profile.address.postalCode"></div>
			</fieldset>
		<% } %>
		<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.city)) { %>
			<fieldset>
				<label>Ciudad</label>
				<html:text name="UpdatePersonFormMobile" property="city" />
				<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.city)) ? "*" : ""%><div id="err.profile.address.city"></div>
			</fieldset>
		<% } %>
	</div>
	<fieldset><input type="submit" id="submitregister" value="Guardar" class="indexButtonBase"></fieldset>
</html:form>
			
</body>
</html>