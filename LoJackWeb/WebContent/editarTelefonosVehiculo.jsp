<%@ include file="includes/tryModal.jspf" %><%--
--%><%@page import="com.tdil.lojack.struts.forms.prevent.SelectVehiclesForm"%><%--
--%><%@page import="com.tdil.lojack.prevent.model.SpeedLimit"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.beans.SpeedSelectionBean"%><%--
--%><%@page import="com.tdil.lojack.prevent.model.Vehicle"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.prevent.VehiclesSpeedLimitForm"%><%--
--%><%@page import="com.tdil.lojack.utils.SystemPropertiesKeys"%><%--
--%><%@page import="com.tdil.lojack.utils.SystemPropertyUtils"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.CameraForm"%><%--
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
<div id="xContainer"><button id="closeEditVehicleForPhoneLayer">X</button></div>
<% SelectVehiclesForm selectVehiclesForm = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForPhonesForm");%>
<h3>Editar teléfonos del vehículo: <span class="plateHighltd"><%=selectVehiclesForm.getSelected().getDescription() %></span></h3>
<div class="alert alert-error" id="savephones" style="display: none;"></div>
<html:form method="POST" action="/saveVehiculesPhones">
	<div id="tableStyle">
		<fieldset class="tableHeader">
			<label class="w1">Acción</label>
			<label class="w3">Teléfono</label>
		</fieldset>
		<fieldset>
			<label class="w1">Línea 1</label>
			<label class="w3"></label><html:text name="SelectVehiclesForPhonesForm" property="alertPhoneCode" styleClass="areacode" /><html:text name="SelectVehiclesForPhonesForm" property="alertPhone" styleClass="phonenum" />
		</fieldset>
		<fieldset>
			<label class="w1">Línea 2</label>
			<label class="w3"></label><html:text name="SelectVehiclesForPhonesForm" property="crashPhoneCode" styleClass="areacode" /><html:text name="SelectVehiclesForPhonesForm" property="crashPhone" styleClass="phonenum" />
		</fieldset>
	</div>
	<fieldset><button id="submitregister" class="indexButtonBase">Grabar</button></fieldset>
</html:form>
<%@ include file="includes/catchModal.jspf" %>