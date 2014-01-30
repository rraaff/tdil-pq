<%@ include file="includes/tryModal.jspf" %><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.ChangePasswordForm"%><%--
--%><%@page import="com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.EditProfileForm"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertiesKeys"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertyUtils"%><%--
--%><%@page import="com.tdil.ljpeugeot.CameraForm"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><%@ page info="home"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><script>

		$("form[name='PreventLoginForm']").validate({
			errorPlacement: function(error, element) {
				error.appendTo( element.parent("div"));
			},
			rules: { 			},
			messages: {			},
			submitHandler: function() {
				<%@ include file="includes/blockUI.jspf" %>
				clearErrors();
	            $("form[name='PreventLoginForm']").ajaxSubmit({
	    			type: "POST",
	    			url: "./preventLogin.do",
	    			dataType: "json",
	    			success: postLoginPrevent,
	    			<%@ include file="includes/openErrorLayerJS.jspf" %>
	    			});
	        }
		});

		$( "#closeLoginPreventLayer" ).click(function() {
			$( "#loginPreventLayer" ).fadeOut();
		});
		
		

function clearErrors() {
	$("div[id^='err.']").each(function(index, valor) {
		$(valor).prop('innerHTML','');
	});
}


function postLoginPrevent(data) {
	<%@ include file="includes/unblockUI.jspf" %>
	if (data.result == 'OK') {
		$( "#loginPreventLayer" ).fadeOut();
		window.location.replace('./productoPrevent.jsp');
	} else {
		$('#loginpreventerr').prop('innerHTML', 'La contraseña es incorrecta');
		$('#loginpreventerr').css('display', 'block');
	}
}

</script>
<div id="loginPreventContentLayer" class="layerOnTop" style="z-index: 1500;">
	<div id="centradorModalesLoginPrevent" class="loginLayerStyles">
		<div class="loginLayerContent">
			<div id="xContainer"><button id="closeLoginPreventLayer">X</button></div>
			<h3>Ingresar a prevent</h3>
			<html:form method="POST" action="/preventLogin">
				<fieldset>
					<label>Clave</label>
					<html:password name="PreventLoginForm" property="password" />
				</fieldset>
				<div class="alert alert-error" id="loginpreventerr" style="display: none;"></div>
				<fieldset>
					<button type="submit" class="indexButtonBase">Ingresar</button>
				</fieldset>
			</html:form>
		</div>
	</div>
</div>
<%@ include file="includes/catchModal.jspf" %>