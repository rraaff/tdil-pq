<%@page import="com.tdil.struts.resources.ApplicationResources"%>
<%@page import="com.tdil.lojack.struts.forms.LoginForm"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.DocumentTypeBean"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.StateBean"%>
<%
	com.tdil.mobile.UAgentInfo agentInfo = new com.tdil.mobile.UAgentInfo(request.getHeader("User-Agent"), request.getHeader("Accept"));
	if (agentInfo.detectMobileLong() || agentInfo.detectAndroidPhone()) { %>
	<jsp:forward page="./mobile/index.jsp"></jsp:forward>
<% } else { %>
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
<!-- Slider -->
<link type="text/css" rel="stylesheet" href="css/slider.css"  />
<script>
	var t=setInterval(function(){$("#right").click()},10000);
	$(document).ready(function()
	{
		var present=1;
		var next=2;
		var total_slide=document.getElementById("slider").childElementCount;

		$("#right").click(function()
		{

			present_slide="#slide"+present;
			next_slide="#slide"+next;
			$(present_slide).css("top","842px");
			$(next_slide).css("top","0px");
			present++;
			next++;
			if(present==(total_slide+1))
			{
				present=1;
				next=2;
				for(i=1;i<=total_slide;i++)
				{
					$("#slide"+i).css("top","842px");
				}
				$("#slide1").css("top","0px");
			}

		});

		$("#left").click(function()
		{
			if(present==1)
			{
			next_slide="#slide"+total_slide;
			present_slide="#slide"+present;
			$(present_slide).css("top","842px");
			$(next_slide).css("top","0px");

			present=total_slide;
			next=1;
			}else
			{
			next_slide="#slide"+(present-1);
			present_slide="#slide"+present;
			$(present_slide).css("top","842px");
			$(next_slide).css("top","0px");
			present--;
			next--;
			}
			if(next==0)
			{
				present=(total_slide-1);
				next=total_slide;

			}
		});
	});

</script>
<!-- End Slider -->
<script type="text/javascript" src="js/jstz.js"></script>
<script>

$(document).ready(
	function(){
		<%@ include file="includes/datePickerES.jspf" %>
		$("input[name=birthDate]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
			changeYear: true, minDate: "-100Y", maxDate: "+0D", yearRange: '-120:+0'})

		$("form[name='RegisterForm']").validate({
			errorPlacement: function(error, element) {
				error.appendTo( element.parent("div"));
			},
			rules: { 			},
			messages: {			},
			submitHandler: function() {
				<%@ include file="includes/blockUI.jspf" %>
				clearErrors();
	            $("form[name='RegisterForm']").ajaxSubmit({
	    			type: "POST",
	    			url: "./register.do",
	    			dataType: "json",
	    			success: postRegister
	    			});
	        }
		});

		$("form[name='LoginForm']").validate({
			errorPlacement: function(error, element) {
				error.appendTo( element.parent("div"));
			},
			rules: {
			},
			messages: {

			},
			submitHandler: function() {
				<%@ include file="includes/blockUI.jspf" %>
	            $("form[name='LoginForm']").ajaxSubmit({
	    			type: "POST",
	    			url: "./login.do",
	    			dataType: "json",
	    			success: postLogin
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
	    			success: postResetPassword
	    			});
	        }
		});

		$("button[cl]").each(function(indice,valor) {
		   $(valor).click(function() {
			   $( "#" + $(this).attr('cl') ).fadeOut();
			});
		});

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
		$( "#closeforgotPasswordLayer" ).click(function() {
			$( "#forgotPasswordLayer" ).fadeOut();
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

		/* Seteo el offset */
		var userDate = new Date();
		var userTimeZone = ( userDate.getTimezoneOffset()/60 )*( -1 );
		$("form[name='LoginForm'] input[name='timezoneOffset']").attr('value', userTimeZone);
		var tz = jstz.determine(); // Determines the time zone of the browser client
		$("form[name='LoginForm'] input[name='timezoneName']").attr('value',  tz.name());
	}
);

function clearErrors() {
	$("div[id^='err.']").each(function(index, valor) {
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
	centerLayer($(window), $( "#registerLayer" ));
}

function login() {
	$('#loginerr').prop('innerHTML', '');
	$('#loginerr').css('display', 'none');
	$("form[name='LoginForm'] input[name='username']").attr('value', '');
	$("form[name='LoginForm'] input[name='password']").attr('value', '');
	centerLayer($(window), $( "#loginLayer" ));
}

function forgotPassword() {
	$("form[name='RequestResetPasswordForm'] input[name='username']").attr('value', '');
	centerLayer($(window), $( "#forgotPasswordLayer" ));
}

<%@ include file="includes/openLegalesLayer.jsp" %>
<%@ include file="includes/contactJS.jspf" %>

function showVideo1() {
	centerLayer($(window), $( "#video1Layer" ));
}

function centerLayer(objWin, objLayer) {
	var top = (objWin.height() / 2) - (objLayer.height() / 2);
	var left = (objWin.width() / 2) - (objLayer.width() / 2);
	objLayer.css({
		position: 'absolute',
		//top: top + 'px',
		//left: left + 'px'
		top: '0px',
		left: '0px'
	}).fadeIn(500);
}

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
		$('#loginerr').prop('innerHTML', 'El usuario o contraseña son incorrectos');
		$('#loginerr').css('display', 'block');
	}
}

function postResetPassword(data) {
	<%@ include file="includes/unblockUI.jspf" %>
	$( "#forgotPasswordLayer" ).fadeOut();
	if (data.result == 'OK') {
		centerLayer($(window), $( "#forgotPasswordEmailSentLayer" ));
	} else {
		if (data.result == '404') {
			centerLayer($(window), $( "#forgotPasswordUserNotFoundLayer" ));
		} else {
			centerLayer($(window), $( "#forgotPasswordErrorLayer" ));
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
	return false;
}
</script>
<link type="text/css" href="css/index_menu.css" rel="stylesheet" />
<link type="text/css" href="css/index_modales.css" rel="stylesheet" />
<link type="text/css" href="css/index_social.css" rel="stylesheet" />
<link type="text/css" href="css/copyright.css" rel="stylesheet" />
<link type="text/css" href="css/laruedita.css" rel="stylesheet" />
<!-- link type="text/css" href="css/laruedita_animation.css" rel="stylesheet" / -->
<link type="text/css" media="@media only screen and (max-width : 480px) and (min-width : 1568px)" href="css/laruedita_animation.css" rel="stylesheet" />
<link type="text/css" href="css/mediaQueries.css" rel="stylesheet" />
</head>
<body>
<div id="menu" style="display:none;">
	<ul class="menu">
		<li class="first"><a href="#" class="parent"><span>Ingresa</span></a>
			<div>
				<ul>
					<li><a href="javascript:login();" id="login" title="Ingresar ahora"><span>Ingresar</span></a></li>
					<li><a href="javascript:forgotPassword();" id="forgotPassword" title="Recuperar clave"><span>Recuperar clave</span></a></li>
					<li><a href="javascript:register();" id="register" title="Registrate gratis"><span>Registrate Gratis</span></a></li>
					<li><a href="<%=ThalamusClientBeanFacade.getFacebookLogin().getUrl()%>" id="fb" title="Ingresá con tu cuenta de Facebook"><span>Ingresá con tu FB</span></a></li>
					<li><a href="<%=twitterUrl.getUrl()%>" id="fb" title="Ingresá con tu cuenta de Twitter"><span>Ingresá con tu Tw</span></a></li>
				</ul>
			</div>
		</li>
	</ul>
</div>
<%@ include file="includes/sectionSlider.jsp" %>
<%@ include file="includes/laRuedita.jsp" %>

<div id="flyingObjectContainer"> 
	<div id="logoIndex"><img src="images/skin_lj_rl/logos/lo-jack_index.png" /></div>
	
	<div id="socialSingleSignOn">
		<div><span class="textInside">Ingresá con tus cuentas</span></div>
		<div>
			<ul>
				<li class="sofacebook"><a href="<%=ThalamusClientBeanFacade.getFacebookLogin().getUrl()%>" id="fb" title="Ingresá con tu cuenta de Facebook"></a></li>
				<li class="sotwitter"><a href="<%=twitterUrl.getUrl()%>" id="fb" title="Ingresá con tu cuenta de Twitter"></a></li>
			</ul>
		</div>
	</div>
</div>

<%@ include file="includes/contactLayers.jspf" %>
<%@ include file="includes/copyright.jsp" %>

<!-- Los LAYERS -->

<div id="parkingsNotLoggedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="defaultLayerStyles">
		<div class="defaultLayerContent">
			<h3>Atención</h3>
			<p>Registrate y accede a parkings. Es gratis y podes usarlo tanto en tu pc como en cualquier dispositivo móvil que soporte HTML 5 y Javascript.</p>
				<fieldset>
					<button id="closeparkingsNotLoggedLayer" cl="parkingsNotLoggedLayer" class="indexButtonBase">Cerrar</button>
				</fieldset>
		</div>
	</div>
</div>

<div id="registerLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="registerLayerStyles">
		<div class="registerLayerContent">
			<div id="xContainer"><button id="closeregisterLayer1">X</button></div>
			<h3>Registrate</h3>
			<div class="myRow">Los campos marcados con * son requeridos para la registración</div>
			<html:form method="POST" action="/register">
				<% RegisterForm registerForm = (RegisterForm)session.getAttribute("RegisterForm");
				registerForm.searchReferenceData();
				%>
				<div class="scrollable">
					<fieldset>
						<label>* Tipo de doc</label>
						<html:select name="RegisterForm" property="documentType" >
							<option value="">Seleccione...</option>
							<% for (DocumentTypeBean codBean : LoginForm.getDocumentTypes()) { %>
								<option value="<%=codBean.getId()%>">
									<%=codBean.getName()%></option>
							<% } %>
						</html:select>
						<div class="myRow errorField" style="display: none;" id="p.profile.documentType">
							<div id="err.profile.documentType"></div>
						</div>
					</fieldset>
						
					<fieldset>
						<label>* Numero</label>
						<html:text name="RegisterForm" property="document" />
						<div class="myRow errorField" style="display: none;" id="p.profile.document">
							<div id="err.profile.document"></div>
						</div>
					</fieldset>
					<fieldset>
						<label>* Nombre</label>
						<html:text name="RegisterForm" property="firstName" />
							<%=(registerForm.isRequired(PersonFieldNames.firstName)) ? "" : ""%>
							<div class="myRow errorField" style="display: none;" id="p.profile.firstname">
								<div id="err.profile.firstname"></div>
							</div>
					</fieldset>
					<fieldset>
						<label>* Apellido</label>
						<html:text name="RegisterForm" property="lastName" />
						<%=(registerForm.isRequired(PersonFieldNames.lastName)) ? "" : ""%>
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
					<fieldset>
						<label>* E-mail</label>
						<html:text name="RegisterForm" property="email"/>
						<%=(registerForm.isRequired(PersonFieldNames.email)) ? "" : ""%>
						<div class="myRow errorField" style="display: none;" id="p.profile.email">
							<div id="err.profile.email"></div>
						</div>
					</fieldset>
					<fieldset>
						<label>* Clave</label>
						<html:password name="RegisterForm" property="password" />
						<%=(registerForm.isRequired(PersonFieldNames.password)) ? "" : ""%>
						<div class="myRow errorField" style="display: none;" id="p.credential.password">
							<div id="err.credential.password"></div>
						</div>
					</fieldset>
					<fieldset>
						<label>* Fecha de nac.</label>
						<html:text name="RegisterForm" property="birthDate" />
						<%=(registerForm.isRequired(PersonFieldNames.birthDate)) ? "" : ""%>
						<div class="myRow errorField" style="display: none;" id="p.profile.birthDate">
							<div id="err.profile.birthDate"></div>
						</div>
					</fieldset>
					<fieldset>
						<label>Código de área</label>
						<html:text name="RegisterForm" property="phoneAreaCode" />
						<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneAreaCode)) ? "" : ""%>
						<div class="myRow errorField" style="display: none;" id="p.profile.phone.areaCode">
							<div id="err.profile.phone.areaCode"></div>
						</div>
					</fieldset>
					<fieldset>
						<label>Teléfono celular</label>
						<html:text name="RegisterForm" property="phoneNumber" />
						<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneNumber)) ? "" : ""%>
						<div class="errorField" style="display: none;" id="p.profile.phone.number">
							<div id="err.profile.phone.number"></div>
						</div>
					</fieldset>
					<fieldset>
						<label>País</label>
						<span><%=registerForm.getCountrySelected()%></span>
					</fieldset>
					<fieldset>
						<label>Provincia</label>
						<html:select name="RegisterForm" property="stateId" >
							<option value="">Seleccione...</option>
							<% for (StateBean stateBean : registerForm.getStates()) { %>
								<option <%=	stateBean.getId() == registerForm.getStateId() ? "selected" : ""%> value="<%=stateBean.getId()%>">
									<%=stateBean.getName()%></option>
							<% } %>
						</html:select>
						<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.stateId)) ? "" : ""%>
						<div class="errorField" style="display: none;" id="p.profile.address.stateId">
							<div id="err.profile.address.stateId"></div>
						</div>
					</fieldset>
					<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.street1)) { %>
						<fieldset>
							<label>Calle 1</label>
							<html:text name="RegisterForm" property="street1" />
							<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.street1)) ? "" : ""%>
							<div class="errorField" style="display: none;" id="p.profile.address.street1">
								<div id="err.profile.address.street1"></div>
							</div>
						</fieldset>
					<% } %>
					<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.street2)) { %>
						<fieldset>
							<label>Calle 2</label>
							<html:text name="RegisterForm" property="street2" />
							<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.street2)) ? "" : ""%>
							<div class="errorField" style="display: none;" id="p.profile.address.street2">
								<div id="err.profile.address.street2"></div>
							</div>
						</fieldset>
					<% } %>
					<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.addressType)) { %>
						<fieldset>
							<label>Tipo</label>
							<html:select name="RegisterForm" property="addressType">
								<option value="">Seleccione...</option>
								<% for (String type : registerForm.getAddressTypes()) { %>
									<option <%=type.equals(registerForm.getAddressType()) ? "selected" : ""%> value="<%=type%>">
									<%=ApplicationResources.getMessage("address_" + type)%></option>
								<% } %>
							</html:select>
							<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.addressType)) ? "" : ""%>
							<div class="errorField" style="display: none;" id="p.profile.address.type">
								<div id="err.profile.address.type"></div>
							</div>
						</fieldset>
					<% } %>
					<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.postalCode)) { %>
						<fieldset>
							<label>Código postal</label>
							<html:text name="RegisterForm" property="postalCode" />
							<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.postalCode)) ? "" : ""%>
							<div class="errorField" style="display: none;" id="p.profile.address.postalCode">
								<div id="err.profile.address.postalCode"></div>
							</div>
						</fieldset>
					<% } %>
					<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.city)) { %>
						<fieldset>
							<label>Ciudad</label>
							<html:text name="RegisterForm" property="city" />
							<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.city)) ? "" : ""%>
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
	<div class="loginLayerStyles">
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
									<option value="<%=codBean.getId()%>">
										<%=codBean.getName()%></option>
								<% } %>
							</html:select>
					</fieldset>
					<fieldset>
						<label>Número</label>
						<html:text name="LoginForm" property="username"/>
					</fieldset>
					<fieldset>
						<label>CLAVE</label>
						<html:password name="LoginForm" property="password"/>
					</fieldset>
					<fieldset>
						<!-- input type="Checkbox" style="float:left; margin: 15px 5px 0 0;" />
						<span>Recordarme</span -->
						<span style="float: right;"><a href="javascript:forgotPassword();" id="forgotPassword" title="Recuperar tu clave">(olvidé mi clave)</a></span>
					</fieldset>
					<fieldset>
						<div style="float:left;"><input type="submit" id="submitlogin" value=" " class="indexLogin"></div>
						<div style="padding-top:15px; float:right;"><a href="javascript:switchToRegisterLayer();" id="closeloginLayerAndOpenRegistration" title="Registrate gratis">¿No estás registrado?</a></div>
					</fieldset>
			</html:form>
		</div>
	</div>
</div>
<!-- login error -->
<div id="loginInvalidLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="defaultLayerStyles">
		<div class="defaultLayerContent">
			<h3>Atención</h3>
			<p>El usuario y/o la clave no coinciden.</p>
				<fieldset>
					<button id="closeloginInvalidLayer" cl="closeloginInvalidLayer" class="indexButtonBase">Cerrar</button>
				</fieldset>
		</div>
	</div>
</div>

<!-- forgot password -->
<div id="forgotPasswordLayer" class="layerOnTop" style="display: none; z-index: 1501;">
	<div class="defaultLayerStyles">
		<div class="loginLayerContent">
			<html:form method="POST" action="/requestResetPassword">
				<div id="xContainer"><button class="buttonLink" id="closeforgotPasswordLayer">X</button></div>
				<h3>Recuperá tu clave</h3>
				<p>Ingresá tu DNI y te enviaremos por E-Mail un link de acceso exclusivo, para generar tu nueva clave.</p>
					<fieldset>
						<label>DNI</label>
						<html:text name="RequestResetPasswordForm" property="username"/>
					</fieldset>
					<fieldset>
						<div  style="padding:20px 0 0 0; float:right;"><button type="submit" id="submitforgotPassword" class="indexButtonBase">Enviar</button></div>
						<!-- input type="submit" id="submitforgotPassword" value="Submit" -->
					</fieldset>
			</html:form>
		</div>
	</div>
</div>
<!-- Forgot password e-mail sent -->
<div id="forgotPasswordEmailSentLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="defaultLayerStyles">
		<div class="loginLayerContent">
			<div id="xContainer"><button class="buttonLink" cl="closeforgotPasswordEmailSentLayer">X</button></div>
			<h3>Atención</h3>
			<div class="alert alert-block">Te hemos enviado una clave temporaria. <br />Si no recibís un E-Mail nuestro con la clave, por favor revisá el correo no deseado.</div>
				<fieldset>
					<div style="padding:20px 0 0 0; float:right;"><button type="submit" id="closeforgotPasswordEmailSentLayer"  class="indexButtonBase">Cerrar</button></div>
				</fieldset>
		</div>
	</div>
</div>
<div id="forgotPasswordUserNotFoundLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="defaultLayerStyles">
		<div class="loginLayerContent">
			<div id="xContainer"><button class="buttonLink" cl="closeforgotPasswordUserNotFoundLayer">X</button></div>
			<h3>Atención</h3>
			<div class="alert alert-block">El DNI no coincide con un usuario de Lo-Jack</div>
				<fieldset>
					<div style="padding:20px 0 0 0; float:right;"><button type="submit" id="closeforgotPasswordUserNotFoundLayer" class="indexButtonBase">Cerrar</button></div>
				</fieldset>
		</div>
	</div>
</div>
<div id="forgotPasswordErrorLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="defaultLayerStyles">
		<div class="loginLayerContent">
			<div id="xContainer"><button class="buttonLink" id="closeforgotPasswordUserNotFoundLayer">X</button></div>
			<h3>Atención</h3>
			<div class="alert alert-block">Ha ocurrido un error. Por favor intentelo nuevamente.</div>
				<fieldset>
					<div style="padding:20px 0 0 0; float:right;"><button type="button" id="closeforgotPasswordErrorLayer" class="indexButtonBase">Cerrar</button></div>
				</fieldset>
		</div>
	</div>
</div>

<div id="video1Layer" class="layerOnTop70" style="display: none; z-index: 1500;">
	<div class="defaultLayerStyles">
		<div style="width:927px; margin:0 auto;">
			<div class="closeLayerVideoLink"><button title="Cerrar video" class="btn btn-link customLink" id="closevideo1Layer" cl="video1Layer">< volver</button></div>
			<div id="videoWrapper">
				<video id="video1" width="900" height="400">
					<source src="" type="video/mp4; codecs='avc1,mp4a'" />
					<source src="" type="video/webm; codecs='vp8,vorbis'" />
				</video>
			</div>
			<div id="footerizer">
				<div class="col1_300 marginRight_60">
					<h3>Video Title</h3>
					<p>Con LoJack for Laptopts sabés que si te roban la computadora, te la encontramos.</p>
					<button class="btn btn-mini btn-primary" type="button">Mas info >></button>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Layer legales -->
<%@ include file="includes/legalesLayer.jsp" %>
</body>
</html>
<% } %>