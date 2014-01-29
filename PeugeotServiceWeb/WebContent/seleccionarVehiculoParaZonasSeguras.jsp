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

function editSecureZone(vehicleId) {
	<%@ include file="includes/blockUI.jspf" %>
	  $('#editSecureZone').load('goToVehiculeSecureZone.do?vehicleId='+vehicleId, function(response, status, xhr) {
		  	<%@ include file="includes/unblockUI.jspf" %>
			  if (status == "error") {
			    errorAjax();
			  } else {
		 		 $( "#selectVehiclesSecureZoneLayer" ).fadeOut();
		  		centerLayer($(window), $( "#editSecureZoneLayer" ));
		  		centerLayer($(window), $( "#centradorModalesSecureZone" ));
			}
	  });
  }

$( "#closeSelectVehicleForSecureZoneLayer" ).click(function() {
	$( "#editSecureZoneLayer" ).fadeOut();
});

</script>
<% SelectVehiclesForm selectVehiclesForm = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForSecureZoneForm");%>
<div id="xContainer"><button id="closeSelectVehicleForSecureZoneLayer">X</button></div>
<h3>Zonas Seguras del vehículo</h3>
<div id="tableStyle">
	<fieldset class="tableHeader">
		<label class="w1">Patente</label>
	</fieldset>
	<% for (Vehicle vehicle : selectVehiclesForm.getVehicles()) { %>
		<a href="javascript:editSecureZone('<%=vehicle.getId()%>')" title="Ver zonas seguras">
			<fieldset>
				<label class="w1"><%=vehicle.getDescription() %></label>
			</fieldset>
		</a>	
	<% } %>
</div>
<%@ include file="includes/catchModal.jspf" %>