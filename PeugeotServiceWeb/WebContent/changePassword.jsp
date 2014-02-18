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
	<div id="centradorModalesChangePass" class="loginLayerStyles">
		<div class="changePasswordLayerContent">
			<div id="xContainer"><button id="closechangepasswordLayer">X</button></div>
			<h3>Cambiar clave</h3>
			<html:form method="POST" action="/changePassword">
				<fieldset>
					<label>Clave actual</label>
					<html:password name="ChangePasswordForm" property="oldPassword" />
				</fieldset>
				<div id="err.oldPassword" class="errorText textCenter"></div>
				<fieldset>
					<label>Nueva clave</label>
					<html:password name="ChangePasswordForm" property="newPassword" />
				</fieldset>
				<div id="err.newPassword" class="errorText textCenter"></div>
				<fieldset>
					<label>Repetir clave</label>
					<html:password name="ChangePasswordForm" property="confirmNewPassword" />
				</fieldset>
				<div id="err.confirmPassword" class="errorText textCenter"></div>
				<fieldset>
					<button type="submit" class="indexButtonBase">Modificar</button>
				</fieldset>
			</html:form>
		</div>
	</div>
</div>
<%@ include file="includes/catchModal.jspf" %>