<%@page import="com.tdil.web.breadcrum.BreadcrumItem"%>
<%@page import="com.tdil.web.breadcrum.Breadcrum"%>
<%@page import="com.tdil.utils.SystemConfig"%>
<%@page import="com.tdil.ljpeugeot.struts.forms.RequestResetPasswordForm"%><%--
--%><%@ include file="includes/agentInfo.jspf" %><%--
--%><%if (isMobile || isAndroid) { 
		session.setAttribute("usingMobile", Boolean.TRUE);
		response.sendRedirect(request.getContextPath() + "/mobile/index.jsp");
 } else {%><%--
--%><%@page import="com.tdil.struts.resources.ApplicationResources"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.LoginForm"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.DocumentTypeBean"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.StateBean"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.beans.OptIn"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.BrandBean"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.CountryBean"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.fields.PersonFieldNames"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.PersonFields"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.RegisterForm"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><%@ page info="index"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="includes/checkThalamusUp.jspf" %><%--
--%><%@ include file="includes/userLogged.jspf" %><%--
--%><%if (websiteUser != null && websiteUser.isLogged()) {%>
	<jsp:forward page="home.jsp"></jsp:forward>
<%
	return;
	}
%><%--
--%><%
	URLHolder twitterUrl = ThalamusClientBeanFacade.getTwitterLogin();
Cookie cookie1 = new Cookie("twt", twitterUrl.getCookie("JSESSIONID").getValue());
cookie1.setMaxAge(24*60*60);
response.addCookie(cookie1);

Cookie ecookie1 = new Cookie("etwt", twitterUrl.getCookie("AWSELB").getValue());
ecookie1.setMaxAge(24*60*60);
response.addCookie(ecookie1);
%><%--
--%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>Peugeot AXS :: Ingrese al sitio</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="fonts/peugeot/fonts.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_website.css" />
<!--[if lt IE 9]>
	<link type="text/css" rel="stylesheet" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_ie8-fixes.css" />
<![endif]-->

<%@ include file="includes/headNotLogged.jsp" %>
<script type='text/javascript' src='./js/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_jquery.cookie.js'></script>
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
		var queryDate = '2013-01-01';
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
				'document': {required: true, digits: true},
				'firstName': {required: true},
				'lastName': {required: true},
				'email': {required: true, email: true},
				'gender': {required: true},
				'password': {required: true},
				'retypePassword': {
					equalTo: "input[name=password]"
				},
				'birthDate': {required: true}
			},
			messages: {	
				'document': {required: "<span>Ingrese el número de documento.</span>",
					digits: "<span>Ingrese solo números.<span>"},
				'firstName': {required: "<span>Ingrese el nombre.</span>"},
				'lastName': {required: "<span>Ingrese el apellido.</span>"},
				'email': {required: "<span>Ingrese el E-Mail.</span>",
					email: "<span>Ingrese un E-Mail válido.</span>"},
				'gender': {required: "<span>Seleccione el sexo.</span>"},
				'password': {required: "<span>Ingrese la clave.</span>"},
				'retypePassword': {equalTo: "<span>Las claves no coinciden</span>"},
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
				'username': {required: true, digits: true},
				'password': {required: true}
			},
			messages: {
				'username': {required: "<span>Ingrese el número de documento.</span>",
					digits: "<span>Ingrese solo números.<span>"},
				'password': {required: "<span>Ingrese la clave.</span>"}

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

		$("form[name='GeneratePasswordForm']").validate({
			errorPlacement: function(error, element) {
				error.appendTo( element.parent("div"));
			},
			rules: { 			},
			messages: {			},
			submitHandler: function() {
				<%@ include file="includes/blockUI.jspf" %>
	            $("form[name='GeneratePasswordForm']").ajaxSubmit({
	    			type: "POST",
	    			url: "./generatePassword.do",
	    			dataType: "json",
	    			success: postGeneratePassword,
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
		$( "#duplicatedErr" ).click(function() {
			$( "#registerLayer" ).fadeOut();
			forgotPassword();
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

		$( "#recoverPasswordFromPois" ).click(function() {
			$( "#parkingsNotLoggedLayer" ).fadeOut();
			forgotPassword();
			return false;
		});

		<%@ include file="includes/closeLegalesLayer.jsp" %>

		<%if ("true".equals(request.getParameter("openRegister"))) {%>
			basicRegister();
		<%}%>

		if (!$.cookie('primerAcceso')) {
			$("form[name='GeneratePasswordForm'] input[name='username']").attr('value', '');
			centerLayerWF($(window), $( "#firstAccessLayer" ), function() {$("form[name='GeneratePasswordForm'] input[name='username']").focus();});
			centerLayer($(window), $( "#centradorModalesfirstAccess" ));
			$.cookie('primerAcceso', "set", { expires: 20*365, path: "/" });
		}

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

	$("form[name='RegisterForm'] input[name='optInAccepted']").each(function(index, param){
		$(param).prop('checked', true);
	});
	
	centerLayerWF($(window), $( "#registerLayer" ), function() {$("form[name='RegisterForm'] input[name='document']").focus();});
	centerLayer($(window), $( "#centradorModalesRegister" ));
}

function login() {
	clearErrors();
	$('#loginerr').prop('innerHTML', '');
	$('#loginerr').css('display', 'none');
	$("form[name='LoginForm'] input[name='username']").attr('value', '');
	$("form[name='LoginForm'] input[name='password']").attr('value', '');
	//$("form[name='LoginForm'] input[name='username']").focus();
	centerLayerWF($(window), $( "#loginLayer" ), function() {$("form[name='LoginForm'] input[name='username']").focus();});
	centerLayer($(window), $( "#centradorModalesLogin" ));
}

function forgotPassword() {
	$('#forgotPassworderr').prop('innerHTML', '');
	$('#forgotPassworderr').css('display', 'none');
	$("form[name='RequestResetPasswordForm'] input[name='username']").attr('value', '');
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
			if (key == 'profile.document' && value.indexOf('Ya estás registrado en nuestra base') != -1) {
				var obj = document.getElementById('err.duplicated');
				var objParent = document.getElementById('duplicatedErr');
				if (objParent) {
					objParent.style.display='block';
				}
				obj.innerHTML = value;
			} else {
				var obj = document.getElementById('err.' + key);
				if (obj) {
					var objParent = document.getElementById('p.' + key);
					if (objParent) {
						objParent.style.display='block';
					}
					obj.innerHTML = value;
				}
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
	if (data.result == 'OK') {
		$( "#forgotPasswordLayer" ).fadeOut();
		centerLayer($(window), $( "#forgotPasswordEmailSentLayer" ));
		centerLayer($(window), $( "#centradorModalesFPES" ));
	} else {
		if (data.result == '404') {
			$('#forgotPassworderr').prop('innerHTML', 'El DNI no coincide con un usuario de Lo-Jack');
			$('#forgotPassworderr').css('display', 'block');
		} else {
			$('#forgotPassworderr').prop('innerHTML', 'Ha ocurrido un error. Por favor intentelo nuevamente.');
			$('#forgotPassworderr').css('display', 'block');
		}
	}
}

function postGeneratePassword(data) {
	<%@ include file="includes/unblockUI.jspf" %>
	if (data.result == 'OK') {
		$( "#firstAccessLayer" ).fadeOut();
		centerLayer($(window), $( "#firstAccessEmailSentLayer" ));
		centerLayer($(window), $( "#centradorModalesfirstAccessEmailSent" ));
	} else {
		if (data.result == '404') {
			$('#firstacesserr').prop('innerHTML', 'El DNI no coincide con un usuario de Lo-Jack');
			$('#firstacesserr').css('display', 'block');
		} else {
			$('#firstacesserr').prop('innerHTML', 'Ha ocurrido un error. Por favor intentelo nuevamente.');
			$('#firstacesserr').css('display', 'block');
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

</head>
<%@ include file="includes/version.jspf" %>
<body>
<% if (usingMobile || isAndroid) { %>
	<div style="background:#99ECD6; line-height:20px; text-align:center; color:#000;">android or mobile</div>
<% } %>
<!-- WEBSITE CONTENT -->
<%@ include file="includes/header.jspf" %>
<%
com.tdil.web.breadcrum.Breadcrum breadcrums = new com.tdil.web.breadcrum.Breadcrum()
	.titles("Inicio","Peugeot App")
	.pages("","");
%>
<%@ include file="includes/page_title.jspf" %>
<%@ include file="includes/wheel_menu.jspf" %>
<%@ include file="includes/copyright.jspf" %>
<%@ include file="includes/footer_web.jspf" %>

<!-- ALL LAYERS -->
<%@ include file="includes/layer_parking_not_logged.jspf" %>
<%@ include file="includes/layer_register.jspf" %>
<%@ include file="includes/layer_login.jspf" %>
<%@ include file="includes/layer_password_recovery.jspf" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/layer_contact.jspf" %>
<%@ include file="includes/layer_legales.jspf" %>
<%@ include file="includes/layer_first_access.jspf" %>

</body>
</html>
<% } %>