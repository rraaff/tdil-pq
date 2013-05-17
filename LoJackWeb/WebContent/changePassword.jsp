<%@page import="com.tdil.lojack.struts.forms.ChangePasswordForm"%>
<%@page import="com.tdil.lojack.web.LoJackErrorFormatter"%>
<%@page import="com.tdil.lojack.struts.forms.EditProfileForm"%>
<%@page import="com.tdil.lojack.utils.SystemPropertiesKeys"%>
<%@page import="com.tdil.lojack.utils.SystemPropertyUtils"%>
<%@page import="com.tdil.lojack.struts.forms.CameraForm"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><!--
--><%@ page info="home"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<script>

		$("form[name='ChangePasswordForm']").validate({
			errorPlacement: function(error, element) {
				error.appendTo( element.parent("div"));
			},
			rules: { 			},
			messages: {			},
			submitHandler: function() {
				clearErrors();
	            $("form[name='ChangePasswordForm']").ajaxSubmit({
	    			type: "POST",
	    			url: "./changePassword.do",
	    			dataType: "json",
	    			success: postChangePassword
	    			});
	        }
		});

		$( "#closechangepasswordLayer" ).click(function() {
			$( "#changePasswordLayer" ).fadeOut();
		});
		
		

function clearErrors() {
	$("div[id^='err.']").each(function(index, valor) {
		$(valor).prop('innerHTML','');
	});
}


function postChangePassword(data) {
	if (data.result == 'OK') {
		$( "#changePasswordLayer" ).fadeOut();
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
<div id="changePassLayer" class="layerOnTop" style="z-index: 1500;">
	<div class="loginLayerStyles">
		<div class="loginLayerContent" style="height:250px; padding:0px 35px 35px 35px;">
			<div id="xContainer"><button id="closechangepasswordLayer" style="margin-left:-40px;">X</button></div>
			<h3 style="padding-bottom:20px;">Cambiar clave</h3>
			<html:form method="POST" action="/changePassword">
				<form>
					<fieldset>
						<label>Clave actual</label>
						<html:password name="ChangePasswordForm" property="oldPassword" />
						<div id="err.oldPassword"></div>
					</fieldset>
					<fieldset>
						<label>Nueva clave</label>
						<html:password name="ChangePasswordForm" property="newPassword" />
						<div id="err.newPassword"></div>
					</fieldset>
					<fieldset>
						<label>Repetir clave</label>
						<html:password name="ChangePasswordForm" property="confirmNewPassword" />
						<div id="err.confirmPassword"></div>
					</fieldset>
				</form>
				<button type="submit" class="indexButtonBase">Modificar</button>
			</html:form>
		</div>
	</div>
</div>