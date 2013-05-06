<%@page import="com.tdil.thalamus.client.facade.json.beans.StateBean"%>
<%
	com.tdil.mobile.UAgentInfo agentInfo = new com.tdil.mobile.UAgentInfo(request.getHeader("User-Agent"), request.getHeader("Accept"));
	if (agentInfo.detectMobileLong()) { %>
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
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/reset-styles" rel="stylesheet" media="screen">
<!-- Bootstrap -->
<%@ include file="includes/headNotLogged.jsp" %>
<!-- Slider -->
<script src="js/jquery-latest.js"></script>
<link href="css/slider.css" rel="stylesheet" type="text/css" />
<script src="js/slideshow.js"></script>
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

		$("input[name=birthDate]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
			changeYear: true, minDate: "-100Y", maxDate: "+0D"});

		$("form[name='RegisterForm']").validate({
			errorPlacement: function(error, element) {
				error.appendTo( element.parent("div"));
			},
			rules: { 			},
			messages: {			},
			submitHandler: function() {
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
	            $("form[name='RequestResetPasswordForm']").ajaxSubmit({
	    			type: "POST",
	    			url: "./requestResetPassword.do",
	    			dataType: "json",
	    			success: postResetPassword
	    			});
	        }
		});

		$( "#closeregisterLayer" ).click(function() {
			$( "#registerLayer" ).fadeOut();
			clearErrors();
		});
		$( "#closeloginLayer" ).click(function() {
			$( "#loginLayer" ).fadeOut();
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
	$("form[name='LoginForm'] input[name='username']").attr('value', '');
	$("form[name='LoginForm'] input[name='password']").attr('value', '');
	centerLayer($(window), $( "#loginLayer" ));
}

function forgotPassword() {
	$("form[name='RequestResetPasswordForm'] input[name='username']").attr('value', '');
	centerLayer($(window), $( "#forgotPasswordLayer" ));
}

<%@ include file="includes/openLegalesLayer.jsp" %>

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
	if (data.result == 'OK') {
		window.location.replace('./home.jsp');
	} else {
		$( "#loginLayer" ).fadeOut();
		centerLayer($(window), $( "#loginInvalidLayer" ));
	}
}

function postResetPassword(data) {
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

</script>
<link type="text/css" href="css/index_menu.css" rel="stylesheet" />
<script type="text/javascript" src="js/indexMenu.js"></script>
</head>
<body>
<div id="menu">
	<ul class="menu">
		<li class="first"><a href="#" class="parent"><span>Ingresa</span></a>
			<div>
				<ul>
					<li><a href="javascript:login();" id="login" title="Access now!"><span>Ingresar</span></a></li>
					<li><a href="javascript:forgotPassword();" id="forgotPassword" title="Recuperar clave"><span>Recuperar clave</span></a></li>
					<li><a href="javascript:verLegales();" id="legales" title="Legales"><span>Legales</span></a></li>
					<li><a href="javascript:register();" id="register" title="Registrate gratis"><span>Registrate Gratis</span></a></li>
					<li><a href="<%=ThalamusClientBeanFacade.getFacebookLogin().getUrl()%>" id="fb" title="Ingresá con tu cuenta de Facebook"><span>Ingresá con tu FB</span></a></li>
					<li><a href="<%=ThalamusClientBeanFacade.getTwitterLogin().getUrl()%>" id="fb" title="Ingresá con tu cuenta de Twitter"><span>Ingresá con tu Tw</span></a></li>
				</ul>
			</div>
		</li>
	</ul>
</div>
<section id="contentSlider">
	<div id="sliderContainer">

		<div id="slider"><!-- <img src="images/null.gif" />-->
			<div id="slide1" class="slide page2"><a href="#" title="">&nbsp;</a></div>
			<div id="slide2" class="slide page2"><a href="#" title="">&nbsp;</a></div>
			<div id="slide3" class="slide page2"><a href="#" title="">&nbsp;</a></div>
			<div id="slide4" class="slide page2"><a href="#" title="">&nbsp;</a></div>
			<div id="slide5" class="slide page2"><a href="#" title="">&nbsp;</a></div>
		</div>
		<div id="sliderControl"><img src="images/front.png" width="40px;" id="right"/>
		<img src="images/back.png" width="40px;"  id="left"/></div>
	</div>
</section>
<section id="footer">

</section>
<div id="registerLayer" class="layerOnTop" style="display: none; z-index: 500;">
	<div class="registerLayerStyles">
		<div class="registerLayerContent">
			<div class="myRow">
				<div class="myLabel width100per" style="text-align:center;">Field marqued with * are required for registration</div>
			</div>
			<html:form method="POST" action="/register">
				<% RegisterForm registerForm = (RegisterForm)session.getAttribute("RegisterForm");
				registerForm.searchReferenceData();
				%>
					<div class="myRow">
						<div class="myLabel width120">* DNI</div>
						<div class="myLabel width270"><html:text name="RegisterForm" property="document" styleClass="normalField width250"/></div>
					</div>
					<div class="myRow errorField" style="display: none;" id="p.profile.document">
						<div id="err.profile.document"></div>
					</div>
					<div class="myRow">
						<div class="myLabel width120">* Name</div>
						<div class="myLabel width270"><html:text name="RegisterForm" property="firstName" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.firstName)) ? "" : ""%>
					<div class="myRow errorField" style="display: none;" id="p.profile.firstName">
						<div id="err.profile.firstName"></div>
					</div>
					<div class="myRow">
						<div class="myLabel width120">* Surname</div>
						<div class="myLabel width270"><html:text name="RegisterForm" property="lastName" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.lastName)) ? "" : ""%>
					<div class="myRow errorField" style="display: none;" id="p.profile.lastName">
						<div id="err.profile.lastName"></div>
					</div>
					<div class="myRow">
						<div class="myLabel width120">* Gender</div>
						<div class="myLabel width30"><html:radio property="gender" value="Male" /></div>
						<div class="myLabel width100">Male</div>
						<div class="myLabel width30"><html:radio property="gender" value="Female" /></div>
						<div class="myLabel width110">Femenino</div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.gender)) ? "" : ""%>
					<div class="myRow errorField" style="display: none;" id="p.profile.gender">
						<div id="err.profile.gender"></div>
					</div>
					<div class="myRow">
						<div class="myLabel width120">* E-mail</div>
						<div class="myLabel width270"><html:text name="RegisterForm" property="email" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.email)) ? "" : ""%>
					<div class="myRow errorField" style="display: none;" id="p.profile.email">
						<div id="err.profile.email"></div>
					</div>
				<div class="myRow">
					<div class="myLabel width120">* Password</div>
					<div class="myLabel width270"><html:password name="RegisterForm" property="password" styleClass="normalField width250"/></div>
				</div>
				<%=(registerForm.isRequired(PersonFieldNames.password)) ? "" : ""%>
				<div class="myRow errorField" style="display: none;" id="p.credential.password">
					<div id="err.credential.password"></div>
				</div>
					<div class="myRow">
						<div class="myLabel width120">* Birth date</div>
						<div class="myLabel width270"><html:text name="RegisterForm" property="birthDate" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.birthDate)) ? "" : ""%>
					<div class="myRow errorField" style="display: none;" id="p.profile.birthDate">
						<div id="err.profile.birthDate"></div>
					</div>
					<div class="myRow">
						<div class="myLabel width120">Area code</div>
						<div class="myLabel width270"><html:text name="RegisterForm" property="phoneAreaCode" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneAreaCode)) ? "" : ""%>
					<div class="myRow errorField" style="display: none;" id="p.profile.phone.areaCode">
						<div id="err.profile.phone.areaCode"></div>
					</div>
					<div class="myRow">
						<div class="myLabel width120">Mobile number</div>
						<div class="myLabel width270"><html:text name="RegisterForm" property="phoneNumber" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.phone, PersonFieldNames.phoneNumber)) ? "" : ""%>
					<div class="myRow errorField" style="display: none;" id="p.profile.phone.number">
						<div id="err.profile.phone.number"></div>
					</div>
					<div class="myRow">
						<div class="myLabel width120">Country</div>
						<div class="myLabel width270">
							<%=registerForm.getCountrySelected()%>
						</div>
					</div>
					<div class="myRow">
						<div class="myLabel width120">State/Province</div>
						<div class="myLabel width270">
							<html:select name="RegisterForm" property="stateId" styleClass="normalField width250">
								<option value="">Select an option</option>
								<% for (StateBean stateBean : registerForm.getStates()) { %>
									<option <%=	stateBean.getId() == registerForm.getStateId() ? "selected" : ""%> value="<%=stateBean.getId()%>">
										<%=stateBean.getName()%></option>
								<% } %>
							</html:select>
						</div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.stateId)) ? "" : ""%>
					<div class="myRow errorField" style="display: none;" id="p.profile.address.stateId">
						<div id="err.profile.address.stateId"></div>
					</div>
				<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.street1)) { %>
					<div class="myRow">
						<div class="myLabel width120">Street 1</div>
						<div class="myLabel width270"><html:text name="RegisterForm" property="street1" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.street1)) ? "" : ""%>
					<div class="myRow errorField" style="display: none;" id="p.profile.address.street1">
						<div id="err.profile.address.street1"></div>
					</div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.street2)) { %>
					<div class="myRow">
						<div class="myLabel width120">Street 2</div>
						<div class="myLabel width270"><html:text name="RegisterForm" property="street2" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.street2)) ? "" : ""%>
					<div class="myRow errorField" style="display: none;" id="p.profile.address.street2">
						<div id="err.profile.address.street2"></div>
					</div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.addressType)) { %>
					<div class="myRow">
						<div class="myLabel width120">Type</div>
						<div class="myLabel width270">
							<html:select name="RegisterForm" property="addressType" styleClass="normalField width250">
								<option value="">Select one option</option>
								<% for (String type : registerForm.getAddressTypes()) { %>
									<option <%=type.equals(registerForm.getAddressType()) ? "selected" : ""%> value="<%=type%>">
								<%=type%></option>
								<% } %>
							</html:select>
						</div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.addressType)) ? "" : ""%>
					<div class="myRow errorField" style="display: none;" id="p.profile.address.type">
						<div id="err.profile.address.type"></div>
					</div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.postalCode)) { %>
					<div class="myRow">
						<div class="myLabel width120">Postal code</div>
						<div class="myLabel width270"><html:text name="RegisterForm" property="postalCode" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.postalCode)) ? "" : ""%>
					<div class="myRow errorField" style="display: none;" id="p.profile.address.postalCode">
						<div id="err.profile.address.postalCode"></div>
					</div>
				<% } %>
				<% if (registerForm.isInUse(PersonFieldNames.address, PersonFieldNames.city)) { %>
					<div class="myRow">
						<div class="myLabel width120">City</div>
						<div class="myLabel width270"><html:text name="RegisterForm" property="city" styleClass="normalField width250"/></div>
					</div>
					<%=(registerForm.isRequired(PersonFieldNames.address, PersonFieldNames.city)) ? "" : ""%>
					<div class="myRow errorField" style="display: none;" id="p.profile.address.city">
						<div id="err.profile.address.city"></div>
					</div>
				<% } %>
				<div class="myRow">
					<div class="myLabel width100per" align="center"><input type="submit" id="submitregister" value="Submit"><input type="button" id="closeregisterLayer" value="Cancel"></div>
				</div>
			</html:form>
		</div>
	</div>
</div>
<!-- Login Form -->
<div id="loginLayer" class="layerOnTop" style="display: none; z-index: 500;">
	<div class="loginLayerStyles">
		<div class="loginLayerContent">
			<html:form method="POST" action="/login">
				<html:hidden name="LoginForm" property="timezoneOffset"/>
				<html:hidden name="LoginForm" property="timezoneName"/>
				<div class="myRow">
					<div class="myLabel width100">&nbsp;</div>
					<div class="myLabel width100">User</div>
					<div class="myLabel width150"><html:text name="LoginForm" property="username" styleClass="normalField width120"/></div>
				</div>
				<div class="myRow">
					<div class="myLabel width100">&nbsp;</div>
					<div class="myLabel width100">Password</div>
					<div class="myLabel width150"><html:password name="LoginForm" property="password" styleClass="normalField width120"/></div>
				</div>
				<div class="myRow">
					<div class="myLabel width100per" align="center"><input type="submit" id="submitlogin" value="Login"><input type="button" id="closeloginLayer" value="Cancel"></div>
				</div>
			</html:form>
		</div>
	</div>
</div>
<!-- login error -->
<div id="loginInvalidLayer" class="layerOnTop" style="display: none; z-index: 500;">
	<div class="defaultLayerStyles">
		<div class="defaultLayerContent">
			<div class="myRow">
				<div class="myLabel width100per" align="center">Username and/or password doesn't match.</div>
			</div>
			<div class="myRow">
				<div class="myLabel width100per" align="center"><input type="button" id="closeloginInvalidLayer" value="Close"></div>
			</div>
		</div>
	</div>
</div>
<!-- forgot password -->
<div id="forgotPasswordLayer" class="layerOnTop" style="display: none; z-index: 500;">
	<div class="resetPassLayerStyles">
		<div class="resetPassLayerContent">
			<html:form method="POST" action="/requestResetPassword">
				<div class="myRow">
					<div class="myLabel width100">&nbsp;</div>
					<div class="myLabel width100">User/E-Mail</div>
					<div class="myLabel width150"><html:text name="RequestResetPasswordForm" property="username" styleClass="normalField width120"/></div>
				</div>
				<div class="myRow">
					<div class="myLabel width100per" align="center"><input type="submit" id="submitforgotPassword" value="Submit"><input type="button" id="closeforgotPasswordLayer" value="Cancel"></div>
				</div>
			</html:form>
		</div>
	</div>
</div>
<!-- Forgot password e-mail sent -->
<div id="forgotPasswordEmailSentLayer" class="layerOnTop" style="display: none; z-index: 500;">
	<div class="defaultLayerStyles">
		<div class="defaultLayerContent">
			<div class="myRow">
				<div class="myLabel width100per" align="center">We've sent you an E-Mail with a link to reset your password.<br />If you don't receive it in a few hour please check your Junk Mail Filters or retry.</div>
			</div>
			<div class="myRow">
				<div class="myLabel width100per" align="center"><input type="button" id="closeforgotPasswordEmailSentLayer" value="Back"></div>
			</div>
		</div>
	</div>
</div>
<div id="forgotPasswordUserNotFoundLayer" class="layerOnTop" style="display: none; z-index: 500;">
	<div class="defaultLayerStyles">
		<div class="defaultLayerContent">
			<div class="myRow">
				<div class="myLabel width100per" align="center">Incorrect username or password</div>
			</div>
			<div class="myRow">
				<div class="myLabel width100per" align="center"><input type="button" id="closeforgotPasswordUserNotFoundLayer" value="Back"></div>
			</div>
		</div>
	</div>
</div>
<div id="forgotPasswordErrorLayer" class="layerOnTop" style="display: none; z-index: 500;">
	<div class="defaultLayerStyles">
		<div class="defaultLayerContent">
			<div class="myRow">
				<div class="myLabel width100per" align="center">An error had occurred, please try again</div>
			</div>
			<div class="myRow">
				<div class="myLabel width100per" align="center"><input type="button" id="closeforgotPasswordErrorLayer" value="Close"></div>
			</div>
		</div>
	</div>
</div>
<!-- Layer legales -->
<%@ include file="includes/legalesLayer.jsp" %>
</body>
</html>
<% } %>