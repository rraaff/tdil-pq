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
--%><script>

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

$( "#closeSelectVehicleForPhoneLayer" ).click(function() {
	$( "#selectVehiclesSpeedLayer" ).fadeOut();
});

</script>
<% SelectVehiclesForm selectVehiclesForm = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForSpeedForm");%>
<div id="xContainer"><button id="closeSelectVehicleForPhoneLayer">X</button></div>
<h3>Velocidades Máximas del vehículo</h3>
<div id="tableStyle">
	<fieldset class="tableHeader">
		<label class="w1">Patente</label>
	</fieldset>
	<% for (Vehicle vehicle : selectVehiclesForm.getVehicles()) { %>
		<a href="javascript:editMaximas('<%=vehicle.getId()%>')" title="Ver Velocidades">
			<fieldset>
				<label class="w1"><%=vehicle.getDescription() %></label>
			</fieldset>
		</a>	
	<% } %>
</div>
<%@ include file="includes/catchModal.jspf" %>