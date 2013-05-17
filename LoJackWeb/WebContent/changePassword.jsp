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
<div class="registerLayerStyles editProfileLayer">
		<div class="registerLayerContent">
			<div id="xContainer"><button id="closechangepasswordLayer" style="margin-left:110px;">X</button></div>
				<html:form method="POST" action="/changePassword">
					<div class="myRow">
						<div class="myLabel width120">Clave actual</div>
						<div class="myLabel width270"><html:password name="ChangePasswordForm" property="oldPassword" styleClass="normalField width250"/></div>
						<div id="err.oldPassword"></div>
					</div>
					<div class="myRow">
						<div class="myLabel width120">Nueva clave</div>
						<div class="myLabel width270"><html:password name="ChangePasswordForm" property="newPassword" styleClass="normalField width250"/></div>
						<div id="err.newPassword"></div>
					</div>
					<div class="myRow">
						<div class="myLabel width120">Repetir clave</div>
						<div class="myLabel width270"><html:password name="ChangePasswordForm" property="confirmNewPassword" styleClass="normalField width250"/></div>
						<div id="err.confirmPassword"></div>
					</div>	
					<div class="myRow" align="center"><input type="submit" value="Grabar" /></div>
			
				</html:form>
			</div>
		</div>
	</div>
</div>