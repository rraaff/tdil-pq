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
				<%@ include file="includes/blockUI.jspf" %>
				clearErrors();
	            $("form[name='ChangePasswordForm']").ajaxSubmit({
	    			type: "POST",
	    			url: "./changePassword.do",
	    			dataType: "json",
	    			success: postChangePassword,
	    			<%@ include file="includes/openErrorLayerJS.jspf" %>
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
	<%@ include file="includes/unblockUI.jspf" %>
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
<div id="changePassLayer" class="layerOnTop" style="z-index: 1500; top:0; left:0;">
	<div class="loginLayerStyles">
		<div class="loginLayerContent changePasswordLayerContent">
			<div id="xContainer"><button id="closechangepasswordLayer">X</button></div>
			<h3 style="padding-bottom:20px;">Cambiar clave</h3>
			<html:form method="POST" action="/changePassword">
					<fieldset>
						<label class="width100">Clave actual</label>
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
					<fieldset>
						<button type="submit" class="indexButtonBase">Modificar</button>
					</fieldset>
			</html:form>
		</div>
	</div>
</div>