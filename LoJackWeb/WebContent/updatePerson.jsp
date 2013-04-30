<%@page import="com.tdil.thalamus.client.facade.json.beans.StateBean"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.BrandBean"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.CountryBean"%>
<%@page import="com.tdil.thalamus.client.facade.json.fields.PersonFieldNames"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.PersonFields"%>
<%@page import="com.tdil.lojack.struts.forms.RegisterForm"%>
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
<%@ include file="includes/headLogged.jsp" %>
<script>
$(document).ready(
	function(){

		$("input[name=birthDate]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
			changeYear: true, minDate: "-100Y", maxDate: "+0D"});
		
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
				<div class="myRow">
						<div class="myLabel width120">* DNI</div>
						<div class="myLabel width270"><html:text name="UpdatePersonForm" property="document" styleClass="normalField width250"/></div>
					</div>
					<div class="myRow errorField" style="display: none;" id="p.profile.document">
						<div id="err.profile.document"></div>
					</div>
					<div class="myRow">
						<div class="myLabel width120">* Name</div>
						<div class="myLabel width270"><html:text name="UpdatePersonForm" property="firstName" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.firstName)) ? "" : ""%><div id="err.profile.firstName"></div>
					<div class="myRow">
						<div class="myLabel width120">* Surname</div>
						<div class="myLabel width270"><html:text name="UpdatePersonForm" property="lastName" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.lastName)) ? "" : ""%><div id="err.profile.lastName"></div>
					<div class="myRow">
						<div class="myLabel width120">* Gender</div>
						<div class="myLabel width30"><html:radio property="gender" value="Male" /></div>
						<div class="myLabel width100">Male</div>
						<div class="myLabel width30"><html:radio property="gender" value="Female" /></div>
						<div class="myLabel width110">Femenino</div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.gender)) ? "" : ""%><div id="err.profile.gender"></div>
					<div class="myRow">
					<% if (registerForm.isPrincipal(PersonFieldNames.email)) { %>
							<div class="myLabel width120">* E-mail / User</div>
							<div class="myLabel width270"><strong><bean:write name="UpdatePersonForm" property="email"/></strong></div>
						</div>
					<% } else { %>
							<div class="myLabel width120">* E-mail</div>
							<div class="myLabel width270"><html:text name="UpdatePersonForm" property="email" styleClass="normalField width250"/></div>
						</div>
						<div id="err.profile.email"></div>
					<% } %>
					<div class="myRow">
						<div class="myLabel width120">* Birth date</div>
						<div class="myLabel width270"><html:text name="UpdatePersonForm" property="birthDate" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.birthDate)) ? "" : ""%>
					<div class="myRow">
						<div class="myLabel width120">Area code</div>
						<div class="myLabel width270"><html:text name="UpdatePersonForm" property="phoneAreaCode" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneAreaCode)) ? "*" : ""%><div id="err.profile.phone.areaCode"></div>
					<div class="myRow">
						<div class="myLabel width120">Local number</div>
						<div class="myLabel width270"><html:text name="UpdatePersonForm" property="phoneNumber" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneNumber)) ? "*" : ""%><div id="err.profile.phone.number"></div>
				
				<div class="myRow">
						<div class="myLabel width120">Country</div>
						<div class="myLabel width270">
							<%=registerForm.getCountrySelected()%>
						</div>
					</div>
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
