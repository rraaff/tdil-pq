<%@ include file="includes/tryModal.jspf" %><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.prevent.SelectVehiclesForm"%><%----%><%@page import="com.tdil.ljpeugeot.prevent.model.SpeedLimit"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.beans.SpeedSelectionBean"%><%--
--%><%@page import="com.tdil.ljpeugeot.prevent.model.Vehicle"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.prevent.VehiclesSpeedLimitForm"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertiesKeys"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertyUtils"%><%--
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
		<section class="modal_header">
			<h2>Localizar vehículos</h2>
			<button class="close" id="closeSelectVehicleForMapLayer">Cerrar <span></span></button>
		</section>
		<section class="modal_content apps_listing">
			<ul>
				<% for (Vehicle vehicle : selectVehiclesForm.getVehicles()) { %>
					<li><a href="./locateVehicleInMap.do?vehicleId=<%=vehicle.getId()%>" title="Ver Ubicación"><%=vehicle.getDescription() %></a></li>
				<% } %>
			</ul>
		</section>
<%@ include file="includes/catchModal.jspf" %>