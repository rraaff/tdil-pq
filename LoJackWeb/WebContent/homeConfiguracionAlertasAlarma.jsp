<%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="includes/checkThalamusUp.jspf" %><!--
--><%@ include file="includes/userLogged.jspf" %>
Configuracion de las alertas 

<html:form method="POST" action="/saveAlarmAlertConf">
	Activacion/Desactivacion:<html:checkbox name="AlarmAlertConfigurationForm" property="activateDeactivate" styleClass="normalField width120"/>
	Activacion/Desactivacion por otros:<html:checkbox name="AlarmAlertConfigurationForm" property="activateDeactivateByOther" styleClass="normalField width120"/>
	Corte de energia:<html:checkbox name="AlarmAlertConfigurationForm" property="powerSupplyLost" styleClass="normalField width120"/>
	Disparado de alarma:<html:checkbox name="AlarmAlertConfigurationForm" property="alarmActivation" styleClass="normalField width120"/>
	<input type="submit" id="submitforgotPassword" value="Submit"><input type="button" id="closeConfAlertLayer" value="Cancel">
</html:form>
<script>
alert(1);
$("form[name='AlarmAlertConfigurationForm']").validate({
	errorPlacement: function(error, element) {
		error.appendTo( element.parent("div"));
	},
	rules: { 			},
	messages: {			},
	submitHandler: function() {
		//clearErrors();
        $("form[name='AlarmAlertConfigurationForm']").ajaxSubmit({
			type: "POST",
			url: "./saveAlarmAlertConf.do",
			dataType: "json",
			success: function(data) {
				alert(data);
				if (data.result == 'OK') {
					alert('ok');
					$( "#confAlertLayer" ).fadeOut();
					// muestro layer de ok
					//window.location.replace('./home.jsp');
				} else {
					alert('error');
					// muestro layer de error
					$( "#loginLayer" ).fadeOut();
					centerLayer($(window), $( "#loginInvalidLayer" ));
				}
			}
		});
    }
});

$( "#closeConfAlertLayer" ).click(function() {
	$( "#confAlertLayer" ).fadeOut();
});
</script>