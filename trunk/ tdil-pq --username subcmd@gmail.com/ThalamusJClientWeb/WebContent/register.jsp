<%@page import="com.tdil.thalamusweb.utils.PhoneNumberType"%>
<%@page import="com.tdil.thalamusweb.utils.AddressType"%>
<%@page import="com.tdil.thalamusweb.struts.forms.beans.ComboBean"%>
<%@page import="com.tdil.thalamusweb.struts.forms.RegisterForm"%>
<%@page import="com.tdil.thalamusweb.utils.ThalamusJClientWebErrorFormatter"%>
<%@ page info="register"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<html>
<head>
<%@ include file="includes/userLogged.jspf" %>
<%@ include file="includes/head.jsp"%>
<%@ include file="includes/errorJS.jsp"%>
<script>
$(document).ready(
	function(){

		$("input[name=birthDate]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
			changeYear: true, yearRange: "1900:2012"});
	}

	
);
</script>
</head>

<body>
<div id="header"></div>
<div id="container">
	<div id="loginBase">
		<h1>Registro</h1>
		<html:form method="POST" action="/register">
		<% RegisterForm registerForm = (RegisterForm)session.getAttribute("RegisterForm"); %>
		<div class="renglon width20">
			<div class="label width20"><span class="errorText"><%=ThalamusJClientWebErrorFormatter.getErrorFrom(request, "general")%></span></div>
		</div>
		<div class="myLabel width110">
			Nombre:<html:text name="RegisterForm" property="firstName" styleClass="normalField width200"/>
		</div>
		<div class="myLabel width110" >
			Apellido:<html:text name="RegisterForm" property="lastName" styleClass="normalField width200"/>
		</div>
		<div class="myLabel width110">
			Email:<html:text name="RegisterForm" property="email" styleClass="normalField width200"/>
		</div>
		<div class="myLabel width110">
			Fecha nac:<html:text name="RegisterForm" property="birthDate" styleClass="normalField width200"/>
		</div>
		<div class="myLabel width110">
			Calle:<html:text name="RegisterForm" property="street" styleClass="normalField width200"/>
		</div>
		<div class="myLabel width110">
			Ciudad:<html:text name="RegisterForm" property="city" styleClass="normalField width200"/>
		</div>
		<div class="myLabel width110" id="Pais">
			Pais:<html:select name="RegisterForm" property="countryId" styleClass="normalField width150" onchange="this.form.action='./refreshStatesRegistro.do';this.form.submit()">
				<option value="">Seleccione</option>
				<% for (ComboBean country : registerForm.getCountries()) { %>
					<option <%=	country.getValue() == registerForm.getCountryId() ? "selected" : ""%> value="<%=country.getValue()%>">
						<%=country.getLabel()%></option>
				<% } %>
			</html:select>
		</div>
		<div class="myLabel width110" id="Pais">
			Estados:<html:select name="RegisterForm" property="stateId" styleClass="normalField width150">
				<option value="">Seleccione</option>
				<% for (ComboBean state : registerForm.getStates()) { %>
					<option <%=	state.getValue() == registerForm.getStateId() ? "selected" : ""%> value="<%=state.getValue()%>">
						<%=state.getLabel()%></option>
				<% } %>
			</html:select>
		</div>
		
		<div class="myLabel width110" id="Pais">
			<html:checkbox name="RegisterForm" property="activeConsumer" />Consumidor activo
		</div>
		<div class="myLabel width110" id="Pais">
			Marca regular:<html:select name="RegisterForm" property="preferedBrand" styleClass="normalField width150">
				<option value="">Seleccione</option>
				<% for (ComboBean brand : registerForm.getBrands()) { %>
					<option <%=	brand.getValue() == registerForm.getPreferedBrand() ? "selected" : ""%> value="<%=brand.getValue()%>">
						<%=brand.getLabel()%></option>
				<% } %>
			</html:select>
		</div>
		<div class="myLabel width110" id="Pais">
			Marca alternativa:<html:select name="RegisterForm" property="alternativeBrandId" styleClass="normalField width150">
				<option value="">Seleccione</option>
				<% for (ComboBean brand : registerForm.getBrands()) { %>
					<option <%=	brand.getValue() == registerForm.getAlternativeBrandId() ? "selected" : ""%> value="<%=brand.getValue()%>">
						<%=brand.getLabel()%></option>
				<% } %>
			</html:select>
		</div>
		<div class="myLabel width110">
			consumptionFrequency:<html:text name="RegisterForm" property="consumptionFrequency" styleClass="normalField width200"/>
		</div>
		
		<div class="myLabel width110">
			postalCode:<html:text name="RegisterForm" property="postalCode" styleClass="normalField width200"/>
		</div>
		<div class="myLabel width110" id="Pais">
			Tipo dir:<html:select name="RegisterForm" property="addressType" styleClass="normalField width150">
				<option value="">Seleccione</option>
				<% for (String type : AddressType.types) { %>
					<option <%=	type.equals(registerForm.getAddressType()) ? "selected" : ""%> value="<%=type%>">
						<%=type%></option>
				<% } %>
			</html:select>
		</div>
		<div class="myLabel width110">
			phoneNumber:<html:text name="RegisterForm" property="phoneNumber" styleClass="normalField width200"/>
		</div>
		<div class="myLabel width110" id="Pais">
			Tipo phone:<html:select name="RegisterForm" property="phoneNumberType" styleClass="normalField width150">
				<option value="">Seleccione</option>
				<% for (String type : PhoneNumberType.types) { %>
					<option <%=	type.equals(registerForm.getPhoneNumberType()) ? "selected" : ""%> value="<%=type%>">
						<%=type%></option>
				<% } %>
			</html:select>
		</div>
		<div class="myLabel width110">
			password:<html:password name="RegisterForm" property="password" styleClass="normalField width200"/>
		</div>
		
		<html:submit property="operation">Registro</html:submit>
		</html:form>
	</div>
</div>

Registrate
<% if (!logged) { %>
	<a href="login.jsp">Ingresa</a>
<% } else { %>
	Hola <%= websiteUser.getName() %>
<% } %>
<a href="catalogo.jsp">Catalogo</a>
<a href="legal.jsp">Legal</a>

</body>
</html>