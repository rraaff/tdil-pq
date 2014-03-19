<%@ include file="includes/tryModal.jspf" %><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.ChangePasswordForm"%><%--
--%><%@page import="com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.EditProfileForm"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertiesKeys"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertyUtils"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><%@ page info="home"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><script>

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
		
		$( "#linkBackchangePasswordLayer" ).click(function() {
			$( "#changePasswordLayer" ).fadeOut();
		});
		
		

function clearErrors() {
	$('#changePassErr').prop('innerHTML', '');
	$('#changePassErr').css('display', 'none');
	$("div[id^='err.']").each(function(index, valor) {
		$(valor).prop('innerHTML','');
	});
}


function postChangePassword(data) {
	<%@ include file="includes/unblockUI.jspf" %>
	if (data.result == 'OK') {
		$( "#changePasswordLayer" ).fadeOut();
	} else {
		if(data.general) {
			$('#changePassErr').prop('innerHTML', data.general);
			$('#changePassErr').css('display', 'block');
		}
		$.each(data, function(key, value) {
			var obj = document.getElementById('err.' + key);
			if (obj) {
				obj.innerHTML = value;
			}
        });
	}
}

</script>
<div id="changePassLayer" class="layerOnTop" style="z-index:1500; top:0; left:0;">
	<div id="centradorModalesChangePass" class="layerModal width320">
		<section class="modal_header">
			<h2>Cambiar mi clave</h2>
			<button class="close" id="closechangepasswordLayer">Cerrar <span></span></button>
		</section>
		<section class="modal_content">
			<span class="modal_subtitle">Complete los datos requeridos</span>
			<div class="alert alert-error" id="changePassErr" style="display: none;"></div>
			<html:form method="POST" action="/changePassword" styleClass="modal_wrapper">
				<fieldset class="width100per">
					<label>Clave actual</label>
					<html:password name="ChangePasswordForm" property="oldPassword" />
				</fieldset>
				<div id="err.oldPassword" class="errorText textCenter"></div>
				<fieldset class="width100per">
					<label>Nueva clave</label>
					<html:password name="ChangePasswordForm" property="newPassword" />
				</fieldset>
				<div id="err.newpassword" class="errorText textCenter"></div>
				<fieldset class="width100per">
					<label>Repetir clave</label>
					<html:password name="ChangePasswordForm" property="confirmNewPassword" />
				</fieldset>
				<div id="err.confirmPassword" class="errorText textCenter"></div>
				<fieldset class="button_bar pOnlyTop25">
					<button class="botton_ahead" type="submit">Modificar<span></span></button>
				</fieldset>
			</html:form>
		</div>
	</div>
</div>
<%@ include file="includes/catchModal.jspf" %>