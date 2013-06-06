<%@page import="com.tdil.lojack.struts.forms.RequestResetPasswordForm"%>
<%@ include file="includes/agentInfo.jspf" %>
<%
	if (isMobile || isAndroid) { 
		session.setAttribute("usingMobile", Boolean.TRUE);
		response.sendRedirect(request.getContextPath() + "/mobile/index.jsp");
 } else { %>
<%@page import="com.tdil.struts.resources.ApplicationResources"%>
<%@page import="com.tdil.lojack.struts.forms.LoginForm"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.DocumentTypeBean"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.StateBean"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%>
<%@page import="com.tdil.lojack.struts.forms.beans.OptIn"%>
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
--><% if (websiteUser != null && websiteUser.isLogged()) { %>
	<jsp:forward page="home.jsp"></jsp:forward>
<% 	return;
	} %>
<%
URLHolder twitterUrl = ThalamusClientBeanFacade.getTwitterLogin();
Cookie cookie1 = new Cookie("twt", twitterUrl.getCookie("JSESSIONID").getValue());
cookie1.setMaxAge(24*60*60);
response.addCookie(cookie1);

Cookie ecookie1 = new Cookie("etwt", twitterUrl.getCookie("AWSELB").getValue());
ecookie1.setMaxAge(24*60*60);
response.addCookie(ecookie1);
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/reset-styles.css" rel="stylesheet" media="screen">
<link href="css/sizers.css" rel="stylesheet" media="screen">
<!-- Bootstrap -->
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="css/tdil.bootstrap.modifier.css" />
<%@ include file="includes/headNotLogged.jsp" %>

<script type="text/javascript" src="js/jstz.js"></script>
<script>

var lastCenter = null;

$(document).ready(
	function(){
		<%@ include file="includes/datePickerES.jspf" %>

		$('#rueditaLogin').bind("mouseenter", function(event) {
			$('#centralRueditaLogin').fadeIn();
		});
		$('#rueditaLogin').bind("mouseout", function(event) {
			$('#centralRueditaLogin').fadeOut();
		});
		$('#rueditaParkings').bind("mouseenter", function(event) {
			$('#centralRueditaParkings').fadeIn();
		});
		$('#rueditaParkings').bind("mouseout", function(event) {
			$('#centralRueditaParkings').fadeOut();
		});

		$("input[name=password]").bind("keydown", function(event) {
		      // track enter key
		      var keycode = (event.keyCode ? event.keyCode : (event.which ? event.which : event.charCode));
		      if (keycode == 13) { // keycode for enter key
		         // force the 'Enter Key' to implicitly click the Update button
		         $('#submitlogin').click();
		         //document.getElementById('defaultActionButton').click();
		         return false;
		      } else  {
		         return true;
		      }
		   });
		$("input[name=username]").bind("keydown", function(event) {
		      // track enter key
		      var keycode = (event.keyCode ? event.keyCode : (event.which ? event.which : event.charCode));
		      if (keycode == 13) { // keycode for enter key
		         // force the 'Enter Key' to implicitly click the Update button
		         $('#submitlogin').click();
		         //document.getElementById('defaultActionButton').click();
		         return false;
		      } else  {
		         return true;
		      }
		   });
		   
		//showOtherMonths: true,
		var queryDate = '2012-01-01';
		var parsedDate = $.datepicker.parseDate('yy-mm-dd', queryDate);
		$("input[name=birthDate]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
			changeYear: true, minDate: "-100Y", maxDate: "+0D", yearRange: '-120:+0', defaultDate: parsedDate});

		$("form[name='RegisterForm']").validate({
			errorPlacement: function(error, element) {
				//error.appendTo( element.parent("fieldset").next("div"));
				if(element.prop('name') == 'gender') {
					error.appendTo( element.parent("fieldset").next("div"));
				} else {
					error.appendTo( element.next("div"));
				}
			},
			rules: { 
				'documentType': {required: true},
				'document': {required: true, digits: true},
				'firstName': {required: true},
				'lastName': {required: true},
				'email': {required: true, email: true},
				'gender': {required: true},
				'password': {required: true},
				'birthDate': {required: true}
			},
			messages: {	
				'documentType': {required: "<span>Seleccione el tipo de documento.</span>"},
				'document': {required: "<span>Ingrese el numero de documento.</span>",
					digits: "<span>Ingrese solo numeros.<span>"},
				'firstName': {required: "<span>Ingrese el nombre.</span>"},
				'lastName': {required: "<span>Ingrese el apellido.</span>"},
				'email': {required: "<span>Ingrese el email.</span>",
					email: "<span>Ingrese un email valido.</span>"},
				'gender': {required: "<span>Seleccione el sexo.</span>"},
				'password': {required: "<span>Ingrese el password.</span>"},
				'birthDate': {required: "<span>Ingrese la fecha de nacimiento.</span>"}
			},
			submitHandler: function() {
				<%@ include file="includes/blockUI.jspf" %>
				clearErrors();
	            $("form[name='RegisterForm']").ajaxSubmit({
	    			type: "POST",
	    			url: "./register.do",
	    			dataType: "json",
	    			success: postRegister,
	    			<%@ include file="includes/openErrorLayerJS.jspf" %>
	    			});
	        }
		});

		$("form[name='LoginForm']").validate({
			errorPlacement: function(error, element) {
				error.appendTo( element.parent("fieldset").next("div"));
			},
			rules: {
				'documentType': {required: true},
				'username': {required: true, digits: true},
				'password': {required: true}
			},
			messages: {
				'documentType': {required: "<span>Seleccione el tipo de documento.</span>"},
				'username': {required: "<span>Ingrese el numero de documento.</span>",
					digits: "<span>Ingrese solo numeros.<span>"},
				'password': {required: "<span>Ingrese el password.</span>"}

			},
			submitHandler: function() {
				<%@ include file="includes/blockUI.jspf" %>
				clearErrors();
	            $("form[name='LoginForm']").ajaxSubmit({
	    			type: "POST",
	    			url: "./login.do",
	    			dataType: "json",
	    			success: postLogin,
	    			<%@ include file="includes/openErrorLayerJS.jspf" %>
	    			});
	        }
		});

		$("form[name='RequestResetPasswordForm']").validate({
			errorPlacement: function(error, element) {
				error.appendTo( element.parent("div"));
			},
			rules: { 			},
			messages: {			},
			submitHandler: function() {
				<%@ include file="includes/blockUI.jspf" %>
	            $("form[name='RequestResetPasswordForm']").ajaxSubmit({
	    			type: "POST",
	    			url: "./requestResetPassword.do",
	    			dataType: "json",
	    			success: postResetPassword,
	    			<%@ include file="includes/openErrorLayerJS.jspf" %>
	    			});
	        }
		});

		<%@ include file="includes/closeLayers.jspf" %>

		$( "#closeregisterLayer" ).click(function() {
			$( "#registerLayer" ).fadeOut();
			clearErrors();
			return false;
		});
		$( "#closeregisterLayer1" ).click(function() {
			$( "#registerLayer" ).fadeOut();
			clearErrors();
			return false;
		});
		$( "#closeloginLayer" ).click(function() {
			$( "#loginLayer" ).fadeOut();
			return false;
		});
		$( "#closeforgotPasswordEmailSentLayer" ).click(function() {
			$( "#forgotPasswordEmailSentLayer" ).fadeOut();
		});
		$( "#closeforgotPasswordUserNotFoundLayer" ).click(function() {
			$( "#forgotPasswordUserNotFoundLayer" ).fadeOut();
		});
		$( "#closeforgotPasswordErrorLayer" ).click(function() {
			$( "#forgotPasswordErrorLayer" ).fadeOut();
		});
		$( "#closeloginInvalidLayer" ).click(function() {
			$( "#loginInvalidLayer" ).fadeOut();
		});

		<%@ include file="includes/closeLegalesLayer.jsp" %>

		<% if ("true".equals(request.getParameter("openRegister"))) { %>
			basicRegister();
		<% } %>

	}
);

<%@ include file="includes/errorAjaxJS.jspf" %>
<%@ include file="includes/centerLayerJS.jspf" %>

function clearErrors() {
	$("div[id^='err.']").each(function(index, valor) {
		$(valor).prop('innerHTML','');
	});
	$(".errorInForm").each(function(index, valor) {
		$(valor).prop('innerHTML','');
	});
	$(".myRow.errorField").each(
		function (index, valor) {
			$(valor).css("display", "none");
		}
	);
}
function switchToRegisterLayer() {
	$( "#loginLayer" ).fadeOut(400, function() {register();});
}
function register() {
	$("form[name='RegisterForm'] input[type='text']").each(function(index, param) {
		$(param).attr('value', '');
	});
	basicRegister();
}

function basicRegister() {
	$("form[name='RegisterForm'] input[type='password']").each(function(index, param) {
		$(param).attr('value', '');
	});
	$("form[name='RegisterForm'] input[type='radio']").each(function(index, param){
		$(param).prop('checked', false);
	  });
	$("form[name='RegisterForm'] input[type='checkbox']").each(function(index, param){
		$(param).prop('checked', false);
	});
	$("form[name='RegisterForm'] select").each(function(index, param) {
		$(param).val('');
	});
	$("form[name='RegisterForm'] select[name=documentType]").val('1');
	centerLayerWF($(window), $( "#registerLayer" ), function() {$("form[name='RegisterForm'] input[name='document']").focus();});
	centerLayer($(window), $( "#centradorModalesRegister" ));
}

function login() {
	clearErrors();
	$('#loginerr').prop('innerHTML', '');
	$('#loginerr').css('display', 'none');
	$("form[name='LoginForm'] input[name='username']").attr('value', '');
	$("form[name='LoginForm'] input[name='password']").attr('value', '');
	$("form[name='LoginForm'] select[name=documentType]").val('1');
	//$("form[name='LoginForm'] input[name='username']").focus();
	centerLayerWF($(window), $( "#loginLayer" ), function() {$("form[name='LoginForm'] input[name='username']").focus();});
	centerLayer($(window), $( "#centradorModalesLogin" ));
}

function forgotPassword() {
	$("form[name='RequestResetPasswordForm'] input[name='username']").attr('value', '');
	$("form[name='RequestResetPasswordForm'] select[name=documentType]").val('1');
	centerLayerWF($(window), $( "#forgotPasswordLayer" ), function() {$("form[name='RequestResetPasswordForm'] input[name='username']").focus();});
	centerLayer($(window), $( "#centradorModalesforgotPass" ));
}

<%@ include file="includes/openLegalesLayer.jsp" %>
<%@ include file="includes/contactJS.jspf" %>

function postRegister(data) {
	<%@ include file="includes/unblockUI.jspf" %>
	if (data.result == 'OK') {
		window.location.replace('./home.jsp');
	} else {
		$.each(data, function(key, value) {
			var obj = document.getElementById('err.' + key);
			if (obj) {
				var objParent = document.getElementById('p.' + key);
				if (objParent) {
					objParent.style.display='block';
				}
				obj.innerHTML = value;
			}
	       });
	}
}

function postLogin(data) {
	<%@ include file="includes/unblockUI.jspf" %>
	if (data.result == 'OK') {
		window.location.replace('./home.jsp');
	} else {
		$('#loginerr').prop('innerHTML', 'El usuario o contrase�a son incorrectos');
		$('#loginerr').css('display', 'block');
	}
}

function postResetPassword(data) {
	<%@ include file="includes/unblockUI.jspf" %>
	$( "#forgotPasswordLayer" ).fadeOut();
	if (data.result == 'OK') {
		centerLayer($(window), $( "#forgotPasswordEmailSentLayer" ));
		centerLayer($(window), $( "#centradorModalesFPES" ));
	} else {
		if (data.result == '404') {
			centerLayer($(window), $( "#forgotPasswordUserNotFoundLayer" ));
			centerLayer($(window), $( "#centradorModalesForgotPasswordUserNotFound" ));
		} else {
			centerLayer($(window), $( "#forgotPasswordErrorLayer" ));
			centerLayer($(window), $( "#centradorModalesForgotPasswordError" ));
		}
	}
}
$(document).ready(
	function(){
		$( "#closeparkingsNotLoggedLayer" ).click(function() {
			$( "#parkingsNotLoggedLayer" ).fadeOut();
			return false;
		});
	}
);
function parkingsNotLogged() {
	centerLayer($(window), $( "#parkingsNotLoggedLayer" ));
	centerLayer($(window), $( "#centradorModalesParkingNo" ));
	return false;
}
</script>
<link type="text/css" href="css/index_menu.css" rel="stylesheet" />
<link type="text/css" href="css/index_modales.css" rel="stylesheet" />
<link type="text/css" href="css/index_social.css" rel="stylesheet" />
<link type="text/css" href="css/copyright.css" rel="stylesheet" />
<link type="text/css" href="css/laruedita.css" rel="stylesheet" />
<link type="text/css" href="css/flexi-background.css" rel="stylesheet" media="screen" />
<link type="text/css" media="@media only screen and (max-width : 480px) and (min-width : 1568px)" href="css/laruedita_animation.css" rel="stylesheet" />
<link type="text/css" href="css/mediaQueries.css" rel="stylesheet" />
</head>
<body>
<script src="js/flexi-background.js" type="text/javascript" charset="utf-8"></script>
<div id="menu" style="display:none;">
	<ul class="menu">
		<li class="first"><a href="#" class="parent"><span>Ingresa</span></a>
			<div>
				<ul>
					<li><a href="javascript:login();" id="login" title="Ingresar ahora"><span>Ingresar</span></a></li>
					<li><a href="javascript:forgotPassword();" id="forgotPassword" title="Recuperar clave"><span>Recuperar clave</span></a></li>
					<li><a href="javascript:register();" id="register" title="Registrate gratis"><span>Registrate Gratis</span></a></li>
					<li><a href="<%=ThalamusClientBeanFacade.getFacebookLogin().getUrl()%>" id="fb" title="Ingres� con tu cuenta de Facebook"><span>Ingres� con tu FB</span></a></li>
					<li><a href="<%=twitterUrl.getUrl()%>" id="fb" title="Ingres� con tu cuenta de Twitter"><span>Ingres� con tu Tw</span></a></li>
				</ul>
			</div>
		</li>
	</ul>
</div>
<%@ include file="includes/laRuedita.jsp" %>

<div id="flyingObjectContainer"> 
	<div id="logoIndex"><img src="images/skin_lj_rl/logos/lo-jack_index.png" /></div>
	
	<!-- div id="socialSingleSignOn">
		<div><span class="textInside">Ingres� con tus cuentas</span></div>
		<div>
			<ul>
				<li class="sofacebook"><a href="< %=ThalamusClientBeanFacade.getFacebookLogin().getUrl()%>" id="fb" title="Ingres� con tu cuenta de Facebook"></a></li>
				<li class="sotwitter"><a href="< %=twitterUrl.getUrl()%>" id="fb" title="Ingres� con tu cuenta de Twitter"></a></li>
			</ul>
		</div>
	</div-->
</div>

<%@ include file="includes/contactLayers.jspf" %>
<%@ include file="includes/copyright.jsp" %>

<!-- Los LAYERS -->

<div id="parkingsNotLoggedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesParkingNo" class="defaultLayerStyles">
		<div class="defaultLayerContent">
			<h3>Atenci�n</h3>
			<p>Registrate y accede a parkings. Es gratis y podes usarlo tanto en tu pc como en cualquier dispositivo m�vil que soporte HTML 5 y Javascript.</p>
			<fieldset>
				<button id="closeparkingsNotLoggedLayer" cl="parkingsNotLoggedLayer" class="indexButtonBase">Cerrar</button>
			</fieldset>
		</div>
	</div>
</div>

<div id="registerLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesRegister" class="loginLayerStyles">
		<div class="registerLayerContent">
			<div id="xContainer"><button id="closeregisterLayer1">X</button></div>
			<h3>Registrate</h3>
			<div class="myRow">Los campos marcados con * son requeridos para la registraci�n</div>
			<html:form method="POST" action="/register">
				<% RegisterForm registerForm = (RegisterForm)session.getAttribute("RegisterForm");
				registerForm.searchReferenceData();
				%>
				<div class="scrollable">
					<fieldset>
						<label class="ajuste">* Tipo de doc</label>
						<html:select name="RegisterForm" property="documentType" >
							<option value="">Seleccione...</option>
							<% for (DocumentTypeBean codBean : LoginForm.getDocumentTypes()) { %>
								<option value="<%=codBean.getId()%>" <%=1 == codBean.getId() ? "selected" : ""%>>
									<%=codBean.getName()%></option>
							<% } %>
						</html:select>
						<div class="errorInForm"></div>
						<div class="myRow errorField" style="display: none;" id="p.profile.documentType">
							<div id="err.profile.documentType"></div>
						</div>
					</fieldset>
					<fieldset>
						<label class="ajuste">* Numero</label>
						<html:text name="RegisterForm" property="document" />
						<div class="errorInForm"></div>
						<div class="myRow errorField" style="display: none;" id="p.profile.document">
							<div id="err.profile.document"></div>
						</div>
					</fieldset>
					<fieldset>
						<label class="ajuste">* Nombre</label>
						<html:text name="RegisterForm" property="firstName" />
							<%=(registerForm.isRequired(PersonFieldNames.firstName)) ? "" : ""%>
							<div class="errorInForm"></div>
							<div class="myRow errorField" style="display: none;" id="p.profile.firstname">
								<div id="err.profile.firstname"></div>
							</div>
					</fieldset>
					<fieldset>
						<label class="ajuste">* Apellido</label>
						<html:text name="RegisterForm" property="lastName" />
						<%=(registerForm.isRequired(PersonFieldNames.lastName)) ? "" : ""%>
						<div class="errorInForm"></div>
						<div class="myRow errorField" style="display: none;" id="p.profile.lastname">
							<div id="err.profile.lastname"></div>
						</div>
					</fieldset>
					<fieldset>
						<label class="ajuste">* Sexo:</label>
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
						<label class="ajuste">* E-mail</label>
						<html:text name="RegisterForm" property="email"/>
						<%=(registerForm.isRequired(PersonFieldNames.email)) ? "" : ""%>
						<div class="errorInForm"></div>
						<div class="myRow errorField" style="display: none;" id="p.profile.email">
							<div id="err.profile.email"></div>
						</div>
					</fieldset>
					<fieldset>
						<label class="ajuste">* Clave</label>
						<html:password name="RegisterForm" property="password" />
						<%=(registerForm.isRequired(PersonFieldNames.password)) ? "" : ""%>
						<div class="errorInForm"></div>
						<div class="myRow errorField" style="display: none;" id="p.credential.password">
							<div id="err.credential.password"></div>
						</div>
					</fieldset>
					<fieldset>
						<label class="ajuste">* Fecha de nac.</label>
						<html:text name="RegisterForm" property="birthDate" />
						<%=(registerForm.isRequired(PersonFieldNames.birthDate)) ? "" : ""%>
						<div class="errorInForm"></div>
						<div class="myRow errorField" style="display: none;" id="p.profile.birthDate">
							<div id="err.profile.birthDate"></div>
						</div>
					</fieldset>
					<fieldset>
						<label class="ajuste">C�digo de �rea</label>
						<html:text name="RegisterForm" property="phoneAreaCode" />
						<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneAreaCode)) ? "" : ""%>
						<div class="errorInForm"></div>
						<div class="myRow errorField" style="display: none;" id="p.profile.phone.areaCode">
							<div id="err.profile.phone.areaCode"></div>
						</div>
					</fieldset>
					<fieldset>
						<label class="ajuste">Tel�fono celular</label>
						<html:text name="RegisterForm" property="phoneNumber" />
						<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneNumber)) ? "" : ""%>
						<div class="errorInForm"></div>
						<div class="errorField" style="display: none;" id="p.profile.phone.number">
							<div id="err.profile.phone.number"></div>
						</div>
					</fieldset>
					<fieldset>
						<label class="ajuste">Pa�s</label>
						<span><%=registerForm.getCountrySelected()%></span>
					</fieldset>
					<fieldset>
						<label class="ajuste">Provincia</label>
						<html:select name="RegisterForm" property="stateId" >
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
							<label class="ajuste">Calle 1</label>
							<html:text name="RegisterForm" property="street1" />
							<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.street1)) ? "" : ""%>
							<div class="errorInForm"></div>
							<div class="errorField" style="display: none;" id="p.profile.address.street1">
								<div id="err.profile.address.street1"></div>
							</div>
						</fieldset>
					<% } %>
					<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.street2)) { %>
						<fieldset>
							<label class="ajuste">Calle 2</label>
							<html:text name="RegisterForm" property="street2" />
							<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.street2)) ? "" : ""%>
							<div class="errorInForm"></div>
							<div class="errorField" style="display: none;" id="p.profile.address.street2">
								<div id="err.profile.address.street2"></div>
							</div>
						</fieldset>
					<% } %>
					<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.addressType)) { %>
						<fieldset>
							<label class="ajuste">Tipo</label>
							<html:select name="RegisterForm" property="addressType">
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
							<label class="ajuste">C�digo postal</label>
							<html:text name="RegisterForm" property="postalCode" />
							<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.postalCode)) ? "" : ""%>
							<div class="errorInForm"></div>
							<div class="errorField" style="display: none;" id="p.profile.address.postalCode">
								<div id="err.profile.address.postalCode"></div>
							</div>
						</fieldset>
					<% } %>
					<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.city)) { %>
						<fieldset>
							<label class="ajuste">Ciudad</label>
							<html:text name="RegisterForm" property="city" />
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
		</div>
	</div>
</div>

<!-- Login Form -->
<div id="loginLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesLogin" class="loginLayerStyles">
		<div class="loginLayerContent">
			<html:form method="POST" action="/login">
				<div id="xContainer"><button id="closeloginLayer">X</button></div>
				<h3>Ingresar</h3>
				<html:hidden name="LoginForm" property="timezoneOffset"/>
				<html:hidden name="LoginForm" property="timezoneName"/>
				<div class="alert alert-error" id="loginerr" style="display: none;"></div>
					<fieldset>
						<label>Tipo doc</label>
						<% LoginForm loginForm = (LoginForm)session.getAttribute("LoginForm"); %>
						<html:select name="LoginForm" property="documentType" >
								<option value="">Seleccione...</option>
								<% for (DocumentTypeBean codBean : LoginForm.getDocumentTypes()) { %>
									<option value="<%=codBean.getId()%>" <%=1 == codBean.getId() ? "selected" : ""%>>
										<%=codBean.getName()%></option>
								<% } %>
							</html:select>
					</fieldset>
					<div class="errorInForm"></div>
					<fieldset>
						<label>N�mero</label>
						<html:text name="LoginForm" property="username"/>
					</fieldset>
					<div class="errorInForm"></div>
					<fieldset>
						<label>CLAVE</label>
						<html:password name="LoginForm" property="password"/>
					</fieldset>
					<div class="errorInForm"></div>
					<fieldset>
						<!-- input type="Checkbox" style="float:left; margin: 15px 5px 0 0;" />
						<span>Recordarme</span -->
						<span style="float: right;"><a href="javascript:forgotPassword();" id="forgotPassword" title="Recuperar tu clave">(olvid� mi clave)</a></span>
					</fieldset>
					<fieldset>
						<div style="float:left;"><input type="submit" id="submitlogin" value=" " class="indexLogin"></div>
						<div style="padding-top:15px; float:right;"><a href="javascript:switchToRegisterLayer();" id="closeloginLayerAndOpenRegistration" title="Registrate gratis">�No est�s registrado?</a></div>
					</fieldset>
			</html:form>
		</div>
	</div>
</div>

<!-- login error -->
<div id="loginInvalidLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesLoginInvalid" class="defaultLayerStyles">
		<div class="defaultLayerContent">
			<h3>Atenci�n</h3>
			<p>El usuario y/o la clave no coinciden.</p>
			<fieldset>
				<button id="closeloginInvalidLayer" cl="loginInvalidLayer" class="indexButtonBase">Cerrar</button>
			</fieldset>
		</div>
	</div>
</div>

<!-- forgot password -->
<div id="forgotPasswordLayer" class="layerOnTop" style="display: none; z-index: 1501;">
	<div id="centradorModalesforgotPass" class="defaultLayerStyles">
		<div class="forgotPasswordLayerContent">
			<div id="xContainer"><button class="buttonLink" cl="forgotPasswordLayer">X</button></div>
			<html:form method="POST" action="/requestResetPassword">
				<h3>Recuper� tu clave</h3>
				<p style="padding-bottom:15px;">Ingres� tu DNI y te enviaremos por E-Mail un link de acceso exclusivo, para generar tu nueva clave.</p>
				<fieldset>
					<label>Tipo doc</label>
					<html:select name="RequestResetPasswordForm" property="documentType" >
							<option value="">Seleccione...</option>
							<% for (DocumentTypeBean codBean : LoginForm.getDocumentTypes()) { %>
								<option value="<%=codBean.getId()%>" <%=1 == codBean.getId() ? "selected" : ""%>>
									<%=codBean.getName()%></option>
							<% } %>
					</html:select>
				</fieldset>
				<fieldset>
					<label>N�mero</label>
					<html:text name="RequestResetPasswordForm" property="username" styleClass="width240" />
				</fieldset>
				<fieldset>
					<button type="submit" id="submitforgotPassword" class="indexButtonBase">Enviar</button>
				</fieldset>
			</html:form>
		</div>
	</div>
</div>
<!-- Forgot password e-mail sent -->
<div id="forgotPasswordEmailSentLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesFPES" class="defaultLayerStyles">
		<div class="loginLayerContent">
			<div id="xContainer"><button class="buttonLink" cl="closeforgotPasswordEmailSentLayer">X</button></div>
			<h3>Atenci�n</h3>
			<div class="alert alert-block">Te hemos enviado una clave temporaria. <br />Si no recib�s un E-Mail nuestro con la clave, por favor revis� el correo no deseado.</div>
			<fieldset>
				<button type="submit" id="closeforgotPasswordEmailSentLayer"  class="indexButtonBase">Cerrar</button>
			</fieldset>
		</div>
	</div>
</div>
<div id="forgotPasswordUserNotFoundLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesForgotPasswordUserNotFound" class="defaultLayerStyles">
		<div class="loginLayerContent">
			<div id="xContainer"><button class="buttonLink" cl="forgotPasswordUserNotFoundLayer">X</button></div>
			<h3>Atenci�n</h3>
			<div class="alert alert-block">El DNI no coincide con un usuario de Lo-Jack</div>
			<fieldset><button type="submit" id="closeforgotPasswordUserNotFoundLayer" class="indexButtonBase">Cerrar</button></fieldset>
		</div>
	</div>
</div>
<div id="forgotPasswordErrorLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesForgotPasswordError" class="defaultLayerStyles">
		<div class="loginLayerContent">
			<div id="xContainer"><button class="buttonLink" id="closeforgotPasswordErrorLayer">X</button></div>
			<h3>Atenci�n</h3>
			<div class="alert alert-block">Ha ocurrido un error. Por favor intentelo nuevamente.</div>
			<!-- fieldset><button type="button" id="closeforgotPasswordErrorLayer" class="indexButtonBase">Cerrar</button></fieldset-->
		</div>
	</div>
</div>

<%@ include file="includes/videoLayers.jsp" %>

<%@ include file="includes/errorAjaxLayer.jspf" %>
<!-- Layer legales -->
<%@ include file="includes/legalesLayer.jsp" %>
</body>
</html>
<% } %>