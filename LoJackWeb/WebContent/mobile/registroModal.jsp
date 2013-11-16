<%@page import="com.tdil.utils.DateUtils"%>
<%@ page info="index"%><%--
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
--%><% if (websiteUser != null && websiteUser.isLogged()) { %>
	<jsp:forward page="home.jsp"></jsp:forward>
<% 	return;
	} %><%--
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
<%@ include file="includes/head.jsp"%>
</head>
<body>
<div id="internalHeader">
	<div id="logo"><img src="../images/skin_lj_rl/logos/lo-jack_mainLogo.png"></div>
	<ul>
		<li><a href="./loginModal.jsp" title="Ingresar ahora">ingresar</a></li>
		<li><a href="legal.jsp">Legales</a></li>
		<li><a href="index.jsp" class="back" title="Volver al inicio">< volver</a></li>
	</ul>
</div>
<div id="registrationContent">
	<h1>Registrate</h1>
	<p>Los campos marcados con * son requeridos para la registración</p>
	<div class="errorInForm">
		<% if (com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "credential.principal.err").contains("registrado en nuestra base")) { %>
			<a href="./recuperarClaveModal.jsp"><%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "credential.principal.err")%></a>
		<% } %>
	</div>
	<html:form method="POST" action="/mobile/registerMobile">
		<% RegisterForm registerForm = (RegisterForm)session.getAttribute("RegisterFormMobile");
		registerForm.setMobile(true);
		registerForm.searchReferenceData();
		%>
		<div class="scrollable">
			<fieldset>
				<label>* Tipo de doc</label>
				<html:select name="RegisterFormMobile" property="documentType" >
					<option value="">Seleccione...</option>
					<% for (DocumentTypeBean codBean : LoginForm.getDocumentTypes()) { %>
						<option value="<%=codBean.getId()%>" <%=registerForm.getDocumentType() == codBean.getId() ? "selected" : ""%>>
							<%=codBean.getName()%></option>
					<% } %>
				</html:select>
				<div class="errorInForm"><%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "RegisterForm.documenttype.err")%></div>
			</fieldset>
				
			<fieldset>
				<label>* Numero</label>
				<html:text name="RegisterFormMobile" property="document" />
				<div class="errorInForm">
					<% if (!com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "credential.principal.err").contains("registrado en nuestra base")) { %>
						<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "credential.principal.err")%>
					<% } %>
				</div>
			</fieldset>
			<fieldset>
				<label>* Nombre</label>
				<html:text name="RegisterFormMobile" property="firstName" />
					<%=(registerForm.isRequired(PersonFieldNames.firstName)) ? "" : ""%>
					<div class="errorInForm"><%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "RegisterForm.firstname.err")%>
					<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "profile.firstname.err")%></div>
			</fieldset>
			<fieldset>
				<label>* Apellido</label>
				<html:text name="RegisterFormMobile" property="lastName" />
				<%=(registerForm.isRequired(PersonFieldNames.lastName)) ? "" : ""%>
				<div class="errorInForm"><%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "RegisterForm.lastname.err")%>
				<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "profile.lastname.err")%></div>
			</fieldset>
			<div class="sexWrapper">
				<fieldset class="sexFieldset">
					<label class="sexLabel">* Sexo:</label>
				</fieldset>
				<fieldset class="sexOptionsFieldset">
					<html:radio property="gender" value="Male" />
					<span>Masculino&nbsp;&nbsp;&nbsp;</span>
					<html:radio property="gender" value="Female" />
					<span>Femenino</span>
					<div class="errorInForm"><%=(registerForm.isRequired(PersonFieldNames.gender)) ? "" : ""%>
					<%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "RegisterForm.gender.err")%></div>
				</fieldset>
			</div>
			<fieldset>
				<label>* E-mail</label>
				<html:text name="RegisterFormMobile" property="email"/>
				<%=(registerForm.isRequired(PersonFieldNames.email)) ? "" : ""%>
				<div class="errorInForm"><%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "RegisterForm.email.err")%></div>
			</fieldset>
			<fieldset>
				<label>* Clave</label>
				<html:password name="RegisterFormMobile" property="password" />
				<%=(registerForm.isRequired(PersonFieldNames.password)) ? "" : ""%>
				<div class="errorInForm"><%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "RegisterForm.password.err")%></div>
			</fieldset>
			<fieldset>
				<label>* Repetir clave</label>
				<html:password name="RegisterFormMobile" property="retypePassword" />
			</fieldset>
			<fieldset>
				<label>* Fecha de nacimiento</label>
				<%=(registerForm.isRequired(PersonFieldNames.birthDate)) ? "" : ""%>
				<div class="dateHelper">
					<html:select name="RegisterFormMobile" property="year" styleClass="year">
						<option value=""></option>
						<% for (String year : registerForm.getYears()) { %>
							<option value="<%=year%>" <%=year.equals(registerForm.getYear()) ? "selected" : ""%>><%=year%></option>
						<% } %>
					</html:select>
					<html:select name="RegisterFormMobile" property="month" styleClass="day-month">
						<option value=""></option>
						<% for (String month : DateUtils.ALL_MONTHS) { %>
							<option value="<%=month%>" <%=month.equals(registerForm.getMonth()) ? "selected" : ""%>><%=month%></option>
						<% } %>
					</html:select>
					<html:select name="RegisterFormMobile" property="day" styleClass="day-month">
						<option value=""></option>
						<% for (String day : DateUtils.ALL_DAYS) { %>
							<option value="<%=day%>" <%=day.equals(registerForm.getDay()) ? "selected" : ""%>><%=day%></option>
						<% } %>
					</html:select>
				</div>
				<div class="errorInForm"><%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "RegisterForm.birthdate.err")%></div>
			</fieldset>
			<fieldset>
				<label>Código de área</label>
				<html:text name="RegisterFormMobile" property="phoneAreaCode" />
				<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneAreaCode)) ? "" : ""%>
				<div class="errorInForm"><%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "profile.phone.areaCode.err")%></div>
				<div class="myRow errorField" style="display: none;" id="p.profile.phone.areaCode">
					<div id="err.profile.phone.areaCode"></div>
				</div>
			</fieldset>
			<fieldset>
				<label>Teléfono celular</label>
				<html:text name="RegisterFormMobile" property="phoneNumber" />
				<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneNumber)) ? "" : ""%>
				<div class="errorInForm"><%=com.tdil.lojack.web.LoJackErrorFormatter.getErrorFrom(request, "profile.phone.number.err")%></div>
				<div class="errorField" style="display: none;" id="p.profile.phone.number">
					<div id="err.profile.phone.number"></div>
				</div>
			</fieldset>
			<fieldset class="countryFieldset">
				<label>País</label>
				<span class="countrySelected"><%=registerForm.getCountrySelected()%></span>
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
				<div class="errorInForm"><%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.stateId)) ? "" : ""%></div>
				<div class="errorField" style="display: none;" id="p.profile.address.stateId">
					<div id="err.profile.address.stateId"></div>
				</div>
			</fieldset>
			<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.street1)) { %>
				<fieldset>
					<label>Calle 1</label>
					<html:text name="RegisterFormMobile" property="street1" />
					<div class="errorInForm"><%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.street1)) ? "" : ""%></div>
					<div class="errorField" style="display: none;" id="p.profile.address.street1">
						<div id="err.profile.address.street1"></div>
					</div>
				</fieldset>
			<% } %>
			<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.street2)) { %>
				<fieldset>
					<label>Calle 2</label>
					<html:text name="RegisterFormMobile" property="street2" />
					<div class="errorInForm"><%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.street2)) ? "" : ""%></div>
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
					<div class="errorInForm"><%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.addressType)) ? "" : ""%></div>
					<div class="errorField" style="display: none;" id="p.profile.address.type">
						<div id="err.profile.address.type"></div>
					</div>
				</fieldset>
			<% } %>
			<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.postalCode)) { %>
				<fieldset>
					<label>Código postal</label>
					<html:text name="RegisterFormMobile" property="postalCode" />
					<div class="errorInForm"><%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.postalCode)) ? "" : ""%></div>
					<div class="errorField" style="display: none;" id="p.profile.address.postalCode">
						<div id="err.profile.address.postalCode"></div>
					</div>
				</fieldset>
			<% } %>
			<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.city)) { %>
				<fieldset>
					<label>Ciudad</label>
					<html:text name="RegisterFormMobile" property="city" />
					<div class="errorInForm"><%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.city)) ? "" : ""%></div>
					<div class="errorField" style="display: none;" id="p.profile.address.city">
						<div id="err.profile.address.city"></div>
					</div>
				</fieldset>
			<% } %>
			<h4>Cláusula de contacto</h4>
			<fieldset class="optinStyles">
				<html:checkbox name="RegisterFormMobile" property="optInAccepted"/>
				<label>Acepto recibir contactos de LoJack y sus compañías asociadas</label>
			</fieldset>
		</div>
		<fieldset>
			<input type="submit" id="submitregister" value="Registrarme" class="buttonSend">
		</fieldset>
	</html:form>
</div>
<%@ include file="../includes/version.jspf" %></body>
</html>