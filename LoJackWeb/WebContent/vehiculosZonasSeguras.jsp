<%@page import="com.tdil.lojack.prevent.model.SecureZone"%>
<%@page import="com.tdil.lojack.struts.forms.beans.SecureZoneSelectionBean"%>
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

$("form[name='VehiclesSecureZoneForm']").validate({
	errorPlacement: function(error, element) {
		error.appendTo( element.parent("div"));
	},
	rules: { 			},
	messages: {			},
	submitHandler: function() {
		<%@ include file="includes/blockUI.jspf" %>
		clearErrors();
		$('#savesz').prop('innerHTML', '');
		$('#savesz').css('display', 'none');
           $("form[name='VehiclesSecureZoneForm']").ajaxSubmit({
   			type: "POST",
   			url: "./saveVehiculesSecureZones.do",
   			dataType: "json",
   			success: postSaveSpeedLimits,
   			<%@ include file="includes/openErrorLayerJS.jspf" %>
   			});
       }
});

$( "#closeeditSecureZonesLayer" ).click(function() {
	$( "#editSecureZonesLayer" ).fadeOut();
});
		

function clearErrors() {
	$("div[id^='err.']").each(function(index, valor) {
		$(valor).prop('innerHTML','');
	});
}

function postSaveSpeedLimits(data) {
	<%@ include file="includes/unblockUI.jspf" %>
	if (data.result == 'OK') {
		$( "#editSecureZonesLayer" ).fadeOut();
	} else {
		$('#savesz').prop('innerHTML', 'Ha ocurrido un error');
		$('#savesz').css('display', 'block');
	}
}

</script>
<div id="changePassLayer" class="layerOnTop" style="z-index: 1500;">
	<div class="registerLayerStyles editProfileLayer">
		<div class="registerLayerContent">
			<div id="xContainer"><button id="closeeditSecureZonesLayer" style="margin-left:60px;">X</button></div>
			<h3>Zonas seguras</h3>
			<div class="alert alert-error" id="savesz" style="display: none;"></div>
			<html:form method="POST" action="/saveVehiculesSecureZones">
				<div id="tableStyle" style="height:220px;">
						<fieldset class="tableHeader">
							<label class="w1">Acción</label>
							<label class="w3">Teléfono</label>
						</fieldset>
						<logic:iterate id="selectedSecureZone" name="VehiclesSecureZoneForm" property="secureZones">
							<fieldset>
								<label class="w1"><bean:write name="selectedSecureZone" property="vehicle.description" /></label>
								<label class="w3"><html:select name="selectedSecureZone" property="secureZoneId" indexed="true">
									<option	value="">-</option>
									<% SecureZoneSelectionBean ssb = (SecureZoneSelectionBean)selectedSecureZone;
										SecureZone selected = ssb.getZones().getActiveZone();
										for (SecureZone sl : ssb.getZones().getSecureZones()) { %>	
											<option	<%=	selected == null ? "" : (selected.getId().equals(sl.getId())) ? "selected" : ""%>
											value="<%=sl.getId()%>">
											<%=sl.getDescription()%></option>
									<% } %>
								</html:select></label>
							</fieldset>
						</logic:iterate>
				</div>
				<fieldset><button id="submitregister" class="indexButtonBase" >Aplicar</button></fieldset>
			</html:form>
		</div>
	</div>
</div>