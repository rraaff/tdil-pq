<%@page import="com.tdil.lojack.struts.forms.prevent.SelectVehiclesForm"%>
<%@page import="com.tdil.lojack.prevent.model.SpeedLimit"%>
<%@page import="com.tdil.lojack.struts.forms.beans.SpeedSelectionBean"%>
<%@page import="com.tdil.lojack.prevent.model.Vehicle"%>
<%@page import="com.tdil.lojack.struts.forms.prevent.VehiclesSpeedLimitForm"%>
<%@page import="com.tdil.lojack.utils.SystemPropertiesKeys"%>
<%@page import="com.tdil.lojack.utils.SystemPropertyUtils"%>
<%@page import="com.tdil.lojack.struts.forms.CameraForm"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%>
<script>

function editPhones(vehicleId) {
	  $('#editVehiclesPhones').load('editVehiculePhones.do?vehicleId='+vehicleId, function() {
		  $( "#selectVehiclesPhonesLayer" ).fadeOut();
		  centerLayer($(window), $( "#editVehiclesPhonesLayer" ));
		});
  }

$( "#closeSelectVehicleForPhoneLayer" ).click(function() {
	$( "#selectVehiclesPhonesLayer" ).fadeOut();
});

</script>
<% SelectVehiclesForm selectVehiclesForm = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForPhonesForm");%>
<div id="changePassLayer" class="layerOnTop" style="z-index: 1500;">
<div class="registerLayerStyles editProfileLayer">
		<div class="registerLayerContent">
			<div id="xContainer"><button id="closeSelectVehicleForPhoneLayer" style="margin-left:110px;">X</button></div>
				<table>
						<% for (Vehicle vehicle : selectVehiclesForm.getVehicles()) { %>
							<tr>
								<td><%=vehicle.getDescription() %></td>
								<td><a href="javascript:editPhones('<%=vehicle.getId()%>')">Ver Telefonos</a></td>
							</tr>
						<% } %>
				</table>
			</div>
		</div>
	</div>
</div>