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

$("form[name='VehiclesSpeedLimitForm']").validate({
	errorPlacement: function(error, element) {
		error.appendTo( element.parent("div"));
	},
	rules: { 			},
	messages: {			},
	submitHandler: function() {
		<%@ include file="includes/blockUI.jspf" %>
		clearErrors();
           $("form[name='VehiclesSpeedLimitForm']").ajaxSubmit({
   			type: "POST",
   			url: "./saveVehiculesSpeedLimits.do",
   			dataType: "json",
   			success: postSaveSpeedLimits
   			});
       }
});

$( "#closeeditMaxSpeedLayer" ).click(function() {
	$( "#editMaxSpeedLayer" ).fadeOut();
});
		

function clearErrors() {
	$("div[id^='err.']").each(function(index, valor) {
		$(valor).prop('innerHTML','');
	});
}

function postSaveSpeedLimits(data) {
	<%@ include file="includes/unblockUI.jspf" %>
	if (data.result == 'OK') {
		$( "#editMaxSpeedLayer" ).fadeOut();
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
			<div id="xContainer"><button id="closeeditMaxSpeedLayer" style="margin-left:60px;">X</button></div>
			<h3>Determinar velocidades máximas</h3>
			<html:form method="POST" action="/saveVehiculesSpeedLimits">
				<div id="tableStyle" style="height:220px;">
						<fieldset class="tableHeader">
							<label class="w1">Patente</label>
							<label class="w3">Velocidad máxima</label>
						</fieldset>
						<logic:iterate id="selectedSpeedLimit" name="VehiclesSpeedLimitForm" property="speedLimits">
							<fieldset>
								<label class="w1"><bean:write name="selectedSpeedLimit" property="vehicle.description" /></label>
								<html:select name="selectedSpeedLimit" property="speedLimitId" indexed="true">
									<option	value="">-</option>
									<% SpeedSelectionBean ssb = (SpeedSelectionBean)selectedSpeedLimit;
										SpeedLimit selected = ssb.getLimits().getActiveSpeedLimit();
										for (SpeedLimit sl : ssb.getLimits().getLimits()) { %>	
											<option	<%=	selected == null ? "" : (selected.getId().equals(sl.getId())) ? "selected" : ""%>
											value="<%=sl.getId()%>">
											<%=sl.getDescription()%></option>
									<% } %>
								</html:select>
							</fieldset>
						</logic:iterate>
				</div>
				<fieldset><button id="submitregister" class="indexButtonBase">Grabar</button></fieldset>
			</html:form>
		</div>
	</div>
</div>