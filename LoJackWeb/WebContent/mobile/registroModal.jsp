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
--><% if (websiteUser != null && websiteUser.isLogged()) { %>
	<jsp:forward page="home.jsp"></jsp:forward>
<% 	return;
	} %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
</head>
<body>
<h3>Registrate</h3>
<div class="myRow">Los campos marcados con * son requeridos para la registraci�n</div>
<html:form method="POST" action="/registerMobile">
	<% RegisterForm registerForm = (RegisterForm)session.getAttribute("RegisterFormMobile");
	registerForm.searchReferenceData();
	%>
	<div class="scrollable">
		<fieldset>
			<label>* Tipo de doc</label>
			<html:select name="RegisterFormMobile" property="documentType" >
				<option value="">Seleccione...</option>
				<% for (DocumentTypeBean codBean : LoginForm.getDocumentTypes()) { %>
					<option value="<%=codBean.getId()%>">
						<%=codBean.getName()%></option>
				<% } %>
			</html:select>
			<div class="errorInForm"></div>
			<div class="myRow errorField" style="display: none;" id="p.profile.documentType">
				<div id="err.profile.documentType"></div>
			</div>
		</fieldset>
			
		<fieldset>
			<label>* Numero</label>
			<html:text name="RegisterFormMobile" property="document" />
			<div class="errorInForm"></div>
			<div class="myRow errorField" style="display: none;" id="p.profile.document">
				<div id="err.profile.document"></div>
			</div>
		</fieldset>
		<fieldset>
			<label>* Nombre</label>
			<html:text name="RegisterFormMobile" property="firstName" />
				<%=(registerForm.isRequired(PersonFieldNames.firstName)) ? "" : ""%>
				<div class="errorInForm"></div>
				<div class="myRow errorField" style="display: none;" id="p.profile.firstname">
					<div id="err.profile.firstname"></div>
				</div>
		</fieldset>
		<fieldset>
			<label>* Apellido</label>
			<html:text name="RegisterFormMobile" property="lastName" />
			<%=(registerForm.isRequired(PersonFieldNames.lastName)) ? "" : ""%>
			<div class="errorInForm"></div>
			<div class="myRow errorField" style="display: none;" id="p.profile.lastname">
				<div id="err.profile.lastname"></div>
			</div>
		</fieldset>
		<fieldset>
			<label class="sexLabel">* Sexo:</label>
			<html:radio property="gender" value="Male" />
			<span>Masculino</span>
			<html:radio property="gender" value="Female" />
			<span>Femenino</span>
			<%=(registerForm.isRequired(PersonFieldNames.gender)) ? "" : ""%>
			<div class="myRow errorField" style="display: none;" id="p.profile.gender">
				<div id="err.profile.gender"></div>
			</div>
		</fieldset>
		<div class="errorInForm"></div>
		<fieldset>
			<label>* E-mail</label>
			<html:text name="RegisterFormMobile" property="email"/>
			<%=(registerForm.isRequired(PersonFieldNames.email)) ? "" : ""%>
			<div class="errorInForm"></div>
			<div class="myRow errorField" style="display: none;" id="p.profile.email">
				<div id="err.profile.email"></div>
			</div>
		</fieldset>
		<fieldset>
			<label>* Clave</label>
			<html:password name="RegisterFormMobile" property="password" />
			<%=(registerForm.isRequired(PersonFieldNames.password)) ? "" : ""%>
			<div class="errorInForm"></div>
			<div class="myRow errorField" style="display: none;" id="p.credential.password">
				<div id="err.credential.password"></div>
			</div>
		</fieldset>
		<fieldset>
			<label>* Fecha de nac.</label>
			<html:text name="RegisterFormMobile" property="birthDate" />
			<%=(registerForm.isRequired(PersonFieldNames.birthDate)) ? "" : ""%>
			<div class="errorInForm"></div>
			<div class="myRow errorField" style="display: none;" id="p.profile.birthDate">
				<div id="err.profile.birthDate"></div>
			</div>
		</fieldset>
		<fieldset>
			<label>C�digo de �rea</label>
			<html:text name="RegisterFormMobile" property="phoneAreaCode" />
			<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneAreaCode)) ? "" : ""%>
			<div class="errorInForm"></div>
			<div class="myRow errorField" style="display: none;" id="p.profile.phone.areaCode">
				<div id="err.profile.phone.areaCode"></div>
			</div>
		</fieldset>
		<fieldset>
			<label>Tel�fono celular</label>
			<html:text name="RegisterFormMobile" property="phoneNumber" />
			<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneNumber)) ? "" : ""%>
			<div class="errorInForm"></div>
			<div class="errorField" style="display: none;" id="p.profile.phone.number">
				<div id="err.profile.phone.number"></div>
			</div>
		</fieldset>
		<fieldset>
			<label>Pa�s</label>
			<span><%=registerForm.getCountrySelected()%></span>
		</fieldset>
		<fieldset>
			<label>Provincia</label>
			<html:select name="RegisterFormMobile" property="stateId" >
				<option value="">Seleccione...</option>
				<% for (StateBean stateBean : registerForm.getStates()) { %>
					<option <%=	stateBean.getId() == registerForm.getStateId() ? "selected" : ""%> value="<%=stateBean.getId()%>">
						<%=stateBean.getName()%></option>
				<% } %>
			</html:select>
			<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.stateId)) ? "" : ""%>
			<div class="errorInForm"></div>
			<div class="errorField" style="display: none;" id="p.profile.address.stateId">
				<div id="err.profile.address.stateId"></div>
			</div>
		</fieldset>
		<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.street1)) { %>
			<fieldset>
				<label>Calle 1</label>
				<html:text name="RegisterFormMobile" property="street1" />
				<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.street1)) ? "" : ""%>
				<div class="errorInForm"></div>
				<div class="errorField" style="display: none;" id="p.profile.address.street1">
					<div id="err.profile.address.street1"></div>
				</div>
			</fieldset>
		<% } %>
		<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.street2)) { %>
			<fieldset>
				<label>Calle 2</label>
				<html:text name="RegisterFormMobile" property="street2" />
				<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.street2)) ? "" : ""%>
				<div class="errorInForm"></div>
				<div class="errorField" style="display: none;" id="p.profile.address.street2">
					<div id="err.profile.address.street2"></div>
				</div>
			</fieldset>
		<% } %>
		<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.addressType)) { %>
			<fieldset>
				<label>Tipo</label>
				<html:select name="RegisterFormMobile" property="addressType">
					<option value="">Seleccione...</option>
					<% for (String type : registerForm.getAddressTypes()) { %>
						<option <%=type.equals(registerForm.getAddressType()) ? "selected" : ""%> value="<%=type%>">
						<%=ApplicationResources.getMessage("address_" + type)%></option>
					<% } %>
				</html:select>
				<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.addressType)) ? "" : ""%>
				<div class="errorInForm"></div>
				<div class="errorField" style="display: none;" id="p.profile.address.type">
					<div id="err.profile.address.type"></div>
				</div>
			</fieldset>
		<% } %>
		<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.postalCode)) { %>
			<fieldset>
				<label>C�digo postal</label>
				<html:text name="RegisterFormMobile" property="postalCode" />
				<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.postalCode)) ? "" : ""%>
				<div class="errorInForm"></div>
				<div class="errorField" style="display: none;" id="p.profile.address.postalCode">
					<div id="err.profile.address.postalCode"></div>
				</div>
			</fieldset>
		<% } %>
		<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.city)) { %>
			<fieldset>
				<label>Ciudad</label>
				<html:text name="RegisterFormMobile" property="city" />
				<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.city)) ? "" : ""%>
				<div class="errorInForm"></div>
				<div class="errorField" style="display: none;" id="p.profile.address.city">
					<div id="err.profile.address.city"></div>
				</div>
			</fieldset>
		<% } %>
	</div>
	<fieldset>
		<input type="submit" id="submitregister" value=" " class="indexLogin">
	</fieldset>
</html:form>
			
</body>
</html>