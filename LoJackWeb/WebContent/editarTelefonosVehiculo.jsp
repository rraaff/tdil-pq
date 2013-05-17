<%@page import="com.tdil.lojack.struts.forms.prevent.SelectVehiclesForm"%>
<%@page import="com.tdil.lojack.prevent.model.SpeedLimit"%>
<%@page import="com.tdil.lojack.struts.forms.beans.SpeedSelectionBean"%>
<%@page import="com.tdil.lojack.prevent.model.Vehicle"%>
<%@page import="com.tdil.lojack.struts.forms.prevent.VehiclesSpeedLimitForm"%>
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

$("form[name='SelectVehiclesForPhonesForm']").validate({
	errorPlacement: function(error, element) {
		error.appendTo( element.parent("div"));
	},
	rules: { 			},
	messages: {			},
	submitHandler: function() {
		clearErrors();
           $("form[name='SelectVehiclesForPhonesForm']").ajaxSubmit({
   			type: "POST",
   			url: "./saveVehiculesPhones.do",
   			dataType: "json",
   			success: postSaveVehiculesPhones
   			});
       }
});

function postSaveVehiculesPhones(data) {
	if (data.result == 'OK') {
		$( "#editVehiclesPhonesLayer" ).fadeOut();
	} else {
		$.each(data, function(key, value) {
			var obj = document.getElementById('err.' + key);
			if (obj) {
				obj.innerHTML = value;
			}
        });
	}
}

$( "#closeEditVehicleForPhoneLayer" ).click(function() {
	$( "#editVehiclesPhonesLayer" ).fadeOut();
});

</script>
<div id="changePassLayer" class="layerOnTop" style="z-index: 1500;">
<div class="registerLayerStyles editProfileLayer">
		<div class="registerLayerContent">
			<div id="xContainer"><button id="closeEditVehicleForPhoneLayer" style="margin-left:110px;">X</button></div>
			<% SelectVehiclesForm selectVehiclesForm = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForPhonesForm");%>
			Vehiculo: <%=selectVehiclesForm.getSelected().getDescription() %><br>
			<html:form method="POST" action="/saveVehiculesPhones">
				<table>
					<tr>
						<td>Alertas</td>
						<td><html:text name="SelectVehiclesForPhonesForm" property="alertPhone" styleClass="normalField width120"/></td>
					</tr>
					<tr>
						<td>Choque</td>
						<td><html:text name="SelectVehiclesForPhonesForm" property="crashPhone" styleClass="normalField width120"/></td>
					</tr>
					<tr>
						<td>Otros</td>
						<td><html:text name="SelectVehiclesForPhonesForm" property="otherPhone" styleClass="normalField width120"/></td>
					</tr>
				</table>
				<div class="myRow">
					<div class="myLabel width100per" align="center"><input type="submit" id="submitregister" value="Submit"></div>
				</div>
			</html:form>
			</div>
		</div>
	</div>
</div>