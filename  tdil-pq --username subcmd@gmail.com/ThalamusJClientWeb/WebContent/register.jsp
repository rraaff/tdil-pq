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
<% RegisterForm registerForm = (RegisterForm)session.getAttribute("RegisterForm"); %>
<script>
$(document).ready(
	function(){

		$("form[name='RegisterForm']").validate({
			errorPlacement: function(error, element) {
				error.appendTo( element.parent("div"));
			},
			rules: { 'firstName': {required: true},
					'lastName': {required: true},
					'birthDate': {required: true},
					'email': {required: true, email: true},
					'countryId': {required: true},
					'stateId': {required: true},
					'password': {required: true}
			},
			messages: {
				'firstName': {required: "<img id='firstnameerror' src='images/unchecked.gif' hovertext='Ingrese el nombre.' />"}, 
				'lastName': {required: "<img id='lastnameerror' src='images/unchecked.gif' hovertext='Ingrese el apellido.' />"}, 
				'birthDate': {required: "<img id='birthdateerror' src='images/unchecked.gif' hovertext='Ingrese la fecha de nacimiento.' />"}, 
				'email': {required: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese el email.' />",
						email: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese un email valido.' />"},
				'countryId': {required: "<img id='phoneAreaCodeerrorreq' src='images/unchecked.gif' hovertext='Seleccione un pais.' />"} ,
				'stateId': {required: "<img id='phoneNumbererrorreq' src='images/unchecked.gif' hovertext='Seleccione una provincia.' />"},
				'password': {required: "<img id='passworderror' src='images/unchecked.gif' hovertext='Ingrese el password.' />"}
			}
		});
		
		$("input[name=activeConsumer]").click(function() {
			 if ($(this).attr("checked")) {
			      $("select[name=preferedBrand]").removeAttr ( "disabled" );
			      $("select[name=alternativeBrandId]").removeAttr ( "disabled" );
			 } else {
			      $("select[name=preferedBrand]").attr ( "disabled" , true );
			      $("select[name=alternativeBrandId]").attr ( "disabled" , true );
			 }
		});
		<% if (!registerForm.isActiveConsumer()) { %>
		  $("select[name=preferedBrand]").attr ( "disabled" , true );
	      $("select[name=alternativeBrandId]").attr ( "disabled" , true );
		<% } %>
		$("input[name=birthDate]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
			changeYear: true, yearRange: "1900:2012"});
	}

	
);
</script>
<style type="text/css">
<!--
#header {
	width:990px;
	height:128px;
	margin:0 auto;
}
#central {
	width:990px;
	height:360px;
	margin:0 auto;
}
#left {
	width:275px;
	height:360px;
	float:left;
}
#form {
	background-image: url(images/skin_nrg/register_03.png);
	background-repeat: no-repeat;
	width:440px;
	height:360px;
	float:left;
	overflow: auto;
	font-family: 'Gill Sans MT', Genova, Arial, Helvetica, sans-serif;
	font-size: 18px;
	color: #2d0703;	
	
}
#right {
	width:275px;
	height:360px;
	float:left;
}
#footer {
	width:990px;
	height:92px;
	margin:0 auto;
}
-->
</style>
</head>

<body>
<div id="header"><img src="images/skin_nrg/register_01.png" width="990" height="128"></div>
<div id="central">
	<div id="left"><img src="images/skin_nrg/register_02.png" width="275" height="360"></div>
	<div id="form">
		<html:form method="POST" action="/register">
		<div class="renglon">
			<div class="label width420" align="center" style="font-size:10px;"><%=ThalamusJClientWebErrorFormatter.getErrorFrom(request, "general")%></div>
		</div>
		<div class="renglon">
			<div class="label width420" align="center"><span class="commment">Fields marked with a * are required</span></div>
		</div>
		<div class="renglon">
			<div class="label width150">* Name</div>
			<div class="label width250"><html:text name="RegisterForm" property="firstName" styleClass="normalField width250"/></div>
		</div>
		<div class="renglon">
			<div class="label width150">* Surname</div>
			<div class="label width250"><html:text name="RegisterForm" property="lastName" styleClass="normalField width250"/></div>
		</div>
		<div class="renglon">
			<div class="label width150">* E-Mail</div>
			<div class="label width250"><html:text name="RegisterForm" property="email" styleClass="normalField width250"/></div>
		</div>
		<div class="renglon">
			<div class="label width150">* Date of birth</div>
			<div class="label width250"><html:text name="RegisterForm" property="birthDate" styleClass="normalField width250"/></div>
		</div>
		<div class="renglon">
			<div class="label width150">* Country</div>
			<div class="label width250">
				<html:select name="RegisterForm" property="countryId" styleClass="normalField width250" onchange="this.form.action='./refreshStatesRegistro.do';this.form.submit()">
					<option value="">Select one option</option>
					<% for (ComboBean country : registerForm.getCountries()) { %>
						<option <%=	country.getValue() == registerForm.getCountryId() ? "selected" : ""%> value="<%=country.getValue()%>">
							<%=country.getLabel()%></option>
					<% } %>
				</html:select>
			</div>
		</div>
		<div class="renglon">
			<div class="label width150">* State</div>
			<div class="label width250">
				<html:select name="RegisterForm" property="stateId" styleClass="normalField width250">
					<option value="">Select one option</option>
					<% for (ComboBean state : registerForm.getStates()) { %>
						<option <%=	state.getValue() == registerForm.getStateId() ? "selected" : ""%> value="<%=state.getValue()%>">
							<%=state.getLabel()%></option>
					<% } %>
				</html:select>
			</div>
		</div>
		<div class="renglon">
			<div class="label width150">City</div>
			<div class="label width250"><html:text name="RegisterForm" property="city" styleClass="normalField width250"/></div>
		</div>
		<div class="renglon">
			<div class="label width150">Street</div>
			<div class="label width250"><html:text name="RegisterForm" property="street" styleClass="normalField width250"/></div>
		</div>
		<div class="renglon">
			<div class="label width150">Postal Code</div>
			<div class="label width250"><html:text name="RegisterForm" property="postalCode" styleClass="normalField width250"/></div>
		</div>
		<div class="renglon">
			<div class="label width150">Address type</div>
			<div class="label width250">
				<html:select name="RegisterForm" property="addressType" styleClass="normalField width250">
					<option value="">Select one option</option>
					<% for (String type : AddressType.types) { %>
						<option <%=	type.equals(registerForm.getAddressType()) ? "selected" : ""%> value="<%=type%>">
							<%=type%></option>
					<% } %>
				</html:select>
			</div>
		</div>
		<div class="renglon">
			<div class="label width150">Phone number</div>
			<div class="label width250"><html:text name="RegisterForm" property="phoneNumber" styleClass="normalField width250"/></div>
		</div>
		<div class="renglon">
			<div class="label width150">Phone type</div>
			<div class="label width250">
				<html:select name="RegisterForm" property="phoneNumberType" styleClass="normalField width250">
					<option value="">Select one option</option>
					<% for (String type : PhoneNumberType.types) { %>
						<option <%=	type.equals(registerForm.getPhoneNumberType()) ? "selected" : ""%> value="<%=type%>">
							<%=type%></option>
					<% } %>
				</html:select>
			</div>
		</div>
		<div class="renglon">
			<div class="label width380">Do you consume energy drinks?</div>
			<div class="label width20" style="padding-top:4px;"><html:checkbox name="RegisterForm" property="activeConsumer"/></div>
		</div>
		<div class="renglon">
			<div class="label width150">Regular brand</div>
			<div class="label width250">
				<html:select name="RegisterForm" property="preferedBrand" styleClass="normalField width250">
					<option value="">Select one option</option>
					<% for (ComboBean brand : registerForm.getBrands()) { %>
						<option <%=	brand.getValue() == registerForm.getPreferedBrand() ? "selected" : ""%> value="<%=brand.getValue()%>">
							<%=brand.getLabel()%></option>
					<% } %>
				</html:select>
			</div>
		</div>
		<div class="renglon">
			<div class="label width150">Alternative brand</div>
			<div class="label width250">
				<html:select name="RegisterForm" property="alternativeBrandId" styleClass="normalField width250">
					<option value="">Select one option</option>
					<% for (ComboBean brand : registerForm.getBrands()) { %>
						<option <%=	brand.getValue() == registerForm.getAlternativeBrandId() ? "selected" : ""%> value="<%=brand.getValue()%>">
							<%=brand.getLabel()%></option>
					<% } %>
				</html:select>
			</div>
		</div>
		<div class="renglon">
			<div class="label width350">How many times a week do you consume an energy drink?</div>
			<div class="label width50"><html:text name="RegisterForm" property="consumptionFrequency" styleClass="normalField width50"/></div>
		</div>
		<div class="renglon">
			<div class="label width150">* Password</div>
			<div class="label width250"><html:password name="RegisterForm" property="password" styleClass="normalField width250"/></div>
		</div>
		<div class="renglon">
			<div class="label width200" style="padding-top:20px;"><a href="index.jsp" title="Cancel registration and back home">Back home</a></div>
			<div class="label width40" style="padding-top:20px;">or</div>
			<div class="label width160 "><input type="image" src="images/skin_nrg/btn_register_at_form.png"/></div>
		</div>
		</html:form>
	</div>
	<div id="left"><img src="images/skin_nrg/register_04.png" width="275" height="360"></div>
</div>
<div id="footer"><img src="images/skin_nrg/register_05.jpg" width="990" height="92"></div>
</body>
</html>