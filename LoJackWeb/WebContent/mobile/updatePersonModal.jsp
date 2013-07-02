<%@page import="com.tdil.utils.DateUtils"%>
<%@ page info="home"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.RegisterForm"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.LoginForm"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.DocumentTypeBean"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.StateBean"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.BrandBean"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.CountryBean"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.fields.PersonFieldNames"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.PersonFields"%><%--
--%><%@page import="com.tdil.struts.resources.ApplicationResources"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="../includes/checkThalamusUp.jspf" %><%--
--%><%@ include file="../includes/userLogged.jspf" %><%--
--%><%@ include file="./includes/mustBeLogged.jspf" %><%--
--%><!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="../favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/reset-styles.css" rel="stylesheet" type="text/css">
<link href="css/internal_menu.css" rel="stylesheet" type="text/css">
<link href="css/mobile_main.css" rel="stylesheet" type="text/css">
<style type="text/css">
@media only screen and (max-height : 320px) {
	#user {
		display: none;
	}
}
</style>
</head>
<body>
<div id="user"><span class="userSaludation">Hola:&nbsp;</span><span class="userName"><%=websiteUser.getName()%></span></div>
<div id="internalHeader">
	<ul>
		<li><a href="./goToChangePasswordMobile.do" title="Cambiar mis clave">Cambiar clave</a></li>
		<li><a href="home.jsp" title="Volver">< Volver</a></li>
		<li><a href="./logoutMobile.do" class="back" title="Salir del sistema">Salir</a></li>
	</ul>
</div>
<div id="registrationContent">
	<h1>Modificar mis datos</h1>
	<p align="center">Los campos marcados con * son requeridos</p>
	<html:form method="POST" action="/mobile/updatePersonMobile">
		<% RegisterForm registerForm = (RegisterForm)session.getAttribute("UpdatePersonFormMobile");
		registerForm.setMobile(true);
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
				<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "RegisterForm.firstname.err")%>
				<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "profile.firstname.err")%>
			</fieldset>
			<fieldset>
				<label>* Apellido</label>
				<html:text name="UpdatePersonFormMobile" property="lastName" />
				<div class="errorInForm"></div>
				<%=(registerForm.isRequired(PersonFieldNames.lastName)) ? "" : ""%><div id="err.profile.lastName"></div>
				<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "RegisterForm.lastname.err")%>
				<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "profile.lastname.err")%>
			</fieldset>
			<fieldset class="sexFieldset">
				<label class="sexLabel">* Sexo:</label>
			</fieldset>
			<fieldset class="sexOptionsFieldset">
				<html:radio property="gender" value="Male" />
				<span>Masculino&nbsp;&nbsp;&nbsp;</span>
				<html:radio property="gender" value="Female" />
				<span>Femenino</span>
				<%=(registerForm.isRequired(PersonFieldNames.gender)) ? "" : ""%>
				<div id="err.profile.gender"></div>
				<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "RegisterForm.gender.err")%>
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
					<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "RegisterForm.email.err")%>
				</fieldset>
			<% } %>
			<fieldset>
				<label>* Fecha de nac.</label>
				<%=(registerForm.isRequired(PersonFieldNames.birthDate)) ? "" : ""%>
				<div class="dateHelper">
					<html:select name="UpdatePersonFormMobile" property="year" styleClass="year">
						<option value=""></option>
						<% for (String year : registerForm.getYears()) { %>
							<option value="<%=year%>" <%=year.equals(registerForm.getYear()) ? "selected" : ""%>><%=year%></option>
						<% } %>
					</html:select>
					<html:select name="UpdatePersonFormMobile" property="month" styleClass="day-month">
						<option value=""></option>
						<% for (String month : DateUtils.ALL_MONTHS) { %>
							<option value="<%=month%>" <%=month.equals(registerForm.getMonth()) ? "selected" : ""%>><%=month%></option>
						<% } %>
					</html:select>
					<html:select name="UpdatePersonFormMobile" property="day" styleClass="day-month">
						<option value=""></option>
						<% for (String day : DateUtils.ALL_DAYS) { %>
							<option value="<%=day%>" <%=day.equals(registerForm.getDay()) ? "selected" : ""%>><%=day%></option>
						<% } %>
					</html:select>
				</div>
				<div class="errorInForm"></div>
				<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "RegisterForm.birthdate.err")%>
			</fieldset>
			<fieldset>
				<label>Código de área</label>
				<html:text name="UpdatePersonFormMobile" property="phoneAreaCode" />
				<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneAreaCode)) ? "*" : ""%><div id="err.profile.phone.areaCode"></div>
				<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "profile.phone.areaCode.err")%>
			</fieldset>
			<fieldset>
				<label>Teléfono celular</label>
				<html:text name="UpdatePersonFormMobile" property="phoneNumber" />
				<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneNumber)) ? "*" : ""%><div id="err.profile.phone.number"></div>
				<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "profile.phone.number.err")%>
			</fieldset>
			<fieldset class="countryFieldset">
				<label>País</label>
				<span class="countrySelected"><%=registerForm.getCountrySelected()%></span>
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
					<label>Código postal</label>
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
			<logic:iterate id="optIn" name="UpdatePersonForm" property="optIns">
				<h4>Cláusula de contacto<!--  bean:write name="optIn" property="brandFamilyName"/ --></h4>
				<fieldset>
					<html:checkbox name="optIn" property="accepted" indexed="true" />
					<label>Acepto recibir contactos de LoJack y sus comañías asociadas<!-- bean:write name="optIn" property="channelName"/ --></label>
				</fieldset>
			</logic:iterate>
		</div>
		<fieldset>
			<input type="submit" id="submitregister" value="Guardar" class="buttonSend">
		</fieldset>
	</html:form>
</div>
<%@ include file="../includes/version.jspf" %></body>
</html>