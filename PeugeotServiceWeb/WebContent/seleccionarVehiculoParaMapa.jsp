<%@ include file="includes/tryModal.jspf" %><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.prevent.SelectVehiclesForm"%><%----%><%@page import="com.tdil.ljpeugeot.prevent.model.SpeedLimit"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.beans.SpeedSelectionBean"%><%--
--%><%@page import="com.tdil.ljpeugeot.prevent.model.Vehicle"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.prevent.VehiclesSpeedLimitForm"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertiesKeys"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertyUtils"%><%--
--%><%@page import="com.tdil.ljpeugeot.CameraForm"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><% SelectVehiclesForm selectVehiclesForm = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForMapForm");%><%--
--%><script>

$( "#closeSelectVehicleForMapLayer" ).click(function() {
	$( "#selectVehiclesForMapLayer" ).fadeOut();
});

document.documentElement.className += 
(("ontouchstart" in document.documentElement) ? ' touch' : ' no-touch');
</script>
<div id="xContainer"><button id="closeSelectVehicleForMapLayer">X</button></div>
<h3>Localizar vehículo/s</h3>
<div id="tableStyle">
	<fieldset class="tableHeader">
		<label class="w1">Patente</label>
		<label class="w2">Localizar</label>
	</fieldset>
	<% for (Vehicle vehicle : selectVehiclesForm.getVehicles()) { %>
		<a href="./locateVehicleInMap.do?vehicleId=<%=vehicle.getId()%>" title="Ver Ubicación">
			<fieldset>
				<label class="w1"><%=vehicle.getDescription() %></label>
				<label class="w2"><img src="images/skin_lj_rl/webApp/car/iconos_table_getPosition.png" width="36" height="36" align="absmiddle" /></label>
			</fieldset>
		</a>
	<% } %>
</div>
<%@ include file="includes/catchModal.jspf" %>