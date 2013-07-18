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
--%><script>

function editPhones(vehicleId) {
	<%@ include file="includes/blockUI.jspf" %>
	  $('#editVehiclesPhones').load('editVehiculePhones.do?vehicleId='+vehicleId, function(response, status, xhr) {
		  	<%@ include file="includes/unblockUI.jspf" %>
			  if (status == "error") {
			    errorAjax();
			  } else {
		 		 $( "#selectVehiclesPhonesLayer" ).fadeOut();
		  		centerLayer($(window), $( "#editVehiclesPhonesLayer" ));
		  		centerLayer($(window), $( "#centradorModalesEditPhones" ));
			}
	  });
  }

$( "#closeSelectVehicleForPhoneLayer" ).click(function() {
	$( "#selectVehiclesPhonesLayer" ).fadeOut();
});

</script>
<% SelectVehiclesForm selectVehiclesForm = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForPhonesForm");%>
<div id="xContainer"><button id="closeSelectVehicleForPhoneLayer">X</button></div>
<h3>Teléfonos de emergencia por vehículo</h3>
<div id="tableStyle">
	<fieldset class="tableHeader">
		<label class="w1">Patente</label>
		<label class="w2">Seleccionar vehículo</label>
	</fieldset>
	<% for (Vehicle vehicle : selectVehiclesForm.getVehicles()) { %>
		<fieldset>
			<label class="w1"><%=vehicle.getDescription() %></label>
			<label class="w2"><a href="javascript:editPhones('<%=vehicle.getId()%>')" title="Ver Telefonos"><img src="images/skin_lj_rl/webApp/car/iconos_table_getPosition.png" width="24" height="24" align="absmiddle" /></a></label>
		</fieldset>
	<% } %>
</div>
<%@ include file="includes/catchModal.jspf" %>