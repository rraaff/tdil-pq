<%@page import="com.tdil.ljpeugeot.struts.forms.prevent.SelectVehiclesForm"%>
<%@ include file="includes/tryModal.jspf" %><%--
--%><%@page import="com.tdil.ljpeugeot.prevent.model.SecureZone"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.beans.SecureZoneSelectionBean"%><%--
--%><%@page import="com.tdil.ljpeugeot.prevent.model.SpeedLimit"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.beans.SpeedSelectionBean"%><%--
--%><%@page import="com.tdil.ljpeugeot.prevent.model.Vehicle"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.prevent.VehiclesSpeedLimitForm"%><%--
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

$("form[name='SelectVehiclesForSecureZoneForm']").validate({
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
           $("form[name='SelectVehiclesForSecureZoneForm']").ajaxSubmit({
   			type: "POST",
   			url: "./saveVehiculeSecureZones.do",
   			dataType: "json",
   			success: postSaveSpeedLimits,
   			<%@ include file="includes/openErrorLayerJS.jspf" %>
   			});
       }
});

$( "#closeeditSecureZonesLayer" ).click(function() {
	$( "#editSecureZoneLayer" ).fadeOut();
});
		

function clearErrors() {
	$("div[id^='err.']").each(function(index, valor) {
		$(valor).prop('innerHTML','');
	});
}

function postSaveSpeedLimits(data) {
	<%@ include file="includes/unblockUI.jspf" %>
	if (data.result == 'OK') {
		$( "#editSecureZoneLayer" ).fadeOut();
	} else {
		$('#savesz').prop('innerHTML', 'Ha ocurrido un error');
		$('#savesz').css('display', 'block');
	}
}

</script>
		<section class="modal_header">
			<h2>Zonas seguras</h2>
			<h3>Seleccione la zona segura</h3>
			<button class="close" id="closeeditSecureZonesLayer">Cerrar <span></span></button>
		</section>
		<div class="alert alert-error" id="savesz" style="display: none;"></div>
		<section class="modal_content apps_listing">
			<span class="modal_subtitle">Vehículo: <bean:write name="SelectVehiclesForSecureZoneForm" property="selected.description" /></span>

			<% SelectVehiclesForm  selectVehiclesForSpeedForm = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForSecureZoneForm"); %>
			<div class="alert alert-error" id="savesz" style="display: none;"></div>
			<html:form method="POST" action="/saveVehiculeSecureZones" styleClass="modal_wrapper">
				<fieldset class="width100per padding25">
					<html:select name="SelectVehiclesForSecureZoneForm" property="secureZoneSelectionBean.secureZoneId" styleClass="full">
						<option	value="">-</option>
						<% SecureZoneSelectionBean ssb = ((SelectVehiclesForm)selectVehiclesForSpeedForm).getSecureZoneSelectionBean();
							SecureZone selected = ssb.getZones().getActiveZone();
							for (SecureZone sl : ssb.getZones().getSecureZones()) { %>	
								<option	<%=	selected == null ? "" : (selected.getId().equals(sl.getId())) ? "selected" : ""%>
								value="<%=sl.getId()%>">
								<%=sl.getDescription()%></option>
						<% } %>
					</html:select>
				</fieldset>
				<fieldset class="button_bar pOnlyTop25">
					<button class="botton_ahead" id="submitregister" type="submit">Aplicar<span></span></button>
				</fieldset>
			</html:form>

		</section>
<%@ include file="includes/catchModal.jspf" %>