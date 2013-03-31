<%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="includes/checkThalamusUp.jspf" %><!--
--><%@ include file="includes/userLogged.jspf" %><!--
--><%@ include file="includes/mustBeLogged.jspf" %>
Configuracion de las alertas 

<html:form method="POST" action="/saveLightAlertConf">
	<div id="errorSavingConf" style="display: none;">Ha ocurrido un error</div>
	Activacion/Desactivacion:<html:checkbox name="LightAlertConfigurationForm" property="activateDeactivate" styleClass="normalField width120"/>
	Activacion/Desactivacion por otros:<html:checkbox name="LightAlertConfigurationForm" property="activateDeactivateByOther" styleClass="normalField width120"/>
	Corte de energia:<html:checkbox name="LightAlertConfigurationForm" property="powerSupplyLost" styleClass="normalField width120"/>
	<input type="submit" id="submitforgotPassword" value="Submit"><input type="button" id="closeConfAlertLayer" value="Cancel">
</html:form>
<script>
$("form[name='LightAlertConfigurationForm']").validate({
	errorPlacement: function(error, element) {
		error.appendTo( element.parent("div"));
	},
	rules: { 			},
	messages: {			},
	submitHandler: function() {
		$( "#errorSavingConf" ).css( 'display', 'none' )
        $("form[name='LightAlertConfigurationForm']").ajaxSubmit({
			type: "POST",
			url: "./saveLightAlertConf.do",
			dataType: "json",
			success: function(data) {
				if (data.result == 'OK') {
					$( "#confAlertLayer" ).fadeOut();
					centerLayer($(window), $( "#confSavedLayer" ));
				} else {
					$( "#errorSavingConf" ).css( 'display', 'block' )
				}
			}
		});
    }
});

$( "#closeConfAlertLayer" ).click(function() {
	$( "#confAlertLayer" ).fadeOut();
});
</script>