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

function editMaximas(vehicleId) {
	<%@ include file="includes/blockUI.jspf" %>
	  $('#editVehiclesSpeed').load('goToVehiculesSpeedLimits.do?vehicleId='+vehicleId, function(response, status, xhr) {
		  	<%@ include file="includes/unblockUI.jspf" %>
			  if (status == "error") {
			    errorAjax();
			  } else {
		 		 $( "#selectVehiclesSpeedLayer" ).fadeOut();
		  		centerLayer($(window), $( "#editVehiclesSpeedLayer" ));
		  		centerLayer($(window), $( "#centradorModalesEditSpeed" ));
			}
	  });
  }

$( "#closeSelectVehicleForHPLayer" ).click(function() {
	$( "#selectVehiclesForHPLayer" ).fadeOut();
});


</script>
<%@ include file="includes/catchModal.jspf" %>