<%@ include file="includes/tryModal.jspf" %><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.prevent.SelectVehiclesForm"%><%--
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

$("form[name='SelectVehiclesForPhonesForm']").validate({
	errorPlacement: function(error, element) {
		error.appendTo( element.parent("div"));
	},
	rules: { 			},
	messages: {			},
	submitHandler: function() {
		<%@ include file="includes/blockUI.jspf" %>
		clearErrors();
		$('#savephones').prop('innerHTML', '');
		$('#savephones').css('display', 'none');
           $("form[name='SelectVehiclesForPhonesForm']").ajaxSubmit({
   			type: "POST",
   			url: "./saveVehiculesPhones.do",
   			dataType: "json",
   			success: postSaveVehiculesPhones,
   			<%@ include file="includes/openErrorLayerJS.jspf" %>
   			});
       }
});

function clearErrors() {
	$("div[id^='err.']").each(function(index, valor) {
		$(valor).prop('innerHTML','');
	});
}

function postSaveVehiculesPhones(data) {
	<%@ include file="includes/unblockUI.jspf" %>
	if (data.result == 'OK') {
		$( "#editVehiclesPhonesLayer" ).fadeOut();
	} else {
		$('#savephones').prop('innerHTML', 'Ha ocurrido un error');
		$('#savephones').css('display', 'block');
	}
}

$( "#closeEditVehicleForPhoneLayer" ).click(function() {
	$( "#editVehiclesPhonesLayer" ).fadeOut();
});

</script>
		<section class="modal_header">
			<h2>Editar teléfonos</h2>
			<h3>Complete los datos</h3>
			<button class="close" id="closeEditVehicleForPhoneLayer">Cerrar <span></span></button>
		</section>
		<div class="alert alert-error" id="savephones" style="display: none;"></div>
		<section class="modal_content apps_listing">
			<% SelectVehiclesForm selectVehiclesForm = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForPhonesForm");%>
			<span class="modal_subtitle">Vehículo: <%=selectVehiclesForm.getSelected().getDescription() %></span>

			<% SelectVehiclesForm  selectVehiclesForSpeedForm = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForSecureZoneForm"); %>
			<div class="alert alert-error" id="savesz" style="display: none;"></div>
			<html:form method="POST" action="/saveVehiculesPhones" styleClass="modal_wrapper">
				<fieldset class="width100per">
					<label class="min-width75">Línea 1</label>
					<html:text name="SelectVehiclesForPhonesForm" property="alertPhoneCode" styleClass="width30" />
					<html:text name="SelectVehiclesForPhonesForm" property="alertPhone" styleClass="width100" />
				</fieldset>
				<fieldset class="width100per">
					<label><span class="sample">Ejemplo:</span></label>
					<label><span class="sample">54 - 1141234568</span></label>
				</fieldset>
				<fieldset class="width100per">
					<label class="min-width75">Línea 2</label>
					<html:text name="SelectVehiclesForPhonesForm" property="crashPhoneCode" styleClass="width30" />
					<html:text name="SelectVehiclesForPhonesForm" property="crashPhone" styleClass="width100" />
				</fieldset>
				<fieldset class="width100per">
					<label><span class="sample">Ejemplo:</span></label>
					<label><span class="sample">54 - 1141234567</span></label>
				</fieldset>
				<fieldset class="button_bar pOnlyTop25">
					<button class="botton_ahead" id="submitregister" type="submit">Aplicar<span></span></button>
				</fieldset>
			</html:form>

		</section>

<%@ include file="includes/catchModal.jspf" %>