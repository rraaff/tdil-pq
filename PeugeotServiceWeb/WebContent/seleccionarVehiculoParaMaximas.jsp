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
		<section class="modal_header">
			<h2>Seleccionar vehículos</h2>
			<button class="close" id="closeSelectVehicleForPhoneLayer">Cerrar <span></span></button>
		</section>
		<section class="modal_content apps_listing">
			<ul>
				<% for (Vehicle vehicle : selectVehiclesForm.getVehicles()) { %>
					<li><a href="javascript:editMaximas('<%=vehicle.getId()%>')" title="Ver Velocidades"><%=vehicle.getDescription() %></a></li>
				<% } %>
			</ul>
		</section>
<%@ include file="includes/catchModal.jspf" %>