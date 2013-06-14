<%@page import="com.tdil.thalamus.client.facade.json.beans.ReferenceOption"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.FieldType"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.DynamicField"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.StateBean"%>
<%@page import="com.tdil.chivas.co.struts.forms.beans.OptIn"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.BrandBean"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.CountryBean"%>
<%@page import="com.tdil.thalamus.client.facade.json.fields.PersonFieldNames"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.PersonFields"%>
<%@page import="com.tdil.chivas.co.struts.forms.RegisterForm"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><!--
--><%@ page info="index"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="includes/checkThalamusUp.jspf" %><!--
--><%@ include file="includes/userLogged.jspf" %><!--
--><%@ include file="includes/mustBeLogged.jspf" %>
<html>
<head>
<%@ include file="includes/head.jsp" %>
<script>
$(document).ready(
	function(){

		$("input[name=consumptionFrequency]").jStepper({minValue:0});
		
		$('select[name=countryId]').change(
			function() {
				var selectToLoad = $('select[name=stateId]');
				selectToLoad.empty();
            	$('<option>Loading</option>').appendTo(selectToLoad);
				var countrySelected = Number($(this).attr('value'));
				if (countrySelected > 0) {
					$.ajax({
			            type: "GET",
			            cache: false,
			            url: "./searchStates.do",
			            data: {countryId: countrySelected },
			            contentType: "application/json; charset=utf-8",
			            success: function(msg) {
			            	var select = $('select[name=stateId]');
			            	select.empty();
			            	$('<option>Select one option</option>').appendTo(select);
			            	$.each(msg, function(index, item) {
				                $('<option value="'+item.id+'">'+item.name+'</option>').appendTo(select);
			                });
			            },
			            error: function() {
			                alert("error consultando los estados");
			            }
			        });
				} else {
					var select = $('select[name=stateId]');
	            	select.empty();
	            	$('<option>Select one option</option>').appendTo(select);
				}
			}
		);
		$("input[name=activeConsumer]").click(function() {
			 if ($(this).attr("checked")) {
			      $("select[name=preferedBrandId]").removeAttr ( "disabled" );
			      $("select[name=alternativeBrandId]").removeAttr ( "disabled" );
			      $("input[name=consumptionFrequency]").removeAttr ( "disabled" );
			 } else {
			      $("select[name=preferedBrandId]").attr ( "disabled" , true );
			      $("select[name=alternativeBrandId]").attr ( "disabled" , true );
			      $("input[name=consumptionFrequency]").attr ( "disabled" , true );
			 }
		});
		$("input[name=birthDate]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
			changeYear: true, minDate: "-100Y", maxDate: "+0D"});

		$('.date').each(function() {
		    $(this).datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
				changeYear: true, minDate: "-100Y", maxDate: "+0D"});
		});
		
		$("form[name='UpdatePersonForm']").validate({
			errorPlacement: function(error, element) {
				error.appendTo( element.parent("div"));
			},
			rules: { 			},
			messages: {			},
			submitHandler: function() {
				clearErrors();
	            $("form[name='UpdatePersonForm']").ajaxSubmit({
	    			type: "POST",
	    			url: "./updatePerson.do",
	    			dataType: "json",
	    			success: postRegister
	    			});
	        }
		});
		
		$( "#closeregisterLayer" ).click(function() {
			$( "#registerLayer" ).fadeOut();
		});
		$( "#goback" ).click(function() {
			parent.history.back();
	        return false;
		});

		<%@ include file="includes/closeLegalesLayer.jsp" %>
	}
);

function register() {
	centerLayer($(window), $( "#registerLayer" ));
}

<%@ include file="includes/openLegalesLayer.jsp" %>

function clearErrors() {
	$("div[id^='err.']").each(function(index, valor) {
		$(valor).prop('innerHTML','');
	});
}

function centerLayer(objWin, objLayer) {
	var top = (objWin.height() / 2) - (objLayer.height() / 2);
	var left = (objWin.width() / 2) - (objLayer.width() / 2);
	objLayer.css({
		position: 'absolute',
		top: top + 'px',
		left: left + 'px'
	}).fadeIn(500);
}

function postRegister(data) {
	if (data.result == 'OK') {
		window.location.replace('./home.jsp');
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
</head>
<body onLoad="javascript:register();">
<div id="structure">
	<div id="content">
		<!-- top menu -->
		<%
			if (logged && websiteUser.appliesToActivity("EMileage")) { com.tdil.chivas.co.utils.ThalamusWebUtils.loginToActivity(websiteUser, "EMileage");
		%>
			<%@ include file="includes/EMileageMenu.jsp" %>
		<%
			} else {
		%>
			<%@ include file="includes/regularMenu.jsp" %>
		<%
			}
		%>
	</div>
	<div id="footer">
		<%
			if (logged && websiteUser.appliesToActivity("EMileage")) { com.tdil.chivas.co.utils.ThalamusWebUtils.loginToActivity(websiteUser, "EMileage");
		%>
			<div id="insertCodeButton">
				<ul>
					<li class="icb_logoAtFooter"><img src="images/skin_nrg2/logos/nrg_on_footer.png"></li>
					<li class="icb_buttonCode"><a href="mp/cupon.jsp" title="Insert your code now!"></a></li>
				</ul>
			</div>
		<% } else { %>
			<div class="signInButtons"><img src="images/skin_nrg2/logos/nrg_on_footer.png"></div>
		<% } %>
		<div class="theThalamusLogo"><a href="http://www.thalamuscorp.com/" title="Thalamus driven" target="_blank"><img src="images/skin_nrg2/logos/thalamus_diven.png" alt="Thalamus driven"></a></div>
	</div>
</div>
<!-- Edit profile layer -->
<div id="registerLayer" class="layerOnTop" style="display: none; z-index: 500;">
	<div class="registerLayerStyles editProfileLayer">
		<div class="registerLayerContent">
			<div class="myRow">
				<div class="myLabel width100per" style="text-align:center;">Field marqued with * are required for users</div>
			</div>
			<html:form method="POST" action="/updatePerson">
				<% RegisterForm registerForm = (RegisterForm)session.getAttribute("UpdatePersonForm");
				%>
				<% if (registerForm.isInUse(PersonFieldNames.firstName)) { %>
					<div class="myRow">
						<div class="myLabel width120">* Name</div>
						<div class="myLabel width270"><html:text name="UpdatePersonForm" property="firstName" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.firstName)) ? "" : ""%><div id="err.profile.firstName"></div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.lastName)) { %>
					<div class="myRow">
						<div class="myLabel width120">* Surname</div>
						<div class="myLabel width270"><html:text name="UpdatePersonForm" property="lastName" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.lastName)) ? "" : ""%><div id="err.profile.lastName"></div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.gender)) { %>
					<div class="myRow">
						<div class="myLabel width120">* Gender</div>
						<div class="myLabel width30"><html:radio property="gender" value="Male" /></div>
						<div class="myLabel width100">Male</div>
						<div class="myLabel width30"><html:radio property="gender" value="Female" /></div>
						<div class="myLabel width110">Femenino</div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.gender)) ? "" : ""%><div id="err.profile.gender"></div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.email)) { %>
					<div class="myRow">
					<% if (registerForm.isPrincipal(PersonFieldNames.email)) { %>
							<div class="myLabel width120">* E-mail / User</div>
							<div class="myLabel width270"><strong><bean:write name="UpdatePersonForm" property="email"/></strong></div>
						</div>
					<% } else { %>
							<div class="myLabel width120">* E-mail</div>
							<div class="myLabel width270"><html:text name="UpdatePersonForm" property="email" styleClass="normalField width250"/></div>
						</div>
						<%=(registerForm.isRequired(PersonFieldNames.email)) ? "*" : ""%><div id="err.profile.email"></div>
					<% } %>
				<% } %>
				<div class="myRow">
					<div class="myLabel width120">* Password</div>
					<div class="myLabel width270"><html:password name="UpdatePersonForm" property="password" styleClass="normalField width250"/></div>
				</div>
				<%=(registerForm.isRequired(PersonFieldNames.password)) ? "" : ""%><div id="err.credential.password"></div>
				<% if (registerForm.isInUse(PersonFieldNames.birthDate)) { %>
					<div class="myRow">
						<div class="myLabel width120">* Birth date</div>
						<div class="myLabel width270"><html:text name="UpdatePersonForm" property="birthDate" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.birthDate)) ? "" : ""%>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.phone, PersonFieldNames.phoneIntCode)) { %>
					<div class="myRow">
						<div class="myLabel width120">Country code</div>
						<div class="myLabel width100"><html:text name="UpdatePersonForm" property="phoneIntCode" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneIntCode)) ? "*" : ""%><div id="err.profile.phone.intCode"></div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.phone, PersonFieldNames.phoneAreaCode)) { %>
					<div class="myRow">
						<div class="myLabel width120">Area code</div>
						<div class="myLabel width270"><html:text name="UpdatePersonForm" property="phoneAreaCode" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneAreaCode)) ? "*" : ""%><div id="err.profile.phone.areaCode"></div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.phone, PersonFieldNames.phoneNumber)) { %>
					<div class="myRow">
						<div class="myLabel width120">Local number</div>
						<div class="myLabel width270"><html:text name="UpdatePersonForm" property="phoneNumber" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneNumber)) ? "*" : ""%><div id="err.profile.phone.number"></div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.phone, PersonFieldNames.phoneType)) { %>
					<div class="myRow">
						<div class="myLabel width120">Type</div>
						<div class="myLabel width270">
							<html:select name="UpdatePersonForm" property="phoneType" styleClass="normalField width250"><br>
								<option value="">Select an option</option>
									<% for (String type : registerForm.getPhoneTypes()) { %>
										<option <%=type.equals(registerForm.getPhoneType()) ? "selected" : ""%> value="<%=type%>">
											<%=type%></option>
									<% } %>
							</html:select>
						</div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneType)) ? "*" : ""%><div id="err.profile.phone.type"></div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.countryId)) { %>
					<div class="myRow">
						<div class="myLabel width120">Country</div>
						<div class="myLabel width270">
							<html:select name="UpdatePersonForm" property="countryId" styleClass="normalField width250">
								<option value="">Select an option</option>
									<% for (CountryBean country : registerForm.getCountries()) { %>
										<option <%=	country.getId() == registerForm.getCountryId() ? "selected" : ""%> value="<%=country.getId()%>">
											<%=country.getName()%></option>
									<% } %>
							</html:select>
						</div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.countryId)) ? "*" : ""%><div id="err.profile.address.countryId"></div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.stateId)) { %>
					<div class="myRow">
						<div class="myLabel width120">State/Province</div>
						<div class="myLabel width270">
							<html:select name="UpdatePersonForm" property="stateId" styleClass="normalField width250">
								<option value="">Select an option</option>
								<% for (StateBean state : registerForm.getStates()) { %>
									<option <%=	state.getId() == registerForm.getStateId() ? "selected" : ""%> value="<%=state.getId()%>">
									<%=state.getName()%></option>
								<% } %>
							</html:select>
						</div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.stateId)) ? "*" : ""%><div id="err.profile.address.stateId"></div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.street1)) { %>
					<div class="myRow">
						<div class="myLabel width120">Street 1</div>
						<div class="myLabel width270"><html:text name="UpdatePersonForm" property="street1" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.street1)) ? "*" : ""%><div id="err.profile.address.street1"></div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.street2)) { %>
					<div class="myRow">
						<div class="myLabel width120">Street 2</div>
						<div class="myLabel width270"><html:text name="UpdatePersonForm" property="street2" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.street2)) ? "*" : ""%><div id="err.profile.address.street2"></div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.addressType)) { %>
					<div class="myRow">
						<div class="myLabel width120">Type</div>
						<div class="myLabel width270">
							<html:select name="UpdatePersonForm" property="addressType" styleClass="normalField width250">
								<option value="">Select one option</option>
									<% for (String type : registerForm.getAddressTypes()) { %>
										<option <%=type.equals(registerForm.getAddressType()) ? "selected" : ""%> value="<%=type%>">
										<%=type%></option>
									<% } %>
							</html:select>
						</div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.addressType)) ? "*" : ""%><div id="err.profile.address.type"></div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.postalCode)) { %>
					<div class="myRow">
						<div class="myLabel width120">Postal code</div>
						<div class="myLabel width270"><html:text name="UpdatePersonForm" property="postalCode" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.postalCode)) ? "*" : ""%><div id="err.profile.address.postalCode"></div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.city)) { %>
					<div class="myRow">
						<div class="myLabel width120">City</div>
						<div class="myLabel width270"><html:text name="UpdatePersonForm" property="city" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.city)) ? "*" : ""%><div id="err.profile.address.city"></div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.activeConsumer)) { %>
					<div class="myRow">
						<div class="myLabel width30"><html:checkbox name="UpdatePersonForm" property="activeConsumer"/></div>
						<div class="myLabel width300">Consume NRG products?</div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.activeConsumer)) ? "*" : ""%><div id="err.consumer.activeConsumer"></div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.preferedBrand)) { %>
					<div class="myRow">
						<div class="myLabel width120">Prefered brand?</div>
						<div class="myLabel width270">
							<html:select name="UpdatePersonForm" property="preferedBrandId" styleClass="normalField width250">
								<option value="">Select one option</option>
								<% for (BrandBean brand : registerForm.getBrands()) { %>
									<option <%=	brand.getId() == registerForm.getPreferedBrandId() ? "selected" : ""%> value="<%=brand.getId()%>">
										<%=brand.getName()%></option>
								<% } %>
							</html:select>
						</div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.preferedBrand)) ? "*" : ""%><div id="err.consumer.preferedBrandId"></div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.alternativeBrandId)) { %>
					<div class="myRow">
						<div class="myLabel width120">Alternate brand?</div>
						<div class="myLabel width270">
							<html:select name="UpdatePersonForm" property="alternativeBrandId" styleClass="normalField width250">
								<option value="">Select one option</option>
								<% for (BrandBean brand : registerForm.getBrands()) { %>
									<option <%=	brand.getId() == registerForm.getAlternativeBrandId() ? "selected" : ""%> value="<%=brand.getId()%>">
										<%=brand.getName()%></option>
								<% } %>
							</html:select>
						</div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.alternativeBrandId)) ? "*" : ""%><div id="err.consumer.alternativeBrandId"></div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.consumtionFrequency)) { %>
					<div class="myRow">
						<div class="myLabel width120">Frecuency</div>
						<div class="myLabel width270"><html:text name="UpdatePersonForm" property="consumptionFrequency" styleClass="normalField width50"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.consumtionFrequency)) ? "*" : ""%><div id="err.consumer.consumtionFrecuency"></div>
				<% } %>
				
				<logic:iterate id="dynamicField" name="UpdatePersonForm" property="dynamicFields">
					<% DynamicField df = (DynamicField)dynamicField; %>
					<% if (FieldType.BOOLEAN.equals(df.getFieldDescription().getType())) { %>
						<bean:write name="dynamicField" property="description"/>:<html:checkbox name="dynamicField" property="booleanValue" indexed="true"/>
					<% } %>
					<% if (FieldType.REFERENCE.equals(df.getFieldDescription().getType())) { %>
						<bean:write name="dynamicField" property="description"/>:
							<html:select name="dynamicField" property="value" indexed="true">
								<option value="">Select one option</option>
								<% for (ReferenceOption option : df.getOptions()) { %>
									<option <%=	(df.getValue() == null) ? "" : (option.getId() == df.getIntValue() ? "selected" : "")%> value="<%=option.getId()%>">
										<%=option.getName()%></option>
								<% } %>
							</html:select>
					<% } %>
					<% if (FieldType.DATE.equals(df.getFieldDescription().getType())) { %>
						<bean:write name="dynamicField" property="description"/>:
							<html:text name="dynamicField" property="dateValue" indexed="true" styleClass="date"/>
					<% } %>
					
					<% if (FieldType.DECIMAL.equals(df.getFieldDescription().getType())) { %>
						<bean:write name="dynamicField" property="description"/>:
							<html:text name="dynamicField" property="decimalValue" indexed="true" styleClass="decimal"/>
					<% } %>
					<% if (FieldType.EMAIL.equals(df.getFieldDescription().getType())) { %>
						<bean:write name="dynamicField" property="description"/>:
							<html:text name="dynamicField" property="value" indexed="true" styleClass="email"/>
					<% } %>
					<% if (FieldType.LARGETEXT.equals(df.getFieldDescription().getType())) { %>
						<bean:write name="dynamicField" property="description"/>:<html:textarea name="dynamicField" property="value" indexed="true"/>
					<% } %>
					<% if (FieldType.TEXT.equals(df.getFieldDescription().getType())) { %>
						<bean:write name="dynamicField" property="description"/>:<html:text name="dynamicField" property="value" indexed="true"/>
					<% } %>
					<% if (FieldType.NUMBER.equals(df.getFieldDescription().getType())) { %>
						<bean:write name="dynamicField" property="description"/>:<html:text name="dynamicField" property="value" indexed="true" styleClass="number"/>
					<% } %>
					<% if (FieldType.PASSWORD.equals(df.getFieldDescription().getType())) { %>
						<bean:write name="dynamicField" property="description"/>:<html:password name="dynamicField" property="value" indexed="true"/>
					<% } %>
					<br>
				</logic:iterate>
				
				<logic:iterate id="optIn" name="UpdatePersonForm" property="optIns">
					<div class="myRow">
						<div class="myLabel width100per"><h3><bean:write name="optIn" property="brandFamilyName"/></h3></div>
					</div>
					<div class="myRow">
						<div class="myLabel width30"><html:checkbox name="optIn" property="accepted" indexed="true" /></div>
						<div class="myLabel width350"><bean:write name="optIn" property="channelName"/></div>
					</div>
				</logic:iterate>
				<div class="myRow">
					<div class="myLabel width100per" align="center">
						<input type="submit" id="submitregister" value="Save">
						<input type="button" id="goback" value="Back">
					</div>
				</div>
			</html:form>
		</div>
	</div>
</div>
<!-- Layer legales -->
<%@ include file="includes/legalesLayer.jsp" %>
</body>
</html>
