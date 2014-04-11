<%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="includes/tryModal.jspf" %><%--
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
--%>
<% SelectVehiclesForm selectVehiclesForm = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForHistoricPath");%>
		<section class="modal_header">
			<h2>Seleccionar vehículos</h2>
			<button class="close" id="closeSelectVehicleForHPLayer">Cerrar <span></span></button>
		</section>
		<div class="alert alert-error" id="searchHPerr" style="display: none;"></div>
		<section class="modal_content apps_listing">
			<html:form method="POST" action="/viewHistoricPath" styleClass="modal_wrapper">
				<html:radio name="SelectVehiclesForHistoricPath" property="historicPathLimit" onclick="changeHPSelection()" value="TODAY">HOY</html:radio><br>
				<html:radio name="SelectVehiclesForHistoricPath" property="historicPathLimit" onclick="changeHPSelection()" value="YESTERDAY">AYER</html:radio><br>
				<html:radio name="SelectVehiclesForHistoricPath" property="historicPathLimit" onclick="changeHPSelection()" value="FREE">Entre</html:radio>
				<html:text name="SelectVehiclesForHistoricPath" property="dateStart"/> 
				<html:text name="SelectVehiclesForHistoricPath" property="dateEnd"/> 
				<fieldset class="width100per">
					<html:select name="SelectVehiclesForHistoricPath" property="selectedVehicleId">
						<option	value="">-</option>
						<% for (com.tdil.ljpeugeot.prevent.model.Vehicle vehicle : selectVehiclesForm.getVehicles()) { %>
								<option	value="<%=vehicle.getId()%>">
								<%=vehicle.getDescription()%></option>
						<% } %>
					</html:select>
				</fieldset>
				<fieldset class="button_bar pOnlyTop25">
					<button class="botton_ahead" id="submitregister" type="submit">Aplicar<span></span></button>
				</fieldset>
			</html:form>
		</section>
		
<script>

function changeHPSelection() {
	var selectedHP = $('input[name=historicPathLimit]:checked').val();
	if (selectedHP != 'FREE') {
		$("form[name='SelectVehiclesForHistoricPath'] input[name='dateStart']").prop('disabled', true);
		$("form[name='SelectVehiclesForHistoricPath'] input[name='dateEnd']").prop('disabled', true);
	} else {
		$("form[name='SelectVehiclesForHistoricPath'] input[name='dateStart']").prop('disabled', false);
		$("form[name='SelectVehiclesForHistoricPath'] input[name='dateEnd']").prop('disabled', false);
	}
}

$( "#closeSelectVehicleForHPLayer" ).click(function() {
	$( "#selectVehiclesForHPLayer" ).fadeOut();
});

$("input[name=dateStart]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
	changeYear: true, minDate: "-5Y", maxDate: "+0D", yearRange: '-5:+0'});
$("input[name=dateEnd]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
	changeYear: true, minDate: "-5Y", maxDate: "+0D", yearRange: '-5:+0'});

$("form[name='SelectVehiclesForHistoricPath']").validate({
	errorPlacement: function(error, element) {
		error.appendTo( element.parent("fieldset").next("div"));
	},
	submitHandler: function() {
		<%@ include file="includes/blockUI.jspf" %>
		$('#searchHPerr').prop('innerHTML', '');
		$('#searchHPerr').css('display', 'none');
        $("form[name='SelectVehiclesForHistoricPath']").ajaxSubmit({
			type: "POST",
			url: "./viewHistoricPath.do",
			dataType: "json",
			success: postSearch,
			<%@ include file="includes/openErrorLayerJS.jspf" %>
			});
    }
});

function postSearch(data) {
	<%@ include file="includes/unblockUI.jspf" %>
	if (data.result == 'OK') {
		alert('OK');
	} else {
		$('#searchHPerr').prop('innerHTML', 'Ha ocurrido un error');
		$('#searchHPerr').css('display', 'block');
	}
}

$( "#closeSelectVehicleForHPLayer" ).click(function() {
	$( "#selectVehiclesForHPLayer" ).fadeOut();
});


</script>
<%@ include file="includes/catchModal.jspf" %>