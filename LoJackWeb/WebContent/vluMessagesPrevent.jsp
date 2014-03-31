<%@page import="com.tdil.lojack.vlu.model.VLUDataDTO"%>
<%@page import="com.tdil.lojack.struts.forms.prevent.VechiclesVLUMessagesForm"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.tdil.lojack.vlu.VLUUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.lojack.model.VLUData"%>
<%@ include file="includes/tryModal.jspf" %><%--
--%><script>

$( "#closevluMessagesLayer" ).click(function() {
	$( "#vehiclesVLUMessagesLayer" ).fadeOut();
});

</script>
<% VechiclesVLUMessagesForm selectVehiclesForm = (VechiclesVLUMessagesForm)session.getAttribute("VechiclesVLUMessagesForm");%>
<div id="xContainer"><button class="buttonLink" id="closevluMessagesLayer">X</button></div>
<h3>Mensaje de LoJack</h3>
<div id="tableStyle">
	<p class="">Si el rastreador de su vehículo requiere mantenimiento, comuníquese al 0800-122-5652</p>
	<fieldset class="tableHeader">
		<label class="w100 monoColumna">Patente</label>
	</fieldset>	
	<% for (VLUDataDTO vluData : selectVehiclesForm.getVlDataDTOs()) { %>
		<% if (!"-".equals(vluData.getMessage())) { %>
			<fieldset class="tableBody">
				<label class="w100 monoColumna"><%=vluData.getDomain() %></label>
			</fieldset>
		<% } %>
	<% } %>
</div>
<%@ include file="includes/catchModal.jspf" %>